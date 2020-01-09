package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Country;
import sakila.address.model.CountryDAO;

/**
 * Servlet implementation class SelectCountryList
 */
@WebServlet("/address/selectCountryListAll")
public class SelectCountryListAll extends HttpServlet {
	CountryDAO countryDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("apllication/json;charset=UTF-8");
		
		countryDAO = new CountryDAO();
		List<Country> list = countryDAO.selectCountryListAll();
		System.out.println(list);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		response.getWriter().write(jsonList);
	}

}
