function check_profile_status() {
  RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" "http://${1}:${2}/getProfile")
  echo "${RESPONSE_CODE}"
}

function find_current_service_name() {
  RESPONSE_CODE=$(check_profile_status $ADDRESS $PORT $BLUE_PORT)

  if [ "${RESPONSE_CODE}" == 200 ]; then
    CURRENT_PROFILE=${1}
  else
    CURRENT_PROFILE=$(curl -s "http://${2}:${3}/getProfile")
  fi
  echo "${CURRENT_PROFILE}"
}

function find_idle_port() {
  if [ "${1}" == "${2}" ]; then
    echo "${3}"
  else
    echo "${4}"
  fi
}

ADDRESS="localhost"

PORT=8096

BLUE_NAME="blue"
GREEN_NAME="green"
BLUE_PORT=8095
GREEN_PORT=8096

RESPONSE_CODE=$(check_profile_status $ADDRESS $PORT $BLUE_PORT)

CURRENT_PROFILE=$(find_current_service_name $BLUE_NAME $ADDRESS $PORT)

IDLE_PORT=$(find_idle_port "$CURRENT_PROFILE" $BLUE_NAME $BLUE_PORT $GREEN_PORT)

echo "${RESPONSE_CODE}"
echo "${CURRENT_PROFILE}"
echo "${IDLE_PORT}"
