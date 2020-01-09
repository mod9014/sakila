package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.*;
import sakila.customer.model.CustomerDAO;
import sakila.model.SalesByCategoryDAO;

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/salesByCategoryController")
public class salesByCategoryController extends HttpServlet {
	private SalesByCategoryDAO salesByCategoryDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		salesByCategoryDAO = new SalesByCategoryDAO();
		Map<String, Integer> map = salesByCategoryDAO.selectSalesByCategory();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}

}
