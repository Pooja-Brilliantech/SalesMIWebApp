package com.fieldmi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fieldmi.CustomerImportRequest;
import com.fieldmi.service.FMCustomerService;
import com.softtantra.salesapp.pojo.CustomerImportRecords;

@RestController
public class CustomerApiController {

	@Autowired
	private FMCustomerService fmCustomerService;

	@RequestMapping(value = "importCustomers", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity importCustomers(@RequestBody CustomerImportRequest customerImportRequest) throws Exception {

		if (customerImportRequest.getCustomersToImport() != null
				&& customerImportRequest.getCustomersToImport().size() <= 9999) {

			List<CustomerImportRecords> importList = fmCustomerService
					.importCustomers(customerImportRequest.getCustomersToImport());

			return new ResponseEntity<List<CustomerImportRecords>>(importList, HttpStatus.OK);
		} else {

			return new ResponseEntity<String>(
					"Customer Import failed due to invalid request OR Check the request format or size of customers to import is less than 20",
					HttpStatus.BAD_REQUEST);
		}
	}

}
