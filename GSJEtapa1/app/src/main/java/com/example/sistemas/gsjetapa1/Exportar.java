package com.example.sistemas.gsjetapa1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DAO.consultas;
import DTO.AlertCalendar;
import DTO.Fecha_Hoy;
import DTO.Variables;
import au.com.bytecode.opencsv.CSVWriter;
import config.DataBaseHelper;

/**
 * Created by Sistemas on 25/02/2015.
 */
public class Exportar extends ActionBarActivity {

    private Button btnCuajado, btnFundido, btnEmpaque, btnTextu, btnPT;
    private TextView fecha;
    String nom_tabla;
    String nom_excel;
    private static Fecha_Hoy FechaH;
    public AlertCalendar AC;
    DataBaseHelper myDbHelper = new DataBaseHelper(Variables.getContextoGral());
    protected SQLiteDatabase db;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exportar_db);

        Variables.setExportar(Exportar.this);
        AC=new AlertCalendar();
        FechaH=new Fecha_Hoy();

        fecha=(TextView)findViewById(R.id.tvExpFecha);
        fecha.setText(FechaH.Hoy());

        btnCuajado=(Button)findViewById(R.id.btnECuajado);



        btnCuajado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Variables.setNombre_excel("Cuajado");
                Variables.setNombre_tabla("cuajado");
                Variables.setTipo_consulta(2);
                AC.dialogee.show();

            }
        });

        btnFundido=(Button)findViewById(R.id.btnEfundido);
        btnFundido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Variables.setNombre_excel("Fundido");
                Variables.setNombre_tabla("fundido");
                Variables.setTipo_consulta(1);
                AC.dialogee.show();


            }
        });

        btnEmpaque=(Button)findViewById(R.id.btnEempaque);
        btnEmpaque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Variables.setNombre_excel("Empaque");
                Variables.setNombre_tabla("empaque");
                Variables.setTipo_consulta(1);
                AC.dialogee.show();

            }
        });

        btnTextu=(Button)findViewById(R.id.btnEtexturizador);
        btnTextu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Variables.setNombre_excel("Texturizador");
                Variables.setNombre_tabla("texturizador");
                Variables.setTipo_consulta(1);
                AC.dialogee.show();

            }
        });

        btnPT=(Button)findViewById(R.id.btnEpt);
        btnPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Variables.setNombre_excel("Producto en Proceso");
                Variables.setNombre_tabla("PT");
                Variables.setTipo_consulta(1);
                AC.dialogee.show();

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
