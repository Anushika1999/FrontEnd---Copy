package com;

import java.sql.*;

public class Invoice {

    public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "power_grid";
        String dbUsername = "root";
        String dbPassword = "root";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
    
    
    //method to insert data
    public String insertInvoice(String invoice_id, String customer_id, String customer_name, String customer_type, String pay_units, String monthly_amount) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO invoice_management (nvoice_id,customer_id,customer_name,customer_type,pay_units,monthly_amount) VALUES (?,?,?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, invoice_id);
        	preparedStatement.setString(2, customer_id);
        	preparedStatement.setString(3, customer_name);
        	preparedStatement.setString(4, customer_type);
        	preparedStatement.setDouble(5, Integer.parseInt(pay_units));
        	preparedStatement.setDouble(6, Float.parseFloat(monthly_amount));
        	
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();

			String newInvoice = readInvoice(); 
			Output = "{\"status\":\"success\", \"data\": \"" + newInvoice + "\"}";
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\": \"Failed to insert the invoice\"}";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to update data
    public String updateInvoice(String invoice_id, String customer_id, String customer_name, String customer_type, String pay_units, String monthly_amount) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE invoice_management SET invoice_id = ?,customer_id = ?,customer_name = ?,customer_type= ?,pay_units= ?,monthly_amount= ? WHERE invoice_id= ?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, invoice_id);
        	preparedStatement.setString(2, customer_id);
        	preparedStatement.setString(3, customer_name);
        	preparedStatement.setString(4, customer_type);
        	preparedStatement.setInt(5, Integer.parseInt(pay_units));
        	preparedStatement.setFloat(6, Float.parseFloat(monthly_amount));
        	
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	String newInvoice = readInvoice(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newInvoice + "\"}";
        	
    	} catch(Exception e) {
    		Output = "{\"status\":\"error\", \"data\":\"Failed to update the invoice.\"}"; 
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String readInvoice() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM invoice_management";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>Invoice ID</th>" +"<th>Customer ID</th><th>Customer Name</th>"+"<th>Customer Type</th><th>Customer Name</th>"+"<th>Customer Type</th><th>Pay Units</th>"
    		+ "<th>Monthly Amount</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String invoice_id = resultSet.getString("invoice_id");
        		String customer_id = resultSet.getString("customer_id");
        		String customer_name = resultSet.getString("customer_name");
        		String customer_type = resultSet.getString("customer_type");
        		String pay_units = Integer.toString(resultSet.getInt("pay_units"));
        		String monthly_amount = Float.toString(resultSet.getFloat("monthly_amount"));
        		
        		// Add a row into the HTML table
        		//Output += "<tr>"+ "<td><input id='hidInvoiceIDUpdate' name='hidInvoiceIDUpdate' type='hidden' value='" + invoice_id + "'>" + invoice_id + "</td>";
        		Output += "<td>" + invoice_id + "</td>";
        		Output += "<td>" + customer_id + "</td>"; 
        		Output += "<td>" + customer_name + "</td>"; 
        		Output += "<td>" + customer_type + "</td>";
        		Output += "<td>" + pay_units + "</td>";
        		Output += "<td>" + monthly_amount + "</td>";
        		
        		// buttons
        		Output += "<td>"
						+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-itemid='" + invoice_id  + "'>"
						+ "</td>" 
        				+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-itemid='" + invoice_id  + "'>"
						+ "</td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the invoice";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to delete data
    public String deleteInvoice(String invoice_id ) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM invoice_management WHERE invoice_id = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1,(invoice_id));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	String newInvoice = readInvoice(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newInvoice+ "\"}"; 
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\":\"Failed to delete the invoice.\"}";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }
}
