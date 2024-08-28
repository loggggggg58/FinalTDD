package finaltdd.service;

import finaltdd.repository.CustomerRepo;
import finaltdd.repository.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    public List<Customer> getCustomers() {
        return customerRepo.getCustomers();
    }

    public boolean addCustomer(Customer customer) {
        return customerRepo.addCustomer(customer);
    }
}
