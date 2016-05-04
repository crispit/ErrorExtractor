package crispit.errorextractor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView ;
    DBHelper mydb;
    private ArrayList<String> list;
    String busId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView busList = new ListView(this.getBaseContext());
        busId = "Vin_Num_001";
        mydb = new DBHelper(this);

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
