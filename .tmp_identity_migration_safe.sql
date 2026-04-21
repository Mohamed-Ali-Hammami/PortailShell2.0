-- Auto-generated safe PK/auto_increment migration for TSV-imported schema
SET sql_safe_updates = 0;

-- achat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM achat);
UPDATE achat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE achat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- achatcaisse.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM achatcaisse);
UPDATE achatcaisse SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE achatcaisse MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- achatcarburant.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM achatcarburant);
UPDATE achatcarburant SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE achatcarburant MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- affectationfiltre.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM affectationfiltre);
UPDATE affectationfiltre SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE affectationfiltre MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- affectationhuile.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM affectationhuile);
UPDATE affectationhuile SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE affectationhuile MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- annee.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM annee);
UPDATE annee SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE annee MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- articlecarburant.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM articlecarburant);
UPDATE articlecarburant SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE articlecarburant MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- avance.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM avance);
UPDATE avance SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE avance MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- avancegestat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM avancegestat);
UPDATE avancegestat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE avancegestat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- avoir.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM avoir);
UPDATE avoir SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE avoir MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- avoirbancaire.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM avoirbancaire);
UPDATE avoirbancaire SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE avoirbancaire MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- banque.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM banque);
UPDATE banque SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE banque MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- bonlivraison.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM bonlivraison);
UPDATE bonlivraison SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE bonlivraison MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- caisse.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM caisse);
UPDATE caisse SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE caisse MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- carteclient.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM carteclient);
UPDATE carteclient SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE carteclient MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- cat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM cat);
UPDATE cat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE cat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- categorie.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM categorie);
UPDATE categorie SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE categorie MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- chauffeur.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM chauffeur);
UPDATE chauffeur SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE chauffeur MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- chefpiste.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM chefpiste);
UPDATE chefpiste SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE chefpiste MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- cheque.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM cheque);
UPDATE cheque SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE cheque MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- chequereglement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM chequereglement);
UPDATE chequereglement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE chequereglement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- client.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM client);
UPDATE client SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE client MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- clientgestat.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM clientgestat);
UPDATE clientgestat SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE clientgestat MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- clientpassation.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM clientpassation);
UPDATE clientpassation SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE clientpassation MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- clientvidange.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM clientvidange);
UPDATE clientvidange SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE clientvidange MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- compte.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM compte);
UPDATE compte SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE compte MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- creditanterieur.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM creditanterieur);
UPDATE creditanterieur SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE creditanterieur MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- creditclient.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM creditclient);
UPDATE creditclient SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE creditclient MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- creditpassation.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM creditpassation);
UPDATE creditpassation SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE creditpassation MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- degree.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM degree);
UPDATE degree SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE degree MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- depense.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM depense);
UPDATE depense SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE depense MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- depense_cheque.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM depense_cheque);
UPDATE depense_cheque SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE depense_cheque MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- depensegestat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM depensegestat);
UPDATE depensegestat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE depensegestat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- etatcheque.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM etatcheque);
UPDATE etatcheque SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE etatcheque MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- facture.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM facture);
UPDATE facture SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE facture MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- factureachat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM factureachat);
UPDATE factureachat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE factureachat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- factureachatcarburant.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM factureachatcarburant);
UPDATE factureachatcarburant SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE factureachatcarburant MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- famillearticle.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM famillearticle);
UPDATE famillearticle SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE famillearticle MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- familleclientpassation.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM familleclientpassation);
UPDATE familleclientpassation SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE familleclientpassation MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- familledepensegestat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM familledepensegestat);
UPDATE familledepensegestat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE familledepensegestat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- familledepensetransport.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM familledepensetransport);
UPDATE familledepensetransport SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE familledepensetransport MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- fournisseur.code
SET @mx := (SELECT COALESCE(MAX(CAST(code AS UNSIGNED)), 0) FROM fournisseur);
UPDATE fournisseur SET code = (@mx := @mx + 1) WHERE code IS NULL OR TRIM(code) = '';
ALTER TABLE fournisseur MODIFY code BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (code);

