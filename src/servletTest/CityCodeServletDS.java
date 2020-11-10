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

@WebServlet("/servletTest/CityCodeServletDS")
public class CityCodeServletDS extends HttpServlet {
	/* IDE能自動協助生成的項目，並不會使用到 */
	private static final long serialVersionUID = 1L;
       
    public CityCodeServletDS() {
        super();
    }

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
	        
	        // 建立Database Access Object,負責Table的Access，實作介面
	        CityCodeDAO cityCodeDAO = new CityCodeJDBCDAO(conn0);
	        
	        // 設定request編碼
	        request.setCharacterEncoding("UTF-8");
	        
	        // 防止欄位全空，本部分可改由JavaScript直接判
	        if(request.getParameter("paramCityCode").equals("") && request.getParameter("paramCityName").equals("")) {
	        	showError(response, "沒有輸入任何參數，請重新執行");
	        } else {
	        	// 以下欄位必有填寫的欄位
	        	// 執行新增
	        	if (request.getParameter("INSERT") != null) {
	        		if (!request.getParameter("paramCityCode").equals("") && !request.getParameter("paramCityName").equals("")) {
	        			doInsert(request,response,cityCodeDAO);  
	        		} else {
	        			showError(response, "沒有輸入必要參數，請重新執行");
	        		}
	        	}
	        	// 執行修改
				if (request.getParameter("UPDATE") != null) {
					if (request.getParameter("paramCityCode").equals("")) {
						showError(response, "沒有輸入必要參數，請重新執行");
					} else {
						doUpdate(request,response,cityCodeDAO);  
					}		
	        	}
	        	// 執行刪除
	        	if (request.getParameter("DELETE") != null) {
	        		doDelete(request,response,cityCodeDAO);
	        	}
	        	// 執行查詢
	        	if (request.getParameter("QUERY").equals("QUERY1")) {
		        	doSelect(request,response,cityCodeDAO, 1);
		        } else if (request.getParameter("QUERY").equals("QUERY2")) {
		            doSelect(request,response,cityCodeDAO, 2);
		        }
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	// 執行新增
	private void doInsert(HttpServletRequest request, 
            HttpServletResponse response,
            CityCodeDAO cityCodeDAO) throws SQLException,IOException {
		// 讀取city_code
	    String inputCityCode = request.getParameter("paramCityCode");
	    // 讀取city_name
	    String inputCityName = request.getParameter("paramCityName");
	    
	    // 透過DAO元件Access Table
	    List<CityCode> insertResult = cityCodeDAO.InsertCityCode(inputCityCode, inputCityName);
	    
	    if (insertResult.size() != 0) {
	    	showForm(response, insertResult);
	    } else{
	    	showError(response, "無法新增" + inputCityCode + "," + inputCityName);
	    } 
	}
	
	// 執行修改
	private void doUpdate(HttpServletRequest request, 
            HttpServletResponse response,
            CityCodeDAO cityCodeDAO) throws SQLException,IOException {
		// 讀取city_code
	    String inputCityCode = request.getParameter("paramCityCode");
	    // 讀取city_name
	    String inputCityName = request.getParameter("paramCityName");
	    
	    // 透過DAO元件Access Table
	    List<CityCode> updateResult = cityCodeDAO.SelectCityCode(inputCityCode, inputCityName);
	    
	    if (updateResult.size() != 0) {
	    	showForm(response, updateResult);
	    } else{
	    	showError(response, "無法修改" + inputCityCode);
	    } 
	}
	
	// 執行刪除
	private void doDelete(HttpServletRequest request, 
            HttpServletResponse response,
            CityCodeDAO cityCodeDAO) throws SQLException,IOException {
		// 讀取city_code
	    String inputCityCode = request.getParameter("paramCityCode");
	    
	    if (inputCityCode.equals("")) {
	    	showError(response, "沒有提供必需的參數");
	    } else {
	    	// 透過DAO元件Access Table
	    	Boolean deleteResult = cityCodeDAO.DeleteCityCode(inputCityCode);
	    	
	    	if (deleteResult) {
		    	showMessage(response, "成功刪除" + inputCityCode);
		    } else{
		    	showError(response, "無法刪除" + inputCityCode);
		    }
	    }
	}
	
	// 執行查詢
	private void doSelect(HttpServletRequest request, 
            HttpServletResponse response,
            CityCodeDAO cityCodeDAO,
            int selectMode) throws SQLException,IOException {
		
		// 讀取city_code
	    String inputCityCode = (selectMode == 1) ? request.getParameter("paramCityCode") : "";
	    // 讀取city_name
	    String inputCityName = (selectMode == 2) ? request.getParameter("paramCityName") : "";
	    
    	// 透過DAO元件Access Table
	    List<CityCode> selectResult = cityCodeDAO.SelectCityCode(inputCityCode, inputCityName);
	    
	    if (selectResult.size() != 0) {
	    	showForm(response, selectResult);
	    } else{
	    	if (selectMode == 1) {
	    		showError(response, "無法找到" + inputCityCode);
	    	}
	    	else if (selectMode == 2) {
	    		showError(response, "無法找到" + inputCityName);
	    	}
	    } 
	}
	
	// 顯示錯誤訊息
	private void showError(HttpServletResponse response, String message)
            throws IOException  {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("message:"+ message);                  
		out.close();
	}
	
	// 顯示正常訊息
	private void showMessage(HttpServletResponse response, String message)
            throws IOException  {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("message:"+ message);                  
		out.close();
	}
	
	// 顯示正常訊息
	private void showForm(HttpServletResponse response, List<CityCode> sqlResult)
            throws IOException  {
		response.setContentType("text/html;charset=UTF-8");              
		PrintWriter out = response.getWriter();
		for (int dataIndex = 0; dataIndex < sqlResult.size(); dataIndex++) {
			out.println("City Code is:" + sqlResult.get(dataIndex).getCity_code());
			out.println("City Name is:" + sqlResult.get(dataIndex).getCity_name());
		}
		out.close();
	}
}
