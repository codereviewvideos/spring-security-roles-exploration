```
###
GET http://localhost:8080/

###
GET http://localhost:8080/anything

###
GET http://localhost:8080/only-authenticated

###
GET http://localhost:8080/only-authorised

###
GET http://localhost:8080/admin

###
GET http://localhost:8080/only-authenticated
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/only-authenticated
Authorization: Basic authorised authorised

###
GET http://localhost:8080/only-authenticated
Authorization: Basic admin admin

###
GET http://localhost:8080/only-authorised
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/only-authorised
Authorization: Basic authorised authorised

###
GET http://localhost:8080/only-authorised
Authorization: Basic admin admin


###
GET http://localhost:8080/admin
Authorization: Basic authenticated authenticated

###
GET http://localhost:8080/admin
Authorization: Basic authorised authorised

###
GET http://localhost:8080/admin
Authorization: Basic admin admin

```