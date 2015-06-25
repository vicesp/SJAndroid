package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import DAO.consultas;
import DTO.Fecha_Hoy;


public class Registro_Texturizador extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private static consultas con;

    private Boolean isEdit = true;
    private boolean deCual =false;

    private Button Eliminar, desplegarProductos,backButton;
    private ImageButton Guardar;
    private TextView Fecha, indicadorText;
    private EditText codigoFamilia, nombreFamilia;

    private String[] Nombre_PT,listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_texturizador);

        FechaH = new Fecha_Hoy();
        con = new consultas();

        /********* Text Views *********/
        Fecha = (TextView)findViewById(R.id.tvptFecha);
        Fecha.setText(FechaH.Hoy());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro_texturizador, menu);
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
