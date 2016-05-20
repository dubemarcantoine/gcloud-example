L'inspiration pour cet example vient du [compte Github de GoogleCloudPlatform](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/managed_vms/sparkjava)
# GCloud Example
---
Cet example démontre une simple application web configurée pour la librairie GCloud-java.

## Configuration
---
Si vous suivez mon atelier en classe, vous pouvez aller directement a l'étape 3.

1. Créez-vous un compte Google Developer. Suivez ces instructions : [Suivez ces instructions](https://cloud.google.com/docs/authentication#preparation) afin de configurer votre compte. Si vous voulez par la suite déployer l'application, vous devez [activer la facturation](https://support.google.com/cloud/?rd=2#topic=6288636) lorsque vous créez votre projet
2. Activer [l'API de Datastore](https://console.cloud.google.com/apis/api/datastore.googleapis.com/overview?) 
3. Configurez votre environnement local en installant la [librairie GCloud pour Java](https://cloud.google.com/sdk/)
4. Assurez-vous d'avoir Java 8 et Maven installés

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
### Datastore
Cet exemple utilise `Google Datastore` afin de persister et traiter les données transmises. Datastore est une base de donnée NoSQL de Google. Les attributs des entités sont tous indexés pour des requêtes plus rapides.
### Routes
Ce petit exemple démontrant des technologies de Google Cloud utilise la micro-librairie `SparkJava` pour les routes de l'API Rest. Par défaut, SparkJava utilise Jetty comme conteneur pour applications Servlet. L'application n'a donc pas besoin de services externes comme un serveur Tomcat. Le démarrage de l'application est donc rapide et le tout est démarré à partir du `main` principal.
Voici un exemple de route qui va chercher tous les usagers et les retourne en format json.
```
get("/users", (req, res) -> {
    return db.getUsers();
}, json());
```
Pour plus d'informations, voir [le site de SparkJava](https://sparkjava.com)
### Déploiment
Le fichier `src/main/appengine/app.yaml` décrit comment sera déployée l'application sur Google App Engine. Cet exemple utilise Docker qui spécifie simplement la version de la JDK et comment démarrer le jar généré par le build. Plusieurs configurations sont possibles. Par exemple, on peut spécifier des règles de `load balancer` ou des règles sur les configurations de la RAM ou des CPUs des instances.
Voici un exemple sur comment configurer un load balancer :
```
automatic_scaling:
  min_num_instances: 5
  max_num_instances: 20
  cool_down_period_sec: 120
  cpu_utilization:
    target_utilization: 0.5 # % CPU moyen des instances avant d'en ajouter ou en enlever.
```
Pour plus d'informations, voir [comment configurer un environnement flexible avec app.yaml dans Google App Engine](https://cloud.google.com/appengine/docs/flexible/java/configuring-your-app-with-app-yaml)
