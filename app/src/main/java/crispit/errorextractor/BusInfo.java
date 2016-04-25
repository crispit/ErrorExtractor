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
    DbHelper mydb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businfo);

        String busId = getIntent().getStringExtra("busId");

        mydb = new DbHelper(this);
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

    //testmetod som skapar en lista med errorreports
    public ArrayList<ErrorReport> createErrorList(){

        ArrayList<ErrorReport> list = new ArrayList<>();
        //ErrorReport er1 = new ErrorReport("Buss 1", "Trasig Vindruta", "En stor spricka genom hela rutan.", "2016-04-20", 4);
        //ErrorReport er4 = new ErrorReport("Buss 2", "Trasig Motor", "Inte najs", "2016-03-05", 2);
        //ErrorReport er2 = new ErrorReport("Buss 4", "Punktering", "Det bara small. Står parkerad nere vid Frihamnen.", "2016-04-22", 5);
        //ErrorReport er3 = new ErrorReport("Buss 5", "Trasiga dörrar som är helt jävla paj. Fattar ingenting ju. Knas kalas.", "BAKALALALALDLLALALALALALFLFSKJSFKAJBKJAFBAFWJKFEKJAEFKJAEFBJAFEJBKFBJKFAEKAEFBJKAEFBJKAFJBKAEFJBAFEBJKAFEBJAEFAAFBAAFEBJAEFFEABFAEKFEKEAFJAFAEFJBKAEF jkEFJKBAEFJKBAEFKJBAEGegihweoiwgionwekjegjkegbjksgkjbsjbksbglwoegoiwgebsjkdfbjksbgbkskeugbsjbksjbkegkjsgbuk", "2016-04-22", 5);
        //list.add(er1);
        //list.add(er2);
        //list.add(er3);
        //list.add(er4);
        return list;
    }
}
