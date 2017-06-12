package com.aprilia.resky.cakeaholic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.aprilia.resky.cakeaholic.db.SQLiteDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limakali on 5/29/2017.
 */

public class ActFavorite extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    Toolbar toolbar;
    GridView gvFavorite;
    List<ItemKue> lstItemKue;
    AdpKue adpKue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_favorite);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gvFavorite = (GridView) findViewById(R.id.gvFavorit);
        lstItemKue = new ArrayList<>();
        adpKue = new AdpKue(this, 0, lstItemKue);

        toolbar.setTitle("Favorite");
        gvFavorite.setAdapter(adpKue);
        gvFavorite.setOnItemClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshList();
    }

    private void refreshList()
    {
        String q = "SELECT * FROM kue WHERE favorit = 1";
        String[][] res = SQLiteDataManager.read(this, q);

        lstItemKue.clear();
        for(String[] s : res)
        {
            ItemKue i = new ItemKue(s);
            lstItemKue.add(i);
        }
        adpKue.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Intent i = new Intent(this, ActDetailKue.class);
        i.putExtra("itemKue", lstItemKue.get(position));
        startActivity(i);
    }
}
