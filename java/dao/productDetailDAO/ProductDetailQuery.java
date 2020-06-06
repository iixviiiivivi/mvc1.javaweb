package dao.productDetailDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import dao.iDAO;
import model.ProductDetail;
import model.Stock;

@Repository
public class ProductDetailQuery extends ProductDetailDAO{

	/**
	 * 1. 購物首頁中顯示不重複商品<br>
	 * 2. page是第幾頁<br>
	 * 3. rows是每一頁顯示幾筆資料<br>
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<ProductDetail> getProductdDetailsPagination(int page, int rows){
		Session session = iDAO.getSession();
		String sql = "select * from productDetail";
		NativeQuery<ProductDetail> query = session.createNativeQuery(sql, ProductDetail.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<ProductDetail> productDetails = null;
		
		try {
			productDetails = query.list();
		} catch (Exception e) {
			System.out.println("購物商品按分頁與筆數查詢錯誤");
			e.printStackTrace();
		}		
				
		return productDetails;
	}
	
	/**
	 * 1. 回傳所有productDetailID<br>
	 * 2. 回傳List<Integer><br>
	 * @return
	 */
	public List<Integer> getProductDetailIDs(){
		String sql = "select productDetailID from productDetail";
		Session session = iDAO.getSession();
		@SuppressWarnings("unchecked")
		NativeQuery<Integer> query = session.createNativeQuery(sql);
		List<Integer> productDetailIDs = query.list();
		return productDetailIDs;
	}
	
	/**
	 * 1. 顯示總共多少購物頁數<br>
	 * 2. 依照每頁顯示多少rows資料，來判定購物頁數<br>
	 * @param rows
	 * @return
	 */
	public int totalProductDetailPages(int rows) {
		Session session = iDAO.getSession();
		String sql = "select count(*) from productDetail";
		NativeQuery<?> query = session.createNativeQuery(sql);
		int counts = (int)query.getSingleResult();
		int totalPages = (int)Math.ceil((double)counts / rows);
		return totalPages;
	}
	
	/**
	 * 1. 顯示被點選商品的詳細資料<br>
	 * 2. id是productDetailID<br>
	 * @param id
	 * @return
	 */
	public List<Stock> querySelectedProduct(int id) {
		String sql = "select productDetailID,size,stock,sold,price,tax,discount,onlineDate,offlineDate,stockStatus from stock where productDetailID=? and stockStatus='上架'";
		List<Stock> stocks = new ArrayList<Stock>();
		Connection con = iDAO.getMSSqlConnection();		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Stock stock = new Stock();
				stock.setProductDetailID(rs.getInt("productDetailID"));
				stock.setSize(rs.getString("size"));
				stock.setStock(rs.getInt("stock"));
				stock.setSold(rs.getInt("sold"));
				stock.setPrice(rs.getInt("price"));
				stock.setTax(rs.getDouble("tax"));
				stock.setDiscount(rs.getDouble("discount"));
				stock.setOnlineDate(rs.getString("onlineDate"));
				stock.setOfflineDate(rs.getString("offlineDate"));
				stock.setStockStatus(rs.getString("stockStatus"));
				stocks.add(stock);
			}			
		} catch (SQLException e) {
			System.out.println("首頁跳轉產品頁面失敗");
			e.printStackTrace();
		}
		
		return stocks;
	}
	
	/**
	 * 1. 用id與size查詢選中商的價格、稅與折扣<br>
	 * 2. id是productDetailID<br>
	 * @param id
	 * @param size
	 * @return
	 */
	public Stock querySinglePriceTaxDiscount(Integer id, String size) {
		String sql = "select price,tax,discount from stock where productDetailID=? and size=? and stockStatus='上架'";
		Stock stock = new Stock();
		Connection con = iDAO.getMSSqlConnection();		
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, size);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				stock.setPrice(rs.getInt("price"));
				stock.setTax(rs.getDouble("tax"));
				stock.setDiscount(rs.getDouble("discount"));
			}			
		} catch (SQLException e) {
			System.out.println("查詢產品價格、稅與折扣失敗");
			e.printStackTrace();
		}
		
		return stock;
	}

	
}
