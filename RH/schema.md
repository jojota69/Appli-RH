=== Bloc 1 ===

employee(<b>id_emp</b>,nom,prenom,email,#id_role,#id_dept)

role(<b>id_role</b>,libelle)

departement(<b>id_dept</b>,libelle)

poste(<b>id_poste</b>,libelle)

typeDemande(<b>id_type</b>,libelle)
(na io creation d un poste na remplacement)
raha remplacement de meme poste fa le olona teo aloha no ovaina

etatDemande(<b>id_etat</b>,libelle) 
(na valide,rejete,en cours de traitement)

demandeBesoin(<b>id_besoin</b>,#id_poste, #id_dept,#id_manager, effectif,motif, budget, date souhaitee,#id_etat,#id_type)

=== Bloc 2 === 

etatOffre(<b>id_etat</b>,libelle) 
(na en cours,terminée,en attente de validation)

offreEmploie(<b>id_offre</b>,#id_etat,#id_poste)

candidat(<b>id_cdt</b>,nom,prenom)

etatCandidature(<b>id_etat</b>,libelle) (na io retenue , en attente , rejete)

candidature(<b>id_candidature</b>,#id_cdt,#id_offre,#id_etat)  
(le id_offre an le offreEmploie mba mahamora ny gestion cote metier)

entretient(<b>id_ent</b>,date_entretient,#id_emp,#id_cdt)  
(le id_emp le id an le nampanao entretient sode ilaina mankony io)

evaluation(<b>id_eval</b>,#id_cdt,note)

=== Bloc 3 ===

etatembauche(<b>id_etat</b>,libelle) (na io accepte,refuse,en cours de traitement)

embauche(<b>id_emb</b>,#id_cdt,#id_etat,salaire_prop,#id_poste,#id_etat)

typeContrat(<b>id_type</b>,libelle,duree,nbr_renouv)
(le nbr_renouv 1 raha CE si CDD , null raha CDI)

=== Bloc 4 === 

contrat(<b>id_contrat</b>,#id_emp,#id_type,nbr_renouv,salaire) 

(le nbr_renouv io afantarana ny renouvellement an le contrat natao tamin ilay emp , ovaina any amin metier rehefe renouvellena le izy )

paie(#id_employe, mois, annee, salaire_base, primes, heures_supplementaires, retenues, cotisations_patronales, salaire_brut, salaire_net, date_paiement, statut)