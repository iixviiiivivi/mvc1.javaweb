package controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.productDAO.ProductQuery;
import model.Product;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {

	@Autowired
	private ProductQuery productQuery;
	
	// home page
	@RequestMapping("/home")
	public String home() {
		return "redirect:/adminProduct/home.jsp";
	}
	
	// create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String saveProduct() {
		
		return "redirect:/adminProduct/adminAccountingCreate.jsp";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveProduct(Product product) {
		productQuery.add(product);
		return "redirect:/adminProduct/home";
	}
	
	// read
	@RequestMapping("/read")
	public String getProducts(Integer page, HttpSession session) {
		if(page == null) page = 1;
		List<Product> products = productQuery.getProductsPagination(page, 5);
		int totalPage = productQuery.getTotalProductPage();
		session.setAttribute("products", products);
		session.setAttribute("totalPages", totalPage);
		session.setAttribute("page", page);
		
		return "redirect:/adminProduct/adminProductRead.jsp";
	}
	
	// update
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateProduct(int id, HttpSession session) {
		Product product = (Product)productQuery.querySingle(id);
		session.setAttribute("product", product);
		
		return "redirect:/adminProduct/adminProductUpdate.jsp";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProduct(Product product, HttpSession session) {
		productQuery.updateAll(product.getProductID(), product);
		return "redirect:/adminProduct/read";
	}
		
}
