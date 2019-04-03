package com.yfyld.tracker.service.event.impl;
import com.yfyld.tracker.mapper.CustomerMapper;
import com.yfyld.tracker.model.Customer;
import com.yfyld.tracker.service.event.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "CustomerService")
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public int addCustomer(Customer customer) {
        return customerMapper.addCustomer(customer);
    }

}
