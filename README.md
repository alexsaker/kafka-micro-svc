# kafka-micro-svc

## Build micro services

$build.sh

## Launch micro services and kafka

docker-compose up --build

## Test it using postman

POST http://192.168.99.102:8090/products

{
"brand":"Nike",
"name":"JORDAN",
"price":77,
"quantity":397
}
