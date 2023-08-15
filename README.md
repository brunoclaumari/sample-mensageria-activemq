# sample-mensageria-activemq
Estudo simples de implementação de Consumidor e Produtor de mensagens integrado com ActiveMQ
Referência: https://mmarcosab.medium.com/mensageria-responsa-com-jms-e-active-mq-f1cb6fd6dc35

Mudei o SGBD (gerenciador de banco de dados) usado no exemplo do link de MySQL para PostgreSQL.

Os application.properties estão prontos para rodar em ambientes diferentes, sendo que neste 
exemplo os principais são o 'application.properties' e 'application-dev.properties', tanto no 
Produtor quanto no Consumidor.

Para rodar este sample é necessário:

1 - Ter um banco ou conteiner do PostgreSQL com um banco chamado 'db_mensageria1'
2 - Neste banco ter a tabela 'tb_person', então executar o comando a seguir:
    create table tb_person (id  bigserial not null, age int, name varchar(255), primary key (id));
    
3 - Ter um ambiente com o ActiveMQ rodando. Pode ser na máquina local ou um conteiner Docker, desde 
que sua máquina esteja com o Docker instalado:
     docker run -p 61616:61616 -p 8161:8161 rmohr/activemq

4 - Criar uma fila no ActiveMQ chamada 'fila.pessoa'. Consultar a referência citada no início.

5 - Subir o 'msg-consumer' e o 'msg-producer'.

6 - Utilizar um simulador de requisições (Insomnia, Postman, etc), usando o endpoint: http://localhost:8081/persons/salvar
  >> usar esse JSON no body para testar : {	"name": "Pessoa 1",	"age": 30 }

É esperado que esse JSON seja passe pelo "msg-producer" e salve na fila e depois o "msg-consumer" salve no banco de dados, 
assim como é esperado de uma comunicação assíncrona. Caso o "msg-consumer" esteja parado, o JSON vai ficar na fila como 
mensagem pendente. Assim que o "msg-consumer" subir novamente, espera-se que obtenha o JSON pendente na fila e salve no 
banco de dados, na tabela 'tb_person'.


