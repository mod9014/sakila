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

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private CountryDAO countryDAO;
	private CityDAO cityDAO;
	private ActorDAO actorDAO;
	private AddressDAO addressDAO;
	private CategoryDAO categoryDAO;
	private CustomerDAO custormerDAO;
	private FilmActorDAO filmActorDAO;
	private FilmCategoryDAO filmCategoryDAO;
	private FilmDAO filmDAO;
	private FilmTextDAO filmTextDAO;
	private InventoryDAO inventoryDAO;
	private LanguageDAO languageDAO;
	private PaymentDAO paymentDAO;
	private RentalDAO rentalDAO;
	private StaffDAO staffDAO;
	private StoreDAO storeDAO;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Integer> map = new HashMap<String, Integer>();
		countryDAO = new CountryDAO();
		cityDAO = new CityDAO();
		actorDAO = new ActorDAO();
		addressDAO = new AddressDAO();
		categoryDAO = new CategoryDAO();
		custormerDAO = new CustomerDAO();
		filmActorDAO = new FilmActorDAO();
		filmCategoryDAO = new FilmCategoryDAO();
		inventoryDAO = new InventoryDAO();
		filmTextDAO = new FilmTextDAO();
		languageDAO = new LanguageDAO();
		paymentDAO = new PaymentDAO();
		rentalDAO = new RentalDAO();
		staffDAO = new StaffDAO();
		storeDAO = new StoreDAO();
		filmDAO = new FilmDAO();
		
		int countryRow = countryDAO.selectCountryCount();
		map.put("countryRow", countryRow);
		int cityRow = cityDAO.selectCityCount();
		map.put("cityRow",cityRow);
		int addressRow = addressDAO.selectAddressCount();
		map.put("addressRow",addressRow);
		int customerRow = custormerDAO.selectCustormerCount();
		map.put("customerRow",customerRow);
		int staffRow = staffDAO.selectStaffCount();
		map.put("staffRow",staffRow);
		int storeRow = storeDAO.selectStoreCount();
		map.put("storeRow",storeRow);
		int paymentRow = paymentDAO.selectPaymentCount();
		map.put("paymentRow",paymentRow);
		int rentalRow = rentalDAO.selectRentalCount();
		map.put("rentalRow",rentalRow);
		int categoryRow = categoryDAO.selectCategoryCount();
		map.put("categoryRow",categoryRow);
		int filmCategotyRow = filmCategoryDAO.selectFilmCategoryCount();
		map.put("filmCategotyRow",filmCategotyRow);
		int filmRow = filmDAO.selectFilmCount();
		map.put("filmRow",filmRow);
		int languageRow = languageDAO.selectLanguageCount();
		map.put("languageRow",languageRow);
		int actorRow = actorDAO.selectActorCount();
		map.put("actorRow",actorRow);
		int filmActorRow = filmActorDAO.selectFilmActorCount();
		map.put("filmActorRow",filmActorRow);
		int inventoryRow = inventoryDAO.selectInventoryCount();
		map.put("inventoryRow",inventoryRow);
		int filmTextRow = filmTextDAO.selectFilmTextCount();
		map.put("filmTextRow",filmTextRow);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		response.getWriter().write(jsonStr);
	}

}
