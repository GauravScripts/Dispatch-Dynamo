# Dispatch-Dynamo

## Project Description 

Dispatch-Dynamo is a comprehensive microservices-based e-commerce and logistics platform built using modern cloud-native technologies. The system leverages Spring Boot microservices architecture with Spring Cloud components for service discovery (Eureka) and API Gateway for routing. The platform features an Angular frontend for rich user interactions and employs a polyglot persistence strategy with multiple databases: MySQL for transactional data, MongoDB for document storage, Neo4j for graph-based recommendations, and Elasticsearch for powerful search capabilities. 

The platform includes core business domains: user authentication, profile management, vendor services, shipping logistics, real-time chat support, feedback management, and intelligent recommendation systems. RabbitMQ enables asynchronous messaging between services, ensuring loose coupling and high scalability. Docker Compose orchestrates the entire ecosystem for seamless local development. Each microservice is independently deployable, following domain-driven design principles, enabling teams to work autonomously while maintaining system coherence. This architecture supports horizontal scaling, fault tolerance, and rapid feature development for modern e-commerce requirements.

## Architecture Overview

### Microservices Components

**Core Infrastructure:**
- **eureka-server**: Service discovery and registration using Netflix Eureka
- **api-gateway**: Spring Cloud Gateway for routing, load balancing, and cross-cutting concerns
- **product-webapp**: Angular 15+ frontend application with responsive design

**Business Services:**
- **authentication-services**: JWT-based authentication, user registration, and authorization
- **user-profile**: User profile management, preferences, and account settings
- **vendor-service**: Vendor management, product catalog, and Elasticsearch integration
- **shipping-services**: Logistics management, shipment tracking, and delivery workflows
- **chat-service**: Real-time messaging system with WebSocket support
- **feedback-service**: Review and rating system for products and services
- **Recommendation-Service**: AI-powered recommendation engine using Neo4j graph database
- **MailServiceApp**: Email notification service for transactional communications

### Data Layer Architecture

**Database Technologies:**
- **MySQL**: Primary relational database for transactional data (users, orders, inventory)
- **MongoDB**: Document database for flexible schemas (chat messages, user sessions, logs)
- **Neo4j**: Graph database for complex relationships and recommendation algorithms
- **Elasticsearch**: Search engine for product discovery, analytics, and full-text search
- **RabbitMQ**: Message broker for asynchronous communication and event-driven architecture

### Technology Stack

**Backend Technologies:**
- Java 11/17
- Spring Boot 2.7+
- Spring Cloud (Gateway, Eureka, Config)
- Spring Data JPA, MongoDB, Neo4j
- Maven for dependency management
- Docker for containerization

**Frontend Technologies:**
- Angular 15+
- TypeScript
- RxJS for reactive programming
- Angular Material for UI components
- WebSocket for real-time features

**DevOps & Infrastructure:**
- Docker Compose for local orchestration
- RESTful APIs with OpenAPI documentation
- Health checks and monitoring endpoints
- Centralized logging and error handling

## Getting Started

### Prerequisites
- Docker Desktop (minimum 8GB RAM recommended)
- Java 11 or higher (for local development)
- Node.js 16+ and npm (for Angular development)
- Git

