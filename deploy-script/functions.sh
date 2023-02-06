function find_idle_service_name() {
  CUR_PROFILE=$(curl -s -k "${1}://${2}:${3}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "green"
  else
    echo "blue"
  fi
}

function find_idle_port() {
  CUR_PROFILE=$(curl -s -k "${1}://${2}:${3}/profile")

  echo "> find_idle_port function result (current running profile) : ${CUR_PROFILE}"

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "${5}"
  else
    echo "${4}"
  fi
}