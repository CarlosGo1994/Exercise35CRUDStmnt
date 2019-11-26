package com.crudstmnt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * Servlet que sirve para dar de alta un producto
 * @author Carlos Gomez Martinez
 * 
 *@since 1.0
 *
 *@see javax.servlet.http.HttpServlet;
 *@see javax.servlet.http.HttpServletRequest
 *@see javax.servlet.http.HttpServletRequest
 *
 *@param request Este parmetro me sirve para recibir los datos  del cliente
 *@param request  
 *
 *
 */

/***
 */
@WebServlet("/CreateServlet")
public class CreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	//Metodo que cacha las solicitudes post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json");
		PrintWriter output = response.getWriter();
		
		String nameProduct= request.getParameter("txtNameProduct");
		double priceProduct = Double.parseDouble(request.getParameter("txtPriceProduct"));
		
		//1.Declaramos las variabes 
		String urlServidor ="jdbc:mysql://localhost:3306/tiendita?useSSL=false&serverTimezone=UTC";
		String nombreUsuario = "root";
		String password = "admin";
		int rowsAffected=0;
		//2.Declaramos los objetos
		/*Connection conn=null;
		Statement stmnt= null;
		ResultSet rs=null;
		*/
		//3.Instanciamos el driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(urlServidor,nombreUsuario,password);
			Statement stmnt = conn.createStatement();
			rowsAffected = stmnt.executeUpdate("INSERT INTO productos (nombreProducto, precioProducto) VALUES ('"+nameProduct+"',"+priceProduct+")");
			if(rowsAffected!=0)
			{
				output.append("Registro añadido con exito");
			}
			else
			{
				output.append("Registro no fue añadido");
			}
			stmnt.close();
			conn.close();
		} catch (SecurityException | ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//4.Abrimos la conexion
		//5. Ejecutamos la sentencia sql
		//6. Procesamos los datos
		//7. Cerramos los objetos de conexion
				
	}

}
