package sakila.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.model.ChartDAO;

/**
 * Servlet implementation class getIndexInfo
 */
@WebServlet("/IndexPieChartController")
public class IndexPieChartController extends HttpServlet {
	private ChartDAO chartDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		chartDAO = new ChartDAO();
		Map<String, Integer> map = chartDAO.selectRentalByCategory();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}

}
