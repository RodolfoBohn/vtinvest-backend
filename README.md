# VTInvest

O VTInvest é um projeto desenvolvido pelos colegas Charles Mossmann, Janaína Pires, Pablo Benz e Rodolfo Bohn, visando atender a disciplina de Desenvolvimento de Sistemas do IFRS Campus Feliz, ministrada pelo professor Tiago Cinto.
Este projeto possui caráter acadêmico, não visando quaisquer lucros sobre a utilização do mesmo.

## O projeto

O projeto visa ser um gerenciador de carteira de investimentos, na qual o usuário, após um pré-cadastro a ser realizado com o perfil analista, pode cadastrar suas carteiras, bem como receber sugestões de aporte de acordo com os percentuais previamente definidos para cada tipo de ação.
Você pode conhecer um pouco mais sobre a documentação de entregas, histórias do usuário e outros detalhes acessando a [documentação](https://drive.google.com/drive/folders/1wnRfdTOOsfEZTM5kqJdubtFoeOxOzYuW).

## Os integrantes

Abaixo segue a lista dos participantes do grupo, bem como seu papel no desenvolvimento.
Para saber mais sobre cada um, acesse o linkedin:
- [Charles Mossmann](https://www.linkedin.com/in/charles-mossmann-325025198/) - Desenvolvedor Frontend;
- [Janaína Pires](https://www.linkedin.com/in/janaina-espindolo-pires-93a37391/) - PO/Analista de Sistemas;
- [Pablo Benz](https://www.linkedin.com/in/pablo-augusto-bentz-a5a2991a0/) - QA;
- [Rodolfo Bohn](www.linkedin.com/in/rodolfo-f-539738a5) - Desenvolvedor FullStack

## Quer executar o projeto?

Para acessar o executável do projeto, explorando suas funcionalidades, você pode acessar [este link](https://vtinvest.herokuapp.com/). Possuímos dois usuários de teste, um com perfil analista, e outro com perfil usuário.

- Perfil analista:
  -- Usuário: admin
  -- Senha: admin123
- Perfil usuário:
  -- Usuário: usuario
  -- Senha: usuario123

## Quer executar localmente?

O Backend do projeto foi criado utilizando Java com Spring framework.
Caso você deseje utilizar o projeto tal qual, sugerimos primeiramente criar um banco de dados utilizando o heroku, podendo ter como base [este tutorial](https://www.youtube.com/watch?v=CrSLbdk6PqI&ab_channel=RooseveltSilva).
Também crie uma conta teste em [alphavantage.co](https://www.alphavantage.co/support/), que será necessária para a atualização dos preços das ações.
Você irá precisar criar as seguintes variáveis de ambiente:
- DB_DATABASE: database do seu banco de dados heroku;
- DB_PORT: porta do seu banco de dados heroku;
- DB_USERNAME: usuário do seu banco de dados heroku;
- DB_PASSWORD: senha do seu banco de dados heroku;
- DB_HOST: host do seu banco de dados heroku;
- ALPHAVANTAGE_API_KEY: chave de acesso criada junto a [alphavantage.co](https://www.alphavantage.co/support/).

Na primeira execução do projeto, altere a configuração ddl-auto do arquivo `src/main/resources/db/application.yml` para create. Interrompa a execução, e volte a informação para validate.
Conecte o banco de dados no gerenciador de sua preferência, e execute os scripts contidos em `src/main/resources/db/db.sql`.
