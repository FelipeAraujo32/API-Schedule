# Schedule API

Esta é uma API para cadastramento de paciente e agendamento de datas e horários, nessa API é possível vincular o paciente diretamente ao agendamento de data, sendo de grande recurso para agendamentos específicos. Essa API foi desenvolvida em Java utilizando Spring Boot, criei essa API para aprimorar meus conhecimento, tentei utilizar bastantes tecnologia voltadas para o MicroService, quero no futuro utilizar essa API para integrar com outras API.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Validation (Implementação validação)
- Spring Security (Implementação Segurança)
- OpenAPI (Swagger)
- JWT (JSON Web Token)
- JPA (Java Persistence API)
- MySQL (Banco de Dados)
- Maven (Gerenciador de Dependências)

## Configuração do Ambiente

Certifique-se de ter as seguintes ferramentas instaladas em seu ambiente de desenvolvimento:

- Java (versão 8 ou superior)
- Maven (Gerenciador de Versionamento)
- MySQL (Banco de Dados)
- IDE (recomendado: IntelliJ IDEA ou Eclipse)

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

- `src/main/java/microservice/com/agenda`: Contém o código-fonte da aplicação.
- `pacote.domain`: Contém os pacotes de entities, repository e service
- `pacote.api`: Contém os pacotes de controller e dto.
- `pacote.security`: Contém os pacotes de security, filter e token.

## Configuração do Banco de Dados

1. Crie um banco de dados MySQL chamado `microservices`.
2. Configure as informações de conexão com o banco de dados no arquivo `src/main/resources/application.properties`.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/microservices
spring.datasource.username=root
spring.datasource.password=
```

## Documentação da API

A documentação da API é gerada automaticamente usando o Swagger/OpenAPI. Acesse http://localhost:8080/swagger-ui.html para explorar e testar os endpoints da API.

## Autenticação e Autorização

A API utiliza Spring Security e JWT para autenticação e autorização. Certifique-se de incluir o token JWT nas requisições protegidas no cabeçalho. Utilizei o `Postman` para realizar requisão do Token.

## Contribuição
Sinta-se à vontade para contribuir com melhorias, correções de bugs ou novos recursos. Abra uma issue para discutir grandes alterações antes de enviar um pull request.

### Licença
Este projeto é licenciado sob a Licença MIT.

Ainda preciso melhorar alguns pontos no projeto, conforme for evoluindo vou alterando meu projeto...
