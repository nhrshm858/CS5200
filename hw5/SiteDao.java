package edu.neu.cs5200.JPA.JWS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.JPA.models.Site;
import edu.neu.cs5200.JPA.models.Sites;


//@Path("/api/site")
@Path("/site")
public class SiteDao {
	EntityManagerFactory factory= Persistence.createEntityManagerFactory("jpa");
	EntityManager em = null;
	
	//@Path("/api/site/{id}")
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Site site = em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
		
		return site;
		
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites()
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createNamedQuery("findAllSites");
		
		List<Site> sites = new ArrayList<Site>();
		sites = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
		
		
	}
	
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site.setId(siteId);
		em.merge(site);
		
		Query q = em.createNamedQuery("findAllSites");
		
		List<Site> sites = new ArrayList<Site>();
		sites = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
		
	}
	
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> deleteSite(@PathParam ("id") int siteId)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Site site = em.find(Site.class, siteId);
		em.remove(site);
		
        Query q = em.createNamedQuery("findAllSites");
		
		List<Site> sites = new ArrayList<Site>();
		sites = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(site);
		
        Query q = em.createNamedQuery("findAllSites");
		
		List<Site> sites = new ArrayList<Site>();
		sites = q.getResultList();
		
		em.getTransaction().commit();
		em.close();
		
		return sites;
		
		
	}
	
	
	

	public static void main(String[] args) {
		
		SiteDao sdao = new SiteDao();
		
		Site s = new Site();
		s.setName("Boston");
		sdao.createSite(s);
		
		Site si = new Site();
		si.setName("Chicago");
		sdao.updateSite(4, si);
		
		sdao.deleteSite(6);
		
		Site s1 = sdao.findSite(2);
		
		List<Site> sitelist = sdao.findAllSites();
		for(Site se:sitelist) {
			System.out.println(se.getName());
		}
		
		Sites theSites = new Sites();
		theSites.setSites(sitelist);
		
		
		
		}

}
