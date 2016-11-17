package com.mercapp.usuario.gui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.mercapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class MapaFragments extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapClickListener,
        android.location.LocationListener {

    private FragmentManager fragmentManager;

    private Context _context = null;
    Activity context;
    TextView superSelecionado, txtView;
    private Marker marker;
    private static  final String TAG = "MapaFragments";
    private GoogleMap mMap;
    private LocationManager locationManager;

    private Location location;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);

        _context = this.getActivity();
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
            mMap.setOnMapClickListener(this);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMyLocationEnabled(true);

            //Localização inicial com zoom animate
            LatLng recife = new LatLng(-8.062272, -34.9126937);
            CameraPosition cameraPosition = new CameraPosition.Builder().target(recife).zoom(12).bearing(0).tilt(45).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.animateCamera(cameraUpdate, 3000, new GoogleMap.CancelableCallback() {
                @Override
                public void onFinish() {
                    Log.i("Script", "CancelableCallback.onFinish()");
                }

                @Override
                public void onCancel() {
                    Log.i("Script", "CancelableCallback.onCancel()");
                }
            });

           // Supermercado supermercado = new Supermercado(); fazer obter localização
            // dos supermercados pelo endereço, para plotar no mapa.

            //Posições
            LatLng boaViagemExtrabom = new LatLng(-8.1201256, -34.9031476);
            LatLng pinaExtrabom = new LatLng(-8.09494, -34.8877137);
            LatLng piedadeExtrabom = new LatLng(-8.183888, -34.9211767);
            LatLng mustardinhaExtrabom = new LatLng(-8.069109, -34.9198987);
            LatLng olindaExtrabom = new LatLng(-7.9995286, -34.8469477);
            LatLng parnamirimExtrabom = new LatLng(-8.027122, -34.9170917);
            LatLng encruzilhadaExtrabom = new LatLng(-8.0371238, -34.899941);
            LatLng torreExtrabom = new LatLng(-8.0462157, -34.9116174);

            LatLng ufrpe = new LatLng(-8.017877, -34.944440);
            LatLng posi2 = new LatLng(-8.021448, -34.933130);


            //MARKER's
            customAddMaker(boaViagemExtrabom, "Extrabom - BoaViagem");
            customAddMaker(piedadeExtrabom, "Extrabom - Piedade");
            customAddMaker(mustardinhaExtrabom, "Extrabom - Mustardinha");
            customAddMaker(olindaExtrabom, "Extrabom - Olinda");
            customAddMaker(parnamirimExtrabom, "Extrabom - Parnamirim");
            customAddMaker(pinaExtrabom, "Extrabom - Pina");
            customAddMaker(encruzilhadaExtrabom, "Extrabom - Encruzilhada");
            customAddMaker(ufrpe, "Supermercado 1");
            customAddMaker(posi2, "Supermercado 2");


//            LatLng ufrpe = new LatLng(-8.01, -34.9);
//            mMap.addMarker(new MarkerOptions().position(ufrpe).title("Ceagri I"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ufrpe, 12));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(ufrpe));
//
//
//            LatLng posi2 = new LatLng(-8.01, -34.9);
//            mMap.addMarker(new MarkerOptions().position(posi2).title("Posição 2"));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(posi2));

            //EVENTO
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(Marker arg0) {
                    if(arg0 != null && arg0.getTitle().equals("Supermercado 1")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Supermercado 2")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - BoaViagem")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Piedade")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Mustardinha")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Olinda")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Parnamirim")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Pina")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Encruzilhada")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else if(arg0 != null && arg0.getTitle().equals("Extrabom - Torre")) {
                        Supermercado retornoBusca = selecionarSupermercado(arg0.getTitle());
                        if (retornoBusca != null) {
                            fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
                        } else {
                            Toast.makeText(getContext(), arg0.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    return false;
                }
            });

        } catch (SecurityException ex) {
            Log.e(TAG, "Error", ex);
        }
    }

    public Supermercado selecionarSupermercado(String texto) {

        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
        Supermercado superSelect = supermercadoNegocio.buscaSupermercado(texto);
        if (superSelect != null) {
            supermercadoNegocio.iniciarSessao(superSelect);
        }
        return superSelect;
    }
    public void customAddMaker(LatLng latLng, String titulo){
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(titulo).draggable(true);
        marker = mMap.addMarker(options);
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

