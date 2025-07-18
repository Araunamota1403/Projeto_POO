# 📋 SPC API - Sistema de Consulta de Dispositivos

## 🎯 O que é este projeto?

Este é um sistema Spring Boot que funciona como uma API intermediária para consulta de dispositivos do SPC (Serviço de Proteção ao Crédito).

**Funcionalidades:**
- Autenticar usuários com email e senha
- Consultar informações de dispositivos usando número de série
- Validar formato de números de série
- Calcular tempo desde a última mensagem do dispositivo

## Conceitos de POO Utilizados

Este projeto demonstra os principais conceitos de Programação Orientada a Objetos:

- **🏗️ Encapsulamento:** Classes com atributos privados e métodos públicos (getters/setters)
- **🧱 Composição:** Classe `Device` contém objetos `DeviceConfig` e `DeviceStatus`
- **🎯 Separação de Responsabilidades:** Controllers (endpoints), Services (lógica), Models (dados)
- **⚠️ Tratamento de Exceções:** Exceções customizadas (`InvalidSerialException`, `MissingTokenException`)
- **🔌 Injeção de Dependências:** Spring gerencia as dependências automaticamente

## 🚀 Como executar o projeto?

### Pré-requisitos:
- Java 17+ instalado
- Maven instalado

### Passos para executar:

1. **Navegar para a pasta do projeto:**
```bash
cd Projeto_POO
```

2. **Compilar o projeto:**
```bash
mvn clean compile
```

3. **Criar o arquivo executável:**
```bash
mvn package -DskipTests
```

4. **Executar a aplicação:**
```bash
java -jar target/spc-api-0.0.1-SNAPSHOT.jar
```

5. **Testar se está funcionando:**
```bash
# Teste de validação (deve retornar erro de serial inválido):
curl -X GET "http://localhost:3000/api/device?serialNumber=invalid" -H "Authorization: Bearer test-token"
```

### ✅ Pronto!
A aplicação estará rodando em: **http://localhost:3000**

---

**🎓 Projeto desenvolvido para apresentação de Programação Orientada a Objetos**