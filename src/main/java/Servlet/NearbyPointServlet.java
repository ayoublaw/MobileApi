package Servlet;

import Model.Entity.Manga;
import Service.JsonService;
import Service.MangaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "NearbyPointServlet", urlPatterns = "/SelectMangas")
public class NearbyPointServlet extends HttpServlet {
    private MangaService m = new MangaService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject jsonobj = JsonService.getJsonObjectFromBufferReader(request.getReader());
        String name = jsonobj.getString("name");
        double lat = jsonobj.getDouble("lat");
        double lng = jsonobj.getDouble("lng");

        List<Manga> mangas = m.SelectMangaNearbyPoint(name,lat,lng);

        JSONObject jsonO = new JSONObject();
        jsonO.put("resultas",mangas);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(jsonO);

        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
