package com.example.imagedownloadingtask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    public static final String IMAGE_URL_1 = "https://th.bing.com/th/id/OIP.DpcLyyRCeTWoiiMNdCTXxQHaEK?w=271&h=180&c=7&o=5&dpr=1.25&pid=1.7";
    public static final String IMAGE_URL_2 ="https://th.bing.com/th/id/OIP.jF9TLINb3igNdnakH3TxxgHaEK?w=285&h=180&c=7&o=5&dpr=1.25&pid=1.7";
    Bitmap myBitmap;
    Button download1,download2;
    ImageView image1,image2;// image2 and download2 are for task using library
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager check = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = check.getAllNetworkInfo();

        download1 = findViewById(R.id.download1);
        download2 = findViewById(R.id.download2);
        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);


        //checking Internet is available or not
        for(int i=0; i <info.length; i++){
            if(info[i].getState() == NetworkInfo.State.CONNECTED){
                Toast.makeText(getApplicationContext(),"Internet is Connected",Toast.LENGTH_SHORT).show();
                downloadImage(image1);
                downloadImageByLibrary(image2);
                break;
            }
            else{
                Toast.makeText(getApplicationContext(),"Check your Internet Connection",Toast.LENGTH_SHORT).show();
            }
        }

    }

    // downloading image from link using httpURLConnection class and URL class
    public void downloadImage(View view){
        image1 = findViewById(R.id.image1);
       download1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadFromUrl obj = new DownloadFromUrl(IMAGE_URL_1,image1);
                obj.execute();
           }
        });
    }

    // showing image in imageView using Glide library
    public void downloadImageByLibrary(View view){
        image2 = findViewById(R.id.image2);
        download2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(getApplicationContext())
                        .load(IMAGE_URL_2)
                        .placeholder(R.drawable.ic_launcher_background)
                        .centerCrop()
                        .into(image2);

            }
        });
    }
}