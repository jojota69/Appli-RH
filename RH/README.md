# Appli RH

## Interface principale

La page `/home` utilise un layout unique : le header et la barre de navigation restent fixes pendant que la zone centrale change selon le choix de navigation.

Les vues disponibles sont accessibles avec le paramètre `section` : `dashboard`, `employees`, `recruitment`, `contracts` et `onboarding`. Le contrôleur ramène toute valeur inconnue vers le tableau de bord.

La feuille de style de l'écran est située dans `src/main/resources/static/css/home.css`.

L'écran de connexion (`/`) réutilise cette même feuille de style afin de conserver le header, les couleurs, la typographie et les composants visuels de l'application.

Le formulaire de connexion transmet les champs `email` et `motDePasse`, conformément à `LoginForm`. Le script `data.sql` contient une instruction neutre afin que l'initialisation SQL Spring Boot fonctionne même sans données de démonstration.

Le formulaire de connexion utilise les propriétés `email` et `motDePasse` définies dans `LoginForm`.

La liste des postes utilise les classes `.postes` pour le conteneur et `.poste` pour chaque carte. Le conteneur contient maintenant des éléments visibles afin que les styles soient effectivement perceptibles.

Dans `home.html`, le rôle est sélectionné par un `th:switch` externe, puis la section est sélectionnée dans le bloc du rôle Manager. Les `th:case` sont ainsi des branches directes de leur `th:switch` respectif.

Les conditions de rôle utilisent désormais `#strings.equalsIgnoreCase` afin d'accepter les différences de casse entre la valeur en base et le template. Après une modification du chargement du rôle, il faut se déconnecter puis se reconnecter pour renouveler la session.

Dans `home.html`, le cas Thymeleaf `listePostes` doit être placé avant le cas générique `*`, sinon ce dernier intercepte toutes les sections et masque la page de liste des postes.

La recherche de l'employé connecté charge également son rôle avec `JOIN FETCH`, afin que Thymeleaf puisse lire `employeeConnecte.role.libelle` après la fermeture de la session Hibernate.

La liste des postes possède maintenant un bouton « Créer un poste » qui ouvre la section `creerPoste` avec un formulaire dédié.