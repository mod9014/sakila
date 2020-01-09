package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.address.model.City;
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
	public CustomerDetail selectCustormerDetail(int customerId) {
		CustomerDetail customerDetail = new CustomerDetail();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		String sql = "SELECT c.customer_id, c.first_name, c.last_name, c.email, active,c.create_date,(SELECT address FROM address WHERE address_id = (SELECT address_id FROM store WHERE store_id = c.store_id)) store_address,a.address,a.district, (SELECT city FROM city WHERE city_id = a.city_id) city, postal_code, phone FROM customer c INNER JOIN address a ON c.address_id=a.address_id WHERE c.customer_id=?";
		String sql2 = "SELECT title, rental_rate, rental_date, return_date FROM rental INNER JOIN film f ON f.film_id = (SELECT film_id FROM inventory WHERE inventory_id = rental.inventory_id) WHERE customer_id=? ORDER BY rental_date DESC";

		try{
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1,customerId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				customerDetail.setCustomer(new Customer());
				customerDetail.getCustomer().setCustomerId(rs.getInt("c.customer_id"));
				customerDetail.getCustomer().setFirstName(rs.getString("c.first_name"));
				customerDetail.getCustomer().setLastName(rs.getString("c.last_name"));
				customerDetail.getCustomer().setEmail(rs.getString("c.email"));
				customerDetail.getCustomer().setActive(rs.getInt("active"));
				
				customerDetail.setStore_address(rs.getString("store_address"));
				
				customerDetail.setAddress(new Address());
				customerDetail.getAddress().setAddress(rs.getString("a.address"));
				customerDetail.getAddress().setDistrict(rs.getString("a.district"));
				customerDetail.getAddress().setCity(new City());
				customerDetail.getAddress().getCity().setCity(rs.getString("city"));
				customerDetail.getAddress().setPosalCode(rs.getString("postal_code"));
				customerDetail.getAddress().setPhone(rs.getString("phone"));
			}
			stmt2 = conn.prepareStatement(sql2);
			stmt2.setInt(1,customerId);
			rs2 = stmt2.executeQuery();
			List<Rental> listRental = new ArrayList<>();
			while(rs2.next()) {
				Rental rental = new Rental();
				rental.setRentalDate(rs2.getString("rental_date"));
				rental.setRentalRate(rs2.getDouble("rental_rate"));
				rental.setReturnDate(rs2.getString("return_date"));
				rental.setTitle(rs2.getString("title"));
				listRental.add(rental);
			}
			customerDetail.setListRental(listRental);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs,stmt, conn);
		}
		return customerDetail;
	}
	
}
