package com.aprilia.resky.cakeaholic;

/**
 * Created by limakali on 5/29/2017.
 */

public class ItemNegara
{
    String idNegara, namaNegara;

    public ItemNegara(String[] s)
    {
        idNegara = s[0];
        namaNegara = s[1];
    }

    @Override
    public String toString()
    {
        return namaNegara;
    }

    public String getIdNegara()
    {
        return idNegara;
    }

    public void setIdNegara(String idNegara)
    {
        this.idNegara = idNegara;
    }

    public String getNamaNegara()
    {
        return namaNegara;
    }

    public void setNamaNegara(String namaNegara)
    {
        this.namaNegara = namaNegara;
    }
}
