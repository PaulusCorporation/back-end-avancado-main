# Aluno Online API

REST API para gerenciamento de alunos, professores, disciplinas e matrículas.

## Tecnologias

- Java 21(Amazon Corretto)
- Spring Security + JWT
- Spring Data JPA + Flyway
- MySQL/MariaDB
- Maven
- SpringDoc OpenAPI (Swagger UI)

## Como executar

Certifique-se de que o spring esteja rodando

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Endpoints principais

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | /login | Autenticação |
| GET/POST | /alunos | Listar/Criar alunos |
| GET/POST | /professores | Listar/Criar professores |
| GET/POST | /disciplinas | Listar/Criar disciplinas |
| GET/POST | /matriculas | Listar/Criar matrículas |

