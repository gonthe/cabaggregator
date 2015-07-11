package com.kiwi.cabaggregator;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kiwi.cabaggregator.uber.entities.Product;
import com.kiwi.cabaggregator.uber.entities.Time;
import com.kiwi.cabaggregator.uber.entities.UberServiceDataWrapper;
import com.kiwi.cabaggregator.uber.utility.Uber;
import com.kiwi.cabaggregator.uber.utility.UberService;
import com.kiwi.cabaggregator.uber.view.UberListActivity;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapsActivity extends FragmentActivity implements View.OnClickListener, LocationListener, Callback<UberServiceDataWrapper> {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Marker marker;
    private LatLng lastLatLng;
    private UberService uberService = new Uber().uberService();
    private boolean requestSent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        final LocationManager locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,1,this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,500,1,this);
        findViewById(R.id.GetCabs).setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    public void setUpMap() {
        updateMap(new LatLng(0, 0));
    }


    private void updateMap(LatLng latLng) {
        if (marker == null) {
            marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Mohit"));
        }
        marker.setPosition(latLng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void onLocationChanged(Location location) {
        lastLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        updateMap(lastLatLng);
        System.out.println("location changed: " + location.toString());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void success(UberServiceDataWrapper uberServiceDataWrapper, Response response) {
        if (uberServiceDataWrapper != null) {
            List<Product> products = uberServiceDataWrapper.getProducts();
            if (products != null) {
                this.products = products;

            }

            if (uberServiceDataWrapper.getTimes() == null) {
                uberService.getTimeEstimate(lastLatLng.latitude,lastLatLng.longitude, this);
                return;
            }

            if (uberServiceDataWrapper.getTimes() != null) {
                this.times = uberServiceDataWrapper.getTimes();
                Intent listIntent = new Intent(this, UberListActivity.class);
                startActivity(listIntent);
                requestSent = false;
            }
        }



    }

    @Override
    public void failure(RetrofitError error) {
        requestSent = false;
    }

    private static List<Product> products;
    private static List<Time> times;

    public static List<Product> getProducts() {
        return products;
    }

    public static List<Time> getTimes() {
        return times;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.GetCabs:
                if (!requestSent) {
                    uberService.getProducts(lastLatLng.latitude, lastLatLng.longitude,this);
                    requestSent = true;
                }
            break;
        }
    }
}
