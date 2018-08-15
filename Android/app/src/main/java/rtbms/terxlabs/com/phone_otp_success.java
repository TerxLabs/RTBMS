package rtbms.terxlabs.com;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class phone_otp_success extends AppCompatActivity {
    private PrefManager prefManager1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_phone_otp_success );
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        Thread myThread = new Thread(){
            @SuppressLint("ApplySharedPref")
            @Override
            public void run() {
                try {
                    sleep(2000);
                    SharedPreferences pref=getSharedPreferences( "ActivityPref", Context.MODE_PRIVATE );
                    SharedPreferences.Editor edty=pref.edit();
                    edty.putBoolean( "activity_executed",true );
                    edty.commit();
                    Intent startMainScreen = new Intent(getApplicationContext(),MainMenu.class);
                    startActivity(startMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }

}
