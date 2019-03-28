package Servlet;

import Model.Entity.Manga;
import Service.MangaService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "AllMangaAndVolumeServlet", urlPatterns = "/AllMangaAndVolume")
public class AllMangaAndVolumeServlet extends HttpServlet {
    MangaService mangaService = new MangaService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Manga> mangas = mangaService.SelectAllManga();
        Map<String,List<Double>> map = new HashMap<>();
        for(Manga manga : mangas){
            if(!map.containsKey(manga.getMangaName()) && manga.getVolume() != null){
                map.put(manga.getMangaName(), new ArrayList<>(Arrays.asList(manga.getVolume())));
            }
            else if(map.containsKey(manga.getMangaName()) && manga.getVolume() != null){
                List<Double> volumes = map.get(manga.getMangaName());
                if(!volumes.contains(manga.getVolume()))
                    volumes.add(manga.getVolume());
            }
        }
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(map);

        response.setContentType("application/json");
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
