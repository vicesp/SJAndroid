package com.example.sistemas.gsjetapa1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import DTO.Fecha_Hoy;


public class Info_Detector extends ActionBarActivity {
    private Button regresar;
    private TextView Fecha;
    private Fecha_Hoy fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_detector);

        regresar = (Button) findViewById(R.id.backButton);
        regresar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                        startActivity(new Intent(Info_Detector.this, Detector_Metales.class));

                    }
                }
        );
        Fecha_Hoy fecha = new Fecha_Hoy();
        Fecha = (TextView) findViewById(R.id.tvptFecha);
        Fecha.setText(fecha.Hoy());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info__detector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
