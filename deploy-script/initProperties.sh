# import functions
source ./functions.sh

if [ "${PROFILE}" == "dev" ]; then
  ADDRESS="localhost"
  PORT=84 # NGINX local PORT
  BLUE_PORT=8095
  GREEN_PORT=8096
elif [ "${PROFILE}" == "prod" ]; then
  ADDRESS="localhost"
  PORT=85 # NGINX local PORT
  BLUE_PORT=8097
  GREEN_PORT=8098
fi

IDLE_SERVICE_NAME=$(find_idle_service_name "${ADDRESS}" "${PORT}" "${BLUE_PORT}" "${GREEN_PORT}")
if [ "${IDLE_SERVICE_NAME}" == "blue" ]; then
  IDLE_PORT="${BLUE_PORT}"
  CURRENT_SERVICE_NAME="green"
  CURRENT_PORT="${GREEN_PORT}"
else
  IDLE_PORT="${GREEN_PORT}"
  CURRENT_SERVICE_NAME="blue"
  CURRENT_PORT="${BLUE_PORT}"
fi

echo "> Properties / PROFILE = ${PROFILE}"
echo "> Properties / ADDRESS = ${ADDRESS}"
echo "> Properties / PORT = ${PORT}"
echo "> Properties / BLUE_PORT = ${BLUE_PORT}"
echo "> Properties / GREEN_PORT = ${GREEN_PORT}"
echo "> Properties / IDLE_SERVICE_NAME = ${IDLE_SERVICE_NAME}"
echo "> Properties / IDLE_PORT = ${IDLE_PORT}"
echo "> Properties / CURRENT_SERVICE_NAME = ${CURRENT_SERVICE_NAME}"
echo "> Properties / CURRENT_PORT = ${CURRENT_PORT}"
