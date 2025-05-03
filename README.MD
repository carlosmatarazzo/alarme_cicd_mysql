03/05/2025
# Aplicação
Esta aplicação tem o objetivo de ajudar a gerenciar a coleta de resíduos
disparando notificações quando os recipientes atingem suas capacidades totais.
## API para Gestão de Resíduos
API para gestão de controle de resíduos em cidades inteligentes.
As APIs disponibilizadas neste projeto permitem o cadastro de:
- Recipientes: locais onde os resíduos são depositados;
- Depósitos: ação de depositar resíduo no recipiente;
- Notificação: alerta disparado quando o recipiente atinge o limite;
- Coleta: ação de coletar o resíduo e esvaziar o recipiente. 
## Principais Tecnologias
- Java 21, Maven, Git Hub, Docker Hub, MySQL, Cucumber, Gherkins
## Docker
```
docker compose up
```
## Confirmação de Serviço Ativo
Para confirmar estado de funcionamento da aplicação.
```
/api/teste
```
## Swagger
```
/swagger-ui/index.html
```
# Testes
```
mvn clean test
```
Visualizar relatório:
```
/target/cucumber-reports/cucumber-reports.html
```