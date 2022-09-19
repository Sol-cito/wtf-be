function find_idle_service_name() {
  CUR_PROFILE=$(curl -s "http://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "green"
  else
    echo "blue"
  fi
}

function find_idle_port() {
  CUR_PROFILE=$(curl -s "http://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo ${4}
  else
    echo ${3}
  fi
}

ADDRESS="localhost"

PORT=8080

BLUE_PORT=8095
GREEN_PORT=8096

IDLE_PROFILE=$(find_idle_service_name $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)

IDLE_PORT=$(find_idle_port $ADDRESS $PORT $BLUE_PORT $GREEN_PORT)

echo "${IDLE_PROFILE}"
echo "${IDLE_PORT}"

