# API-FATEC - API de Pedidos de Venda

Este projeto consiste em uma API REST desenvolvida com Spring Boot, projetada para gerenciar pedidos de venda. O principal objetivo é fornecer material didático de alta qualidade para estudantes da FATEC, facilitando o aprendizado prático de conceitos de desenvolvimento de API e back-end com Java.

## Funcionalidades

## CLIENTES

## Listar Todos os Clientes

- **Endpoint:** `GET /api/clientes/v1`
- **Descrição:** Retorna uma lista de todos os clientes cadastrados no sistema.
- **Resposta:** Uma lista de objetos `Cliente`, com status HTTP 200 OK.

## Buscar Clientes por Nome

- **Endpoint:** `GET /api/clientes/v1/buscar-por-nome/{nome}`
- **Descrição:** Permite a busca de clientes pelo nome. Retorna todos os clientes cujos nomes contêm a string fornecida.
- **Resposta:** Uma lista de objetos `Cliente` que correspondem ao critério de busca, com status HTTP 200 OK.

## Encontrar Cliente por ID

- **Endpoint:** `GET /api/clientes/v1/{id}`
- **Descrição:** Retorna os detalhes de um cliente específico baseado em seu ID.
- **Resposta:** Um objeto `Cliente` se encontrado, com status HTTP 200 OK, ou status HTTP 404 Not Found se nenhum cliente for encontrado.

## Salvar um Novo Cliente

- **Endpoint:** `POST /api/clientes/v1`
- **Descrição:** Permite a criação de um novo cliente. Os dados do cliente devem ser enviados como um objeto JSON no corpo da requisição.
- **Resposta:** O objeto `Cliente` criado, com status HTTP 201 CREATED.

## Deletar um Cliente

- **Endpoint:** `DELETE /api/clientes/v1/{id}`
- **Descrição:** Remove um cliente do sistema baseado em seu ID.
- **Resposta:** Status HTTP 204 No Content após a exclusão bem-sucedida.

## Atualizar um Cliente Existente

- **Endpoint:** `PUT /api/clientes/v1/{id}`
- **Descrição:** Atualiza os dados de um cliente existente. O ID do cliente e os novos dados são fornecidos na URL e no corpo da requisição, respectivamente.
- **Resposta:** O objeto `Cliente` atualizado, com status HTTP 200 OK.

## PRODUTOS

## Listar Todos os Produtos

- **Endpoint:** `GET /api/produtos/v1`
- **Descrição:** Retorna uma lista de todos os produtos disponíveis no sistema.
- **Resposta:** Uma lista de objetos `Produto`, com status HTTP 200 OK.

## Encontrar Produto por ID

- **Endpoint:** `GET /api/produtos/v1/{id}`
- **Descrição:** Retorna os detalhes de um produto específico baseado em seu ID.
- **Resposta:** Um objeto `Produto` se encontrado, com status HTTP 200 OK, ou status HTTP 404 Not Found se nenhum produto for encontrado.

## Salvar um Novo Produto

- **Endpoint:** `POST /api/produtos/v1`
- **Descrição:** Permite a criação de um novo produto. Os dados do produto devem ser enviados como um objeto JSON no corpo da requisição.
- **Resposta:** O objeto `Produto` criado, com status HTTP 201 CREATED.

## Deletar um Produto

- **Endpoint:** `DELETE /api/produtos/v1/{id}`
- **Descrição:** Remove um produto do sistema baseado em seu ID.
- **Resposta:** Status HTTP 204 No Content após a exclusão bem-sucedida.

## Atualizar um Produto Existente

- **Endpoint:** `PUT /api/produtos/v1/{id}`
- **Descrição:** Atualiza os dados de um produto existente. O ID do produto e os novos dados são fornecidos na URL e no corpo da requisição, respectivamente.
- **Resposta:** O objeto `Produto` atualizado, com status HTTP 200 OK.

