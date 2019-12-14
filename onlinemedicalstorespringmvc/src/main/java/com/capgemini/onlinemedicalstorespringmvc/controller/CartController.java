package com.capgemini.onlinemedicalstorespringmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.onlinemedicalstorespringmvc.beans.CartBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;
import com.capgemini.onlinemedicalstorespringmvc.service.CartServices;

@Controller
public class CartController {
	
	@Autowired
	private CartServices cartServices;
	
	@GetMapping("/placeOrder")
	public String displayAddProductIntoCart(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			modelMap.addAttribute("msg", "please Login first");
			return "userLoginForm";
		} else {
			return "placeOrderForm";
		}
	} //End of displayAddProductIntoCart()
	
	@PostMapping("/addProductIntoCart")
	public String addProductIntoCart(int userId, int productId, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid session
			modelMap.addAttribute("msg", "please Login first");
			return "userLoginForm";
		} else {
			// valid session
			if (cartServices.addIntoCart(userId, productId)) {
				modelMap.addAttribute("msg", "Product Added Successfully Into Cart");
			} else {
				modelMap.addAttribute("msg", "Unable To Add Product Into Cart");
			}
		}
		return "placeOrderForm";
	} //End of addProductIntoCart()
	
	@GetMapping("/showAllCartProducts")
	public String showCartProducts(HttpSession session, ModelMap modelMap) {

		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "userLoginForm";

		} else {
			// Valid Session
			List<CartBean> cartList = cartServices.showAllProducts();
			modelMap.addAttribute("cartList", cartList);

			return "displayCartProducts";
		}
	}// End of showCartProducts()
	
	@GetMapping("/removeCartProductForm")
	public String displayRemoveCartProductForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			modelMap.addAttribute("msg", "please Login first");
			return "userLoginForm";
		} else {
			return "removeCartProductForm";
		}
	} //End of displayRemoveCartProductForm()
	
	@GetMapping("/removeCartProduct")
	public String removeProductFromCart(int cartId, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "userLoginForm";

		} else {
			// Valid Session
			if (cartServices.removeFromCart(cartId)) {
				modelMap.addAttribute("msg", "Product Deleted Successfully!");
			} else {
				modelMap.addAttribute("msg", "Cart ID " + cartId + " Not Found!");
			}

			return "removeCartProductForm";
		}

	} // end of removeProductFromCart()
	
	@GetMapping("/totalBill")
	public String getBill(int userId, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Add Medicine First");
			return "addMedicineToCart";
		} else {
			// Valid Session
			double price = cartServices.totalBill(userId);
			modelMap.addAttribute("msg", "Your Total Bill Is Rs :" +price);
		}
		return "logoutUser";
	}
	

}//End of Class
