FROM openjdk:17-alpine

WORKDIR /app

# Install Redis CLI and other network diagnostic tools
RUN apk add --no-cache redis iputils curl busybox-extras

# Copy the wait-for-redis script
COPY wait-for-redis.sh wait-for-redis.sh
RUN chmod +x wait-for-redis.sh

# Copy the application JAR
COPY build/libs/app.jar app.jar

EXPOSE 1111

# Healthcheck to verify the application can connect to Redis
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD redis-cli -h $REDIS_HOST -p $REDIS_PORT PING || exit 1

ENTRYPOINT ["./wait-for-redis.sh"]