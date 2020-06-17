package bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import constants.LogisticOperatorConstants;
		
@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.USERS)
public class User implements Serializable {
		
	public static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = LogisticOperatorConstants.USERS_COLUMNS.USERNAME, updatable = false, nullable = false)
	private String userName;
	
	@Column(name = LogisticOperatorConstants.USERS_COLUMNS.NAME)
	private String name;
	
	@OneToOne(mappedBy = "user")
	private UserPassword userPassword;
	
	@OneToMany(mappedBy = "user")
	private List<Orders> orderList;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public UserPassword getUserPassword() {
		return userPassword;
	}
	
	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}
	
	public List<Orders> getOrderList() {
		return orderList;
	}
	
	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
}

