package servletTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class CityCodeServletDS
 */
@WebServlet("/servletTest/CityCodeServletDS")
public class CityCodeServletDS extends HttpServlet {
	/* IDE能自動協助生成的項目，並不會使用到 */
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CityCodeServletDS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds0 = null;
	    InitialContext ctxt0 = null;
	    Connection conn0 = null;
	    
	    try  {
	    	// 建立Context Object,連到JNDI Server
	        ctxt0 = new InitialContext();
	        
	        // 使用JNDI API找到DataSource，最後面的名稱請跟web.xml上的<res-ref-name>以及context.xml上的<Resource name>一致
	        ds0 = ( DataSource ) ctxt0.lookup("java:comp/env/jdbc/WebProject0");
	        
	        // 向DataSource要Connection
	        conn0 = ds0.getConnection();
	        
	        // 建立Database Access Object,負責Table的Access
	        CityCodeDAO cityCodeDAO = new CityCodeDAO(conn0);
	        
	        // 設定request編碼
	        request.setCharacterEncoding("UTF-8");
	        
	        if (request.getParameter("QUERY").equals("QUERY1")) {
	        	doQuery(request,response,cityCodeDAO);
	        } else if (request.getParameter("QUERY").equals("QUERY2")) {
	            doQuery(request,response,cityCodeDAO);
	        }
	        
	    } catch (NamingException nE) {
	    	System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException sqlE) {
	    	System.out.println("Database Connection Error"); 
		} finally {
			try {
				if (conn0 != null) {
					conn0.close();
				}
			} catch (SQLException sqlE0) {
				System.out.println("Database Connection Error"); 
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void doQuery(HttpServletRequest request, 
            HttpServletResponse response,
            CityCodeDAO cityCodeDAO) throws SQLException,IOException {
		
		// 讀取city_code
	    String inputCityCode = (!request.getParameter("paramCityCode").equals("")) ? request.getParameter("paramCityCode") : "";
	    // 讀取city_name
	    String inputCityName = (!request.getParameter("paramCityName").equals("")) ? request.getParameter("paramCityName") : "";
	    
	    // 透過DAO元件Access Table
	    List<CityCode> queryResult = cityCodeDAO.getCityInfo(inputCityCode, inputCityName);
	    
	    if (queryResult.size() != 0) {
	    	showForm(response, queryResult);
	    } else{
	    	if (!inputCityCode.equals("")) {
	    		showError(response, "無法找到" + inputCityCode);
	    	}
	    	else if (!inputCityName.equals("")) {
	    		showError(response, "無法找到" + inputCityName);
	    	}
	    } 
	}
	
	private void showError(HttpServletResponse response, String message)
            throws IOException  {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("message:"+ message);                  
		out.close();
	}
	
	private void showForm(HttpServletResponse response, List<CityCode> queryResult)
            throws IOException  {
		response.setContentType("text/html;charset=UTF-8");              
		PrintWriter out = response.getWriter();
		for (int dataIndex = 0; dataIndex < queryResult.size(); dataIndex++) {
			out.println("City Code is:" + queryResult.get(dataIndex).getCity_code());
			out.println("City Name is:" + queryResult.get(dataIndex).getCity_name());
		}
		out.close();
	}
}
