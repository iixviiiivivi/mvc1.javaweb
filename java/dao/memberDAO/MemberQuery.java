package dao.memberDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import dao.iDAO;
import model.Member;

@Repository
public class MemberQuery extends MemberDAO{
	
	/**
	 * 1.查詢所有會員資料
	 * 2.返回一個List集合的所有會員資料
	 * @return 
	 */
	public List<Member> queryAll(){
		String sql = "select * from member where memberStatus = :status";
		Session session = iDAO.getSession();
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		query.setParameter("status", "註冊");
		List<Member> members = null;
		
		try {
			 members = query.list();
		} catch (Exception e) {
			System.out.println("查詢所有會員資料錯誤");
			e.printStackTrace();
		}		
		return members;
	}
	
	/**
	 * 1.用PK值id與要更新的會員資料m來更新資料庫該筆會員資料<br>
	 * 2.只更新會員允許資料更新的欄位<br>
	 * 3.更新資料不是null，才更新資料庫該欄位資料<br>
	 * @param id
	 * @param m
	 * @return Member
	 */
	public Member updateMember(int id, Member m) {
		Session session = iDAO.getSession();
		Member oldM = session.get(Member.class, id);
		Member newM = m;		
		if (newM.getFirstName()!=null) oldM.setFirstName(newM.getFirstName());
		if (newM.getLastName()!=null) oldM.setLastName(newM.getLastName());
		if (newM.getSex()!=null) oldM.setSex(newM.getSex());
		if (newM.getBirthday()!=null) oldM.setBirthday(newM.getBirthday());
		if (newM.getEmail()!=null) oldM.setEmail(newM.getEmail());
		if (newM.getMobile()!=null) oldM.setMobile(newM.getMobile());
		if (newM.getCountry()!=null) oldM.setCountry(newM.getCountry());
		if (newM.getCity()!=null) oldM.setCity(newM.getCity());
		if (newM.getDistrict()!=null) oldM.setDistrict(newM.getDistrict());
		if (newM.getAddress()!=null) oldM.setAddress(newM.getAddress());
		if (newM.getPromotion()!=null) oldM.setPromotion(newM.getPromotion());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		oldM.setUpdateDate(sdf.format(new Date()));
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.update(oldM);
			trans.commit();
		} catch (Exception e) {
			System.out.println("會員更新會員失敗");
			e.printStackTrace();
		}		
		return oldM;
	}	
	
	/**
	 * 1.使用參數的username與password驗證資料庫是否有此筆資料<br>
	 * 2.資料庫有資料則回傳該會員所有資料，無則回傳null<br>
	 * @param username
	 * @param password
	 * @return Member
	 */
	public Member logInValidation(String username, String password) {
		String sql = "select * from member where username=:username and password=:password";
		Session session = iDAO.getSession();
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		Member m = null;
		
		try {
			m = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("驗證帳號密碼會員查詢錯誤");
			e.printStackTrace();
		}			
		return m;
	}
	
	/**
	 * 1.用username查詢資料庫是否有同樣的username<br>
	 * 2.用來提示用戶使用者名稱輸入錯誤<br>
	 * 3.也可用驗證有無重複username<br>
	 * @param username
	 * @return bool
	 */
	public boolean usernameValidation(String username) {
		if(username!=null) {
			String sql = "select count(*) from member where username=:username";
			Session session = iDAO.getSession();
			NativeQuery<?> query = session.createNativeQuery(sql);
			query.setParameter("username", username);
			int check = (int)query.getSingleResult();
			
			if(check==1) return true;
			else return false;
		}else {
			return false;
		}
	}
	
	/**
	 * 1.使用password查詢資料庫中是否有同樣的password<br>
	 * 2.用來提示用戶密碼輸入錯誤<br>
	 * @param password
	 * @return bool
	 */
	public boolean passwordValidation(String password) {
		String sql = "select count(*) from member where password=:password";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		query.setParameter("password", password);
		int check = (int)query.getSingleResult();
		
		if(check==1) return true;
		else return false;
	}
	
	/**
	 * 1.更新用戶密碼<br>
	 * @param m
	 */
	public void passwordUpdate(Member m) {
		Session session = iDAO.getSession();
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.update(m);;
			trans.commit();
		} catch (Exception e) {
			System.out.println("會員密碼更新失敗");
			e.printStackTrace();
		}
	}
	
	/**
	 * 1.依照分頁號碼page與每頁要顯示多少筆資料rows來查詢符合的會員資料<br>
	 * 2.回傳查詢的結果是List集合的會員資料<br>
	 * @param page
	 * @param rows
	 * @return List<Member>
	 */
	public List<Member> queryByPage(int page, int rows){
		Session session = iDAO.getSession();
		String sql = "select * from member where memberStatus = :status";
		NativeQuery<Member> query = session.createNativeQuery(sql, Member.class);
		query.setParameter("status", "註冊");
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<Member> memebers= null;
		
		try {
			 memebers = query.list();
		} catch (Exception e) {
			System.out.println("會員按分頁與筆數查詢錯誤");
			e.printStackTrace();
		}		
		return memebers;
	}	
	
}
