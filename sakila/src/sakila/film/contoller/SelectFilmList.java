package sakila.film.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.film.model.Film;
import sakila.film.model.FilmDAO;

/**
 * Servlet implementation class SelectCountryCount
 */
@WebServlet("/film/selectFilmList")
public class SelectFilmList extends HttpServlet {
	private FilmDAO filmDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		filmDAO = new FilmDAO();
		List<Film> count = filmDAO.selectFilmList(Integer.parseInt(request.getParameter("currentPage")));
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		System.out.println(jsonCount);
		response.getWriter().write(jsonCount);
	}

}
