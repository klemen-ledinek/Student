package com.student.ledo.student2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Settings extends AppCompatActivity {

    private Button scanBtn;
    private TextView formatTxt, contentTxt,result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        scanBtn = (Button)findViewById(R.id.scan_button);
        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);
        result = (TextView) findViewById(R.id.result);

        scanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.scan_button){
                    IntentIntegrator scanIntegrator = new IntentIntegrator(Settings.this);
                    scanIntegrator.initiateScan();
                }
            }

        });

    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            formatTxt.setText("Format skeniranja: " + scanFormat);
            contentTxt.setText("Vsebina: " + scanContent);
            if(scanContent != "62298" && scanContent != "62299" && scanContent != "62300" && scanContent != "62301" && scanContent != "62302"){
                result.setText("To pohištvo ni iz tvoje sobe!");
            }else{
                result.setText("To pohištvo je iz tvoje sobe!");
            }
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
