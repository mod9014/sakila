package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.City;
import sakila.address.model.CityDAO;
import sakila.address.model.Country;
import sakila.address.model.CountryDAO;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDAO;

/**
 * Servlet implementation class SelectCountryList
 */
@WebServlet("/customer/selectCustomerList")
public class SelectCustomerList extends HttpServlet {
	CustomerDAO customerDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("apllication/json;charset=UTF-8");
	
		customerDAO = new CustomerDAO();
		List<Customer> list = customerDAO.selectCustormerList(Integer.parseInt(request.getParameter("currentPage")));
		System.out.println(list);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		response.getWriter().write(jsonList);
	}

}
