package servletTest;

import java.io.Serializable;

/* 為符合javaBeans需求，宣告實作Serializable介面 */
public class CityCode implements Serializable{
	
	/* IDE能自動協助生成的項目，並不會使用到 */
	private static final long serialVersionUID = 1L;
	
	/* 屬性宣告private，透過public的setter/getter進行存取/修改 */
	private String city_code;
	private String city_name;
	
	/* 無參數建構子 */
	CityCode(){
		
	}
	
	/* 帶參數建構子 */
	CityCode(String paramCityCode, String paramCityName) {
		this.city_code = paramCityCode;
		this.city_name = paramCityName;
	}
	
	/* setter&getter */
	public String getCity_code() {
		return city_code;
	}
	
	public void setCity_code(String paramCityCode) {
		this.city_code = paramCityCode;
	}
	
	public String getCity_name() {
		return city_name;
	}
	
	public void setCity_name(String paramCityName) {
		this.city_name = paramCityName;
	}
}
