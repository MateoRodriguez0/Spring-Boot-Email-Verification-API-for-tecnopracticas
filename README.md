# Spring-Boot-Email-Verification-API-for-tecnopracticas

Api de mensajeria automatica para proyecto academico de practicas estudiantiles con mailSender a traves de Gmail.

![Captura de pantalla 2023-11-12 162106](https://github.com/MateoRodriguez0/Spring-Boot-Email-Verification-API-for-tecnopracticas/assets/107595139/60b2914a-da7d-4648-8a4e-9baffb66c907)

![Captura de pantalla 2023-11-12 162141](https://github.com/MateoRodriguez0/Spring-Boot-Email-Verification-API-for-tecnopracticas/assets/107595139/54924feb-e6af-47d5-b346-d012a00669ec)


## aplication.yaml

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: mateo204r@gmail.com
    password: jqckrulxctshddgq
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true
  jackson:
    time-zone: America/Bogota
    
    

```
## API Reference

### Send email VerificationCode 

```http
 POST http://localhost:8080/mail/enviar
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. email al que se le enviara el codigo de verificación |



### Get VerificationCodes

```http
  GET http://localhost:8080/mail/verifications
```
devuelve todos los VerificationCodes registrados



### verificar codigo

```http
 POST http://localhost:8080/mail/verifier
```

| ReuquestBody| Type     | Description                |
| :-------- | :------- | :------------------------- |
| `verificationCode` | `VerificationCode` | **Required**. objeto que contiene el email y el codigo de verificación que se comprobara |

comprueba que el codigo de verificación sea correcto y no haya expirado 


### send email cuenta creada

```http
  GET http://localhost:8080/account-created
```
devuelve todos los VerificationCodes registrados

#### example

```javascript
 {
        "email": "mateo204r@gmail.com",
        "codigo": 406354
    }
```

