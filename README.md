# Architecture Microservices Spring Boot

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=for-the-badge&logo=spring)
![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.1-blue?style=for-the-badge&logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.8+-red?style=for-the-badge&logo=apache-maven)

## Description

Architecture microservices basée sur **Spring Boot** et **Spring Cloud** avec **Netflix Eureka** pour la découverte de services et **Spring Cloud Gateway** pour le routage des requêtes.

## Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                         CLIENT / POSTMAN                        │
└────────────────────────────┬────────────────────────────────────┘
                              │
                              ▼
                    ┌──────────────────┐
                    │   API GATEWAY    │
                    │   (Port 8080)    │
                    └────────┬─────────┘
                             │
                ┌────────────┴────────────┐
                │                         │
                ▼                         ▼
    ┌──────────────────────┐   ┌──────────────────────┐
    │   EUREKA SERVER      │   │  STUDENT SERVICE     │
    │   (Port 8761)        │   │  (Port 8081)         │
    └──────────────────────┘   └──────────────────────┘
                ▲                         │
                └───────── Enregistrement ┘
```

## Services et Ports

| Service | Port | URL |
|---------|------|-----|
| Eureka Server | 8761 | http://localhost:8761 |
| API Gateway | 8080 | http://localhost:8080 |
| Student Service | 8081 | http://localhost:8081 |

## Technologies

- Java 17
- Spring Boot 3.2.5
- Spring Cloud 2023.0.1
- Spring Cloud Gateway
- Netflix Eureka
- Maven 3.8+

## Prérequis

- Java JDK 17 ou supérieur
- Maven 3.8+
- Git

## Installation

```bash
git clone    https://github.com/Maxaldo/microservices-springboot
cd microservice-springboot
mvn clean install
```

## Lancement

**Important :** Démarrer les services dans l'ordre suivant :

### 1. Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

Vérifier : http://localhost:8761

### 2. Student Service

```bash
cd student-service
mvn spring-boot:run
```

### 3. API Gateway

```bash
cd ApiGetWay
mvn spring-boot:run
```

### Vérification

- Eureka Dashboard : http://localhost:8761 (vérifier que `STUDENT-SERVICE` et `APIGETWAY` sont enregistrés)
- API Gateway : http://localhost:8080
- Student Service : http://localhost:8081

## Endpoints API

### Via API Gateway

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `http://localhost:8080/STUDENT-SERVICE/student/hello` | Message de test |

**Format :** `http://localhost:8080/{SERVICE-NAME}/{endpoint}`

### Accès Direct

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `http://localhost:8081/student/hello` | Message de test |

### Exemple cURL

```bash
curl http://localhost:8080/STUDENT-SERVICE/student/hello
curl http://localhost:8081/student/hello
```

## Configuration

Les fichiers de configuration se trouvent dans `src/main/resources/application.properties` de chaque service :

- **Eureka Server** : Port 8761, désactivation de l'auto-enregistrement
- **API Gateway** : Port 8080, connexion à Eureka
- **Student Service** : Port 8081, enregistrement auprès d'Eureka

## Structure du Projet

```
microservice-springboot/
├── ApiGetWay/              # API Gateway
├── eureka-server/          # Service Discovery
└── student-service/        # Service métier
```

## Dépannage

**Service non enregistré dans Eureka**
- Vérifier que Eureka Server est démarré en premier
- Vérifier l'URL dans `application.properties`

**API Gateway ne route pas les requêtes**
- Vérifier que le service est enregistré dans Eureka
- Utiliser le format : `http://localhost:8080/{SERVICE-NAME}/{endpoint}` (nom en MAJUSCULES)

**Port déjà utilisé**
- Modifier le port dans `application.properties` ou arrêter le processus utilisant le port

## Auteur

**Max Ronaldo SOGBOSSI**
