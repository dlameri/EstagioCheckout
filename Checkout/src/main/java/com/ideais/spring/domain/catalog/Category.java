package com.ideais.spring.domain.catalog;

import java.util.List;
import com.ideais.spring.domain.stock.json.CategoryJSON;

public class Category {
	private Long id;
	private String name;
	private List<Subcategory> subcategories;
	private Boolean active;
	
	public Category(CategoryJSON categoryJSON) {
		if (categoryJSON != null) {
			this.id = categoryJSON.getId();
			this.name = categoryJSON.getName();
			this.active = categoryJSON.getActive();
		}
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Subcategory> getSubcategories() {
		return subcategories;
	}

	public void setSubcategories(List<Subcategory> subcategories) {
		this.subcategories = subcategories;
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

	@Override
	public String toString() {
		return name;
	}
	
	public String toJson() {
		String json = "{" 
						+"\"id\": " + this.id + ","
						+"\"name\": " + "\"" + this.name + "\","
						+ "\"subcategories\": [";
						
		if(hasSubcategory()) {
			for (int i = 0; i < this.subcategories.size() ; i++) {
				Subcategory subcategory = subcategories.get(i);
				json = json.concat( subcategory.toJson() );
				if( i < subcategories.size()-1 ){
					json = json.concat(",");
				}
			}
		}
		return json.concat("]}");
	}
	
	public boolean hasSubcategory() {
		return this.subcategories != null && !this.subcategories.isEmpty();
	}
}
