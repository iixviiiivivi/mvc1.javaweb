package dao.accountingDAO;

import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import dao.iDAO;
import model.Accounting;

@Repository
public class AccountingQuery extends AccountingDAO{

	/**
	 * 1. 查詢所有accounting資料<br>
	 * 2. 回傳List<Accounting>資料<br>
	 * @return
	 */
	public List<Accounting> queryAllAccountings(){
		String sql = "select * from accounting";
		Session session = iDAO.getSession();
		NativeQuery<Accounting> query = session.createNativeQuery(sql, Accounting.class);
		List<Accounting> accountings = query.list();
		
		return accountings;
	}
	
	/**
	 * 1. 會計資料分頁<br>
	 * 2. page代表第幾頁, row代表每頁幾筆會計資料<br>
	 * 3. 回傳List<Accounting>資料<br>
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<Accounting> queryByPage(int page, int rows){
		String sql = " select * from accounting";
		Session session = iDAO.getSession();
		NativeQuery<Accounting> query = session.createNativeQuery(sql, Accounting.class);
		query.setFirstResult((page-1)*rows);
		query.setMaxResults(rows);
		List<Accounting> accountings = null;
		
		try {
			accountings = query.list();
		} catch (Exception e) {
			System.out.println("會計資料按分頁與筆數查詢錯誤");
			e.printStackTrace();
		}		
		return accountings;
	}
	
	/**
	 * 1. 計算會計資料按照每頁5筆資料區分，總共有幾頁
	 * 2. 回傳共多少會計資料頁面
	 * @return
	 */
	public int totalAccountingPage() {
		String sql = "select CEILING((select count(*) from accounting) / 5.0);";
		Session session = iDAO.getSession();
		NativeQuery<?> query = session.createNativeQuery(sql);
		int totalPages = 0;
		
		try {
			totalPages = ((BigDecimal)query.getSingleResult()).intValue();
		} catch (Exception e) {
			System.out.println("計算會計資料分頁總共幾頁錯誤");
			e.printStackTrace();
		}
				
		return totalPages;
	}
}
