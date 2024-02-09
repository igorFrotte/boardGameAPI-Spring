# boardGameAPI-Spring

### Resumo

O BoardGameAPI é uma API desenvolvida dem java voltada para o gerenciamento de aluguéis para jogos de tabuleiro.

### Funcionalidades 

**Jogos:** Permite o cadastro e a listagem dos jogos existentes.

**Clientes** Permite o cadastro de clientes e a busca pelo id do cliente.

**Aluguéis** Permite o gerenciamento dos aluguéis através da sua criação, listagem e finalização.

### Pré-requisitos

- Git instalado
- JDK instalado e uma IDE Java
- Banco de Dados instalado

### Instalação

1. Clonar o repositório
2. Criar o arquivo .env
3. Modificar o .env como o modelo encontrado no .env.exemple com os seus dados do BD
4. Criar o Banco de Dados de acordo com os dados anteriores
5. Executar o aplicativo pela pasta main

### Testes

Os arquivos de teste podem ser encontrados no diretório `src/test/java`.

Para executar os testes, basta entrar no diretório raiz do projeto, entrar no terminal e executar:

```bash
mvn test
```

### Deploy

O deploy da aplicação está localizado em [boardgamespring.onrender.com] (https://boardgamespring.onrender.com/).
