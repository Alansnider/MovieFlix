# 📝 Arquivo .env - Variáveis de Ambiente

## ✅ O que foi criado

1. **`.env`** - Arquivo com todas as variáveis de ambiente (ignorado por Git)
2. **`.env.example`** - Template para novos desenvolvedores
3. **`.gitignore`** - Atualizado para ignorar `.env` e outros arquivos sensíveis

## 🔒 Segurança

### ⚠️ NUNCA faça commit do `.env`

Nunca adicione o arquivo `.env` ao repositório Git! Ele contém:
- Senhas do banco de dados
- Chaves JWT
- Credenciais sensíveis

### ✅ O que fazer

1. **Adicione `.env.example` ao Git** (sem valores reais)
2. **Cada desenvolvedor copia:**
   ```bash
   cp .env.example .env
   ```
3. **Preencha com valores reais localmente**
4. **Adicione à `.gitignore` (já feito)**

## 📋 Variáveis Disponíveis

### Banco de Dados

```env
# H2 (Desenvolvimento)
SPRING_DATASOURCE_URL=jdbc:h2:mem:movieflixdb
SPRING_DATASOURCE_USERNAME=sa
SPRING_DATASOURCE_PASSWORD=

# PostgreSQL (Produção)
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/movieflix
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
```

### JWT

```env
JWT_SECRET=sua_chave_secreta_aqui
JWT_EXPIRATION=86400000
```

### Security

```env
SPRING_SECURITY_USER_NAME=admin
SPRING_SECURITY_USER_PASSWORD=admin
```

### Server

```env
SERVER_PORT=8080
SERVER_SERVLET_CONTEXT_PATH=/api
```

## 🚀 Como Usar

### 1. **Desenvolvimento Local (H2)**

```bash
# Copiar template
cp .env.example .env

# Já vem pré-configurado para H2
# Apenas execute:
mvn spring-boot:run
```

### 2. **Com PostgreSQL**

```bash
# Editar .env
vi .env

# Descomentar linhas PostgreSQL:
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/movieflix
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres

# Iniciar com Docker
docker-compose up -d
mvn spring-boot:run
```

### 3. **Docker**

```bash
# O docker-compose.yml lê automaticamente do .env

# Ou passe variáveis diretamente:
docker run -d \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/movieflix \
  -e SPRING_DATASOURCE_PASSWORD=minha_senha \
  movieflix:latest
```

## 📝 Estrutura do .gitignore

```
### .env (Ignorado)
.env                  # Arquivo local com valores reais
.env.local           # Sobrescreve .env localmente
.env.*.local         # Por ambiente (dev, prod, test)

### Secrets (Ignorado)
*.key                # Chaves SSH/SSL
*.pem                # Certificados

### Outros (Ignorado)
.env.prod
.env.staging
```

## 🔄 CI/CD (GitHub Actions/GitLab CI)

Em produção, defina variáveis de ambiente diretamente no CI/CD:

### GitHub Actions

```yaml
env:
  SPRING_DATASOURCE_URL: ${{ secrets.DB_URL }}
  SPRING_DATASOURCE_PASSWORD: ${{ secrets.DB_PASSWORD }}
  JWT_SECRET: ${{ secrets.JWT_SECRET }}
```

### GitLab CI

```yaml
variables:
  SPRING_DATASOURCE_URL: $DB_URL
  JWT_SECRET: $JWT_SECRET
```

## ⚡ Boas Práticas

✅ **Faça:**
- Comitar `.env.example`
- Usar variáveis para senhas
- Usar variáveis para chaves secretas
- Documentar todas as variáveis

❌ **Não faça:**
- Comitar `.env` com valores reais
- Hardcoding de senhas no código
- Compartilhar `.env` por mensagem
- Usar senhas padrão em produção

## 🆘 Troubleshooting

### Variáveis não estão sendo lidas

```bash
# Verifique se o arquivo .env existe
ls -la .env

# Verifique a sintaxe
cat .env

# Reinicie a aplicação
mvn clean spring-boot:run
```

### Erro de conexão ao banco

```bash
# Verifique as variáveis:
echo $SPRING_DATASOURCE_URL
echo $SPRING_DATASOURCE_USERNAME

# Teste a conexão:
psql -h localhost -U postgres -d movieflix
```

## 📚 Links Úteis

- [Spring Boot Environment Variables](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features.external-config)
- [Env File Format](https://linux.die.net/man/1/env)
- [Git Ignore Documentation](https://git-scm.com/docs/gitignore)

