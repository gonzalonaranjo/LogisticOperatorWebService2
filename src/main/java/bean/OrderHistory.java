package bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import constants.LogisticOperatorConstants;

@Entity
@Table(name = LogisticOperatorConstants.TABLE_NAMES.ORDER_HISTORY)
public class OrderHistory implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogisticOperatorConstants.ORDER_HISTORY_COLUMNS.ID)
	private Orders order;
	
	@Id
	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = LogisticOperatorConstants.ORDER_HISTORY_COLUMNS.STATUS)
	private Status status;
	
	@Id
	@Column(name = LogisticOperatorConstants.ORDER_HISTORY_COLUMNS.DATE)
	private Date date;

	public Orders getOrder() {
		return order;
	}

	public Status getStatus() {
		return status;
	}

	public Date getDate() {
		return date;
	}

	public void setOrders(Orders order) {
		this.order = order;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
