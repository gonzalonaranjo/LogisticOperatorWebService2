package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateUtil {
	private static SessionFactory sessionFactory;

	private static ServiceRegistry serviceRegistry;
	
	private HibernateUtil() {}
	
	public static synchronized void buildSessionFactory(){

		try {
			if(ValidationUtils.isNull(sessionFactory)) {
				
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(bean.OrderHistory.class);
				configuration.addAnnotatedClass(bean.Orders.class);
				configuration.addAnnotatedClass(bean.Product.class);
				configuration.addAnnotatedClass(bean.ProductOrder.class);
				configuration.addAnnotatedClass(bean.Status.class);
				configuration.addAnnotatedClass(bean.User.class);
				configuration.addAnnotatedClass(bean.UserPassword.class);
				configuration.addResource("hibernate.cfg.xml");
				
				serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			}
			
		} catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed" + th);
			throw new ExceptionInInitializerError(th);
		}

	}

	public static SessionFactory getSessionFactory() {
		if(ValidationUtils.isNull(sessionFactory)) {
			buildSessionFactory();
		}
		return sessionFactory;
	}
	
	public static void closeSessionFactory() {
		if (ValidationUtils.isNotNull(sessionFactory) && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}
	
	public static void closeSessionAndUnbindFromThread() {
		Session session = sessionFactory.getCurrentSession();
		if (ValidationUtils.isNotNull(session)) {
			session.close();
		}
	}
	
	public static void commitAndCloseSession(Session session) {
		session.getTransaction().commit();
		closeSessionAndUnbindFromThread();
	}
}
