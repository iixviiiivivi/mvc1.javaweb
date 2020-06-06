package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "productDetail")
@DynamicInsert
@DynamicUpdate
public class ProductDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productDetailID;
	@ManyToOne
	@JoinColumn(name = "productID", referencedColumnName = "productID")
	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "companyID", referencedColumnName = "companyID")
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	// private Integer productID;
//	private Integer companyID;
	private byte[] image;

	public Integer getProductDetailID() {
		return productDetailID;
	}

	public void setProductDetailID(Integer productDetailID) {
		this.productDetailID = productDetailID;
	}
//	public Integer getProductID() {
//		return productID;
//	}
//	public void setProductID(Integer productID) {
//		this.productID = productID;
//	}
//	public Integer getCompanyID() {
//		return companyID;
//	}
//	public void setCompanyID(Integer companyID) {
//		this.companyID = companyID;
//	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
}
