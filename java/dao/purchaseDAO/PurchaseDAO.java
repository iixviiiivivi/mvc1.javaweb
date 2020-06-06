package dao.purchaseDAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import dao.iDAO;
import model.Accounting;
import model.Purchase;

public class PurchaseDAO implements iDAO{

	/**
	 * 1. 新增一筆進貨資料<br>
	 * 2. 新增同時更新accounting與stock表格<br>
	 */
	@Override
	public void add(Object obj) {
		System.out.println("1");
		Purchase purchase = (Purchase)obj;
		purchase.setCost(purchase.getQuantity() * purchase.getCost());
		System.out.println("2");
		Session session = iDAO.getSession();
		Serializable purchaseID = 0;
		
		Connection con = iDAO.getMSSqlConnection();
		String sql = "update stock set stock+=? where productDetailID=? and size=?";
		System.out.println("3");
		try {
			session.beginTransaction();
			purchaseID = session.save(purchase);
			Accounting accounting = new Accounting();
			accounting.setPurchaseID((int)purchaseID);
			accounting.setCost(purchase.getCost());
			session.save(accounting);
			session.save(purchase);
			session.getTransaction().commit();
			
			System.out.println("4");
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, purchase.getQuantity());
			ps.setInt(2, purchase.getProductDetailID());
			ps.setString(3, purchase.getSize());
			ps.executeUpdate();
			
			System.out.println("5");
		} catch (Exception e) {
			System.out.println("新增進貨資料錯誤");
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	/**
	 * 1. 查詢指定進貨資料<br>
	 * 2. 用id (purchaseID)查詢<br>
	 */
	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Purchase purchase = session.get(Purchase.class, id);
		return purchase;
	}
	
	/**
	 * 1. 更新指定進貨資料<br>
	 * 2. 用參數id與obj更新進貨資料<br>
	 */
	@Override
	public void updateAll(int id, Object obj) {
		Purchase newPurchase = (Purchase)obj;
		newPurchase.setPurchaseID(id);
		Session session = iDAO.getSession();
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.update(newPurchase);
			trans.commit();
		} catch (Exception e) {
			System.out.println("進貨資料更新錯誤");
			trans.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 1. 刪除指定進貨資料<br>
	 * 2. 用參數id (purchaseID)刪除<br>
	 */
	@Override
	public void delete(int id) {
		String sqlPurchase = "update purchase set purchaseStatus = :status where purchaseID = :id";
		Session session = iDAO.getSession();
		Purchase purchase = session.get(Purchase.class, id);
		if (purchase == null) return;
		
		NativeQuery<?> queryP = session.createNativeQuery(sqlPurchase);
		queryP.setParameter("status", "取消");
		queryP.setParameter("id", id);
		
		String sqlAccounting = "update accounting set cost = 0 where purchaseID = :id";
		NativeQuery<?> queryA = session.createNativeQuery(sqlAccounting);
		queryA.setParameter("id", id);
		
		try {
			session.beginTransaction();
			queryP.executeUpdate();
			queryA.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("刪除進貨資料失敗");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
