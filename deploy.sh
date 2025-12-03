pm2 delete social-media-service || true
export $(grep -v '^#' .env | xargs) && pm2 start "java -jar ./presentation/build/libs/presentation-0.0.1-SNAPSHOT.jar" --name social-media-service