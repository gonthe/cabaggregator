package com.kiwi.cabaggregator.uber.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kiwi.cabaggregator.MapsActivity;
import com.kiwi.cabaggregator.R;
import com.kiwi.cabaggregator.uber.entities.Time;

import java.util.List;


public class UberListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_list);

        List<Time> times = MapsActivity.getTimes();
       // List<Product> products = MapsActivity.getProducts();

        ArrayAdapter<Time> adapter = new ArrayAdapter<Time>(this,
                android.R.layout.simple_list_item_1, times);
        this.setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_uber_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        // ListView Clicked item value
        Time  itemValue    = (Time) l.getItemAtPosition(position);

        Toast.makeText(this, itemValue.getProductId(), Toast.LENGTH_SHORT).show();

    }
}
