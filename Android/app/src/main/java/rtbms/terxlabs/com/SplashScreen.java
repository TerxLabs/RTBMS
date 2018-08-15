package rtbms.terxlabs.com;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_splash_screen);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        Thread myThread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    SharedPreferences pref=getSharedPreferences( "ActivityPref", Context.MODE_PRIVATE );
                    if(pref.getBoolean( "activity_executed",false )){
                        Intent startMainScreen = new Intent(getApplicationContext(),MainMenu.class);
                        startActivity(startMainScreen);
                        finish();}
                    else{  Intent startMainScreen = new Intent(getApplicationContext(),WelcomeActivity.class);
                        startActivity(startMainScreen);
                        finish();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}