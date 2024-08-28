package finaltdd.service;

import finaltdd.repository.CustomerRepo;
import finaltdd.repository.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class CustomerServiceTest {
    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepo customerRepo;

    @Test
    public void should_return_all_customers() {
        List<Customer> fakeListCustomers = Arrays.asList(
                new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00"),
                new Customer(2, "Dac Quy", "quy123@gmail.com", "C002", "27-08-2024 12:54"),
                new Customer(3, "Van Quang", "quangle@yahoo.com", "C004", "27-08-2024 13:00")
        );

        when(customerRepo.getCustomers()).thenReturn(fakeListCustomers);

        List<Customer> actualListCustomers = customerService.getCustomers();

        Assertions.assertEquals(fakeListCustomers, actualListCustomers);
    }

    @Test
    public void should_return_true_after_add_customer_successfully() {
        Customer fakeCustomer = new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00");

        when(customerRepo.addCustomer(fakeCustomer)).thenReturn(true);

        boolean expected = true;

        boolean actual = customerService.addCustomer(fakeCustomer);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_return_false_after_add_customer_with_exist_email() {
        Customer fakeCustomer = new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00");

        when(customerRepo.addCustomer(fakeCustomer)).thenReturn(false);

        boolean expected = false;

        boolean actual = customerService.addCustomer(fakeCustomer);

        Assertions.assertEquals(expected, actual);
    }
}
