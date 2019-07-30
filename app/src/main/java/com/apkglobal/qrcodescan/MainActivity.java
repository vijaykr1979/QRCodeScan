package com.apkglobal.qrcodescan;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
Button btn_scan;
TextView tv_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_scan = findViewById(R.id.btn_scan);
        tv_data = findViewById(R.id.tv_data);

        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator i = new IntentIntegrator(MainActivity.this);
                i.setPrompt("Scan QR Code");
                i.setOrientationLocked(false);
                i.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult ir = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(ir != null)
        {
            if(ir.getContents() == null )
            {
                Toast.makeText(this,"No data found", Toast.LENGTH_SHORT).show();
            }
            else
            {
                tv_data.setText(ir.getContents());
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
