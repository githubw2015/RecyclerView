package com.example.com.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HomeActivity extends AppCompatActivity {
    private Button btn;
    private ImageView mNetImageView;
    private Bitmap mDownLoadBtBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn = (Button) findViewById(R.id.button);
        mNetImageView = (ImageView) findViewById(R.id.imageView);
        Button btnNew = (Button) findViewById(R.id.btn_new);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    new downPicture().execute(new URL("http://pica.nipic.com/2007-11-09/200711912453162_2.jpg"));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public class downPicture extends AsyncTask<URL, Integer, String> {
        @Override
        protected void onPreExecute() {
            //在主线程中运行
            super.onPreExecute();
            Log.i("TAG", "onPreExecute方法");
        }

        @Override
        protected String doInBackground(URL... params) {
            //在子线程中运行
            Log.i("TAG", "doInBackground方法");
            URL imageUrl = null;
            try {
                imageUrl = params[0];
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("", e.getMessage());
            }
            try {
                //使用HttpURLConnection打开连接
                URLConnection urlConn = imageUrl.openConnection();
                //将得到的数据转化成InputStream
                InputStream is = urlConn.getInputStream();
                //将InputStream转换成Bitmap
                mDownLoadBtBitmap = BitmapFactory.decodeStream(is);
                is.close();
            } catch (IOException e) {
                Log.e("", e.getMessage());
            }
            return "ok";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //在主线程中运行
            super.onProgressUpdate(values);
            Log.i("TAG", "onProgressUpdate方法");
        }

        @Override
        protected void onPostExecute(String s) {
            //在子线程中运行
            super.onPostExecute(s);
            Log.i("TAG", "onPostExecute方法");
            mNetImageView.setImageBitmap(mDownLoadBtBitmap);
        }
    }
}
