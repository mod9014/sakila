package sakila.address.controller;

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

/**
 * Servlet implementation class SelectCountryList
 */
@WebServlet("/address/selectCityListByCountry")
public class SelectCityListByCountry extends HttpServlet {
	private CityDAO cityDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("apllication/json;charset=UTF-8");
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println("SelectCityListByCountry.java - countryId : " + countryId);
		
		cityDAO = new CityDAO();
		List<City> list = cityDAO.selectCityListByCountry(countryId);
		System.out.println(list);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		response.getWriter().write(jsonList);
	}

}
