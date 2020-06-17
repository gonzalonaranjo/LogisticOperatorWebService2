package dao;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import bean.Status;
import utils.HibernateUtil;

public class StatusDAO {
	
	/**
	 * Devuelve un objeto estado a partir de su codigo de estado
	 * @param statusCode Integer
	 * @return Status
	 */
	public static Status getStatusByCode(Integer statusCode) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Status> query = session.createQuery("Select s from Status s where s.id = :id", Status.class);
			query.setParameter("id", statusCode);
			
			Status actualStatus = query.getSingleResult();
			
			session.getTransaction().commit();
			
			return actualStatus;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
		
	}
}
