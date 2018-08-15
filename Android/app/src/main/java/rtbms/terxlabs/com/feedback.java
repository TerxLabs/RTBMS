package rtbms.terxlabs.com;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;


public class feedback extends AppCompatActivity {

    private WebView view;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_feedback);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Feedback" );

        view = this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        final ProgressDialog Dialog = new ProgressDialog(feedback.this);
        Dialog.setMessage("Please Give feedback");

        Dialog.show();


        view.loadUrl("https://goo.gl/forms/FVCp7Bwh01gbxHBE2"); //try js alert

        view.setWebChromeClient(new WebChromeClient()); // adding js alert support
        Dialog.hide();
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}