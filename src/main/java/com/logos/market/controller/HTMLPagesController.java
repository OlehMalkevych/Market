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

	@RequestMapping("/admin/shop")
	private String getAdminShopPage(){
		return "/html/admin-shop.html";
	}


}
