package dao.productDAO;

import org.hibernate.Session;
import dao.iDAO;
import model.Product;

public class ProductDAO implements iDAO{

	@Override
	public void add(Object obj) {
		Product product = (Product)obj;
		Session session = iDAO.getSession();
		
		try {
			session.beginTransaction();
			session.save(product);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("新增產品失敗");
			e.printStackTrace();
		}
	}

	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Product product = null;
		
		try {
			 product = session.get(Product.class, id);
		} catch (Exception e) {
			System.out.println("查詢產品失敗");
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void updateAll(int id, Object obj) {
		Session session = iDAO.getSession();
		Product oldProduct = session.get(Product.class, id);
		if(oldProduct == null) return;
		
		Product newProduct = (Product)obj;
		try {
			session.clear();
			session.beginTransaction();
			session.update(newProduct);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("更新產品失敗");
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		
	}

}
