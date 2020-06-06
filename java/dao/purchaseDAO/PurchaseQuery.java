package dao.purchaseDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import dao.iDAO;
import model.Purchase;
import model.Warehouse;

@Repository
public class PurchaseQuery extends PurchaseDAO {
	
	// 查詢所有進貨資料
	public List<Purchase> queryAllPurchases(){
		String sql = "select * from purchase";
		Session session = iDAO.getSession();
		NativeQuery<Purchase> query = session.createNativeQuery(sql, Purchase.class);
		List<Purchase> purchases = query.list();
		return purchases;
	}
	
	// 查詢所有進貨分頁資料
	public List<Purchase> queryPurchaseByPage(int page, int rows){
		String sql = " select * from purchase";
		Session session = iDAO.getSession();
		NativeQuery<Purchase> query = session.createNativeQuery(sql, Purchase.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<Purchase> purchases = null;
		
		try {
			purchases = query.list();
		} catch (Exception e) {
			System.out.println("進貨資料按分頁與筆數查詢錯誤");
			e.printStackTrace();
		}		
		return purchases;
	}
	
	// 查詢進貨分頁共幾頁
	public int totalPurchasePage() {
		String sql = "select CEILING((select count(*) from purchase) / 5.0);";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		int totalPages = ((BigDecimal)query.getSingleResult()).intValue();		
		return totalPages;
	}
	
	// 查詢所有庫存產品
	public List<Warehouse> queryAllWarehouses(){
		String sql = "select p.productID, pd.productDetailID,pd.companyID, p.productName, pd.color, s.size, s.stock, s.sold, s.stockStatus " + 
				     "from product as p " + 
				     "join productDetail as pd on pd.productID=p.productID " + 
				     "join stock as s on s.productDetailID=pd.productDetailID";
		Connection con = iDAO.getMSSqlConnection();
		List<Warehouse> warehouses = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Warehouse wh = new Warehouse();
				wh.setProductID(rs.getInt("productID"));
				wh.setProductDetailID(rs.getInt("productDetailID"));
				wh.setCompanyID(rs.getInt("companyID"));
				wh.setProductName(rs.getString("productName"));
				wh.setColor(rs.getString("color"));
				wh.setSize(rs.getString("size"));
				wh.setStock(rs.getInt("stock"));
				wh.setSold(rs.getInt("sold"));
				wh.setStockStatus(rs.getString("stockStatus"));
				warehouses.add(wh);
			}
		} catch (SQLException e) {
			System.out.println("查詢所有庫存產品失敗");
			e.printStackTrace();
		}
		
		return warehouses;
	}
	
	// 回傳指定的庫存產品資料
		public Warehouse querySingleWarehouses(int id, String size){
			String sql = "select p.productID, pd.productDetailID,pd.companyID, p.productName, pd.color, s.size, s.stock, s.sold, s.stockStatus " + 
					     "from product as p " + 
					     "join productDetail as pd on pd.productID=p.productID " + 
					     "join stock as s on s.productDetailID=pd.productDetailID " +
					     "where pd.productDetailID=? and size=?";
			Connection con = iDAO.getMSSqlConnection();
			Warehouse wh = new Warehouse();
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, size);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					wh.setProductID(rs.getInt("productID"));
					wh.setProductDetailID(rs.getInt("productDetailID"));
					wh.setCompanyID(rs.getInt("companyID"));
					wh.setProductName(rs.getString("productName"));
					wh.setColor(rs.getString("color"));
					wh.setSize(rs.getString("size"));
					wh.setStock(rs.getInt("stock"));
					wh.setSold(rs.getInt("sold"));
					wh.setStockStatus(rs.getString("stockStatus"));
				}
			} catch (SQLException e) {
				System.out.println("查詢所有庫存產品失敗");
				e.printStackTrace();
			}
			
			return wh;
		}
		
		// 查詢所有庫存產品分頁資料
		public List<Warehouse> queryByPage(int page, int rows){
			String sql = "select p.productID, pd.productDetailID,pd.companyID, p.productName, pd.color, s.size, s.stock, s.sold, s.stockStatus " + 
						 "from product as p " + 
						 "join productDetail as pd on pd.productID=p.productID " + 
						 "join stock as s on s.productDetailID=pd.productDetailID " + 
						 "order by p.productID " + 
						 "offset ? rows fetch next ? rows only";
			Connection con = iDAO.getMSSqlConnection();
			List<Warehouse> warehouses = new ArrayList<>();
			
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, page-1);
				ps.setInt(2, rows);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Warehouse wh = new Warehouse();
					wh.setProductID(rs.getInt("productID"));
					wh.setProductDetailID(rs.getInt("productDetailID"));
					wh.setCompanyID(rs.getInt("companyID"));
					wh.setProductName(rs.getString("productName"));
					wh.setColor(rs.getString("color"));
					wh.setSize(rs.getString("size"));
					wh.setStock(rs.getInt("stock"));
					wh.setSold(rs.getInt("sold"));
					wh.setStockStatus(rs.getString("stockStatus"));
					warehouses.add(wh);
				}
			} catch (SQLException e) {
				System.out.println("查詢所有庫存產品失敗");
				e.printStackTrace();
			}
			
			return warehouses;
		}
		
		// 查詢庫存產品分頁共幾頁
		public int totalWarehousePage() {
			String sql = "with tbl\r\n" + 
						 "as(\r\n" + 
						 "	select count(*) as counts\r\n" + 
						 "	from product as p \r\n" + 
						 "	join productDetail as pd on pd.productID=p.productID\r\n" + 
						 "	join stock as s on s.productDetailID=pd.productDetailID\r\n" + 
						 ")\r\n" + 
						 "select CEILING((select * from tbl) / 5.0)";
			int totalPages = 0;
			Connection con = iDAO.getMSSqlConnection();
			Statement stmt;
			try {
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()) totalPages = rs.getInt(1);
			} catch (SQLException e) {
				System.out.println("查詢庫存總頁數失敗");
				e.printStackTrace();
			}
			
			return totalPages;
		}
}
