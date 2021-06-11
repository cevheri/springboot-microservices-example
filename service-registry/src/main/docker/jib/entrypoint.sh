#!/bin/sh

echo "The application will start in ..."
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.cevher.ms.service.registry.ServiceRegistryApplication"  "$@"
