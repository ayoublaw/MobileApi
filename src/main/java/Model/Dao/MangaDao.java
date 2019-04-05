package Model.Dao;

import Model.Entity.Manga;
import Model.Entity.MapsAddress;
import Util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MangaDao extends Dao<Manga>{
   public List<Manga> getMangaNearbyPointWithoutVolume(String name,double lat, double lng){
    SessionFactory factory = HibernateUtil.getSessionFactory();
    Session s = factory.openSession();
    Query q = s.createQuery(
            "select m from "
                    +Manga.class.getSimpleName()+" as m JOIN m.address mp " +
                    "where m.mangaName = :z "+
                    "and SQRT(\n" +
                    "    POW(69.1 * (:x - mp.lat), 2) +\n" +
                    "    POW(69.1 * (mp.lng - :y) * COS(:x / 57.3), 2)) < 25");
            q.setParameter("x",lat);q.setParameter("y",lng);q.setParameter("z",name);
            List<Manga> list = q.list();
            s.close();
            return list;
   }
   public List<Manga> getMangaNearbyPointWithVolume(String name,Double volume,double lat, double lng){
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session s = factory.openSession();
        Query q = s.createQuery(
                "select m from "
                        +Manga.class.getSimpleName()+" as m JOIN m.address mp " +
                        "where m.mangaName = :z "+
                        "and m.volume = :a "+
                        "and SQRT(\n" +
                        "    POW(69.1 * (:x - mp.lat), 2) +\n" +
                        "    POW(69.1 * (mp.lng - :y) * COS(:x / 57.3), 2)) < 25");
        q.setParameter("x",lat);q.setParameter("a",volume);q.setParameter("y",lng);q.setParameter("z",name);
        List<Manga> list = q.list();
        s.close();
        return list;
   }
   public Manga getManga(String mangaName,String sellerName,Double volume,double price, String adresse){
       SessionFactory factory = HibernateUtil.getSessionFactory();
       Session s = factory.openSession();
       Query q = s.createQuery(
               "select manga from "
                       +Manga.class.getSimpleName()+" as manga JOIN manga.address mp " +
                       "where manga.mangaName = :a "+
                       "and manga.volume = :b "+
                       "and manga.sellerName = :x "+
                       "and manga.price = :y "+
                       "and mp.Address = :z "
       );
       q.setParameter("a",mangaName);q.setParameter("b",volume);q.setParameter("x",sellerName);q.setParameter("y",price);q.setParameter("z",adresse);
       List<Manga> list = q.list();
       s.close();
       return list.get(0);
   }

}
