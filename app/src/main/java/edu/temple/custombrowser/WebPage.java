package edu.temple.custombrowser;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebPage extends Fragment {

    WebView myWebView;
    public WebPage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_web_page, container, false);

        myWebView = (WebView) v.findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return v;


    }


    public void goToPage(String url) {

        if(url.equals("")){
            url = "http://www.google.com";
        }
        else if((!url.contains("http://") || !url.contains("https://")) && url.contains("www.") && (url.contains(".com")|| url.contains(".edu"))){
            url = "https://" + url;
        }
        else if ((!url.contains("http://") || !url.contains("https://")) && !url.contains("www.") && (url.contains(".com") || url.contains(".edu"))) {
            url = "https://www." + url;
        }
        else if ((!url.contains("http://") || !url.contains("https://")) && (!url.contains(".com") || !url.contains("www."))){
            url = "https://www.google.com/search?q=" + url;
        }

        myWebView.loadUrl(url);
    }


    public void goForward(){
        if(myWebView.canGoForward()){
            myWebView.goForward();
        }
        else{
            Toast.makeText(getActivity(),"No more pages left",Toast.LENGTH_SHORT).show();
        }
    }

    public void goBack(){
        if(myWebView.canGoBack()){
            myWebView.goBack();
        }
        else{
            Toast.makeText(getActivity(),"No more pages left",Toast.LENGTH_SHORT).show();
        }
    }



}
