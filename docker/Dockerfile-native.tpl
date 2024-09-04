FROM alpine:edge as builder
COPY target/ani-rss /usr/app/ani-rss
WORKDIR /usr/app
VOLUME /config
ENV PORT="7789"
ENV CONFIG="/config"
ENV TZ="Asia/Shanghai"
EXPOSE 7789
CMD ["./ani-rss"]