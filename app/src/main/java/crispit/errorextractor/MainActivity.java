package crispit.errorextractor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    //ErrorDbHelper mydb;
    int clickPosition;
    ArrayAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView busList = new ListView(this.getBaseContext());

        //mydb = new ErrorDbHelper(this);

        listView = (ListView) findViewById(R.id.busList);

        list=new ArrayList<String>();

        //list = mydb.getAllBuses(); - Lägger till alla bussar i listan
        list.add("Buss 1");
        list.add("Buss 2");
        list.add("Buss 4");
        list.add("Buss 7");
        //TODO: Skapa metoden getAllBuses i DbHelper

        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),BusInfo.class);
         //TODO: Skapa en bundle och skicka med data för vilken buss som ska öppnas
                startActivity(intent);
            }

        });

    }


}
