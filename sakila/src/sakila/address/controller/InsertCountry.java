package sakila.address.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDAO;

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/address/InsertCountry")
public class InsertCountry extends HttpServlet {
	private CountryDAO countryDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Enumeration params = request.getParameterNames(); String name =
		 * (String)params.nextElement();
		 */
		String country = request.getParameter("country");
		System.out.println("country : "+ country);
		
		countryDAO = new CountryDAO();
		Country vo = new Country();
		vo.setCountry(country);
		countryDAO.insertCountry(vo);
		
	}

}