-- gestion.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM gestion);
UPDATE gestion SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE gestion MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- gestionrappel.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM gestionrappel);
UPDATE gestionrappel SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE gestionrappel MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- historiquepayement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM historiquepayement);
UPDATE historiquepayement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE historiquepayement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- imagebl.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM imagebl);
UPDATE imagebl SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE imagebl MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- imageemployee.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM imageemployee);
UPDATE imageemployee SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE imageemployee MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- imagelavage.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM imagelavage);
UPDATE imagelavage SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE imagelavage MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- imagevetement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM imagevetement);
UPDATE imagevetement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE imagevetement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- indexdeuxt.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM indexdeuxt);
UPDATE indexdeuxt SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE indexdeuxt MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- journal.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM journal);
UPDATE journal SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE journal MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- ligne_imagebl.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM ligne_imagebl);
UPDATE ligne_imagebl SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE ligne_imagebl MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignealimentation.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignealimentation);
UPDATE lignealimentation SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignealimentation MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignealimentationcar.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignealimentationcar);
UPDATE lignealimentationcar SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignealimentationcar MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignecommande.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignecommande);
UPDATE lignecommande SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignecommande MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignecommandepass.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignecommandepass);
UPDATE lignecommandepass SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignecommandepass MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignegestion.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignegestion);
UPDATE lignegestion SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignegestion MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- ligneimageemployee.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM ligneimageemployee);
UPDATE ligneimageemployee SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE ligneimageemployee MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- ligneimagerendement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM ligneimagerendement);
UPDATE ligneimagerendement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE ligneimagerendement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- ligneindex.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM ligneindex);
UPDATE ligneindex SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE ligneindex MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignepaiegestion.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignepaiegestion);
UPDATE lignepaiegestion SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignepaiegestion MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignepaiegestionprime.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignepaiegestionprime);
UPDATE lignepaiegestionprime SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignepaiegestionprime MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignepaiegestionrappel.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignepaiegestionrappel);
UPDATE lignepaiegestionrappel SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignepaiegestionrappel MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignetransert.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignetransert);
UPDATE lignetransert SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignetransert MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignevente.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignevente);
UPDATE lignevente SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignevente MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- lignevidangeproduit.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM lignevidangeproduit);
UPDATE lignevidangeproduit SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE lignevidangeproduit MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- marque.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM marque);
UPDATE marque SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE marque MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- model.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM model);
UPDATE model SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE model MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- note.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM note);
UPDATE note SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE note MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- paie.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM paie);
UPDATE paie SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE paie MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- paieconge.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM paieconge);
UPDATE paieconge SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE paieconge MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- paieprime.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM paieprime);
UPDATE paieprime SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE paieprime MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- paramettre.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM paramettre);
UPDATE paramettre SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE paramettre MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- pointage.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM pointage);
UPDATE pointage SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE pointage MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- pointageconge.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM pointageconge);
UPDATE pointageconge SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE pointageconge MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- pompe.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM pompe);
UPDATE pompe SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE pompe MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- pompiste.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM pompiste);
UPDATE pompiste SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE pompiste MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- produit.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM produit);
UPDATE produit SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE produit MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- produits.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM produits);
UPDATE produits SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE produits MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- rappel.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM rappel);
UPDATE rappel SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE rappel MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- rendement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM rendement);
UPDATE rendement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE rendement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- retour.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM retour);
UPDATE retour SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE retour MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- retourcuve.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM retourcuve);
UPDATE retourcuve SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE retourcuve MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- role.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM role);
UPDATE role SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE role MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- societe.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM societe);
UPDATE societe SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE societe MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- soldetpe.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM soldetpe);
UPDATE soldetpe SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE soldetpe MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- ticket.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM ticket);
UPDATE ticket SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE ticket MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- tpeshell.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM tpeshell);
UPDATE tpeshell SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE tpeshell MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- tracegestat.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM tracegestat);
UPDATE tracegestat SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE tracegestat MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- tracepaie.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM tracepaie);
UPDATE tracepaie SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE tracepaie MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- traceshop.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM traceshop);
UPDATE traceshop SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE traceshop MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- tracetransport.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM tracetransport);
UPDATE tracetransport SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE tracetransport MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- transaction.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM transaction);
UPDATE transaction SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE transaction MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- transactiondepense.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM transactiondepense);
UPDATE transactiondepense SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE transactiondepense MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- transactionrecdit.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM transactionrecdit);
UPDATE transactionrecdit SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE transactionrecdit MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- transfert.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM transfert);
UPDATE transfert SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE transfert MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- tva.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM tva);
UPDATE tva SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE tva MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- vente.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM vente);
UPDATE vente SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE vente MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- vetement.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM vetement);
UPDATE vetement SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE vetement MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);

-- vhecule.id
SET @mx := (SELECT COALESCE(MAX(CAST(id AS UNSIGNED)), 0) FROM vhecule);
UPDATE vhecule SET id = (@mx := @mx + 1) WHERE id IS NULL OR TRIM(id) = '';
ALTER TABLE vhecule MODIFY id BIGINT NOT NULL AUTO_INCREMENT, ADD PRIMARY KEY (id);
