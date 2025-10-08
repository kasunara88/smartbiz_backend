package com.smartbiz.smartbiz_backend.service.impl;

import com.smartbiz.smartbiz_backend.dto.CustomerDTO;
import com.smartbiz.smartbiz_backend.entity.Customer;
import com.smartbiz.smartbiz_backend.repository.CustomerRepo;
import com.smartbiz.smartbiz_backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    final private CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = toEntity(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        return toDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Integer id) {
        return customerRepo.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> findByID = customerRepo.findById(customerDTO.getId());
        if(findByID.isPresent()){
            Customer customer = findByID.get();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setPhone(customerDTO.getPhone());
            Customer updatedCustomer = customerRepo.save(customer);
            return toDTO(updatedCustomer);
        }else{
            return null;

        }
    }

    @Override
    public List<CustomerDTO> searchCustomer(String query) {
        List<Customer> customerList = customerRepo.searchCustomers(query);

        return customerList.stream().
                map(this::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDTO deleteCustomer(Integer id) {
        return customerRepo.findById(id)
                .map(customer -> {
                    customerRepo.delete(customer);
                    return toDTO(customer);
                })
                .orElse(null);
    }

    private CustomerDTO toDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone());
    }

    private Customer toEntity(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getName(),
                customerDTO.getEmail(),
                customerDTO.getPhone());
    }
}
