package rtbms.terxlabs.com;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class hospital_result_notfound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_hospital_result_notfound );
        ActionBar actionbar = getSupportActionBar();
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Not found" );
    }
}
