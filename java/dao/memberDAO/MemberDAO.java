package dao.memberDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import dao.iDAO;
import model.Member;

public class MemberDAO implements iDAO {

	/**
	 * 1.新增會員時同時建立會員消費歷史紀錄表 2.新增會員的輸入資料不包含資料庫已設定預設值欄位與memberID跟updateDate欄位
	 */
	@Override
	public void add(Object obj) {
		Member m = (Member) obj;
		String sql1 = "INSERT INTO [dbo].[member] ([username],[password],[firstName],[lastName],[sex],[birthday],[email],[mobile],[country],[city],[district],[address],[promotion])"
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "create table history." + m.getUsername() + "(historyID int identity primary key, orderID int)";
		Connection con = null;

		// 新增會員時，同時建立會員歷史消費紀錄表
		try {
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
			ps.executeUpdate();
			
			try (Statement stmt = con.createStatement();) {
				stmt.execute(sql2);
			} catch (Exception e) {
				System.out.println("會員:" + m.getUsername() + ",建立消費歷史紀錄失敗");
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

	/**
	 * 1.用PK鍵值查詢會員資料，回傳一筆會員資料
	 */
	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Member m = null;

		try {
			m = session.get(Member.class, id);
			if (m == null)
				throw new Exception("無此會員資料");
		} catch (Exception e) {
			System.out.println("會員查詢失敗");
			e.printStackTrace();
		}

		return m;
	}

	/**
	 * 1.用PK鍵值查詢該會員並更新會員資料 2.判斷更新的資料值是否為null，不是則更新資料庫資料，是則不更新
	 */
	@Override
	public void updateAll(int id, Object obj) {
		Session session = iDAO.getSession();
		Member oldM = session.get(Member.class, id);
		Member newM = (Member) obj;

		if (newM.getUsername() != null)
			oldM.setUsername(newM.getUsername());
		if (newM.getPassword() != null)
			oldM.setPassword(newM.getPassword());
		if (newM.getFirstName() != null)
			oldM.setFirstName(newM.getFirstName());
		if (newM.getLastName() != null)
			oldM.setLastName(newM.getLastName());
		if (newM.getSex() != null)
			oldM.setSex(newM.getSex());
		if (newM.getBirthday() != null)
			oldM.setBirthday(newM.getBirthday().toString());
		if (newM.getEmail() != null)
			oldM.setEmail(newM.getEmail());
		if (newM.getMobile() != null)
			oldM.setMobile(newM.getMobile());
		if (newM.getCountry() != null)
			oldM.setCountry(newM.getCountry());
		if (newM.getCity() != null)
			oldM.setCity(newM.getCity());
		if (newM.getDistrict() != null)
			oldM.setDistrict(newM.getDistrict());
		if (newM.getAddress() != null)
			oldM.setAddress(newM.getAddress());
		if (newM.getPromotion() != null)
			oldM.setPromotion(newM.getPromotion());
		if (newM.getType() != null)
			oldM.setType(newM.getType());
		if (newM.getRegisterDate() != null)
			oldM.setRegisterDate(newM.getRegisterDate().toString());
		oldM.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
		if (newM.getAgreement() != null)
			oldM.setAgreement(newM.getAgreement());
		if (newM.getMemberStatus() != null)
			oldM.setMemberStatus(newM.getMemberStatus());
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.update(oldM);
			trans.commit();
		} catch (Exception e) {
			System.out.println("管理員更新會員失敗");
			e.printStackTrace();
		}
	}

	/**
	 * 1.用PK值查詢會員資料並將會員狀態改成取消
	 */
	@Override
	public void delete(int id) {
		Session session = iDAO.getSession();
		Member m = session.get(Member.class, id);
		if(m == null) return;
		
		String sql = "update member set memberStatus = :status where memberID = :id";
		NativeQuery<?> query = session.createNativeQuery(sql);
		query.setParameter("status", "取消");
		query.setParameter("id", id);
		session.beginTransaction();
		query.executeUpdate();
		session.getTransaction().commit();
	}

}
