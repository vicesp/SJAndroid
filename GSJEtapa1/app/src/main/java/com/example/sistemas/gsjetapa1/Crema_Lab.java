package com.example.sistemas.gsjetapa1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Crema_Lab extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private TextView Fecha;
    private Button btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crema__lab);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());



        /*********** Buttons **************/

        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (var.isFromCrema()) {
                    var.setFromAdminCrema(true);
                    finish();
                    startActivity(new Intent(Crema_Lab.this, Realizados.class));
                } else {
                    finish();
                    startActivity(new Intent(Crema_Lab.this, Panel_Lab.class));

                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crema__lab, menu);
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
