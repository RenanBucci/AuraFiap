AppAura
AppAura é um aplicativo Android desenvolvido para gerenciar eventos e e-mails, além de oferecer personalizações de configurações de usuário. Este projeto é construído em Kotlin, utilizando bibliotecas modernas como Retrofit para integração com APIs e ViewBinding/DataBinding para manipulação de interfaces.

Funcionalidades
Autenticação de Usuário:

Login de usuários existentes.
Registro de novos usuários.
Gerenciamento de Eventos:

Adicionar novos eventos.
Listar eventos salvos.
Gerenciamento de E-mails:

Enviar novos e-mails.
Listar e-mails recebidos.
Listar e-mails enviados.
Configurações de Usuário:

Alterar tema do aplicativo (modo claro/escuro).
Ajustar o tamanho da fonte.
Estrutura do Projeto
O projeto segue uma estrutura organizada em pacotes para facilitar a manutenção e expansão.

Diretórios Principais
model:
Contém as classes de dados (DTOs) usadas para transferir informações entre a interface e o backend.

util:
Inclui classes utilitárias, como ConfiguracaoUtil, que gerencia as preferências do usuário.

api:
Contém a interface ApiService, responsável por definir as chamadas HTTP utilizando Retrofit.

activity:
Armazena as atividades principais da aplicação.

Principais Telas
MainActivity.kt:
Tela inicial que exibe as opções de login e registro.

LoginActivity.kt:
Tela para autenticação de usuários.

RegisterActivity.kt:
Tela para registro de novos usuários.

HomeActivity.kt:
Tela principal pós-login, exibindo e-mails recebidos.

AdicionarEventoActivity.kt:
Tela para criar e salvar novos eventos.

EnviarEmailActivity.kt:
Tela para compor e enviar e-mails.

EmailsEnviadosActivity.kt:
Tela para listar os e-mails enviados pelo usuário.

ConfiguracaoActivity.kt:
Tela para personalização de configurações, como tema e tamanho da fonte.

Tecnologias Utilizadas
Linguagem: Kotlin

Bibliotecas:

Retrofit: Para consumo de APIs RESTful.
ViewBinding e DataBinding: Para manipulação eficiente de views.
Material Design Components: Para interfaces modernas e responsivas.
Armazenamento Local: Utilização de SharedPreferences para gerenciar configurações de usuário.

Configuração do Projeto
Pré-requisitos
Android Studio (versão mais recente recomendada).
JDK 8 ou superior.
Conexão com uma API REST para autenticação e manipulação de dados.
Passos para Configurar
Clone o repositório:
bash
Copiar código
git clone https://github.com/RenanBucci/AuraFiap.git
Abra o projeto no Android Studio.
Sincronize as dependências do Gradle.
Configure as URLs de API em ConfiguracaoUtil.kt.
Execute o projeto em um dispositivo ou emulador.
Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.
