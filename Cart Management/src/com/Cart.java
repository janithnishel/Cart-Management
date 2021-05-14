package com;

import java.sql.*;

public class Cart {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carts", "root", "Pablo@1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readCart() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Item Id</th><th>Item Name</th>" + "<th>Item Price</th>"
					+ "<th>Item Quantity</th>" + "<th>Item Description</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from cart";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String cartId = Integer.toString(rs.getInt("cartId"));
				String itemId = Integer.toString(rs.getInt("itemId"));
				String itemName = rs.getString("itemName");
				String itemPrice = Double.toString(rs.getDouble("itemPrice"));
				String quantity = Integer.toString(rs.getInt("quantity"));
				String description = rs.getString("description");
// Add into the html table
				output += "<tr><td><input id='hidcartIdUpdate' "
				 		+ "name='hidcartIdUpdate' "
				 		+ "type='hidden' value='" + cartId 
				 		+ "'>" + itemId + "</td>"; 
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + description + "</td>";
				// buttons
				output += "<td><input name='btnUpdate'  type='button' value='update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger'  data-cartid='"
						+ cartId + "'>" /*
										 * +
										 * "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn  btn-secondary'  data-itemId='"
										 * + itemID + "'>"
										 */ + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the cart.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertCart(String itemId, String itemName, String itemPrice, String quantity, String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			// create a prepared statement
			String query = " insert into cart(`cartId`,`itemId`,`itemName`,`itemPrice`,`quantity`,`description`)"
					+ " values (?, ?, ?, ?, ?, ?);";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, Integer.parseInt(itemId));
			preparedStmt.setString(3, itemName);
			preparedStmt.setDouble(4, Double.parseDouble(itemPrice));
			preparedStmt.setInt(5, Integer.parseInt(quantity));
			preparedStmt.setString(6, description);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readCart();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the cart.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateCart(String cartId, String itemId, String itemName, String itemPrice, String quantity,
			String description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE cart SET itemId=?,itemName=?,itemPrice=?,quantity=?,description=?WHERE cartId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemId));
			preparedStmt.setString(2, itemName);
			preparedStmt.setDouble(3, Double.parseDouble(itemPrice));
			preparedStmt.setInt(4, Integer.parseInt(quantity));
			preparedStmt.setString(5, description);
			preparedStmt.setInt(6, Integer.parseInt(cartId));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readCart();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the cart.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCart(String cartId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from cart where cartId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(cartId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newItems = readCart();
			output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  \"Error while deleting the cart.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}