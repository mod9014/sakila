package sakila.film.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class FilmDAO {
	
	public List<Film> selectFilmList(int currentPage){
		List<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		String sql = "SELECT title, (SELECT NAME FROM category WHERE category.category_id=(SELECT category_id FROM film_category WHERE film_id=film.film_id)) category, release_year, (SELECT name FROM language WHERE language_id=film.language_id) language, length, rating, rental_rate " + 
				"FROM film LIMIT ?,?";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Film film = new Film();
				film.setTitle(rs.getString("title"));
				film.setCategory(rs.getString("category"));
				film.setReleaseYear(rs.getString("release_year"));
				film.setLanguage(rs.getString("language"));
				film.setLength(rs.getInt("length"));
				film.setRating(rs.getString("rating"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				list.add(film);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return list;
	}
}
