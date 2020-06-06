package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import dao.companyDAO.CompanyQuery;
import dao.productDAO.ProductQuery;
import dao.productDetailDAO.ProductDetailQuery;
import model.Company;
import model.Product;
import model.ProductDetail;

@Controller
@RequestMapping("/adminProductDetail")
public class AdminProductDetail {
	
	@Autowired
	private ProductDetailQuery productDetailQuery;
	@Autowired
	private ProductQuery productQuery;
	@Autowired
	private CompanyQuery companyQuery;
	
	// read
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public String readProductDetailPagination(Integer page, HttpSession session) {
		if(page == null) page = 1;
		List<ProductDetail> productDetails = productDetailQuery.getProductdDetailsPagination(page, 5);
		int totalPages = productDetailQuery.totalProductDetailPages(5);
		session.setAttribute("productDetails", productDetails);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("page", page);
		
		return "redirect:/adminProductDetail/adminProductDetailRead.jsp";
	}
	
	// create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createProductDetail(HttpSession session) {
		List<Product> products = productQuery.getProducts();
		List<Company> companies = companyQuery.queryAllCompanies();
		session.setAttribute("products", products);
		session.setAttribute("companies", companies);
		return "redirect:/adminProductDetail/adminProductDetailCreate.jsp";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createProductDetail(ProductDetail productDetail, MultipartFile img) throws IOException {
		productDetail.setImage(img.getBytes());
		productDetailQuery.add(productDetail);
		return "redirect:/adminProduct/home";
	}
	
	// update
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateProductDetail(Integer id, HttpSession session) {
		ProductDetail productDetail = (ProductDetail)productDetailQuery.querySingle(id);
		List<Product> products = productQuery.getProducts();
		List<Company> companies = companyQuery.queryAllCompanies();
		session.setAttribute("productDetail", productDetail);
		session.setAttribute("products", products);
		session.setAttribute("companies", companies);
		return "redirect:/adminProductDetail/adminProductDetailUpdate.jsp";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateProductDetail(ProductDetail productDetail, MultipartFile img) throws IOException {
		productDetail.setImage(img.getBytes());
		productDetailQuery.updateAll(productDetail.getProductDetailID(), productDetail);
		return "redirect:/adminProductDetail/read";
	}

}
