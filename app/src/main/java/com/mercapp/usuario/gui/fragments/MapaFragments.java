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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
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
import com.mercapp.supermercado.gui.CadastroSupermercados;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;
import com.mercapp.usuario.gui.TelaMenuActivity;

public class MapaFragments extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener,
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final FragmentManager fragmentManager = getFragmentManager();
        try {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            mMap = googleMap;
            mMap.setOnMapClickListener(this);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.setMyLocationEnabled(true);
            visualizacaoMapa();
            addMarcadoresNoMapa();
            eventosWaypoints(fragmentManager);
        } catch (SecurityException ex) {
            Log.e(TAG, "Error", ex);
        }
    }

    private void visualizacaoMapa() {
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
    }

    private void addMarcadoresNoMapa() {
        //Posições
        LatLng boaViagemExtrabom = new LatLng(-8.1201256, -34.9031476);
        LatLng pinaExtrabom = new LatLng(-8.09494, -34.8877137);
        LatLng piedadeExtrabom = new LatLng(-8.183888, -34.9211767);
        LatLng mustardinhaExtrabom = new LatLng(-8.069109, -34.9198987);
        LatLng olindaExtrabom = new LatLng(-7.9995286, -34.8469477);
        LatLng parnamirimExtrabom = new LatLng(-8.027122, -34.9170917);
        LatLng encruzilhadaExtrabom = new LatLng(-8.0371238, -34.899941);
        LatLng torreExtrabom = new LatLng(-8.0462157, -34.9116174);
        LatLng ufrpeExtrabom = new LatLng(-8.017877, -34.944440);
        LatLng doisIrmaosExtrabom = new LatLng(-8.021448, -34.933130);

        //MARKER's
        customAddMaker(boaViagemExtrabom, "Extrabom - BoaViagem");
        customAddMaker(piedadeExtrabom, "Extrabom - Piedade");
        customAddMaker(mustardinhaExtrabom, "Extrabom - Mustardinha");
        customAddMaker(olindaExtrabom, "Extrabom - Olinda");
        customAddMaker(parnamirimExtrabom, "Extrabom - Parnamirim");
        customAddMaker(pinaExtrabom, "Extrabom - Pina");
        customAddMaker(encruzilhadaExtrabom, "Extrabom - Encruzilhada");
        customAddMaker(torreExtrabom, "Extrabom - Torre");
        customAddMaker(ufrpeExtrabom, "Extrabom - UFRPE");
        customAddMaker(doisIrmaosExtrabom, "Extrabom - Dois Irmãos");
    }

    public void customAddMaker(LatLng latLng, String titulo){
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(titulo).draggable(true);
        marker = mMap.addMarker(options);
    }

    private void eventosWaypoints(final FragmentManager fragmentManager) {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marcador) {
                if(marcador != null) {
                    waypointPressionado(marcador, marcador.getTitle(), fragmentManager);
                }
                return false;
            }
        });
    }

    public void waypointPressionado(Marker marcador, String supermercado, FragmentManager fm) {
        if(marcador != null && marcador.getTitle().equals(supermercado)) {
            Supermercado retornoBusca = selecionarSupermercado(marcador.getTitle());
            if (retornoBusca != null) {
                fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
            } else {
                Toast.makeText(getContext(), marcador.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Supermercado selecionarSupermercado(String texto) {
        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(_context);
        Supermercado supermercadoSelecionado = supermercadoNegocio.buscaSupermercado(texto);
        if (supermercadoSelecionado != null) {
            supermercadoNegocio.iniciarSessao(supermercadoSelecionado);
        }
        return supermercadoSelecionado;
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

    @Override
    public void onMapLongClick(LatLng latLng) {



        Intent cadastrar = new Intent(getActivity(), CadastroSupermercados.class);

        startActivity(cadastrar);
        getActivity().finish();

    }
}