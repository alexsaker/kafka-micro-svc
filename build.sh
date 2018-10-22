#!/bin/sh
cd product && ./gradlew clean build;
cd ../productstore && ./gradlew clean build;
