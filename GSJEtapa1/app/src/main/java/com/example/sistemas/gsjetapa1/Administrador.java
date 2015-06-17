package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


public class Administrador extends ActionBarActivity {

    private Button btnCuajado, btnFundido, btnEmpaque, btnTextu, btnPT,btnLab, btnProductos, btnFamilias;
    private TextView fecha;
    private static Fecha_Hoy FechaH;
    private static Variables var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrador);

        FechaH=new Fecha_Hoy();
        var= new Variables();

        fecha=(TextView)findViewById(R.id.tvExpFecha);
        fecha.setText(FechaH.Hoy());


        btnCuajado=(Button)findViewById(R.id.btnECuajado);
        btnCuajado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
                startActivity(new Intent(Administrador.this, Cuajado_Realizados.class));
            }
        });

        btnFundido=(Button)findViewById(R.id.btnEfundido);
        btnFundido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            var.setFromAdminFundido(true);
            finish();startActivity(new Intent(Administrador.this, Realizados.class));


            }
        });

        btnEmpaque=(Button)findViewById(R.id.btnEempaque);
        btnEmpaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //AlertaEmpaque("Seleccione la pantalla a la que se quiere dirigir.");
                finish();startActivity(new Intent(Administrador.this, Empaque_Realizados.class));

            }
        });

        btnTextu=(Button)findViewById(R.id.btnEtexturizador);
        btnTextu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();startActivity(new Intent(Administrador.this, Texturizador_Edit.class));

            }
        });

        btnPT=(Button)findViewById(R.id.btnEpt);
        btnPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        btnLab=(Button)findViewById(R.id.btnLab);
        btnLab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                var.setFromAdminLaboratorio(true);
                finish();startActivity(new Intent(Administrador.this, Realizados.class));

            }
        });

        btnProductos=(Button)findViewById(R.id.btnProd);
        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                startActivity(new Intent(Administrador.this, Registro_Productos.class));

            }
        });
        btnFamilias=(Button)findViewById(R.id.btnFam);
        btnFamilias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();startActivity(new Intent(Administrador.this, Registro_Familias.class));

            }
        });

        if(var.getNombre_usuario().equals("EL"))
        {

            btnFamilias.setEnabled(false);
            btnProductos.setEnabled(false);
            btnCuajado.setEnabled(false);
            btnEmpaque.setEnabled(false);
            btnFundido.setEnabled(false);
            btnPT.setEnabled(false);
            btnTextu.setEnabled(false);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_administrador, menu);
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

    public void AlertaEmpaque(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Administrador.this);

        alertDialogBuilder.setTitle("Seleccione la opción que desea realizar:");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Edicion de Registro", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                finish();startActivity(new Intent(Administrador.this, Empaque_Realizados.class));

            }


        });
        alertDialogBuilder.setNegativeButton("Producto", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                finish();startActivity(new Intent(Administrador.this, Registro_Productos.class));

            }


        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}
