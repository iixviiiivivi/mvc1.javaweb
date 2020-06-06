package controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dao.memberDAO.MemberQuery;
import model.Member;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberQuery memberQuery;
	
	// 會員註冊
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String register() {
		return "redirect:/member/memberCreate.jsp";
	}

	// 會員首頁(RUD功能)
	@RequestMapping("/home")
	public String memberHome() {
		return "redirect:/member/home.jsp";
	}

	// 新增會員
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String memberAdd(Member m) {
		if(m.getAgreement()==null) return "redirect: /LoremIpsum/member/create";
		
		if (m.getAgreement().equals("yes") && !memberQuery.usernameValidation(m.getUsername())) {
			memberQuery.add(m);
			return "redirect:/index.jsp";
		} else {
			return "redirect: /LoremIpsum/member/create";
		}
	}

	// 查詢會員
	@RequestMapping("/read")
	public String memberQuery() {
		return "redirect:/member/memberReadUpdate.jsp";
	}

	// 更新會員資料
	@RequestMapping("/update")
	public String memberUpdate(Member newM, HttpSession session) {
		Member m = memberQuery.updateMember(newM.getMemberID(), newM);
		session.removeAttribute("account");
		session.setAttribute("account", m);
		return "redirect:/member/home";
	}

	// 更改會員密碼View
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public String memberUpdatePassword() {
		return "redirect:/member/memberUpdatePassword.jsp";
	}

	// 更改會員密碼
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String memberUpdatePassword(Member m, String newPassword, HttpSession session) {
		Member dbMember = (Member) session.getAttribute("account");
		if (dbMember.getPassword().equals(m.getPassword())) {
			dbMember.setPassword(newPassword);
			memberQuery.passwordUpdate(dbMember);
			session.removeAttribute("account");
			session.setAttribute("account", dbMember);
			session.setAttribute("message", "密碼更新成功");
		} else {
			session.setAttribute("message", "密碼更新失敗");
		}
		
		return "redirect:/member/memberUpdatePassword.jsp";
	}

	// 刪除會員
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String memberDelete() {
		return "redirect:/member/memberDelete.jsp";
	}

	// 刪除會員
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String memberDelete(String password, HttpSession session) {
		Member m = (Member) session.getAttribute("account");
		if (m.getPassword().equals(password)) {
			memberQuery.delete(m.getMemberID());
			session.removeAttribute("account");
			return "redirect:/index.jsp";
		}
		return "redirect:/member/memberDelete.jsp";
	}

	// 會員登入驗證畫面
	@RequestMapping(value = "/logIn", method = RequestMethod.GET)
	public String logIn() {
		return "redirect:/logIn.jsp";
	}

	// 會員登入驗證
	@RequestMapping(value = "/logIn", method = RequestMethod.POST)
	public ModelAndView logInValidation(String username, String password, HttpSession session) {
		Member m = memberQuery.logInValidation(username, password);
		if (m != null) {
			session.setAttribute("account", m);
			return new ModelAndView("redirect:/index.jsp");
		} else {
			ModelAndView mv = new ModelAndView("/logIn");
			if (!memberQuery.usernameValidation(username) && !memberQuery.passwordValidation(password))
				mv.addObject("error", "帳號名稱與密碼錯誤，請註冊會員");
			else if (!memberQuery.usernameValidation(username))
				mv.addObject("error", "帳號名稱錯誤");
			else if (!memberQuery.passwordValidation(password))
				mv.addObject("error", "密碼錯誤");
			return mv;
		}
	}

	// 會員登出
	@RequestMapping("/logOut")
	public String logOut(HttpSession session) {
		session.removeAttribute("account");
		session.removeAttribute("cart");
		return "redirect:/index.jsp";
	}

}
