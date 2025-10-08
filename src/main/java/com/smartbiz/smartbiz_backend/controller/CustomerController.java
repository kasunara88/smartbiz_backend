package com.smartbiz.smartbiz_backend.controller;

import com.smartbiz.smartbiz_backend.dto.CustomerDTO;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/smartbiz/customers")
@CrossOrigin
public class CustomerController {
    final private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerDTO customers){
        CustomerDTO customerDTO = customerService.addCustomer(customers);
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id){
        CustomerDTO getCustomerById = customerService.getCustomerById(id);
        return  new ResponseEntity<>(getCustomerById, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO){
        customerDTO.setId(id);
        CustomerDTO update = customerService.updateCustomer(customerDTO);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable Integer id){
        CustomerDTO deleteVehicle = customerService.deleteCustomer(id);
        return  new ResponseEntity<>(deleteVehicle, HttpStatus.OK);
    }

    @GetMapping("/search")
public ResponseEntity<List<CustomerDTO>> searchCustomer(@RequestParam String query){
        List<CustomerDTO> searchCustomer = customerService.searchCustomer(query);
        return new ResponseEntity<>(searchCustomer, HttpStatus.OK);
    }

}
