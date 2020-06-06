package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import dao.orderDAO.OrderQuery;
import model.Member;
import model.OrderList;

@Controller
@RequestMapping("/memberShop")
public class MemberShopController {

	@Autowired
	private OrderQuery orderQuery;
	
	// 顯示會員購物管理首頁
	@RequestMapping("/home")
	public String home() {
		return "redirect:/memberShop/home.jsp";
	}
	
	// 顯示會員購物清單
	@RequestMapping("/readOrders")
	public String readOrders(HttpSession session) {
		Member member = (Member)session.getAttribute("account");
		List<OrderList> orderLists = orderQuery.queryPurchasedOrders(member.getMemberID());
		session.setAttribute("orderLists", orderLists);
		return "redirect:/memberShop/memberShopReadOrders.jsp";
	}
	
	// 會員購物清單取消
	@RequestMapping("/deleteOrders")
	public String deleteOrders(Integer orderID, HttpSession session) {
		Member member = (Member)session.getAttribute("account");
		orderQuery.deletePurchasedOrders(orderID, member.getMemberID());
		return "redirect:/memberShop/readOrders";
	}
}
