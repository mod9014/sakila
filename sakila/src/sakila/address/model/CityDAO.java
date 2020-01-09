package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CityDAO {
	public List<City> selectCityListByCountry(int countryId){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT city_id, city FROM city WHERE country_id=?";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("city_id"));
				city.setCity(rs.getString("city"));
				list.add(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return list;
	}
	public int selectCityCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM city";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				count = rs.getInt("COUNT(*)");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return count;
	}
	public List<City> selectCityList(int currentPage){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		String sql = "SELECT * FROM city LIMIT ?,?";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("city_id"));
				city.setCity(rs.getString("city"));
				city.setCountry(new Country());
				city.getCountry().setCountryId(rs.getInt("country_id"));
				city.setLastUpdate(rs.getString("last_update"));
				list.add(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return list;
	}
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO city(city, country_id, last_update) VALUES(?,?,now())";
		
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,city.getCity());
			stmt.setInt(2,city.getCountry().getCountryId());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(null,stmt, conn);
		}
	}
}
