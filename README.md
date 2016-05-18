L'inspiration pour cet example vient du [compte Github de GoogleCloudPlatform](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/managed_vms/sparkjava)
# GCloud Example
---
Cet example démontre une simple application web configurée pour la librairie GCloud-java.

## Configuration
---
Si vous suivez mon atelier en classe, vous pouvez aller directement a l'étape 2.

1. Créez-vous un compte Google Developer. Suivez ces instructions : [Suivez ces instructions](https://cloud.google.com/docs/authentication#preparation) afin de configurer votre compte. Si vous voulez par la suite déployer l'application, vous devez [activer la facturation](https://support.google.com/cloud/?rd=2#topic=6288636).
2. Configurez votre environnement local en installant la [librairie GCloud pour Java](https://cloud.google.com/sdk/)
3. Assurez-vous d'avoir Java 8 et Maven installés

## Lancer le projet localement
Si vous suivez mon atelier en classe

1. Téléchargez la [clé](https://support.google.com/cloud/?rd=2#topic=6288636) et créez votre variable d'environnement `GOOGLE_APPLICATION_CREDENTIALS` pointant vers l'emplacement du fichier.
2. Lancez le main situé à `com.appspot.gcloudExample.Main`
3. Dirigez-vous à l'adresse `localhost:8080` dans votre navigateur

Si vous ne suivez pas l'atelier en classe :

1. À la racine du projet, exécutez : `mvn clean package exec:java`
2. Dirigez-vous à l'adresse `localhost:8080` dans votre navigateur

## Déployer sur Google App Engine
Si vous avez activé la facturation lors de la configuration du projet, exécutez la commande suivante à la racine de votre projet : `mvn gcloud:deploy`

## Comment le projet fonctionne
