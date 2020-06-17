package bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.STATUS)
public class Status implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = LogisticOperatorConstants.STATUS_COLUMNS.ID)
	private Integer id;
	
	@Column(name = LogisticOperatorConstants.STATUS_COLUMNS.NAME)
	private String name;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
