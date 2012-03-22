package fr.univartois.ili.sadoc.utils;

import java.util.List;
import java.util.Map;

import fr.univartois.ili.sadoc.client.webservice.ClientWebServiceImpl;
import fr.univartois.ili.sadoc.client.webservice.IClientWebService;
import fr.univartois.ili.sadoc.dao.AcquisitionDAO;
import fr.univartois.ili.sadoc.dao.CompetenceDAO;
import fr.univartois.ili.sadoc.dao.DocumentDAO;
import fr.univartois.ili.sadoc.dao.OwnerDAO;
import fr.univartois.ili.sadoc.entities.Acquisition;
import fr.univartois.ili.sadoc.entities.Competence;
import fr.univartois.ili.sadoc.entities.Document;
import fr.univartois.ili.sadoc.entities.Owner;

public class Synchronization {
	private DocumentDAO ddao;
	private AcquisitionDAO adao;
	private OwnerDAO odao;
	private CompetenceDAO cdao;

	public Synchronization() {
		ddao = new DocumentDAO();
		adao = new AcquisitionDAO();
		odao = new OwnerDAO();
		cdao = new CompetenceDAO();
	}

	public void SynchronizeWebAPPDatabase(String mail) {

		IClientWebService clientWebService = new ClientWebServiceImpl();

		Owner own = odao.findByMail(mail);

		List<fr.univartois.ili.sadoc.client.webservice.tools.Document> docwsList = clientWebService
				.getAllDocument(own);
		for (fr.univartois.ili.sadoc.client.webservice.tools.Document docws : docwsList) {
			if (docws != null) {
				Document doctoregister = ddao
						.findById(TestID.createFalseID(docws.getId().longValue()));
				if (doctoregister == null) {
					Byte[] fakearraytmp = docws.getPk7();
					byte[] fakearray = new byte[fakearraytmp.length];
					for (int i = 0; i < fakearraytmp.length; i++) {
						fakearray[i] = fakearraytmp[i];
					}
					doctoregister = new Document(docws.getName(),
							docws.getCheckSum(), "", fakearray, null);
					doctoregister.setId(TestID.createFalseID(docws.getId().longValue()));
					ddao.create(doctoregister);
				}
				Map<fr.univartois.ili.sadoc.client.webservice.tools.Owner, List<fr.univartois.ili.sadoc.client.webservice.tools.Competence>> comp = clientWebService
						.getCompetences(docws.getId().longValue());
				fr.univartois.ili.sadoc.client.webservice.tools.Owner incowner = comp
						.keySet().iterator().next();

				for (fr.univartois.ili.sadoc.client.webservice.tools.Competence competence : comp
						.get(incowner)) {
					Competence c = cdao.findById(competence.getId().intValue());
					if (c == null) {
						c = new Competence(competence.getName(),
								competence.getDescription());
						c.setId(competence.getId().intValue());
						cdao.create(c);
					}
					Acquisition a = new Acquisition();
					a.setCompetence(c);
					a.setDocument(doctoregister);
					a.setOwner(own);
					adao.create(a);
				}

			}
		}
	}

}
