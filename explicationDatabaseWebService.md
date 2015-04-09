# Introduction #

explication of tables and links of the database webService

# Details #

## database schema: ##

![http://img84.imageshack.us/img84/4611/webservice.jpg](http://img84.imageshack.us/img84/4611/webservice.jpg)

This database is composed of 5 tables.

We have a central table (signature) and four other table (user, document, certificate and competence).

## In terms of foreign keys: ##

-The signature table will have a document, a user, a certificate and competence.

-The user table will have multiple signatures and several certificates.

-The certificate table will have multiple signatures and  user.

-The document table and competence have just one signature.

## The attributes of each table. ##

### The signature table has as foreign keys : ###
-creationDate

### The certificate table has as foreign keys : ###
-publicKey

-privateKey

### The user table has as foreign keys : ###
-firstName

-LastName

-mail

### The competence table has as foreign keys : ###
-name

-description

-creationDate

### The document table has as foreign keys : ###
-name

-sum

-creationDate