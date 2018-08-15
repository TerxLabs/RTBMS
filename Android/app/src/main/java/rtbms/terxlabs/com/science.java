package rtbms.terxlabs.com;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class science extends AppCompatActivity {
    private WebView webView;
    ProgressDialog Dialog;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        webView = (WebView) findViewById(R.id.webView);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
         Dialog = new ProgressDialog(science.this);
        Dialog.setMessage("Please Wait");
        Dialog.show();
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#FF3D00" ) ) );
        actionbar.setTitle( "Science" );
webView.setWebViewClient(new AppWebViewClients());
webView.getSettings().setJavaScriptEnabled( true );
webView.getSettings().setUseWideViewPort( true );
        webView.loadUrl(  "https://drive.google.com/file/d/17MoyDLjboWI47lt1CBeOTtI3HzL4MfO1/view");

    }
    private class AppWebViewClients extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url) {
         view.loadUrl( url );
         return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished( view, url );
            Dialog.hide();
        }
    }
}