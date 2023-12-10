# Product-service 

The Product-service is a Spring Boot microservice that exposes REST API endpoints for the product service, supporting all CRUD operations. For detailed information on each API, refer to the Swagger documentation.

http://localhost:9090/swagger-ui.html#/

## Set-up to run in local
### Step 1: Create image file of product service
```agsl
docker build -t product-service .
```
### Step 2: Run the docker-compose file 
```agsl
 docker-compose build
 docker-compose up  
```
### Step 3: Run the application using the below link
**Swagger page:** http://localhost:9090/swagger-ui.html#/ \
**Base url:** http://localhost:9090/product

![Screenshot 2023-12-10 at 1 37 36 PM](https://github.com/zeeshanzsh/productService/assets/30749560/e8a2b073-f7ee-4d64-a44d-b2d43dd2adc5)

