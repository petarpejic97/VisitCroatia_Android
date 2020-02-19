package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;

public class City extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ImageView cityImg;
    private TextView tvTitle;
    private EditText edDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        cityImg=findViewById(R.id.ivCity);
        tvTitle=findViewById(R.id.cityName);
        edDate= findViewById(R.id.date);
        edDate.setOnClickListener(view -> {

            Calendar cal = Calendar.getInstance();
            int day= cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);

            DatePickerDialog dialog = new DatePickerDialog(
                    City.this,
                    android.R.style.Theme_Holo_Dialog_MinWidth,
                    mDateSetListener,
                    day,month,year);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
            dialog.show();
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month+=1;
                String date=day+"."+month+"."+year+".";
                Toast.makeText(City.this,date,Toast.LENGTH_SHORT).show();

                StoreInPreferences(date);

                edDate.setText(date);
            }
        };
        getInformation();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
               .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void StoreInPreferences(String date) {
        SharedPreferences sharedPreferences = getSharedPreferences("pressedButton", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("date",date).apply();
    }

    private void getInformation() {
        SharedPreferences result = getSharedPreferences("city", Context.MODE_PRIVATE);
        if(result.getString("city", "zero") != null){
            tvTitle.setText(result.getString("city", "zero"));
            if(result.getString("city", "zero").equals("Zagreb")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.zagreb));
            }
            else if(result.getString("city", "zero").equals("Split")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.split));
            }
            else if(result.getString("city", "zero").equals("Vinkovci")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.vinkovci));
            }
            else if(result.getString("city", "zero").equals("Osijek")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.osijek));
            }
            else if(result.getString("city", "zero").equals("Rijeka")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.rijeka));
            }
            else if(result.getString("city", "zero").equals("Zadar")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.zadar));
            }
            else if(result.getString("city", "zero").equals("Slavonski Brod")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.slavbrod));
            }
            else if(result.getString("city", "zero").equals("Pula")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.pula));
            }
            else if(result.getString("city", "zero").equals("Karlovac")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.karlovac));
            }
            else if(result.getString("city", "zero").equals("Sisak")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.sisak));
            }
            else if(result.getString("city", "zero").equals("Dubrovnik")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.dubrovnik));
            }
            else if(result.getString("city", "zero").equals("Varaždin")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.varazdin));
            }
            else if(result.getString("city", "zero").equals("Šibenik")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.sib2));
            }
            else if(result.getString("city", "zero").equals("Velika Gorica")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.velikagorica));
            }
            else if(result.getString("city", "zero").equals("Vukovar")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.vukovar));
            }
            else if(result.getString("city", "zero").equals("Bjelovar")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.bjelovar));
            }
            else if(result.getString("city", "zero").equals("Koprivnica")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.koprivnica));
            }
            else if(result.getString("city", "zero").equals("Đakovo")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.dakovo));
            }
            else if(result.getString("city", "zero").equals("Kaštela")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.kastela));
            }
            else if(result.getString("city", "zero").equals("Sesvete")){
                cityImg.setImageDrawable(ContextCompat.getDrawable(City.this,R.drawable.sesvete));
            }
        }
    }

    public void goToCityList(View view) {
        Intent intent = new Intent(City.this,CityList.class);
        finish();
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        float zoomLevel = 13.0f;
        if (tvTitle.getText().toString().equals("Zagreb")) {
            //Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            LatLng zagreb = new LatLng(45.812901, 15.977394);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zagreb, 10.0f));
        }
        else if (tvTitle.getText().toString().equals("Split")) {
            LatLng split = new LatLng(43.507556, 16.438188);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(split, 12.0f));
        }
        else if (tvTitle.getText().toString().equals("Vinkovci")) {
            LatLng vinkovci = new LatLng(45.288128, 18.805153);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vinkovci, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Osijek")) {
            LatLng osijek = new LatLng(45.555762, 18.694178);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(osijek, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Rijeka")) {
            LatLng rijeka = new LatLng(45.329999, 14.443108);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rijeka, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Zadar")) {
            LatLng zadar = new LatLng(44.116343, 15.237401);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zadar, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Slavonski Brod")) {
            LatLng slbrod = new LatLng(45.160074, 18.012600);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(slbrod, 12.0f));
        }
        else if (tvTitle.getText().toString().equals("Pula")) {
            LatLng pula = new LatLng(44.870961, 13.851841);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pula, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Karlovac")) {
            LatLng karlovac = new LatLng(45.489041, 15.553050);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(karlovac, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Sisak")) {
            LatLng sisak = new LatLng(45.490455, 16.373216);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sisak, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Dubrovnik")) {
            LatLng dubrovnik = new LatLng(42.649922, 18.091044);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dubrovnik, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Varaždin")) {
            LatLng varazdin = new LatLng(46.305078, 16.344324);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(varazdin, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Šibenik")) {
            LatLng sibenik = new LatLng(43.733634, 15.895059);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sibenik, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Velika Gorica")) {
            LatLng velgorica = new LatLng(45.711474, 16.070998);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(velgorica, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Vukovar")) {
            LatLng vukovar = new LatLng(45.348857, 19.002262);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vukovar, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Bjelovar")) {
            LatLng bjelovar = new LatLng(45.900370, 16.844076);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bjelovar, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Koprivnica")) {
            LatLng koprivnica = new LatLng(46.161608, 16.834071);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(koprivnica, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Đakovo")) {
            LatLng dakovo = new LatLng(45.308613, 18.410888);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dakovo, zoomLevel));
        }
        else if (tvTitle.getText().toString().equals("Kaštela")) {
            LatLng kastela = new LatLng(43.551533, 16.377121);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kastela, 11.3f));
        }
        else if (tvTitle.getText().toString().equals("Sesvete")) {
            LatLng sesvete = new LatLng(45.826286, 16.110515);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sesvete, zoomLevel));
        }
    }

    public void goToEventList(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("pressedButton", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("button","all").apply();
        Intent intent = new Intent(City.this,EventList.class);
        finish();
        startActivity(intent);
    }

    public void goToEventListSearch(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("pressedButton", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("button","search").apply();
        Intent intent = new Intent(City.this,EventList.class);
        finish();
        startActivity(intent);
    }
}
