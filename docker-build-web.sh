#!/bin/sh

set -eux
docker build -f Dockerfile.web -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023:0.1.4 .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023:0.1.4
