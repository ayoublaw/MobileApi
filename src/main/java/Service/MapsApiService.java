package Service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MapsApiService {
   private String Key = "AIzaSyAejI8898winqzlekeYkhyJ2m1ZEPb3im0";
   private JSONObject GetJsonFromUrl(String url){
      url = url.replaceAll(" ","%20");
      URL Url;
      String tmp = "";
      String line;
      try {
         Url = new URL(url);
         HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
         conn.setDoOutput(true);
         conn.setRequestMethod("GET");
         conn.setRequestProperty("Content-Type", "application/json");

         BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
         while ((line = br.readLine()) != null) {
            tmp += line;
         }
         JSONObject obj = new JSONObject(tmp);
         conn.disconnect();
         return obj;

      } catch (IOException e) {
         e.printStackTrace();
      }
      return null;
   }

   public JSONObject GetPlaceLatLng(String adresse){
      JSONObject json = this.GetJsonFromUrl("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?input="+adresse+"&inputtype=textquery&fields=geometry&key="+Key);
      return json;
   }
}
