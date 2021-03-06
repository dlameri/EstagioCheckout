package com.ideais.spring.domain.stock.json;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SubcategoryJSON  {

	private Long id;
	private String name;
	private Boolean active;
	private List<Link> links = new ArrayList<Link>();


	public String getURI(String name) {

		if (name != null) {
			for (Link link : links) {
				if (name.equals(link.getName())) {
					return link.getHref();
				}
			}
		}

		return null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

}