package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView ;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;

public class Cuajado_lista extends Activity implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener, OnQueryTextListener {


    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    Button Regresa;
    ListView listPacientes;
    SearchView Buscador;
    ArrayAdapter<String> adapterr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuajado_lista_pendientes);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();

        listPacientes=(ListView)findViewById(R.id.lvCPendientes);
        Buscador=(SearchView)findViewById(R.id.searchView1);

        LLena_Lista(con.DAOListaLotes());
        listPacientes.setTextFilterEnabled(true);
        listPacientes.setFastScrollEnabled(true);
        listPacientes.setOnItemClickListener(this);
        listPacientes.setCacheColorHint(0);  //para poner de color blanco la lista al momento de hacer scroll
        setupSearchView();

        Regresa=(Button)findViewById(R.id.btnRegPendiLista);
        Regresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
            listPacientes.clearTextFilter();
        } else {
            listPacientes.setFilterText(newText);
        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Variables.setLote_pendiente(((TextView)view.findViewById(R.id.tvItem)).getText().toString());
        Variables.setLopen(((TextView)view.findViewById(R.id.tvItem)).getText().toString());
        finish();startActivity(new Intent(Cuajado_lista.this, Cuajado_parte2.class));

    }

    public void LLena_Lista(final ArrayList<consultas> ListaPaciente){


        for (final consultas con : ListaPaciente) {

            adapterr = new ArrayAdapter<String>(this,R.layout.lista_item,R.id.tvItem, con.lote);
            try {
                listPacientes.setAdapter(adapterr);
            }catch (NullPointerException e){

            }



        }
    }

    private void setupSearchView() {
        Buscador.setIconifiedByDefault(false);
        Buscador.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        Buscador.setSubmitButtonEnabled(false);

    }
}