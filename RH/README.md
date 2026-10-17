# Appli RH

## Interface principale

La page `/home` utilise un layout unique : le header et la barre de navigation restent fixes pendant que la zone centrale change selon le choix de navigation.

Les vues disponibles sont accessibles avec le paramètre `section` : `dashboard`, `employees`, `recruitment`, `contracts` et `onboarding`. Le contrôleur ramène toute valeur inconnue vers le tableau de bord.

La feuille de style de l'écran est située dans `src/main/resources/static/css/home.css`.