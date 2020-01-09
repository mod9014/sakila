package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CountryDAO;

/**
 * Servlet implementation class SelectCountryCount
 */
@WebServlet("/address/selectCountryCount")
public class SelectCountryCount extends HttpServlet {
	private CountryDAO countryDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		countryDAO = new CountryDAO();
		int count = countryDAO.selectCountryCount();
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		System.out.println(jsonCount);
		response.getWriter().write(jsonCount);
	}

}
