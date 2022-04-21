## Informações sobre o projeto

### Como subir o projeto

- Certifique-se de configuar o banco de dados PostgreSQL;
  - Ele pode ser executado facilmente usando docker. Execute o comando `docker-compose up` na raiz do projeto.
  - Outra opção é configurar um banco PostgreSQL e alterar as configurações de acesso no arquivo `application.properties` 
- Certifique-se que a porta 8080 esteja desocupada;
- Certifique-se de que você possui o maven instalado localmente;
- Certifique-se de que você está na raiz do projeto;
- Rode o `./mvnw spring-boot:run`

Com isso as dependências serão baixadas e a API subirá na porta `8080`;

### Fazendo uma requisição

Importe a collection abaixo no postman ou outra ferramenta para realizar as requisições http:

[Download Collection](https://www.postman.com/collections/eea24c40158609084b62)

### Lista dos endpoints

| Método | Path                            | Descrição                                                     |
|--------|---------------------------------|---------------------------------------------------------------|
| POST   | /planet-with-probes             | Registra um planeta e multíplas sondas                        |
| POST   | /planet-with-probes/planet/{id} | Registra uma sonda em um determinado planeta                  |
| PATCH  | /planet-with-probes/probe/{id}  | Move uma sonda através de comandos                            |

### Documentação dos endpoints

A documentação dos endpoints também está disponível através do swagger-ui. Após executar a aplicação acesse o endereço abaixo.
```
http://localhost:8080/swagger-ui/index.html
```

### Monitoria

No projeto foi adicionado a dependência *actuator* para monitoriar a "saúde" da aplicação.
```
http://localhost:8080/actuator
http://localhost:8080/actuator/health
```


### Explicação da necessidade:
```
Tamanho da área do planeta : 5x5

Posição de pouso da sonda 1: x=1, y=2 face para Norte
Sequencia de comandos: LMLMLMLMM
Posição final da sonda: x=1 y=3 face para Norte

Posição de pouso da sonda 2: x=3, y=3 face para Leste
Sequencia de comandos: MMRMMRMRRML flw
Posição final da sonda: x=5 y=1 face para Norte
```

A sequência de comandos é um conjunto de instruções enviadas da terra para a sonda, onde :
- `M` -> Andar para a frente na direção que está 1 posição.
- `L` -> Virar a sonda para a esquerda (90 graus)
- `R` -> Virar a sonda para a direita (90 graus)

A área do planeta é um plano cartesiano com o tamanho informado pelo operador.

A orientação da sonda dentro do plano cartesiano usa uma rosa dos ventos como referência

![rosa dos ventos](http://i.imgur.com/li8Ae5L.png "Rosa dos Ventos")

O sistema deveria receber os dados sobre o planeta, sondas e movimentos por meio de uma interface REST.

