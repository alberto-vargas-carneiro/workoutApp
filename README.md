# Workout App 📱
## Descrição do Projeto
Este projeto é um aplicativo para gerenciamento de treinos desenvolvido para facilitar o acompanhamento de exercícios e a gestão de rotinas de treino. O backend é desenvolvido em Java com o framework Spring. O frontend é desenvolvido em React, proporcionando uma interface dinâmica e intuitiva.

## Funcionalidades Principais
- Cadastro e Autenticação de Usuários: Implementação de autenticação via OAuth, além de validação de dados utilizando Bean Validation.
- Gestão de Treinos: Criação e gerenciamento de rotinas de treino personalizadas para cada usuário.
## Tecnologias Utilizadas
Backend:

- Java
- Spring Framework (Spring Boot, Spring Data JPA, Spring Security)
- H2 Database

Frontend:

- React
- TypeScript
- Next.js

## Pré-requisitos para rodar o projeto

É necessário ter os seguintes programas instalados na máquina: (versões utilizadas nesse projeto)

- Git
- Node.js (22.15.1)
- Java (21)
- Maven (3.9.9)

## Depois de clonar o projeto

Frontend:
- Acessar o arquivo referente ao frontend
- Digitar "npm i" no terminal para instalar as dependências
- Digitar "npm run dev" para iniciar o programa (será executado na porta http://localhost:3000)

Backend:
- Acessar o arquivo referente ao backend
- Clicar no botão da sua IDE para rodar o projeto java ou digitar "./mvnw spring-boot:run" no terminal (será executado na porta http://localhost:8080)
- Para acessar o banco H2 entrar em "http://localhost:8080/h2-console"
JDBC URL: jdbc:h2:mem:testdb,
User name: sa,
Password:
