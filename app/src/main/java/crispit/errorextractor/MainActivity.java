package crispit.errorextractor;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.widget.Button;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ListView listView ;
    private Button sortButton;
    DBHelper mydb;
    private ArrayList<ErrorReport> errorList;
    String busId;
    ListRowAdapter objAdapter;
    int sortState = 1;
    @Override
    public void onClick(View view) {
        if(sortState == 2) {
            Collections.sort(errorList, new Comparator<ErrorReport>() {
                @Override
                public int compare(ErrorReport report1, ErrorReport report2) {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd,hh:mm:ss", Locale.ENGLISH);
                    Date date1=null;
                    Date date2=null;
                    try {
                        date1 = format.parse(report1.getPubdate());
                        date2 = format.parse(report2.getPubdate());
                    }
                    catch(ParseException e){

                    }

                    return (date1.compareTo(date2)) * (-1);

                }
            });
            objAdapter.notifyDataSetChanged();
            sortState=1;
        }
        else if(sortState == 1){
            Collections.sort(errorList, new Comparator<ErrorReport>() {
                @Override
                public int compare(ErrorReport report1, ErrorReport report2) {
                    int a = report1.getGrade();
                    int b = report2.getGrade();
                    if(a>b)
                        return -1;
                    else if (a<b)
                        return 1;
                    else
                        return 0;
                }
            });
            objAdapter.notifyDataSetChanged();
            sortState=2;
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView busList = new ListView(this.getBaseContext());
        sortButton = (Button) findViewById(R.id.sortButton);
        //busId = "Vin_Num_001";
        mydb = new DBHelper(this);

        listView = (ListView) findViewById(R.id.busList);

        errorList = mydb.getAllReportsDetailed();

        setAdapterToListview();

        sortButton.setOnClickListener(this);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                Intent intent = new Intent(view.getContext(),DetailedErrorReport.class);
                Bundle bundle = new Bundle();
                bundle.putString("errorId",errorList.get(position).getId());
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });


    }


    public void setAdapterToListview() {
        objAdapter = new ListRowAdapter(MainActivity.this,
                R.layout.row, errorList);
        listView.setAdapter(objAdapter);

    }
}
