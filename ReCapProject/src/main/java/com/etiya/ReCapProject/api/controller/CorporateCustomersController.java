package com.etiya.ReCapProject.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.dtos.CorporateCustomerDetailDto;
import com.etiya.ReCapProject.entities.requests.delete.DeleteCorporateCustomerRequest;
import com.etiya.ReCapProject.entities.requests.update.UpdateCorporateCustomerRequest;

@RestController
@RequestMapping("api/corporateCustomers")
public class CorporateCustomersController {
	private CorporateCustomerService corporateCustomerService;

	@Autowired
	public CorporateCustomersController(CorporateCustomerService corporateCustomerService) {
		super();
		this.corporateCustomerService = corporateCustomerService;
	}

//	@GetMapping("/getAll")
//	public DataResult<List<CorporateCustomer>> getAll() {
//		return this.corporateCustomerService.getAll();
//	}
//
//	@GetMapping("/getbyid")
//	public DataResult<CorporateCustomer> getById(@RequestParam("customerId") int customerId) {
//		return this.corporateCustomerService.getById(customerId);
//	}

	@GetMapping("/getCorporateCustomerDetailsById")
	public DataResult<CorporateCustomerDetailDto> getCorporateCustomerDetailsById(
			@RequestParam("corporateCustomerId") int corporateCustomerId) {
		return this.corporateCustomerService.getCorporateCustomerDetailsById(corporateCustomerId);
	}

	@PutMapping("/update")
	public Result update(@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}

	@DeleteMapping("/delete")
	public Result delete(@Valid DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
}
