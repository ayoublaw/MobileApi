package Service;

import Model.Dao.DaoFactory;
import Model.Dao.MangaDao;
import Model.Entity.Manga;
import Model.Entity.MapsAddress;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MangaService {
    public Manga AddManga(String name, Double volume, double price, String sellerName, String telephone, String adresse, double lat, double lng, String imageUrl) {
        Manga m = new Manga();
        MapsAddress mp = new MapsAddress();
        mp.setAddress(adresse);
        mp.setLat(lat);
        mp.setLng(lng);

        m.setMangaName(name);
        m.setPrice(price);
        m.setSellerName(sellerName);
        m.setTelephone(telephone);
        m.setAddress(mp);
        m.setVolume(volume);
        m.setImageUrl(imageUrl);

        DaoFactory.getMapsAddressDao().Save(mp);
        DaoFactory.getMangaDao().Save(m);
        return m;
    }
    public List<Manga> SelectMangaNearbyPointWithoutVolume(String name, double lat, double lng){
        return DaoFactory.getMangaDao().getMangaNearbyPointWithoutVolume(name, lat, lng);
    }
    public List<Manga> SelectMangaNearbyPointWithVolume(String name,double volume, double lat, double lng){
        return DaoFactory.getMangaDao().getMangaNearbyPointWithVolume(name, volume, lat, lng);
    }
    public List<Manga> SelectAllManga(){
        return DaoFactory.getMangaDao().selectAll();
    }
    public void DeleteManga(String mangaName,String sellerName,Double volume,double price, String adresse){
        Manga m = DaoFactory.getMangaDao().getManga(mangaName,sellerName,volume,price,adresse);
        DaoFactory.getMangaDao().remove(m);
    }
}
