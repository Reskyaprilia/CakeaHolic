package com.aprilia.resky.cakeaholic;

import java.io.Serializable;

/**
 * Created by limakali on 5/29/2017.
 */

public class ItemKue implements Serializable
{
    String idKue, namaKue, bahan, caraPembuatan, gambar, idkategori, favorit;

    public ItemKue(String[] s)
    {
        idKue = s[0];
        namaKue = s[1];
        bahan = s[2];
        caraPembuatan = s[3];
        gambar = s[4];
        idkategori = s[5];
        favorit = s[6];
    }

    public String getFavorit()
    {
        return favorit;
    }

    public void setFavorit(String favorit)
    {
        this.favorit = favorit;
    }

    public String getIdKue()
    {
        return idKue;
    }

    public void setIdKue(String idKue)
    {
        this.idKue = idKue;
    }

    public String getNamaKue()
    {
        return namaKue;
    }

    public void setNamaKue(String namaKue)
    {
        this.namaKue = namaKue;
    }

    public String getBahan()
    {
        return bahan;
    }

    public void setBahan(String bahan)
    {
        this.bahan = bahan;
    }

    public String getCaraPembuatan()
    {
        return caraPembuatan;
    }

    public void setCaraPembuatan(String caraPembuatan)
    {
        this.caraPembuatan = caraPembuatan;
    }

    public String getGambar()
    {
        return gambar;
    }

    public void setGambar(String gambar)
    {
        this.gambar = gambar;
    }

    public String getIdkategori()
    {
        return idkategori;
    }

    public void setIdkategori(String idkategori)
    {
        this.idkategori = idkategori;
    }
}
