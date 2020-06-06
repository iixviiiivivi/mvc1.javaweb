package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.companyDAO.CompanyQuery;
import model.Company;

@Controller
@RequestMapping("/adminCompany")
public class AdminCompanyController {

	@Autowired
	private CompanyQuery companyQuery;

	// 廠商管理系統首頁
	@RequestMapping("/home")
	public String home() {
		return "redirect:/adminCompany/home.jsp";
	}

	// Create
	// 廠商:介面
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {
		return "redirect:/adminCompany/adminCompanyCreate.jsp";
	}

	// 廠商:新增廠商
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(Company company) {
		companyQuery.add(company);
		return "redirect:/adminCompany/home";
	}

	// Read
	// 廠商: 顯示預設廠商資料
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String read(Integer page, HttpSession session) {
		if (page == null) page = 1;
		List<Company> companies = companyQuery.queryByPage(page, 5);
		int totalPage = companyQuery.totalCompanyPage();
		session.setAttribute("companies", companies);
		session.setAttribute("totalPage", totalPage);
		session.setAttribute("page", page);
		return "redirect:/adminCompany/adminCompanyRead.jsp";
	}

	// Update
	// 廠商：顯示更新的廠商資料
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Integer id, HttpSession httpSession) {
		Company company = (Company) companyQuery.querySingle(id);
		httpSession.setAttribute("company", company);
		return "redirect:/adminCompany/adminCompanyUpdate.jsp";
	}

	// 廠商：更新廠商資料
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Company company, HttpSession httpSession) {
		companyQuery.updateAll(company.getCompanyID(), company);
		return "redirect:/adminCompany/read";
	}

	// Delete
	// 刪除廠商
	@RequestMapping("/delete")
	public String delete(Integer id, HttpSession session) {
		companyQuery.delete(id);
		int page = (int) session.getAttribute("page");
		int totalPage = (int) session.getAttribute("totalPage");
		List<Company> companies = companyQuery.queryByPage(page, 5);
		if (companies.size() == 0 && page != 1) {
			companies = companyQuery.queryByPage(page - 1, 5);
			session.setAttribute("page", page - 1);
			session.setAttribute("totalPage", totalPage - 1);
		}
		session.setAttribute("companies", companies);
		return "redirect:/adminCompany/adminCompanyRead.jsp";
	}

}
