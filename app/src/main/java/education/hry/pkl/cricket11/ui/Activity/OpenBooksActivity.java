package education.hry.pkl.cricket11.ui.Activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.AppBarLayout;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import education.hry.pkl.cricket11.R;
import education.hry.pkl.cricket11.apicall.WebAPiCall;
import education.hry.pkl.cricket11.utility.CSPreferences;
import education.hry.pkl.cricket11.utility.GlobalClass;

public class OpenBooksActivity extends AppCompatActivity implements View.OnClickListener {

    private static WebView webView;
    private static ProgressBar webViewProgressBar, progressBar;
    private static ImageView back, forward, refresh, close;
    TextView toolbartitle;
    AppBarLayout applay;

    private static String webViewUrl = "", PhoneNo, itemType, typeId;
    int itemid;
    Boolean readCountdata = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openbooks);


        /// webViewUrl = CSPreferences.readString(this, "admissionURL");


        initViews();
        setUpWebView();
        setListeners();


    }


    private void initViews() {

        back = (ImageView) findViewById(R.id.webviewBack);
        forward = (ImageView) findViewById(R.id.webviewForward);
        refresh = (ImageView) findViewById(R.id.webviewReload);
        close = (ImageView) findViewById(R.id.webviewClose);
        toolbartitle = (TextView) findViewById(R.id.toolbartitle);
        webViewProgressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //  applay = findViewById(R.id.applay);


        try {
            PhoneNo = CSPreferences.readString(this, "PhoneNo");

            Bundle extras = getIntent().getExtras();

            if (extras != null) {

                String result = extras.getString("title");
                webViewUrl = extras.getString("Url");

                toolbartitle.setAllCaps(true);
                toolbartitle.setText(result);

                //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                //    applay.setBackgroundColor(getResources().getColor(android.R.color.transparent));

              /*  if (typeId.equalsIgnoreCase("4")||typeId.equalsIgnoreCase("5")||typeId.equalsIgnoreCase("9")){
                  //  applay.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    itemType="video";
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }else {

                    itemType="book";
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                }*/


                // if (typeId.equalsIgnoreCase("6")) {

                   /* if (GlobalClass.isNetworkConnected(OpenBooksActivity.this)) {

                        if (readCountdata) {

                            ReadViewsCountRequest readViewsCountRequest = new ReadViewsCountRequest();

                            readViewsCountRequest.setBookid(String.valueOf(itemid));
                            readViewsCountRequest.setPhone(PhoneNo);
                                readViewsCountRequest.setType(itemType);

                            WebAPiCall webapiCall = new WebAPiCall();
                            webapiCall.readCountDataMethod(OpenBooksActivity.this, OpenBooksActivity.this, readViewsCountRequest);

                            readCountdata = false;
                        }

                    } else {

                        Toast.makeText(OpenBooksActivity.this, GlobalClass.nointernet, Toast.LENGTH_LONG).show();
                    }*/
                // }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void setUpWebView() {


        webView = (WebView) findViewById(R.id.sitesWebView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        LoadWebViewUrl(webViewUrl);
    }

    private void setListeners() {
        back.setOnClickListener(this);
        forward.setOnClickListener(this);
        refresh.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.webviewBack:
                // isWebViewCanGoBack();
                finish();
                break;
            case R.id.webviewForward:
                if (webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.webviewReload:
                String url = webView.getUrl();
                LoadWebViewUrl(url);
                break;
            case R.id.webviewClose:
                finish();
                break;
        }
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
            refresh.setVisibility(View.GONE);
            if (!webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            refresh.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(OpenBooksActivity.this, R.string.make_sure_reloading_page, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(OpenBooksActivity.this, R.string.make_sure_reloading_page, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            refresh.setVisibility(View.VISIBLE);
            if (webViewProgressBar.isShown())
                webViewProgressBar.setVisibility(View.GONE);
            Toast.makeText(OpenBooksActivity.this, R.string.make_sure_reloading_page, Toast.LENGTH_SHORT).show();

        }

    }

    // To handle "Back" key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isWebViewCanGoBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }


    private void isWebViewCanGoBack() {
        if (webView.canGoBack())
            finish();
            //  webView.goBack();
        else
            finish();
    }

    private static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    private void LoadWebViewUrl(String url) {


        if (isInternetConnected())
            //  webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url="+pdfurl);
            if (getFileExtension(url).equalsIgnoreCase("pdf")) {
                try {
                    webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + URLEncoder.encode(url, "ISO-8859-1"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                webView.loadUrl(url);

            }

        else {
            refresh.setVisibility(View.VISIBLE);
            Toast.makeText(OpenBooksActivity.this, R.string.oops_there_is_no_interenet, Toast.LENGTH_LONG).show();

        }
    }

    public boolean isInternetConnected() {
        // At activity startup we manually check the internet status and change
        // the text status
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;

    }


}