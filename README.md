#How to reproduce ?

## Launch that HTTP request : 

curl --request PUT \
--url 'http://localhost:8082/api/payins?id=59981081' \
--header 'Content-Type: application/json' \
--data '{
"status" : "SUCCEEDED"
}'