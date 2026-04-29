🎬 API do MovieFlix
O MovieFlix é uma API RESTful robusta desenvolvida para o gerenciamento completo de um ecossistema de entretenimento, abrangendo filmes, categorias e plataformas de streaming. O projeto foca em segurança, escalabilidade e uma arquitetura profissional em camadas.
----------------------------------------------------------------------------------------------------------------------------------------------------------------------
🚀 Tecnologias e Ferramentas
O ecossistema do projeto foi construído com as melhores práticas do mercado:

Núcleo: Java 17 e Spring Boot 3

Persistência: Spring Data JPA e PostgreSQL

Migrações: Flyway (versão de banco de dados)

Segurança: Spring Security e JWT (Auth0)

Documentação: OpenAPI 3/Swagger

Utilitários: Lombok & Bean Validation
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
🏗️ Arquitetura do Sistema
A API segue o padrão de Arquitetura em Camadas , garantindo separação de responsabilidades e facilidade de manutenção:

Controlador: Porta de entrada, lida com as requisições HTTP.

Serviço: Detém a regra de negócio e orquestração.

Repositório: Interface de comunicação direta com o PostgreSQL.

Segurança (Filtros): Intercepta requisições para validação de tokens JWT.

Exception Handler: Gerenciamento global de erros para respostas padronizadas.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
📌 Funcionalidades Principais
🎭 Gestão de Conteúdo
Filmes: CRUD completo com filtros por categoria.

Categorias: Organização sistemática do catálogo.

Streaming: Cadastro de plataformas onde os títulos estão disponíveis.

🔐 Segurança e Acesso
Registro e Login: Cadastro de novos usuários e autenticação.

JWT: Emissão de tokens para sessões stateless .

BCrypt: Criptografia de senhas antes da persistência no banco.
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
🛠️ Como executar o projeto
Pré-requisitos
Java 17 instalado.

PostgreSQL rodando localmente ou via Docker.

Maven (ou use o Maven Wrapper inclusive).

Passo a Passo
Clonar o:

Bash
git clone https://github.com/seu-usuario/movieflix.git
cd movieflix
Configure o Banco de Dados: 
Crie um banco chamado movieflixe ajuste as credenciais no arquivo src/main/resources/application.yaml:

YAML
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/movieflix
    username: seu_usuario
    password: sua_senha
Inicie a aplica:

Bash
./mvnw spring-boot:run
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
📖 Documentação da APIUma vez que o aplicativo estiver rodando, você pode acessar a interface interativa do Swagger para testar todos os endpoints:🔗http://localhost:8080/swagger-ui.htmlExemplo de AutenticaçãoPara acessar rotas protegidas, primeiro faça o login:POST /auth/loginJSON{
  "email": "user@email.com",
  "password": "123"
}
A API retornará um token. Utilize-o no cabeçalho das próximas requisições:
Authorization: Bearer <SEU_TOKEN_AQUI>🧪 Endpoints Principais (Resumo)RecursoPonto finalDescriçãoFilmesGET /moviesLista todos os filmesFilmesPOST /moviesCadastrar um novo filme (Privado)CategoriasGET /categoriesLista todas as categoriasTransmissãoGET /streamingsLista contém cadastradosAut.POST /auth/registerCria uma nova conta🤝 ContribuiçãoFaça um Fork do projeto.Crie um Branch para seu recurso ( git checkout -b feature/NovaFeature).Dê um Commit nas suas alterações ( git commit -m 'Add NovaFeature').Faça um Push para Branch ( git push origin feature/NovaFeature).Abra um Pull Request .👨‍💻 AutorDesenvolvido por Seu Nome –Seu LinkedIn–Seu e-mailEste projeto é para fins de estudo e portfólio.

