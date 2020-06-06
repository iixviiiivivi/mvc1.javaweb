package controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import dao.memberDAO.AdminQuery;
import model.Member;

@Controller
@RequestMapping("/adminMember")
public class AdminMemberController {
	
	@Autowired
	private AdminQuery adminQuery;
	
	// 顯示管理者首頁介面
	@RequestMapping("/home")
	public String home() {		
		return "redirect:/adminMember/home.jsp";
	}
	
	// Create
	// 建立會員資料:介面
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String addMember() {
		return "redirect:/adminMember/adminMemberCreate.jsp";
	}
	
	// 建立會員資料:建立
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addMember(Member member) {
		adminQuery.add(member);
		return "redirect:/adminMember/home";
	}
	
	// Read
	// 查詢所有會員資料:預設頁數是第1頁
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String queryAllMember(Integer page, HttpSession session) {
		if (page==null) page=1;		
		List<Member> members = adminQuery.queryByPage(page, 5);
		int totalPages = adminQuery.totalMemberPage();
		session.setAttribute("members", members);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("page", page);
		return "redirect:/adminMember/adminMemberRead.jsp";
	}
	
	// Update
	// 更新會員資料:介面
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateMember(Integer id, HttpSession httpSession) {
		Member member = (Member)adminQuery.querySingle(id);
		httpSession.setAttribute("user", member);
		return "redirect:/adminMember/adminMemberUpdate.jsp";
	}
	
	// 更新會員資料:更新
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateMember(Member member, HttpSession session) {
		adminQuery.updateAll(member.getMemberID(), member);
		return "redirect:/adminMember/read";
	}

	// Delete
	// 刪除會員
	@RequestMapping("/delete")
	public String deleteMember(Integer id, HttpSession session) {
		adminQuery.delete(id);
		int page = (int)session.getAttribute("page");
		int totalPage = (int)session.getAttribute("totalPages");
		List<Member> members = adminQuery.queryByPage(page, 5);
		if(members.size()==0 && page!=1) {
			members = adminQuery.queryByPage(page-1, 5);
			session.setAttribute("page", page-1);	
			session.setAttribute("totalPage", totalPage-1);
		}
		session.setAttribute("members", members);
		return "redirect:/adminMember/adminMemberRead.jsp";
	}
}
