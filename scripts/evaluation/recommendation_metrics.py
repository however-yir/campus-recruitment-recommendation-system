#!/usr/bin/env python3
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


def main():
    data_path = Path("evaluation/recommendation_dataset.sample.json")
    if not data_path.exists():
        print("dataset not found:", data_path)
        return

    rows = json.loads(data_path.read_text(encoding="utf-8"))
    ks = [3, 5]
    for k in ks:
        p, r, h = 0.0, 0.0, 0.0
        for row in rows:
            pred = row.get("predicted", [])
            truth = set(row.get("ground_truth", []))
            p += precision_at_k(pred, truth, k)
            r += recall_at_k(pred, truth, k)
            h += hit_rate_at_k(pred, truth, k)
        n = max(len(rows), 1)
        print(f"K={k} Precision={p/n:.4f} Recall={r/n:.4f} HitRate={h/n:.4f}")


if __name__ == "__main__":
    main()
