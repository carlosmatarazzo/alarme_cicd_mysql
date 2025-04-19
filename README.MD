18/04/2025

# Objetivo
Esta aplicação tem o objetivo de ajudar a gerenciar a coleta de resíduos disparando notificações quando os recipientes atingem suas capacidades totais.

# API de Controle de Resíduos.
API para gestão de controle de resíduos em cidades inteligentes.
As APIs disponibilizadas neste projeto permitem o cadastro de:
- Recipientes: locais onde os resíduos são depositados;
- Depósitos: ação de depositar resíduo no recipiente;
- Notificação: alerta disparado quando o recipiente atinge o limite;
- Coleta: ação de coletar o resíduo e esvaziar o recipiente. 

# Segurança
O acesso às APIs requerem autenticação Bearer cujo token é obtido com o login de um usuário.

# Tecnologias
- Java 21
- Maven
- Git Hub
- Docker Hub
- Azure Web App
- MySQL

# Build
```
mvn clean package
```

# Testes
```
./mvnw test
```

# Métodos API

## Teste
Para confirmar estado de funcionamento da aplicação.
```
/api/teste
```

## Swagger
```
/swagger-ui/index.html
```
