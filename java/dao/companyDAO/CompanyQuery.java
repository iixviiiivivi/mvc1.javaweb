package dao.companyDAO;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;

import dao.iDAO;
import model.Company;

@Repository
public class CompanyQuery extends CompanyDAO{
	
	/**
	 * 1. 讀取資料庫所有廠商資料，然後回傳List物件<br>
	 * 2. List物件companies是讀取的廠商資料<br>
	 * @return
	 */
	public List<Company> queryAllCompanies() {
		Session session = iDAO.getSession();
		String sql = "select * from company where companyStatus != :status";
		NativeQuery<Company> query = session.createNativeQuery(sql, Company.class);
		query.setParameter("status", "取消");
		
		List<Company> companies = null;
		try {
			companies = query.list();
		} catch (Exception e) {
			System.out.println("查詢所有Company資料錯誤");
			e.printStackTrace();
		}
		
		return companies;
	}
	
	/**
	 * 1. 回傳所有CompanyID<br>
	 * 2. 回傳List<Integer><br>
	 * @return
	 */
	public List<Integer> queryAllCompanyID() {
		String sql = "select companyID from company";
		Session session = iDAO.getSession();
		@SuppressWarnings("unchecked")
		NativeQuery<Integer> query = session.createNativeQuery(sql);
		List<Integer> companyIDs = null;
		try {
			companyIDs = (List<Integer>)query.list();
		} catch (Exception e) {
			System.out.println("查詢所有CompanyID錯誤");
			e.printStackTrace();
		}
		
		return companyIDs;
	}
	
	/**
	 * 1. 查詢Company分頁資料<br>
	 * 2. page是第幾頁，rows是每頁幾筆資料<br>
	 * 3. 回傳List<Company><br>
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Company> queryByPage(int page, int rows){
		String sql = " select * from company where companyStatus != :status";
		Session session = iDAO.getSession();
		NativeQuery<Company> query = session.createNativeQuery(sql, Company.class);
		query.setParameter("status", "取消");
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<Company> companies= null;
		
		try {
			companies = query.list();
		} catch (Exception e) {
			System.out.println("廠商按分頁與筆數查詢錯誤");
			e.printStackTrace();
		}		
		
		return companies;
	}
	
	/**
	 * 1. 查詢Company資料分頁共幾頁
	 * @return
	 */
	public int totalCompanyPage() {
		String sql = "select CEILING((select count(*) from company where companyStatus != :status) / 5.0);";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createSQLQuery(sql);
		query.setParameter("status", "取消");
		int totalPages = 0;
		
		try {
			totalPages = ((BigDecimal)query.getSingleResult()).intValue();	
		} catch (Exception e) {
			System.out.println("查詢Company資料分頁共幾頁錯誤");
			e.printStackTrace();
		}
			
		return totalPages;
	}
}
