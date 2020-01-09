package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.db.DBHelper;

public class CustomerDAO {
	public int selectCustormerCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM customer";
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
	public List<Customer> selectCustormerList(int currentPage) {
		List<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage-1)*ROW_PER_PAGE;
		String sql = "SELECT * FROM customer ORDER BY customer_id desc limit ?,?";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setStoreId(rs.getInt("store_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setAddress(new Address());
				customer.getAddress().setAddressId(rs.getInt("address_id"));
				customer.setActive(rs.getInt("active"));
				customer.setCreateDate(rs.getString("create_date"));
				customer.setLastUpdate(rs.getString("last_update"));
				list.add(customer);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return list;
	}
	public void insertCustomer(Connection conn, Customer customer) throws Exception {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO customer(store_id, first_name, last_name, email, address_id, active, create_date, last_update) VALUES(?,?,?,?,?,1,now(),now())";
		
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,customer.getStoreId());
			stmt.setString(2,customer.getFirstName());
			stmt.setString(3,customer.getLastName());
			stmt.setString(4,customer.getEmail());
			stmt.setInt(5,customer.getAddress().getAddressId());
			stmt.executeUpdate();
		
	}
	
	public void transactionTest() {
		Connection conn = null;
		PreparedStatement stmt = null;
		PreparedStatement stmt2 = null;
		String sql = "SELECT COUNT(*) FROM customer";
		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt2 = conn.prepareStatement(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(null,stmt, conn);
		}
	}
}
