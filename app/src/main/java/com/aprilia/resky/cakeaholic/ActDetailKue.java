package com.aprilia.resky.cakeaholic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprilia.resky.cakeaholic.classes.ClassUtil;
import com.aprilia.resky.cakeaholic.db.SQLiteDataManager;

/**
 * Created by limakali on 5/29/2017.
 */

public class ActDetailKue extends AppCompatActivity implements View.OnClickListener
{
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imgKue, imgFavorite;
    TextView txtBahan, txtCara;
    ItemKue i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_detail_kue);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        imgKue = (ImageView) findViewById(R.id.imgKue);
        imgFavorite = (ImageView) findViewById(R.id.imgFavorit);
        txtBahan = (TextView) findViewById(R.id.txtBahan);
        txtCara = (TextView) findViewById(R.id.txtCara);
        i = (ItemKue) getIntent().getSerializableExtra("itemKue");

        collapsingToolbarLayout.setTitle(i.getNamaKue());
        imgFavorite.setOnClickListener(this);
        imgKue.setImageBitmap(ClassUtil.getBitmapFromAsset(this, i.getGambar()));
        txtBahan.setText(i.getBahan());
        txtCara.setText(i.getCaraPembuatan());
        setImageFavorit();
    }

    private void setImageFavorit()
    {
        if (i.getFavorit().equals("0"))
            imgFavorite.setImageBitmap(ClassUtil.getBitmapFromAsset(this, "favorite_off.png"));
        else
            imgFavorite.setImageBitmap(ClassUtil.getBitmapFromAsset(this, "favorite_on.png"));
    }

    @Override
    public void onClick(View v)
    {
        if (i.getFavorit().equals("0"))
        {
            String q = "UPDATE kue SET favorit = 1 WHERE id_kue = '" + i.getIdKue() + "'";
            SQLiteDataManager.write(this, q);
            i.setFavorit("1");
            setImageFavorit();
        }
        else
        {
            String q = "UPDATE kue SET favorit = 0 WHERE id_kue = '" + i.getIdKue() + "'";
            SQLiteDataManager.write(this, q);
            i.setFavorit("0");
            setImageFavorit();
        }
    }
}
