package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.address.model.City;
import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;

/**
 * Servlet implementation class insertCustomer
 */
@WebServlet("/insertCustomer")
public class insertCustomer extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Address address = new Address();
		address.setAddress(request.getParameter("address"));
		address.setAddress2(request.getParameter("address2"));
		address.setDistrict(request.getParameter("district"));
		address.setCity(new City());
		address.getCity().setCityId(Integer.parseInt(request.getParameter("cityId")));
		address.setPosalCode(request.getParameter("postalCode"));
		address.setPhone(request.getParameter("phone"));
		
		Customer customer = new Customer();	
		customer.setStoreId(Integer.parseInt(request.getParameter("storeId")));
		customer.setFirstName(request.getParameter("firstName"));
		customer.setLastName(request.getParameter("lastName"));
		customer.setEmail(request.getParameter("email"));
		customer.setAddress(address);
		System.out.println(customer.toString());
		System.out.println(address.toString());
		customerService = new CustomerService();
		customerService.insertCustomer(address, customer);
	}

}
