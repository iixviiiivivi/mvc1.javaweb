package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.productDetailDAO.ProductDetailQuery;
import model.Cart;
import model.ProductDetail;
import model.Stock;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ProductDetailQuery productDetailQuery;
	
	// 購物首頁畫面
	@RequestMapping("/home")
	public String home(Integer page, HttpSession session) {
		if(page==null) page=1;
		List<ProductDetail> productDetails = productDetailQuery.getProductdDetailsPagination(page, 6);
		int totalPages = new ProductDetailQuery().totalProductDetailPages(6);
		session.setAttribute("productDetails", productDetails);
		session.setAttribute("page", page);
		session.setAttribute("totalPages", totalPages);
		return "redirect:/shop/home.jsp";
	}
	
	// 顯示被選擇商品的畫面
	@RequestMapping("/product")
	public String product(Integer id, HttpSession session) {
		ProductDetail productDetail = (ProductDetail) productDetailQuery.querySingle(id);
		List<Stock> stocks = productDetailQuery.querySelectedProduct(id);
		session.setAttribute("productDetail", productDetail);
		session.setAttribute("stocks", stocks);
		return "redirect:/shop/shopProduct.jsp";
	}

	// 加入購物車
	@SuppressWarnings("unchecked")
	@RequestMapping("/cart")
	public String addCart(Cart cart, HttpSession session) {
		Map<String, Cart> map = null;
		if(session.getAttribute("cart") == null) {
			map = new HashMap<String, Cart>();
			map.put(cart.getId()+cart.getSize(), cart);
			session.setAttribute("cart", map);
		}else {
			map = (Map<String, Cart>)session.getAttribute("cart");
			if(map.containsKey(cart.getId()+cart.getSize())) {
				Cart c = map.get(cart.getId()+cart.getSize());
				c.setQuantity(c.getQuantity() + cart.getQuantity());
				map.replace(cart.getId()+cart.getSize(), c);
			}else {
				map.put(cart.getId()+cart.getSize(), cart);
			}
			session.setAttribute("cart", map);
		}
		return "redirect:/shop/product?id="+cart.getId();
	}
	
	// 點選商品尺寸時，回傳被點選商品尺寸的價格、稅與折扣
	// 回傳格式是JSON
	@RequestMapping(value = "/price")
	@ResponseBody
	public String getPirceTaxDiscount(Integer id, String size) {
		Stock stock = productDetailQuery.querySinglePriceTaxDiscount(id, size);
		ObjectMapper mapper = new ObjectMapper();
		String jsonsString = null;
		
		try {
			jsonsString = mapper.writeValueAsString(stock);
		} catch (JsonProcessingException e) {
			System.out.println("物件轉JSON失敗");
			e.printStackTrace();
		}
		return jsonsString;
	}
	
	// 購物車畫面
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpSession session) {		
		if(session.getAttribute("account")==null)
			return "redirect:/logIn.jsp";
		else
			return "redirect:/shop/cart.jsp";
	}
	
	// 取消購物車訂單
	@SuppressWarnings("unchecked")
	@RequestMapping("/cancel")
	public String cancelOrder(String pid, String size, HttpSession session) {
		Map<String, Cart> carts = (Map<String, Cart>)session.getAttribute("cart");
		String key = pid + size;
		carts.remove(key);
		session.setAttribute("cart", carts);
		return "redirect:/shop/create";
	}
	
}
