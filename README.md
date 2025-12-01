# Projeto Barrinha - App de Gerenciamento de Transporte Escolar

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge\&logo=kotlin)
![Android Studio](https://img.shields.io/badge/Android%20Studio-Hedgehog-3DDC84?style=for-the-badge\&logo=android-studio)
![API](https://img.shields.io/badge/API-27%2B-3DDC84?style=for-the-badge\&logo=android)

## 📄 Descrição

O **Projeto Barrinha** é um aplicativo Android desenvolvido para otimizar a gestão do transporte escolar. Ele serve como uma ponte de comunicação e organização entre motoristas, pais e a administração, centralizando informações sobre alunos, responsáveis e rotas.

---

## ✨ Funcionalidades Principais

* 👤 **Autenticação de Usuários:** Sistema seguro de Login e Cadastro.
* 🏠 **Painel de Controle (Home):** Acesso rápido às principais funcionalidades do app.
* 👧 **Gestão de Alunos:** Cadastre, visualize e edite a lista de alunos.
* 👨‍👩‍👧 **Gestão de Responsáveis:** Mantenha uma lista atualizada dos contatos dos responsáveis.
* ℹ️ **Detalhes e Informações:** Acesse informações detalhadas de alunos e responsáveis.
* 🚪 **Logout Seguro:** Encerre a sessão com segurança.
* UI intuitiva com **Navigation Drawer** para uma navegação fluida.

---

## 🖼️ Galeria de Imagens do Projeto

<div align="center">
  <h3>📸 Telas do Aplicativo</h3>
  <p>Adicione abaixo capturas de tela do app para ilustrar melhor o funcionamento.</p>

  <!-- Substitua os src="" pelas URLs das suas imagens -->

  <div style="display: flex; justify-content: center; flex-wrap: wrap; gap: 20px; margin-top: 20px;">
    <img src="https://raw.githubusercontent.com/DevAlberissi/projeto_barrinha/Andinho/app/prints/print_alunos.png" alt="Tela 1" width="250" />
    <img src="https://raw.githubusercontent.com/DevAlberissi/projeto_barrinha/Andinho/app/prints/print_home.png" alt="Tela 2" width="250" />
    <img src="https://raw.githubusercontent.com/DevAlberissi/projeto_barrinha/Andinho/app/prints/print_veiculos.png" alt="Tela 3" width="250" />
    <img src="https://raw.githubusercontent.com/DevAlberissi/projeto_barrinha/Andinho/app/prints/print_responsaveis.png" alt="Tela 4" width="250" />
  </div>
</div>

---

## 🛠️ Arquitetura e Tecnologias

Este projeto foi desenvolvido seguindo as melhores práticas de arquitetura de software, utilizando o padrão **MVVM (Model-View-ViewModel)** para garantir um código desacoplado, testável e de fácil manutenção.

### Pilares Tecnológicos:

* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Arquitetura:** MVVM (Model-View-ViewModel)
* **Android Jetpack:**

  * `Navigation Component`: Gerenciamento de fluxo de navegação.
  * `ViewModel`: Gerenciamento de estado da UI, resistente a mudanças de configuração.
  * `LiveData`: Comunicação reativa entre ViewModel e View.
  * `Room`: Banco de dados local para persistência de dados offline.
  * `ViewBinding`: Vinculação de views de forma segura e eficiente.
* **UI/UX:**

  * `Material Design 3`: Componentes modernos para uma interface rica e consistente.
  * `CircleImageView`: Para exibição de imagens de perfil.
* **Gerenciamento de Dependências:** [Gradle](https://gradle.org/)

---

## 🚀 Como Começar

Siga os passos abaixo para configurar e executar o projeto em seu ambiente de desenvolvimento.

**Pré-requisitos:**

* Android Studio Hedgehog (ou superior)
* JDK 11 (ou superior)

**Instalação:**

1. **Clone o repositório:**

```bash
git clone https://SEU_USUARIO/projeto-barrinha.git
```

2. **Abra no Android Studio:**

   * Inicie o Android Studio.
   * Selecione `Open an existing project`.
   * Navegue até o diretório onde você clonou o projeto e selecione-o.
3. **Sincronize o Gradle:**

   * O Android Studio irá sincronizar automaticamente as dependências do projeto.
4. **Execute o aplicativo:**

   * Selecione um emulador ou conecte um dispositivo físico.
   * Clique no botão `Run 'app'` (▶️).

---

## 🤝 Como Contribuir

Contribuições são o que tornam a comunidade de código aberto um lugar incrível para aprender, inspirar e criar. Qualquer contribuição que você fizer será **muito apreciada**.

1. Faça um **Fork** do projeto.
2. Crie sua **Feature Branch** (`git checkout -b feature/AmazingFeature`).
3. Faça o **Commit** de suas mudanças (`git commit -m 'Add some AmazingFeature'`).
4. Faça o **Push** para a Branch (`git push origin feature/AmazingFeature`).
5. Abra um **Pull Request**.
