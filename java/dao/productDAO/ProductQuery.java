package dao.productDAO;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import dao.iDAO;
import model.Product;


@Repository
public class ProductQuery extends ProductDAO{

	// 全部Product資料
	public List<Product> getProducts(){
		String sql = "select * from product";
		Session session = iDAO.getSession();
		NativeQuery<Product> query = session.createNativeQuery(sql, Product.class);
		List<Product> products = query.list();
		if(products.size() < 0) return null;
		
		return products;
	}
	
	// 全部Product分頁資料
	public List<Product> getProductsPagination(int page, int rows){
		String sql = "select * from product";
		Session session = iDAO.getSession();
		NativeQuery<Product> query = session.createNativeQuery(sql, Product.class);
		query.setFirstResult((page-1) * rows);
		query.setMaxResults(rows);
		List<Product> products = query.list();
		if(products.size() < 0) return null;
		
		return products;
	}
	
	// 查詢Product資料總共幾頁
	public int getTotalProductPage() {
		String sql = "select CEILING((select count(*) from product) / 5.0);";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createSQLQuery(sql);
		int totalPages = 0;
		
		try {
			totalPages = ((BigDecimal)query.getSingleResult()).intValue();	
		} catch (Exception e) {
			System.out.println("查詢Product資料分頁共幾頁錯誤");
			e.printStackTrace();
		}
			
		return totalPages;
	}
	
}
