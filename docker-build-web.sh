#!/bin/sh

set -eux
rev=$(git rev-parse --short HEAD)
docker build -f Dockerfile.web -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023:$rev .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023:$rev
