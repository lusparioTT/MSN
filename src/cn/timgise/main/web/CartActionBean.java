/**
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.timgise.main.web;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.timgise.domain.Cart;
import cn.timgise.service.CatalogService;

@At("/cart")
@IocBean
@Component
public class CartActionBean {

	private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
	private static final String CHECK_OUT = "/WEB-INF/jsp/cart/Checkout.jsp";

	@Autowired
	private CatalogService catalogService;

	private Cart cart = new Cart();
	private String workingItemId;

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public void setWorkingItemId(String workingItemId) {
		this.workingItemId = workingItemId;
	}

	public void clear() {
		cart = new Cart();
		workingItemId = null;
	}

}
