package edu.neu.CS5200.xslt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class SiteDao {
	
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("xslt-jpa");
	
	public Site findSite(int siteId)
	{
		Site site = new Site();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		site = em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
		return site;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Site> findAllSites() 
	{
		List<Site> sitelst = new ArrayList<Site>();
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createNamedQuery("findAllSites");
		sitelst = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sitelst;
		
	}
	
	public void exportSiteDatabaseToXmlFile(SiteList sitelist, String xmlFileName)
	{
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteList.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sitelist, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void convertXmlFileToOutputFile(String inputXmlFileName, String outputXmlFileName, String xsltFileName) 
	{
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);

		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);

		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args)
	{
		SiteDao dao = new SiteDao();
		
		Site site1 = new Site();
		int siteId = 1;
		site1 = dao.findSite(siteId);
		System.out.println("Site Id: " + siteId);
		System.out.println("Site Name: " + site1.getName());
		System.out.println("Site Latitude: " + site1.getLatitude());
		System.out.println("Site Longitude: " + site1.getLongitude());
		
		
		List<Site> sitelst = dao.findAllSites();
		for(Site site : sitelst)
		{
			System.out.println("Site Id: " + site.getId());
			System.out.println("Site Name: " + site.getName());
			System.out.println("Site Latitude: " + site.getLatitude());
			System.out.println("Site Longitude: " + site.getLongitude());
		}
		
		
		SiteList sites = new SiteList();
		sites.setSites(sitelst);
		
		String xmlFilename = "xml/sites.xml";
		dao.exportSiteDatabaseToXmlFile(sites, xmlFilename);
		
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
		
	}
}
