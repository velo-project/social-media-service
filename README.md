# Social Media Service

The **Social Media Service** project is a microservice responsible for managing social interactions, including posts, comments, reactions, and social feeds within the Velo platform.

## Table of Contents

- [Social Media Service](#social-media-service)
  - [Table of Contents](#table-of-contents)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Environment Configuration](#environment-configuration)
  - [Usage](#usage)
    - [Running the Environment with Docker Compose](#running-the-environment-with-docker-compose)
    - [Database Migrations](#database-migrations)
      - [Creating a New Migration](#creating-a-new-migration)
  - [API](#api)
    - [gRPC](#grpc)
  - [Project Structure](#project-structure)

## Getting Started

These instructions will provide you with a copy of the project running on your local machine for development and testing purposes.

### Prerequisites

- [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/velo-project/social-media-service
   ```
2. Navigate to the project directory:
   ```sh
   cd social-media-service
   ```

### Environment Configuration

1. Create a `.env` file from the example:
   ```sh
   cp .env.example .env
   ```
2. Edit the `.env` file with your settings. The environment variables are:

- `SERVER_PORT`: Application HTTP port.
- `GRPC_PORT`: gRPC server port.
- `POSTGRES_HOST`: PostgreSQL host (do not include jdbc:postgresql://).
- `POSTGRES_PORT`: PostgreSQL port.
- `POSTGRES_DB`: Database name (e.g. social_media_services).
- `POSTGRES_USER`: Database user.
- `POSTGRES_PASSWORD`: Database password.
- `GOOGLE_CLOUD_PROJECT`: Google Cloud project name.
- `GOOGLE_CLOUD_CREDENTIALS`: Path to the Google Cloud service account credentials file.
- `GOOGLE_CLOUD_BUCKET`: Google Cloud Storage bucket name for media assets.
- `GEMINI_API_KEY`: API key used to access Gemini.
- `GEMINI_EMBEDDING_MODEL`: Name of the embedding model used for content and recommendation features.
- `SENTRY_DSN`: Sentry DSN for error tracking.
- `SENTRY_AUTH_TOKEN`: Authentication token for Sentry.
- `RSA_PUBLIC_KEY`: Path to the RSA public key used for token validation or secure communication

## Usage

### Running the Environment with Docker Compose

To start all services (application, database, RabbitMQ, Redis, and Nginx), run the following command:

```sh
docker-compose up -d
```

The application will be available at `http://localhost`.

### Database Migrations

The project uses [Flyway](https://flywaydb.org/) to manage database migrations. Migrations are automatically run when the application starts.

The migration files are located in `presentation/src/main/resources/db/migration`.

#### Creating a New Migration

To create a new migration, add a new SQL file in the migrations directory following the Flyway naming pattern: `V<VERSION>__<DESCRIPTION>.sql`.

For example: `V8__Add_new_table.sql`.

## API

### gRPC

The service exposes a gRPC API for internal communication between microservices. The service definition is in the `presentation/src/main/proto/user.proto` file.

To generate the gRPC client code, you can use `protoc` with the gRPC plugin for your programming language.

## Project Structure

The project is divided into modules, following the principles of Clean Architecture:

- **domain**: Contains the entities, value objects, and core business logic.
- **application**: Orchestrates the data flow between the `domain` and `infrastructure`. Contains the use cases (commands and queries).
- **infrastructure**: Implements the technical details, such as database repositories, email services, and cache.
- **presentation**: The outermost layer, responsible for exposing the API (REST and gRPC) and handling HTTP requests.
