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

import sakila.model.IndexDAO;

/**
 * Servlet implementation class getIndexInfo
 */
@WebServlet("/GetIndexInfoController")
public class GetIndexInfoController extends HttpServlet {
	private IndexDAO indexDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		indexDAO = new IndexDAO();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("customerCount", indexDAO.selectCustomerCount());
		map.put("totalInventory",indexDAO.selectTotalInventory());
		map.put("totalRental",indexDAO.selectTotalRental());
		map.put("totalSales",indexDAO.selectTotalSales());
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}

}
