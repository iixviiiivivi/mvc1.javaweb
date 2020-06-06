package factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import dao.accountingDAO.AccountingQuery;
import dao.companyDAO.CompanyQuery;
import dao.memberDAO.AdminQuery;
import dao.memberDAO.MemberQuery;
import dao.orderDAO.OrderQuery;
import dao.productDAO.ProductQuery;
import dao.productDetailDAO.ProductDetailQuery;
import dao.purchaseDAO.PurchaseQuery;
import model.Accounting;
import model.Cart;
import model.Company;
import model.Member;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductDetail;
import model.Purchase;
import model.Stock;
import model.Warehouse;

@Component
public class Factory {
	
	@Autowired
	private Accounting accounting;
	@Autowired
	private Cart cart;
	@Autowired
	private Company company;
	@Autowired
	private Member member;
	@Autowired
	private Order order;
	@Autowired
	private OrderDetail orderDetail;
	@Autowired
	private Product product;
	@Autowired
	private ProductDetail productDetail;
	@Autowired
	private Purchase purchase;
	@Autowired
	private Stock stock;
	@Autowired
	private Warehouse warehouse;
	
	@Autowired
	private AccountingQuery accountingQuery;
	@Autowired
	private CompanyQuery companyQuery;
	@Autowired
	private AdminQuery adminQuery;
	@Autowired
	private MemberQuery memberQuery;
	@Autowired
	private OrderQuery orderQuery;
	@Autowired
	private ProductQuery productQuery;
	@Autowired
	private ProductDetailQuery productDetailQuery;
	@Autowired
	private PurchaseQuery purchaseQuery;
	
	public Accounting getAccounting() {
		return accounting;
	}
	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductDetail getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(ProductDetail productDetail) {
		this.productDetail = productDetail;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public Warehouse getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	public AccountingQuery getAccountingQuery() {
		return accountingQuery;
	}
	public void setAccountingQuery(AccountingQuery accountingQuery) {
		this.accountingQuery = accountingQuery;
	}
	public CompanyQuery getCompanyQuery() {
		return companyQuery;
	}
	public void setCompanyQuery(CompanyQuery companyQuery) {
		this.companyQuery = companyQuery;
	}
	public AdminQuery getAdminQuery() {
		return adminQuery;
	}
	public void setAdminQuery(AdminQuery adminQuery) {
		this.adminQuery = adminQuery;
	}
	public MemberQuery getMemberQuery() {
		return memberQuery;
	}
	public void setMemberQuery(MemberQuery memberQuery) {
		this.memberQuery = memberQuery;
	}
	public OrderQuery getOrderQuery() {
		return orderQuery;
	}
	public void setOrderQuery(OrderQuery orderQuery) {
		this.orderQuery = orderQuery;
	}
	public ProductQuery getProductQuery() {
		return productQuery;
	}
	public void setProductQuery(ProductQuery productQuery) {
		this.productQuery = productQuery;
	}
	public ProductDetailQuery getProductDetailQuery() {
		return productDetailQuery;
	}
	public void setProductDetailQuery(ProductDetailQuery productDetailQuery) {
		this.productDetailQuery = productDetailQuery;
	}
	public PurchaseQuery getPurchaseQuery() {
		return purchaseQuery;
	}
	public void setPurchaseQuery(PurchaseQuery purchaseQuery) {
		this.purchaseQuery = purchaseQuery;
	}
	
}
