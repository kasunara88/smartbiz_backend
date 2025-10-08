package com.smartbiz.smartbiz_backend.service;

import com.smartbiz.smartbiz_backend.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {
    CustomerDTO addCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Integer id);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(CustomerDTO customerDTO);
    List<CustomerDTO> searchCustomer(String query);
    CustomerDTO deleteCustomer(Integer id);



}
