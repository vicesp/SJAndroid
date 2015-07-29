package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;


import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;
import au.com.bytecode.opencsv.CSVWriter;
import config.DataBaseHelper;


public class Cuajado extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String [] silo, familia_cuajado, Nombre_PT, listaProductos;
    private String silo_select;
    private Spinner spSilo;
    private TextView Fecha,Lote,NumTina,NumEquipo;
    private Button  Guarda2,Regresar, btnFamiliaCuaj;
    private ImageButton Guarda1;
    private int num_tina_consec;
    private EditText lecheSilo,phLeche,grasaLecheSilo,grasaLecheTina,proteinaLecheSilo,lecheTina,proteinaTina;
    private EditText adi1,lote1,adi2,lote2,adi3,lote3,adi4,lote4,adi5,lote5,adi6,lote6,adi7,lote7,adi8,lote8,adi9,lote9,adi10,lote10,adi11,lote11,adi12,lote12,adi13,lote13,adi14,lote14;
    private EditText tempCoagulacion,phPasta,horaAdicionCuajo,tempCocido;
    private double num_phleche;
    private ArrayList<String> array_sort;
    int textlength=0;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private AlertDialog myalertDialog=null;
    DataBaseHelper myDbHelper = new DataBaseHelper(Variables.getContextoGral());
    protected SQLiteDatabase db;
    protected Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuajado);



        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        Variables.setIp_servidor(con.DAOSelecConfigIP());

        //******************    Edit Text    ****************//
        lecheSilo=(EditText)findViewById(R.id.etLecheCuajado);
        grasaLecheSilo=(EditText)findViewById(R.id.etGrasaLecheCuaj);
        phLeche=(EditText)findViewById(R.id.etpHLecheCuajado);


        proteinaLecheSilo=(EditText)findViewById(R.id.etProteinaLecheCuajado);
        /*grasaCrema=(EditText)findViewById(R.id.etGrasaCremaCuajado);
        cremaEstandarizada=(EditText)findViewById(R.id.etCremaCuajado);*/
        lecheTina=(EditText)findViewById(R.id.etLecheTinaCuajado);
        grasaLecheTina=(EditText)findViewById(R.id.etGrasaTinaCuajado);
        proteinaTina=(EditText)findViewById(R.id.etProteinaTinaCuajado);

        tempCoagulacion=(EditText)findViewById(R.id.etTempCuajado1);
        phPasta=(EditText)findViewById(R.id.etpHPastaCoagulacion);
        horaAdicionCuajo=(EditText)findViewById(R.id.etHoraAdiciCoagulacion);
        tempCocido=(EditText)findViewById(R.id.editText29);

        adi1=(EditText)findViewById(R.id.etAditiCuajado1);
        lote1=(EditText)findViewById(R.id.etLote);
        adi2=(EditText)findViewById(R.id.etAditiCuajado2);
        lote2=(EditText)findViewById(R.id.etLote2);
        adi3=(EditText)findViewById(R.id.etAditiCuajado3);
        lote3=(EditText)findViewById(R.id.etLote3);
        adi4=(EditText)findViewById(R.id.etAditiCuajado4);
        lote4=(EditText)findViewById(R.id.etLote4);
        adi5=(EditText)findViewById(R.id.etAditiCuajado5);
        lote5=(EditText)findViewById(R.id.etLote5);
        adi6=(EditText)findViewById(R.id.etAditiCuajado6);
        lote6=(EditText)findViewById(R.id.etLote6);
        adi7=(EditText)findViewById(R.id.etAditiCuajado7);
        lote7=(EditText)findViewById(R.id.etLote7);
        adi8=(EditText)findViewById(R.id.etAditiCuajado8);
        lote8=(EditText)findViewById(R.id.etLote8);
        adi9=(EditText)findViewById(R.id.etAditiCuajado9);
        lote9=(EditText)findViewById(R.id.etLote9);
        adi10=(EditText)findViewById(R.id.etAditiCuajado10);
        lote10=(EditText)findViewById(R.id.etLote10);
        adi11=(EditText)findViewById(R.id.etAditiCuajado11);
        lote11=(EditText)findViewById(R.id.etLote11);
        adi12=(EditText)findViewById(R.id.etAditiCuajado12);
        lote12=(EditText)findViewById(R.id.etLote1000);
        adi13=(EditText)findViewById(R.id.etAditiCuajado13);
        lote13=(EditText)findViewById(R.id.etLote12);
        adi14=(EditText)findViewById(R.id.etAditiCuajado14);
        lote14=(EditText)findViewById(R.id.etLote13);

        //******************    Text Viewes    ****************//

        Fecha=(TextView)findViewById(R.id.tvFechaCuaj);
        Fecha.setText(FechaH.Hoy());

        NumEquipo=(TextView)findViewById(R.id.tvNumequipo);
        NumEquipo.setText(Variables.getEquipo_tina());

        con.DAOC_llena_info_sin_guardar(NumEquipo.getText().toString(), Fecha.getText().toString());


        lecheSilo.setText(Variables.getLecheSilo());
        grasaLecheSilo.setText(Variables.getGrasaLecheSilo());
        grasaLecheSilo.addTextChangedListener(new CurrencyTextWatcher());

        phLeche.setText(Variables.getPhLeche());
        phLeche.addTextChangedListener(new CurrencyTextWatcher());


        proteinaLecheSilo.setText(Variables.getProteinaLecheSilo());
        proteinaLecheSilo.addTextChangedListener(new CurrencyTextWatcher());

       /* grasaCrema.setText(Variables.getGrasaCrema());
        grasaCrema.addTextChangedListener(new CurrencyTextWatcher());

        cremaEstandarizada.setText(Variables.getCremaEstandarizada());*/
        lecheTina.setText(Variables.getLecheTina());

        grasaLecheTina.setText(Variables.getGrasaLecheTina());
        grasaLecheTina.addTextChangedListener(new CurrencyTextWatcher());

        proteinaTina.setText(Variables.getProteinaTina());
        proteinaTina.addTextChangedListener(new CurrencyTextWatcher());

        tempCoagulacion.setText(Variables.getTempCoagulacion());
        tempCoagulacion.addTextChangedListener(new CurrencyTextWatcher1decimal());

        phPasta.setText(Variables.getPhPasta());
        phPasta.addTextChangedListener(new CurrencyTextWatcher());

        horaAdicionCuajo.setText(Variables.getHoraAdicionCuajo());
        horaAdicionCuajo.addTextChangedListener(new CurrencyTextWatcherHoras());

        tempCocido.setText(Variables.getTempCocido());
        tempCocido.addTextChangedListener(new CurrencyTextWatcher1decimal());


        NumTina=(TextView)findViewById(R.id.tvNumTina);

        if (NumEquipo.getText().toString()=="A"){
            if(Variables.getNumero_tina_A()!=0){

            if(Variables.isPendiente_tina_A()){
                num_tina_consec=Variables.getNumero_tina_A();
            }
            else{
                if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_C()!=0){
                    if(Variables.getNumero_tina_B()<Variables.getNumero_tina_C()){
                        num_tina_consec=Variables.getNumero_tina_C()+1;
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_B()+1;}
                }
                else{
                    num_tina_consec=Variables.getNumero_tina_A()+1;
                }

            }
            }
            else{
                if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_C()!=0){
                    if(Variables.getNumero_tina_B()<Variables.getNumero_tina_C()){
                        num_tina_consec=Variables.getNumero_tina_C()+1;
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_B()+1;}
                }
                else{
                    num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                    num_tina_consec+=1;
                    //num_tina_consec=Variables.getNum_tina_mostrar();
                }
            }

        }
        /*else if (Variables.getNumero_tina_B()!=0 && NumEquipo.getText().toString()=="B"){
            num_tina_consec=Variables.getNumero_tina_B();
        }*/
        else if (NumEquipo.getText().toString()=="B"){
            if(Variables.getNumero_tina_B()!=0){

                if(Variables.isPendiente_tina_B()){
                    num_tina_consec=Variables.getNumero_tina_B();
                }
                else{
                    if(Variables.getNumero_tina_A()!=0 || Variables.getNumero_tina_C()!=0){
                        if(Variables.getNumero_tina_A()<Variables.getNumero_tina_C()){
                            num_tina_consec=Variables.getNumero_tina_C()+1;
                        }
                        else{
                            num_tina_consec=Variables.getNumero_tina_A()+1;}
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_B()+1;
                    }

                }
            }
            else{
                if(Variables.getNumero_tina_A()!=0 || Variables.getNumero_tina_C()!=0){
                    if(Variables.getNumero_tina_A()<Variables.getNumero_tina_C()){
                        num_tina_consec=Variables.getNumero_tina_C()+1;
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_A()+1;}
                }
                else{
                    num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                    num_tina_consec+=1;
                    //num_tina_consec=Variables.getNum_tina_mostrar();
                }
            }

        }
        /*else if (Variables.getNumero_tina_C()!=0 && NumEquipo.getText().toString()=="C"){
            num_tina_consec=Variables.getNumero_tina_C();
        }*/

        else if (NumEquipo.getText().toString()=="C"){
            if(Variables.getNumero_tina_C()!=0){

                if(Variables.isPendiente_tina_C()){
                    num_tina_consec=Variables.getNumero_tina_C();
                }
                else{
                    if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_A()!=0){
                        if(Variables.getNumero_tina_B()<Variables.getNumero_tina_A()){
                            num_tina_consec=Variables.getNumero_tina_A()+1;
                        }
                        else{
                            num_tina_consec=Variables.getNumero_tina_B()+1;}
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_C()+1;
                    }

                }
            }
            else{
                if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_A()!=0){
                    if(Variables.getNumero_tina_B()<Variables.getNumero_tina_A()){
                        num_tina_consec=Variables.getNumero_tina_A()+1;
                    }
                    else{
                        num_tina_consec=Variables.getNumero_tina_B()+1;}
                }
                else{
                    num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                    num_tina_consec+=1;
                    //num_tina_consec=Variables.getNum_tina_mostrar();
                }
            }

        }

        else{
            num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
            num_tina_consec+=1;
        }


        NumTina.setText(""+num_tina_consec);

        Lote=(TextView)findViewById(R.id.tvLoteCuajado);
        Lote.setText(NumTina.getText().toString()+DiaJ.Dame_dia_J_y_anio());
        con.DAOC_llena_info_sin_guardar_aditivos(Lote.getText().toString());
        adi1.setText(Variables.getAdi1());
        lote1.setText(Variables.getLote1());
        adi2.setText(Variables.getAdi2());
        lote2.setText(Variables.getLote2());
        adi3.setText(Variables.getAdi3());
        lote3.setText(Variables.getLote3());
        adi4.setText(Variables.getAdi4());
        lote4.setText(Variables.getLote4());
        adi5.setText(Variables.getAdi5());
        lote5.setText(Variables.getLote5());
        adi6.setText(Variables.getAdi6());
        lote6.setText(Variables.getLote6());
        adi7.setText(Variables.getAdi7());
        lote7.setText(Variables.getLote7());
        adi8.setText(Variables.getAdi8());
        lote8.setText(Variables.getLote8());
        adi9.setText(Variables.getAdi9());
        lote9.setText(Variables.getLote9());
        adi10.setText(Variables.getAdi10());
        lote10.setText(Variables.getLote10());
        adi11.setText(Variables.getAdi11());
        lote11.setText(Variables.getLote11());
        adi12.setText(Variables.getAdi12());
        lote12.setText(Variables.getLote12());
        adi13.setText(Variables.getAdi13());
        lote13.setText(Variables.getLote13());
        adi14.setText(Variables.getAdi14());
        lote14.setText(Variables.getLote14());



        //con.DAOC_elimina_registro(Lote.getText().toString());

        //******************    Spinners    ****************//

        spSilo = (Spinner) findViewById(R.id.spSilo);
        silo=getResources().getStringArray(R.array.numero_silo);
        ArrayAdapter<String> siloAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, silo);
        siloAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSilo.setAdapter(siloAdapter);


        
        

        spSilo.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(25);
                silo_select=silo[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });




        //******************    Inicio Buttons    ****************//
        Guarda1=(ImageButton)findViewById(R.id.btnGuardaCuajo1);
        Guarda1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (Variables.isPendiente_tina_A()){
                    con.DAOC_elimina_registro(Lote.getText().toString());
                    //Variables.setPendiente_tina_A(false);

                }else if (Variables.isPendiente_tina_B()){
                    con.DAOC_elimina_registro(Lote.getText().toString());
                    //Variables.setPendiente_tina_B(false);
                }
                else if (Variables.isPendiente_tina_C()){
                    con.DAOC_elimina_registro(Lote.getText().toString());
                    //Variables.setPendiente_tina_C(false);
                }
                if(var.isFromCuajado()) {


                    boolean exitoso = con.DAOActualizarCuajado(Lote.getText().toString(),
                            silo_select,
                            NumTina.getText().toString(),
                            btnFamiliaCuaj.getText().toString(),
                            Fecha.getText().toString(),
                            lecheSilo.getText().toString(), grasaLecheSilo.getText().toString(), phLeche.getText().toString(), proteinaLecheSilo.getText().toString(),
                            lecheTina.getText().toString(), grasaLecheTina.getText().toString(),
                            proteinaTina.getText().toString(), tempCoagulacion.getText().toString(), phPasta.getText().toString(), horaAdicionCuajo.getText().toString(),
                            tempCocido.getText().toString(), NumEquipo.getText().toString(), 1, 0);

                    boolean exitoso_Aditivos = con.DAOActualizarCuajadoAditivos(Lote.getText().toString(), adi1.getText().toString(), lote1.getText().toString(), adi2.getText().toString(), lote2.getText().toString(),
                            adi3.getText().toString(), lote3.getText().toString(), adi4.getText().toString(), lote4.getText().toString(), adi5.getText().toString(), lote5.getText().toString(),
                            adi6.getText().toString(), lote6.getText().toString(), adi7.getText().toString(), lote7.getText().toString(), adi8.getText().toString(), lote8.getText().toString(),
                            adi9.getText().toString(), lote9.getText().toString(), adi10.getText().toString(), lote10.getText().toString(), adi11.getText().toString(), lote11.getText().toString(),
                            adi12.getText().toString(), lote12.getText().toString(), adi13.getText().toString(), lote13.getText().toString(), adi14.getText().toString(), lote14.getText().toString(), 1);


                    if (exitoso && exitoso_Aditivos) {

                        var.setFromCuajado(false);

                        GuardaCuajadoSync task = new GuardaCuajadoSync();
                        task.execute();

                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        /*num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                        num_tina_consec+=1;
                        NumTina.setText(""+num_tina_consec);*/

                        /*if(NumEquipo.getText().toString()=="A"){

                            Variables.setNumero_tina_A(Integer.parseInt(NumTina.getText().toString()));
*/
                            /*if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_C()!=0){

                                if(Variables.getNumero_tina_B()<Variables.getNumero_tina_C()){
                                    num_tina_consec=Variables.getNumero_tina_C()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_B()+1;}
                            }*/
                        if (NumEquipo.getText().toString() == "A") {
                            Variables.setNumero_tina_A(Integer.parseInt(NumTina.getText().toString()));
                        } else if (NumEquipo.getText().toString() == "B") {
                            Variables.setNumero_tina_B(Integer.parseInt(NumTina.getText().toString()));
                        } else if (NumEquipo.getText().toString() == "C") {
                            Variables.setNumero_tina_C(Integer.parseInt(NumTina.getText().toString()));
                        }

                        if (Variables.getNumero_tina_A() > Variables.getNumero_tina_B() && Variables.getNumero_tina_A() > Variables.getNumero_tina_C()) {
                            //System.out.println("El numero mayor es " + A);
                            num_tina_consec = Variables.getNumero_tina_A() + 1;
                        } else {
                            if (Variables.getNumero_tina_B() > Variables.getNumero_tina_A() && Variables.getNumero_tina_B() > Variables.getNumero_tina_C()) {
                                //System.out.println("El numero mayor es " + B);
                                num_tina_consec = Variables.getNumero_tina_B() + 1;
                            } else {
                                //System.out.println("El numero mayor es " + C);
                                num_tina_consec = Variables.getNumero_tina_C() + 1;
                            }
                        }

                            /*else{
                                num_tina_consec=Variables.getNumero_tina_A()+1;
                            }*/

                            /*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*/
                         /*   NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_A(false);
                        }*/
                        /*else if(NumEquipo.getText().toString()=="B"){
                            int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_B((sig+1));
                            NumTina.setText(""+Variables.getNumero_tina_B());
                            Variables.setPendiente_tina_B(true);
                        }*/

                        /*else if(NumEquipo.getText().toString()=="B"){

                            Variables.setNumero_tina_B(Integer.parseInt(NumTina.getText().toString()));

                            if(Variables.getNumero_tina_A()!=0 || Variables.getNumero_tina_C()!=0){
                                if(Variables.getNumero_tina_A()<Variables.getNumero_tina_C()){
                                    num_tina_consec=Variables.getNumero_tina_C()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_A()+1;}
                            }
                            else{
                                num_tina_consec=Variables.getNumero_tina_B()+1;
                            }

                            *//*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*//*
                            NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_B(false);
                        }

                        *//*else if(NumEquipo.getText().toString()=="C"){
                            int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_C((sig+1));
                            NumTina.setText(""+Variables.getNumero_tina_C());
                            Variables.setPendiente_tina_C(true);
                        }*//*

                        else if(NumEquipo.getText().toString()=="C"){

                            Variables.setNumero_tina_C(Integer.parseInt(NumTina.getText().toString()));

                            if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_A()!=0){
                                if(Variables.getNumero_tina_B()<Variables.getNumero_tina_A()){
                                    num_tina_consec=Variables.getNumero_tina_A()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_B()+1;}
                            }
                            else{
                                num_tina_consec=Variables.getNumero_tina_C()+1;
                            }

                            *//*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*//*
                            NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_A(false);
                        }*/

                        NumTina.setText("" + num_tina_consec);
                        Lote.setText(NumTina.getText().toString() + DiaJ.Dame_dia_J_y_anio());


                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                    limpia_campos();


                }
                else {
                    boolean exitoso = con.DAOCuajado(Lote.getText().toString(),
                            silo_select,
                            NumTina.getText().toString(),
                            btnFamiliaCuaj.getText().toString(),
                            FechaH.Hoy_hora(),
                            lecheSilo.getText().toString(), grasaLecheSilo.getText().toString(), phLeche.getText().toString(), proteinaLecheSilo.getText().toString(),
                            lecheTina.getText().toString(), grasaLecheTina.getText().toString(),
                            proteinaTina.getText().toString(), tempCoagulacion.getText().toString(), phPasta.getText().toString(), horaAdicionCuajo.getText().toString(),
                            tempCocido.getText().toString(), NumEquipo.getText().toString(), 1, 0, FechaH.Hoy());

                    boolean exitoso_Aditivos = con.DAOCuajadoAditivos(Lote.getText().toString(), adi1.getText().toString(), lote1.getText().toString(), adi2.getText().toString(), lote2.getText().toString(),
                            adi3.getText().toString(), lote3.getText().toString(), adi4.getText().toString(), lote4.getText().toString(), adi5.getText().toString(), lote5.getText().toString(),
                            adi6.getText().toString(), lote6.getText().toString(), adi7.getText().toString(), lote7.getText().toString(), adi8.getText().toString(), lote8.getText().toString(),
                            adi9.getText().toString(), lote9.getText().toString(), adi10.getText().toString(), lote10.getText().toString(), adi11.getText().toString(), lote11.getText().toString(),
                            adi12.getText().toString(), lote12.getText().toString(), adi13.getText().toString(), lote13.getText().toString(), adi14.getText().toString(), lote14.getText().toString(), 1);
                    if (exitoso && exitoso_Aditivos) {

                        GuardaCuajadoSync task = new GuardaCuajadoSync();
                        task.execute();

                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                        /*num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                        num_tina_consec+=1;
                        NumTina.setText(""+num_tina_consec);*/

                        /*if(NumEquipo.getText().toString()=="A"){

                            Variables.setNumero_tina_A(Integer.parseInt(NumTina.getText().toString()));
*/
                            /*if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_C()!=0){

                                if(Variables.getNumero_tina_B()<Variables.getNumero_tina_C()){
                                    num_tina_consec=Variables.getNumero_tina_C()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_B()+1;}
                            }*/
                        if (NumEquipo.getText().toString() == "A") {
                            Variables.setNumero_tina_A(Integer.parseInt(NumTina.getText().toString()));
                        } else if (NumEquipo.getText().toString() == "B") {
                            Variables.setNumero_tina_B(Integer.parseInt(NumTina.getText().toString()));
                        } else if (NumEquipo.getText().toString() == "C") {
                            Variables.setNumero_tina_C(Integer.parseInt(NumTina.getText().toString()));
                        }

                        if (Variables.getNumero_tina_A() > Variables.getNumero_tina_B() && Variables.getNumero_tina_A() > Variables.getNumero_tina_C()) {
                            //System.out.println("El numero mayor es " + A);
                            num_tina_consec = Variables.getNumero_tina_A() + 1;
                        } else {
                            if (Variables.getNumero_tina_B() > Variables.getNumero_tina_A() && Variables.getNumero_tina_B() > Variables.getNumero_tina_C()) {
                                //System.out.println("El numero mayor es " + B);
                                num_tina_consec = Variables.getNumero_tina_B() + 1;
                            } else {
                                //System.out.println("El numero mayor es " + C);
                                num_tina_consec = Variables.getNumero_tina_C() + 1;
                            }
                        }

                            /*else{
                                num_tina_consec=Variables.getNumero_tina_A()+1;
                            }*/

                            /*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*/
                         /*   NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_A(false);
                        }*/
                        /*else if(NumEquipo.getText().toString()=="B"){
                            int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_B((sig+1));
                            NumTina.setText(""+Variables.getNumero_tina_B());
                            Variables.setPendiente_tina_B(true);
                        }*/

                        /*else if(NumEquipo.getText().toString()=="B"){

                            Variables.setNumero_tina_B(Integer.parseInt(NumTina.getText().toString()));

                            if(Variables.getNumero_tina_A()!=0 || Variables.getNumero_tina_C()!=0){
                                if(Variables.getNumero_tina_A()<Variables.getNumero_tina_C()){
                                    num_tina_consec=Variables.getNumero_tina_C()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_A()+1;}
                            }
                            else{
                                num_tina_consec=Variables.getNumero_tina_B()+1;
                            }

                            *//*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*//*
                            NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_B(false);
                        }

                        *//*else if(NumEquipo.getText().toString()=="C"){
                            int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_C((sig+1));
                            NumTina.setText(""+Variables.getNumero_tina_C());
                            Variables.setPendiente_tina_C(true);
                        }*//*

                        else if(NumEquipo.getText().toString()=="C"){

                            Variables.setNumero_tina_C(Integer.parseInt(NumTina.getText().toString()));

                            if(Variables.getNumero_tina_B()!=0 || Variables.getNumero_tina_A()!=0){
                                if(Variables.getNumero_tina_B()<Variables.getNumero_tina_A()){
                                    num_tina_consec=Variables.getNumero_tina_A()+1;
                                }
                                else{
                                    num_tina_consec=Variables.getNumero_tina_B()+1;}
                            }
                            else{
                                num_tina_consec=Variables.getNumero_tina_C()+1;
                            }

                            *//*int sig=Integer.parseInt(NumTina.getText().toString());
                            Variables.setNumero_tina_A((sig+1));*//*
                            NumTina.setText(""+num_tina_consec);
                            Variables.setPendiente_tina_A(false);
                        }*/

                        NumTina.setText("" + num_tina_consec);
                        Lote.setText(NumTina.getText().toString() + DiaJ.Dame_dia_J_y_anio());


                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }
            }
        });

        Regresar=(Button)findViewById(R.id.btnCRegresar);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                boolean exitoso=con.DAOCuajado(Lote.getText().toString(),
                        silo_select,
                        NumTina.getText().toString(),
                        btnFamiliaCuaj.getText().toString(),
                        FechaH.Hoy_hora(),
                        lecheSilo.getText().toString(), grasaLecheSilo.getText().toString(), phLeche.getText().toString(), proteinaLecheSilo.getText().toString(),
                        lecheTina.getText().toString(), grasaLecheTina.getText().toString(),
                        proteinaTina.getText().toString(), tempCoagulacion.getText().toString(), phPasta.getText().toString(), horaAdicionCuajo.getText().toString(),
                        tempCocido.getText().toString(),NumEquipo.getText().toString(),0,0, FechaH.Hoy());

                boolean exitoso_Aditivos=con.DAOCuajadoAditivos(Lote.getText().toString(), adi1.getText().toString(),lote1.getText().toString(),adi2.getText().toString(),lote2.getText().toString(),
                        adi3.getText().toString(),lote3.getText().toString(),adi4.getText().toString(),lote4.getText().toString(),adi5.getText().toString(),lote5.getText().toString(),
                        adi6.getText().toString(),lote6.getText().toString(),adi7.getText().toString(),lote7.getText().toString(),adi8.getText().toString(),lote8.getText().toString(),
                        adi9.getText().toString(),lote9.getText().toString(),adi10.getText().toString(),lote10.getText().toString(),adi11.getText().toString(),lote11.getText().toString(),
                        adi12.getText().toString(),lote12.getText().toString(),adi13.getText().toString(),lote13.getText().toString(),adi14.getText().toString(),lote14.getText().toString(),0);

                if(exitoso && exitoso_Aditivos){

                    //num_tina_consec=con.DAOC_Numero_tina(FechaH.Hoy());
                    //num_tina_consec+=1;
                    //NumTina.setText(""+num_tina_consec);
                    con.DAOC_llena_info_sin_guardar_aditivos(Lote.getText().toString());
                    if(NumEquipo.getText().toString()=="A"){
                        int n=Integer.parseInt(NumTina.getText().toString());
                    Variables.setNumero_tina_A(n);
                    Variables.setPendiente_tina_A(true);
                    }
                    else if(NumEquipo.getText().toString()=="B"){
                        int n=Integer.parseInt(NumTina.getText().toString());
                        Variables.setNumero_tina_B(n);
                        Variables.setPendiente_tina_B(true);
                    }
                    else if(NumEquipo.getText().toString()=="C"){
                        int n=Integer.parseInt(NumTina.getText().toString());
                        Variables.setNumero_tina_C(n);
                        Variables.setPendiente_tina_C(true);
                    }


                }


                finish();
            }
        });
        btnFamiliaCuaj = (Button) findViewById(R.id.spFamilia);
        btnFamiliaCuaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView();
            }
        });

        
        

        //******************    Fin Buttons   ****************//


        if (var.isFromCuajado())
        {
            llenarValoresBusquedaCuajado(var.getLoteCuajado());
            llenarValoresBusquedaCuajadoAditivos(var.getLoteCuajado());

        }
        else
        {
            Guarda1.setImageResource(R.drawable.guarda);

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
    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        String strName=array_sort.get(position);
        btnFamiliaCuaj.setText(strName);


        myalertDialog.dismiss();
    }
    @Override
    public void onClick(View v) {

    }

    class CurrencyTextWatcher implements TextWatcher {

        boolean mEditing;

        public CurrencyTextWatcher() {
            mEditing = false;
        }

        public synchronized void afterTextChanged(Editable s) {
            if(!mEditing) {
                mEditing = true;

                String digits = s.toString().replaceAll("\\D", "");
                DecimalFormat df=new DecimalFormat("##,##.##");
                df.setDecimalSeparatorAlwaysShown(true);
                try{
                    String formatted = df.format(Integer.parseInt(digits));
                    //round(Double.parseDouble(digits),2);
                    s.replace(0, s.length(), formatted);
                } catch (NumberFormatException nfe) {
                    s.clear();
                }

                mEditing = false;
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        public void onTextChanged(CharSequence s, int start, int before, int count) { }

    }

    class CurrencyTextWatcher1decimal implements TextWatcher {

        boolean mEditing;

        public CurrencyTextWatcher1decimal() {
            mEditing = false;
        }

        public synchronized void afterTextChanged(Editable s) {
            if(!mEditing) {
                mEditing = true;

                String digits = s.toString().replaceAll("\\D", "");
                DecimalFormat df=new DecimalFormat("#,#.#");
                df.setDecimalSeparatorAlwaysShown(true);
                try{
                    String formatted = df.format(Integer.parseInt(digits));
                    s.replace(0, s.length(), formatted);
                } catch (NumberFormatException nfe) {
                    s.clear();
                }

                mEditing = false;
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        public void onTextChanged(CharSequence s, int start, int before, int count) { }

    }

    class CurrencyTextWatcherHoras implements TextWatcher {

        boolean mEditing;

        public CurrencyTextWatcherHoras() {
            mEditing = false;
        }

        public synchronized void afterTextChanged(Editable s) {
            if(!mEditing) {
                mEditing = true;

                String digits = s.toString().replaceAll("\\D", "");
                Locale locale = new Locale("es", "MX");
                String forma="##,##.##";
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
                symbols.setDecimalSeparator(';');
                symbols.setGroupingSeparator(':');


                DecimalFormat df=new DecimalFormat(forma,symbols);

                try{
                    String formatted = df.format(Integer.parseInt(digits));
                    s.replace(0, s.length(), formatted);
                } catch (NumberFormatException nfe) {
                    s.clear();
                }

                mEditing = false;
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        public void onTextChanged(CharSequence s, int start, int before, int count) { }

    }

    public static String round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        double total;
        total=tmp / factor;
        return ""+total;
    }

    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cuajado.this);
        alertDialogBuilder.setTitle("Aviso");
        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void llenarValoresBusquedaCuajado(String lote)
    {

        //ote_origen.setEnabled(false);
        //btn_listviewdialog.setVisibility(View.INVISIBLE);

        cursor = con.DAOLLenarCuajado(lote);
        btnFamiliaCuaj.setText(cursor.getString(cursor.getColumnIndex("familia")));
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_hoy")));
        lecheSilo.setText(cursor.getString(cursor.getColumnIndex("leche_silo")));
        phLeche.setText(cursor.getString(cursor.getColumnIndex("ph_leche")));
        grasaLecheSilo.setText(cursor.getString(cursor.getColumnIndex("porcen_grasa_leche")));
        proteinaLecheSilo.setText(cursor.getString(cursor.getColumnIndex("porce_proteina")));
        lecheTina.setText(cursor.getString(cursor.getColumnIndex("leche_tina")));
        grasaLecheTina.setText(cursor.getString(cursor.getColumnIndex("porce_grasa_leche_tina")));
        proteinaTina.setText(cursor.getString(cursor.getColumnIndex("porce_prot_tina")));
        tempCoagulacion.setText(cursor.getString(cursor.getColumnIndex("temp_adi_cuajo")));
        horaAdicionCuajo.setText(cursor.getString(cursor.getColumnIndex("hora_adi_cuajo")));
        tempCocido.setText(cursor.getString(cursor.getColumnIndex("temp_cocido")));
        phPasta.setText(cursor.getString(cursor.getColumnIndex("ph_pasta_coag")));

        NumEquipo.setText(cursor.getString(cursor.getColumnIndex("num_equipo")));
        NumTina.setText(cursor.getString(cursor.getColumnIndex("num_tina")));
        Lote.setText(lote);

        if(silo[0].equals(cursor.getString(cursor.getColumnIndex("silo"))))
        {
            spSilo.setSelection(0);
        }

        else if(silo[0].equals(cursor.getString(cursor.getColumnIndex("silo"))))
        {
            spSilo.setSelection(1);
        }

        else
        {
            spSilo.setSelection(2);
        }


    }

    public void llenarValoresBusquedaCuajadoAditivos(String lote)
    {

        cursor = con.DAOLLenarCuajadoAditivos(lote);
        adi1.setText(cursor.getString(cursor.getColumnIndex("mp001")));
        adi2.setText(cursor.getString(cursor.getColumnIndex("mp003")));
        adi3.setText(cursor.getString(cursor.getColumnIndex("mp002")));
        adi4.setText(cursor.getString(cursor.getColumnIndex("mp006")));
        adi5.setText(cursor.getString(cursor.getColumnIndex("mp011")));
        adi6.setText(cursor.getString(cursor.getColumnIndex("mp021")));
        adi7.setText(cursor.getString(cursor.getColumnIndex("cr01")));
        adi8.setText(cursor.getString(cursor.getColumnIndex("mp025")));
        adi9.setText(cursor.getString(cursor.getColumnIndex("mp062")));
        adi10.setText(cursor.getString(cursor.getColumnIndex("mp070")));
        adi11.setText(cursor.getString(cursor.getColumnIndex("mp071")));
        adi12.setText(cursor.getString(cursor.getColumnIndex("mp072")));
        adi13.setText(cursor.getString(cursor.getColumnIndex("le04")));
        adi14.setText(cursor.getString(cursor.getColumnIndex("le03")));

        lote1.setText(cursor.getString(cursor.getColumnIndex("lote_mp001")));
        lote2.setText(cursor.getString(cursor.getColumnIndex("lote_mp003")));
        lote3.setText(cursor.getString(cursor.getColumnIndex("lote_mp002")));
        lote4.setText(cursor.getString(cursor.getColumnIndex("lote_mp006")));
        lote5.setText(cursor.getString(cursor.getColumnIndex("lote_mp011")));
        lote6.setText(cursor.getString(cursor.getColumnIndex("lote_mp021")));
        lote7.setText(cursor.getString(cursor.getColumnIndex("lote_cr01")));
        lote8.setText(cursor.getString(cursor.getColumnIndex("lote_mp025")));
        lote9.setText(cursor.getString(cursor.getColumnIndex("lote_mp062")));
        lote10.setText(cursor.getString(cursor.getColumnIndex("lote_mp070")));
        lote11.setText(cursor.getString(cursor.getColumnIndex("lote_mp071")));
        lote12.setText(cursor.getString(cursor.getColumnIndex("lote_mp072")));
        lote13.setText(cursor.getString(cursor.getColumnIndex("lote_le04")));
        lote14.setText(cursor.getString(cursor.getColumnIndex("lote_le03")));




    }
    public void limpia_campos()
    {
        //Fecha.setText("");
        lecheSilo.setText("");
        phLeche.setText("");
        grasaLecheSilo.setText("");
        proteinaLecheSilo.setText("");
        lecheTina.setText("");
        grasaLecheTina.setText("");
        proteinaTina.setText("");
        tempCoagulacion.setText("");
        horaAdicionCuajo.setText("");
        tempCocido.setText("");
        phPasta.setText("");
        btnFamiliaCuaj.setText("Seleccione Familia");

        NumEquipo.setText("");
        NumTina.setText("");
        //Lote.setText("");

        adi1.setText("");
        adi2.setText("");
        adi3.setText("");
        adi4.setText("");
        adi5.setText("");
        adi6.setText("");
        adi7.setText("");
        adi8.setText("");
        adi9.setText("");
        adi10.setText("");
        adi11.setText("");
        adi12.setText("");
        adi13.setText("");
        adi14.setText("");

        lote1.setText("");
        lote2.setText("");
        lote3.setText("");
        lote4.setText("");
        lote5.setText("");
        lote6.setText("");
        lote7.setText("");
        lote8.setText("");
        lote9.setText("");
        lote10.setText("");
        lote11.setText("");
        lote12.setText("");
        lote13.setText("");
        lote14.setText("");
    }
    public void launchView()
    {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Cuajado.this);


        Nombre_PT = getProductosArray(con.DAOGetTodosFamilias(false, true));




        //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
        final EditText editText = new EditText(Cuajado.this);
        final ListView listview = new ListView(Cuajado.this);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
        array_sort = new ArrayList<String>(Arrays.asList(Nombre_PT));
        LinearLayout layout = new LinearLayout(Cuajado.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText);
        layout.addView(listview);
        myDialog.setView(layout);
        CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Cuajado.this, array_sort);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener(Cuajado.this);
        editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                textlength = editText.getText().length();
                array_sort.clear();
                for (int i = 0; i < Nombre_PT.length; i++) {
                    if (textlength <= Nombre_PT[i].length()) {

                        if (Nombre_PT[i].toLowerCase().contains(editText.getText().toString().toLowerCase().trim())) {
                            array_sort.add(Nombre_PT[i]);
                        }
                    }
                }
                listview.setAdapter(new CustomAlertAdapter(Cuajado.this, array_sort));
            }
        });
        myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        myalertDialog = myDialog.show();

    }
    public String[] getProductosArray(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {

            listaProductos = con.producto;
        }
        return listaProductos;

    }


    public class GuardaCuajadoSync extends AsyncTask<String, Void, Boolean>

    {
        private final ProgressDialog dialog = new ProgressDialog(Cuajado.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Enviando datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args)

        {

            final String NAMESPACE = "http://serv_gsj.net/";
            //final String URL="http://"+Variables.getIp_servidor()+"/ServicioClientes.asmx";
            final String URL = "http://" + Variables.getIp_servidor() + "/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertaCuajado";
            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
            final int time = 20000, time2 = 190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            /*Para tabla cuajado*/
            request.addProperty("lote", Lote.getText().toString());
            request.addProperty("silo", "" + silo_select);
            request.addProperty("num_tina", NumTina.getText().toString());
            request.addProperty("familia", btnFamiliaCuaj.getText().toString());
            request.addProperty("fecha", FechaH.Hoy_hora());
            request.addProperty("leche_silo", lecheSilo.getText().toString());
            request.addProperty("grasa_leche_silo", grasaLecheSilo.getText().toString());
            request.addProperty("ph_leche", phLeche.getText().toString());
            request.addProperty("proteina_leche", proteinaLecheSilo.getText().toString());
            request.addProperty("leche_tina", lecheTina.getText().toString());
            request.addProperty("grasa_leche_tina", grasaLecheTina.getText().toString());
            request.addProperty("proteina_tina", proteinaTina.getText().toString());
            request.addProperty("temp_coagulacion", tempCoagulacion.getText().toString());
            request.addProperty("ph_pasta", phPasta.getText().toString());
            request.addProperty("hora_adi_cuajo", horaAdicionCuajo.getText().toString());
            request.addProperty("temp_cocido", tempCocido.getText().toString());
            request.addProperty("num_equipo", NumEquipo.getText().toString());
            request.addProperty("estatus_pendiente", "0");

            /*Para Cuajado Aditivos*/

            request.addProperty("lote_aditivo", Lote.getText().toString());
            request.addProperty("adi1", adi1.getText().toString());
            request.addProperty("lote1", lote1.getText().toString());
            request.addProperty("adi2", adi2.getText().toString());
            request.addProperty("lote2", lote2.getText().toString());
            request.addProperty("adi3", adi3.getText().toString());
            request.addProperty("lote3", lote3.getText().toString());
            request.addProperty("adi4", adi4.getText().toString());
            request.addProperty("lote4", lote4.getText().toString());
            request.addProperty("adi5", adi5.getText().toString());
            request.addProperty("lote5", lote5.getText().toString());
            request.addProperty("adi6", adi6.getText().toString());
            request.addProperty("lote6", lote6.getText().toString());
            request.addProperty("adi7", adi7.getText().toString());
            request.addProperty("lote7", lote7.getText().toString());
            request.addProperty("adi8", adi8.getText().toString());
            request.addProperty("lote8", lote8.getText().toString());
            request.addProperty("adi9", adi9.getText().toString());
            request.addProperty("lote9", lote9.getText().toString());
            request.addProperty("adi10", adi10.getText().toString());
            request.addProperty("lote10", lote10.getText().toString());
            request.addProperty("adi11", adi11.getText().toString());
            request.addProperty("lote11", lote11.getText().toString());
            request.addProperty("adi12", adi12.getText().toString());
            request.addProperty("lote12", lote12.getText().toString());
            request.addProperty("adi13", adi13.getText().toString());
            request.addProperty("lote13", lote13.getText().toString());
            request.addProperty("adi14", adi14.getText().toString());
            request.addProperty("lote14", lote14.getText().toString());
//47 elementos
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL, time);

            try {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                String mensaje = resultado_xml.toString();
                if (mensaje.contentEquals("true")) {
                    //transporte.getConnection().disconnect();

                    //transporte.getServiceConnection().disconnect();

                    //transporte.reset();
                    return true;
                } else {
                    // transporte.getConnection().disconnect();
                    //transporte.getServiceConnection().disconnect();
                    //transporte.reset();
                    Log.i("Mensaje", "Mensaje SOAP:    " + mensaje);
                    return false;
                }


            } catch (Exception e) {
                Log.i("Error", "Error de Sincronizacion:  " + e);

                return false;

            }


        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (success) {
                Toast.makeText(Cuajado.this, "Sincronización Exitosa", Toast.LENGTH_SHORT).show();


            } else {
                Toast.makeText(Cuajado.this, "Error de Sincronización", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
