package dao.orderDAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.iDAO;
import model.Order;

public class OrderDAO implements iDAO {
	
	@Override
	public void add(Object obj) {
		Order order = (Order)obj;
		Session session = iDAO.getSession();
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.save(order);
			trans.commit();
		} catch (Exception e) {
			System.out.println("新增order失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}

	@Override
	public Object querySingle(int id) {
		Session session = iDAO.getSession();
		Order order = session.get(Order.class, id);
		return order;
	}

	@Override
	public void updateAll(int id, Object obj) {
		Order newOrder = (Order)obj;
		newOrder.setOrderID(id);
		Session session = iDAO.getSession();
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.update(newOrder);
			trans.commit();
		} catch (Exception e) {
			System.out.println("更新order失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}

	@Override
	public void delete(int id) {
		Session session = iDAO.getSession();
		Order order = session.get(Order.class, id);
		Transaction trans = null;
		
		try {
			trans = session.beginTransaction();
			session.delete(order);
			trans.commit();
		} catch (Exception e) {
			System.out.println("刪除Order失敗");
			e.printStackTrace();
			trans.rollback();
		}
	}

}