## PEDIDOS DE VENDA

## Adicionar um Novo Pedido

- **Endpoint:** `POST /api/pedido-venda/v1`
- **Descrição:** Permite a criação de um novo pedido de venda. Os detalhes do pedido devem ser enviados como um objeto JSON no corpo da requisição.
- **Resposta:** O objeto `PedidoVenda` criado, com status HTTP 200 OK.

## Buscar um Pedido pelo ID

- **Endpoint:** `GET /api/pedido-venda/v1/{id}`
- **Descrição:** Retorna os detalhes de um pedido específico baseado em seu ID.
- **Resposta:** Um objeto `PedidoVenda` se encontrado, com status HTTP 200 OK, ou status HTTP 404 Not Found se nenhum pedido for encontrado.

## Listar Todos os Pedidos

- **Endpoint:** `GET /api/pedido-venda/v1`
- **Descrição:** Retorna uma lista de todos os pedidos de venda registrados no sistema.
- **Resposta:** Uma lista de objetos `PedidoVenda`.

## Atualizar os Dados de um Pedido

- **Endpoint:** `PUT /api/pedido-venda/v1/{id}`
- **Descrição:** Atualiza os dados de um pedido existente. O ID do pedido e os novos dados são fornecidos na URL e no corpo da requisição, respectivamente.
- **Resposta:** O objeto `PedidoVenda` atualizado, com status HTTP 200 OK, ou status HTTP 404 Not Found se o pedido não for encontrado.

## Cancelar um Pedido

- **Endpoint:** `PUT /api/pedido-venda/v1/{id}/cancelar`
- **Descrição:** Cancela um pedido de venda existente. O cancelamento é tratado por uma lógica específica que pode levar ao retorno de diferentes códigos de resposta com base no estado do pedido.
- **Resposta:** O objeto `PedidoVenda` cancelado, com status HTTP 200 OK, status HTTP 400 Bad Request se o pedido não puder ser cancelado devido a um estado inválido, ou status HTTP 404 Not Found se o pedido não for encontrado.

## Tecnologias Utilizadas

- **Java 17:** Linguagem de programação.
- **Spring Boot 3.2.3:** Framework para desenvolvimento de aplicações Spring.
- **Maven:** Ferramenta de gerenciamento e compreensão de projeto.
- **Spring Data JPA:** Facilita a implementação de repositórios baseados em JPA.
- **Spring Boot Starter Web:** Oferece funcionalidades essenciais para o desenvolvimento de aplicações web.
- **H2 Database:** Banco de dados em memória para facilitar os testes e desenvolvimento.
- **Spring Boot Starter Test:** Provê suporte integral para testes de unidade e integração.
- **Spring Boot Devtools:** Oferece recursos que aumentam a produtividade durante o desenvolvimento.
- **Validation API:** Permite a validação de dados de entrada para garantir a integridade das operações.

## Instalação e Execução

1. **Clone o repositório:**
git clone https://github.com/h2danilofatec/api-fatec.git

markdown
Copy code
2. **Navegue até o diretório do projeto:**
cd api-fatec

markdown
Copy code
3. **Instale as dependências:**
mvn install

markdown
Copy code
4. **Execute a aplicação:**
mvn spring-boot:run

markdown
Copy code
A aplicação estará acessível em `http://localhost:8090`.


## Contribuições

Contribuições são muito bem-vindas! Se você tem alguma sugestão para melhorar o projeto, por favor, faça um fork do repositório e submeta um pull request, ou abra uma issue com as tags correspondentes.

## Licença

Este projeto é licenciado sob a Licença MIT - veja o arquivo `LICENSE.md` para mais detalhes.

## Contato

- **Professor Danilo Valim** - [danilo.valim01@fatec.sp.gov.br](mailto:danilo.valim01@fatec.sp.gov.br)
- **GitHub do Projeto:** [API-FATEC](https://github.com/h2danilofatec/api-fatec.git)