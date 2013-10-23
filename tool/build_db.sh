#!/bin/bash
cd isoman-db-config
mvn clean install -Ddb.config.skip=false -Ddb.init.data.skip=false