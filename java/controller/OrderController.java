package controller;

import java.util.Map;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import dao.orderDAO.OrderQuery;
import model.Cart;
import model.Order;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderQuery orderQuery;
	
	// 新增訂單到資料庫中
	@SuppressWarnings("unchecked")
	@RequestMapping("/create")
	public String createOrder(Order order, HttpSession session) {
		Map<String, Cart> carts = (Map<String, Cart>) session.getAttribute("cart");
		int orderNo = new OrderQuery().getOrderNo(); // 取得訂單編號
		orderQuery.createOrdersOrderDetails(orderNo, order, carts);
		session.removeAttribute("cart");
		return "redirect:/shop/create";
	}

}
