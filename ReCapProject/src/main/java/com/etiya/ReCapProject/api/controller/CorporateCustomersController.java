package com.etiya.ReCapProject.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etiya.ReCapProject.business.abstracts.CorporateCustomerService;
import com.etiya.ReCapProject.core.utilities.result.DataResult;
import com.etiya.ReCapProject.core.utilities.result.Result;
import com.etiya.ReCapProject.entities.concretes.CorporateCustomer;
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
	
	@GetMapping("/getAll")
	public DataResult<List<CorporateCustomer>> getAll() {
		return this.corporateCustomerService.getAll();
	}
	
	@GetMapping("/getbyid")
	public DataResult<CorporateCustomer> getById(int customerId) {
		return this.corporateCustomerService.getById(customerId);
	}
	
//	@PostMapping("/add")
//    public Result add(@Valid @RequestBody CreateCorporateCustomerRequest createCorporateCustomerRequest) {
//        return this.corporateCustomerService.add(createCorporateCustomerRequest);
//    }
	
	@PostMapping("/update")
	public Result update(@Valid @RequestBody UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
	}
	
	@PostMapping("/delete")
	public Result delte(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
	}
}
