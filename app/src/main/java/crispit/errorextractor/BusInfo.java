package crispit.errorextractor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mikael on 2016-04-18.
 */
public class BusInfo extends AppCompatActivity {

    //Sortera efter gradering, allvarligast först

    ArrayList<ErrorReport> errorList;
    ListView listView;
    DBHelper mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businfo);

        String busId = getIntent().getStringExtra("busId");

        mydb = new DBHelper(this);
        errorList = mydb.getBusReports(busId);

        listView = (ListView) findViewById(R.id.businfoview);
        setAdapterToListview();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), DetailedErrorReport.class);
                Bundle bundle = new Bundle();
                bundle.putString("errorId", errorList.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
    }

    public void setAdapterToListview() {
        ListRowAdapter objAdapter = new ListRowAdapter(BusInfo.this,
                R.layout.row, errorList);
        listView.setAdapter(objAdapter);
    }
}
