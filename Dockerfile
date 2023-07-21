FROM hub-mirror.c.163.com/node:lts-alpine as builder

ADD ./web/ /root/
WORKDIR /root/web/
RUN yarn install
RUN yarn run build

FROM hub-mirror.c.163.com/nginx:alpine

COPY --from=builder /root/web/build/ /usr/share/nginx/html/
EXPOSE 80
