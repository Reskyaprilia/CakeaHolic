package com.aprilia.resky.cakeaholic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprilia.resky.cakeaholic.classes.ClassUtil;
import com.aprilia.resky.cakeaholic.db.SQLiteDataManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by limakali on 5/29/2017.
 */

public class ActKueNegara extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    GridView gvKueNegara;
    List<ItemKue> lstItemKue;
    AdpKue adpKue;
    String idNegara;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_kue_negara);

        gvKueNegara = (GridView) findViewById(R.id.gvKueNegara);
        lstItemKue = new ArrayList<>();
        adpKue = new AdpKue(this, 0, lstItemKue);
        idNegara = getIntent().getStringExtra("idNegara");

        gvKueNegara.setAdapter(adpKue);
        gvKueNegara.setOnItemClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshList();
    }

    private void refreshList()
    {
        String q = "SELECT * FROM kue WHERE id_negara = '"+ idNegara + "'";
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
