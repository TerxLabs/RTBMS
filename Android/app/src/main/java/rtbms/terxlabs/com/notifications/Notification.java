package rtbms.terxlabs.com.notifications;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.iid.FirebaseInstanceId;

import rtbms.terxlabs.com.R;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main_menu);

        System.out.println("MainActivity.onCreate: " + FirebaseInstanceId.getInstance().getToken());
    }
}