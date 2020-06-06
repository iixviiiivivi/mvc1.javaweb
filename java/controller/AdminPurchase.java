package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.companyDAO.CompanyQuery;
import dao.productDetailDAO.ProductDetailQuery;
import dao.purchaseDAO.PurchaseQuery;
import model.Purchase;
import model.Warehouse;

@Controller
@RequestMapping("/adminPurchase")
public class AdminPurchase {
	
	@Autowired
	private CompanyQuery companyQuery;
	@Autowired
	private PurchaseQuery purchaseQuery;
	@Autowired
	private ProductDetailQuery productDetailQuery;
	
	
	// 進貨首頁畫面
	@RequestMapping("/home")
	public String home() {
		return "redirect:/adminPurchase/home.jsp";
	}
	
	// 新增進或畫面
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Integer id, String size, HttpSession session) {
		Warehouse warehouse = purchaseQuery.querySingleWarehouses(id, size);
		List<Integer> companyIDs = companyQuery.queryAllCompanyID();
		List<Integer> productDetailIDs = productDetailQuery.getProductDetailIDs();
		session.setAttribute("warehouse", warehouse);
		session.setAttribute("companyIDs", companyIDs);
		session.setAttribute("productDetailIDs", productDetailIDs);
		return "redirect:/adminPurchase/adminPurchaseCreate.jsp";
	}
	
	// 新增進貨
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Purchase purchase, HttpSession session) {
		System.out.println(purchase.getOrderDate());
		System.out.println(purchase.getArrivalDate());
		purchaseQuery.add(purchase);
		return "redirect:/adminPurchase/read";
	}
	
	// 查詢進貨
	@RequestMapping("/read")
	public String read(Integer page, HttpSession session) {
		if (page==null) page=1;
		List<Purchase> purchases = purchaseQuery.queryPurchaseByPage(page, 5);
		int totalPage = purchaseQuery.totalPurchasePage();
		session.setAttribute("purchases", purchases);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("page", page);
		return "redirect:/adminPurchase/adminPurchaseRead.jsp";
	}
	
	// 更新進貨畫面
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, HttpSession session) {
		Purchase purchase = (Purchase)purchaseQuery.querySingle(id);
		session.setAttribute("purchase", purchase);
		return "redirect:/adminPurchase/adminPurchaseUpdate.jsp";
	}
	
	// 更新進貨
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Purchase purchase) {
		purchaseQuery.updateAll(purchase.getPurchaseID(), purchase);
		return "redirect:/adminPurchase/read";
	}
	
	// 取消進貨
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(int id) {
		System.out.println("delete============");
		purchaseQuery.delete(id);
		return "redirect:/adminPurchase/read";
	}
	
	// 查詢庫存資料頁面
	@RequestMapping("/readStocks")
	public String readStocks(Integer page, HttpSession session) {
		if (page==null) page=1;
		List<Warehouse> warehouses = purchaseQuery.queryByPage(page, 5);
		int totalPage = purchaseQuery.totalWarehousePage();
		session.setAttribute("warehouses", warehouses);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("page", page);
		return "redirect:/adminPurchase/adminPurchaseReadStocks.jsp";
	}

}
