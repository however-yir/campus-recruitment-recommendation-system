#!/usr/bin/env python3
import argparse
import json
from pathlib import Path


def precision_at_k(pred, truth, k):
    pred_k = pred[:k]
    if not pred_k:
        return 0.0
    hit = sum(1 for item in pred_k if item in truth)
    return hit / float(len(pred_k))


def recall_at_k(pred, truth, k):
    if not truth:
        return 0.0
    pred_k = pred[:k]
    hit = sum(1 for item in pred_k if item in truth)
    return hit / float(len(truth))


def hit_rate_at_k(pred, truth, k):
    pred_k = pred[:k]
    return 1.0 if any(item in truth for item in pred_k) else 0.0


def normalize_predictions(raw_pred):
    pred = []
    reasons = {}
    for item in raw_pred:
        if isinstance(item, dict):
            item_id = item.get("id") or item.get("item_id") or item.get("job_id")
            if item_id is None:
                continue
            item_id = str(item_id)
            pred.append(item_id)
            reasons[item_id] = item.get("reason", "")
        else:
            pred.append(str(item))
    return pred, reasons


def load_dataset(path):
    raw = json.loads(path.read_text(encoding="utf-8"))
    if isinstance(raw, dict):
        return raw.get("rows", []), [str(item) for item in raw.get("catalog_items", [])]
    return raw, []


def coverage(predictions, truth_sets, catalog_items):
    recommended = {item for pred in predictions for item in pred}
    if catalog_items:
        catalog_size = len(set(catalog_items))
    else:
        catalog_size = len(recommended | {item for truth in truth_sets for item in truth})
    if catalog_size == 0:
        return 0.0
    return len(recommended) / float(catalog_size)


def fallback_ratio(rows, predictions, prediction_reasons):
    total = 0
    fallback = 0
    for row, pred, reasons in zip(rows, predictions, prediction_reasons):
        row_reasons = {str(k): v for k, v in row.get("reasons", {}).items()}
        row_reasons.update(reasons)
        fallback_items = {str(item) for item in row.get("fallback_items", [])}
        for item in pred:
            total += 1
            reason = str(row_reasons.get(item, ""))
            if item in fallback_items or "兜底" in reason or "fallback" in reason.lower():
                fallback += 1
    if total == 0:
        return 0.0
    return fallback / float(total)


def main():
    parser = argparse.ArgumentParser(description="Evaluate recommendation quality")
    parser.add_argument(
        "--dataset",
        default="evaluation/recommendation_dataset.sample.json",
        help="Path to evaluation dataset JSON",
    )
    parser.add_argument(
        "--k", nargs="+", type=int, default=[3, 5], help="K values to evaluate"
    )
    parser.add_argument("--output", default=None, help="Output JSON file for results")
    args = parser.parse_args()

    data_path = Path(args.dataset)
    if not data_path.exists():
        print("dataset not found:", data_path)
        return

    rows, catalog_items = load_dataset(data_path)
    results = {}
    predictions = []
    prediction_reasons = []
    truth_sets = []
    for row in rows:
        pred, reasons = normalize_predictions(row.get("predicted", []))
        predictions.append(pred)
        prediction_reasons.append(reasons)
        truth_sets.append({str(item) for item in row.get("ground_truth", [])})

    for k in args.k:
        p, r, h = 0.0, 0.0, 0.0
        for pred, truth in zip(predictions, truth_sets):
            p += precision_at_k(pred, truth, k)
            r += recall_at_k(pred, truth, k)
            h += hit_rate_at_k(pred, truth, k)
        n = max(len(rows), 1)
        results[f"precision@{k}"] = round(p / n, 4)
        results[f"recall@{k}"] = round(r / n, 4)
        results[f"hit_rate@{k}"] = round(h / n, 4)
        print(f"K={k} Precision={p/n:.4f} Recall={r/n:.4f} HitRate={h/n:.4f}")

    coverage_value = coverage(predictions, truth_sets, catalog_items)
    fallback_value = fallback_ratio(rows, predictions, prediction_reasons)
    results["coverage"] = round(coverage_value, 4)
    results["fallback_ratio"] = round(fallback_value, 4)
    print(f"Coverage={coverage_value:.4f} FallbackRatio={fallback_value:.4f}")

    if args.output:
        Path(args.output).write_text(
            json.dumps(results, indent=2, ensure_ascii=False), encoding="utf-8"
        )
        print(f"\nResults written to {args.output}")


if __name__ == "__main__":
    main()
