FROM node:lts-alpine as builder

ADD ./web/ /root/web/
WORKDIR /root/web/
RUN yarn install && yarn run build

FROM nginx:alpine

COPY --from=builder /root/web/build/ /usr/share/nginx/html/
EXPOSE 80
