package sakila.address.model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import sakila.db.DBHelper;

public class AddressDAO {
	public int selectAddressCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM address";
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
	public int insertAddress(Connection conn, Address address) throws Exception {
		int addressId = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO address(address, address2, district, city_id, postal_code, phone, last_update) VALUES(?,?,?,?,?,?,now())";
		
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,address.getAddress());
			stmt.setString(2,address.getAddress2());
			stmt.setString(3,address.getDistrict());
			stmt.setInt(4,address.getCity().getCityId());
			stmt.setString(5,address.getPosalCode());
			stmt.setString(6,address.getPhone());
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
	
		return addressId;
	}
}
