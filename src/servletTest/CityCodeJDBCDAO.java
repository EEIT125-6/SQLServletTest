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
	public List<CityCode> InsertCityCode(String paramCityCode, String paramCityName) {
		List<CityCode> result = new ArrayList<>();
		
		try (PreparedStatement preStmt0 = connection0.prepareStatement("INSERT INTO dbo.CityCode (city_code, city_name) VALUES(?, ?)")) {
			/* begin transaction */ 
			connection0.setAutoCommit(false);
			
			String updateCityCode = paramCityCode;
			preStmt0.setString(1, updateCityCode);
			String updateCityName = paramCityName;
			preStmt0.setString(2, updateCityName);
			
			/* 執行更新 */
			if (preStmt0 != null) {
				/* 加入批次 */
                preStmt0.addBatch();
                /* 清空變數 */
                preStmt0.clearParameters();
                // 執行executeBatch
                preStmt0.executeBatch();
                /* 清空batch */
                preStmt0.clearBatch();
				
				/* commit transaction */
	            connection0.commit();
	            
	            CityCode resultItem = new CityCode();
	            resultItem.setCity_code(paramCityCode);
	            resultItem.setCity_name(paramCityName);
	            result.add(resultItem);
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
		
		return result; 
	}
	
	/* 修改 */
	public List<CityCode> UpdateCityCode(String paramCityCode, String paramCityName) {
		List<CityCode> result = new ArrayList<>();
		
		try (PreparedStatement preStmt0 = connection0.prepareStatement("UPDATE dbo.CityCode SET city_name = ? WHERE city_code = ? ")) {
			/* begin transaction */ 
			connection0.setAutoCommit(false);
			
			String updateCityName = paramCityName;
			preStmt0.setString(1, updateCityName);
			String updateCityCode = paramCityCode;
			preStmt0.setString(2, updateCityCode);
			
			/* 執行修改 */
			if (preStmt0 != null) {
				/* 加入批次 */
                preStmt0.addBatch();
                /* 清空變數 */
                preStmt0.clearParameters();
                // 執行executeBatch
                preStmt0.executeBatch();
                /* 清空batch */
                preStmt0.clearBatch();
				
				/* commit transaction */
	            connection0.commit();
	            
	            CityCode resultItem = new CityCode();
	            resultItem.setCity_code(paramCityCode);
	            resultItem.setCity_name(paramCityName);
	            result.add(resultItem);
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
		
		return result;
	}
	
	/* 刪除 */
	public Boolean DeleteCityCode(String paramCityCode) {
		Boolean result = false;

		try (PreparedStatement preStmt0 = connection0.prepareStatement("DELETE FROM dbo.CityCode WHERE city_code = ? ")) {
			/* begin transaction */ 
			connection0.setAutoCommit(false);
			
			String deleteCityCode = paramCityCode;
			preStmt0.setString(1, deleteCityCode);
			
			/* 執行刪除 */
			if (preStmt0 != null) {
				/* 加入批次 */
                preStmt0.addBatch();
                /* 清空變數 */
                preStmt0.clearParameters();
                // 執行executeBatch
                preStmt0.executeBatch();
                /* 清空batch */
                preStmt0.clearBatch();
				
				/* commit transaction */
	            connection0.commit();
	            result = true;
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
		
		return result;
	}
	
	/* 查詢 */
	public List<CityCode> SelectCityCode(String paramCityCode, String paramCityName) {
		List<CityCode> result = new ArrayList<>();
		
		if(paramCityName.equals("")) {
			try (PreparedStatement preStmt0 = connection0.prepareStatement("SELECT * FROM dbo.CityCode WHERE city_code = ? ")) {
				/* begin transaction */ 
				connection0.setAutoCommit(false);
				
				String selectCityCode = paramCityCode;
				preStmt0.setString(1, selectCityCode);
				
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
