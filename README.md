# Blusalt Drone API

## Overview

A RESTful API for managing drones and medication deliveries.

### Features

Register drones
Load medications onto drones
Check loaded medications
Check available drones
Monitor drone battery levels

### Tech Stack

Java 21, Spring Boot 3.4.4
Spring Data JPA, Hibernate, Flyway
H2 (local), Redis (caching)
SpringDoc (Swagger), Docker

**Setup and Run instructions**

Clone & Start
git clone https://github.com/T-Tek/bluesalt-drone.git
cd blusalt-drone
mvn clean spring-boot:run
Run with Docker

Run with Docker
docker-compose up -d  # for starting Redis  
docker build -t blusaltdrone .  
docker run -p 9041:9041 blusaltdrone  

Run Tests
mvn test


### API Endpoints
Method		Endpoint			            Description
POST	/api/v1/drones/register			    Register a drone
POST	/api/v1/drones/{id}/load		    Load medications
GET 	/api/v1/drones/{id}/medications		Get loaded medications
GET	    /api/v1/drones/available		    Available drones
GET	    /api/v1/drones/{id}/battery		    Check battery


Swagger UI:
http://localhost:9041/swagger-ui.html

### Configuration

Application Properties
server.port=9041  
spring.datasource.url=jdbc:h2:mem:drone_db  
spring.flyway.enabled=true  
spring.cache.type=redis  
spring.data.redis.host=localhost  
spring.data.redis.port=6379  


### Notes
_Disable caching if Redis is not running: spring.cache.type=none_

Thank you