package services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "order")
public class OrderBean {
	
	
	private String userName;
	private ProductOrderBean[] productOrder;
	
	public OrderBean() {
		
	}
	
	public OrderBean(String userName, ProductOrderBean[] productOrder) {
		setUserName(userName);
		setProductOrder(productOrder);
	}

	public String getUserName() {
		return userName;
	}
	
	@XmlElement
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ProductOrderBean[] getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrderBean[] productOrder) {
		this.productOrder = productOrder;
	}
	
	
}
