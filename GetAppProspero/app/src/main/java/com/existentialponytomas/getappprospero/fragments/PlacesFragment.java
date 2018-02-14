package com.existentialponytomas.getappprospero.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.contracts.PlacesContract;
import com.existentialponytomas.getappprospero.model.Place;
import com.existentialponytomas.getappprospero.presenters.PlacesPresenter;
import com.existentialponytomas.getappprospero.repos.local.PlaceLocalStorage;

import java.util.List;

public class PlacesFragment extends Fragment implements OnMapReadyCallback, PlacesContract.View {

    View rootView;
    private GoogleMap mMap;
    MapView mMapView;

    public PlacesFragment() {
        // Required empty public constructor
    }

    PlacesContract.Presenter presenter;

    public static PlacesFragment newInstance() {
        PlacesFragment fragment = new PlacesFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showDangerPlaces(List<Place> places) {
        for (Place pl : places) {
            if (pl.isDanger()) {
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pl.getLocation().getCoordinates().getLatitude(),
                                pl.getLocation().getCoordinates().getLongitude()))
                        .title(pl.getName())
                        .snippet(pl.getComment())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.ic_money_off_black_24dp)));
            }
        }
    }

    @Override
    public void showCoolPlaces(List<Place> places) {
        for (Place pl : places) {
            if (pl.isCool()) {
                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(pl.getLocation().getCoordinates().getLatitude(),
                                pl.getLocation().getCoordinates().getLongitude()))
                        .title(pl.getName())
                        .snippet(pl.getComment())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.fairytale_48)));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new PlacesPresenter(this, PlaceLocalStorage.getInstance());
        try {
            rootView = inflater.inflate(R.layout.fragment_places, container, false);
            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (InflateException e) {
            Log.e("err", "Inflate exception");
        }
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener(){
            @Override
            public boolean onMyLocationButtonClick()
            {
                // DEPRECATED, BUT WORKS
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                        mMap.getMyLocation().getLatitude(),
                        mMap.getMyLocation().getLongitude()), 15));
                return true;
            }
        });
        presenter.loadCoolPlaces();
        presenter.loadDangerPlaces();
    }

    /*
        Stub method for current location
     */
    private void loadMyLocation() {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(
                mMap.getMyLocation().getLatitude(),
                mMap.getMyLocation().getLongitude()), 15));
    }
}
