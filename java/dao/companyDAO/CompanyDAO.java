package dao.companyDAO;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import dao.iDAO;
import model.Company;

public class CompanyDAO implements iDAO{
	
	/**
	 * 1. 新增一筆公司資料<br>
	 * 2. 註冊日期資料庫預設是自動填寫當下日期與時間<br>
	 * 3. 更新日期資料庫預設是空值<br>
	 */
	@Override
	public void add(Object obj) {
		Company c = (Company)obj;
		Session session = iDAO.getSession();
		try {
			session.beginTransaction();
			session.save(c);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("新增公司失敗");
		}
	}

	/**
	 * 1. 用參數id查詢公司資料<br>
	 * 2. 回傳查詢的公司資料物件<br>
	 */
	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Company company = null;
		
		try {
			company = session.get(Company.class, id);
			if(company==null) throw new Exception("無此公司資料");
		} catch (Exception e) {
			System.out.println("查詢Company資料失敗");
			e.printStackTrace();
		}		
		return company;
	}

	/**
	 * 1. 用參數id取得資料庫中符合id的公司物件oldCompany<br>
	 * 2. 將參數物件obj轉型為公司物件newCompany<br>
	 * 3. 若newCompany的屬性數值不是空值，則更新oldCompany的屬性數值<br>
	 */
	@Override
	public void updateAll(int id, Object obj) {
		Session session = iDAO.getSession();
		Company newCompany = (Company)obj;
		Company oldCompany = session.get(Company.class, id);
		if(oldCompany == null) return;
		
		try {
			session.clear();
			session.beginTransaction();
			session.update(newCompany);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("更新公司資料失敗");
			e.printStackTrace();
		}		
	}

	/**
	 * 1. 用參數id刪除資料庫中符合id的公司資料<br>
	 */
	@Override
	public void delete(int id) {
		Session session = iDAO.getSession();
		Company company = session.get(Company.class, id);
		if(company == null) return;
		
		String sql = "update company set companyStatus = '取消' where companyID = :id";
		NativeQuery<?> query = session.createNativeQuery(sql);
		query.setParameter("id", id);
		
		try {
			session.beginTransaction();
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("刪除company ID: " + id + "失敗");
		}
	}

}
