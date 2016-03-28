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
package cn.timgise.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import cn.timgise.domain.Item;
import cn.timgise.domain.LineItem;
import cn.timgise.domain.Order;
import cn.timgise.domain.Sequence;
import cn.timgise.persistence.ItemMapper;
import cn.timgise.persistence.LineItemMapper;
import cn.timgise.persistence.OrderMapper;
import cn.timgise.persistence.SequenceMapper;

/**
 * @author Eduardo Macarron
 * 
 */
@Service
public class OrderService {

	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private SequenceMapper sequenceMapper;
	@Autowired
	private LineItemMapper lineItemMapper;

	public void insertOrder(Order order) {
		order.setOrderId(getNextId("ordernum"));
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			String itemId = lineItem.getItemId();
			Integer increment = new Integer(lineItem.getQuantity());
			Map<String, Object> param = new HashMap<String, Object>(2);
			param.put("itemId", itemId);
			param.put("increment", increment);
			itemMapper.updateInventoryQuantity(param);
		}

		orderMapper.insertOrder(order);
		orderMapper.insertOrderStatus(order);
		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			lineItem.setOrderId(order.getOrderId());
			lineItemMapper.insertLineItem(lineItem);
		}
	}

	public Order getOrder(int orderId) {
		Order order = orderMapper.getOrder(orderId);
		order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));

		for (int i = 0; i < order.getLineItems().size(); i++) {
			LineItem lineItem = (LineItem) order.getLineItems().get(i);
			Item item = itemMapper.getItem(lineItem.getItemId());
			item.setQuantity(itemMapper.getInventoryQuantity(lineItem
					.getItemId()));
			lineItem.setItem(item);
		}

		return order;
	}

	public List<Order> getOrdersByUsername(String username) {
		return orderMapper.getOrdersByUsername(username);
	}

	public int getNextId(String name) {
		Sequence sequence = new Sequence(name, -1);
		sequence = (Sequence) sequenceMapper.getSequence(sequence);
		if (sequence == null) {
			throw new RuntimeException(
					"Error: A null sequence was returned from the database (could not get next "
							+ name + " sequence).");
		}
		Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
		sequenceMapper.updateSequence(parameterObject);
		return sequence.getNextId();
	}

}
