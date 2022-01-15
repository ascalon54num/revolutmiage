# Revolutmiage
Auteur: Stéphane Mairesse
## Table des contenus
1. [Informations-Generales](#informations-generales)
2. [Technologies](#technologies)
3. [Prerequis](#prerequis)
4. [Usage](#usage)

## Informations-Generales
***
Le projet Revolutmiage est réalisé dans le cadre du cours de Passage à l'échelle.

## Technologies
***
Les technologies utilisées dans ce projet sont:

* [JAVA](https://openjdk.java.net/) - Programming langage and runtime environnement JAVA 11
* [SpringBoot](https://start.spring.io/) - Framework de développement JAVA pour microservices
* [Docker] (https://docker.io) - Outil de conteneurisation
* [Consul] 
* [Postgresql]

## Prerequis
***
Les technologies suivantes doivent être installées :

* JAVA 11
* Docker

## Usage
***
La dockerisation permet une installation simple.
Il suffit de se mettre à la racine du projet là où il y a le fichier docker-compose.yml et d'utiliser la première fois la commande:
* ```sh
    sudo docker-compose up --build -d
  ```

Pour recréer les conteneur du projet si ils sont déjà existants :
*  ```sh
    sudo docker-compose up  -d
    ```

Une fois tout les services up, les points d'accès des différents services sont:
* http://localhost:8080 pour le gateway
* http://localhost:8081 pour la banque
* http://localhost:8082 pour le commerce
* http://localhost:8500 pour l'instance de Consul
* http://localhost:5432 pour l'instance de Postgres

Pour bien démarrer avec le service banque il faut d'abord créer un compte avec la route [POST http://localhost:8081/comptes] avec pour body par exemple:
```json
{
    "nom": "Dupont",
    "prenom": "Jean",
    "pays": "France",
    "noPasseport":"09Az54625",
    "numTel": "+33650214256",
    "secret": "password",
    "iban": "FR761034328909432347"
}
```
Puis il faut récupèrer un token d'authentification si on veut utiliser d'autres fonctionnalités du service (sauf pour l’authentification, la création d’un compte, la vérification de carte et la doc). Pour cela il faut s'authentifier avec la route [POST http://localhost:8081/authenticate] et avec par exemple le body :
```json
{
    "username":"09Az54625", //noPasseport du compte
    "password":"password" //secret du compte
}
```
On obtient alors le token jwt.
Il doit alors être utlisé dans les autres requêtes en tant que Bearer token.

Routes disponibles:

- **/authenticate** (Post uniquement) pour l'authentification
- **/comptes** pour les comptes
- **/transactions** pour les opérations
- **/cartes** pour les cartes
- **/cartes/validate** (POST uniquement) pour vérifier la correspondance entre un numéros de carte et un code donné. Si le code est bon on obtient l’id du compte associé à la carte.
- **/swagger-ui.html** pour la documentation

Pour le service **Commerce**, la seule route existante est la route [POST http://localhost:8082/ventes] qui n'est malheureusement pas fonctionnelle à cause du problème de gateway (cf rapport). Vous serez automatique redirigé sur le fallback du circuit breaker.