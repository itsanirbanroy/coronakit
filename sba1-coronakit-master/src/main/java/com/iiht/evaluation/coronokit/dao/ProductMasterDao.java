package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;



public class ProductMasterDao {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	private List<ProductMaster> product;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public List<ProductMaster> getProducts() throws ClassNotFoundException, SQLException {
		String sql = "select * from products";
		this.connect();
		
		Statement stmt = this.jdbcConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// map it to model
		List<ProductMaster> products = new ArrayList<ProductMaster>();
		while(rs.next()) {
			ProductMaster product = new ProductMaster(rs.getInt("id"), 
					rs.getString("productName"), 
					rs.getString("cost"), 
					rs.getString("productDescription"));
			products.add(product);		
		}
		
		rs.close();
		stmt.close();
		this.disconnect();
		
		return products;
	}

	public boolean addNewProduct(String productName, String cost, String productDescription) throws ClassNotFoundException, SQLException {
		String sql = "insert into products (productName,cost,productDescription) values(?,?,?)";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, productName);
		pstmt.setString(2, cost);
		pstmt.setString(3, productDescription);
		
		boolean added = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return added;
	}

	public boolean deleteProduct(String id) throws ClassNotFoundException, SQLException {
		
		String sql = "delete from products where id=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		
		boolean deleted = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return deleted;
	}
	
	public boolean updateProduct(String id, String name, String cost, String desc) throws ClassNotFoundException, SQLException {
		
		String sql = "update products set productName = ?, cost = ?, productDescription = ? where id=?";
		this.connect();
		
		PreparedStatement pstmt = this.jdbcConnection.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setString(2, cost);
		pstmt.setString(3, desc);
		pstmt.setInt(4,  Integer.parseInt(id));
		
		System.out.println("Query = "+pstmt);
		boolean executed = pstmt.executeUpdate() > 0;
		
		pstmt.close();
		this.disconnect();
		return executed;
	}
}