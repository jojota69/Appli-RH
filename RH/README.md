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

Les clés primaires du script `src/main/resources/schema.sql` utilisent désormais `GENERATED ALWAYS AS IDENTITY` à la place de `SERIAL`.

La section `listeDemandes` affiche maintenant les demandes reçues avec le poste, le manager, l'effectif, le budget, la date souhaitée et l'état. Les associations nécessaires sont chargées avec la liste pour permettre leur rendu dans Thymeleaf.

Les postes affichent désormais les initiales de chaque mot dans leur icône, par exemple `Administrateur Reseau` devient `AR`.

Les icônes de `listePoste` affichent les initiales du prénom et du nom de l'employé, tandis que les icônes de `listeDemandes` affichent les initiales de chaque mot de l'intitulé du poste, par exemple `Developpeur Junior` devient `DJ`.

Les sections `modifDemande` et `voirDemande` permettent respectivement de modifier et de consulter une demande sélectionnée par son identifiant.

La vue détail utilise désormais `demandeSelectionnee.id` pour accéder à la modification, car la variable `liste` n'existe que dans la boucle d'affichage des demandes.

Les demandes reçues disposent maintenant d'icônes SVG pour la modification et la consultation.

La section `voirPoste` récupère la fiche de l'employé sélectionné avec le paramètre `selectedEmployeeId` et affiche le détail du poste : le nombre de personnes occupant ce poste dans le département, le budget demandé pour ce poste et le budget total demandé pour le département, ainsi que le département et le type de contrat. Les associations de la fiche sont chargées avec l'employé pour permettre leur rendu dans Thymeleaf. Cette section est également déclarée dans le routage Thymeleaf de `home.html`.

Dans la liste des employés, le nom et le prénom sont affichés côte à côte grâce à la mise en page flexible de `.poste-details`, avec retour à la ligne possible lorsque l'espace disponible est insuffisant.

La liste des employés affiche également le nom du poste associé à chaque fiche employé.

La section `remplacerPoste` reprend le formulaire de création de poste et préremplit l'intitulé, le département, le type de contrat, le budget et le motif à partir de la fiche de l'employé sélectionné. La demande créée est enregistrée avec le type `Remplacement`.

Les données de démonstration contiennent également le poste `Ingenieur reseau` et l'employé `Julien Moreau`, rattachés au département `Informatique` dans `employee_fiche`.