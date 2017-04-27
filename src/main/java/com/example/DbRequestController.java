package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

@RestController
class DbRequestController {
	
	
    //http://localhost:8080/hello/StellaSelena
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
    	
    	try {
			DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String url = "jdbc:mysql://localhost:3306/test";
    	try {
			Connection c = (Connection) DriverManager.getConnection(url,"root","");
			PreparedStatement ps = (PreparedStatement) c.prepareStatement("select * from users");
			System.out.println(ps.executeQuery());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "Hello, " + name + "!";
    }
}