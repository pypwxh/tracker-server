package com.yfyld.tracker.mapper;
import com.yfyld.tracker.model.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
    int addCustomer(Customer customer);
}
