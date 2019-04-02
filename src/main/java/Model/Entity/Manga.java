package Model.Entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Manga")
public class Manga implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column
    private String mangaName;

    @Column
    private String sellerName;

    @Column
    private double price;

    @Column
    private String telephone;

    @Column
    private Double volume;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    @Column
    private String ImageUrl;

    @OneToOne
    private MapsAddress address;

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getMangaName() {
        return mangaName;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public MapsAddress getAddress() {
        return address;
    }

    public void setAddress(MapsAddress address) {
        this.address = address;
    }
}

