package edu.neu.CS5200.xslt;

import java.util.List;

import javax.xml.bind.annotation.*;  

@XmlRootElement
@XmlAccessorType(value=XmlAccessType.FIELD)
public class SiteList {

	@XmlElement(name="site")
	private List<Site> sites;

	public SiteList() {
		super();
	}
	
	 
	public List<Site> getSites() {
		return this.sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
   
}

