package Servlet;

import Service.JsonService;
import Service.MangaService;
import Service.MapsApiService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AllUsersServlet" ,urlPatterns = "/AddManga")
public class AddServlet extends javax.servlet.http.HttpServlet {

    private MapsApiService mapApi = new MapsApiService();
    private MangaService mangaService = new MangaService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());

        String name = jsonobj.getString("name");
        double price = jsonobj.getDouble("price");
        String sellerName = jsonobj.getString("sellerName");
        String telephone = jsonobj.getString("telephone");
        String adresse = jsonobj.getString("adresse");
        Double volume = jsonobj.getDouble("volume");

        JSONObject jsonLatAndLng = mapApi.GetPlaceLatLng(adresse);
        System.out.println("AFFFichage : "+ jsonLatAndLng);
        JSONObject location = jsonLatAndLng.getJSONArray("candidates").getJSONObject(0).getJSONObject("geometry").getJSONObject("location");

        double lat = (double) location.get("lat");
        double lng = (double) location.get("lng");

        mangaService.AddManga(name,volume,price,sellerName,telephone,adresse,lat,lng);

        JsonObject jsonO = new JsonObject();
        jsonO.addProperty("message","Operation Bien effectué");
        String json = new Gson().toJson(jsonO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }
}
