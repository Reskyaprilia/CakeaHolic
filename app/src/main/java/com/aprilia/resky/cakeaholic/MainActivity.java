package com.aprilia.resky.cakeaholic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends BaseActDrawer
{
    GridView gvMain;
    List<ItemKue> lstItemKue;
    AdpKue adpKue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_main, fl);

        gvMain = (GridView) findViewById(R.id.gvMain);
        lstItemKue = new ArrayList<>();
        adpKue = new AdpKue(this, 0, lstItemKue);

        gvMain.setAdapter(adpKue);
        gvMain.setOnItemClickListener(itemc);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        refreshList();
    }

    private void refreshList()
    {
        String q = "SELECT * FROM kue";
        String[][] res = SQLiteDataManager.read(this, q);

        lstItemKue.clear();
        for(String[] s : res)
        {
            ItemKue i = new ItemKue(s);
            lstItemKue.add(i);
        }
        adpKue.notifyDataSetChanged();
    }

    AdapterView.OnItemClickListener itemc = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Intent i = new Intent(MainActivity.this, ActDetailKue.class);
            i.putExtra("itemKue", lstItemKue.get(position));
            startActivity(i);
        }
    };
}
