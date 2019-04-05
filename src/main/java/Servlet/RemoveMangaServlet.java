package Servlet;

import Service.JsonService;
import Service.MangaService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveMangaServlet", urlPatterns = "/deleteManga")
public class RemoveMangaServlet extends HttpServlet {
    private MangaService mangaService = new MangaService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());

        String name = jsonobj.getString("name");
        double price = jsonobj.getDouble("price");
        String sellerName = jsonobj.getString("sellerName");
        String adresse = jsonobj.getString("adresse");
        Double volume = jsonobj.getDouble("volume");

        mangaService.DeleteManga(name,sellerName,volume,price,adresse);

        JsonObject jsonO = new JsonObject();
        jsonO.addProperty("message","Operation Bien effectué");
        String json = new Gson().toJson(jsonO);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(200);
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
