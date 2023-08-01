#!/bin/sh

docker build -f Dockerfile.server -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.2 .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.2
