
# Tech Challenge - Customer API

Microserviço responsável pela gestão dos clientes da lanchonete digital.
Este repositório faz parte da solução maior descrita no repositório principal de documentação.

**Observação:** Este repositório é parte de um sistema maior com outros microsserviços que interagem entre si, como o `Product API`, `Order API`, e `Payment API`. A integração entre os microsserviços é realizada através de chamadas HTTP REST.

📚 Para mais detalhes sobre a solução e arquitetura completa, consulte nossa documentação [Repositório Overview](https://github.com/RenatoMartinsXrd/fiap-soat-tech-challenge-overview)

---

## 📦 Tecnologias Utilizadas

- **Java 21** + **Spring Boot**
- **PostgreSQL** + **Flyway** 
- **Docker** + **Docker Compose**
- **Swagger (OpenAPI)**
- **JUnit** + **Mockito** 
- **Clean Architecture**
---

## ▶️ Executando a API Localmente

### **Pré-requisitos:**
- **Docker** + **Docker Compose**
- **Java 21** (caso queira rodar pela IDE)

### **Passos para executar:**
1. Clone o repositório:
   ```bash
   git clone https://github.com/RenatoMartinsXrd/fiap-soat-tech-challenge-customer-api.git
   cd fiap-soat-tech-challenge-customer-api
   ```

2. Utilize o Docker Compose para rodar o serviço localmente:
   ```bash
   docker-compose up --build
   ```

3. A API estará disponível em [http://localhost:8081](http://localhost:8081).

4. Caso precise acessar o banco de dados, pode usar o PGAdmin na porta [http://localhost:5051](http://localhost:5051).

---

### **Acessando a documentação OpenAPI/Swagger**

- A documentação da API pode ser acessada através do Swagger UI. Abra o seguinte URL no seu navegador:
  ```sh
  http://localhost:8081/swagger-ui/index.html
  ```

---

## 🔌 Endpoints Disponíveis

| Método | Rota              | Descrição                               |
|--------|-------------------|-----------------------------------------|
| GET    | /customers         | Lista todos os clientes                |
| GET    | /customers/{id}    | Retorna um cliente específico          |
| POST   | /customers         | Cadastra um novo cliente               |
| POST   | /customers/identifyOrCreate | Identifica ou cria um cliente |
  
> A documentação completa pode ser acessada via Swagger.

---

## 🧠 Arquitetura

Este microserviço adota o padrão **Clean Architecture**, com foco em separação de responsabilidades e independência tecnológica.

### **Principais camadas:**
- **core**: Entidades, regras de negócio, casos de uso e contratos (ports).
- **adapters**: Controladores e mapeadores que traduzem o mundo externo para o domínio.
- **frameworks**: Implementações específicas (REST, JPA, integrações externas).
- **shared**: Configurações globais e utilitários comuns.

---

## 🧪 Testes

Os testes são realizados com **JUnit** e **Mockito** para garantir a qualidade do código.

### **Execução dos testes**

1. No diretório do repositório, execute o comando Maven para rodar os testes:

   ```bash
   mvn test
   ```

2. Para gerar o relatório de cobertura de testes com **Jacoco**, execute:

   ```bash
   mvn clean verify
   ```

3. A cobertura mínima exigida é de **80%**.

---

## 👥 Equipe

- Renato Martins - @RenatoMartinsXrd
- Daniel Quevedo - @dequevedo

---
