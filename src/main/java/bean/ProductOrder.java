package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.PRODUCT_ORDER)
public class ProductOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne
    @JoinColumn(name = LogisticOperatorConstants.PRODUCT_ORDER_COLUMNS.ORDERID)
	private Orders order;
	
	@Id
	@ManyToOne
    @JoinColumn(name = LogisticOperatorConstants.PRODUCT_ORDER_COLUMNS.PRODUCTID)
	private Product product;
	
	@Column(name = LogisticOperatorConstants.PRODUCT_ORDER_COLUMNS.QUANTITY)
	private int quantity;

	public Orders getOrder() {
		return order;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
