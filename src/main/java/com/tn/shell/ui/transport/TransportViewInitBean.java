package com.tn.shell.ui.transport;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

public class TransportViewInitBean {

	private static final Logger LOG = Logger.getLogger(TransportViewInitBean.class.getName());

	public void init() {
		FacesContext context = FacesContext.getCurrentInstance();
		String view = context.getExternalContext().getRequestParameterMap().get("view");
		if (view == null || view.trim().isEmpty()) {
			return;
		}
		try {
			ClientBean clientBean = resolve(context, ClientBean.class, "#{ClientBean}", "#{clientBean}");
			ChauffeurBean chauffeurBean = resolve(context, ChauffeurBean.class, "#{ChauffeurBean}", "#{chauffeurBean}");
			VheculeBean vheculeBean = resolve(context, VheculeBean.class, "#{VheculeBean}", "#{vheculeBean}");
			ProduitBean produitBean = resolve(context, ProduitBean.class, "#{ProduitBean}", "#{produitBean}");
			FamilledepensetransportBean familledepensetransportBean = resolve(context, FamilledepensetransportBean.class,
					"#{FamilledepensetransportBean}", "#{familledepensetransportBean}");
			boolean requiresFactureBean = "facturetransports".equals(view) || "facturepassagers".equals(view)
					|| "listefacture".equals(view) || "bondelivraisonnonfacturee".equals(view);
			FactureBean factureBean = requiresFactureBean
					? resolve(context, FactureBean.class, "#{FactureBean}", "#{factureBean}")
					: null;
			LOG.log(Level.INFO,
					"TransportViewInitBean resolve view={0} clientBean={1} chauffeurBean={2} vheculeBean={3} produitBean={4} familleBean={5} factureBean={6}",
					new Object[] { view, clientBean != null, chauffeurBean != null, vheculeBean != null, produitBean != null,
							familledepensetransportBean != null, factureBean != null });

			switch (view) {
			case "listeclient":
				if (clientBean != null) {
					clientBean.getclient();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listeclient size={0}",
							clientBean.getListclient() == null ? 0 : clientBean.getListclient().size());
				}
				break;
			case "listechauffeur":
				if (chauffeurBean != null) {
					chauffeurBean.getchauffeur();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listechauffeur size={0}",
							chauffeurBean.getListchauffeur() == null ? 0 : chauffeurBean.getListchauffeur().size());
				}
				break;
			case "listevhecule":
				if (vheculeBean != null) {
					vheculeBean.getvhecule();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listevhecule size={0}",
							vheculeBean.getListvhecule() == null ? 0 : vheculeBean.getListvhecule().size());
				}
				break;
			case "listeproduit":
				if (produitBean != null) {
					produitBean.getallProduit();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listeproduit size={0}",
							produitBean.getListprosuits() == null ? 0 : produitBean.getListprosuits().size());
				}
				break;
			case "listetypedep":
				if (familledepensetransportBean != null) {
					familledepensetransportBean.getfamilledepense();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listetypedep size={0}",
							familledepensetransportBean.getListfamilledepense() == null ? 0
									: familledepensetransportBean.getListfamilledepense().size());
				}
				break;
			case "listedepense":
				if (familledepensetransportBean != null) {
					familledepensetransportBean.getdepense();
					LOG.log(Level.INFO, "TransportViewInitBean loaded listedepense size={0}",
							familledepensetransportBean.getListdepense() == null ? 0
									: familledepensetransportBean.getListdepense().size());
				}
				break;
			case "facturetransports":
				if (factureBean != null) {
					factureBean.getAllTransport();
					LOG.log(Level.INFO, "TransportViewInitBean loaded facturetransports size={0}",
							factureBean.getListfacture() == null ? 0 : factureBean.getListfacture().size());
				}
				break;
			case "facturepassagers":
				if (factureBean != null) {
					factureBean.getAllPasager();
					LOG.log(Level.INFO, "TransportViewInitBean loaded facturepassagers size={0}",
							factureBean.getListfacturepass() == null ? 0 : factureBean.getListfacturepass().size());
				}
				break;
			case "listefacture":
				if (factureBean != null) {
					factureBean.listFacture();
				}
				break;
			case "bondelivraisonnonfacturee":
				if (factureBean != null) {
					factureBean.listbls();
				}
				break;
			default:
				break;
			}
			LOG.log(Level.INFO, "TransportViewInitBean initialized view={0}", view);
		} catch (Exception ex) {
			LOG.log(Level.SEVERE, "TransportViewInitBean failed for view=" + view, ex);
		}
	}

	private <T> T resolve(FacesContext context, Class<T> type, String... expressions) {
		Application application = context.getApplication();
		for (String expression : expressions) {
			T bean = application.evaluateExpressionGet(context, expression, type);
			if (bean != null) {
				return bean;
			}
		}
		return null;
	}
}
