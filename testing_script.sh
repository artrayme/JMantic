#!/bin/bash

sctp() {
  cd /ostis/scripts
  ./run_sctp.sh &
}

sc_web() {
  cd /ostis/sc-machine/web/client
  yarn run webpack-dev

  cd /ostis/scripts
  echo "\n\e[1;32mStarting the old sc-web on http://localhost:8000 and the new on http://localhost:8090...\e[0m\n"
  ./run_scweb.sh &
}

sctp
sc_web

sh ./gradlew test
