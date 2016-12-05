package com.springapp.mvc.integration.shop.dto;

import java.math.BigDecimal;

/**
 * Created by Иван on 05.12.2016.
 */
public class MusicCD extends Item {
    private static final long serialVersionUID = -1218825207603759861L;

    private String artist;

    public MusicCD() {
        super();
    }

    public MusicCD(String title, String publisher, BigDecimal price, int yearPublished, String artist) {
        super(title, publisher, price, yearPublished);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        MusicCD other = (MusicCD) obj;
        if (artist == null) {
            if (other.artist != null)
                return false;
        } else if (!artist.equals(other.artist))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + " MusicCD [artist=" + artist + "]";
    }
}
