package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.accountingDAO.AccountingQuery;
import model.Accounting;

@Controller
@RequestMapping("/adminAccounting")
public class AdminAccounting {

	@Autowired
	private AccountingQuery accountingQuery;
	
	// 會計系統首頁
	@RequestMapping("/home")
	public String home() {
		return "redirect:/adminAccounting/home.jsp";
	}
	
	// 會計系統:新增會計資料畫面
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "redirect:/adminAccounting/adminAccountingCreate.jsp";
	}
	
	// 會計系統:新增會計資料
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Accounting accounting) {
		accountingQuery.add(accounting);
		return "redirect:/adminAccounting/home";
	}
	
	// 會計系統:查詢會計資料
	@RequestMapping("/read")
	public String read(Integer page, HttpSession session) {
		if (page==null) page=1;
		List<Accounting> accountings = accountingQuery.queryByPage(page, 5);
		int totalPage = accountingQuery.totalAccountingPage();
		session.setAttribute("accountings", accountings);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("page", page);
		return "redirect:/adminAccounting/adminAccountingRead.jsp";
	}
	
	// 會計系統:更新會計資料畫面
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, HttpSession session) {
		Accounting accounting = (Accounting)accountingQuery.querySingle(id);
		session.setAttribute("accounting", accounting);
		return "redirect:/adminAccounting/adminAccountingUpdate.jsp";
	}
	
	// 會計系統:更新會計資料
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Accounting accounting, HttpSession session) {
		accountingQuery.updateAll(accounting.getAccountingID(), accounting);
		return "redirect:/adminAccounting/read";
	}
	
	// 會計系統:刪除會計資料
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Integer id, HttpSession session) {
		accountingQuery.delete(id);
		int page = (int)session.getAttribute("page");
		int totalPage = (int)session.getAttribute("totalPage");
		List<Accounting> accountings = accountingQuery.queryByPage(page, 5);
		if(accountings.size()==0 && page!=1) {
			accountings = accountingQuery.queryByPage(page-1, 5);
			session.setAttribute("page", page-1);	
			session.setAttribute("totalPage", totalPage-1);
		}			
		session.setAttribute("accountings", accountings);
		return "redirect:/adminAccounting/adminAccountingRead.jsp";
	}
}
