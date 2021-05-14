package com;
import com.Cart;
import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CartAPI") 
public class CartAPI extends HttpServlet 
{ 
	private static final long serialVersionUID =1L;
	
	Cart cartObj = new Cart();

	public CartAPI() {
		
		super(); 
	}
	
	// Convert request parameters to a Map
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}
	
	
	
	
		
	
	
 //Your code
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException
			 
			 {
			 String output = cartObj.insertCart(request.getParameter("itemId"), 
			 request.getParameter("itemName"), 
			request.getParameter("itemPrice"), 
			request.getParameter("quantity"),
			 request.getParameter("description"));  
			response.getWriter().write(output); 
		}
		
		
			 
	
			

	
	
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{  
			 Map paras = getParasMap(request); 
			 String output = cartObj.updateCart(paras.get("hidcartIdSave").toString(), 
			 paras.get("itemId").toString(), 
			 paras.get("itemName").toString(), 
			paras.get("itemPrice").toString(), 
			paras.get("quantity").toString(),
			 paras.get("description").toString());
			response.getWriter().write(output); 
			} 
	
	
	
	
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = cartObj.deleteCart(paras.get("cartId").toString()); 
			response.getWriter().write(output); 
			}
			
}
	 