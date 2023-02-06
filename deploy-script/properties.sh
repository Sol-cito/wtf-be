PROFILE=$1

if [ "${PROFILE}" == "dev" ]; then
  export ADDRESS="localhost"
  export PORT=84 # NGINX local PORT
  export BLUE_PORT=8095
  export GREEN_PORT=8096
  export HTTP_TYPE="http"
elif [ "${PROFILE}" == "prod" ]; then
  export ADDRESS="localhost"
  export PORT=85 # NGINX local PORT
  export BLUE_PORT=8097
  export GREEN_PORT=8098
  export HTTP_TYPE="https"
fi

echo "> Properties / PROFILE = ${PROFILE}"
echo "> Properties / ADDRESS = ${ADDRESS}"
echo "> Properties / PORT = ${PORT}"
echo "> Properties / BLUE_PORT = ${BLUE_PORT}"
echo "> Properties / GREEN_PORT = ${GREEN_PORT}"
echo "> Properties / HTTP_TYPE = ${HTTP_TYPE}"
