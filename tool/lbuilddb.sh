#!/bin/bash
#Wywoływać z tego katalogu
cd ../isoman-db-config 
mvn clean install -Ddb.config.skip=false -Ddb.init.data.skip=false -Ddb.ledge-security.config.skip=false
