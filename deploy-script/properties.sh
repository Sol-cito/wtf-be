PROFILE=$1

# import functions
source ./functions.sh

if [ "${PROFILE}" == "dev" ]; then
  export ADDRESS="localhost"
  export PORT=84 # NGINX local PORT
  export BLUE_PORT=8095
  export GREEN_PORT=8096
elif [ "${PROFILE}" == "prod" ]; then
  export ADDRESS="localhost"
  export PORT=85 # NGINX local PORT
  export BLUE_PORT=8097
  export GREEN_PORT=8098
fi

export IDLE_SERVICE_NAME=$(find_idle_service_name "${ADDRESS}" "${PORT}" "${BLUE_PORT}" "${GREEN_PORT}")
if [ "${IDLE_SERVICE_NAME}" == "blue" ]; then
  export IDLE_PORT=${GREEN_PORT}
  export CURRENT_SERVICE_NAME="green"
  export CURRENT_PORT=${BLUE_PORT}
else
  export IDLE_PORT=${BLUE_PORT}
  export CURRENT_SERVICE_NAME="blue"
  export CURRENT_PORT=${GREEN_PORT}
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