### Quick Start with Docker

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd Dispatch-Dynamo
   ```

2. **Start the entire stack:**
   ```bash
   docker-compose up --build
   ```

3. **Access the applications:**
   - Angular Web App: http://localhost:4200
   - Eureka Dashboard: http://localhost:8761
   - API Gateway: http://localhost:8086
   - RabbitMQ Management: http://localhost:15672 (guest/guest)
   - Elasticsearch: http://localhost:9200

### Service Ports Reference

| Service | Port | Description |
|---------|------|-------------|
| product-webapp | 4200 | Angular frontend application |
| api-gateway | 8086 | Main API gateway |
| eureka-server | 8761 | Service discovery |
| authentication-services | 9201 | Authentication service |
| MailServiceApp | 9202 | Email service |
| feedback-service | 7003 | Feedback and reviews |
| Recommendation-Service | 8082 | Recommendation engine |
| vendor-service | 8083 | Vendor management |
| user-profile | 8085 | User profile service |
| chat-service | 9205 | Chat and messaging |
| shipping-services | 7575 | Shipping and logistics |
| MySQL | 3306 | Primary database |
| MongoDB | 27017 | Document database |
| Neo4j | 7474/7687 | Graph database |
| Elasticsearch | 9200/9300 | Search engine |
| RabbitMQ | 5672/15672 | Message broker |

### Local Development (Without Full Docker)

For development purposes, you can run infrastructure services in Docker and run application services locally:

1. **Start infrastructure services:**
   ```bash
   docker-compose up mysql mongo-container neo4jService rabbitmq elasticsearch
   ```

2. **Build and run individual services:**
   ```bash
   # Navigate to any service directory
   cd authentication-services
   ./mvnw spring-boot:run
   ```

3. **Run Angular frontend:**
   ```bash
   cd product-webapp/webapp
   npm install
   ng serve
   ```

## Environment Configuration

### Database Configuration
- **MySQL**: 
  - Database: CargoPluse
  - Username: root
  - Password: root
- **Neo4j**: 
  - Username: neo4j
  - Password: gaurav@1234
  - Database: users
- **RabbitMQ**: 
  - Username: guest
  - Password: guest

### Environment Variables
Key environment variables are defined in docker-compose.yml. For production deployments, use secure credential management.

## Code Structure

Each microservice follows a standard Spring Boot layered architecture:

```
service-name/
├── src/main/java/
│   ├── controller/     # REST endpoints
│   ├── service/        # Business logic
│   ├── repository/     # Data access layer
│   ├── model/          # Entity classes
│   ├── dto/            # Data transfer objects
│   └── config/         # Configuration classes
├── src/main/resources/
│   ├── application.yml # Service configuration
│   └── static/         # Static resources
└── Dockerfile          # Container configuration
```

### Frontend Structure (Angular)
```
webapp/src/
├── app/
│   ├── components/     # UI components
│   ├── services/       # API services
│   ├── guards/         # Route guards
│   ├── interceptors/   # HTTP interceptors
│   ├── models/         # TypeScript interfaces
│   └── environments/   # Environment configs
├── assets/             # Static assets
└── styles/             # Global styles
```

## API Documentation

Each microservice exposes RESTful APIs. Common endpoints:
- `/actuator/health` - Health check
- `/actuator/info` - Service information
- Service-specific endpoints documented in individual service README files

## Development Workflow

1. **Service Development:**
   - Each service follows standard Spring Boot structure
   - Controllers handle HTTP requests
   - Services contain business logic
   - Repositories manage data access
   - DTOs for data transfer

2. **Frontend Development:**
   - Component-based architecture
   - Services for API communication
   - Guards for route protection
   - Interceptors for authentication

3. **Testing:**
   - Unit tests with JUnit and Mockito
   - Integration tests with TestContainers
   - Frontend tests with Jasmine and Karma

## Troubleshooting

### Common Issues

**Angular TensorFlow Error:**
If you encounter the following error while running ng serve:
```
Error: node_modules/@tensorflow/tfjs-core/dist/hash_util.d.ts:4:49 - error TS2304: Cannot find name 'Long'.
```

**Solution:** Add `import * as Long from "long";` in hash_util.d.ts file before ng serve

**Docker Network Issues (Windows/Mac):**
This compose file uses `network_mode: "host"` which works natively on Linux. On Windows/Mac:
- Use WSL2 backend for Docker Desktop
- Or modify docker-compose.yml to use bridge networking

**Memory Issues:**
Elasticsearch and Neo4j require sufficient memory. Increase Docker Desktop memory allocation to at least 8GB.

## Demo Videos

The following demonstration videos showcase the platform's capabilities:

https://s10.gifyu.com/images/homedf4c4bab03fd9fc4.gif
<hr>

https://s10.gifyu.com/images/Video_230314132832.gif
<hr>

https://s2.gifyu.com/images/Video_230314133930.gif
<hr>

https://s10.gifyu.com/images/Video_230314134518.gif
<hr>

https://s2.gifyu.com/images/Video_230314134831Segment1.gif
<hr>

https://s2.gifyu.com/images/Video_230314134831Segment1-2.gif

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
