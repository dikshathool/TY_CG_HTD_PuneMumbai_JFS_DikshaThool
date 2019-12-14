package com.capgemini.onlinemedicalstorespringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinemedicalstorespringboot.beans.MedicalResponses;
import com.capgemini.onlinemedicalstorespringboot.beans.OrderHistoryBean;
import com.capgemini.onlinemedicalstorespringboot.beans.PaymentDetails;
import com.capgemini.onlinemedicalstorespringboot.services.OrderServices;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
	
	@Autowired
	private OrderServices orderServices;
	
	@PostMapping(path = "/paymentDetails")
	public MedicalResponses payment(@RequestBody PaymentDetails paymentDetails) {
		boolean payment = orderServices.payment(paymentDetails.getUserId(), paymentDetails.getAddress());
		MedicalResponses response = new MedicalResponses();
		if (payment) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setPrice(paymentDetails.getPrice());
			response.setDescription("Payment Done.......");
		} else if (payment) {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setPrice(paymentDetails.getPrice());
			response.setDescription("payment Failed........");
		}
		return response;
	}// End of payment()
	
	@GetMapping(path = "/showOrder/{userId}")
	public MedicalResponses showOrder(@PathVariable("userId") int userId) {
		List<OrderHistoryBean> orderList = orderServices.showOrder(userId);
		MedicalResponses response = new MedicalResponses();

		if (orderList != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Order History Retrived.......");
			response.setOrderList(orderList);
		} else {
			response.setStatusCode(401);
			response.setMessage("Failed");
			response.setDescription("Failed to retrive........");
		}

		return response;
	}// end of viewOrder()
	
}//End of class
