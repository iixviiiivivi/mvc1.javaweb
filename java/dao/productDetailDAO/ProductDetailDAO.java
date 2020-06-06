package dao.productDetailDAO;

import org.hibernate.Session;
import dao.iDAO;
import model.ProductDetail;

public class ProductDetailDAO implements iDAO{

	@Override
	public void add(Object obj) {
		ProductDetail productDetail = (ProductDetail)obj;
		Session session = iDAO.getSession();
		
		try {
			session.beginTransaction();
			session.save(productDetail);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("新增產品詳細資料失敗");
			e.printStackTrace();
		}
	}

	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		ProductDetail productDetail = session.get(ProductDetail.class, id);
		return productDetail;
	}

	@Override
	public void updateAll(int id, Object obj) {
		Session session = iDAO.getSession();
		if(session.get(ProductDetail.class, id) == null) return;
		
		ProductDetail newProductDetail = (ProductDetail)obj;
		try {
			session.clear();
			session.beginTransaction();
			session.update(newProductDetail);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("更新產品明細資料失敗");	
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
	}

}
