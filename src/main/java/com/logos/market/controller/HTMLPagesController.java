package com.logos.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class HTMLPagesController {

	@RequestMapping
	private String getMainPage() {
		return "/html/index.html";
	}

	@RequestMapping("/admin/shop")
	private String getAdminShopPage(){
		return "/html/admin-shop.html";
	}

	@RequestMapping("/admin/shop-manage")
	private String getAdminShopManagePage() {
		return "/html/admin-shop-manage.html";
	}

	@RequestMapping("/registration")
	private String getUserRegistrationPage() {
		return "/html/user-registration.html";
	}

	@RequestMapping("/login")
	private String getLoginPage() {
		return "/html/login.html";
	}

}
