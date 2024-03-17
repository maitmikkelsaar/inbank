# Inbank REST API

# Inbank App with Vue and Spring Boot
## Build inbank-api image from root directory
```sh
cd inbank-api
./gradlew :jibDockerBuild
```

## Build inbank-frontend image from root directory
```sh
cd inbank-frontend
docker build -t inbank-frontend .
```

## Run containers from root directory
```sh
docker-compose up -d
```

## Run inbank-api locally with dev profile (CORS config)
```sh
cd inbank-api
./gradlew :bootRun --args='--spring.profiles.active=dev'
```

## Install the packages for inbank-frontend
```sh
cd inbank-frontend
npm install
```

## Run inbank-frontend locally
```sh
cd inbank-frontend
npm run dev
```