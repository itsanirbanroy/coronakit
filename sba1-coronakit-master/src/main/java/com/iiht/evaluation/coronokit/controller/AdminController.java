package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductMasterDao productMasterDao;
	
	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}	
	

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");

		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action =  request.getParameter("action");
		String viewName = "";
		try {
			switch (action) {
			case "login" : 
				viewName = adminLogin(request, response);
				break;
			case "newproduct":
				viewName = showNewProductForm(request, response);
				break;
			case "insertproduct":
				viewName = insertProduct(request, response);
				break;
			case "deleteproduct":
				viewName = deleteProduct(request, response);
				break;
			case "editproduct":
				viewName = showEditProductForm(request, response);
				break;
			case "updateproduct":
				viewName = updateProduct(request, response);
				break;
			case "list":
				viewName = listAllProducts(request, response);
				break;	
			case "logout":
				viewName = adminLogout(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;		
			}
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage());
		}
		RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
		dispatch.forward(request, response);
		
		
	}

	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
	
		// TODO Auto-generated method stub
		return "";
	}

	private String listAllProducts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "listproducts.jsp";
	}

	private String updateProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String cost= request.getParameter("cost");
		String desc = request.getParameter("desc");
		
		System.out.println("Query = "+id+" "+name+" "+cost+" "+desc);
		
		this.productMasterDao.updateProduct(id, name, cost, desc);
		
		List<ProductMaster> products = this.productMasterDao.getProducts();
		// put data into request object (to share with view page)
		request.setAttribute("products", products);
		return "listproducts.jsp";
	}

	private String showEditProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		//request.setAttribute("id", id);
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		return "editproduct.jsp";
	}

	private String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		this.productMasterDao.deleteProduct(request.getParameter("id"));
		List<ProductMaster> products = this.productMasterDao.getProducts();
		// put data into request object (to share with view page)
		request.setAttribute("products", products);
		
		return "listproducts.jsp";
	}

	private String insertProduct(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String cost= request.getParameter("cost");
		String desc = request.getParameter("desc");
		
		this.productMasterDao.addNewProduct(name, cost, desc);
		
		// check boolean value and return the entry page url
		List<ProductMaster> products = this.productMasterDao.getProducts();
		// put data into request object (to share with view page)
		request.setAttribute("products", products);		
		return "listproducts.jsp";
	}

	private String showNewProductForm(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "newproduct.jsp";
	}

	private String adminLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("loginid");
		String pwd = request.getParameter("password");
		if(username.contentEquals("admin")&&pwd.equals("admin")) {
			// need to retrieve records from DB
			List<ProductMaster> products = this.productMasterDao.getProducts();
			// put data into request object (to share with view page)
			request.setAttribute("products", products);
			return "listproducts.jsp";
		}
		else
			throw new Exception("Invalid credential");
	}

	
}