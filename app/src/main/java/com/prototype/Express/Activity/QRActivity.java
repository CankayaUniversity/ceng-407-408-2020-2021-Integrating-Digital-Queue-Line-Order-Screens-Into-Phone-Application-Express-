package com.prototype.Express.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
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
import com.prototype.Express.R;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


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
        String result = rawResult.getText();
        open_RestaurantsActivity(result);
        scannerView.resumeCameraPreview(this);
        // scannerView.startCamera(); can be added for prevent frozen after QR scan
    }

    public void open_RestaurantsActivity(String key)
    {
        Intent intent = new Intent(QRActivity.this, RestaurantsActivity.class);
        intent.putExtra("key", key);
        startActivity(intent);
    }
}
