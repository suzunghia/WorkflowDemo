package com.workflow.demo.controllers;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.workflow.demo.repository.CustomerRepository;

import com.workflow.demo.exception.CustomerNotFoundException;
import com.workflow.demo.exception.InvalidCustomerRequestException;
import com.workflow.demo.models.Customer;

@RestController
public class CustomerController {

	private CustomerRepository customerRepository;
	
	@Autowired
    public void setProductService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST)
	public ResponseEntity<?>  createCustomer(            
            @RequestParam(value="firstName", required=true) String firstName,
            @RequestParam(value="lastName", required=true) String lastName,
            @RequestParam(value="dateOfBirth", required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date dateOfBirth) {
        
//        	CustomerImage customerImage = fileArchiveService.saveFileToS3(image);        	
        	Customer customer = new Customer(firstName, lastName, dateOfBirth);
        	
        	customerRepository.save(customer);
            return new ResponseEntity<Customer>(customer, HttpStatus.OK);               
    }
	
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomer(@PathVariable("customerId") Long customerId) {
		
		/* validate customer Id parameter */
		if (null==customerId) {
			throw new InvalidCustomerRequestException();
		}
		
		Customer customer = customerRepository.findOne(customerId);
		
		if(null==customer){
			throw new CustomerNotFoundException();
		}
		
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	public List<Customer> getCustomers() {
		
		return (List<Customer>) customerRepository.findAll();
	}
	
	@RequestMapping(value = "/customers/{customerId}", method = RequestMethod.DELETE)
	public void removeCustomer(@PathVariable("customerId") Long customerId, HttpServletResponse httpResponse) {

		if(customerRepository.exists(customerId)){
			Customer customer = customerRepository.findOne(customerId);
			customerRepository.delete(customer);	
		}
		
		httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
	}
}
