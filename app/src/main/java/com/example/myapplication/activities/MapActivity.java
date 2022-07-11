package com.example.myapplication.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.maps.GoogleMap;


public class MapActivity extends AppCompatActivity {

    private GoogleMap mMap;

    int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        initIntent();
//
//        if (user_id < 0) Toast.makeText(this, "userid not passed", Toast.LENGTH_SHORT);

        // Initialize fragment

        Fragment fragment = new MapFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

//    @Override
//    public void onMapReady(final GoogleMap googleMap) {
//
//        mMap = googleMap;
//
//        LatLng SEOUL = new LatLng(37.56, 126.97);
//
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(SEOUL);
//        markerOptions.title("서울");
//        markerOptions.snippet("한국의 수도");
//        mMap.addMarker(markerOptions);
//
//
//        // 기존에 사용하던 다음 2줄은 문제가 있습니다.
//        // CameraUpdateFactory.zoomTo가 오동작하네요.
//        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
//        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
//
//    }
//    public void initIntent() {
//        Intent intent = getIntent();
//        user_id =  intent.getIntExtra("user_id", -1);
//    }

}