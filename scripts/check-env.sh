#!/usr/bin/env bash
set -euo pipefail

ENV_FILE="${1:-.env}"
ERRORS=0

if [ ! -f "$ENV_FILE" ]; then
  echo "ERROR: $ENV_FILE not found. Copy .env.example and fill in values."
  echo "  cp .env.example .env"
  exit 1
fi

check_var() {
  local var="$1"
  local required="${2:-true}"
  local val
  val=$(grep "^${var}=" "$ENV_FILE" | cut -d= -f2- || true)

  if [ -z "$val" ]; then
    if [ "$required" = "true" ]; then
      echo "ERROR: $var is not set"
      ERRORS=$((ERRORS + 1))
    else
      echo "SKIP: $var is not set (optional)"
    fi
    return
  fi

  if echo "$val" | grep -qE '<.*>|CHANGE_ME|123456|password$'; then
    echo "WARNING: $var looks like a placeholder ('$val')"
    ERRORS=$((ERRORS + 1))
  fi
}

echo "Checking environment variables in $ENV_FILE ..."
echo ""

check_var DB_URL
check_var DB_USERNAME
check_var DB_PASSWORD
check_var AI_ASSISTANT_API_KEY false

echo ""
if [ "$ERRORS" -gt 0 ]; then
  echo "Found $ERRORS issue(s). Fix them before starting."
  exit 1
fi

echo "Environment check passed."
