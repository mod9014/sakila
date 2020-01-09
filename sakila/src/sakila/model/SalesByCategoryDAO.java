package sakila.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import sakila.db.DBHelper;

public class SalesByCategoryDAO {
	public Map<String, Integer> selectSalesByCategory() {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Map<String, Integer> map = new HashMap<>();
		String sql = "SELECT category, total_sales from sales_by_film_category";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getString("category"), rs.getInt("total_sales"));
			}
				
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return map;
	}
}
