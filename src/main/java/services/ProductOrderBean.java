package services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "productOrder")
public class ProductOrderBean {
	
	private String id;
	private String quantity;
	
	public ProductOrderBean() {
		
	}
	
	public ProductOrderBean(String id, String quantity) {
		setId(id);
		setQuantity(quantity);
	}
	
	public String getId() {
		return id;
	}

	public String getQuantity() {
		return quantity;
	}
	
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
}
