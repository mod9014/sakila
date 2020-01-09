package sakila.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import sakila.db.DBHelper;

public class ChartDAO {
	public Map<String , Integer> selectRentalByCategory() {
		Map<String , Integer> map = new HashMap<String , Integer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*),(SELECT NAME FROM category WHERE category_id = (SELECT category_id FROM film_category WHERE film_id = (SELECT film_id FROM inventory WHERE inventory_id = rental.inventory_id))) cate FROM rental GROUP BY cate";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString("cate"),rs.getInt("COUNT(*)"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return map;
	}
	
}
