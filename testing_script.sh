#!/bin/bash

sctp() {
  cd /ostis/scripts
  ./run_sctp.sh &
}

sc_web() {
  cd /ostis/sc-machine/web/client
  yarn run webpack-dev

  cd /ostis/scripts
  echo "\n\e[1;32mStarting the new sc_web on http://localhost:8090...\e[0m\n"
  ./run_scweb.sh &
}

sctp
sc_web
cd /JMantic
git pull
gradle test
