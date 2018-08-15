package rtbms.terxlabs.com;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class Detail_Hospital extends AppCompatActivity implements OnMapReadyCallback {
    String name;
 String email;
    String contact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hospital);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Detail Hospital" );
        Bundle b = getIntent().getExtras();
        assert b != null;

        final String state_selection= b.getString("state");
        final String district_selection= b.getString("district");
        final Button b1 = findViewById(R.id.callbutton);
        final Button b2=findViewById(R.id.get_direction);
        final Button b3=findViewById(R.id.button_email);
        final ProgressBar progressBar=findViewById( R.id.progressBar );
        final String d = b.getString("HID");
              progressBar.setVisibility(View.VISIBLE);
        DatabaseReference mDatabase;

        assert district_selection != null;
        assert state_selection != null;
        mDatabase = FirebaseDatabase.getInstance().getReference().child("web").child("hospitals").child( state_selection ).child(district_selection);


        Query qm = mDatabase.orderByChild("hid").equalTo(d);


        qm.addValueEventListener( new ValueEventListener() {
                                      @RequiresApi(api = Build.VERSION_CODES.N)
                                      @SuppressLint("SetTextI18n")
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                          for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                                              final info_hospital location = locationSnapshot.getValue( info_hospital.class );


                                              assert location != null;

                                              TextView nm = findViewById( R.id.text_name );
                                              TextView ad = findViewById( R.id.text_add );

                                              ImageView h_image = findViewById( R.id.hospital_image );
                                              name = location.getName();
                                              String addr = location.getAddress();
                                              contact = location.getPhone();
                                              email = location.getEmail();
                                              String im = location.getPhotoURL();
                                              nm.setText( "Name- " + name );
                                              ad.setText( "Address- " + addr );

                                              Picasso.with( getBaseContext() ).load( im ).into( h_image, new Callback() {
                                                  @Override
                                                  public void onSuccess() {
                                                      progressBar.setVisibility( View.GONE );
                                                  }

                                                  @Override
                                                  public void onError() {

                                                  }
                                              } );


                                              b1.setOnClickListener( new View.OnClickListener() {
                                                  public void onClick(View arg0) {
                                                      Intent callIntent = new Intent( Intent.ACTION_CALL );
                                                      callIntent.setData( Uri.parse( "tel:" + location.getPhone() ) );

                                                      if (ActivityCompat.checkSelfPermission( Detail_Hospital.this,
                                                              Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                                                          return;
                                                      }
                                                      startActivity( callIntent );
                                                  }


                                              } );
                                              b3.setOnClickListener( new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View v) {


                                                      Log.i( "Send email", "" );
                                                      String[] TO = {
                                                              email
                                                      };

                                                      Intent emailIntent = new Intent( Intent.ACTION_SEND );
                                                      emailIntent.setData( Uri.parse( "mailto:" ) );
                                                      emailIntent.setType( "text/plain" );
                                                      emailIntent.putExtra( Intent.EXTRA_EMAIL, TO );

                                                      emailIntent.putExtra( Intent.EXTRA_SUBJECT, "Your subject" );
                                                      emailIntent.putExtra( Intent.EXTRA_TEXT, "Email message goes here" );
                                                      try {
                                                          startActivity( Intent.createChooser( emailIntent, "Send mail..." ) );
                                                          finish();
                                                          Log.i( "Finished send", "" );
                                                      } catch (ActivityNotFoundException ex) {
                                                          Toast.makeText( Detail_Hospital.this, "There is no email client installed.", Toast.LENGTH_SHORT ).show();

                                                      }
                                                  }
                                              } );
                                          }
                                          b2.setOnClickListener( new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {


                                                  Intent i = new Intent( getApplicationContext(), GetDirection_Activity.class );
                                                  i.putExtra( "district1", district_selection );
                                                  i.putExtra( "name1", name );
                                                  i.putExtra( "id", d );
                                                  i.putExtra( "state1",state_selection );
                                                  startActivity( i );
                                                  //  setContentView(R.layout.activity_get_direction);
                                              }

                                          } );
                                      }

                                      @Override
                                      public void onCancelled(@NonNull DatabaseError databaseError) {

                                      }
                                  }

        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}