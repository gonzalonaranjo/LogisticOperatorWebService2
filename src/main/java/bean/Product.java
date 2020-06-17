package bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.PRODUCT)
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = LogisticOperatorConstants.PRODUCT_COLUMNS.ID)
	private Integer id;
	
	@Column(name = LogisticOperatorConstants.PRODUCT_COLUMNS.NAME)
	private String name;
	
	@OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<ProductOrder> productOrderList;
	
	public List<ProductOrder> getProductOrderList() {
		return productOrderList;
	}

	public void setProductOrderList(List<ProductOrder> productOrderList) {
		this.productOrderList = productOrderList;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
