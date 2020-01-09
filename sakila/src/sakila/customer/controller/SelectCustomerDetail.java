package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.CustomerDAO;
import sakila.customer.model.CustomerDetail;

/**
 * Servlet implementation class SelectCountryList
 */
@WebServlet("/customer/SelectCustomerDetail")
public class SelectCustomerDetail extends HttpServlet {
	CustomerDAO customerDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("apllication/json;charset=UTF-8");
	
		customerDAO = new CustomerDAO();
		CustomerDetail customerDetail = customerDAO.selectCustormerDetail(Integer.parseInt(request.getParameter("customerId")));
		System.out.println(customerDetail);
		Gson gson = new Gson();
		String jsonList = gson.toJson(customerDetail);
		response.getWriter().write(jsonList);
	}

}
