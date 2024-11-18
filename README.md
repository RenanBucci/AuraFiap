# AuraFiap
AppAura
AppAura é um aplicativo Android desenvolvido para gerenciar eventos e emails, além de permitir a configuração de preferências do usuário. Este projeto utiliza Kotlin, Retrofit para chamadas de API, e ViewBinding/DataBinding para manipulação de views.  
Funcionalidades
Autenticação: Login e registro de usuários.
Gerenciamento de Eventos: Adicionar e listar eventos.
Gerenciamento de Emails: Enviar e listar emails recebidos e enviados.
Configurações do Usuário: Alterar tema (dark mode) e tamanho da fonte.
Estrutura do Projeto
Diretórios Principais
model: Contém as classes de dados (DTOs) utilizadas no projeto.
util: Contém utilitários, como a classe ConfiguracaoUtil para gerenciar configurações do usuário.
api: Contém a interface ApiService para definir as chamadas de API.
activity: Contém as atividades principais do aplicativo.
Principais Arquivos
MainActivity.kt: Tela inicial com opções de login e registro.
LoginActivity.kt: Tela de login.
RegisterActivity.kt: Tela de registro de novos usuários.
HomeActivity.kt: Tela principal após login, exibindo emails recebidos.
AdicionarEventoActivity.kt: Tela para adicionar novos eventos.
EnviarEmailActivity.kt: Tela para enviar novos emails.
EmailsEnviadosActivity.kt: Tela para listar emails enviados.
ConfiguracaoActivity.kt: Tela para alterar configurações do usuário.
