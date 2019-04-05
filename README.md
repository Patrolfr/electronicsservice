# Electronics service module


Postgresql db configration:

- Create greuser with password 'gre123'
- Create database: 
    `createdb -h localhost -p 5432 -U greuser electronicsdb`

- Getting authorization token for endpoints:
`POST http://localhost:8080/oauth/token` `x-www-form-urlencoded` with params: `username=user|admin`; `password=secret`; `grant_type=password`
- Autohorize with header: `authorization = bearer {token}`