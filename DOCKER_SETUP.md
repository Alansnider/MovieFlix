# 🐳 Docker Setup - MovieFlix

## 📋 Pré-requisitos

- Docker instalado (https://www.docker.com/products/docker-desktop)
- Docker Compose (incluído no Docker Desktop)

---

## 🚀 Executar com Docker Compose (Recomendado)

### 1. **Build e Iniciar**

```bash
# Iniciar aplicação e banco de dados
docker-compose up -d

# Ver logs
docker-compose logs -f movieflix-app

# Parar
docker-compose down

# Parar e remover volumes
docker-compose down -v
```

### 2. **Acessar a Aplicação**

- **API:** http://localhost:8080/api
- **Swagger UI:** http://localhost:8080/api/swagger-ui.html
- **Banco de Dados:** PostgreSQL em `localhost:5432`

---

## 🔨 Build Manual

### 1. **Construir Imagem Docker**

```bash
docker build -t movieflix:latest .
```

### 2. **Executar Container**

```bash
# Com PostgreSQL removoto
docker run -d \
  --name movieflix-api \
  -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/movieflix \
  movieflix:latest

# Com PostgreSQL via Docker
docker run -d \
  --name movieflix-api \
  -p 8080:8080 \
  --network host \
  movieflix:latest
```

---

## 📊 Estrutura do Docker Compose

### Serviços:

1. **PostgreSQL (postgres)**
   - Imagem: `postgres:15-alpine`
   - Porta: `5432`
   - Volume: `postgres_data`
   - Variáveis:
     - `POSTGRES_DB`: movieflix
     - `POSTGRES_USER`: postgres
     - `POSTGRES_PASSWORD`: postgres

2. **MovieFlix API (movieflix-app)**
   - Build: Dockerfile (multi-stage)
   - Porta: `8080`
   - Depende de: postgres (healthy)
   - Variáveis de ambiente configuradas automaticamente

---

## 🔧 Variáveis de Ambiente

| Variável | Valor Padrão | Descrição |
|----------|--------------|-----------|
| `SPRING_DATASOURCE_URL` | `jdbc:postgresql://postgres:5432/movieflix` | URL do banco |
| `SPRING_DATASOURCE_USERNAME` | `postgres` | Usuário do BD |
| `SPRING_DATASOURCE_PASSWORD` | `postgres` | Senha do BD |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | `update` | Auto-criar tabelas |
| `SPRING_FLYWAY_ENABLED` | `true` | Habilitador Flyway |

---

## 📝 Dockerfile Explicado

### Stage 1: Build
- Usa imagem Maven com Java 17
- Compila o projeto
- Gera JAR

### Stage 2: Runtime
- Usa imagem Alpine (mais leve)
- Copia apenas o JAR
- Reduz tamanho da imagem final

**Tamanho aproximado:** ~200MB (vs ~700MB sem multi-stage)

---

## 🏥 Health Check

A aplicação possui health check configurado:

```bash
# Verificar saúde
curl http://localhost:8080/api/actuator/health

# Entrada do docker-compose
HEALTHCHECK --interval=30s --timeout=10s --retries=3
```

---

## 🐛 Troubleshooting

### Porta já em uso

```bash
# Verificar qual processo está usando a porta
lsof -i :8080  # macOS/Linux
netstat -ano | findstr :8080  # Windows

# Mudar porta no docker-compose.yml
ports:
  - "8081:8080"  # Host:Container
```

### Banco de dados não conecta

```bash
# Verificar logs
docker-compose logs postgres

# Reiniciar serviços
docker-compose restart postgres
docker-compose restart movieflix-app
```

### Limpar tudo

```bash
docker-compose down -v
docker system prune -a
```

---

## 📦 Publicar Imagem (Opcional)

### 1. **Tag**
```bash
docker tag movieflix:latest seu-usuario/movieflix:1.0
```

### 2. **Push para Docker Hub**
```bash
docker login
docker push seu-usuario/movieflix:1.0
```

### 3. **Pull e Executar**
```bash
docker run -p 8080:8080 seu-usuario/movieflix:1.0
```

---

## 🔗 Links Úteis

- [Docker Docs](https://docs.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Docker Hub](https://hub.docker.com/)
- [Spring Boot Docker](https://spring.io/guides/topical/docker/)

