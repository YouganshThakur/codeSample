package com.inbuiltsolutions.buggeroff;

/**
 * Created by HP PC on 09-11-2016.
 */
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.util.UUID;

public class ConnectingClass extends AppCompatActivity {
    BluetoothAdapter BtAdapter;
    BluetoothSocket BtSocket;
    BluetoothDevice BtDevice;
    UUID uuid;
    int flag=0;
    TextView textView;
    int REQUEST_ENABLE_BT=1;
    ReceiveClass receiveClass;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.status);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /*textView=(TextView)findViewById(R.id.textView3);
        textView.setText("yougansh");
        BtAdapter=BluetoothAdapter.getDefaultAdapter();
        uuid= UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        receiveClass=new ReceiveClass();
        intent=new Intent(this,alarm.class);
        flag=0;
        if(BtAdapter==null)
        {

        }
        else
        {
            if(!BtAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
        ConnectThread t=new ConnectThread();
        t.start();
        textView.setText("Bluetooth Connected");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        receiveClass.execute();
*/
    }

    class ReceiveClass extends AsyncTask<Void,String,Void>
    {
        String msg;

        @Override
        protected Void doInBackground(Void... params) {

            byte[] buffer = new byte[1024];
            int Bytes=0;

            while (true) {

                try {
                    Bytes = BtSocket.getInputStream().read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                msg = new String(buffer,0,Bytes);
                if (msg.contains("1"))
                    publishProgress("Touch Detected");

            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //ReceivedText.setText(values[0]);
            textView.setText(values[0]);

            if(flag==0) {
                startActivity(intent);
                flag=1;
            }
            msg="empty";
        }
    }

    class ConnectThread extends Thread
    {
        ConnectThread()
        {
            BtDevice=BtAdapter.getRemoteDevice("00:15:83:35:8F:98");
        }
        public void run() {
            try {
                BtSocket=BtDevice.createRfcommSocketToServiceRecord(uuid);
                BtSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
