#!/bin/bash

mvn clean package -DskipTests
java -jar target/java-coding-assessment-db-0.0.1-SNAPSHOT.jar
