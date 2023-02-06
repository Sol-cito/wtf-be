PROFILE=$1

if [ "${PROFILE}" == "dev" ]; then
  export HTTP_TYPE="http"
  export ADDRESS="localhost"
  export PORT=84 # NGINX local PORT
  export BLUE_PORT=8095
  export GREEN_PORT=8096
elif [ "${PROFILE}" == "prod" ]; then
  export HTTP_TYPE="https"
  export ADDRESS="localhost"
  export PORT=85 # NGINX local PORT
  export BLUE_PORT=8097
  export GREEN_PORT=8098
fi

echo "> Properties / HTTP_TYPE = ${HTTP_TYPE}"
echo "> Properties / PROFILE = ${PROFILE}"
echo "> Properties / ADDRESS = ${ADDRESS}"
echo "> Properties / PORT = ${PORT}"
echo "> Properties / BLUE_PORT = ${BLUE_PORT}"
echo "> Properties / GREEN_PORT = ${GREEN_PORT}"
