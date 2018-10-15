package com.example.tumnova.nytimes.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;

import com.example.tumnova.nytimes.DataUtils;
import com.example.tumnova.nytimes.NTAdapter;
import com.example.tumnova.nytimes.NTItemDecoretor;
import com.example.tumnova.nytimes.R;

public class NewsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        return true;
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new NTAdapter(this, DataUtils.generateNews()));
        recyclerView.setLayoutManager(getLayoutManager());

        NTItemDecoretor itemDecorator = new NTItemDecoretor();
        recyclerView.addItemDecoration(itemDecorator);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            float dpWidth = metrics.widthPixels / metrics.density;
            int noOfColumn = (int) dpWidth / 250;
            return new GridLayoutManager(this, noOfColumn);
        } else {
            return new LinearLayoutManager(this);
        }
    }
}
