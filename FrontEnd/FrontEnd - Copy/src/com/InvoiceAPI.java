package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//for map
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class InvoiceAPI
 */
@WebServlet("/InvoiceAPI")
public class InvoiceAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Invoice invoiceObj = new Invoice();

	//convert request parameters to a map
	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();

			String[] params = queryString.split("&");

			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {

		}

		return map;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InvoiceAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sending values to insert function
		String output = invoiceObj.insertInvoice(request.getParameter("invoice_id"),
				request.getParameter("customer_id"),
				request.getParameter("customer_name"),
				request.getParameter("customer_type"),
				request.getParameter("pay_units"),
				request.getParameter("monthly_amount"));
											
		//sending the output to client
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);

		//getting values from the map and sending to update function
		String output = invoiceObj.updateInvoice( paras.get("hidInvoiceIDSave").toString(),
											/*paras.get("invoice_id").toString(),*/
											paras.get("customer_id").toString(),
											paras.get("customer_name").toString(),
											paras.get("customer_type").toString(),
											paras.get("pay_units").toString(),
											paras.get("monthly_amount").toString());
		
		//sending the output to client
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter map
		Map paras = getParasMap(request);

		//getting values from the map and sending to delete function
		String output = invoiceObj.deleteInvoice(paras.get("invoice_id").toString());
		
		//sending the output to client
		response.getWriter().write(output);
	}

}
