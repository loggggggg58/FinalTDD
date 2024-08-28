package finaltdd.controller;

import finaltdd.repository.model.Customer;
import finaltdd.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public String getListCustomersPage(Model model) {
        List<Customer> customers = customerService.getCustomers();
        model.addAttribute("customers", customers);
        return "list";
    }

    @GetMapping("/create")
    public String getCreateCustomerPage() {
        return "create";
    }

    @PostMapping("/")
    public String addCustomer(@RequestBody Customer customer) {
        boolean result = customerService.addCustomer(customer);
        if (result) return "list";
        return "create";
    }
}
