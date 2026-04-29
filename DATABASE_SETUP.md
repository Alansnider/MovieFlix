# 📊 Guia de Configuração - Database Tool Window

## ✅ Status Atual

O projeto MovieFlix foi configurado com as seguintes tabelas:

### Banco de Dados (H2 em Memória - Desenvolvimento)
- **URL:** `jdbc:h2:mem:movieflixdb`
- **Usuário:** `sa`
- **Senha:** (vazia)
- **Console H2:** `http://localhost:8080/api/h2-console`

### Tabelas Criadas Automaticamente:
1. **category** - Categorias de filmes
2. **streaming** - Plataformas de streaming
3. **movie** - Filmes
4. **movie_category** - Relacionamento N:N entre filmes e categorias
5. **movie_streaming** - Relacionamento N:N entre filmes e streamings

---

## 🔧 Configurar PostgreSQL (Opção Alternativa)

Se deseja usar **PostgreSQL** em produção, siga os passos abaixo:

### 1. **Iniciar PostgreSQL com Docker**

```bash
docker-compose up -d
```

### 2. **Atualizar `application.yaml`**

Altere em `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movieflix
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

  flyway:
    enabled: true
```

### 3. **Adicionar conexão no JetBrains IDE**

1. Abra a IDE
2. Vá em **View** → **Tool Windows** → **Database**
3. Clique em **"+" → Data Source → PostgreSQL**
4. Preencha os dados:
   - **Name:** MovieFlix
   - **Host:** localhost
   - **Port:** 5432
   - **Database:** movieflix
   - **User:** postgres
   - **Password:** postgres
5. Clique em **Test Connection**
6. Clique em **OK**

### 4. **Sincronizar Schema**

No Database tool window:
1. Clique com botão direito na conexão
2. Selecione **Synchronize**
3. Aguarde a sincronização

---

## ✨ Acessar H2 Console (Desenvolvimento)

1. Inicie a aplicação: `mvn spring-boot:run`
2. Abra: `http://localhost:8080/api/h2-console`
3. Clique em **Connect**
4. Verifique as tabelas

---

## 🗄️ Entidades Relacionadas

### Movie (Filme)
```sql
- id (PK)
- title
- description
- release_date
- rating
- created_at
- updated_at
- categories (N:N)
- streamings (N:N)
```

### Category (Categoria)
```sql
- id (PK)
- name
- movies (N:N)
```

### Streaming (Plataforma)
```sql
- id (PK)
- name
- movies (N:N)
```

---

## 📝 Migrations (Flyway)

- **V1:** Create categories table
- **V2:** Create streaming table
- **V3:** Create movie table
- **V4:** Create movie_category table
- **V5:** Create movie_streaming table

---

## 🚀 Próximos Passos

1. ✅ Criar Controllers REST
2. ✅ Criar Services
3. ✅ Criar Mappers (MapStruct)
4. ✅ Implementar autenticação JWT
5. ✅ Testes unitários

