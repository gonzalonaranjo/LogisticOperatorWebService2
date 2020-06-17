package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.USER_PASSWORD)
public class UserPassword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = LogisticOperatorConstants.USER_PASSWORD_COLUMNS.USERNAME)
	private User user;
	
	@Column(name = LogisticOperatorConstants.USER_PASSWORD_COLUMNS.PASSWORD)
	private String userPassword;

	public User getUser() {
		return user;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
