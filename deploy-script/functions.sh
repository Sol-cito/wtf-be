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
    echo "${4}"
  else
    echo "${3}"
  fi
}