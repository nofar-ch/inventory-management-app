package com.nofarProject.nofarApp.myRestApi.services;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import com.nofarProject.nofarApp.myRestApi.entities.Product;

public interface ProductJPARepository extends JpaRepository<Product, Integer>{

	List <Product> findByOrderByNameAsc();
	
}
