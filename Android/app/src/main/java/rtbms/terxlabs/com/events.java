package rtbms.terxlabs.com;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static java.lang.String.valueOf;


public class events extends AppCompatActivity implements LocationListener {
    TextView tv;
    public Criteria criteria;
    private static final int request = 1;
    public String bestProvider;
    LocationManager locationManager;
    private FirebaseRecyclerAdapter<eventsadapter, Holder> adapter;
    String city;
    static public final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, request );
            return;
        }        setContentView( R.layout.events_recycleview );
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Events" );

        DatabaseReference datarefer = FirebaseDatabase.getInstance().getReference( "web" ).child( "notifyPeople" );
        datarefer.keepSynced( true );
        final ProgressDialog Dialog = new ProgressDialog( events.this );
        Dialog.setMessage( "Searching Events" );
        Dialog.show();
        locationManager = (LocationManager) this.getSystemService( Context.LOCATION_SERVICE );
        criteria = new Criteria();
        bestProvider = valueOf( locationManager.getBestProvider( criteria, true ) );
        locationManager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        assert locationManager != null;
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 5000, 5, this );
        RecyclerView obj_hospital = findViewById( R.id.events_recycle );
        if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation( bestProvider );{
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i( "Current address", String.valueOf( addresses ) );
            assert addresses != null;
            city=addresses.get( 0 ).getAdminArea(  );}
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference( "web" ).child( "notifyPeople" );

        Query state = ref.orderByChild( "state" );
        Query events_state=state.equalTo( city );
        obj_hospital.hasFixedSize();
        obj_hospital.setLayoutManager( new LinearLayoutManager( this ) );
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        FirebaseRecyclerOptions firerecycleoptions = new FirebaseRecyclerOptions.Builder<eventsadapter>().setQuery( events_state, eventsadapter.class ).build();
        adapter = new FirebaseRecyclerAdapter<eventsadapter, Holder>( firerecycleoptions ) {
            @Override
            protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull eventsadapter model) {
                Dialog.hide();
                holder.setName( model.getName());
                holder.setDate( model.getDate());
                holder.setTime( model.getTime());
                holder.setVenue( model.getVenue());
                holder.setCityPincode( model.getCityPincode());
            }
            @NonNull
            @Override
            public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from( parent.getContext() )
                        .inflate( R.layout.activity_events, parent, false );
                return new Holder( view );
            }
        };
        obj_hospital.setAdapter( adapter );
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onLocationChanged(Location location) {
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            city=addresses.get( 0 ).getAdminArea(  );

        }catch(Exception e)
        {

        }
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
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    public static class Holder extends RecyclerView.ViewHolder{
        View v;
        Holder(View itemView){
            super(itemView);
            v = itemView;
        }
        @SuppressLint("SetTextI18n")
        public void setName(String evname){
            TextView tname = v.findViewById(R.id.event_name);
            tname.setText(evname);

        }
        @SuppressLint("SetTextI18n")
        public void setVenue(String venue){
            TextView tname = v.findViewById(R.id.event_venue);
            tname.setText(venue+",");

        }
        @SuppressLint("SetTextI18n")
        public void setDate(String date){
            TextView tname = v.findViewById(R.id.event_date);
            tname.setText(date);
        }
        @SuppressLint("SetTextI18n")
        public void setTime(String time){
            TextView tname = v.findViewById(R.id.event_time);
            tname.setText(time);

        }

        @SuppressLint("SetTextI18n")
        public void setCityPincode(String pincode){
            TextView tname = v.findViewById(R.id.event_city_pincode);
            tname.setText(pincode);

        }
    }}
