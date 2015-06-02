package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;


public class Texturizador extends ActionBarActivity {

    private String [] familia;
    private Spinner spFamiliaTextu;
    private String texturizador_select;
    private int in1=0,in2=0,in3=0,in4=0,in5=0,in6=0,in7=0,in8=0,in9=0,in10=0,in11=0,in12=0,in13=0,in14=0,in15=0,in16=0,in17=0,in18=0,numero_conse=0;
    private TextView Fecha,Lote;
    private TextView tvtm1,tvtm2,tvtm3,tvtm4,tvtm5,tvtm6,tvtm7,tvtm8,tvtm9,tvtm10,tvtm11,tvtm12,tvtm13,tvtm14,tvtm15,tvtm16,tvtm17,tvtm18;
    private TextView tvtx1,tvtx2,tvtx3,tvtx4,tvtx5,tvtx6,tvtx7,tvtx8,tvtx9,tvtx10,tvtx11,tvtx12,tvtx13,tvtx14,tvtx15,tvtx16,tvtx17,tvtx18;
    private EditText lote1,lote2,lote3,lote4,lote5,lote6,lote7,lote8,lote9,lote10,lote11,lote12,lote13,lote14,lote15,lote16,lote17,lote18,kilos_tot;
    private Button Guardar;
    private Switch sw1,sw2,sw3,sw4,sw5,sw6,sw7,sw8,sw9,sw10,sw11,sw12,sw13,sw14,sw15,sw16,sw17,sw18;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private ColorStateList color;
    private static ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.texturizador);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();



        //******************    Text View    ****************//

        Fecha=(TextView)findViewById(R.id.tvtFecha);
        Fecha.setText(FechaH.Hoy());

        Lote=(TextView)findViewById(R.id.tvtLote);
        //fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
        numero_conse=con.DAOTextu_consecutivo(FechaH.Hoy());
        numero_conse+=1;
        Lote.setText(numero_conse+DiaJ.Dame_dia_J_y_anio());


        tvtm1=(TextView)findViewById(R.id.tvtm1);
        tvtm2=(TextView)findViewById(R.id.tvtm2);
        tvtm3=(TextView)findViewById(R.id.tvtm3);
        tvtm4=(TextView)findViewById(R.id.tvtm4);
        tvtm5=(TextView)findViewById(R.id.tvtm5);
        tvtm6=(TextView)findViewById(R.id.tvtm6);
        tvtm7=(TextView)findViewById(R.id.tvtm7);
        tvtm8=(TextView)findViewById(R.id.tvtm8);
        tvtm9=(TextView)findViewById(R.id.tvtm9);
        tvtm10=(TextView)findViewById(R.id.tvtm10);
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
        tvtx10=(TextView)findViewById(R.id.textView112);
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
        lote10=(EditText)findViewById(R.id.ettlot10);
        lote11=(EditText)findViewById(R.id.ettlot11);
        lote12=(EditText)findViewById(R.id.ettlot12);
        lote13=(EditText)findViewById(R.id.ettlot13);
        lote14=(EditText)findViewById(R.id.ettlot14);
        lote15=(EditText)findViewById(R.id.ettlot15);
        lote16=(EditText)findViewById(R.id.ettlot16);
        lote17=(EditText)findViewById(R.id.ettlot17);
        lote18=(EditText)findViewById(R.id.ettlot18);
        kilos_tot=(EditText)findViewById(R.id.ettKilosTot);



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
        sw10=(Switch)findViewById(R.id.swt10);
        sw10=(Switch)findViewById(R.id.swt10);
        lote10.setEnabled(false);
        sw10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    lote10.setEnabled(true);
                    in10=1;
                }else{
                    lote10.setEnabled(false);
                    in10=0;
                }

            }
        });
        sw11=(Switch)findViewById(R.id.swt11);
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
                Log.i("posssss", "textu" + position);


                if (position == 0) {
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(false, true, true, true, true, false, true, false, false, true, false, true, false, false, false, false, false, false);

                    cambia_color_textos(getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)


                    );

                    actualizarValores("valor1");
                }

                else if (position == 1) {
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(false, true, true, true, true, false, true, true, true, true, true, false, false, false, false, false, false, false);

                    cambia_color_textos(getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    actualizarValores("valor2");
                }

                else if (position == 2) {
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(false, true, true, true, true, false, true, true, true, true, true, false, false, true, true, false, false, false);

                    cambia_color_textos(getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );

                    actualizarValores("valor3");
                }

                else if (position == 3) {
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(true, true, true, true, true, true, true, false, false, true, false, true, false, false, false, false, false, false);

                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );

                    actualizarValores("valor4");
                }
                else if (position == 4) {
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(false, true, true, true, true, false, true, true, true, true, false, true, false, false, false, false, false, false);

                    cambia_color_textos(getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    actualizarValores("valor5");
                }
                else if (position == 5) {  //Personalizado
                    switch_false();
                   // lote_texto_vacio();

                    cambia_switch(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true);

                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act)
                    );
                    actualizarValores("valor6");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        //******************    Boton    ****************//
        Guardar=(Button)findViewById(R.id.btntSave);
        Guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                boolean exitoso=con.DAOTexturizador(Lote.getText().toString(), Fecha.getText().toString(), texturizador_select,tvtm1.getText().toString(),lote1.getText().toString(),
                        tvtm2.getText().toString(),lote2.getText().toString(),tvtm3.getText().toString(),lote3.getText().toString(),tvtm4.getText().toString(),lote4.getText().toString(),tvtm5.getText().toString(),lote5.getText().toString(),tvtm6.getText().toString(),lote6.getText().toString(),
                        tvtm7.getText().toString(),lote7.getText().toString(),tvtm8.getText().toString(),lote8.getText().toString(),tvtm9.getText().toString(),lote9.getText().toString(),tvtm10.getText().toString(),lote10.getText().toString(),tvtm11.getText().toString(),lote11.getText().toString(),
                        tvtm12.getText().toString(),lote12.getText().toString(),tvtm13.getText().toString(),lote13.getText().toString(),tvtm14.getText().toString(),lote14.getText().toString(),tvtm15.getText().toString(),lote15.getText().toString(),tvtm16.getText().toString(),lote16.getText().toString(),
                        tvtm17.getText().toString(),lote17.getText().toString(),tvtm18.getText().toString(),lote18.getText().toString(),kilos_tot.getText().toString(),numero_conse);


                if(exitoso){
                    Alerta(getResources().getString(R.string.Alerta_Guardado));

                    //String fechamod=(FechaH.Hoy().substring(0,2)+FechaH.Hoy().substring(3,5)+FechaH.Hoy().substring(8,10));
                    numero_conse=con.DAOTextu_consecutivo(FechaH.Hoy());
                    numero_conse+=1;
                    Lote.setText(numero_conse+DiaJ.Dame_dia_J_y_anio());


                }
                else{
                    Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                }

            }
        });





    }

    public void llena_Texturizador(final ArrayList<consultas> genArray)
    {

        for(final consultas con: genArray)
        {

            adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, con.descripcion);

            familia= con.descripcion;

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

    public void cambia_color_textos(int tv1,int tv2,int tv3,int tv4,int tv5,int tv6,int tv7,int tv8,
                                    int tv9,int tv10,int tv11,int tv12,int tv13,int tv14,int tv15,int tv16,int tv17,int tv18){

        tvtx1.setTextColor(tv1);
        tvtx2.setTextColor(tv2);
        tvtx3.setTextColor(tv3);
        tvtx4.setTextColor(tv4);
        tvtx5.setTextColor(tv5);
        tvtx6.setTextColor(tv6);
        tvtx7.setTextColor(tv7);
        tvtx8.setTextColor(tv8);
        tvtx9.setTextColor(tv9);
        tvtx10.setTextColor(tv10);
        tvtx11.setTextColor(tv11);
        tvtx12.setTextColor(tv12);
        tvtx13.setTextColor(tv13);
        tvtx14.setTextColor(tv14);
        tvtx15.setTextColor(tv15);
        tvtx16.setTextColor(tv16);
        tvtx17.setTextColor(tv17);
        tvtx18.setTextColor(tv18);

    }

    public void cambia_switch(boolean swTex1,boolean swTex2,boolean swTex3,boolean swTex4,boolean swTex5,
                              boolean swTex6,boolean swTex7,boolean swTex8,boolean swTex9,boolean swTex10,
                              boolean swTex11,boolean swTex12,boolean swTex13,boolean swTex14,boolean swTex15,boolean swTex16,boolean swTex17,boolean swTex18){
        sw1.setEnabled(swTex1);
        if (!swTex1){lote1.setText("");}
        sw2.setEnabled(swTex2);
        if (!swTex2){lote2.setText("");}
        sw3.setEnabled(swTex3);
        if (!swTex3){lote3.setText("");}
        sw4.setEnabled(swTex4);
        if (!swTex4){lote4.setText("");}
        sw5.setEnabled(swTex5);
        if (!swTex5){lote5.setText("");}
        sw6.setEnabled(swTex6);
        if (!swTex6){lote6.setText("");}
        sw7.setEnabled(swTex7);
        if (!swTex7){lote7.setText("");}
        sw8.setEnabled(swTex8);
        if (!swTex8){lote8.setText("");}
        sw9.setEnabled(swTex9);
        if (!swTex9){lote9.setText("");}
        sw10.setEnabled(swTex10);
        if (!swTex10){lote10.setText("");}
        sw11.setEnabled(swTex11);
        if (!swTex11){lote11.setText("");}
        sw12.setEnabled(swTex12);
        if (!swTex12){lote12.setText("");}
        sw13.setEnabled(swTex13);
        if (!swTex13){lote13.setText("");}
        sw14.setEnabled(swTex14);
        if (!swTex14){lote14.setText("");}
        sw15.setEnabled(swTex15);
        if (!swTex15){lote15.setText("");}
        sw16.setEnabled(swTex16);
        if (!swTex16){lote16.setText("");}
        sw17.setEnabled(swTex17);
        if (!swTex17){lote17.setText("");}
        sw18.setEnabled(swTex18);
        if (!swTex18){lote18.setText("");}
    }

    public void cambia_texto_cantidad(String tv1,String tv2,String tv3,String tv4,String tv5,String tv6,String tv7,String tv8,
                                      String tv9,String tv10,String tv11,String tv12,String tv13,String tv14,String tv15,String tv16,String tv17,String tv18){

        tvtm1.setText(tv1);
        tvtm2.setText(tv2);
        tvtm3.setText(tv3);
        tvtm4.setText(tv4);
        tvtm5.setText(tv5);
        tvtm6.setText(tv6);
        tvtm7.setText(tv7);
        tvtm8.setText(tv8);
        tvtm9.setText(tv9);
        tvtm10.setText(tv10);
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
        sw10.setChecked(false);
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
        lote15.setText("");
        lote16.setText("");
        lote17.setText("");
        lote18.setText("");

    }

    public void actualizarValores(String columna)
    {


        tvtm1.setText(con.DAOValoresActuales(columna,"1"));
        tvtm2.setText(con.DAOValoresActuales(columna,"2"));
        tvtm3.setText(con.DAOValoresActuales(columna,"3"));
        tvtm4.setText(con.DAOValoresActuales(columna,"4"));
        tvtm5.setText(con.DAOValoresActuales(columna,"5"));
        tvtm6.setText(con.DAOValoresActuales(columna,"6"));
        tvtm7.setText(con.DAOValoresActuales(columna,"7"));
        tvtm8.setText(con.DAOValoresActuales(columna,"8"));
        tvtm9.setText(con.DAOValoresActuales(columna,"9"));
        tvtm10.setText(con.DAOValoresActuales(columna,"10"));
        tvtm11.setText(con.DAOValoresActuales(columna,"11"));
        tvtm12.setText(con.DAOValoresActuales(columna,"12"));
        tvtm13.setText(con.DAOValoresActuales(columna,"13"));
        tvtm14.setText(con.DAOValoresActuales(columna,"14"));
        tvtm15.setText(con.DAOValoresActuales(columna,"15"));
        tvtm16.setText(con.DAOValoresActuales(columna,"16"));
        tvtm17.setText(con.DAOValoresActuales(columna,"17"));
        tvtm18.setText(con.DAOValoresActuales(columna, "18"));

    }
}