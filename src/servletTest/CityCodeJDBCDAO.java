package servletTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityCodeJDBCDAO implements CityCodeDAO{
	
	/* 定義連線物件 */
	private Connection connection0;
	
	/* 建構子 */
	CityCodeJDBCDAO(Connection paramConnection) {
		this.connection0 = paramConnection;
	}
	
	/* 新增 */
	
	/* 修改 */
	
	/* 刪除 */
	
	/* 查詢 */
	public List<CityCode> SelectCityCode(String paramCityCode, String paramCityName) {
		List<CityCode> result = new ArrayList<>();
		
		if(paramCityName.equals("")) {
			try (PreparedStatement preStmt0 = connection0.prepareStatement("SELECT * FROM dbo.CityCode WHERE city_code = ? ")) {
				/* begin transaction */ 
				connection0.setAutoCommit(false);
				
				String queryCityCode = paramCityCode;
				preStmt0.setString(1, queryCityCode);
				
				if (preStmt0 != null) {
					/* 執行查詢 */
		            ResultSet rs0 = preStmt0.executeQuery();
		            
		            while (rs0.next()) {
		            	CityCode resultItem = new CityCode();
		            	resultItem.setCity_code(rs0.getString("city_code"));
		            	resultItem.setCity_name(rs0.getString("city_name"));
		            	result.add(resultItem);
		            }
		            rs0.close();
		            
		            /* commit transaction */
		            connection0.commit();
				}
			} catch (SQLException sqlE) {
				System.out.println(sqlE);
				try {
					/* commit roll back*/
					connection0.rollback();
				} catch (SQLException sqlE1) {
					System.out.println(sqlE1);
				}
			}
		} else if (paramCityCode.equals("")) {
			try (PreparedStatement preStmt0 = connection0.prepareStatement("SELECT * FROM dbo.CityCode WHERE city_name LIKE ? ")) {
				/* begin transaction */ 
				connection0.setAutoCommit(false);
				
				String queryCityName = "%" + paramCityName + "%";
				preStmt0.setString(1, queryCityName);
				
				if (preStmt0 != null) {
					/* 執行查詢 */
		            ResultSet rs0 = preStmt0.executeQuery();
		            
		            while (rs0.next()) {
		            	CityCode resultItem = new CityCode();
		            	resultItem.setCity_code(rs0.getString("city_code"));
		            	resultItem.setCity_name(rs0.getString("city_name"));
		            	result.add(resultItem);
		            }
		            rs0.close();
		            
		            /* commit transaction */
		            connection0.commit();
				}
			} catch (SQLException sqlE) {
				System.out.println(sqlE);
				try {
					/* commit roll back*/
					connection0.rollback();
				} catch (SQLException sqlE1) {
					System.out.println(sqlE1);
				}
			}
		}
		
		return result;
	}
}
