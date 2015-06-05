package com.example.sistemas.gsjetapa1;

/**
 *Created by Sistemas
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.app.AlertDialog;
import java.util.ArrayList;

import DAO.consultas;
import DTO.Fecha_Hoy;


public class Texturizador_Edit extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private String texturizador_select;
    private String [] familia;
    private TextView Fecha;
    private Spinner spFamiliaTextu;
    private static consultas con;
    private static ArrayAdapter adapter;
    private Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9,sw10,sw11,sw12,sw13,sw14,sw15,sw16,sw17,sw18;
    private TextView tvtx1,tvtx2,tvtx3,tvtx4,tvtx5,tvtx6,tvtx7,tvtx8,tvtx9,tvtx10,tvtx11,tvtx12,tvtx13,tvtx14,tvtx15,tvtx16,tvtx17,tvtx18;
    private TextView tvtm1,tvtm2,tvtm3,tvtm4,tvtm5,tvtm6,tvtm7,tvtm8,tvtm9,tvtm10,tvtm11,tvtm12,tvtm13,tvtm14,tvtm15,tvtm16,tvtm17,tvtm18;
    private EditText valNuevo1, valNuevo2, valNuevo3, valNuevo4, valNuevo5, valNuevo6, valNuevo7, valNuevo8, valNuevo9, valNuevo10, valNuevo11, valNuevo12, valNuevo13, valNuevo14, valNuevo15, valNuevo16, valNuevo17, valNuevo18 ;
    private Button Guardar,Buscador;
    private int in1=0,in2=0,in3=0,in4=0,in5=0,in6=0,in7=0,in8=0,in9=0,in10=0,in11=0,in12=0,in13=0,in14=0,in15=0,in16=0,in17=0,in18=0,numero_conse=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texturizador__edit);

        FechaH = new Fecha_Hoy();
        con = new consultas();

        //******************    Switch  ****************//

        sw1=(Switch)findViewById(R.id.swt1);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if(isChecked){
                    valNuevo1.setEnabled(true);
                    in1=1;
                    tvtx1.setTextColor(getResources().getColor(R.color.act));

                }else{
                    valNuevo1.setEnabled(false);
                    in1=0;
                    tvtx1.setTextColor(getResources().getColor(R.color.desac));
                    tvtm1.setText("");
                }

            }
        });

        sw2=(Switch)findViewById(R.id.swt2);
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo2.setEnabled(true);
                    in2=1;
                    tvtx2.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo2.setEnabled(false);
                    in2=0;
                    tvtx2.setTextColor(getResources().getColor(R.color.desac));
                    tvtm2.setText("");
                }

            }
        });

        sw3=(Switch)findViewById(R.id.swt3);
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo3.setEnabled(true);
                    in3=1;
                    tvtx3.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo3.setEnabled(false);
                    in3=0;
                    tvtx3.setTextColor(getResources().getColor(R.color.desac));
                    tvtm3.setText("");
                }

            }
        });
        sw4=(Switch)findViewById(R.id.swt4);
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo4.setEnabled(true);
                    in4=1;
                    tvtx4.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo4.setEnabled(false);
                    in4=0;
                    tvtx4.setTextColor(getResources().getColor(R.color.desac));
                    tvtm4.setText("");
                }

            }
        });
        sw5=(Switch)findViewById(R.id.swt5);
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo5.setEnabled(true);
                    in5=1;
                    tvtx5.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo5.setEnabled(false);
                    in5=0;
                    tvtx5.setTextColor(getResources().getColor(R.color.desac));
                    tvtm5.setText("");
                }

            }
        });
        sw6=(Switch)findViewById(R.id.swt6);
        sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo6.setEnabled(true);
                    in6=1;
                    tvtx6.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo6.setEnabled(false);
                    in6=0;
                    tvtx6.setTextColor(getResources().getColor(R.color.desac));
                    tvtm6.setText("");
                }

            }
        });
        sw7=(Switch)findViewById(R.id.swt7);
        sw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo7.setEnabled(true);
                    in7=1;
                    tvtx7.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo7.setEnabled(false);
                    in7=0;
                    tvtx7.setTextColor(getResources().getColor(R.color.desac));
                    tvtm7.setText("");
                }

            }
        });
        sw8=(Switch)findViewById(R.id.swt8);
        sw8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo8.setEnabled(true);
                    in8=1;
                    tvtx8.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo8.setEnabled(false);
                    in8=0;
                    tvtx8.setTextColor(getResources().getColor(R.color.desac));
                    tvtm8.setText("");
                }

            }
        });
        sw9=(Switch)findViewById(R.id.swt9);
        sw9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo9.setEnabled(true);
                    in9=1;
                    tvtx9.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo9.setEnabled(false);
                    in9=0;
                    tvtx9.setTextColor(getResources().getColor(R.color.desac));
                    tvtm9.setText("");
                }

            }
        });
        sw10=(Switch)findViewById(R.id.swt10);
        sw10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo10.setEnabled(true);
                    in10=1;
                    tvtx10.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo10.setEnabled(false);
                    in10=0;
                    tvtx10.setTextColor(getResources().getColor(R.color.desac));
                    tvtm10.setText("");
                }

            }
        });
        sw11=(Switch)findViewById(R.id.swt11);
        sw11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo11.setEnabled(true);
                    in11=1;
                    tvtx11.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo11.setEnabled(false);
                    in11=0;
                    tvtx11.setTextColor(getResources().getColor(R.color.desac));
                    tvtm11.setText("");
                }

            }
        });
        sw12=(Switch)findViewById(R.id.swt12);
        sw12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo12.setEnabled(true);
                    in12=1;
                    tvtx12.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo12.setEnabled(false);
                    in12=0;
                    tvtx12.setTextColor(getResources().getColor(R.color.desac));
                    tvtm12.setText("");
                }

            }
        });
        sw13=(Switch)findViewById(R.id.swt13);
        sw13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo13.setEnabled(true);
                    in13=1;
                    tvtx13.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo13.setEnabled(false);
                    in13=0;
                    tvtx13.setTextColor(getResources().getColor(R.color.desac));
                    tvtm13.setText("");
                }

            }
        });
        sw14=(Switch)findViewById(R.id.swt14);
        sw14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo14.setEnabled(true);
                    in14=1;
                    tvtx14.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo14.setEnabled(false);
                    in14=0;
                    tvtx14.setTextColor(getResources().getColor(R.color.desac));
                    tvtm14.setText("");
                }

            }
        });
        sw15=(Switch)findViewById(R.id.swt15);
        sw15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo15.setEnabled(true);
                    in15=1;
                    tvtx15.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo15.setEnabled(false);
                    in15=0;
                    tvtx15.setTextColor(getResources().getColor(R.color.desac));
                    tvtm15.setText("");
                }

            }
        });
        sw16=(Switch)findViewById(R.id.swt16);
        sw16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo16.setEnabled(true);
                    in16=1;
                    tvtx16.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo16.setEnabled(false);
                    in16=0;
                    tvtx16.setTextColor(getResources().getColor(R.color.desac));
                    tvtm16.setText("");
                }

            }
        });
        sw17=(Switch)findViewById(R.id.swt17);
        sw17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo17.setEnabled(true);
                    in17=1;
                    tvtx17.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo17.setEnabled(false);
                    in17=0;
                    tvtx17.setTextColor(getResources().getColor(R.color.desac));
                    tvtm17.setText("");
                }

            }
        });
        sw18=(Switch)findViewById(R.id.swt18);
        sw18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    valNuevo18.setEnabled(true);
                    in18=1;
                    tvtx18.setTextColor(getResources().getColor(R.color.act));
                }else{
                    valNuevo18.setEnabled(false);
                    in18=0;
                    tvtx18.setTextColor(getResources().getColor(R.color.desac));
                    tvtm18.setText("");
                }

            }
        });


        //******************    Text View    ****************//

        Fecha = (TextView) findViewById(R.id.tvtFecha);
        Fecha.setText(FechaH.Hoy());


        tvtm1 = (TextView) findViewById(R.id.textView301);
        tvtm2 = (TextView) findViewById(R.id.textView302);
        tvtm3 = (TextView) findViewById(R.id.textView303);
        tvtm4 = (TextView) findViewById(R.id.textView304);
        tvtm5 = (TextView) findViewById(R.id.textView305);
        tvtm6 = (TextView) findViewById(R.id.textView306);
        tvtm7 = (TextView) findViewById(R.id.textView307);
        tvtm8 = (TextView) findViewById(R.id.textView308);
        tvtm9 = (TextView) findViewById(R.id.textView309);
        tvtm10 = (TextView) findViewById(R.id.textView310);
        tvtm11 = (TextView) findViewById(R.id.textView311);
        tvtm12 = (TextView) findViewById(R.id.textView312);
        tvtm13 = (TextView) findViewById(R.id.textView313);
        tvtm14 = (TextView) findViewById(R.id.textView314);
        tvtm15 = (TextView) findViewById(R.id.textView315);
        tvtm16 = (TextView) findViewById(R.id.textView316);
        tvtm17 = (TextView) findViewById(R.id.textView317);
        tvtm18 = (TextView) findViewById(R.id.textView318);

        tvtx1 = (TextView) findViewById(R.id.textView98);
        tvtx2 = (TextView) findViewById(R.id.textView99);
        tvtx3 = (TextView) findViewById(R.id.textView102);
        tvtx4 = (TextView) findViewById(R.id.textView104);
        tvtx5 = (TextView) findViewById(R.id.textView107);
        tvtx6 = (TextView) findViewById(R.id.textView108);
        tvtx7 = (TextView) findViewById(R.id.textView109);
        tvtx8 = (TextView) findViewById(R.id.textView110);
        tvtx9 = (TextView) findViewById(R.id.textView111);
        tvtx10 = (TextView) findViewById(R.id.textView112);
        tvtx11 = (TextView) findViewById(R.id.textView113);
        tvtx12 = (TextView) findViewById(R.id.textView114);
        tvtx13 = (TextView) findViewById(R.id.textView115);
        tvtx14 = (TextView) findViewById(R.id.textView116);
        tvtx15 = (TextView) findViewById(R.id.textView117);
        tvtx16 = (TextView) findViewById(R.id.textView118);
        tvtx17 = (TextView) findViewById(R.id.textView119);
        tvtx18 = (TextView) findViewById(R.id.textView120);


        //******************    Edit Text    ****************//


        valNuevo1 = (EditText) findViewById(R.id.valNuevo1);
        valNuevo2 = (EditText) findViewById(R.id.valNuevo2);
        valNuevo3 = (EditText) findViewById(R.id.valNuevo3);
        valNuevo4 = (EditText) findViewById(R.id.valNuevo4);
        valNuevo5 = (EditText) findViewById(R.id.valNuevo5);
        valNuevo6 = (EditText) findViewById(R.id.valNuevo6);
        valNuevo7 = (EditText) findViewById(R.id.valNuevo7);
        valNuevo8 = (EditText) findViewById(R.id.valNuevo8);
        valNuevo9 = (EditText) findViewById(R.id.valNuevo9);
        valNuevo10 = (EditText) findViewById(R.id.valNuevo10);
        valNuevo11 = (EditText) findViewById(R.id.valNuevo11);
        valNuevo12 = (EditText) findViewById(R.id.valNuevo12);
        valNuevo13 = (EditText) findViewById(R.id.valNuevo13);
        valNuevo14 = (EditText) findViewById(R.id.valNuevo14);
        valNuevo15 = (EditText) findViewById(R.id.valNuevo15);
        valNuevo16 = (EditText) findViewById(R.id.valNuevo16);
        valNuevo17 = (EditText) findViewById(R.id.valNuevo17);
        valNuevo18 = (EditText) findViewById(R.id.valNuevo18);


        //******************    Spinner    ****************//

        spFamiliaTextu = (Spinner) findViewById(R.id.spTexturizador);
        llena_Texturizador(con.DAODescripcionTexturizador());

        spFamiliaTextu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextSize(22);
                texturizador_select = familia[position];
                switch (position) {
                    case 0:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool1");
                        setSwitches("valBool1");
                        actualizarValores("valor1");
                        break;
                    case 1:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool2");
                        setSwitches("valBool2");
                        actualizarValores("valor2");
                        break;
                    case 2:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool3");
                        setSwitches("valBool3");
                        actualizarValores("valor3");
                        break;
                    case 3:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool4");
                        setSwitches("valBool4");
                        actualizarValores("valor4");
                        break;
                    case 4:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool5");
                        setSwitches("valBool5");
                        actualizarValores("valor5");
                        break;

                    case 5:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool6");
                        setSwitches("valBool6");
                        actualizarValores("valor6");
                        break;
                    case 6:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool7");
                        setSwitches("valBool7");
                        actualizarValores("valor7");
                        break;
                    case 7:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool8");
                        setSwitches("valBool8");
                        actualizarValores("valor8");
                        break;
                    case 8:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool9");
                        setSwitches("valBool9");
                        actualizarValores("valor9");
                        break;
                    case 9:
                        vaciarValorNuevo();
                        cambia_color_textos("valBool10");
                        setSwitches("valBool10");
                        actualizarValores("valor10");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


//******************    Boton    ****************//
        Guardar = (Button) findViewById(R.id.btntSave);

        Buscador = (Button) findViewById(R.id.btntBuscar);


        Guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int position = spFamiliaTextu.getSelectedItemPosition();
                boolean exitoso=true;

                switch (position)
                {
                    case 0:
                        cambia_color_textos("valBool1");
                        exitoso = guardarValores("1");
                        setSwitches("valBool1");
                        actualizarValores("valor1");
                        break;
                    case 1:
                        cambia_color_textos("valBool2");
                        exitoso =guardarValores("2");
                        setSwitches("valBool2");
                        actualizarValores("valor2");
                        break;
                    case 2:
                        cambia_color_textos("valBool3");
                        exitoso =guardarValores("3");
                        setSwitches("valBool3");
                        actualizarValores("valor3");
                        break;
                    case 3:
                        cambia_color_textos("valBool4");
                        exitoso =guardarValores("4");
                        setSwitches("valBool4");
                        actualizarValores("valor4");
                        break;
                    case 4:
                        cambia_color_textos("valBool5");
                        exitoso =guardarValores("5");
                        setSwitches("valBool5");
                        actualizarValores("valor5");
                        break;
                    case 5:
                        cambia_color_textos("valBool6");
                        exitoso = guardarValores("6");
                        setSwitches("valBool6");
                        actualizarValores("valor6");
                        break;
                    case 6:
                        cambia_color_textos("valBool7");
                        exitoso = guardarValores("7");
                        setSwitches("valBool7");
                        actualizarValores("valor7");
                        break;
                    case 7:
                        cambia_color_textos("valBool8");
                        exitoso = guardarValores("8");
                        setSwitches("valBool8");
                        actualizarValores("valor8");
                        break;
                    case 8:
                        cambia_color_textos("valBool9");
                        exitoso = guardarValores("9");
                        setSwitches("valBool9");
                        actualizarValores("valor9");
                        break;
                    case 9:
                        cambia_color_textos("valBool10");
                        exitoso = guardarValores("10");
                        setSwitches("valBool10");
                        actualizarValores("valor10");
                        break;
                }
                if (exitoso) {
                    Alerta(getResources().getString(R.string.Alerta_Guardado));
                    //String fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
                    vaciarValorNuevo();
                } else {
                    Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                }
            }
        });
        Buscador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();startActivity(new Intent(Texturizador_Edit.this, Texturizador_Realizados.class));
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
    public void llena_Texturizador(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {
            adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, con.producto);
            familia= con.producto;
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFamiliaTextu.setAdapter(adapter);
    }

    public void cambia_color_textos(String columna){

        if(con.DAOSwitchBool(columna,Integer.toString(1))) {
            tvtx1.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx1.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"2")) {
            tvtx2.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx2.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"3")) {
            tvtx3.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx3.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"4")) {
            tvtx4.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx4.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"5")) {
            tvtx5.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx5.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"6")) {
            tvtx6.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx6.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"7")) {
            tvtx7.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx7.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"8")) {
            tvtx8.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx8.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"9")) {
            tvtx9.setTextColor(getResources().getColor(R.color.act));
        }else
        {
            tvtx9.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"10")) {
            tvtx10.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx10.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"11")) {
            tvtx11.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx11.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"12")) {
            tvtx12.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx12.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"13")) {
            tvtx13.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx13.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"14")) {
            tvtx14.setTextColor(getResources().getColor(R.color.act));
        }
        else {
            tvtx14.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"15")) {
            tvtx15.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx15.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"16")) {
            tvtx16.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx16.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"17")) {
            tvtx17.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx17.setTextColor(getResources().getColor(R.color.desac));
        }
        if(con.DAOSwitchBool(columna,"18")) {
            tvtx18.setTextColor(getResources().getColor(R.color.act));
        }
        else
        {
            tvtx18.setTextColor(getResources().getColor(R.color.desac));
        }
    }

    public void vaciarValorNuevo()
    {

        valNuevo1.setText("");
        valNuevo2.setText("");
        valNuevo3.setText("");
        valNuevo4.setText("");
        valNuevo5.setText("");
        valNuevo6.setText("");
        valNuevo7.setText("");
        valNuevo8.setText("");
        valNuevo9.setText("");
        valNuevo10.setText("");
        valNuevo11.setText("");
        valNuevo12.setText("");
        valNuevo13.setText("");
        valNuevo14.setText("");
        valNuevo15.setText("");
        valNuevo16.setText("");
        valNuevo17.setText("");
        valNuevo18.setText("");

    }

    public void actualizarValores(String columna)

    {
        Log.i("columna:", columna);
        if(sw1.isChecked()) {
            tvtm1.setText(con.DAOValoresActuales(columna, "1"));
        }
        else {
            tvtm1.setText("");
        }
        if(sw2.isChecked()) {
            tvtm2.setText(con.DAOValoresActuales(columna, "2"));
        }
        else {
            tvtm2.setText("");
        }
        if(sw3.isChecked()) {
            tvtm3.setText(con.DAOValoresActuales(columna, "3"));
        }
        else {
            tvtm3.setText("");
        }
        if(sw4.isChecked()) {
            tvtm4.setText(con.DAOValoresActuales(columna, "4"));
        }
        else {
            tvtm4.setText("");
        }
        if(sw5.isChecked()) {
            tvtm5.setText(con.DAOValoresActuales(columna, "5"));
        }
        else {
            tvtm5.setText("");
        }
        if(sw6.isChecked()) {
            tvtm6.setText(con.DAOValoresActuales(columna, "6"));
        }
        else {
            tvtm6.setText("");
        }
        if(sw7.isChecked()) {
            tvtm7.setText(con.DAOValoresActuales(columna, "7"));
        }
        else {
            tvtm7.setText("");
        }
        if(sw8.isChecked()) {
            tvtm8.setText(con.DAOValoresActuales(columna, "8"));
        }
        else {
            tvtm8.setText("");
        }
        if(sw9.isChecked()) {
            tvtm9.setText(con.DAOValoresActuales(columna, "9"));
        }
        else {
            tvtm9.setText("");
        }
        if(sw10.isChecked()) {
            tvtm10.setText(con.DAOValoresActuales(columna, "10"));
        }
        else {
            tvtm10.setText("");
        }
        if(sw11.isChecked()) {
            tvtm11.setText(con.DAOValoresActuales(columna, "11"));
        }
        else {
            tvtm11.setText("");
        }
        if(sw12.isChecked()) {
            tvtm12.setText(con.DAOValoresActuales(columna, "12"));
        }
        else {
            tvtm12.setText("");
        }
        if(sw13.isChecked()) {
            tvtm13.setText(con.DAOValoresActuales(columna, "13"));
        }
        else {
            tvtm13.setText("");
        }
        if(sw14.isChecked()) {
            tvtm14.setText(con.DAOValoresActuales(columna, "14"));
        }
        else {
            tvtm14.setText("");
        }
        if(sw15.isChecked()) {
            tvtm15.setText(con.DAOValoresActuales(columna, "15"));
        }
        else {
            tvtm15.setText("");
        }
        if(sw16.isChecked()) {
            tvtm16.setText(con.DAOValoresActuales(columna, "16"));
        }
        else {
            tvtm16.setText("");
        }
        if(sw17.isChecked()) {
            tvtm17.setText(con.DAOValoresActuales(columna, "17"));
        }
        else {
            tvtm17.setText("");
        }
        if(sw18.isChecked()) {
            tvtm18.setText(con.DAOValoresActuales(columna, "18"));
        }
        else {
            tvtm18.setText("");
        }


    }

    public Boolean guardarValores(String numeroCampo)
    {

        return con.DAOGuardarValores("valor" + numeroCampo, 1, valNuevo1.getText().toString(), "valBool" + numeroCampo, in1) &&
                con.DAOGuardarValores("valor" + numeroCampo, 2, valNuevo2.getText().toString(), "valBool" + numeroCampo, in2) &&
                con.DAOGuardarValores("valor" + numeroCampo, 3, valNuevo3.getText().toString(), "valBool" + numeroCampo, in3) &&
                con.DAOGuardarValores("valor" + numeroCampo, 4, valNuevo4.getText().toString(), "valBool" + numeroCampo, in4) &&
                con.DAOGuardarValores("valor" + numeroCampo, 5, valNuevo5.getText().toString(), "valBool" + numeroCampo, in5) &&
                con.DAOGuardarValores("valor" + numeroCampo, 6, valNuevo6.getText().toString(), "valBool" + numeroCampo, in6) &&
                con.DAOGuardarValores("valor" + numeroCampo, 7, valNuevo7.getText().toString(), "valBool" + numeroCampo, in7) &&
                con.DAOGuardarValores("valor" + numeroCampo, 8, valNuevo8.getText().toString(), "valBool" + numeroCampo, in8) &&
                con.DAOGuardarValores("valor" + numeroCampo, 9, valNuevo9.getText().toString(), "valBool" + numeroCampo, in9) &&
                con.DAOGuardarValores("valor" + numeroCampo, 10, valNuevo10.getText().toString(), "valBool" + numeroCampo, in10) &&
                con.DAOGuardarValores("valor" + numeroCampo, 11, valNuevo11.getText().toString(), "valBool" + numeroCampo, in11) &&
                con.DAOGuardarValores("valor" + numeroCampo, 12, valNuevo12.getText().toString(), "valBool" + numeroCampo, in12) &&
                con.DAOGuardarValores("valor" + numeroCampo, 13, valNuevo13.getText().toString(), "valBool" + numeroCampo, in13) &&
                con.DAOGuardarValores("valor" + numeroCampo, 14, valNuevo14.getText().toString(), "valBool" + numeroCampo, in14) &&
                con.DAOGuardarValores("valor" + numeroCampo, 15, valNuevo15.getText().toString(), "valBool" + numeroCampo, in15) &&
                con.DAOGuardarValores("valor" + numeroCampo, 16, valNuevo16.getText().toString(), "valBool" + numeroCampo, in16) &&
                con.DAOGuardarValores("valor" + numeroCampo, 17, valNuevo17.getText().toString(), "valBool" + numeroCampo, in17) &&
                con.DAOGuardarValores("valor" + numeroCampo, 18, valNuevo18.getText().toString(), "valBool" + numeroCampo, in18);
    }

    public void setSwitches(String columna)
    {

        sw1.setChecked(con.DAOSwitchBool(columna, "1"));
        sw2.setChecked(con.DAOSwitchBool(columna, "2"));
        sw3.setChecked(con.DAOSwitchBool(columna, "3"));
        sw4.setChecked(con.DAOSwitchBool(columna, "4"));
        sw5.setChecked(con.DAOSwitchBool(columna, "5"));
        sw6.setChecked(con.DAOSwitchBool(columna, "6"));
        sw7.setChecked(con.DAOSwitchBool(columna, "7"));
        sw8.setChecked(con.DAOSwitchBool(columna, "8"));
        sw9.setChecked(con.DAOSwitchBool(columna, "9"));
        sw10.setChecked(con.DAOSwitchBool(columna, "10"));
        sw11.setChecked(con.DAOSwitchBool(columna, "11"));
        sw12.setChecked(con.DAOSwitchBool(columna, "12"));
        sw13.setChecked(con.DAOSwitchBool(columna, "13"));
        sw14.setChecked(con.DAOSwitchBool(columna, "14"));
        sw15.setChecked(con.DAOSwitchBool(columna, "15"));
        sw16.setChecked(con.DAOSwitchBool(columna, "16"));
        sw17.setChecked(con.DAOSwitchBool(columna, "17"));
        sw18.setChecked(con.DAOSwitchBool(columna, "18"));
        valNuevo1.setEnabled(con.DAOSwitchBool(columna, "1"));
        valNuevo2.setEnabled(con.DAOSwitchBool(columna,"2"));
        valNuevo3.setEnabled(con.DAOSwitchBool(columna,"3"));
        valNuevo4.setEnabled(con.DAOSwitchBool(columna,"4"));
        valNuevo5.setEnabled(con.DAOSwitchBool(columna,"5"));
        valNuevo6.setEnabled(con.DAOSwitchBool(columna,"6"));
        valNuevo7.setEnabled(con.DAOSwitchBool(columna,"7"));
        valNuevo8.setEnabled(con.DAOSwitchBool(columna,"8"));
        valNuevo9.setEnabled(con.DAOSwitchBool(columna,"9"));
        valNuevo10.setEnabled(con.DAOSwitchBool(columna,"10"));
        valNuevo11.setEnabled(con.DAOSwitchBool(columna,"11"));
        valNuevo12.setEnabled(con.DAOSwitchBool(columna,"12"));
        valNuevo13.setEnabled(con.DAOSwitchBool(columna,"13"));
        valNuevo14.setEnabled(con.DAOSwitchBool(columna,"14"));
        valNuevo15.setEnabled(con.DAOSwitchBool(columna,"15"));
        valNuevo16.setEnabled(con.DAOSwitchBool(columna,"16"));
        valNuevo17.setEnabled(con.DAOSwitchBool(columna,"17"));
        valNuevo18.setEnabled(con.DAOSwitchBool(columna,"18"));


    }
    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Texturizador_Edit.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
