package crispit.errorextractor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Mikael on 2016-04-18.
 */
public class BusInfo extends AppCompatActivity {

    //Rubrik med allvarlighetsgrad
    //Lista med zon, gradering, symptom, kommentar
    //Lista i lista
    //Sortera efter gradering, allvarligast först

    ArrayList<ErrorReport> errorList;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businfo);

        errorList = createErrorList();

        listView = (ListView) findViewById(R.id.businfoview);
        setAdapterToListview();

        //mydb = new ErrorDbHelper(this);
        //arrayOfList = mydb.getErrorReports(busId);
        //TODO: Skapa en metod i DbHelper som returnerar en lista med alla errorreports för bussen



    }

    public void setAdapterToListview() {
        ListRowAdapter objAdapter = new ListRowAdapter(BusInfo.this,
                R.layout.row, errorList);
        listView.setAdapter(objAdapter);
    }

    //testmetod som skapar en lista med errorreports
    public ArrayList<ErrorReport> createErrorList(){

        ArrayList<ErrorReport> list = new ArrayList<>();
        ErrorReport er1 = new ErrorReport("Buss 1", "Trasig Vindruta", "En stor spricka genom hela rutan.", "2016-04-20", 4);
        ErrorReport er2 = new ErrorReport("Buss 4", "Punktering", "Det bara small. Står parkerad nere vid Frihamnen.", "2016-04-22", 5);
        list.add(er1);
        list.add(er2);
        return list;
    }
}
