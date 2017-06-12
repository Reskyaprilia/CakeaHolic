package com.aprilia.resky.cakeaholic;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.aprilia.resky.cakeaholic.db.SQLiteDataManager;

public class BaseActDrawer extends AppCompatActivity implements AdapterView.OnItemClickListener
{

	DrawerLayout dl;
	ActionBarDrawerToggle abdToggle;
	Toolbar toolbar;
	NavigationView navView;
	
	protected FrameLayout fl;
	ListView lvDrawer;
	List<ItemNegara> lstItemNegara;
	ArrayAdapter<ItemNegara> adpNegara;
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lyt_toolbar_nav);
		
		toolbar = (Toolbar)findViewById(R.id.toolbar);
		lvDrawer = (ListView)findViewById(R.id.lvDrawer);
		lstItemNegara = new ArrayList<>();
		adpNegara = new ArrayAdapter<ItemNegara>(this, android.R.layout.simple_list_item_1, lstItemNegara);
		dl = (DrawerLayout)findViewById(R.id.lytDrawer);
		navView = (NavigationView)findViewById(R.id.navigation_view);
		abdToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
		fl = (FrameLayout)findViewById(R.id.content_frame);

        toolbar.setTitle(R.string.app_name);
		dl.addDrawerListener(abdToggle);
		lvDrawer.setAdapter(adpNegara);
		lvDrawer.setOnItemClickListener(this);

		refreshList();
	}

    private void refreshList()
	{
		String q = "SELECT * FROM negara";
		String[][] res = SQLiteDataManager.read(this, q);

		lstItemNegara.clear();
		lstItemNegara.add(new ItemNegara(new String[]{"0", "Favorite"}));

		for(String[] s : res)
		{
			ItemNegara i = new ItemNegara(s);
			lstItemNegara.add(i);
		}
		adpNegara.notifyDataSetChanged();
	}

	@Override
	protected void onPostCreate(@Nullable Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		abdToggle.syncState();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
        if (position == 0)
            startActivity(new Intent(this, ActFavorite.class));
        else
        {
            Intent i = new Intent(this, ActKueNegara.class);
            i.putExtra("idNegara", lstItemNegara.get(position).getIdNegara());
            startActivity(i);
        }
        dl.closeDrawers();
	}

	@Override
	public void onBackPressed()
	{
//		if (dl.)
		super.onBackPressed();
	}
}
