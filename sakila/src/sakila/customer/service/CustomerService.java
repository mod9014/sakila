package sakila.customer.service;

import java.sql.Connection;
import java.sql.SQLException;

import sakila.address.model.Address;
import sakila.address.model.AddressDAO;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDAO;
import sakila.db.DBHelper;

public class CustomerService {
	private AddressDAO addressDAO;
	private CustomerDAO customerDAO;
	public void insertCustomer(Address address , Customer customer) {
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);

			addressDAO = new AddressDAO();
			int addressId = addressDAO.insertAddress(conn, address);
			 
			customer.getAddress().setAddressId(addressId);
			
			customerDAO = new CustomerDAO();
			customerDAO.insertCustomer(conn, customer);
			conn.commit();
		}catch(Exception e) {
			try {conn.rollback();e.printStackTrace();}catch(SQLException se) {System.out.println("rollback");}
		}finally {
			DBHelper.close(null, null, conn);
		}
	}
}
