package sakila.customer.model;

import java.util.List;

import sakila.address.model.Address;

public class CustomerDetail {
	private Customer customer;
	private String store_address;
	private Address address;
	private List<Rental> listRental;
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public List<Rental> getListRental() {
		return listRental;
	}
	public void setListRental(List<Rental> listRental) {
		this.listRental = listRental;
	}
	@Override
	public String toString() {
		return "CustomerDetail [customer=" + customer + ", store_address=" + store_address + ", address=" + address
				+ ", listRental=" + listRental + "]";
	}
}
