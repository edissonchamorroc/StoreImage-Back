# StoreImage-Back

Creación de proyecto en spring boot, con el fin de constituir una API con marco de trabajo RestFull. El proposito de esta API es almacenar en una de base de datos postgre la información de la imagen con el fin de poder visualizarla en el navegador.
Dentro de los archivos en el repositorio, se omite por seguridad la configuración real para la conexión con la base de datos, pero se agrega una configuración a modo de ejemplo.

El proyecto está desplegado en una instancia de AWS EC2, por lo cual es posible realizar pruebas en su herramienta de preferencia.

GET ALL IMAGES /POST: http://localhost:8080/api/image/
GET OR DELETE OF ONE IMAGES: http://localhost:8080/api/image/nnmbre-imagen.formato

## Estructura del proyecto

![image](https://user-images.githubusercontent.com/71468355/214082059-4d58db7e-2d33-411c-899f-9e4a05225b53.png)

## Ejemplo de conexión con base de datos ( Application.properties )

![image](https://user-images.githubusercontent.com/71468355/214085448-9d69b1b6-dce1-4c89-a0ee-8c7dcc59d701.png)

