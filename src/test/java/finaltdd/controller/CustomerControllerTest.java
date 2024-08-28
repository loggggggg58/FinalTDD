package finaltdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import finaltdd.repository.model.Customer;
import finaltdd.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void should_return_list_customers_view() throws Exception {
        List<Customer> fakeListCustomers = Arrays.asList(
                new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00"),
                new Customer(2, "Dac Quy", "quy123@gmail.com", "C002", "27-08-2024 12:54"),
                new Customer(3, "Van Quang", "quangle@yahoo.com", "C004", "27-08-2024 13:00")
        );

        when(customerService.getCustomers()).thenReturn(fakeListCustomers);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", fakeListCustomers))
                .andDo(print());
    }

    @Test
    public void should_return_create_customer_view() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andDo(print());
    }

    @Test
    public void should_return_create_customer_view_when_create_fail() throws Exception {
        Customer fakeCustomer = new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00");

        when(customerService.addCustomer(fakeCustomer)).thenReturn(false);

        mockMvc.perform(post("/")
                        .content(new ObjectMapper().writeValueAsString(fakeCustomer))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("create"))
                .andDo(print());
    }

    @Test
    public void should_return_list_customers_view_when_create_success() throws Exception {
        Customer fakeCustomer = new Customer(1, "Thanh Long", "tranlong@gmail.com", "C001", "28-08-2024 15:00");

        when(customerService.addCustomer(fakeCustomer)).thenReturn(true);

        mockMvc.perform(post("/")
                        .content(new ObjectMapper().writeValueAsString(fakeCustomer))
                        .contentType("application/json")
                        .accept("application/json")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andDo(print());
    }

}
