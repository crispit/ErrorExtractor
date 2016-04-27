package crispit.errorextractor;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mikael on 2016-04-18.
 */
public class DetailedErrorReport extends AppCompatActivity {

    ArrayList<String> detailedList;
    ListView listView;
    DBHelper mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailederrorreport);

        String errorId = getIntent().getStringExtra("errorId");

        mydb = new DBHelper(this);
        detailedList = new ArrayList<>();

        Cursor res = mydb.getData(errorId);
        res.moveToFirst();

        for(int i=0;i<res.getColumnCount();i++){
            detailedList.add(res.getColumnName(i)+ ": " + res.getString(i));
        }

        listView = (ListView) findViewById(R.id.detailedErrorReportView);
        setAdapterToListview();
    }

    public void setAdapterToListview() {
        CustomListAdapter objAdapter = new CustomListAdapter(DetailedErrorReport.this,
                R.layout.custom_list, detailedList);
        listView.setAdapter(objAdapter);
    }

}
