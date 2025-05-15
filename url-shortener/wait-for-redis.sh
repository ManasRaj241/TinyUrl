#!/bin/sh

echo "Waiting for Redis to be available at $REDIS_HOST:$REDIS_PORT..."

# Extended Redis check with more verbose output
MAX_RETRIES=30
RETRIES=0

until [ $RETRIES -eq $MAX_RETRIES ]; do
  echo "Attempt $((RETRIES+1))/$MAX_RETRIES: Testing Redis connection..."

  # Try simple ping first
  if redis-cli -h $REDIS_HOST -p $REDIS_PORT PING | grep -q PONG; then
    echo "✅ Redis responded to PING"

    # Add a more comprehensive check
    if redis-cli -h $REDIS_HOST -p $REDIS_PORT INFO | grep -q "redis_version"; then
      echo "✅ Redis INFO command successful"
      echo "Redis is up and responding to commands."
      break
    else
      echo "❌ Redis INFO command failed - server might not be fully initialized"
    fi
  else
    echo "❌ Redis PING failed - server not responding"
  fi

  RETRIES=$((RETRIES+1))
  echo "Waiting 2 seconds before next attempt..."
  sleep 2
done

if [ $RETRIES -eq $MAX_RETRIES ]; then
  echo "Redis did not become available after $MAX_RETRIES attempts. Exiting."
  exit 1
fi

# Add useful information for debugging
echo "Redis connection verified successfully"
echo "Getting Redis server info:"
redis-cli -h $REDIS_HOST -p $REDIS_PORT INFO server | grep -v "#"
echo "Testing network connectivity:"
ping -c 2 $REDIS_HOST

echo "Starting the Spring Boot application..."

# Run with explicit system properties for Redis
exec java \
  -Dspring.redis.host=$REDIS_HOST \
  -Dspring.redis.port=$REDIS_PORT \
  -Dspring.redis.timeout=30000 \
  -Dlogging.level.org.springframework.data.redis=DEBUG \
  -Dlogging.level.io.lettuce.core=DEBUG \
  -jar app.jar