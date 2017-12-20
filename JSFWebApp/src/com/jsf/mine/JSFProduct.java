package com.jsf.mine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="product")
@RequestScoped
public class JSFProduct {

	private int productId;
	private String productName;
	private int availableQuantity;
	private String country;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String add()
	{
	
	int i = 0;

	if(getProductId() !=0)
	{
	PreparedStatement ps = null;
	Connection con = null; 
	try
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "scott", "tiger");
	String sql = "INSERT INTO JSFPRODUCT(PRODUCTID, PRODUCTNAME, AVAILABLEQUANTITY, COUNTRY) VALUES(?,?,?,?)";
	ps= con.prepareStatement(sql); 
	ps.setInt(1, getProductId());
	ps.setString(2, getProductName());
	ps.setInt(3, getAvailableQuantity());
	ps.setString(4, getCountry());
    
	i = ps.executeUpdate();
	System.out.println(i);
	System.out.println("Data Added Successfully");
	}
	catch(Exception e)
	{
	System.out.println(e.getMessage()); 
	}
	finally
	{
	try
	{
	con.close();
	ps.close();
	}
	catch(Exception e)
	{
	e.printStackTrace();
	}
	}
	if(i >0)
	{
	return "success";
	}
	else
	{
	return "failure";
	}
	}else

	{
		return "invalid";
	}

	}
}
