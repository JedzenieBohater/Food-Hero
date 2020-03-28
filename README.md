# Food-Hero
[![Build Status](https://travis-ci.org/JedzenieBohater/Food-Hero.svg?branch=master)](https://travis-ci.org/JedzenieBohater/Food-Hero)

# Starting an application:
- Firstly, create *.jar file. Go to /src/backend and type: `mvn clean compile package`
- Next, go to /Docker and type: `docker-compose -f Docker/docker-compose-dev.yml up --build` to run an application

# Technology:

- Java 8 (Spring Boot, Spring Security, Hibernate)
- PostgreSQL
- JavaScript (ReactJS)
- Enzyme, Selenium
- Travis
- Docker

# Architecture

Application consists of 3 modules:
- Database (PostgreSQL)
- Backend (Java, Spring Boot, Spring Security, Maven)
- Frontend (ReactJS)

# Short description:

Food Hero is an application that implements the conception of meal bazaar.

The main aim of the system is to ensure the ability of selling and buying meals online. There are 2 groups of users here: buyers and cooks.

First of this group brings together users who want to purchase a meal offered by cooks. This users are able to browse, filter, review and buy meals. 

Second group is consisted of people who want to earn money - cooks. They are able to create their own offers for the prepared meals and to receive money from selling them. 

Both of this groups are cooperating in the whole process of buying a meal. Starting from creating an offer up to receiving the payment.

# Testing

We are using Selenium and Enzyme to test frontend part.

# Authors
* [Konrad Magiera](https://github.com/KonradMagiera) (KonradMagiera) - Frontend & Test
* [Patryk Zaniewski](https://github.com/PatrykZaniewski) (PatrykZaniewski) - Backend & Database
* [Bartek Siwiński](https://github.com/Pyrrun) (Pyrrun) - Frontend & Database
* [Jakub Celuch](https://github.com/JCeluch) (JCeluch) - Frontend
* [Łukasz Glapiak](https://github.com/Lukas2018) (Lukas2018) - Frontend
* [Mateusz Ciupa](https://github.com/MateuszCiupa) (MateuszCiupa) - Frontend & Test & SysOps
* [Mateusz Smoliński](https://github.com/matsmolinski) (matsmolinski) - Frontend & Documentation
