package rtbms.terxlabs.com;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rtbms.terxlabs.com.googlenearbyplaces.nearby_hospital;
import rtbms.terxlabs.com.googlenearbyplaces.nearby_pharmacy;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class MainMenu extends AppCompatActivity  {

    private static final int request = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);

        defineButtons();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case request:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission( this, Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {

                        return;
                    }
                    if (ActivityCompat.checkSelfPermission( this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ) {

                        return;
                    }

                }
        }
    }

    public void defineButtons(){
        findViewById(R.id.request).setOnClickListener(buttonClickListener);
        findViewById(R.id.events).setOnClickListener(buttonClickListener);
        findViewById(R.id.science).setOnClickListener(buttonClickListener);
        findViewById(R.id.feedback).setOnClickListener(buttonClickListener);
        findViewById(R.id.does).setOnClickListener(buttonClickListener);
        findViewById(R.id.nearby_pharmacies).setOnClickListener(buttonClickListener);
        findViewById( R.id.nearby_hospital ).setOnClickListener( buttonClickListener );
        findViewById(R.id.aboutus).setOnClickListener(buttonClickListener);
        findViewById(R.id.profile).setOnClickListener(buttonClickListener);
        findViewById(R.id.newsfeed).setOnClickListener(buttonClickListener);
    }
    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.request:
                    startActivity(new Intent(MainMenu.this,blood_request.class));
                    break;
                case R.id.nearby_pharmacies:
                    startActivity(new Intent(MainMenu.this,nearby_pharmacy.class));
                    break;
                case R.id.events:
                    startActivity(new Intent(MainMenu.this, events.class));
                    break;
                case R.id.nearby_hospital:
                    startActivity( new Intent( MainMenu.this,nearby_hospital.class ) );
                    break;
                case R.id.science:
                    startActivity(new Intent(MainMenu.this, science.class));
                    break;
                case R.id.feedback:
                    startActivity(new Intent(MainMenu.this, feedback.class));
                    break;
                case R.id.does:
                    startActivity(new Intent(MainMenu.this, does.class));
                    break;
                case R.id.aboutus:
                    startActivity(new Intent(MainMenu.this, aboutus.class));
                    break;
                case R.id.profile:
                    startActivity(new Intent(MainMenu.this, Profile.class));
                    break;
                case R.id.newsfeed:
                    startActivity(new Intent(MainMenu.this, NewsFeed.class));
                    break;
            }
        }
    };




}