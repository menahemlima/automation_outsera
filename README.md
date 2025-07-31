# Projeto de Automação de Testes

Este repositório contém uma suíte de testes automatizados que cobrem diferentes camadas de uma aplicação, incluindo testes de API, End-to-End (Web), Mobile e Performance.

---
<br>

## Estrutura do Projeto
O repositório está organizado em quatro projetos principais, cada um focado em um tipo específico de teste:

  📁 api_restassured (Testes de API)
   - Este projeto foca na validação de endpoints da API, utilizando Rest-Assured para fazer as requisições HTTP e validações.
  ```
   api_restassured/
├── src/test/java/
│   ├── com/reqres/api/clients       # Clientes HTTP para interagir com a API
│   ├── com/reqres/api/endpoints     # Mapeamento e construção dos endpoints
│   ├── com/reqres/api/pojos         # Plain Old Java Objects para serialização/deserialização
│   ├── com/reqres/config            # Classes de configuração do ambiente
│   ├── com/reqres/hooks             # Hooks do Cucumber (Before/After)
│   ├── com/reqres/runners           # Classes para executar os testes Cucumber
│   ├── com/reqres/stepDefinitions   # Implementação dos passos dos cenários BDD
│   └── com/reqres/utils             # Funções utilitárias
├── src/test/resources/
│   ├── features/                    # Arquivos .feature com os cenários em Gherkin
│   ├── schemas/                     # Schemas JSON para validação de contratos
│   ├── application.properties       # Configurações de ambiente (URLs, credenciais)
│   └── log4j2.xml                   # Configuração de logs
└── pom.xml                          # Dependências e configurações do Maven
```
  📁 e2e_selenium (Testes End-to-End Web)
  - Este projeto contém testes automatizados para a interface web, simulando a jornada do usuário final com Selenium WebDriver.
 ```
e2e_selenium/
├── src/test/java/
│   ├── com/automation/web/config    # Classes de configuração do WebDriver
│   ├── com/automation/web/hooks     # Hooks do Cucumber
│   ├── com/automation/web/pages     # Mapeamento de elementos e ações das páginas (Page Objects)
│   ├── com/automation/web/runners   # Classes para executar os testes
│   └── com/automation/web/stepDefinitions # Implementação dos passos Gherkin
├── src/test/resources/
│   ├── features/                    # Arquivos .feature com os cenários web
│   ├── application.properties       # Configurações de ambiente
│   └── log4j2.xml                   # Configuração de logs
└── pom.xml                          # Dependências e configurações do Maven
 ```
 📁 mobile_appium (Testes Mobile)
 - Este projeto é dedicado aos testes em dispositivos móveis (Android/iOS) utilizando Appium.
```
mobile_appium/
├── src/test/java/
│   ├── com/automation/mobile/config  # Configuração do Appium Driver e capabilities
│   ├── com/automation/mobile/hooks   # Hooks do Cucumber
│   ├── com/automation/mobile/pages   # Page Objects para as telas do app
│   ├── com/automation/mobile/runners # Classes para executar os testes
│   └── com/automation/mobile/stepDefinitions # Implementação dos passos Gherkin
├── src/test/resources/
│   ├── apps/                         # Local para armazenar os arquivos .apk ou .ipa
│   ├── features/                     # Arquivos .feature com os cenários mobile
│   └── application.properties        # Configurações de ambiente e devices
└── pom.xml                           # Dependências e configurações do Maven
```
 📁 k6_performance (Testes de Performance)
 - Este projeto utiliza a ferramenta k6 para executar testes de carga, estresse e validação de SLAs de performance.
```
k6_performance/
├── app/                             # Código da aplicação (se aplicável)
├── dataset/                         # Massa de dados para ser usada nos testes
├── k6/
│   ├── src/
│   │   ├── api/                     # Funções para realizar chamadas de API
│   │   ├── config/                  # Configurações de ambiente, thresholds
│   │   ├── data/                    # Módulos para manipulação de dados
│   │   ├── reporters/               # Configuração de relatórios customizados
│   │   └── utils/                   # Funções utilitárias
│   └── load_test.js                 # Script principal do teste de performance
```

---
<br>

