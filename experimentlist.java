package com.inbuiltsolutions.astute_yourcollegeaide;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class experimentlist extends AppCompatActivity {
    Bitmap myBitmap;
    ImageView imageView;
    int flag=0;
    ProgressBar progress;
    boolean internet=false;
    int value;
    String path;
    String webaddress=" ";
    int A[]=new int[4];
    String name=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_experimentlist);
        imageView=(ImageView)findViewById(R.id.imageView);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progress.setVisibility(View.INVISIBLE);
        Intent in=getIntent();
        name=in.getStringExtra("key4");
        path="/sdcard/Astute/"+in.getStringExtra("key4");
        webaddress="http://palladian.netau.net/AstuteFiles/images/"+in.getStringExtra("key4");

        A[0]=R.drawable.praclist503;
        A[1]=R.drawable.praclist503;
        A[2]=R.drawable.praclist504;
        A[3]=R.drawable.praclist506;

        switch(in.getStringExtra("key4"))
        {
            case "praclist501.png":value=0;
                break;
            case "praclist503.png":value=1;
                break;
            case "praclist504.png":value=2;
                break;
            case "praclist506.png":value=3;
                break;


        }

        File imageFile = new  File(path);
        if(imageFile.exists()) {

            imageView.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));
        }
        else
        {
            imageView.setImageResource(A[value]);
        }
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if ((info == null || !info.isConnected() || !info.isAvailable())) {
            Toast.makeText(this, "Please enable your internet connection to refresh the list", Toast.LENGTH_LONG).show();
            internet=false;
        }
        else
        {
            internet=true;
           /* progress.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Refreshing.......", Toast.LENGTH_LONG).show();
            image t=new image();
            t.start();
            downloadTT tt=new downloadTT();
            tt.execute();*/
        }








        //Drawable drawable=imageView.getDrawable();
        //Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();

        //
        /*image t=new image();
        t.start();
        while(flag==0)
        {

        }
        imageView.setImageBitmap(myBitmap);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setAdjustViewBounds(true);
        /*Toast.makeText(this, "checkpoint1", Toast.LENGTH_LONG).show();
        Drawable drawable=imageView.getDrawable();
        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
        File direct = new File(Environment.getExternalStorageDirectory() + "/Astute2");
        Toast.makeText(this, "checkpoint2", Toast.LENGTH_LONG).show();
        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Astute2/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Astute2/"), "timetable.jpg");
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Toast.makeText(this, "checkpoint3", Toast.LENGTH_LONG).show();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        //SaveImage(myBitmap, "timetable.jpg");*/


    }
    public void save()
    {
        Toast.makeText(this, "Please enable your internet connection to refresh the List..", Toast.LENGTH_LONG).show();
        Drawable drawable=imageView.getDrawable();
        Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();
        File direct = new File(Environment.getExternalStorageDirectory() + "/Astute");

        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Astute/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Astute/"),name );
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            Toast.makeText(this, "Saving....", Toast.LENGTH_LONG).show();
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        progress.setVisibility(View.INVISIBLE);
        Toast.makeText(this, "List Updated!", Toast.LENGTH_LONG).show();
    }
    class downloadTT extends AsyncTask<Void,Void,Void>
    {

        @Override
        protected Void doInBackground(Void... params) {
            while (true) {
                if (flag == 1) {
                    publishProgress();
                    break;
                }
            }
            return null ;
        }
        @Override
        protected void onProgressUpdate(Void... params)
        {

            imageView.setImageBitmap(myBitmap);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setAdjustViewBounds(true);
            save();

        }
    }
    public void refresh(View view)
    {
        if(internet) {
            progress.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Refreshing.......", Toast.LENGTH_LONG).show();
            image t = new image();
            t.start();
            downloadTT tt = new downloadTT();
            tt.execute();
        }
        else
            Toast.makeText(this, "Please enable your internet connection to refresh the List..", Toast.LENGTH_LONG).show();

    }

    private void SaveImage(Bitmap imageToSave, String fileName) {

        File direct = new File(Environment.getExternalStorageDirectory() + "/Astute");
        Toast.makeText(this, "checkpoint2", Toast.LENGTH_LONG).show();
        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Astute/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Astute/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class image extends Thread
    {
        public void run()
        {
            try {
                URL url = new URL(webaddress);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);

                connection.connect();


                InputStream input = connection.getInputStream();
                myBitmap = BitmapFactory.decodeStream(input);
                flag=1;

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}