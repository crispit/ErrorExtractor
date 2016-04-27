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
        //new RetrieveBusData().execute("Test");
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

    private class RetrieveBusData extends AsyncTask<String, String, String> {

        private Exception exception;

        protected String doInBackground(String... str) {
            try {

                mydb.insertErrorReport("5", "Gasen funkar!!", "OMG WEEY!!", "Vin_Num_001", "2016-02-25,05:00", 1,BusData.getBusInfo(busId,"Accelerator_Pedal_Position"),
                        BusData.getBusInfo(busId,"Ambient_Temperature"), BusData.getBusInfo(busId,"At_Stop"),
                        BusData.getBusInfo(busId,"Cooling_Air_Conditioning"), BusData.getBusInfo(busId,"Driver_Cabin_Temperature"),
                        BusData.getBusInfo(busId,"Fms_Sw_Version_Supported"), BusData.getBusInfo(busId,"GPS"),
                        BusData.getBusInfo(busId,"GPS2"), BusData.getBusInfo(busId,"GPS_NMEA"),
                        BusData.getBusInfo(busId,"Journey_Info"), BusData.getBusInfo(busId,"Mobile_Network_Cell_Info"),
                        BusData.getBusInfo(busId,"Mobile_Network_Signal_Strength"), BusData.getBusInfo(busId,"Next_Stop"),
                        BusData.getBusInfo(busId,"Offroute"), BusData.getBusInfo(busId,"Online_Users"),
                        BusData.getBusInfo(busId,"Opendoor"), BusData.getBusInfo(busId,"Position_Of_Doors"),
                        BusData.getBusInfo(busId,"Pram_Request"), BusData.getBusInfo(busId,"Ramp_Wheel_Chair_Lift"),
                        BusData.getBusInfo(busId,"Status_2_Of_Doors"), BusData.getBusInfo(busId,"Stop_Pressed"),
                        BusData.getBusInfo(busId,"Stop_Request"), BusData.getBusInfo(busId,"Total_Vehicle_Distance"),
                        BusData.getBusInfo(busId,"Turn_Signals"), BusData.getBusInfo(busId,"Wlan_Connectivity"));

            } catch (Exception e) {
                this.exception = e;
                return "Could not insert!";
            }
            return "Insertion successful!";
        }

        protected void onPostExecute() {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }
}
