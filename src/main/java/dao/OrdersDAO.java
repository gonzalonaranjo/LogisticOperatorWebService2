package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bean.Orders;
import bean.User;
import utils.HibernateUtil;
import utils.ValidationUtils;

public class OrdersDAO {
	
	/**
	 * Metodo general de obtencion de Pedidos
	 * @param user User - Usuario que tiene el pedido
	 * @param status int - Codigo del estado del pedido
	 * @return
	 */
	public static List<Orders> getOrders(User user, int status){
		
		try {
			
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Orders> query = session.createQuery("select o from Orders o join o.user u join o.status s where u.userName = :userName and s.id = :status", Orders.class);
			query.setParameter("status", status);
			query.setParameter("userName", user.getUserName());
			
			List<Orders> orderList = query.getResultList();
			
			session.getTransaction().commit();
			
			return orderList;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	/***
	 * Metodo que obtiene el Orders asociado a un ID 
	 * @return order Orders
	 */
	public static Orders getOrderById(Integer orderId) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Orders> query = session.createQuery("Select o from Orders o join o.status s join fetch o.orderHistoryList oh where o.id = :id", Orders.class);
			query.setParameter("id", orderId);
			
			Orders order = query.getSingleResult();
			
			session.getTransaction().commit();
			
			return order;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	/***
	 * Avanza de estado una peticion
	 * @param order Orders
	 */
	public static void modifyOrder(Orders order) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			session.saveOrUpdate(order);
			session.getTransaction().commit(); 
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	public static Session modifyOrderWithoutCommit(Orders order) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		session.saveOrUpdate(order);
		
		return session;
	}
	
	/***
	 * Metodo que obtiene el Orders para el detalle asociado a un ID 
	 * @return order Orders
	 */
	public static Orders getOrderDetailsById(Integer orderId) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Orders> query = session.createQuery("Select o from Orders o join o.status s "
					+ "join o.productOrderList prOr join prOr.product where o.id = :id", Orders.class);
			query.setParameter("id", orderId);
			
			Orders order = query.getSingleResult();
			
			session.getTransaction().commit();
			
			return order;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	/**
	 * Metodo para crear un nuevo pedido
	 * @param order Orders
	 * */
	public static void createNewOrder(Orders order) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			session.save(order);
			session.getTransaction().commit(); 
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	/**
	 * Es una guarreria pero aunque la BBDD esta definida como autoincrement 
	 * Hibernate EXIGE la informacion de dicho atributo asi que no queda otra
	 * 
	 * @return Integer
	 */
	public static Integer getLastOrderId() {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Integer> query = session.createQuery("Select max(o.id) from Orders o", Integer.class);
			
			Integer lastId = query.getSingleResult();
			
			session.getTransaction().commit();
			
			if(ValidationUtils.isNull(lastId)) {
				lastId = 0;
			}
			
			return lastId;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
}
