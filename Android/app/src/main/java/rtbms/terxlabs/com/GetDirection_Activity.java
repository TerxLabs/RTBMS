package rtbms.terxlabs.com;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static java.lang.String.valueOf;


public class GetDirection_Activity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMarkerClickListener, ValueEventListener {
    public LocationManager locationManager;
    private GoogleMap mMap;
    Query Qm;
    private static final int request = 1;
    Marker marker;
    public String bestProvider;
    private FusedLocationProviderClient client;
    String name;
    Task<Location> locationTask;
    double latc, latd, longd;
    public Criteria criteria;
    double longc;
    LatLng destination;
    private LatLng origin;
    String distance = "";
    String duration = "";
    TextView objdis, objtim,objname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_get_direction );
        objdis = findViewById( R.id.distance );
        objtim = findViewById( R.id.time );
        objname=findViewById( R.id.getdirection_hospital_name );

        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        client = LocationServices.getFusedLocationProviderClient( this );
        Bundle bundle = getIntent().getExtras();
        Bundle b = getIntent().getExtras();
        assert bundle != null;
        final String state = bundle.getString( "state1" );
        final String dist = bundle.getString( "district1" );
        name = bundle.getString( "name1" );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
        assert dist != null;
        assert state != null;
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child( "web" ).child( "hospitals" ).child( state ).child( dist );
        mDatabase.push().setValue( marker );
        if (b != null) {
            String d = b.getString( "id" );
            Qm = mDatabase.orderByChild( "hid" ).equalTo( d );
            Qm.addValueEventListener( new ValueEventListener() {
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot s : dataSnapshot.getChildren()) {
                        info_hospital user = s.getValue( info_hospital.class );
                        if (user != null) {
                            double latd = Double.parseDouble( user.getLatitude() );
                            String b=user.getName();
                            double longd = Double.parseDouble( user.getLongitude() );
                            destination = new LatLng( latd, longd );
                            objname.setText( b);
                            mMap.addMarker( new MarkerOptions().position( destination ).title( user.name ) ).setIcon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_RED ) );
                            mMap.addMarker( new MarkerOptions().position( origin ).title( "Current Location" ) ).setIcon( BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_RED ) );
                            // Getting URL to the Google Directions API

                            mMap.animateCamera( CameraUpdateFactory.newLatLngZoom( new LatLng( latd, longd ), 15.0f ) );
                            String url = getDirectionsUrl( origin, destination );

                            Log.i( "destination", String.valueOf( destination ) );
                            DownloadTask downloadTask = new DownloadTask();

                            // Start downloading json data from Google Directions API
                            downloadTask.execute( url );

                        }
                        Log.i( "Destination", String.valueOf( latd ) );
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            } );

            locationManager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
            criteria = new Criteria();
            bestProvider = valueOf( locationManager.getBestProvider( criteria, true ) );

            if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {


                return;
            }

            Location location = locationManager.getLastKnownLocation( bestProvider );
            {
                latc = location.getLatitude();
                longc = location.getLongitude();


                origin = new LatLng( latc, longc );


            }

        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.i( "Currentlocatio Latitude", String.valueOf( latc ) );
        Log.i( "CurrenlocationLongitude", String.valueOf( longc ) );
        Log.i( "DestinationLongitude", String.valueOf( latd ) );
        Log.i( "DestinationLatitude", String.valueOf( longd ) );

        googleMap.setOnMarkerClickListener( this );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, request );
            return;
        }
        locationTask = client.getLastLocation().addOnSuccessListener( this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                latc = location.getLatitude();
                longc = location.getLongitude();
            }


        } );

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl( url[0] );
            } catch (Exception e) {
                Log.d( "Background Task", e.toString() );
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute( result );

            ParserTask parserTask = new ParserTask();


            parserTask.execute( result );

        }
    }

    @SuppressLint("StaticFieldLeak")
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject( jsonData[0] );
                DirectionsJSONParser parser = new DirectionsJSONParser();

                routes = parser.parse( jObject );
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get( i );

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get( j );
                    if (j == 0) {    // Get distance from the list
                        distance = point.get( "distance" );
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = point.get( "duration" );
                        continue;
                    }
                    double lat = Double.parseDouble( point.get( "lat" ) );
                    double lng = Double.parseDouble( point.get( "lng" ) );
                    LatLng position = new LatLng( lat, lng );

                    points.add( position );
                }

                lineOptions.addAll( points );
                lineOptions.width( 15 );
                lineOptions.color( ContextCompat.getColor(getApplicationContext(),R.color.blue));
                lineOptions.geodesic( true );
                objdis.setText( distance );
                objtim.setText( duration );
            }

// Drawing polyline in the Google Map for the i-th route
            mMap.addPolyline( lineOptions );
        }
    }

    private String getDirectionsUrl(LatLng origin, LatLng destination) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + destination.latitude + "," + destination.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service


        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL( strUrl );

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader( new InputStreamReader( iStream ) );

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append( line );
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Log.d( "Exception", e.toString() );
        } finally {
            assert iStream != null;
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case request:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    mMap.setMyLocationEnabled( true );
                }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
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
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }}
