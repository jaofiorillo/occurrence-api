# CarBigData API

Este projeto é uma API robusta desenvolvida com **Spring Boot** para o gerenciamento de ocorrências e clientes. A
aplicação conta com segurança via JWT, persistência em PostgreSQL, integração com Storage de arquivos (MinIO) e filtros
dinâmicos com QueryDSL.

## 📌 Funcionalidades

- **Cadastro de Clientes:** Implementei a gestão de usuários com validações rigorosas de CPF e E-mail únicos.
- **Autenticação Segura:** Eu implementei dois novos campos cruciais no cliente, **Email** e **Senha**, para permitir a
  autenticação via JWT.
- **Gestão de Ocorrências:** Cadastro de ocorrências vinculadas a clientes e endereços.
- **Anexo de Fotos:** Suporte a upload de múltiplas imagens por ocorrência, armazenadas de forma eficiente no **MinIO**.
- **Filtros Dinâmicos:** Utilizei QueryDSL para permitir buscas complexas (por nome, CPF, cidade ou intervalo de datas).

## 🧪 Testes Unitários

- **Cobertura de testes:** Implementei a cobertura de testes testando todos os comportamentos das classes de serviço e
  das controllers.
- **Testes de Controller:** Realizei testes nas controllers para validar os endpoints, garantindo que as requisições,
  respostas e códigos de status (HTTP) estejam corretos.
- **Tecnologias:** Utilizei **JUnit 5**, **Mockito** e **AssertJ** para garantir a confiabilidade das regras de negócio.

## 🔒 Regras de Acesso

- **Endpoints Públicos:** `/api/auth/**`, `/api/cliente/**` (além do console do H2 para testes locais).
- **Autenticação:** Todas as demais URLs exigem **autenticação JWT** via Bearer Token.
- **Privacidade:** As consultas respeitam as permissões de acesso, garantindo a integridade dos dados.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.4**
- **Spring Security & JWT**
- **JPA / Hibernate / QueryDSL**
- **PostgreSQL** (Banco de dados principal)
- **MinIO** (Storage de fotos compatível com S3)
- **Docker & Docker Compose**

## 🛠 Como Rodar a Aplicação

Para subir o ambiente completo (API + Banco + Storage), utilize os comandos abaixo na ordem:

1. **Limpar volumes e containers antigos:**

```bash
docker compose down -v
```

2. **Subir os serviços em background com build:**

```bash
docker compose up -d --build
```

3. **Rodar a aplicação com migrações e atualização de schema:**

```bash
docker compose run -e SPRING_FLYWAY_ENABLED=true -e SPRING_JPA_HIBERNATE_DDL_AUTO=update app
```

## Ambiente Local (H2)

**Para rodar a aplicação localmente utilizando o banco de dados em memória H2.**

**Basta definir o profile ativo para test: Configure a propriedade: -Dspring.profiles.active=test**

## 📩 Coleção de Requisições para Postman

Para facilitar os testes, disponibilizei uma coleção de requisições para o Postman:

🔗 [Acessar Coleção Postman](https://drive.google.com/drive/u/0/folders/1iDIz-24Cpil9AFxhEcY--cpb6GW6130v)

## 💡 Minhas Implementações

"Durante o desenvolvimento, eu foquei na segurança e na escalabilidade. A inclusão do email e senha no modelo de Cliente
foi um passo essencial para garantir que a API fosse protegida. A cobertura de testes unitários nas camadas de serviço
e controller garante que as regras de negócio permaneçam sólidas. Por falta de tempo, não consegui implementar diversas
coisas que eu queria, como Checkstyle para mais validações de código, alguns filtros adicionais e a
cobertura total (100%) de todos os cenários de testes, mas o core do sistema está funcional e protegido."