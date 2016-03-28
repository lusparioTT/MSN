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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.timgise.domain.Order;
import cn.timgise.service.OrderService;

@IocBean
@At("/order")
@Component
public class OrderActionBean {

	private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";
	private static final String LIST_ORDERS = "/WEB-INF/jsp/order/ListOrders.jsp";
	private static final String NEW_ORDER = "/WEB-INF/jsp/order/NewOrderForm.jsp";
	private static final String SHIPPING = "/WEB-INF/jsp/order/ShippingForm.jsp";
	private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";

	private static final List<String> CARD_TYPE_LIST;
	
	@Autowired
	private OrderService orderService;

	private Order order = new Order();
	private boolean shippingAddressRequired;
	private boolean confirmed;
	private List<Order> orderList;

	static {
		List<String> cardList = new ArrayList<String>();
		cardList.add("Visa");
		cardList.add("MasterCard");
		cardList.add("American Express");
		CARD_TYPE_LIST = Collections.unmodifiableList(cardList);
	}

	public int getOrderId() {
		return order.getOrderId();
	}

	public void setOrderId(int orderId) {
		order.setOrderId(orderId);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public boolean isShippingAddressRequired() {
		return shippingAddressRequired;
	}

	public void setShippingAddressRequired(boolean shippingAddressRequired) {
		this.shippingAddressRequired = shippingAddressRequired;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public List<String> getCreditCardTypes() {
		return CARD_TYPE_LIST;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void clear() {
		order = new Order();
		shippingAddressRequired = false;
		confirmed = false;
		orderList = null;
	}

}
