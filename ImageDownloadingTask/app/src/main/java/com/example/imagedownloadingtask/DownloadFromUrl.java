package com.example.imagedownloadingtask;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class DownloadFromUrl extends AsyncTask<Void, Integer, Bitmap> {

    String link;
    ImageView image1;
    MainActivity main = new MainActivity();

    DownloadFromUrl( String link, ImageView image1){
        this.link = link;
        this.image1 = image1;
    }

    @Override
    protected Bitmap doInBackground(Void... params) {

        try {
            URL url = new URL(link);
            HttpURLConnection httpConnect = (HttpURLConnection) url.openConnection();
            httpConnect.connect();
             // InputStream inputStream = url.openStream();
            InputStream inputStream = httpConnect.getInputStream();
            main.myBitmap = BitmapFactory.decodeStream(inputStream);
           // Bitmap resized = Bitmap.createScaledBitmap(main.myBitmap,1000,1000,true);
            return main.myBitmap;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        image1.setImageBitmap(bitmap);
    }

}
