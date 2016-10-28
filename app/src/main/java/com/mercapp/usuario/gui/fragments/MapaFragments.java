package com.mercapp.usuario.gui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.mercapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class MapaFragments extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener,
        android.location.LocationListener {

    private FragmentManager fragmentManager;

    Activity context;
    TextView superSelecionado, txtView;

    private static  final String TAG = "MapaFragments";

    private GoogleMap mMap;

    private LocationManager locationManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        // Ativa GPS
        LocationManager lM = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        lM.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 0, 100, this);

    }
    @Override
    public void onPause() {
        super.onPause();

        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        locationManager.removeUpdates(this);

    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        try {

            final FragmentManager fm = getFragmentManager();

            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            mMap = googleMap;

            mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f));

            mMap.setOnMapClickListener(this);

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setMyLocationEnabled(true);

            LatLng ufrpe = new LatLng(-8.017877, -34.944440);
            mMap.addMarker(new MarkerOptions().position(ufrpe).title("Ceagri I"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ufrpe, 12));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(ufrpe));


            LatLng posi2 = new LatLng(-8.01, -34.9);
            mMap.addMarker(new MarkerOptions().position(posi2).title("Posição 2"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(posi2));

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()


            {

                @Override
                public boolean onMarkerClick(Marker arg0) {
                    if(arg0 != null && arg0.getTitle().equals("Ceagri I")) {
                        Toast.makeText(getContext(), arg0.getTitle(), Toast.LENGTH_SHORT).show();
                        fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        //supermercadoSelecionado
                    }

                    if(arg0 != null && arg0.getTitle().equals("Posição 2")) {
                        Toast.makeText(getContext(), arg0.getTitle(), Toast.LENGTH_SHORT).show();
                        fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                    }
                    return true;
                }

            });

        }
        catch (SecurityException ex) {
            Log.e(TAG,"Error",ex);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {

        //Toast.makeText(getContext(), "Coordenadas: " + latLng.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLocationChanged(Location location) {

        //Toast.makeText(getActivity(), "Prov Alterada", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

        //Toast.makeText(getActivity(), "Status Alterado", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderEnabled(String provider) {

        //Toast.makeText(getActivity(), "Prov Habi", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onProviderDisabled(String provider) {

        //Toast.makeText(getActivity(), "Prov Desabi", Toast.LENGTH_LONG).show();

    }

    private void showTela (Fragment fragment, String name) {

        fragmentManager = getFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, fragment, name);

        transaction.commit();

    }
}

