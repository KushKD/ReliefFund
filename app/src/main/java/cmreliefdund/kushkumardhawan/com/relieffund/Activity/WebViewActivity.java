package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cmreliefdund.kushkumardhawan.com.relieffund.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView1;
    private String someVariable = null;
    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             someVariable = extras.getString("URL");
        }


        webView1  = findViewById(R.id.webView1);
        webView1.setWebViewClient(new MyWebViewClient());
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.getSettings().setDomStorageEnabled(true);
        webView1.getSettings().setLoadWithOverviewMode(true);
        webView1.getSettings().setUseWideViewPort(true);
        webView1.getSettings().setBuiltInZoomControls(true);
        webView1.getSettings().setDisplayZoomControls(false);
        webView1.getSettings().setSupportZoom(true);
        webView1.getSettings().setDefaultTextEncodingName("utf-8");
        webView1.loadUrl(someVariable);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            prDialog = new ProgressDialog(WebViewActivity.this);
            prDialog.setMessage("Please wait ...");
            prDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(prDialog!=null){
                prDialog.dismiss();
            }
        }
    }
}
