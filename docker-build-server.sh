#!/bin/sh

set -eux
docker build -f Dockerfile.server -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.6 .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:0.1.6
