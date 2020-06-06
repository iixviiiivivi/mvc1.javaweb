package dao.orderDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import dao.iDAO;
import model.Cart;
import model.Order;
import model.OrderDetail;
import model.OrderList;

@Repository
public class OrderQuery extends OrderDAO{

	/**
	 * 1. 取得訂單編號<br>
	 * @return
	 */
	public int getOrderNo() {
		String sql = "select next value for orderno";
		Connection con = iDAO.getMSSqlConnection();
		int orderNo = 0;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs =  stmt.executeQuery(sql);
			while(rs.next()) {
				orderNo = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderNo;
	}
	
	/**
	 * 1. 同時新增orders與orderDetail資料<br>
	 * 2. 更新stock表格中的庫存量與增加accounting表格資料<br>
	 * @param orderno
	 * @param order
	 * @param carts
	 */
	public void createOrdersOrderDetails(int orderno, Order order, Map<String, Cart> carts) {
		order.setOrderID(orderno); // 訂單編號
		String sql1 = "insert into orderDetail(orderID,productDetailID,color,size,quantity,price)"+ 
					  "values(?,?,?,?,?,?)";
		String sql2 = "INSERT INTO orders(orderID,memberID,firstName,lastName,email,mobile,country,city,district,address)" + 
					  "VALUES(?,?,?,?,?,?,?,?,?,?)";
		String sql3 = "update stock set stock=stock-?, sold=sold+? where productDetailID=? and size=?";
		String sql4 = "insert into accounting(orderID, profit) values(?,?)";
		
		Connection con = iDAO.getMSSqlConnection();
		PreparedStatement ps = null;
		
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(sql2);
			ps.setInt(1, orderno);
			ps.setInt(2, order.getMemberID());
			ps.setString(3, order.getFirstName());
			ps.setString(4, order.getLastName());
			ps.setString(5, order.getEmail());
			ps.setString(6, order.getMobile());
			ps.setString(7, order.getCountry());
			ps.setString(8, order.getCity());
			ps.setString(9, order.getDistrict());
			ps.setString(10, order.getAddress());
			ps.executeUpdate();
			
			ps = con.prepareStatement(sql1);
			for(String key: carts.keySet()) {
				Cart cart = carts.get(key);
				ps.setInt(1, orderno);
				ps.setInt(2, cart.getId());
				ps.setString(3, cart.getColor());
				ps.setString(4, cart.getSize());
				ps.setInt(5, cart.getQuantity());
				ps.setInt(6, cart.getPrice());
				ps.addBatch();
			}
			ps.executeBatch();
			
			ps = con.prepareStatement(sql3);
			for(String key: carts.keySet()) {
				Cart cart = carts.get(key);
				ps.setInt(1, cart.getQuantity());
				ps.setInt(2, cart.getQuantity());
				ps.setInt(3, cart.getId());
				ps.setString(4, cart.getSize());
				ps.addBatch();
			}
			ps.executeBatch();
			
			ps = con.prepareStatement(sql4);
			for(String key: carts.keySet()) {
				Cart cart = carts.get(key);
				ps.setInt(1, orderno);
				ps.setInt(2, cart.getPrice());
				ps.addBatch();
			}
			ps.executeBatch();
			
			con.commit();
		} catch (SQLException e) {
			System.out.println("新增orders與orderDetails失敗");
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				System.out.println("新增orders與orderDetails rollback失敗");
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 1. 查詢會員購物清單<br>
	 * @param memberID
	 * @return
	 */
	public List<OrderList> queryPurchasedOrders(int memberID) {
		String sql = "select o.orderID, p.productID,pd.productDetailID,p.productName, od.color, od.size, od.quantity, od.price, o.orderDate, o.orderStatus " + 
				     "from orders as o " + 
				     "join orderDetail as od on od.orderID=o.orderID " + 
				     "join productDetail as pd on pd.productDetailID=od.productDetailID " + 
				     "join product as p on p.productID=pd.productID " + 
				     "where o.memberID=? and o.orderStatus<>'取消'"; 
		Connection con = iDAO.getMSSqlConnection();
		List<OrderList> orderLists = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, memberID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderList ol = new OrderList();
				ol.setOrderID(rs.getInt("orderID"));
				ol.setProductID(rs.getInt("productID"));
				ol.setProductDetailID(rs.getInt("productDetailID"));
				ol.setProductName(rs.getString("productName"));
				ol.setColor(rs.getString("color"));
				ol.setSize(rs.getString("size"));
				ol.setQuantity(rs.getInt("quantity"));
				ol.setPrice(rs.getInt("price"));
				ol.setOrderDate(rs.getString("orderDate"));
				ol.setOrderStatus(rs.getString("orderStatus"));
				orderLists.add(ol);
			}
		} catch (SQLException e) {
			System.out.println("查詢會員購物清單失敗");
			e.printStackTrace();
		}
		
		return orderLists;
	}
	
	/**
	 * 1. 取消會員購物清單並將accounting表格含有orderID的profit欄位設為0<br>
	 * @param orderID
	 * @param memberID
	 */
	public void deletePurchasedOrders(int orderID, int memberID) {
		String sql1 = "update orders set orderStatus='取消' where orderID=? and memberID=?";
		String sql2 = "update accounting set profit=0 where orderID=?";
		Connection con = iDAO.getMSSqlConnection();
		PreparedStatement ps = null;
		
		try {
			ps = con.prepareStatement(sql1);
			ps.setInt(1, orderID);
			ps.setInt(2, memberID);
			ps.execute();
			
			ps = con.prepareStatement(sql2);
			ps.setInt(1, orderID);
			ps.execute();
		} catch (SQLException e) {
			System.out.println("會員購物清單取消失敗");
			e.printStackTrace();
		}
	}
	
	// 查詢Order分頁資料
	public List<Order> queryOrdersByPage(int page, int rows){
		String sql = "select * from orders";
		Session session = iDAO.getSession();
		NativeQuery<Order> query = session.createNativeQuery(sql, Order.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<Order> orders = null;
		
		try {
			orders = query.list();
		} catch (Exception e) {
			System.out.println("Orders分頁錯誤");
			e.printStackTrace();
		}
		
		return orders;
	}
	
	// 查詢總共多少Order頁
	public int totalOrderPages() {
		String sql = "select ceiling((select count(*) from orders) / 5.0)";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		int  totalPage = ((BigDecimal)query.getSingleResult()).intValue();
		
		return totalPage;
	}
	
	public List<OrderDetail> queryOrderDetailsById(int id){
		String sql = "select orderID,productDetailID,color,size,quantity,price " + 
					 "from orderDetail " + 
					 "where orderID=?";
		Connection con = iDAO.getMSSqlConnection();
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				OrderDetail od = new OrderDetail();
				od.setOrderID(rs.getInt("orderID"));
				od.setProductDetailID(rs.getInt("productDetailID"));
				od.setColor(rs.getString("color"));
				od.setSize(rs.getString("size"));
				od.setQuantity(rs.getInt("quantity"));
				od.setPrice(rs.getInt("price"));
				orderDetails.add(od);
			}
		} catch (SQLException e) {
			System.out.println("查詢orderDetail失敗");
			e.printStackTrace();
		}
		
		return orderDetails;
	}
	
	// 取消Order
	public void deleteOrder(int id) {
		String sql = "update orders set orderStatus='取消' where orderID=:orderID";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			query.setParameter("orderID", id);
			query.executeUpdate();
			trans.commit();
		} catch (Exception e) {
			System.out.println("取消Order失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}
	
}
