package com.mahaveer.invoicemaker;

import android.app.*;import android.os.*;import android.print.*;import android.webkit.*;import android.view.*;import android.widget.*;import android.graphics.pdf.PdfDocument;import android.content.*;import android.net.Uri;import java.io.*;

public class MainActivity extends Activity {
 WebView web; PrintManager printManager;
 @Override public void onCreate(Bundle b){super.onCreate(b); web=new WebView(this); setContentView(web); web.getSettings().setJavaScriptEnabled(true); web.getSettings().setDomStorageEnabled(true); web.getSettings().setAllowFileAccess(true); web.setWebViewClient(new WebViewClient()); web.loadUrl("file:///android_asset/invoice.html");
  web.addJavascriptInterface(new AndroidBridge(),"AndroidApp");
 }
 public class AndroidBridge { @JavascriptInterface public void printPdf(){ runOnUiThread(()->{ PrintManager pm=(PrintManager)getSystemService(PRINT_SERVICE); pm.print("Mahaveer Invoice", web.createPrintDocumentAdapter("Mahaveer Invoice"), new PrintAttributes.Builder().setMediaSize(PrintAttributes.MediaSize.ISO_A4).setMinMargins(PrintAttributes.Margins.NO_MARGINS).build()); }); } }
 @Override public void onBackPressed(){ if(web.canGoBack()) web.goBack(); else super.onBackPressed(); }
}
