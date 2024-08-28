package finaltdd.repository;

import finaltdd.repository.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepo {
    public List<Customer> getCustomers() {
        throw new RuntimeException("Not implemented");
    }

    public boolean addCustomer(Customer customer) {
        throw new RuntimeException("Not implemented");
    }
}
