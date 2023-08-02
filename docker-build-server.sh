#!/bin/sh

set -eux
docker build -f Dockerfile.server -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.8 .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.8
