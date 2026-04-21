package com.tn.shell.ui.gestat;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;

public class GestatViewInitBean {

	private static final Logger LOG = Logger.getLogger(GestatViewInitBean.class.getName());
	private static final Map<String, String[]> VIEW_ACTIONS = buildViewActions();

	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		if (context == null || context.isPostback()) {
			return;
		}
		String view = context.getExternalContext().getRequestParameterMap().get("view");
		if (view == null || view.trim().isEmpty()) {
			LOG.info("GestatViewInitBean skipped: no view parameter");
			return;
		}
		LOG.log(Level.INFO, "GestatViewInitBean init view={0}", view);

		String[] expressions = VIEW_ACTIONS.get(view);
		if (expressions == null) {
			expressions = VIEW_ACTIONS.get(view.toLowerCase(Locale.ROOT));
		}
		if (expressions == null || expressions.length == 0) {
			LOG.log(Level.INFO, "GestatViewInitBean no mapping for view={0}", view);
			return;
		}

		for (String expression : expressions) {
			invoke(context, view, expression);
		}
	}

	private void invoke(FacesContext context, String view, String expression) {
		if (expression == null || expression.trim().isEmpty()) {
			return;
		}

		try {
			LOG.log(Level.INFO, "GestatViewInitBean invoking view={0} expression={1}", new Object[] { view, expression });
			MethodExpression methodExpression = context.getApplication().getExpressionFactory()
					.createMethodExpression(context.getELContext(), expression, Object.class, new Class<?>[0]);
			methodExpression.invoke(context.getELContext(), null);
			LOG.log(Level.INFO, "GestatViewInitBean initialized view={0} expression={1}",
					new Object[] { view, expression });
		} catch (Exception ex) {
			LOG.log(Level.WARNING, "GestatViewInitBean failed for view=" + view + " expression=" + expression, ex);
		}
	}

	private static Map<String, String[]> buildViewActions() {
		Map<String, String[]> map = new LinkedHashMap<String, String[]>();

		map.put("AjouterFournisseur",
				new String[] { "#{FournisseursBean.getAllfournisseur}", "#{FournisseurBean.getAllfournisseur}" });
		map.put("achat", new String[] { "#{AchatcarburantBean.nouveauachat}" });
		map.put("ajouterclient", new String[] { "#{ClientgestatBean.nouvauclient}" });
		map.put("ajouterdepense", new String[] { "#{FamilledepenseBean.nouvaudepense}" });
		map.put("ajouterDepenseCheque", new String[] { "#{DepenseChequeBean.getDepenseCheque}" });
		map.put("ajouterfamilledepense", new String[] { "#{FamilledepenseBean.nouvaufamilledepense}" });
		map.put("avantcaisse", new String[] { "#{CaisseBeans.avantCaisse}" });
		map.put("caisse", new String[] { "#{CaisseBeans.caisse}" });
		map.put("cahiercredit", new String[] { "#{ClientgestatBean.cahiercredit}" });
		map.put("cahierstock", new String[] { "#{ProduitgestatBean.cahierstock}" });
		map.put("cahierouvrier", new String[] { "#{EmployeeBean.cahieremployee}" });
		map.put("cahierLitrage", new String[] { "#{SituationBean.cahierlitrage}" });
		map.put("cahierLitrageAvecRetour", new String[] { "#{SituationBean.cahierlitrageAvecRetour}" });
		map.put("carteClient", new String[] { "#{CarteClientBean.getcarteClient}" });
		map.put("creditfuelpose", new String[] { "#{CrediClientBean.saisiecredit}" });
		map.put("depensefuelpose", new String[] { "#{CrediClientBean.saisieDepense}" });
		map.put("etatachatesp", new String[] { "#{EtatBean.etatAchat}" });
		map.put("etatAvance", new String[] { "#{AvancegestatBean.etatAvance}" });
		map.put("etatcartebancaires", new String[] { "#{AvoirBean.etatcartebancaires}" });
		map.put("etatcarteshell", new String[] { "#{AvoirBean.etatcarteshell}" });
		map.put("etatdebanque", new String[] { "#{EtatBean.etatdebanque}" });
		map.put("etatDepenses", new String[] { "#{EtatBean.getetatDepenses}" });
		map.put("etatdeprofil", new String[] { "#{EtatBean.etatdeprofil}" });
		map.put("etatdeprofilcomtable", new String[] { "#{EtatBean.etatdeprofil2}" });
		map.put("etatdeschequeparjour", new String[] { "#{ChequeBean.getchequeparjour}" });
		map.put("etatdescheques", new String[] { "#{EtatBean.etatCheques}" });
		map.put("EtatDesFactureAchat2", new String[] { "#{AchatcarburantBean.etatFactureachat}" });
		map.put("etatFactureachat", new String[] { "#{AchatcarburantBean.etatFactureachat}" });
		map.put("etatFiscal", new String[] { "#{EtatBean.etatFiscal}" });
		map.put("etatfiscal", new String[] { "#{EtatBean.etatFiscal}" });
		map.put("facturationClient", new String[] { "#{ClientgestatBean.facturation}" });
		map.put("ficheregulation", new String[] { "#{ArticleBean.regulationinventaire}" });
		map.put("gestion_des_avances", new String[] { "#{AvancegestatBean.getAllavance}" });
		map.put("gestioncreditPassation", new String[] { "#{CreditPassationBean.accesPassation}" });
		map.put("historiqueCarburant", new String[] { "#{AchatcarburantBean.historiqueCarburant}" });
		map.put("historiqueParfournisseur", new String[] { "#{AchatcarburantBean.historiqueParfournisseur}" });
		map.put("HistoriquecarteClient", new String[] { "#{CarteClientBean.historiquecarteClient}" });
		map.put("impressioncaisse", new String[] { "#{SituationBean.impressionCaisse}" });
		map.put("inventairecarburant", new String[] { "#{ArticleBean.inventairecarburant}" });
		map.put("listeclient", new String[] { "#{ClientgestatBean.getclient}" });
		map.put("listedepense", new String[] { "#{FamilledepenseBean.getdepense}" });
		map.put("listemplyee", new String[] { "#{EmployeeBean.getAllEmployee}" });
		map.put("listetypedep", new String[] { "#{FamilledepenseBean.getfamilledepense}" });
		map.put("listfactureachatbyfour", new String[] { "#{AchatcarburantBean.getAllfournisseur}" });
		map.put("MAJpompe", new String[] { "#{ProduitgestatBean.miseAjourpompe}" });
		map.put("miseajourindex", new String[] { "#{CaisseBeans.miseajourindex}" });
		map.put("Modificationcaisse", new String[] { "#{CaisseBeans.avantCaisse}" });
		map.put("nouveauEmployee", new String[] { "#{EmployeeBean.nouvemployee}" });
		map.put("nouvelleAvance", new String[] { "#{AvancegestatBean.nouvelleAvance}" });
		map.put("rapportpiste", new String[] { "#{Rapportgestat.rapportgestat}" });
		map.put("rechercheCheque", new String[] { "#{ChequeBean.findCheque}" });
		map.put("reglementFactureAchat", new String[] { "#{AchatcarburantBean.reglementFacture}" });
		map.put("regulationinventairecarburant", new String[] { "#{ArticleBean.regulationinventaire}" });
		map.put("retenue", new String[] { "#{CerteficatBean.certif}" });
		map.put("saiseavoir", new String[] { "#{AvoirBean.saisieAvoir}" });
		map.put("saiseavoirbancaire", new String[] { "#{AvoirBean.saisieAvoirbanue}" });
		map.put("situationJournalieres", new String[] { "#{SituationBean.situationJournaliere}" });
		map.put("verfificateurcahierStock", new String[] { "#{ProduitgestatBean.verfificateurcahierStock}" });

		return Collections.unmodifiableMap(map);
	}
}
