#!/bin/bash

java \
    -javaagent:agent/skywalking/skywalking-agent.jar \
    -Dskywalking.agent.service_name=${SW_SERVICE_NAME:-deepexi-spring-cloud} \
    -Dskywalking.collector.backend_service=${SW_SERVICE_ADDR} \
    -jar app.jar $@
