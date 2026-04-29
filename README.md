🎬 MovieFlix API

API RESTful para gerenciamento de filmes, categorias e serviços de streaming, com autenticação via JWT e arquitetura em camadas utilizando Spring Boot 3.

🚀 Tecnologias Utilizadas
Java 17
Spring Boot 3
Spring Web
Spring Data JPA
Spring Security
JWT (java-jwt 4.4.0)
PostgreSQL
Flyway
Bean Validation
Lombok
OpenAPI / Swagger (springdoc)
📌 Funcionalidades
🎭 Categorias
Criar categoria
Listar categorias
Buscar categoria por ID
Remover categoria
🎥 Filmes
Criar filme
Listar filmes
Buscar filme por ID
Atualizar filme
Remover filme
Buscar filmes por categoria
📺 Streaming
Criar serviço de streaming
Listar serviços
Buscar por ID
Remover serviço
👤 Usuários
Registro de usuário
Login com autenticação JWT
🔐 Segurança
Autenticação via JWT
Proteção de rotas
Password criptografado com BCrypt
🧱 Arquitetura do Projeto
Controller → Service → Repository → Database
                    ↓
             Security (JWT)
                    ↓
        Exception Handling (ControllerAdvice)
⚙️ Como executar o projeto
1. Clonar o repositório
git clone https://github.com/seu-usuario/movieflix.git
cd movieflix
2. Configurar o banco PostgreSQL

Crie um banco:

CREATE DATABASE movieflix;
3. Configurar application.yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movieflix
    username: postgres
    password: postgres

  jpa:
    show-sql: true
    hibernate:
      ddl-auto: validate

  flyway:
    enabled: true
4. Rodar a aplicação
./mvnw spring-boot:run

ou

mvn spring-boot:run
🔐 Autenticação (JWT)
🔑 Login
POST /auth/login
📥 Body
{
  "email": "user@email.com",
  "password": "123456"
}
📤 Response
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
🔒 Usar token

Em requisições protegidas:

Authorization: Bearer SEU_TOKEN
📚 Documentação Swagger

Após rodar o projeto:

http://localhost:8080/swagger-ui.html
🧪 Exemplos de Endpoints
🎬 Movies
GET /movies
POST /movies
GET /movies/{id}
PUT /movies/{id}
DELETE /movies/{id}
GET /movies/category/{id}
🎭 Categories
GET /categories
POST /categories
GET /categories/{id}
DELETE /categories/{id}
📺 Streaming
GET /streamings
POST /streamings
GET /streamings/{id}
DELETE /streamings/{id}
🛡️ Segurança
Spring Security configurado com filtro JWT
Senhas criptografadas com BCrypt
Rotas protegidas por autenticação
Stateless session
⚠️ Validações e Exceptions
Bean Validation (@NotNull, @NotBlank, etc.)
Tratamento global de erros com @RestControllerAdvice
Exception personalizada para login inválido
🗂️ Migrations (Flyway)

O banco é versionado automaticamente com Flyway:

src/main/resources/db/migration
📦 Dependências principais
Spring Boot Starter Web
Spring Boot Starter Data JPA
Spring Boot Starter Security
PostgreSQL Driver
Flyway
Lombok
Java JWT
Springdoc OpenAPI
👨‍💻 Autor

Desenvolvido como projeto de estudo backend Java com foco em arquitetura profissional e boas práticas.
