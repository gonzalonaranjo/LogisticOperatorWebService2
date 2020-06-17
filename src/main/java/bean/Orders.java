package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.ORDERS)
public class Orders implements Serializable {
	
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = LogisticOperatorConstants.ORDERS_COLUMNS.ID)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = LogisticOperatorConstants.ORDERS_COLUMNS.USERNAME)
	private User user;
	
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogisticOperatorConstants.ORDERS_COLUMNS.STATUS)
	private Status status;
	
	@OneToMany(mappedBy = "order", fetch=FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	private List<ProductOrder> productOrderList;
	
	@OneToMany(mappedBy = "order")
	@Cascade(CascadeType.ALL)
	private List<OrderHistory> orderHistoryList;
	
	@Column(name = LogisticOperatorConstants.ORDERS_COLUMNS.CREATED_AT)
	private Date createdAt;
	
	@Column(name = LogisticOperatorConstants.ORDERS_COLUMNS.MODIFIED_AT)
	private Date modifiedAt;
	
	public Integer getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ProductOrder> getProductOrderList() {
		return productOrderList;
	}
	
	public void setProductOrderList(List<ProductOrder> productOrderList) {
		this.productOrderList = productOrderList;
	}

	public List<OrderHistory> getOrderHistoryList() {
		return orderHistoryList;
	}

	public void setOrderHistoryList(List<OrderHistory> orderHistoryList) {
		this.orderHistoryList = orderHistoryList;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
}
