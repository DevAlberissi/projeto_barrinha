<div align="center">

# 🚌 Projeto Barrinha
### App de Gerenciamento de Transporte Escolar

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge&logo=kotlin)
![Android Studio](https://img.shields.io/badge/Android%20Studio-Hedgehog-3DDC84?style=for-the-badge&logo=android-studio)
![API](https://img.shields.io/badge/API-27%2B-3DDC84?style=for-the-badge&logo=android)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-blue?style=for-the-badge)

</div>

---

## 📄 Sobre o Projeto

O **Projeto Barrinha** é um aplicativo Android nativo desenvolvido para otimizar e modernizar a gestão do transporte escolar.

Ele atua como uma ponte eficiente entre motoristas, pais e a administração escolar, centralizando dados vitais e garantindo segurança e transparência. Com uma interface intuitiva baseada no Material Design 3, o app facilita o acompanhamento de rotas, gestão de alunos e comunicação com responsáveis.

---

## 📱 Screenshots

| Login | Dashboard | Lista de Alunos | Detalhes |
|:---:|:---:|:---:|:---:|
| <img src="URL_DA_IMAGEM_LOGIN" width="200" /> | <img src="URL_DA_IMAGEM_HOME" width="200" /> | <img src="URL_DA_IMAGEM_LISTA" width="200" /> | <img src="URL_DA_IMAGEM_DETALHE" width="200" /> |

*(Substitua `URL_DA_IMAGEM` pelos links das capturas de tela do seu app)*

---

## ✨ Funcionalidades Principais

* 👤 **Autenticação Segura:** Sistema robusto de Login e Cadastro para motoristas.
* 🏠 **Dashboard Interativo:** Painel de controle com acesso rápido às ações do dia a dia.
* 👧 **Gestão de Alunos:**
    * Cadastro completo de estudantes.
    * Visualização e edição de perfis individuais.
* 👨‍👩‍👧 **Gestão de Responsáveis:** Banco de dados de contatos dos pais/responsáveis.
* 🗺️ **Navegação Fluida:** Menu lateral (Navigation Drawer) para transição rápida entre módulos.
* 💾 **Persistência de Dados:** Funcionamento offline garantido pelo banco de dados local.

---

## 🛠️ Arquitetura e Tecnologias

Este projeto segue as melhores práticas de desenvolvimento Android moderno, utilizando a arquitetura **MVVM (Model-View-ViewModel)** para garantir um código desacoplado, testável e de fácil manutenção.

### 🏗️ Estrutura
* **Linguagem:** [Kotlin](https://kotlinlang.org/)
* **Arquitetura:** MVVM
* **Design Pattern:** Repository Pattern

### 📚 Bibliotecas e Ferramentas (Jetpack)
| Tecnologia | Função |
| :--- | :--- |
| **Navigation Component** | Gerenciamento do fluxo e grafo de navegação entre Fragments. |
| **ViewModel** | Gerenciamento de estado da UI, sobrevivendo a mudanças de configuração. |
| **LiveData** | Observabilidade de dados e comunicação reativa com a View. |
| **Room Database** | Abstração do SQLite para persistência de dados local (Offline First). |
| **View Binding** | Acesso às Views de forma segura (Null-safe). |
| **Material Design 3** | Componentes de UI modernos e acessíveis. |
| **CircleImageView** | Manipulação de imagens de perfil circulares. |

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar o ambiente de desenvolvimento:

### Pré-requisitos
* Android Studio Hedgehog (ou superior)
* JDK 11 (ou superior)

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/projeto-barrinha.git](https://github.com/SEU_USUARIO/projeto-barrinha.git)
    ```

2.  **Abra no Android Studio:**
    * Inicie o Android Studio.
    * Selecione `Open an existing project`.
    * Navegue até a pasta clonada.

3.  **Sincronização:**
    * Aguarde o Gradle baixar as dependências e indexar o projeto.

4.  **Execução:**
    * Conecte um dispositivo físico via USB ou inicie um Emulador (AVD).
    * Clique no botão **Run** (▶️) ou pressione `Shift + F10`.

---

<div align="center">

</div>
