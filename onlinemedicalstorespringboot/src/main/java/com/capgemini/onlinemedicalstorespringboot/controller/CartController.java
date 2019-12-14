package com.capgemini.onlinemedicalstorespringboot.controller;

import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemedicalstorespringboot.beans.CartBean;
import com.capgemini.onlinemedicalstorespringboot.beans.MedicalResponses;
import com.capgemini.onlinemedicalstorespringboot.services.CartServices;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CartController {

	@Autowired
	private CartServices cartServices;

	@PutMapping(path = "/cartAddProduct")
	public MedicalResponses cartProduct(@RequestBody CartBean cartBean) {
		boolean isAdded = cartServices.addIntoCart(cartBean.getUserId(), cartBean.getProductId());
		MedicalResponses response = new MedicalResponses();
		if (isAdded) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product Added to cart.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Add Product........");
		}
		return response;
	}// End of cartProduct()

	@PostMapping(path = "/removeCartProduct")
	public MedicalResponses removeCartProduct(@RequestBody CartBean cartBean) {
		boolean isDeleted = cartServices.removeFromCart(cartBean.getUserId(), cartBean.getCartId());
		MedicalResponses response = new MedicalResponses();
		if (isDeleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product deleted from cart.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Delete Product From Cart........");
		}
		return response;
	}// End of cartDelete()

	@GetMapping(path = "/showCart/{userId}")
	public MedicalResponses showCart(@PathVariable("userId") int userId) {
		List<CartBean> cartList = cartServices.showAllProducts(userId);
		MedicalResponses response = new MedicalResponses();
		if (cartList != null) {

			 response.setStatusCode(201); 
			 response.setMessage("Success"); 
			response.setDescription("Cart Data Retriverd.......");
			response.setCartList(cartList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("payment Failed........");
		}
		return response;
	}// end of viewCart()

	@GetMapping(path = "/totalBill", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses totalBill(@RequestParam int userId) {
		double bill = cartServices.totalBill(userId);

		MedicalResponses response = new MedicalResponses();
		if (bill > 0.0) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setPrice(bill);
			response.setDescription("Payment Done.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setPrice(bill);
			response.setDescription("payment Failed........");
		}
		return response;
	}// End of totalBill()

	@DeleteMapping(path = "/deleteCart", produces = MediaType.APPLICATION_JSON_VALUE)
	public MedicalResponses deleteCart(@RequestParam int userId) {
		boolean isDeleted = cartServices.deleteCart(userId);
		MedicalResponses response = new MedicalResponses();
		if (isDeleted) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Cart deleted Successfully.......");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Delete Cart........");
		}
		return response;
	}// End of deleteCart()
	
	@GetMapping(path = "/totalBill/{userId}") 
	public MedicalResponses showBill(@PathVariable("userId") int userId) {
		double bill = cartServices.totalBill(userId);
		MedicalResponses response = new MedicalResponses();
		if(bill > 0) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setTotalBill(bill);
			response.setDescription("Total Bill Calculated....");
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Unable To Calculate Total Bill....");
		}
		return null;
	}//End of showBill()


}// End of Controller Class
