package com.prototype.Express;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import java.net.URLConnection;


public class QRActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView scannerView;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_);

        // INITIALIZE
        scannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        txtResult = (TextView) findViewById(R.id.txt_result);


        // REQUEST PERMISSION & THROW EXPECTATION
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response)
                    {
                        scannerView.setResultHandler(QRActivity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(QRActivity.this, "CAMERA ACCESS REQUIRED", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    @Override
    protected void onDestroy()
    {
        scannerView.stopCamera();
        super.onDestroy();
    }



    // To-Do: RESULT WILL BE SENDED TO DATABASE IN FUTURE
    @Override
    public void handleResult(Result rawResult)
    {
        txtResult.setText(rawResult.getText());
        Toast.makeText(this, "Selected Restaurant:" + rawResult.getText(), Toast.LENGTH_SHORT).show();
        String result = rawResult.getText();
        scannerView.resumeCameraPreview(this);
        // scannerView.startCamera(); can be added for prevent frozen after QR scan


        String url_address = "http://104.248.207.133:5000/api/v1/project-info";

        try
        {
            URL url = new URL(url_address);
            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(result);
            writer.flush();

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }




    }

}
