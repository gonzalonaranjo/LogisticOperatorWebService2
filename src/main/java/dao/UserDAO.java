package dao;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bean.User;
import utils.HibernateUtil;

public class UserDAO {
	 
	public User checkLogin(String userName, String password) {
		try {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<User> query = session.createQuery("from User u inner join fetch u.userPassword us where u.userName = :userName " +
					"and us.userPassword = :password", User.class);
			query.setParameter("userName", userName);
			query.setParameter("password", password);
			
			User user = query.getSingleResult();
			
			session.getTransaction().commit();
			return user;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	public static User getUserbyUserName(String userName) {
		try {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<User> query = session.createQuery("from User u where u.userName = :userName ", User.class);
			query.setParameter("userName", userName);

			
			User user = query.getSingleResult();
			
			session.getTransaction().commit();
			return user;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
}
