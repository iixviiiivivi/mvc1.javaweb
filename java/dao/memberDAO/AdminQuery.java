package dao.memberDAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import dao.iDAO;
import model.Member;

@Repository
public class AdminQuery extends MemberQuery{
	
	public void add(Object obj) {
		Member m = (Member)obj;
		String sql1 = "INSERT INTO [dbo].[member]([username],[password],[firstName],[lastName],[sex],[birthday],[email],[mobile],[country],[city],[district],[address],[promotion],[type],[memberStatus])" + 
				"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "create table history."+ m.getUsername() 
		+"(historyID int identity primary key, orderID int)";
		Connection con = null;
		
		// 新增會員時，同時建立會員歷史消費紀錄表
				try{
					con = iDAO.getMSSqlConnection();
					PreparedStatement ps = con.prepareStatement(sql1);
					con.setAutoCommit(false);
					ps.setString(1, m.getUsername());
					ps.setString(2, m.getPassword());
					ps.setString(3, m.getFirstName());
					ps.setString(4, m.getLastName());
					ps.setString(5, m.getSex());
					ps.setString(6, m.getBirthday());
					ps.setString(7, m.getEmail());
					ps.setString(8, m.getMobile());
					ps.setString(9, m.getCountry());
					ps.setString(10, m.getCity());
					ps.setString(11, m.getDistrict());
					ps.setString(12, m.getAddress());
					ps.setString(13, m.getPromotion());
					ps.setString(14, m.getType());
					ps.setString(15, m.getMemberStatus());
					ps.executeUpdate();
					
					try(Statement stmt = con.createStatement();) {
						stmt.execute(sql2);
					} catch (Exception e) {
						System.out.println("會員:"+ m.getUsername() +",建立消費歷史紀錄失敗");
						e.printStackTrace();
						con.rollback();
					}					
					con.commit();
				} catch (Exception e) {
					System.out.println("會員新增失敗");
					e.printStackTrace();
					try {
						con.rollback();
					} catch (SQLException e1) {
						System.out.println("會員新增Rollback失敗");
						e1.printStackTrace();
					}
				}
	}
	
	// 計算會員資料共幾頁
	public int totalMemberPage() {
		String sql = "select CEILING((select count(*) from member) / 5.0);";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		int totalPages = ((BigDecimal)query.getSingleResult()).intValue();
		
		return totalPages;
	}
	
	
	
}
