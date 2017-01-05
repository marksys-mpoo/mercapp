package com.mercapp.usuario.gui.fragments;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mercapp.R;
import com.mercapp.supermercado.dominio.Supermercado;
import com.mercapp.supermercado.gui.CadastroSupermercadosActivity;
import com.mercapp.supermercado.negocio.SupermercadoNegocio;

import java.util.Iterator;
import java.util.List;

public class MapaFragments extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, GoogleMap.OnMapClickListener,
        android.location.LocationListener {

    private Context context = null;
    private Marker marker;
    private static  final String TAG = "MapaFragments";
    private GoogleMap mMap;
    private LocationManager locationManager;

    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public void setLocationManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public static String getTAG() {
        return TAG;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
        setContext(this.getActivity());
    }
    @Override
    public void onResume() {
        super.onResume();
        // Ativa GPS
        LocationManager lM = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        final int distanciaMinima = 100;
        lM.requestLocationUpdates(android.location.LocationManager.GPS_PROVIDER, 0, distanciaMinima, this);
    }

    @Override
    public void onPause() {
        super.onPause();
        setLocationManager((LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE));
        getLocationManager().removeUpdates(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final FragmentManager fragmentManager = getFragmentManager();
        try {
            setLocationManager((LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE));
            setmMap(googleMap);
            getmMap().setOnMapClickListener(this);
            getmMap().setOnMapLongClickListener(this);
            getmMap().getUiSettings().setZoomControlsEnabled(true);
            getmMap().setMyLocationEnabled(true);
            visualizacaoMapa();
            addMarcadoresNoMapa();
            eventosWaypoints(fragmentManager);
        } catch (SecurityException ex) {
            Log.e(getTAG(), "Error", ex);
        }
    }

    private void visualizacaoMapa() {
        //Localização inicial com zoom animate
        final double latitudeInicial = -8.062272;
        final double longetudeInicial = -34.9126937;
        LatLng recife = new LatLng(latitudeInicial, longetudeInicial);
        final int zommInicial = 12;
        final int posicaoCameraInicial = 45;
        CameraPosition cameraPosition = new CameraPosition.Builder().target(recife).zoom(zommInicial).bearing(0).tilt(posicaoCameraInicial).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        final int tempoAnimacao = 3000;
        getmMap().animateCamera(cameraUpdate, tempoAnimacao, new GoogleMap.CancelableCallback() {
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
        SupermercadoNegocio consulta = new SupermercadoNegocio(getContext());
        List<Supermercado> supermercados = consulta.listar();
        Iterator iterator = supermercados.iterator();
        while (iterator.hasNext()){
            Supermercado supermercado = (Supermercado) iterator.next();
            customAddMaker(supermercado.getCoordenadas(), supermercado.getNome());
        }

    }

    public void customAddMaker(LatLng latLng, String titulo){
        MarkerOptions options = new MarkerOptions();
        options.position(latLng).title(titulo).draggable(true);
        setMarker(getmMap().addMarker(options));
    }

    private void eventosWaypoints(final FragmentManager fragmentManager) {
        getmMap().setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
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
            Supermercado retornoBusca = this.selecionarSupermercado(marcador.getTitle());
            if (retornoBusca != null) {
                fm.beginTransaction().replace(R.id.container2, new RodapeMapa()).commit();
            } else {
                Toast.makeText(getContext(), marcador.getTitle() + " não cadastrado.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Supermercado selecionarSupermercado(String texto) {
        SupermercadoNegocio supermercadoNegocio = new SupermercadoNegocio(getContext());
        Supermercado supermercadoSelecionado = supermercadoNegocio.buscaSupermercado(texto);
        if (supermercadoSelecionado != null) {
            supermercadoNegocio.iniciarSessaoSupermercado(supermercadoSelecionado);
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

    @Override
    public void onMapLongClick(LatLng latLng) {
        Toast.makeText(getActivity(), "Coordenadas registradas!", Toast.LENGTH_LONG).show();
        Intent cadastrar = new Intent(getActivity(), CadastroSupermercadosActivity.class);
        cadastrar.putExtra("CoordLat",latLng.latitude);
        cadastrar.putExtra("CoordLong",latLng.longitude);
        startActivity(cadastrar);
        getActivity().finish();
    }
}