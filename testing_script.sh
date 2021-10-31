#!/bin/bash
sh ../ostis/scripts/ostis --sctp --web &
sleep 1
gradle test
exit 1

