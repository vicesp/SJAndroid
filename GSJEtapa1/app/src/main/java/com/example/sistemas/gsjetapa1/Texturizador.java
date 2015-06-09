package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Texturizador extends ActionBarActivity {

    private String [] familia;
    private Spinner spFamiliaTextu;
    private String texturizador_select, datos_cambiados;
    private int in1=0,in2=0,in3=0,in4=0,in5=0,in6=0,in7=0,in8=0,in9=0,in10=0,in11=0,in12=0,in13=0,in14=0,in15=0,in16=0,in17=0,in18=0,numero_conse=0;
    private static TextView Fecha,Lote;
    private TextView tvtm1,tvtm2,tvtm3,tvtm4,tvtm5,tvtm6,tvtm7,tvtm8,tvtm9,tvtm11,tvtm12,tvtm13,tvtm14,tvtm15,tvtm16,tvtm17,tvtm18;
    private TextView tvtx1,tvtx2,tvtx3,tvtx4,tvtx5,tvtx6,tvtx7,tvtx8,tvtx9,tvtx11,tvtx12,tvtx13,tvtx14,tvtx15,tvtx16,tvtx17,tvtx18;
    private EditText lote1,lote2,lote3,lote4,lote5,lote6,lote7,lote8,lote9,lote11,lote12,lote13,lote14,lote15,lote16,lote17,lote18,
            kilos_tot, etObservaciones;
    private Button Regresar;
    private ImageButton Guardar;
    private RelativeLayout layoutObservaciones;
    private Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9,sw11,sw12,sw13,sw14,sw15,sw16,sw17,sw18;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private ColorStateList color;
    private static ArrayAdapter adapter;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texturizador);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var= new Variables();

        datos_cambiados="";


        //******************    Relative Layout    ****************//
        layoutObservaciones = (RelativeLayout)findViewById(R.id.relativeLayout4);

        //******************    Text View    ****************//

        Fecha=(TextView)findViewById(R.id.tvtFecha);
        Fecha.setText(FechaH.Hoy());

        Lote=(TextView)findViewById(R.id.tvtLote);
        //fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
        numero_conse=con.DAOTextu_consecutivo(FechaH.Hoy());
        numero_conse+=1;

        tvtm1=(TextView)findViewById(R.id.tvtm1);
        tvtm2=(TextView)findViewById(R.id.tvtm2);
        tvtm3=(TextView)findViewById(R.id.tvtm3);
        tvtm4=(TextView)findViewById(R.id.tvtm4);
        tvtm5=(TextView)findViewById(R.id.tvtm5);
        tvtm6=(TextView)findViewById(R.id.tvtm6);
        tvtm7=(TextView)findViewById(R.id.tvtm7);
        tvtm8=(TextView)findViewById(R.id.tvtm8);
        tvtm9=(TextView)findViewById(R.id.tvtm9);

        tvtm11=(TextView)findViewById(R.id.tvtm11);
        tvtm12=(TextView)findViewById(R.id.tvtm12);
        tvtm13=(TextView)findViewById(R.id.tvtm13);
        tvtm14=(TextView)findViewById(R.id.tvtm14);
        tvtm15=(TextView)findViewById(R.id.tvtm15);
        tvtm16=(TextView)findViewById(R.id.tvtm16);
        tvtm17=(TextView)findViewById(R.id.tvtm17);
        tvtm18=(TextView)findViewById(R.id.tvtm18);

        tvtx1=(TextView)findViewById(R.id.textView98);
        tvtx2=(TextView)findViewById(R.id.textView99);
        tvtx3=(TextView)findViewById(R.id.textView102);
        tvtx4=(TextView)findViewById(R.id.textView104);
        tvtx5=(TextView)findViewById(R.id.textView107);
        tvtx6=(TextView)findViewById(R.id.textView108);
        tvtx7=(TextView)findViewById(R.id.textView109);
        tvtx8=(TextView)findViewById(R.id.textView110);
        tvtx9=(TextView)findViewById(R.id.textView111);

        tvtx11=(TextView)findViewById(R.id.textView113);
        tvtx12=(TextView)findViewById(R.id.textView114);
        tvtx13=(TextView)findViewById(R.id.textView115);
        tvtx14=(TextView)findViewById(R.id.textView116);
        tvtx15=(TextView)findViewById(R.id.textView117);
        tvtx16=(TextView)findViewById(R.id.textView118);
        tvtx17=(TextView)findViewById(R.id.textView119);
        tvtx18=(TextView)findViewById(R.id.textView120);

        //******************    Edit Text    ****************//
        lote1=(EditText)findViewById(R.id.ettlot1);
        lote2=(EditText)findViewById(R.id.ettlot2);
        lote3=(EditText)findViewById(R.id.ettlot3);
        lote4=(EditText)findViewById(R.id.ettlot4);
        lote5=(EditText)findViewById(R.id.ettlot5);
        lote6=(EditText)findViewById(R.id.ettlot6);
        lote7=(EditText)findViewById(R.id.ettlot7);
        lote8=(EditText)findViewById(R.id.ettlot8);
        lote9=(EditText)findViewById(R.id.ettlot9);

        lote11=(EditText)findViewById(R.id.ettlot11);
        lote12=(EditText)findViewById(R.id.ettlot12);
        lote13=(EditText)findViewById(R.id.ettlot13);
        lote14=(EditText)findViewById(R.id.ettlot14);
        lote15=(EditText)findViewById(R.id.ettlot15);
        lote16=(EditText)findViewById(R.id.ettlot16);
        lote17=(EditText)findViewById(R.id.ettlot17);
        lote18=(EditText)findViewById(R.id.ettlot18);
        kilos_tot=(EditText)findViewById(R.id.ettKilosTot);

        etObservaciones=(EditText)findViewById(R.id.eteObservaciones);



        //******************    Switch    ****************//
        sw1=(Switch)findViewById(R.id.swt1);
        lote1.setEnabled(false);
        sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote1.setEnabled(true);
                    in1=1;
                }else{
                    lote1.setEnabled(false);
                    in1=0;
                }

            }
        });

        sw2=(Switch)findViewById(R.id.swt2);
        lote2.setEnabled(false);
        sw2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote2.setEnabled(true);
                    in2=1;
                }else{
                    lote2.setEnabled(false);
                    in2=0;
                }

            }
        });

        sw3=(Switch)findViewById(R.id.swt3);
        lote3.setEnabled(false);
        sw3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote3.setEnabled(true);
                    in3=1;
                }else{
                    lote3.setEnabled(false);
                    in3=0;
                }

            }
        });

        sw4=(Switch)findViewById(R.id.swt4);
        lote4.setEnabled(false);
        sw4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote4.setEnabled(true);
                    in4=1;
                }else{
                    lote4.setEnabled(false);
                    in4=0;
                }

            }
        });

        sw5=(Switch)findViewById(R.id.swt5);
        lote5.setEnabled(false);
        sw5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote5.setEnabled(true);
                    in5=1;
                }else{
                    lote5.setEnabled(false);
                    in5=0;
                }

            }
        });

        sw6=(Switch)findViewById(R.id.swt6);
        lote6.setEnabled(false);
        sw6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote6.setEnabled(true);
                    in6=1;
                }else{
                    lote6.setEnabled(false);
                    in6=0;
                }

            }
        });

        sw7=(Switch)findViewById(R.id.swt7);
        lote7.setEnabled(false);
        sw7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote7.setEnabled(true);
                    in7=1;
                }else{
                    lote7.setEnabled(false);
                    in7=0;
                }

            }
        });

        sw8=(Switch)findViewById(R.id.swt8);
        lote8.setEnabled(false);
        sw8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote8.setEnabled(true);
                    in8=1;
                }else{
                    lote8.setEnabled(false);
                    in8=0;
                }

            }
        });

        sw9=(Switch)findViewById(R.id.swt9);
        lote9.setEnabled(false);
        sw9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote9.setEnabled(true);
                    in9=1;
                }else{
                    lote9.setEnabled(false);
                    in9=0;
                }

            }
        });



        sw11=(Switch)findViewById(R.id.swt11);
        lote11.setEnabled(false);
        sw11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote11.setEnabled(true);
                    in11=1;
                }else{
                    lote11.setEnabled(false);
                    in11=0;
                }

            }
        });

        sw12=(Switch)findViewById(R.id.swt12);
        lote12.setEnabled(false);
        sw12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote12.setEnabled(true);
                    in12=1;
                }else{
                    lote12.setEnabled(false);
                    in12=0;
                }

            }
        });

        sw13=(Switch)findViewById(R.id.swt13);
        lote13.setEnabled(false);
        sw13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote13.setEnabled(true);
                    in13=1;
                }else{
                    lote13.setEnabled(false);
                    in13=0;
                }

            }
        });

        sw14=(Switch)findViewById(R.id.swt14);
        lote14.setEnabled(false);
        sw14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote14.setEnabled(true);
                    in14=1;
                }else{
                    lote14.setEnabled(false);
                    in14=0;
                }

            }
        });

        sw15=(Switch)findViewById(R.id.swt15);
        lote15.setEnabled(false);
        sw15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote15.setEnabled(true);
                    in15=1;
                }else{
                    lote15.setEnabled(false);
                    in15=0;
                }

            }
        });

        sw16=(Switch)findViewById(R.id.swt16);
        lote16.setEnabled(false);
        sw16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote16.setEnabled(true);
                    in16=1;
                }else{
                    lote16.setEnabled(false);
                    in16=0;
                }

            }
        });

        sw17=(Switch)findViewById(R.id.swt17);
        lote17.setEnabled(false);
        sw17.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote17.setEnabled(true);
                    in17=1;
                }else{
                    lote17.setEnabled(false);
                    in17=0;
                }

            }
        });

        sw18=(Switch)findViewById(R.id.swt18);
        lote18.setEnabled(false);
        sw18.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote18.setEnabled(true);
                    in18=1;
                }else{
                    lote18.setEnabled(false);
                    in18=0;
                }

            }
        });

        //******************    Spinners    ****************//



        spFamiliaTextu=(Spinner)findViewById(R.id.spTexturizador);
        llena_Texturizador(con.DAODescripcionTexturizador());


        spFamiliaTextu.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                texturizador_select = familia[position];

                if (position == 0) {
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool1");
                    cambia_switch("valBool1");
                    setSwitches("valBool1");
                    actualizarValores("valor1");

                }

                else if (position == 1) {
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool2");
                    cambia_switch("valBool2");
                    setSwitches("valBool2");
                    actualizarValores("valor2");

                }

                else if (position == 2) {
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool3");
                    cambia_switch("valBool3");
                    setSwitches("valBool3");
                    actualizarValores("valor3");

                }

                else if (position == 3) {
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool4");
                    setSwitches("valBool4");
                    cambia_switch("valBool4");
                    actualizarValores("valor4");
                }
                else if (position == 4) {
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool5");
                    setSwitches("valBool5");
                    cambia_switch("valBool5");
                    actualizarValores("valor5");


                }
                else if (position == 5) {  //Personalizado
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool6");
                    setSwitches("valBool6");
                    cambia_switch("valBool6");
                    actualizarValores("valor6");


                }
                else if (position == 6) {  //Personalizado
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool7");
                    setSwitches("valBool7");
                    cambia_switch("valBool7");
                    actualizarValores("valor7");


                }
                else if (position == 7) {  //Personalizado
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool8");
                    setSwitches("valBool8");
                    cambia_switch("valBool8");
                    actualizarValores("valor8");


                }
                else if (position == 8) {  //Personalizado
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool9");
                    setSwitches("valBool9");
                    cambia_switch("valBool9");
                    actualizarValores("valor9");


                }
                else if (position == 9) {  //Personalizado
                    switch_false();
                    lote_texto_vacio();
                    cambia_color_textos("valBool10");
                    setSwitches("valBool10");
                    cambia_switch("valBool10");
                    actualizarValores("valor10");


                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        //******************    Boton    ****************//
        Regresar=(Button)findViewById(R.id.btnRegPendiLista);
        Regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(Texturizador.this, Texturizador_Realizados.class));
            }

        });


        Guardar=(ImageButton)findViewById(R.id.btntSave);
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                boolean exitoso = false;
                boolean exitosoActualizado = false;

                if (var.isFromSearch()) {

                    generarDatosCambiados();
                    Log.i("datos:", datos_cambiados);
                    if (datos_cambiados=="")
                    {
                        datos_cambiados="no se hicieron cambios";
                    }
                    exitosoActualizado = con.DAOActualizarTexturizador(Lote.getText().toString(), Fecha.getText().toString(), texturizador_select, tvtm1.getText().toString(), lote1.getText().toString(),
                            tvtm2.getText().toString(), lote2.getText().toString(), tvtm3.getText().toString(), lote3.getText().toString(), tvtm4.getText().toString(), lote4.getText().toString(), tvtm5.getText().toString(), lote5.getText().toString(), tvtm6.getText().toString(), lote6.getText().toString(),
                            tvtm7.getText().toString(), lote7.getText().toString(), tvtm8.getText().toString(), lote8.getText().toString(), tvtm9.getText().toString(), lote9.getText().toString(), tvtm11.getText().toString(), lote11.getText().toString(),
                            tvtm12.getText().toString(), lote12.getText().toString(), tvtm13.getText().toString(), lote13.getText().toString(), tvtm14.getText().toString(), lote14.getText().toString(), tvtm15.getText().toString(), lote15.getText().toString(), tvtm16.getText().toString(), lote16.getText().toString(),
                            tvtm17.getText().toString(), lote17.getText().toString(), tvtm18.getText().toString(), lote18.getText().toString(), kilos_tot.getText().toString(), numero_conse);

                    con.DAOConsultaBitacora(Variables.getNombre_usuario(), "texturizador", datos_cambiados, etObservaciones.getText().toString(), Fecha.getText().toString());
                    datos_cambiados="";

                }

                else {
                    exitoso = con.DAOTexturizador(Lote.getText().toString(), Fecha.getText().toString(), texturizador_select, tvtm1.getText().toString(), lote1.getText().toString(),
                            tvtm2.getText().toString(), lote2.getText().toString(), tvtm3.getText().toString(), lote3.getText().toString(), tvtm4.getText().toString(), lote4.getText().toString(), tvtm5.getText().toString(), lote5.getText().toString(), tvtm6.getText().toString(), lote6.getText().toString(),
                            tvtm7.getText().toString(), lote7.getText().toString(), tvtm8.getText().toString(), lote8.getText().toString(), tvtm9.getText().toString(), lote9.getText().toString(), tvtm11.getText().toString(), lote11.getText().toString(),
                            tvtm12.getText().toString(), lote12.getText().toString(), tvtm13.getText().toString(), lote13.getText().toString(), tvtm14.getText().toString(), lote14.getText().toString(), tvtm15.getText().toString(), lote15.getText().toString(), tvtm16.getText().toString(), lote16.getText().toString(),
                            tvtm17.getText().toString(), lote17.getText().toString(), tvtm18.getText().toString(), lote18.getText().toString(), kilos_tot.getText().toString(), numero_conse);

                }
                if (exitoso) {
                    Alerta(getResources().getString(R.string.Alerta_Guardado));

                    //String fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
                    numero_conse = con.DAOTextu_consecutivo(FechaH.Hoy());
                    numero_conse += 1;
                    Lote.setText(numero_conse + DiaJ.Dame_dia_J_y_anio());
                }
                else if (exitosoActualizado) {
                    Alerta(getResources().getString(R.string.Alerta_Actualizado));

                    //String fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
                }
                else if(!exitosoActualizado)
                {
                    Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                }
                else {
                    Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                }

            }
        });

        if(var.isFromSearch())
        {
            llenarValoresBusqueda(var.getLoteTexturizador());
            layoutObservaciones.setVisibility(View.VISIBLE);
        }
        else {
            Lote.setText(numero_conse + DiaJ.Dame_dia_J_y_anio());
        }

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

    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Texturizador.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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

    public void cambia_switch(String columna){
        sw1.setEnabled(con.DAOSwitchBool(columna, "1"));
        sw2.setEnabled(con.DAOSwitchBool(columna, "2"));
        sw3.setEnabled(con.DAOSwitchBool(columna, "3"));
        sw4.setEnabled(con.DAOSwitchBool(columna, "4"));
        sw5.setEnabled(con.DAOSwitchBool(columna, "5"));
        sw6.setEnabled(con.DAOSwitchBool(columna, "6"));
        sw7.setEnabled(con.DAOSwitchBool(columna, "7"));
        sw8.setEnabled(con.DAOSwitchBool(columna, "8"));
        sw9.setEnabled(con.DAOSwitchBool(columna, "9"));

        sw11.setEnabled(con.DAOSwitchBool(columna, "11"));
        sw12.setEnabled(con.DAOSwitchBool(columna, "12"));
        sw13.setEnabled(con.DAOSwitchBool(columna, "13"));
        sw14.setEnabled(con.DAOSwitchBool(columna, "14"));
        sw15.setEnabled(con.DAOSwitchBool(columna, "15"));
        sw16.setEnabled(con.DAOSwitchBool(columna, "16"));
        sw17.setEnabled(con.DAOSwitchBool(columna, "17"));
        sw18.setEnabled(con.DAOSwitchBool(columna, "18"));
    }
    public void cambia_texto_cantidad(String tv1,String tv2,String tv3,String tv4,String tv5,String tv6,String tv7,String tv8,
                                      String tv9,String tv11,String tv12,String tv13,String tv14,String tv15,String tv16,String tv17,String tv18){
        tvtm1.setText(tv1);
        tvtm2.setText(tv2);
        tvtm3.setText(tv3);
        tvtm4.setText(tv4);
        tvtm5.setText(tv5);
        tvtm6.setText(tv6);
        tvtm7.setText(tv7);
        tvtm8.setText(tv8);
        tvtm9.setText(tv9);

        tvtm11.setText(tv11);
        tvtm12.setText(tv12);
        tvtm13.setText(tv13);
        tvtm14.setText(tv14);
        tvtm15.setText(tv15);
        tvtm16.setText(tv16);
        tvtm17.setText(tv17);
        tvtm18.setText(tv18);
    }
    public void switch_false(){
        sw1.setChecked(false);
        sw2.setChecked(false);
        sw3.setChecked(false);
        sw4.setChecked(false);
        sw5.setChecked(false);
        sw6.setChecked(false);
        sw7.setChecked(false);
        sw8.setChecked(false);
        sw9.setChecked(false);

        sw11.setChecked(false);
        sw12.setChecked(false);
        sw13.setChecked(false);
        sw14.setChecked(false);
        sw15.setChecked(false);
        sw16.setChecked(false);
        sw17.setChecked(false);
        sw18.setChecked(false);
    }
    public void lote_texto_vacio(){
        if (!var.isFromSearch()) {
            lote1.setText("");
            lote2.setText("");
            lote3.setText("");
            lote4.setText("");
            lote5.setText("");
            lote6.setText("");
            lote7.setText("");
            lote8.setText("");
            lote9.setText("");

            lote11.setText("");
            lote12.setText("");
            lote13.setText("");
            lote14.setText("");
            lote15.setText("");
            lote16.setText("");
            lote17.setText("");
            lote18.setText("");
            kilos_tot.setText("");
            etObservaciones.setText("");
        }
    }
    public void actualizarValores(String columna)
    {

        if(sw1.isEnabled()) {
            tvtm1.setText(con.DAOValoresActuales(columna, "1"));
        }
        else {
            tvtm1.setText("");
        }
        if(sw2.isEnabled()) {
            tvtm2.setText(con.DAOValoresActuales(columna, "2"));
        }
        else {
            tvtm2.setText("");
        }
        if(sw3.isEnabled()) {
            tvtm3.setText(con.DAOValoresActuales(columna, "3"));
        }
        else {
            tvtm3.setText("");
        }
        if(sw4.isEnabled()) {
            tvtm4.setText(con.DAOValoresActuales(columna, "4"));
        }
        else {
            tvtm4.setText("");
        }
        if(sw5.isEnabled()) {
            tvtm5.setText(con.DAOValoresActuales(columna, "5"));
        }
        else {
            tvtm5.setText("");
        }
        if(sw6.isEnabled()) {
            tvtm6.setText(con.DAOValoresActuales(columna, "6"));
        }
        else {
            tvtm6.setText("");
        }
        if(sw7.isEnabled()) {
            tvtm7.setText(con.DAOValoresActuales(columna, "7"));
        }
        else {
            tvtm7.setText("");
        }
        if(sw8.isEnabled()) {
            tvtm8.setText(con.DAOValoresActuales(columna, "8"));
        }
        else {
            tvtm8.setText("");
        }
        if(sw9.isEnabled()) {
            tvtm9.setText(con.DAOValoresActuales(columna, "9"));
        }
        else {
            tvtm9.setText("");
        }

        if(sw11.isEnabled()) {
            tvtm11.setText(con.DAOValoresActuales(columna, "11"));
        }
        else {
            tvtm11.setText("");
        }
        if(sw12.isEnabled()) {
            tvtm12.setText(con.DAOValoresActuales(columna, "12"));
        }
        else {
            tvtm12.setText("");
        }
        if(sw13.isEnabled()) {
            tvtm13.setText(con.DAOValoresActuales(columna, "13"));
        }
        else {
            tvtm13.setText("");
        }
        if(sw14.isEnabled()) {
            tvtm14.setText(con.DAOValoresActuales(columna, "14"));
        }
        else {
            tvtm14.setText("");
        }
        if(sw15.isEnabled()) {
            tvtm15.setText(con.DAOValoresActuales(columna, "15"));
        }
        else {
            tvtm15.setText("");
        }
        if(sw16.isEnabled()) {
            tvtm16.setText(con.DAOValoresActuales(columna, "16"));
        }
        else {
            tvtm16.setText("");
        }
        if(sw17.isEnabled()) {
            tvtm17.setText(con.DAOValoresActuales(columna, "17"));
        }
        else {
            tvtm17.setText("");
        }
        if(sw18.isEnabled()) {
            tvtm18.setText(con.DAOValoresActuales(columna, "18"));
        }
        else {
            tvtm18.setText("");
        }
    }
    public void setSwitches(String columna)
    {
        sw1.setChecked(con.DAOSwitchBool(columna,"1"));
        sw2.setChecked(con.DAOSwitchBool(columna,"2"));
        sw3.setChecked(con.DAOSwitchBool(columna,"3"));
        sw4.setChecked(con.DAOSwitchBool(columna,"4"));
        sw5.setChecked(con.DAOSwitchBool(columna,"5"));
        sw6.setChecked(con.DAOSwitchBool(columna,"6"));
        sw7.setChecked(con.DAOSwitchBool(columna,"7"));
        sw8.setChecked(con.DAOSwitchBool(columna,"8"));
        sw9.setChecked(con.DAOSwitchBool(columna,"9"));

        sw11.setChecked(con.DAOSwitchBool(columna,"11"));
        sw12.setChecked(con.DAOSwitchBool(columna,"12"));
        sw13.setChecked(con.DAOSwitchBool(columna,"13"));
        sw14.setChecked(con.DAOSwitchBool(columna,"14"));
        sw15.setChecked(con.DAOSwitchBool(columna,"15"));
        sw16.setChecked(con.DAOSwitchBool(columna,"16"));
        sw17.setChecked(con.DAOSwitchBool(columna,"17"));
        sw18.setChecked(con.DAOSwitchBool(columna,"18"));
    }
    public void llenarValoresBusqueda(String lote)
    {
        cursor = con.DAOLLenarTexturizador(lote);
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha")));

        lote1.setText(cursor.getString(cursor.getColumnIndex("lote_mp002")));
        lote2.setText(cursor.getString(cursor.getColumnIndex("lote_mp003")));
        lote3.setText(cursor.getString(cursor.getColumnIndex("lote_mp004")));
        lote4.setText(cursor.getString(cursor.getColumnIndex("lote_mp005")));
        lote5.setText(cursor.getString(cursor.getColumnIndex("lote_mp006")));
        lote6.setText(cursor.getString(cursor.getColumnIndex("lote_mp007")));
        lote7.setText(cursor.getString(cursor.getColumnIndex("lote_mp008")));
        lote8.setText(cursor.getString(cursor.getColumnIndex("lote_mp009")));
        lote9.setText(cursor.getString(cursor.getColumnIndex("lote_mp010")));

        lote11.setText(cursor.getString(cursor.getColumnIndex("lote_mp025")));
        lote12.setText(cursor.getString(cursor.getColumnIndex("lote_mp026")));
        lote13.setText(cursor.getString(cursor.getColumnIndex("lote_mp027")));
        lote14.setText(cursor.getString(cursor.getColumnIndex("lote_mp028")));
        lote15.setText(cursor.getString(cursor.getColumnIndex("lote_mp031")));
        lote16.setText(cursor.getString(cursor.getColumnIndex("lote_mp012")));
        lote17.setText(cursor.getString(cursor.getColumnIndex("lote_mp013")));
        lote18.setText(cursor.getString(cursor.getColumnIndex("lote_mp014")));
        kilos_tot.setText(cursor.getString(cursor.getColumnIndex("kilos_totales")));

        Lote.setText(lote);

        Guardar.setImageResource(R.drawable.guarda);


    }

    public void generarDatosCambiados()
    {

        if (!(cursor.getString(cursor.getColumnIndex("lote_mp002")).equals(lote1.getText().toString()))) {
            datos_cambiados = datos_cambiados + "MP002 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp002")) + ", Valor Nuevo: " + lote1.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp003")).equals(lote2.getText().toString()))) {
            datos_cambiados = datos_cambiados + "MP003 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp003")) + ", Valor Nuevo: " + lote2.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp004")).equals(lote3.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP004 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp004")) + ", Valor Nuevo: " + lote3.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp005")).equals(lote4.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP005 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp005")) + ", Valor Nuevo: " + lote4.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp006")).equals(lote5.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP006 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp006")) + ", Valor Nuevo: " + lote5.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp007")).equals(lote6.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP007 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp007")) + ", Valor Nuevo: " + lote6.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp008")).equals(lote7.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP008 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp008")) + ", Valor Nuevo: " + lote7.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp009")).equals(lote8.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP009 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp009")) + ", Valor Nuevo: " + lote8.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp010")).equals(lote9.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP010 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp010")) + ", Valor Nuevo: " + lote9.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp025")).equals(lote11.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP025 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp025")) + ", Valor Nuevo: " + lote11.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp026")).equals(lote12.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP026 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp026")) + ", Valor Nuevo: " + lote12.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp027")).equals(lote13.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP027 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp027")) + ", Valor Nuevo: " + lote13.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp028")).equals(lote14.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP028 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp028")) + ", Valor Nuevo: " + lote14.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp031")).equals(lote15.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP031 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp031")) + ", Valor Nuevo: " + lote15.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp012")).equals(lote16.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP012 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp012")) + ", Valor Nuevo: " + lote16.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp013")).equals(lote17.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP013 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp013")) + ", Valor Nuevo: " + lote17.getText().toString() + "; ";
        }
        if (!(cursor.getString(cursor.getColumnIndex("lote_mp014")).equals(lote18.getText().toString()))){
            datos_cambiados = datos_cambiados + "MP014 Valor Previo: " + cursor.getString(cursor.getColumnIndex("lote_mp014")) + ", Valor Nuevo: " + lote18.getText().toString() + "; ";
        }
    }

    public class GuardaTexturizador extends AsyncTask <String, Void, Boolean>
    {
        private final ProgressDialog dialog = new ProgressDialog(Texturizador.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Enviando Datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args)
        {
            final String NAMESPACE = "http://serv_gsj.net/";
            final String URL = "http://" + Variables.getIp_servidor() + "ServidorWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertatexturizador";
            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
            final int time = 2000, time2 = 190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("lote", Lote.getText().toString());
            request.addProperty("fecha", Fecha.getText().toString());
            request.addProperty("texturizador", texturizador_select);
            request.addProperty("mp002", tvtx1.getText().toString());
            request.addProperty("lote_mp002",lote1.getText().toString());
            request.addProperty("mp003", tvtx2.getText().toString());
            request.addProperty("lote_mp003", lote2.getText().toString());
            request.addProperty("mp004", tvtx3.getText().toString());
            request.addProperty("lote_mp004", lote3.getText().toString());
            request.addProperty("mp005", tvtx4.getText().toString());
            request.addProperty("lote_mp005", lote4.getText().toString());
            request.addProperty("mp006", tvtx5.getText().toString());
            request.addProperty("lote_mp006", lote5.getText().toString());
            request.addProperty("mp007", tvtx6.getText().toString());
            request.addProperty("lote_mp007", lote6.getText().toString());
            request.addProperty("mp008", tvtx7.getText().toString());
            request.addProperty("lote_mp008", lote7.getText().toString());
            request.addProperty("mp009", tvtx8.getText().toString());
            request.addProperty("lote_mp009", lote8.getText().toString());
            request.addProperty("mp010", tvtx9.getText().toString());
            request.addProperty("lote_mp010", lote9.getText().toString());
            request.addProperty("mp025", tvtx11.getText().toString());
            request.addProperty("lote_mp025", lote11.getText().toString());
            request.addProperty("mp026", tvtx12.getText().toString());
            request.addProperty("lote_mp026", lote12.getText().toString());
            request.addProperty("mp027", tvtx13.getText().toString());
            request.addProperty("lote_mp027", lote13.getText().toString());
            request.addProperty("mp028", tvtx14.getText().toString());
            request.addProperty("lote_mp028", lote14.getText().toString());
            request.addProperty("mp031", tvtx15.getText().toString());
            request.addProperty("lote_mp031", lote15.getText().toString());
            request.addProperty("mp012", tvtx16.getText().toString());
            request.addProperty("lote_mp012", lote16.getText().toString());
            request.addProperty("mp013", tvtx17.getText().toString());
            request.addProperty("lote_mp013", lote17.getText().toString());
            request.addProperty("mp014", tvtx18.getText().toString());
            request.addProperty("lote_mp014", lote18.getText().toString());
            request.addProperty("kilos_totales", kilos_tot.getText().toString());
            request.addProperty("num_consecutivo", numero_conse);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL, time);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_XML = (SoapPrimitive)envelope.getResponse();
                String mensaje = resultado_XML.toString();

                if(mensaje.contentEquals("true")){
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception e)
            {
                Log.i("Error", "Error de sincronizacion:  " + e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (success)
            {
                Toast.makeText(Texturizador.this, "Sincronizacion Exitosa", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Texturizador.this, "Error de Sincronizacion", Toast.LENGTH_SHORT).show();
            }
        }

        public void PaginaMonitor()
        {
            WebView myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl("http://" + Variables.getIp_servidor() + "SignalRTest/simplechat.aspx?val=123");

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
    }
}