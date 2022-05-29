#!/bin/bash
ENV=${1}
export SPRING_PROFILES_ACTIVE=${ENV}
export JASYPT_ENCRYPTOR_PASSWORD=supersecretz-${ENV}
mvn spring-boot:run
