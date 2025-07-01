package com.fieldmi.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fieldmi.FieldMILogger;
import com.fieldmi.ProductImportRequest;
import com.fieldmi.SalesOrderExportRequest;
import com.fieldmi.service.FMProductService;
import com.fieldmi.service.FMSalesMasterService;
import com.fieldmi.stubs.WsProductDetails;
import com.softtantra.salesapp.pojo.NewPriceMaster;
import com.softtantra.salesapp.pojo.ProductDetails;
import com.softtantra.salesapp.pojo.Tax;

@RestController
public class SalesOrderApiController {

	@Autowired
	private FMSalesMasterService fmSalesMasterService;

	@RequestMapping(value = "exportSalesOrder", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity exportSalesOrder(@RequestBody SalesOrderExportRequest salesOrderExportRequest)
			throws Exception {

		if (salesOrderExportRequest != null && salesOrderExportRequest.getFromDate() != null
				&& salesOrderExportRequest.getToDate() != null) {

			List<String> importMessagesList = new ArrayList<String>();
			try {

				fmSalesMasterService.exportSalesData(salesOrderExportRequest.getCompany_id(),
						salesOrderExportRequest.getU_id(), salesOrderExportRequest.getFromDate(),
						salesOrderExportRequest.getToDate());

			} catch (Exception e) {

				FieldMILogger.error(this.getClass().getName(), e);
			}

			return new ResponseEntity<List<String>>(importMessagesList, HttpStatus.OK);
		} else {

			return new ResponseEntity<String>(
					"Product Import failed due to invalid request OR Check the request format or size of customers to import is less than 20",
					HttpStatus.BAD_REQUEST);
		}
	}

}
