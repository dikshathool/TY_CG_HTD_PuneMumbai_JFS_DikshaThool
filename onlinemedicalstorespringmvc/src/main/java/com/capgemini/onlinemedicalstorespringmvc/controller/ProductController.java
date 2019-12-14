package com.capgemini.onlinemedicalstorespringmvc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.capgemini.onlinemedicalstorespringmvc.beans.ProductBean;
import com.capgemini.onlinemedicalstorespringmvc.beans.UserBean;
import com.capgemini.onlinemedicalstorespringmvc.service.ProductServices;

@Controller
public class ProductController {
	@Autowired
	private ProductServices productServices;
	
	@GetMapping("/productOperationPage")
	public String displayProductOperationPage(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			//Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";
		} else {
			//Valid Session
			return "productOperationPage";
		}
	}// End of displayProductOperationPage()
	
	@GetMapping("/showAllProducts")
	public String getAllProducts(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			List<ProductBean> productsList = productServices.showAllProducts();
			modelMap.addAttribute("productsList", productsList);

			return "displayAllProducts";
		}
	}// End of getAllProducts()
	
	@GetMapping("/categoryProducts")
	public String displaySearchCategoryProductsForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";
		
		} else {
			// Valid Session
			return "searchCategoryProducts";
		}
	}// End of displaySearchCategoryProductsForm()
	
	@GetMapping("/searchCategoryProducts")
	public String searchCategoryProduct(String category, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";
		
		} else {
			List<ProductBean> productList = productServices.categoryProducts(category);
			if (productList != null) {
				modelMap.addAttribute("productList", productList);
			} else {
				modelMap.addAttribute("msg", "Product Category " + category + " Not Found!!!");
			}
			
			return "searchCategoryProducts";
		}
	}// End of searchCategoryProduct()
	
	@GetMapping("/addNewProduct")
	public String displayAddProductForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			modelMap.addAttribute("msg", "please Login first");
			return "adminLoginForm";
		} else {
			return "addProductForm";
		}
	}// End of displayAddProductForm()
	
	@PostMapping("/addProduct")
	public String addProduct(ProductBean productBean, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// inavlid session
			modelMap.addAttribute("msg", "please Login first");
			return "adminLoginForm";
		} else {
			// valid session
			if (productServices.addProduct(productBean)) {
				modelMap.addAttribute("msg", "Product Added Successfully");
			} else {
				modelMap.addAttribute("msg", "Unable To Add Product");
			}
		}
		return "addProductForm";
	}// end of addProduct()
	
	@GetMapping("/removeProductForm")
	public String displayProductRemoveForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			//Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";
		} else {
			//Valid Session
			return "removeProductForm";
		}
	}// End of displayProductRemoveForm()
	
	@PostMapping("/removeProduct")
	public String removeProduct(int productId, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";

		} else {
			// Valid Session
			if (productServices.removeProduct(productId)) {
				modelMap.addAttribute("msg", "Product Deleted Successfully!");
			} else {
				modelMap.addAttribute("msg", "Product ID " + productId + " Not Found!");
			}

			return "removeProductForm";
		}
	} // end of removeProduct()
	
	@GetMapping("/updateProductForm")
	public String displayUpdateProductForm(HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			//Invalid Session
			modelMap.addAttribute("msg", "Please Login First");
			return "adminLoginForm";
		} else {
			//Valid Session
			return "updateProductForm";
		}
	}// End of displayUpdateProductForm()
	
	@PostMapping("/updateProduct")
	public String updateProduct(ProductBean productBean, HttpSession session, ModelMap modelMap) {
		if (session.isNew()) {
			// invalid session
			modelMap.addAttribute("msg", "please Login first");
			return "adminLoginForm";
		} else {
			// valid session
			if (productServices.updateProduct(productBean)) {
				modelMap.addAttribute("msg", "Product updated Sucsessfully!!!!!");
			} else {
				modelMap.addAttribute("msg", " unable to update Product");
			}
		}
		return "updateProductForm";
	} // end of updateMedicine()

	
}//End of controller
