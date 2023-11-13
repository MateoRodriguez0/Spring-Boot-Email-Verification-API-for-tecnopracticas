# Spring-Boot-Email-Verification-API-for-tecnopracticas

Api de mensajeria automatica para proyecto academico de practicas estudiantiles con mailSender a traves de Gmail.

![Spring](https://github.com/MateoRodriguez0/Spring-Boot-Email-Verification-API-for-tecnopracticas/assets/107595139/211c1fe9-2acc-45ee-8d30-b9a007c395ca)

![Captura de pantalla 2023-11-12 162744](https://github.com/MateoRodriguez0/Spring-Boot-Email-Verification-API-for-tecnopracticas/assets/107595139/59f64de4-9b15-4dec-90bf-20f01b0d4e70)
![Captura de pantalla 2023-11-12 162831](https://github.com/MateoRodriguez0/Spring-Boot-Email-Verification-API-for-tecnopracticas/assets/107595139/953d7b43-596c-4cc1-9a89-087fb5598d94)

## aplication.yaml

```yaml
spring:
  mail:
    host: smtp.gmail.com
    port: 587
    username: example@github.com
    password: secret
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
 POST http://localhost:8080/mail/enviar?email=example@github.com
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

