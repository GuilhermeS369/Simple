# Simple

## O Projeto
Esse é um projeto Back-end (Webservices, JPA+H2) onde você terá uma aplicação onde existe um cadastro de pessoas com diversos endereços.

## 📋 Pré-requsitos

### Java 11 Zulu: 

- https://cdn.azul.com/zulu/bin/zulu18.32.11-ca-jdk18.0.2-win_x64.msi

## 🔧 Instruções 

### Instalar o Zulu

Baixar e realiza a instalação marcando a opção JAVA_HOME para instalação.

Verificar se foi registrando a variavel de ambiente, caso contrario registrar.

## ⚙️ Usabilidade (Requisições)

Ao executar a aplicação acesse: http://localhost:8080/swagger-ui.html para testar a aplicação

Ou veja abaixo as requisições possiveis

`GET` Client

```yaml
Buscar pelo ID:
localhost:8080/client/{ID}
Exemplo:
localhost:8080/client/1
```

___________________________________________________________________________________________________________________

`POST` Client

```yaml
Inserir um novo usuário:
localhost:8080/client/

Enviar no Body em formato JSON:
{
  "addressList": [
    {
      "cep": 0,
      "mainaddress": {n},
      "city": "string",
      "id": 0,
      "number": 0,
      "street": "string"
    }
  ],
  "date": "yyyy-MM-dd'T'HH:mm:ss'Z'",
  "id": 0,
  "name": "string"
}
```
____________________________________________________________________________________________________________________

`PUT` Client

```yaml
Alterar um usuário existente:
localhost:8080/client/{ID}
Exemplo:
localhost:8080/client/1

Enviar no Body em formato JSON:
{
  "date": "2023-01-18T04:34:25.327Z",
  "name": "string"
}
```
____________________________________________________________________________________________________________________

`DELETE` Client

```yaml
localhost:8080/client/{ID}
Exemplo:
localhost:8080/client/1
```
____________________________________________________________________________________________________________________

____________________________________________________________________________________________________________________
`GET` AddressByClient

```yaml
Buscar pelo ID do Client:
localhost:8080/address/{ID}
Exemplo:
localhost:8080/address/1
```
___________________________________________________________________________________________________________________

`POST` InsertNewAddressByClient

```yaml
Inserir um novo endereço usando id do client:
localhost:8080/address/{ID}

Enviar no Body em formato JSON:
{
  "cep": 0,
  "mainaddress": {n},
  "city": "string",
  "id": 0,
  "number": 0,
  "street": "string"
}
```
___________________________________________________________________________________________________________________
`PUT` EditMainAddress

```yaml
Definir qual será o endereço principal do client:
localhost:8080/address/{idClient}/{idAddress}
Exemplo:
localhost:8080/address/1/3
```
___________________________________________________________________________________________________________________
`DELETE` DeleteAddress

```yaml
localhost:8080/address/{idAddress}
Exemplo:
localhost:8080/address/1
```

## 🛠️ Construído com

* [SpringToolsSuite4](https://spring.io/guides/gs/sts/) - IDE
* [SpringBoot](https://spring.io/) - Framework principal
* [Maven](https://maven.apache.org/) - Gerente de Dependência


## 📄 Licença

The scripts and documentation in this project are released under the [MIT License](license)
