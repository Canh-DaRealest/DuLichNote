package com.example.dulichnote;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.dulichnote.model.PlaceItem;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapManager {
    private static MapManager instance;
    private Location myLocation;
    private boolean isUpdateLocation;
    private Marker marker;
    private List<Marker> listPlaceMarker;
    private OnMarkerCallback callback;

    public void setCallback(OnMarkerCallback callback) {
        this.callback = callback;
    }

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    public MapManager() {
    }

    private GoogleMap map;

    public void initMap(Context context, GoogleMap ggGoogleMap) {
        this.map = ggGoogleMap;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        map.setBuildingsEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setAllGesturesEnabled(true);
        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                if (marker.getTag() != null) {
                    showInfo((PlaceItem) marker.getTag());
                }
                return false;
            }
        });
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(context);

        //truy cap vi tri
        LocationRequest request = new LocationRequest.Builder(2000).setPriority(Priority.PRIORITY_HIGH_ACCURACY).build();

        client.requestLocationUpdates(request, new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult rs) {
                super.onLocationResult(rs);
                myLocation = rs.getLastLocation();
                showMyLocation();
            }
        }, Looper.getMainLooper());
    }

    private void showInfo(PlaceItem placeItem) {
        callback.markerCallBack(placeItem);

    }

    public void forceShowMyLocation() {
        isUpdateLocation = true;
    }


    private void showMyLocation() {
        if (myLocation == null) {
            return;
        }
        if (marker == null) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("I'm here");
            markerOptions.position(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


            marker = map.addMarker(markerOptions);
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(markerOptions.getPosition(), 13));

        } else if (isUpdateLocation) {
            marker.setPosition(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()));
            isUpdateLocation = false;
        }

    }

    public void showAllPlace(List<PlaceItem> listPlace) {
        if (listPlaceMarker == null) {
            listPlaceMarker = new ArrayList<>();

            for (int i = 0; i < listPlace.size(); i++) {
                PlaceItem item = listPlace.get(i);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title(item.name);
                markerOptions.position(new LatLng(item.lat, item.lgn));
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));


                marker = map.addMarker(markerOptions);
                marker.setTag(item);
                listPlaceMarker.add(marker);
            }
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(listPlaceMarker.get(0).getPosition(), 14));

    }



    public interface OnMarkerCallback {
        void markerCallBack(PlaceItem item);

    }
}
