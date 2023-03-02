Pasos para probar la API

paso 1. Enviar la siguiente petición

Endpoint: http://localhost:8080/user/login?email=nisum@nisum.com&password=nisum
Metodo GET
Respuesta:
{
    "code": 200,
    "detail": "OK",
    "responseObject": {
        "response": {
            "email": "nisum@nisum.com",
            "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bUBuaXN1bS5jb20iLCJleHAiOjE2Nzc3OTQwNjYsImxhc3RMb2dpbiI6MTY3Nzc5Mzg4NjkyM30.9uvsS73WWM_DmsGRKYzr_R_TSMWb_i5EcUNunXEg_JA"
        }
    }
}
NOTA: esta api funciona enviando los params establecidos en el ejemplo ya que en la base de datos H2 se
guarda este usuario desde el archivo data.sql. De esta manera se autentica el usuario y se genera el token.

Paso 2. Obtener token generado en el Paso 1 y enviar la siguiente petición

Endopoint: http://localhost:8080/user
Metodo: POST
Body:
{
    "name": "Santiago Lozano Murcia",
    "email": "santtiagolozano5@gmail.com",
    "password" : "1aAAs123@",
    "phones" : [{
        "number" : "12344556",
        "cityCode": 1,
        "countryCode": 57
    }]
}
Authorization: Bearer Token (Se agrega el token obtenido en el paso 1)
Respuesta 1:
{
    "code": 201,
    "detail": "Created",
    "responseObject": {
        "response": {
            "id": 2,
            "name": "Santiago Lozano Murcia",
            "email": "santtiagolozano@gmail.com",
            "password": "1aAAs123@",
            "phones": [
                {
                    "number": "12344556",
                    "cityCode": 1,
                    "countryCode": 57
                }
            ],
            "created": "2023-03-02T22:00:02.592+00:00",
            "modified": "2023-03-02T22:00:02.592+00:00",
            "token": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJuaXN1bUBuaXN1bS5jb20iLCJleHAiOjE2Nzc3OTQ1NjMsImxhc3RMb2dpbiI6MTY3Nzc5NDM4MzE4Mn0.7kA-qVRec9DAJkw-a3od52npOkn8qESrZsCg9RJbKD8",
            "isActive": true,
            "lastLogin": "2023-03-02T21:59:43.182+00:00"
        }
    }
}

Respuesta 2:
{
    "code": 200,
    "detail": "OK",
    "message": "¡Error! Correo ya registrado."
}
