function find_idle_service_name() {
  CUR_PROFILE=$(curl -s -k "${5}://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "green"
  elif [ "${CUR_PROFILE}" == "green" ]; then
    echo "blue"
  else
    echo "nothing"
  fi
}

function find_idle_port() {
  CUR_PROFILE=$(curl -s -k "${5}://${1}:${2}/profile")

  if [ "${CUR_PROFILE}" == "blue" ]; then
    echo "${4}"
  elif [ "${CUR_PROFILE}" == "green" ]; then
    echo "${3}"
  else
    echo "nothing"
  fi
}