#!/bin/sh

set -eux
rev=$(git rev-parse --short HEAD)
docker build -f Dockerfile.server -t registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:$rev .
docker push registry.cn-hangzhou.aliyuncs.com/imaegoo/cloudnative2023server:$rev
