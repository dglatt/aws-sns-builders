#!/bin/bash

openssl genrsa -des -out unittest.key 2048
openssl req -new -key unittest.key -out unittest.csr
cp unittest.key unittest.key.org
openssl rsa -in unittest.key.org -out unittest.key 
openssl x509 -req -days 365 -in unittest.csr -signkey unittest.key -out unittest.crt
openssl pkcs12 -export -in unittest.crt -inkey unittest.key -out unittest.p12

