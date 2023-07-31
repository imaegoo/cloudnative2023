#!/bin/sh

docker build -f Dockerfile.server -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:latest .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:latest
