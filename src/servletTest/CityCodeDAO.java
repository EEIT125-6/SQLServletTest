package servletTest;

import java.util.List;

public interface CityCodeDAO {
	/* 新增 */
	public List<CityCode> InsertCityCode(String paramCityCode, String paramCityName);
	
	/* 修改 */
	public List<CityCode> UpdateCityCode(String paramCityCode, String paramCityName);
	
	/* 刪除 */
	public Boolean DeleteCityCode(String paramCityCode);
	
	/* 查詢 */
	public List<CityCode> SelectCityCode(String paramCityCode, String paramCityName);
}
