package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.City;
import sakila.address.model.CityDAO;
import sakila.address.model.Country;

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/address/InsertCity")
public class InsertCity extends HttpServlet {
	private CityDAO cityDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * Enumeration params = request.getParameterNames(); String name =
		 * (String)params.nextElement();
		 */
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		String city = request.getParameter("city");
		System.out.println("countryId : "+ countryId);
		System.out.println("city : "+ city);
		
		cityDAO = new CityDAO();
		City vo = new City();
		vo.setCity(city);
		vo.setCountry(new Country());
		vo.getCountry().setCountryId(countryId);
		cityDAO.insertCity(vo);
		
	}

}
