# MovieFlix API

API REST em Java 17 + Spring Boot 3 (Web, Security, Data JPA, Validation), PostgreSQL, Flyway, JWT e Swagger.

## Como rodar

```bash
docker compose up -d      # sobe o PostgreSQL
./mvnw spring-boot:run    # ou: mvn spring-boot:run
```

- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Variável opcional: `JWT_SECRET` (use um segredo forte em produção)

## Endpoints

| Método | Rota | Auth |
|---|---|---|
| POST | /movieflix/auth/register | pública |
| POST | /movieflix/auth/login | pública |
| GET, POST | /movieflix/category | JWT |
| GET, DELETE | /movieflix/category/{id} | JWT |
| GET, POST | /movieflix/streaming | JWT |
| GET, DELETE | /movieflix/streaming/{id} | JWT |
| GET, POST | /movieflix/movie | JWT |
| GET, PUT, DELETE | /movieflix/movie/{id} | JWT |
| GET | /movieflix/movie/search?category={id} | JWT |

## Exemplos

```bash
curl -X POST localhost:8080/movieflix/auth/register -H 'Content-Type: application/json' \
  -d '{"name":"Maria","email":"maria@email.com","password":"123456"}'

curl -X POST localhost:8080/movieflix/auth/login -H 'Content-Type: application/json' \
  -d '{"email":"maria@email.com","password":"123456"}'

curl -X POST localhost:8080/movieflix/movie -H "Authorization: Bearer $TOKEN" -H 'Content-Type: application/json' \
  -d '{"name":"Matrix","description":"Ficção científica","releaseDate":"1999-03-31","rating":8.7,"categories":[1],"streamings":[1]}'
```
