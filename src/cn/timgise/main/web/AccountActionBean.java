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

import cn.timgise.domain.Account;
import cn.timgise.domain.Product;
import cn.timgise.service.AccountService;
import cn.timgise.service.CatalogService;

@IocBean
@At("/account")
@Component
public class AccountActionBean {

	private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/NewAccountForm.jsp";
	private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
	private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";

	private static final List<String> LANGUAGE_LIST;
	private static final List<String> CATEGORY_LIST;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CatalogService catalogService;

	private Account account = new Account();
	private List<Product> myList;
	private boolean authenticated;

	static {
		List<String> langList = new ArrayList<String>();
		langList.add("english");
		langList.add("japanese");
		LANGUAGE_LIST = Collections.unmodifiableList(langList);

		List<String> catList = new ArrayList<String>();
		catList.add("FISH");
		catList.add("DOGS");
		catList.add("REPTILES");
		catList.add("CATS");
		catList.add("BIRDS");
		CATEGORY_LIST = Collections.unmodifiableList(catList);
	}

	public Account getAccount() {
		return this.account;
	}

	public String getUsername() {
		return account.getUsername();
	}

	public void setUsername(String username) {
		account.setUsername(username);
	}

	public String getPassword() {
		return account.getPassword();
	}

	public void setPassword(String password) {
		account.setPassword(password);
	}

	public List<Product> getMyList() {
		return myList;
	}

	public void setMyList(List<Product> myList) {
		this.myList = myList;
	}

	public List<String> getLanguages() {
		return LANGUAGE_LIST;
	}

	public List<String> getCategories() {
		return CATEGORY_LIST;
	}

	public boolean isAuthenticated() {
		return authenticated && account != null
				&& account.getUsername() != null;
	}

	public void clear() {
		account = new Account();
		myList = null;
		authenticated = false;
	}

}
