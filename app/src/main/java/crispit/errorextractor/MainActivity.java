package crispit.errorextractor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    DbHelper mydb;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView busList = new ListView(this.getBaseContext());

        mydb = new DbHelper(this);
        //mydb.insertErrorReport("1", "Trasig Dörr", "Fungerar inte alls.", "Buss1", "2016-03-22,15:22", 3, "a", "a", "a", "a", "a", "a",
                 //"a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a", "a");
        //mydb.insertErrorReport("2","Trasig Motor","Fungerar en del.","Buss1","2016-02-22,10:00",4);
        //mydb.insertErrorReport("3","Trasigt Däck","Total paj.","Buss2","2016-04-22,15:22",5);
        listView = (ListView) findViewById(R.id.busList);

        list=new ArrayList<>();

        list = mydb.getAllBuses();

        setAdapterToListview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),BusInfo.class);
                Bundle bundle = new Bundle();
                bundle.putString("busId",list.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }

    public void setAdapterToListview() {
        CustomListAdapter objAdapter = new CustomListAdapter(MainActivity.this,
                R.layout.custom_list, list);
        listView.setAdapter(objAdapter);
    }
}
