package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import bean.Product;
import utils.HibernateUtil;

public class ProductDAO {
	
	
	/**
	 * Inserta un nuevo producto en la base de datos
	 * @param product Product
	 */
	public static void createNewProduct(Product product) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			session.save(product);
			
			session.getTransaction().commit(); 
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
	
	public static List<Product> getAllProducts() {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			TypedQuery<Product> query = session.createQuery("select p from Product p", Product.class);
			
			List<Product> productList = query.getResultList();
			
			session.getTransaction().commit(); 
			return productList;
		} finally {
			HibernateUtil.closeSessionAndUnbindFromThread();
		}
	}
}
