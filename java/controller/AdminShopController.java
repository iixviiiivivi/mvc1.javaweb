package controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.orderDAO.OrderQuery;
import model.Order;
import model.OrderDetail;

@Controller
@RequestMapping("/adminShop")
public class AdminShopController {

	@Autowired
	private OrderQuery orderQuery;
	
	// 管理者購物系統首頁
	@RequestMapping("/home")
	public String home() {
		return "redirect:/adminShop/home.jsp";
	}
	
	// 管理者查詢購物資料
	@RequestMapping("/readOrders")
	public String read(Integer page, HttpSession session) {
		if(page==null) page=1;
		List<Order> orders = orderQuery.queryOrdersByPage(page, 5);
		int totalPage = orderQuery.totalOrderPages();
		session.setAttribute("orders", orders);
		session.setAttribute("page", page);
		session.setAttribute("totalPage", totalPage);
		return "redirect:/adminShop/adminShopRead.jsp";
	}
	
	// 管理者查詢特定購物資料的購物詳細資料
	@RequestMapping("/readOrderDetails")
	public String readOrderDetails(Integer id, HttpSession session) {
		List<OrderDetail> orderDetails = orderQuery.queryOrderDetailsById(id);
		session.setAttribute("orderDetails", orderDetails);
		return "redirect:/adminShop/adminShopOrderDetails.jsp";
	}
	
	
	// 管理者更新購物資料畫面
	@RequestMapping(value = "/updateOrder", method = RequestMethod.GET)
	public String updateOrder(Integer id, HttpSession session) {
		Order order = (Order)orderQuery.querySingle(id);
		session.setAttribute("order", order);
		return "redirect:/adminShop/adminShopUpdate.jsp";
	}
	
	// 管理者更新購物資料畫面
	@RequestMapping(value = "/updateOrder", method = RequestMethod.POST)
	public String updateOrder(Order order, HttpSession session) {
		orderQuery.updateAll(order.getOrderID(), order);
		session.removeAttribute("order");
		return "redirect:/adminShop/readOrders";
	}
	
	// 管理者刪除購物資料
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer id) {
		orderQuery.delete(id);
		return "redirect:/adminShop/adminShopRead.jsp";
	}
	
}
