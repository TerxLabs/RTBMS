package rtbms.terxlabs.com;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class aboutus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ourteam);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        setTitle("Developers");
    }
}
