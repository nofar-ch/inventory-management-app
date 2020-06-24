package com.nofarProject.nofarApp.myRestApi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Value;

@Entity
public class Product {
	
	@Id
	private Integer no;
	
	private String name;
	private int amount;
	private String inventory_code;
	
	private int withdrawal; //take
	private int deposit; //put
	
	public Product() {
		
	}
	
	public Product (int no, String name, int amount, String inventory_code){
		this.no = no;
		this.name = name;
		this.amount = amount;
		this.inventory_code = inventory_code;
		this.withdrawal = amount;
		this.deposit = 0;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getInventory_code() {
		return inventory_code;
	}
	public void setInventory_code(String inventory_code) {
		this.inventory_code = inventory_code;
	}

	public int getWithdrawal() {
		return withdrawal;
	}

	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", amount=" + amount 
				+ ", inventory_code=" + inventory_code
				+ ", withdrawal=" + withdrawal + ", deposit=" + deposit + "]";
	}
	
	

	

}
