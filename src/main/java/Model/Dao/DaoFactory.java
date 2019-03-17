package Model.Dao;

public class DaoFactory {
    private static MangaDao mangaDao;
    private static MapsAddressDao mapsAddressDao;

    public static MangaDao getMangaDao(){
        if(mangaDao == null)
            mangaDao = new MangaDao();
        return mangaDao;
    }
    public static MapsAddressDao getMapsAddressDao(){
        if(mapsAddressDao == null)
            mapsAddressDao = new MapsAddressDao();
        return mapsAddressDao;
    }

}
