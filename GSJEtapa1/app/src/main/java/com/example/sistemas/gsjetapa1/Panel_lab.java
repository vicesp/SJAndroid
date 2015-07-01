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
import DTO.Variables;


public class Panel_Lab extends ActionBarActivity {

    private Button btnPT, btnCrem, btnCuaj;
    private TextView fecha;
    private static Fecha_Hoy FechaH;
    private static Variables var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_lab);
        FechaH=new Fecha_Hoy();
        var= new Variables();

        /******* Text Views ********/
        fecha=(TextView)findViewById(R.id.tvExpFecha);
        fecha.setText(FechaH.Hoy());


        /******* Buttons **********/
        btnCrem = (Button)findViewById(R.id.btnCrem);
        btnCrem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();startActivity(new Intent(Panel_Lab.this, Crema_Lab.class));
                    }
                }
        );
        btnCuaj = (Button)findViewById(R.id.btnCuaj);
        btnCuaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();startActivity(new Intent(Panel_Lab.this, Cuajadas_Lab.class));
                    }
                }
        );
        btnPT = (Button)findViewById(R.id.btnPT);
        btnPT.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();startActivity(new Intent(Panel_Lab.this, Laboratorio_Calidad.class));
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panel_lab, menu);
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
