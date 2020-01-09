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
@WebServlet("/address/selectCityList")
public class SelectCityList extends HttpServlet {
	CityDAO cityDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("apllication/json;charset=UTF-8");
		int currentPage = 1;
		if(request.getParameter("currentPage") != null)
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		cityDAO = new CityDAO();
		List<City> list = cityDAO.selectCityList(currentPage);
		System.out.println(list);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		response.getWriter().write(jsonList);
	}

}
