/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientinterfaces;

/**
 *
 * @author user
 */

 import javafx.beans.property.LongProperty;  
 import javafx.beans.property.SimpleLongProperty;  
 import javafx.beans.property.SimpleStringProperty;  
 import javafx.beans.property.StringProperty;  
 import net.aksingh.owmjapis.CurrentWeather;  
 import net.aksingh.owmjapis.OpenWeatherMap;  
 import org.json.JSONException;  
   
 /**  
  *  
  * @author djordje gavrilovic  
  */  
 public class WeatherModel {  
     
   private final LongProperty cityID = new SimpleLongProperty(this,"cityID");  
   private final StringProperty cityName = new SimpleStringProperty(this,"cityName");  
   private final StringProperty description = new SimpleStringProperty(this,"description");  
   private final StringProperty temperature = new SimpleStringProperty(this,"temperature");  
   private final StringProperty pressure = new SimpleStringProperty(this,"pressure");  
   private final StringProperty humidity = new SimpleStringProperty(this,"humidity");  
   private final StringProperty wind = new SimpleStringProperty(this,"wind");  
   private final StringProperty iconID = new SimpleStringProperty(this,"iconID");  
     
   public long getCityID() {return cityID.get();}  
   public void setCityID(int cityID) {this.cityID.set(cityID);}  
   public LongProperty cityIDProperty() {return cityID;}  
     
   public String getCityName() {return cityName.get();}  
   public void setCityName(String cityName) {this.cityName.set(cityName);}  
   public StringProperty cityNameProperty() {return cityName;}  
     
   public String getDescription() {return description.get();}  
   public void setDescription(String description) {this.description.set(description);}  
   public StringProperty descriptionProperty() {return description;}  
     
   public String getTemperature() {return temperature.get();}  
   public void setTemperature(String temperature) {this.temperature.set(temperature);}  
   public StringProperty temperatureProperty() {return temperature;}  
     
   public String getPressure() {return pressure.get();}  
   public void setPressure(String pressure) {this.pressure.set(pressure);}  
   public StringProperty pressureProperty() {return pressure;}  
     
   public String getHumidity() {return humidity.get();}  
   public void setHumidity(String humidity) {this.humidity.set(humidity);}  
   public StringProperty humidityProperty() {return humidity;}  
     
   public String getWind() {return wind.get();}  
   public void setWind(String wind) {this.wind.set(wind);}  
   public StringProperty windProperty() {return wind;}  
     
   public String getIconID() {return iconID.get();}  
   public void setIconID(String iconID) {this.iconID.set(iconID);}  
   public StringProperty iconIDProperty() {return iconID;}  
       
   public WeatherModel(){}  
   public WeatherModel(int cityID) throws JSONException{  
     this.cityID.set(cityID);  
     getWeather();  
   }  
     
   // gets weather data from OWM  
   public void getWeather() throws JSONException{  
     OpenWeatherMap wm = new OpenWeatherMap("d74a7197cedb9fd9641b120f10b8102b"); // personal OWM API KEY  
     CurrentWeather cw = wm.currentWeatherByCityCode(cityID.longValue());  
       
     this.cityName.set(cw.getCityName());  
     this.description.set(cw.getWeatherInstance(0).getWeatherDescription());  
     float tempC = (((cw.getMainInstance().getTemperature())-32)*5)/9;  
     this.temperature.set(String.format("%.1f",tempC));  
     this.pressure.set(String.valueOf(cw.getMainInstance().getPressure()));  
     this.humidity.set(String.valueOf(cw.getMainInstance().getHumidity()));  
     float kmph = (cw.getWindInstance().getWindSpeed()) * 1.609F;  
     this.wind.set(String.format("%.1f",kmph));  
     this.iconID.set(cw.getWeatherInstance(0).getWeatherIconName());          
   }  
     
   @Override  
   public String toString() {  
     return   
         "ID: " + cityID.get() + "\n" +   
         "City Name: " + cityName.get() + "\n" +   
         "Description: " + description.get() + "\n" +   
         "Temperature: " + temperature.get() + " C" + "\n" +   
         "Pressure: " + pressure.get() + " mbar" + "\n" +   
         "Humidity: " + humidity.get() + " %" + "\n" +  
         "Wind: " + wind.get() + " kmph" + "\n" +  
         "Icon Name: " + iconID.get();  
   }  
     
 }  