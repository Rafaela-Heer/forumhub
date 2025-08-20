<h1 align="center">ForumHub</h1>
<h2 align="center">API REST de um fórum de tópicos construída com Spring Boot 3, Java 21, MySQL 8, JPA/Hibernate, Flyway e JWT (Spring Security).</h2>

## Tecnologias
- Java 21
- Spring Boot 3.5.x
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-security
  - validation (Jakarta Validation)
- MySQL 8
- Flyway (migrações em `src/main/resources/db/migration`)

## Pré-Requisitos
- Java 21 instalado (`java -version`)
- MySQL 8 em execução
- Nenhuma necessidade de Maven instalado — use o wrapper do projeto (`mvnw`/`mvnw.cmd`)
- (Opcional) Insomnia ou Postman

## Configuração do banco
1. Crie o banco e o usuário (ajuste a senha como preferir):
```sql
   CREATE DATABASE forumhub CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

CREATE USER 'forum'@'localhost' IDENTIFIED BY 'forum';
GRANT ALL PRIVILEGES ON forumhub.* TO 'forum'@'localhost';
FLUSH PRIVILEGES;
```
2. Garanta que o MySQL está acessível

## Como rodar o projeto
No Windows (PowerShell) pela wrapper do Maven:
``` powershell
.\mvnw spring-boot:run
```
Se `mvn` não é reconhecido, sempre use `.\mvnw` (a wrapper já está no projeto).
- App sobe em: `http://localhost:8080`

## Estrutura do projeto 
```
src/main/java/com/project/forumhub
├─ domain/
│  ├─ Topic.java
│  └─ User.java
├─ repository/
│  └─ TopicRepository.java
├─ security/
│  ├─ SecurityConfig.java
│  ├─ JwtAuthFilter.java
│  └─ TokenService.java
├─ web/
│  ├─ TopicController.java
│  ├─ AuthController.java
│  └─ dto/
│     ├─ TopicCreateRequest.java
│     ├─ TopicResponse.java
│     └─ Login{Request,Response}.java
└─ ForumhubApplication.java

src/main/resources
├─ application.properties
└─ db/migration
   └─ V1__init.sql
```

## Desenvolvedora:
 ### Rafaela Heer Robinson (rafaelaheerr@hotmail.com)
 
