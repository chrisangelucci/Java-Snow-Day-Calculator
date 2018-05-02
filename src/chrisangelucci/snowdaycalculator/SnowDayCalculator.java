package chrisangelucci.snowdaycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * Simple class that parses information from snowdaycalculator.com and returns it in a usable format.<br>
 * By: <b>Chris Angelucci</b>
 */
public class SnowDayCalculator {
	
	private String zipcode;
	private int snowdays;
	private SchoolType schoolType;
	
	private final String BASE_URL = "http://www.snowdaycalculator.com/prediction.php";
	
	public SnowDayCalculator(String zipcode, int snowdays, SchoolType schoolType){
		this.zipcode = zipcode;
		this.snowdays = snowdays;
		this.schoolType = schoolType;
	}
	
	/**
	 * 
	 * Used to get the prediction using the parameters of the object.
	 * @return Prediction value, this is the percentage used on the website, however, this value can be larger than 99 and less than 0.
	 * @throws IOException
	 */
	public long getPrediction() throws IOException{
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		int dateInt = Integer.valueOf(format.format(date)) + 1;
		
		URL url = new URL(BASE_URL + "?zipcode=" + this.zipcode + "&snowdays=" + this.snowdays + "&extra=" + this.schoolType.getExtra());
        BufferedReader in = new BufferedReader(
        new InputStreamReader(url.openStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null){
        	if(inputLine.startsWith(" theChance[" + dateInt + "]")){
        		long chance = Math.round(Double.valueOf(inputLine.split("=")[1].split(";")[0].replaceAll(" ", "")));
        		return chance;
        	}
            
        }
        in.close();
        return 0;
	}
	
	public String getZipcode(){
		return this.zipcode;
	}
	
	public int getSnowdays(){
		return this.snowdays;
	}
	
	public SchoolType getSchoolType(){
		return this.schoolType;
	}
	
	enum SchoolType {
		PUBLIC(0),URBAN_PUBLIC(0.4),RURAL_PUBLIC(-0.4),PRIVATE_PREP(-0.4),BOARDING(1);
		private double extra;
		SchoolType(double extra){
			this.extra = extra;
		}
		public double getExtra(){
			return this.extra;
		}
	}
	
	
	
}
