package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SearchView ;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.consultas;
import DTO.AlertCalendar;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Realizados extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener, OnQueryTextListener {


    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private TextView Fecha,nombre;
    private static Variables var;
    Button Regresa;
    ListView listConsultas;
    SearchView Buscador;
    private static ArrayAdapter<String> adapter;
    CalendarView calendario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.realizados);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();
        Calendar c = Calendar.getInstance();


        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText( FechaH.Hoy());
        nombre =(TextView)findViewById(R.id.textView19);


        /*********** List Views ***************/
        listConsultas=(ListView)findViewById(R.id.lvCPendientes);
        listConsultas.setTextFilterEnabled(true);
        listConsultas.setFastScrollEnabled(true);
        listConsultas.setOnItemClickListener(this);
        listConsultas.setCacheColorHint(0);  //para poner de color blanco la lista al momento de hacer scroll




        /************ Search View *************/
        Buscador=(SearchView)findViewById(R.id.searchView);
        setupSearchView();


        /************ Buttons *****************/
        Regresa=(Button)findViewById(R.id.btnRegPendiLista);
        Regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(var.isFromAdminCrema()||var.isFromAdminCuajadas()||var.isFromAdminLaboratorio()||var.isFromAdminRequeson()){
                    var.setFromAdminCuajadas(true);
                    var.setFromAdminCrema(true);
                    var.setFromAdminLaboratorio(true);
                    var.setFromAdminRequeson(true);
                    finish();startActivity(new Intent(Realizados.this, Panel_Lab.class));
                }
                else{
                var.setFromAdminFundido(false);
                var.setFromAdminLaboratorio(false);
                var.setFromAdminCuajado(false);
                var.setFromAdminEmpaque(false);
                var.setFromAdminTexturizador(false);
                var.setFromAdminProducto(false);
                    var.setFromAdminDetector(false);
                finish();
                startActivity(new Intent(Realizados.this, Administrador.class));}
            }
        });

        /************ Calendar View ****************/
        calendario = (CalendarView) findViewById(R.id.calendarView);
        calendario.setMaxDate(c.getTimeInMillis());
        calendario.setOnDateChangeListener(new OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                if (var.isFromAdminLaboratorio()) {
                    LLena_Lista(con.DAOListaLaboratorioRealizado(fechaSeleccionada(calendario.getDate())));
                } else if (var.isFromAdminFundido()) {
                    LLena_Lista(con.DAOListaLaboratorioRealizado(fechaSeleccionada(calendario.getDate())));
                } else if (var.isFromAdminCuajado()) {
                    LLena_Lista(con.DAOListaCuajadoRealizado(fechaSeleccionada(calendario.getDate())));
                } else if (var.isFromAdminEmpaque()) {
                    LLena_Lista(con.DAOListaEmpaqueRealizado(fechaSeleccionada(calendario.getDate())));
                } else if (var.isFromAdminTexturizador()) {
                    LLena_Lista(con.DAOListaTexturizadorRealizado(fechaSeleccionada(calendario.getDate())));
                } else if (var.isFromAdminProducto()) {
                    LLena_Lista(con.DAOListaProductoRealizado(fechaSeleccionada(calendario.getDate())));
                }
                else if(var.isFromAdminCuajadas()){
                    LLena_Lista(con.DAOListaCuajadasLabRealizado(fechaSeleccionada(calendario.getDate())));
                }
                else if (var.isFromAdminCrema()){
                    LLena_Lista(con.DAOListaCremaLabRealizado(fechaSeleccionada(calendario.getDate())));
                }
                else if(var.isFromAdminRequeson()){
                    LLena_Lista(con.DAOListaRequesonLab(fechaSeleccionada(calendario.getDate())));
                }
                else if(var.isFromAdminDetector()){
                    LLena_Lista(con.DAOListaDetectorRealizado(fechaSeleccionada(calendario.getDate())));
                }
            }
        });


        /*Log.i("LAB:", var.isFromAdminLaboratorio() + "");
        Log.i("FUN:",var.isFromAdminFundido()+"");
        Log.i("CUAJ:",var.isFromAdminCuajado()+"");
        Log.i("EMP:",var.isFromAdminEmpaque()+"");
        Log.i("TEXT:",var.isFromAdminTexturizador()+"");*/



        if (var.isFromAdminLaboratorio()) {
            LLena_Lista(con.DAOListaLaboratorioRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("LABORATORIO REALIZADOS");
        }
        if(var.isFromAdminFundido()){
            LLena_Lista(con.DAOListaFundidoRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("FUNDIDO REALIZADOS");
        }
        if(var.isFromAdminCuajado()){

            LLena_Lista(con.DAOListaCuajadoRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("CUAJADO REALIZADOS");
        }
        if(var.isFromAdminEmpaque()){
            LLena_Lista(con.DAOListaEmpaqueRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("EMPAQUE REALIZADOS");
        }
        if(var.isFromAdminTexturizador()){
            LLena_Lista(con.DAOListaTexturizadorRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("TEXTURIZADOR REALIZADOS");
        }
        if(var.isFromAdminProducto()){
            LLena_Lista(con.DAOListaProductoRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("PRODUCTO TERMINADO REALIZADOS");
        }
        if(var.isFromAdminCuajadas()){
            LLena_Lista(con.DAOListaCuajadasLabRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("CUAJADAS LAB REALIZADOS");
        }
        if(var.isFromAdminCrema()){
            LLena_Lista(con.DAOListaCremaLabRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("CREMA LAB REALIZADOS");
        }
        if(var.isFromAdminRequeson()){
            LLena_Lista(con.DAOListaRequesonLab(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("REQUESON LAB REALIZADOS");
        }
        if(var.isFromAdminDetector()){
            LLena_Lista(con.DAOListaDetectorRealizado(fechaSeleccionada(c.getTimeInMillis())));
            nombre.setText("DETECTOR METALES REALIZADOS");
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            listConsultas.clearTextFilter();
        } else {
            listConsultas.setFilterText(newText);
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Log.i("Funcionamiento positivo","");

        if(var.isFromAdminLaboratorio()) {
            String texto = ((TextView) view.findViewById(R.id.tvItem)).getText().toString();
            var.setFromLaboratorio(true);
            var.setLoteLaboratorio(texto.substring(0, texto.indexOf('-')).trim());
            var.setCodProdLaboratorio(texto.substring(texto.indexOf('-')+1,texto.indexOf('/')).trim());
            var.setFromAdminLaboratorio(false);
            finish();
            startActivity(new Intent(Realizados.this, Laboratorio_Calidad.class));
        }
        else if (var.isFromAdminFundido()){

            var.setFromFundido(true);
            var.setLoteFundido(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            var.setFromAdminFundido(false);
            finish();
            startActivity(new Intent(Realizados.this, Fundido.class));
        }
        else if(var.isFromAdminCuajado()){
            var.setFromCuajado(true);
            var.setLoteCuajado(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            var.setFromAdminCuajado(false);
            finish();
            startActivity(new Intent(Realizados.this, Cuajado.class));
        }
        else if(var.isFromAdminEmpaque()){
            var.setFromEmpaque(true);
            var.setLoteEmpaque(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            finish();
            startActivity(new Intent(Realizados.this, Empaque.class));
        }
        else if(var.isFromAdminTexturizador()){
            var.setFromSearch(true);
            var.setLoteTexturizador(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            finish();
            startActivity(new Intent(Realizados.this, Texturizador.class));
        } else if(var.isFromAdminProducto()){
            var.setFromProducto(true);
            var.setLoteProducto(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            finish();
            startActivity(new Intent(Realizados.this, Producto_Terminado.class));
        }
        else if(var.isFromAdminCuajadas()){
            var.setFromCuajadas(true);
            var.setLoteCuajadas(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            finish();startActivity(new Intent(Realizados.this, Cuajadas_Lab.class));
        }
        else if(var.isFromAdminCrema()){
            String texto = ((TextView) view.findViewById(R.id.tvItem)).getText().toString();
            var.setFromCrema(true);
            var.setLoteCrema(texto.substring(0, texto.indexOf('-')).trim());
            finish();startActivity(new Intent(Realizados.this, Crema_Lab.class));
        }
        else if(var.isFromAdminRequeson()){
            String texto = ((TextView) view.findViewById(R.id.tvItem)).getText().toString();
            var.setFromRequeson(true);
            var.setLoteRequeson(texto.substring(0, texto.indexOf('-')).trim());
            finish();startActivity(new Intent(Realizados.this, Requeson_Lab.class));
        }
        else if(var.isFromAdminDetector()){
            var.setFromDetector(true);
            var.setLoteDetector(((TextView) view.findViewById(R.id.tvItem)).getText().toString());
            var.setFromAdminDetector(false);
            finish();startActivity(new Intent(Realizados.this, Detector_Metales.class));

        }
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

    public void LLena_Lista(final ArrayList<consultas> ListaEmpaque){
        adapter=null;

        for (final consultas con : ListaEmpaque)
        {
            adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.tvItem, con.empaque);
        }
        try {
            listConsultas.setAdapter(adapter);
        } catch (NullPointerException e) {

        }
    }

    private void setupSearchView() {
        Buscador.setIconifiedByDefault(false);
        Buscador.setOnQueryTextListener(this);
        Buscador.setSubmitButtonEnabled(false);

    }
    public String fechaSeleccionada( long fechaSeleccionada){
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String hoyConFormato = df.format(fechaSeleccionada);
        return  hoyConFormato;
    }
}