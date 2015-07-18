package com.example.sistemas.gsjetapa1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import DTO.AlertCalendar;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Panel_Lab extends ActionBarActivity {

    private Button btnPT, btnCrem, btnCuaj, btnBack, btnReq;
    private TextView fecha;
    private static Fecha_Hoy FechaH;
    private static Variables var;
    private static AlertCalendar AC;
    private Boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_lab);
        FechaH=new Fecha_Hoy();
        var= new Variables();
       // AC =new AlertCalendar();
        flag = var.isFromAdminCrema()&&var.isFromAdminCuajadas()&&var.isFromAdminLaboratorio()&&var.isFromAdminRequeson()&&!(var.isFromExportador());
        /******* Text Views ********/
        fecha=(TextView)findViewById(R.id.tvExpFecha);
        fecha.setText(FechaH.Hoy());

        Log.i("bandera:", "" + flag);

            //Variables.setExportar(Panel_Lab.this);


        /******* Buttons **********/
        btnCrem = (Button)findViewById(R.id.btnCrem);
        btnCrem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (flag) {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Realizados.class));
                            var.setFromAdminLaboratorio(false);
                            var.setFromAdminCuajadas(false);
                            var.setFromAdminRequeson(false);
                        }
                        else if(var.isFromExportador()){

                            Variables.setNombre_excel("Crema Lab");
                            Variables.setNombre_tabla("crema_lab");
                            Variables.setTipo_consulta(1);
                            finish();startActivity(new Intent(Panel_Lab.this, Exportar.class));
                        }
                        else {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Crema_Lab.class));
                        }
                    }
                }
        );
        btnCuaj = (Button)findViewById(R.id.btnCuaj);
        btnCuaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag) {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Realizados.class));
                            var.setFromAdminCrema(false);var.setFromAdminLaboratorio(false);var.setFromAdminRequeson(false);
                        }
                        else if(var.isFromExportador()){

                            Variables.setNombre_excel("Cuajadas Lab");
                            Variables.setNombre_tabla("cuajadas_lab");
                            var.setTipo_consulta(1);
                            finish();startActivity(new Intent(Panel_Lab.this, Exportar.class));
                            //AC.dialogee.show();
                        }
                        else {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Cuajadas_Lab.class));
                        }
                        }
                }
        );
        btnPT = (Button)findViewById(R.id.btnPT);
        btnPT.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag) {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Realizados.class));
                            var.setFromAdminCuajadas(false);var.setFromAdminCrema(false);var.setFromAdminRequeson(false);
                        }
                        else if(var.isFromExportador()){

                            Variables.setNombre_excel("Laboratorio Calidad");
                            Variables.setNombre_tabla("laboratorio_calidad");
                            Variables.setTipo_consulta(1);
                            finish();startActivity(new Intent(Panel_Lab.this, Exportar.class));
                        }
                        else {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Laboratorio_Calidad.class));
                        }
                        }
                }
        );
        btnReq = (Button)findViewById(R.id.btnRequeson);
        btnReq.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag) {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Realizados.class));
                            var.setFromAdminCuajadas(false);var.setFromAdminCrema(false);var.setFromAdminLaboratorio(false);
                        }
                        else if(var.isFromExportador()){

                            Variables.setNombre_excel("Requeson Lab");
                            Variables.setNombre_tabla("requeson_lab");
                            Variables.setTipo_consulta(1);
                            finish();startActivity(new Intent(Panel_Lab.this, Exportar.class));
                        }
                        else {
                            finish();
                            startActivity(new Intent(Panel_Lab.this, Requeson_Lab.class));
                        }
                    }
                }
        );
        btnBack=(Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(flag){
                            var.setFromAdminCuajadas(false);
                            var.setFromAdminLaboratorio(false);
                            var.setFromAdminCrema(false);
                            var.setFromAdminRequeson(false);
                            finish();startActivity(new Intent(Panel_Lab.this, Administrador.class));
                        }
                        if(var.isFromExportador()){
                            finish();startActivity(new Intent(Panel_Lab.this, Exportar.class));
                            var.setFromExportador(false);
                        }
                    }});




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
