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

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.timgise.domain.Category;
import cn.timgise.domain.Item;
import cn.timgise.domain.Product;
import cn.timgise.service.CatalogService;

@At("/catalog")
@IocBean
@Component
public class CatalogActionBean {

	private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
	private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";
	private static final String VIEW_PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
	private static final String VIEW_ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
	private static final String SEARCH_PRODUCTS = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

	@Autowired
	private CatalogService catalogService;

	private String keyword;

	private String categoryId;
	private Category category;
	private List<Category> categoryList;

	private String productId;
	private Product product;
	private List<Product> productList;

	private String itemId;
	private Item item;
	private List<Item> itemList;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public void clear() {
		keyword = null;

		categoryId = null;
		category = null;
		categoryList = null;

		productId = null;
		product = null;
		productList = null;

		itemId = null;
		item = null;
		itemList = null;
	}

}
