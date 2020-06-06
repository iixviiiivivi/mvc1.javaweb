package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "accounting")
@DynamicInsert
@DynamicUpdate
public class Accounting {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountingID;
	private String date;
	private Integer orderID;
	private Integer purchaseID;
	private Integer cost;
	private Integer profit;
	
	public Integer getAccountingID() {
		return accountingID;
	}
	public void setAccountingID(Integer accountingID) {
		this.accountingID = accountingID;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(Integer purchaseID) {
		this.purchaseID = purchaseID;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
}
