package services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class ProductBean {
	
	private String id;
	private String name;
	
	public ProductBean() {
		
	}
	
	public ProductBean(String id, String name) {
		setId(id);
		setName(name);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}	
	
	
}
