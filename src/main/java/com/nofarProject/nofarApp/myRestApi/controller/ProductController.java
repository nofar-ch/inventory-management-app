package com.nofarProject.nofarApp.myRestApi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Optional;
import com.nofarProject.nofarApp.myRestApi.entities.Product;
import com.nofarProject.nofarApp.myRestApi.services.ProductJPARepository;


@RestController
public class ProductController  {

	@Autowired
	private ProductJPARepository product_service;

	@GetMapping("products")
	public List<Product> getProducts() {
		return product_service.findAll();
	}
	@GetMapping("products/{id}")
	public Product getProductById(@PathVariable int id) {
		return product_service.findById(id).get();
	}
	@GetMapping("products/Withdrawal/{id}")
	public int getProductWithdrawal(@PathVariable int id) {
		return product_service.findById(id).get().getWithdrawal();
	}
	@GetMapping("products/Deposit/{id}")
	public int getProductDeposit(@PathVariable int id) {
		return product_service.findById(id).get().getDeposit();
	}

	@DeleteMapping("products/{id}") 
	public String deleteProductById(@PathVariable int id) {
		Product product = product_service.findById(id).get();
		if (product == null)
			return "this product is not exist";
		else 
		{
			product_service.deleteById(id);
			return "OK";
		}
	}

	@DeleteMapping("products/{id}/{amount}") 
	public String deleteAmountProduct(@PathVariable int id, int amount) {
		Product product = product_service.getOne(id);
		if(product != null && amount > 0) {
			int oldAmount = product.getAmount();
			if (oldAmount - amount >= 0) {
				product.setAmount(oldAmount-amount);
				product.setDeposit(product.getDeposit()+amount);
				product_service.save(product);
				return "OK";
			}
			else // oldAmount < amount
				return "ERROR - oldAmount less from amount";
		}
		else 
			return "ERROR - product not exist or amount < 0";
	}
		@PostMapping("products") 
		public String addNewProduct(@RequestBody Product newProduct) {
			if(newProduct != null) {
				product_service.save(newProduct);
				return "OK";
			}
			else 
				return "ERROR-product is null";	
		}

		@PostMapping("products/{id}/{amount}") 
		public String addAmountProduct(@PathVariable int id, int amount) {
			Product product = product_service.getOne(id);
			if(product != null && amount > 0) {
				int oldAmount = product.getAmount();
				product.setAmount(oldAmount+amount);
				
				product.setWithdrawal(product.getWithdrawal()+amount);
				product_service.save(product);
				return "OK";
			}
			else 
				return "ERROR - product is null or amount < 0";


		}
		@GetMapping("products/sort/{sortBy}")
		public List <Product> orderBy(@PathVariable String sortBy) {
			List<Product> sortedList = product_service.findAll(Sort.by(Sort.Direction.ASC, sortBy));
			return sortedList;
		}
	}

