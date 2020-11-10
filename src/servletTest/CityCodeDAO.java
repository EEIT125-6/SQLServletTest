package servletTest;

import java.util.List;

public interface CityCodeDAO {
	/* 新增 */
	
	/* 修改 */
	
	/* 刪除 */
	public Boolean DeleteCityCode(String paramCityCode);
	
	/* 查詢 */
	public List<CityCode> SelectCityCode(String paramCityCode, String paramCityName);
}
