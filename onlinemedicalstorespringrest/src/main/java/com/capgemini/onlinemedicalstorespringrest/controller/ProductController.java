package com.capgemini.onlinemedicalstorespringrest.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemedicalstorespringrest.beans.MedicalResponses;
import com.capgemini.onlinemedicalstorespringrest.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringrest.beans.UserBean;
import com.capgemini.onlinemedicalstorespringrest.services.ProductServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {
	
	@Autowired
	private ProductServices productServices;
	
	@PutMapping(path = "/addProduct", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses addProduct(@RequestBody ProductBean productBean) {
		boolean isAdded = productServices.addProduct(productBean);
		MedicalResponses response=new MedicalResponses();
		if (isAdded) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product Added Successfully.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Add Product........");
		}
		return response;
	}//End of addProduct()
	
	@PostMapping(path = "/updateProduct",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses updateProduct(@RequestBody ProductBean productBean) {
		boolean isUpdated = productServices.updateProduct(productBean);
		MedicalResponses response = new MedicalResponses();
		if (isUpdated) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product updated Successfully.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Update Product........");
		}
		return response;
	}//End of updateProduct()
	
	@DeleteMapping(path = "/removeProduct", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses removeProduct(@RequestParam int productId) {
		boolean isDeleted = productServices.removeProduct(productId);
		MedicalResponses response = new MedicalResponses();
		if (isDeleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product deleted Successfully.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Delete Product........");
		}
		return response;
	}//End of removeProduct()
	
	@GetMapping(path = "/showAllProducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses showAllProducts() {
		List<ProductBean> productBeans = productServices.showAllProducts();
		MedicalResponses response = new MedicalResponses();
		if (productBeans != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Products List Found........");
			response.setProductList(productBeans);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Products List Not Found........");
		}
		return response;
	}//End of showAllProducts()
	
	@GetMapping(path = "/showProduct", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public MedicalResponses showProduct(@RequestParam String category) {
		List<ProductBean> productBeans = productServices.categoryProducts(category);
		MedicalResponses response = new MedicalResponses();
		if (productBeans != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Products List Found........");
			response.setProductList(productBeans);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Products List Not Found........");
		}
		return response;

	}// End of getEmployees()
	
	
}//End of Controller Class
