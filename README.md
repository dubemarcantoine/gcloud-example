L'inspiration pour cet example vient du [compte Github de GoogleCloudPlatform](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/managed_vms/sparkjava)
# GCloud Example
---
Cet example démontre une simple application web configurée pour la librairie GCloud-java.

## Configuration
---

1. Créez-vous un compte Google Developer. Suivez ces instructions : [Suivez ces instructions](https://cloud.google.com/docs/authentication#preparation) afin de configurer votre compte. Si vous voulez par la suite déployer l'application, vous devez [activer la facturation](https://support.google.com/cloud/?rd=2#topic=6288636) lorsque vous créez votre projet
2. Activer [l'API de Datastore](https://console.cloud.google.com/apis/api/datastore.googleapis.com/overview?) 
3. Configurez votre environnement local en installant la [librairie GCloud pour Java](https://cloud.google.com/sdk/)
    *  Spécifiez le projet que vous voulez utiliser : `gcloud config set project <PROJECT_NAME>`
4. Assurez-vous d'avoir Java 8 et Maven installés

## Lancer le projet localement
1. À la racine du projet, exécutez : `mvn clean package exec:java`
2. Dirigez-vous à l'adresse `localhost:8080` dans votre navigateur

## Déployer sur Google App Engine
Si vous avez activé la facturation lors de la configuration du projet, exécutez la commande suivante à la racine de votre projet : `mvn gcloud:deploy`

## Comment le projet fonctionne
### Datastore
Cet exemple utilise `Google Datastore` afin de persister et traiter les données transmises. Datastore est une base de donnée NoSQL de Google. Les attributs des entités sont tous indexés pour des requêtes plus rapides.

**Connection au Datastore**

Si on utilise la librairie GCloud : 
```
Datastore client = DatastoreOptions.defaultInstance().service();
```
Si on utilise Datastore à l'extérieur des environnements Google :
```
DatastoreOptions options = DatastoreOptions.builder()
        .projectId("<PROJECT_NAME>")
        .authCredentials(AuthCredentials.createApplicationDefaults()).build();
Datastore client = options.service();
```

**KeyFactory**

Dans nos classes DAOs, nous devons spécifier un `KeyFactory` qui représentera le type des entités gérées par notre DAO : 
```
KeyFactory keyFactory = datastore.newKeyFactory().kind(USER);
```

**Création d'entités**

Datastore utilise des `clés-valeurs` afin de représenter les données. Voici comment nous pouvons faire une insertion :
```
FullEntity<IncompleteKey> incUser = Entity.builder(this.keyFactory.newKey())
       .set("username", name)
       .set("email", email)
       .build();
Entity createdEntity = datastore.add(incUser);
```
Il est possible d'avoir la clé générée comme cela : 
```
//Key Object
createdEntity.key();
//Long id
createdEntity.key().id();
//Clé hashée url-safe
createdEntity.key().toUrlSafe();
```

**Modification**

Si nous faisons un `update` d'une entité, nous pouvons vérifier si l'entité existe comme cela :
```
Key key = this.keyFactory.newKey(id);
Entity entity = this.datastore.get(key);
if (entity == null) {
   throw new IllegalArgumentException("No user with id '" + id + "' found");
} else {
   //Update
}
```
Lors du update, on passe l'entité récupérée dans le `Builder` :
```
entity = Entity.builder(entity)
        .set("username", name)
        .set("email", email)
        .build();
datastore.update(entity);
```

**Suppression**

La suppression d'entités dans Datastore se fait comme cela : 
```
Key key = keyFactory.newKey(id);
datastore.delete(key);
```

**Requêtes**

Les requêtes dans Datastore se font comme cela :
```
Query<Entity> query = Query.entityQueryBuilder()
       .kind(DatastoreKind.USER)
       .build();
QueryResults<Entity> results = datastore.run(query);
```
Afin de récupérer les données, nous devons boucler sur le résultat : 
```
while (results.hasNext()) {
   Entity e = results.next();
   User u = new User();
   u.setEmail(e.getString("email"));
   u.setUsername(e.getString("username"));
   u.setId(e.key().id());
   users.add(u);
}
```

Nous pouvons aussi filtrer les données lors des requêtes avec des `PropertyFilter`:
```
Query<Entity> query = Query.entityQueryBuilder()
    .kind(TASK)
    .filter(StructuredQuery.PropertyFilter.eq("userId", this.userkeyFactory.newKey(userId)))
    .build();
```
L'exemple précédent démontre comment faire une requête sur une clé d'une entité.

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

### Injection de dépendences
Le projet utilise [Google Guice](https://github.com/google/guice) pour les injections de dépendances. Cette librairie permet de facilement utiliser une implémentation de classe différente en modifiant les bindings dans les classes qui injectent. Les classes servant à injecter des modules comme par exemple les DAOs héritent de `com.google.inject.AbstractModule`. On doit ensuite Override la méthode `void configure()` afin de binder nos implémentations à nos classes :
```
@Override
protected void configure() {
    //Bind the dao interface to the UserDao implementation in datastore package
    bind(IUserDao.class).to(UserDao.class);
    bind(ITaskDao.class).to(TaskDao.class);
}
```
Nous devons ensuite configurer notre injecteur comme cela :
```
Injector routingInjector = Guice.createInjector(new RoutingInjector());
```
Lorsque nous voulons injecter une classe dans une autre, on utilise le constructeur avec des interfaces comme paramètres et on y ajoute l'annotation `@Inject`.
