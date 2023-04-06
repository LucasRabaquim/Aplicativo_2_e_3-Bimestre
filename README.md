# Bem vindo a documentação da usada pela da Sovril

 A Sovril utiliza a API oferecida pela OpenLibrary para mostrar dados sobre os livros que os usuarios estão lendo no momento.

# API [OpenLibrary](https://openlibrary.org/developers/api)
A API da OpenLibrary permite o acesso a dados (capa, autor, páginas e muitos outros) de milhões de livros.

## Métodos e dados de Autenticação
 Estão presentes somente nas bibliotecas em Python, Ruby e Elixir, logo não são aplicáveis a este programa em Java. Entretanto, é possível o login programático pelo método POST, enviando como parâmetros o nome de usuário e a senha na rota  /account/login. Veja mais sobre em: https://openlibrary.org/dev/docs/restful_api#save 

## Métodos disponíveis
 A maioria dos endpoints possuem somente o método GET, como mostra a própria sandbox da Api, disponível neste link: https://openlibrary.org/api_sandbox. Uma exceção já foi citada com o login programático, outra é a criação de listas feita pelos usuários utilizando o método POST. Inclusive o deletar de listas também ocorre pelo método POST. Veja mais em: https://openlibrary.org/dev/docs/api/lists
