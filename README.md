## Bem vindo candidato(a)!

Vamos explicar como funciona o nosso desafio:

Um desenvolvedor recebeu um tarefa de uma pessoa da equipe de produto. Ele queria poder controlar sondas em outros planetas por meio de comandos. Para explicar o funcionamento do produto, ele escreveu o seguinte funcionamento em um pedaço de papel:

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

## O desafio

Você vai fazer o code review do sistema que foi implementado por um desenvolvedor não muito experiente. O código está funcional e com certa cobertura de testes automatizados mas não necessariamente está seguindo boas práticas. Olhe o código, anote propostas de melhorias ou refatore o código com a suas idéias.

Além de olhar para o código em si, pense a respeito dos seguintes tópicos:

### Sobre o contrato da aplicação web:

- Como eu cadastro mais sondas em um planeta existente?
- Como eu movo uma sonda já pousada?

### Sobre modelagem de código:

- Imaginando um planeta com uma superfície limitada e várias sondas em movimento, existem regras de negócio importantes não mapeadas no código?
- As responsabilidades estão bem distribuídas no código? Como melhorar?

Vamos deixar aqui alguns posts que consideramos boas práticas de desenvolvimento na parte de modelagem O.O. para sua consulta

- https://www.alura.com.br/artigos/nao-aprender-oo-getters-e-setters
- https://www.alura.com.br/artigos/o-que-e-modelo-anemico-e-por-que-fugir-dele

### Para pretensões senior:

No caso da pretenção estar no patamar de senior nós requisitamos alguns desafios extras:

- O teste possui um mecanismo de persistência em memória, altere para uma persistência utilizando um ou mais banco de dados de forma a armazenar as informações de planetas e sondas e buscá-las ou alterá-las de maneira eficiente;
- Se preocupe com uma maneira de documentar a api do sistema web;
- Tenha em mente escalabilidade, disponibilidade e performance em sua solução. Apesar do problema proposto ser bem didático procure tratar a solução como um sistema de produção real.

Obs: Se você está em dúvida se a sua pretenção é senior ou não procure nossa tech recruiter sobre o assunto, ela saberá responder. Caso sua pretenção seja junior ou pleno você pode encarar os pontos acima como opcionais para demonstrar seu conhecimento e potencializar o valor inicial de nossa oferta, mas se a sua pretenção é junior ou pleno os pontos acima NÃO SÃO OBRIGATÓRIOS para a entrega da solução.

## Informações sobre o projeto

### Como subir o projeto

- Certifique-se que a porta 8080 esteja desocupada;
- Certifique-se de que você possui o maven instalado localmente;
- Certifique-se de que você está na raiz do projeto;
- Rode o `./mvnw spring-boot:run`

Com isso as dependências serão baixadas e a API subirá na porta `8080`;

### Fazendo uma requisição

- Aqui você pode usar o Postman, por exemplo, ou o curl como abaixo:

```bash
curl -X POST http://localhost:8080/planet-with-probes -H 'Content-Type: application/json' -d '{"width":10,"height":10,"probes":[{"x":1,"y":2,"direction":"N","commands": "LMLMLMLMM"},{"x":3,"y":3,"direction":"E","commands": "MMRMMRMRRM"}]}'
```