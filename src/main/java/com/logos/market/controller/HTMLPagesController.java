package com.logos.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HTMLPagesController {

	@RequestMapping
	private String getMainPage() {
		return "/html/index.html";
	}

	@RequestMapping("/shopItems/{id}")
	private String getShopProductPage(){
		return "/html/shop-product-page.html";
	}

	@RequestMapping("/admin/shop")
	private String getAdminShopPage(){
		return "/html/admin/admin-shop.html";
	}

	@RequestMapping("/admin/shop-manage")
	private String getAdminShopManagePage() {
		return "/html/admin/admin-shop-manage.html";
	}

	@RequestMapping("/authorization")
	private String getUserRegistrationPage() {
		return "/html/authorization.html";
	}
}
