package dao.accountingDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.iDAO;
import model.Accounting;

public class AccountingDAO implements iDAO{
	
	/**
	 * 1. 新增accounting資料<br>
	 */
	@Override
	public void add(Object obj) {
		Accounting accounting = (Accounting)obj;
		Session session = iDAO.getSession();
		Transaction trans = null;
		try {
			trans = session.beginTransaction();
			session.save(accounting);
			trans.commit();
		} catch (Exception e) {
			System.out.println("新增會計資料失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}

	/**
	 * 1. 查詢單一accounting資料<br>
	 * 2. 用參數id查詢指定accounting資料<br>
	 */
	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Accounting accounting = null;
		
		try {
			accounting = session.get(Accounting.class, id);
		} catch (Exception e) {
			System.out.println("查詢Accounting錯誤");
			e.printStackTrace();
		}
		return accounting;
	}

	/**
	 * 1. 更新accounting資料<br>
	 * 2. 用參數id與obj更新資料<br>
	 */
	@Override
	public void updateAll(int id, Object obj) {
		Accounting newAccounting = (Accounting)obj;
		newAccounting.setAccountingID(id);
		Session session = iDAO.getSession();
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.update(newAccounting);
			trans.commit();
		} catch (Exception e) {
			System.out.println("更新會計資料失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}

	/**
	 * 1. 刪除單一accounting資料<br>
	 * 2. 用參數id刪除指定accountingID資料<br>
	 */
	@Override
	public void delete(int id) {
		Session session = iDAO.getSession();
		Accounting accounting = session.get(Accounting.class, id);
		Transaction trans = null;

		try {
			trans = session.beginTransaction();
			session.delete(accounting);
			trans.commit();
		} catch (Exception e) {
			System.out.println("刪除會計資料失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}
}
