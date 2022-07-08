package com.example.myapplication.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.myapplication.R;
import com.google.android.gms.maps.GoogleMap;


public class MapActivity extends AppCompatActivity {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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

}