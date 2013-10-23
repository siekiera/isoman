#!/bin/bash
set JPDA_TRANSPORT=dt_socket set JPDA_ADDRESS=8787
catalina.sh jpda start #-Xdebug -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n