## 🛠️ Ferramentas e Linguagens Utilizadas
| Categoria | Tecnologia | Propósito |
| :--- | :--- | :--- |
| *Linguagem* | Java 21 | Desenvolvimento dos testes de API, Web e Mobile. |
| | JavaScript (ES6+) | Desenvolvimento dos scripts de performance. |
| | nodeJS (22+) | Instalação de pacotes JS. |
| | Gherkin | Escrita dos cenários de teste em formato BDD. |
| *Build & Dependência* | Maven | Gerenciamento de dependências e ciclo de vida dos projetos Java. |
| *Testes de API* | Rest-Assured | Framework para testes de serviços REST de forma simples em Java. |
| *Testes Web E2E* | Selenium WebDriver | Automação de navegadores para testes de interface web. |
| *Testes Mobile* | Appium | Automação de testes para aplicações nativas, híbridas e web mobile. |
| *Testes de Performance*| k6 | Ferramenta moderna para testes de carga e performance. |
| *Behavior-Driven Dev.*| Cucumber | Framework BDD para escrever testes em linguagem natural. |
| *Logging* | Log4j 2 | Biblioteca para registro de logs da execução dos testes. |

---
<br>

## 🧪 Técnicas de Testes Utilizadas

A estratégia de testes foi definida com base em uma combinação de técnicas para garantir a máxima cobertura e eficiência.

#### 🔍 Análise de Risco

Os cenários de teste são priorizados com base no risco de negócio e na complexidade técnica. Funcionalidades críticas como:

- Autenticação  
- Finalização de compra
- Preenchimento de dados  
- Fluxos principais de usuário  

#### 🔍 Testes de performance
 São aplicados com foco na **escalabilidade** e **estabilidade**, como resultado direto da análise de risco.



#### 🔍 Heurísticas de Teste

Utilizei heurísticas para guiar os testes exploratórios e a criação de novos cenários:

- CRUD: Para a API, garanti que as operações de **Criar**, **Ler**, **Atualizar** e **Deletar** sejam testadas para as principais entidades do sistema.

#### 🔍Casos de Uso (BDD)
  Foi adotado a abordagem de **Desenvolvimento Guiado por Comportamento (BDD)** com **Cucumber**.  
  Cada arquivo `.feature` representa um caso de uso ou uma história de usuário, descrita em **Gherkin**.

 Isso torna os testes compreensíveis para stakeholders não técnicos e garante que a automação esteja alinhada com os requisitos de negócio.

---
<br>

## 🏷️ Tags de Testes

Foi utilizado **tags do Cucumber** para organizar e filtrar a execução dos testes. As tags são definidas nos arquivos `.feature` acima dos cenários ou funcionalidades.

- **Mobile:** `@regression`, `@login`, `@form`  
- **Web:** `@regression`, `@login`, `@purchase`  
- **API:** `@regression`, `@login`, `@schema`, `@list`, `@delete`, `@create`, `@update`

---
<br>

## 🚀 Passo a Passo para Execução Local

Siga as instruções abaixo para clonar, configurar o ambiente e executar os testes localmente.


### 1️⃣ Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas e configuradas:

- ✅ Git  
- ☕ Java JDK 21 ou superior  
- 🌐 Node.js e npm (para gerenciamento de dependências JavaScript e execução de scripts)
- 🛠️ Apache Maven  
- 📈 k6 (para testes de performance)  
- 💻 IDE de sua preferência (IntelliJ, Eclipse, VS Code)  
- 📱 Appium Server (apenas para testes mobile Android)
- 🤖 Android Studio (apenas para testes mobile Android)


### 2️⃣ Clonando o Repositório

```bash
git clone https://github.com/menahemlima/automation_outsera.git
```
### 3️⃣ Configuração e Execução Local

a)**Testes API**

Para a realização dos testes de api foi utilizada uma api externa https://reqres.in/

Navegue até a pasta do projeto desejado:

```bash
cd api_restassured
```
Instale as Dependências do POM:

  ```bash
  mvn clean install
  ```
Execute os Testes:
 Executar todos os testes

  ```bash
mvn clean test
  ```

Executar testes por tag @regression
  ```bash
mvn test -Dcucumber.filter.tags="@regression"
  ```

Relatório de testes é gerado e salvo na pasta:
C:\...\git\automation_outsera\api_restassured\target\cucumber-reports

---
<br>

b) **Testes Web**

Navegue até a pasta do projeto desejado:
```bash
cd e2e_selenium
```
Instale as Dependências do POM:
  ```bash
  mvn clean install
  ```
Execute os Testes:
 Executar todos os testes
  ```bash
mvn clean test
  ```
Executar testes por tag @regression

  ```bash
mvn test -Dcucumber.filter.tags="@regression"
  ```

Relatório de testes é gerado e salvo na pasta:
C:\...\git\automation_outsera\e2e_selenium\target\cucumber-reports

---
<br>

 c) **Testes Mobile**

