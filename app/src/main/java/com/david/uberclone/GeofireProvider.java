package com.david.uberclone;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class GeofireProvider  {
    private DatabaseReference mDatabase;
    private GeoFire mGeofire;

    public GeofireProvider(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("geolocalisation");
        mGeofire = new GeoFire(mDatabase);
    }

    public void saveLocation(String id, LatLng latLng){
        mGeofire.setLocation(id,new GeoLocation(latLng.latitude, latLng.longitude));
    }

}
