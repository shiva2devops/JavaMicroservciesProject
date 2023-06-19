## Build App
    mvn clean install

## Test cases run

    mvn test

## Run App

    mvn spring-boot:run

## Test app

### Test save data

    curl --location 'localhost:8080/save/account' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic bWFuYWdlcjE6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=9B4A29D6D6ACBF3E574018B92B4B324C' \
--data '{
"id":"111",
"balance":25000,
"name":"account2"
}'

### Test Get account

curl --location 'localhost:8080/get/account?account_id=111' \
--header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=222B262477CB6B5405773D6E4E8D90D5'

### Test update account

curl --location --request PUT 'localhost:8080/update/account' \
--header 'account_id: 111' \
--header 'ammount: 30000' \
--header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=222B262477CB6B5405773D6E4E8D90D5'

### Test Delete Account

curl --location --request DELETE 'localhost:8080/delete/account/111' \
--header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
--header 'Cookie: JSESSIONID=222B262477CB6B5405773D6E4E8D90D5'

