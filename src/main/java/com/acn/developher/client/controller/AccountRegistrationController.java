package com.acn.developher.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.acn.developher.client.domain.Account;
import com.acn.developher.client.domain.Accounts;

@RestController
public class AccountRegistrationController {
	
	@Value("${account.registration.server.retrieve.url}")
	private String accountRetrieveUrl;
	
	@Value("${account.registration.server.register.url}")
	private String accountRegisterUrl;
	
	@GetMapping(path="/account/client/retrieve", produces = "application/json")
	public ResponseEntity<Accounts> getAccountRegistrations() {
	     RestTemplate restTemplate = new RestTemplate();
		 Accounts accounts = restTemplate
		    .getForObject(accountRetrieveUrl,Accounts.class);
		return ResponseEntity.ok(accounts);

	}
	
	@PostMapping(path= "/account/client/add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
		 RestTemplate restTemplate = new RestTemplate();
		 Account retrievedAccount = restTemplate
				    .postForObject(accountRegisterUrl, account,Account.class);
		return ResponseEntity.ok(retrievedAccount);

	}
}
