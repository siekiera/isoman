#!/bin/bash
cd isoman-db-config
mvn clean install -Ddb.config.skip=false -Ptest