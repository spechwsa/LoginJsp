## Application JAVA JEE JSP simulant une inscription et une authentification

Cette Application permet à un utilisateur de s'inscrire et à un utilisateur inscrit de se logger. 

# Technologie / framework utilisé:
- Java servlet
- page jsp
- mySql jdbc


## TEST
Ajouter un server WildFly sous eclipse configuré avec standalone-full.xml.  
Nécessite une base MySQL.  
Mettre à jour "WebContent\WEB-INF\lib\mysql-connector-java-8.0.14.jar" en fonction de la version.

## TIPS
- org.jboss.modules.ModuleNotFoundException: java.seS'il y a une erreur à propos du javax.servlet vérifier dans menu "configure path" onglet libraries qu'elle n'est pas "unbound".
Pour corriger il faut éditer la librairie et pointer au bon endroit.


- Pour l'erreur "org.jboss.modules.ModuleNotFoundException" java.se au lancement de jboss:
C'est un problème avec java11, qui devrait être fixé WildFly15: Le contournement est de double cliquer sur le serveur et ouvrir "Open Launch configuration" et ajouter --add-modules=java.se dans la partie VM argument.


- chaine de connection : url = jdbc:mysql://localhost:3306/bdd_ecole?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC&useSSL=false  
**zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC** est là pour contourner bug : "ava.sql.SQLException: The server time zone value 'Paris, Madrid (heure d?été)' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
" voir https://www.developpez.net/forums/d1876029/java/general-java/server-time-zone-non-reconnu/
**useSSL=false** est là pour contourner bug "exception is thrown when a connector is closed" voir https://github.com/brettwooldridge/HikariCP/issues/1268  

## fonctionnement
![Fonctionnement](./Model/connectionOk.svg?sanitize=true "Title")
