# Introduction #

explication of tables and links of the database webApplication

# Details #

## DataBase Schema: ##

![http://img832.imageshack.us/img832/8121/webapp.jpg](http://img832.imageshack.us/img832/8121/webapp.jpg)

This database is composed of 5 tables.

We have a central table (Acquisition) and four other table (user, competence, degree,document ).

## In terms of foreign keys: ##
-The Acquisition table will have a document, a owner, a document and competence.

-The user table will have multiple competence and multiple acquisitions.

-The competence table will have multiple owner,multiple acquisitions and multiple degrees.

-The document table will have multiple acquisitions.

-The degrees table will have multiple comptence.

## The attributes of each table. ##
### The owner table has as foreign keys : ###
-firstName

-lastName

-mail

-password

-adress

-zipCode

-town

-phone

### The Acquisition table has as foreign keys : ###
- creattionDate

### The competence table has as foreign keys : ###
-name

-description

### he document table has as foreign keys : ###
-name

-sum

-url

-creationDate

### he degrees table has as foreign keys : ###
-name

-description