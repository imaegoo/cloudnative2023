FROM node:lts-alpine as builder

ADD ./web/ /root/
WORKDIR /root/web/
RUN yarn install
RUN yarn run build

FROM nginx:alpine

COPY --from=builder /root/web/build/ /usr/share/nginx/html/
EXPOSE 80
