# Utilisez l'image de base OpenJDK 8
FROM openjdk:8-jdk

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le fichier JAR de votre application dans le conteneur (assurez-vous que le nom du fichier JAR correspond à votre projet)
COPY target/users-micoservice-0.0.1-SNAPSHOT.jar app.jar

# Exposez le port sur lequel votre application Spring Boot écoute (généralement 8080)
EXPOSE 8080

# Installez MySQL Server dans le conteneur
RUN apt-get update && apt-get install -y mysql-server

# Copiez le fichier de configuration de MySQL (le cas échéant)
# COPY path/to/mysql.conf /etc/mysql/conf.d/

# Définissez le mot de passe root pour MySQL (changez-le selon vos besoins)
ENV MYSQL_ROOT_PASSWORD=root_password

# Exécutez MySQL Server
CMD ["mysqld"]

# Commande pour exécuter votre application Spring Boot
CMD ["java", "-jar", "app.jar"]