**Prérequisitos:**
Instalação da configuração 

Siga os passos para instalação correta do NodeJS:
https://nodejs.org/pt/download

- Windows:
Instalação do Appium Server:
```bash
cd npm install -g appium@latest
```

 Windows:
Instalação do UIAutomator2:
```bash
cd appium driver install uiautomator2
```

Siga os passos para instalação correta do Android Studio:
https://developer.android.com/studio?hl=pt-br

Instale um Device virtual, pode ser o mais atual.
![Image](https://github.com/user-attachments/assets/645ab70b-8772-4792-a090-e16d66201e1c)

Execução dos testes:

Subir o server do Appium:
Abra um terminal novo e digite:
```bash
appium
```

![Image](https://github.com/user-attachments/assets/078e7027-1a20-42b5-90ee-33cad72506e4)

Executar o device virtualizado:

![Image](https://github.com/user-attachments/assets/e9f57d7e-b8b5-486d-bf98-4376a9a2a414)

* Caso esteja usando um device atual
Alterar a propriedades do platform.version = 14

![Image](https://github.com/user-attachments/assets/52f17b93-9711-44a1-8951-7a9c317a469f)

Navegue até a pasta:
```bash
cd mobile_appium
```
Instale as Dependências do POM:
  ```bash
  mvn clean install
  ```
Execute os Testes:
 Executar todos os testes
  ```bash
mvn clean test
  ```
Executar testes por tag @regression

  ```bash
mvn test -Dcucumber.filter.tags="@regression"
  ```

![Image](https://github.com/user-attachments/assets/e473514e-c4a6-4198-89b4-07ffeb6a4f8b)

Relatório de testes é gerado e salvo na pasta:
C:\...\git\automation_outsera\mobile_appium\target\cucumber-reports

---
<br>

d) **Testes de Performance (k6)**

**Prérequisitos:**

Instalação do Python

- Windows:
Siga os passos para instalação correta:
[Instalação do Python no Windows](https://python.org.br/instalacao-windows/)

- Linux:
```bash
sudo apt install python3
```

### Instalar as dependências do Python
1. Acesse o diretório do projeto:
   ```bash
   C:\...\git\automation_outsera\k6_performance\app
   ```
2. Execute o comando:
   ```bash
   pip install -r requirements.txt
   ```

Instalar o K6

Prérequisitos:
Siga os passos para instalação correta do K6, conforme sua escolha:
https://grafana.com/docs/k6/latest/set-up/install-k6/

- Windows
Instale utilizando o Chocolatey:
```bash
choco install k6 -y
```

- Linux
```bash
sudo apt install -y k6
```

Executar a aplicação para iniciar os testes
1. Acesse o repositório do projeto:
```bash
cd k6_performance/app
```
2. Execute o comando para executar o servidor do app:
   ```bash
   python aplicativo.py

Com o aplicativo executando

![Image](https://github.com/user-attachments/assets/17f93a73-7fbc-4767-882b-9b5d704e5799)

Navegue até a pasta:

```bash
cd k6_performance/k6
```

Execute os Testes:
     Execução Padrão:
```bash
k6 run load_test.js
```
Relatório de testes é gerado e salvo na pasta:
C:\...\git\automation_outsera\k6_performance\k6

---
<br>

## 📊 Relatório dos testes

### Relatório Api

<img width="1252" height="586" alt="Image" src="https://github.com/user-attachments/assets/cbce7bd9-3b54-448a-892f-037b4bc31a74" />

<img width="979" height="495" alt="Image" src="https://github.com/user-attachments/assets/5069a120-085a-4879-b792-8e4db9bb69e7" />

✅ 09 testes passaram com sucesso.

❌ 03 testes falharam durante a execução.

**Falha 01:** Api retornando 201 no endpoint de Create New User quando é informado dados inválidos com caracteres especiais. Esperado 400.

**Falha 02:** Api retornando 204 no endpoint de Delete a User quando é informado um usuário inexistente. Esperado 400.

**Falha 03:** Api retornando 200 no endpoint de Update a User quando é informando um usuário inexistente. Esperado 400.

#
### Relatório Web

<img width="1245" height="503" alt="Image" src="https://github.com/user-attachments/assets/412b3c6b-fb02-4104-8dee-0e07315c986d" />

<img width="1036" height="427" alt="Image" src="https://github.com/user-attachments/assets/08315b16-0811-43e3-97d6-8fb862e96eaf" />

✅ A execução dos testes foi concluída com êxito, sem a identificação de falhas.

#

### Relatório Mobile

<img width="1245" height="512" alt="Image" src="https://github.com/user-attachments/assets/208fc0df-d10b-4cde-97ca-781d52773d84" />

<img width="951" height="511" alt="Image" src="https://github.com/user-attachments/assets/3c1fda91-6d77-4c38-9f53-e352cb12b967" />


✅ A execução dos testes foi concluída com êxito, sem a identificação de falhas.

#


### Relatório CI

![Relatório CI](https://github.com/user-attachments/assets/3ab392df-b39d-4ce0-afee-13d42737560a)

Após execução da CI no github Actions, o relatório de testes podem ser acessados normalmente

1. Acessar o respositório: https://github.com/menahemlima/automation_outsera
2. Clicar em Actions > workflow run
3. Clicar no workflow > Re-run all jobs.


### Relatório Performance

<img width="1240" height="761" alt="Image" src="https://github.com/user-attachments/assets/14b90ee3-7c96-451d-94ad-90ec459120fd" />

#

O teste foi configurado para rodar com 500 usuários virtuais por um período de 5 minutos. A cada iteração, um usuário virtual envia uma requisição para o endpoint /predict e faz uma pausa de 1 segundo (sleep(1)). Isso explica por que a iteration_duration no relatório tem uma média de ~1013ms (1000ms da pausa + o tempo da requisição).

**✅ Pontos Positivos (Confirmados pelo Código)
Estabilidade (0 Requisições Falhadas):**

O teste tinha um critério de aceitação (threshold) que permitia uma taxa de falha de no máximo 1% (rate<0.01).

O resultado de 0 falhas no relatório (k601.jpg) mostra que este critério foi atendido com folga, confirmando a alta disponibilidade da aplicação sob a carga de 500 VUs.

Performance (0 Limites Violados):

O critério de performance mais importante era que o 95º percentil do tempo de resposta (http_req_duration) deveria ser menor que 250ms (p(95)<250).

O relatório mostra que o p(95) foi de apenas 22.73ms, um resultado excelente e muito abaixo do limite definido. Isso significa que a aplicação é consistentemente rápida para 95% dos usuários.

**❌ Causa das 245 Verificações Falhadas**

A análise anterior suspeitava de erros na aplicação, e o código nos mostra exatamente o que aconteceu.

O arquivo assertions.js define 3 "checks" (verificações) que são executados para cada uma das 148.286 requisições:

O status da resposta deve ser 200.

O tempo de resposta daquela requisição individual (r.timings.duration) deve ser menor que 300ms.

A resposta JSON não pode ser nula.

O relatório mostra que o tempo máximo de resposta (Maximum) foi de 1893.54ms.

**Conclusão da Falha:**

As 245 verificações falharam porque, embora a média de performance seja excelente, 245 requisições individuais levaram mais de 300ms para responder, falhando no segundo check definido em assertions.js.

Isso representa apenas 0.16% do total de requisições, o que não foi suficiente para violar o threshold geral de p(95), mas foi corretamente capturado como uma falha de verificação individual.

**Recomendação:**

A aplicação é robusta, estável e atende aos critérios de performance definidos. A recomendação agora é mais específica:

Investigar a causa dos outliers de latência. É preciso descobrir por que um pequeno subconjunto de requisições (~0.16%) ultrapassa o limite de 300ms. Possíveis causas incluem:

"Cold Starts" de infraestrutura (ex: funções serverless).

Picos de "Garbage Collection" no servidor da aplicação.

Consultas específicas no banco de dados que são mais lentas.

Contenção de recursos momentânea no servidor sob carga.

Corrigir a causa desses picos de latência garantirá uma experiência rápida e consistente para 100% dos usuários.

---
<br>

## 📄 Links úteis:
* Java - [https://www.java.com/pt-BR/download/](https://www.java.com/pt-BR/download/)
* NodeJS - [https://nodejs.org/pt](https://nodejs.org/pt)
* Maven - [https://maven.apache.org/](https://maven.apache.org/)
* Junit - [https://junit.org/](https://junit.org/)
* Cucumber -  [https://cucumber.io/](https://cucumber.io/)
* Appium - [https://appium.io/docs/en/latest/](https://appium.io/docs/en/latest/)
* Selenium - [https://www.selenium.dev/](https://www.selenium.dev/)
* RestAssured - [https://rest-assured.io/](https://rest-assured.io/)
* K6 - [https://k6.io/](https://k6.io/)
* Android Studio - [https://developer.android.com/studio?hl=pt-br](https://developer.android.com/studio?hl=pt-br)

