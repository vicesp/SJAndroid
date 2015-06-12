package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.ResizeAnimation;
import DTO.Variables;
import au.com.bytecode.opencsv.CSVWriter;
import config.DataBaseHelper;


public class Fundido extends ActionBarActivity {

    private String [] familia_fundido,tipo_cuajada,tipo_Crema,tipo_Crema2,familia_reprocdso,texturizador,tipo_cuajada_1,tipo_cuajada_2,tipo_cuajada_3;
    private String familia_sel,tipo_crema_sel,tipo_crema_sel2,familia_repro_sel,texturizador_sel,tipo_cj_1_sel,tipo_cj_2_sel,tipo_cj_3_sel;
    private Spinner spFamiliaFun, spTipoCrema, spTipoCrema2, spFamiliaReproceso, spTexturizador,spTipoCuajada1,spTipoCuajada2,spTipoCuajada3;
    private TextView Fecha,Lote, Fundida, Linea,Peso_tot,tipo_cre,lote_tipocre,cantidadCrem;
    private TextView tvF1,lote1,tvF2,lote2,tvF3,lote3,tvF4,lote4,tvF5,lote5,tvF6,lote6,tvF7,lote7,tvF8,lote8,tvF9;
    private EditText cj01,lote_cj01,ph_cj01,cj011,lote_cj011,ph_cj011,ad1,lot1,ad2,lot2,ad3,lot3,ad4,lot4,tempe_final,cantidad_reproceso,lote_textu;
    private EditText cj01_1,lote_cj01_1,ph_cj01_1,cj01_2,lote_cj01_2,ph_cj01_2,lote_crema2,cantidad_crema2,cj01_3,lote_cj01_3,ph_cj01_3;
    private EditText sa01,lote_sa01,mp024,lote_mp024,mp078,lote_mp078,cj02,lote_cj02,agua,lote_crema,cantidad_crema,lote_famiRepro,kilos_FamiRepro;
    private Button GuardarF,Calcula_peso;
    private ImageButton AddCuajada;
    private double peso_texturizador=0,valor_total=0,cantidad_cj01=0,cantidad_cj01_1=0,cantidad_cj01_2=0,cantidad_cj01_3=0,canti_crema2=0,cantidad_cj011=0,cantidad_mp005=0,cantidad_mp015=0,cantidad_s0101=0,tot_s0101=0,tot_mp015=0,cantidad_mp007=0,cantidad_sa01=0,cantidad_mp024=0,cantidad_int_crema=0,cantidad_familia_repro=0;
   private Switch tinas;
    private int bandera=1;
    private RelativeLayout rlAddtina;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundido);


        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var =new Variables();


        Variables.setIp_servidor(con.DAOSelecConfigIP());

        rlAddtina=(RelativeLayout)findViewById(R.id.rlCuajadaAdd);

        tipo_cre=(TextView)findViewById(R.id.textView62);
        lote_tipocre=(TextView)findViewById(R.id.textView63);
        cantidadCrem=(TextView)findViewById(R.id.textView64);


        //******************    Inicio Edit Text    ****************//

        lote_crema2=(EditText)findViewById(R.id.etLoteCrem2);
        cantidad_crema2=(EditText)findViewById(R.id.etCantidCremaFun2);

        cj01=(EditText)findViewById(R.id.etCj01Fun);
        lote_cj01=(EditText)findViewById(R.id.etLo1Cj01Fun);
        ph_cj01=(EditText)findViewById(R.id.etPhCj01Fun);
        ph_cj01.addTextChangedListener(new CurrencyTextWatcher());

        cj01_1=(EditText)findViewById(R.id.etCj01Fun1);
        lote_cj01_1=(EditText)findViewById(R.id.etLo1Cj01Fun1);
        ph_cj01_1=(EditText)findViewById(R.id.etPhCj01Fun1);
        ph_cj01_1.addTextChangedListener(new CurrencyTextWatcher());

        cj01_2=(EditText)findViewById(R.id.etCj01Fun2);
        lote_cj01_2=(EditText)findViewById(R.id.etLo1Cj01Fun2);
        ph_cj01_2=(EditText)findViewById(R.id.etPhCj01Fun2);
        ph_cj01_2.addTextChangedListener(new CurrencyTextWatcher());

        cj01_3=(EditText)findViewById(R.id.etCj01Fun3);
        lote_cj01_3=(EditText)findViewById(R.id.etLo1Cj01Fun3);
        ph_cj01_3=(EditText)findViewById(R.id.etPhCj01Fun3);
        ph_cj01_3.addTextChangedListener(new CurrencyTextWatcher());

        lote_textu=(EditText)findViewById(R.id.etfLoteTextu);

        cj011=(EditText)findViewById(R.id.etCj011Fun);
        lote_cj011=(EditText)findViewById(R.id.etLo1Cj011Fun);
        ph_cj011=(EditText)findViewById(R.id.etPhCj011Fun);
        ph_cj011.addTextChangedListener(new CurrencyTextWatcher());

        ad1=(EditText)findViewById(R.id.etAd1);
        lot1=(EditText)findViewById(R.id.etLo1);

        ad2=(EditText)findViewById(R.id.etAd2);
        //ad2.addTextChangedListener(new CurrencyTextWatcher3decimales());

        lot2=(EditText)findViewById(R.id.etLo2);
        ad3=(EditText)findViewById(R.id.etAd3);
        //ad3.addTextChangedListener(new CurrencyTextWatcher3decimales());

        lot3=(EditText)findViewById(R.id.etLo3);
        ad4=(EditText)findViewById(R.id.etAd4);
        lot4=(EditText)findViewById(R.id.etLo4);

        tempe_final=(EditText)findViewById(R.id.etTempFinFun);
        tempe_final.addTextChangedListener(new CurrencyTextWatcher1decimal());

        sa01=(EditText)findViewById(R.id.etSA01Fun);
        lote_sa01=(EditText)findViewById(R.id.etLoteMP1);
        mp024=(EditText)findViewById(R.id.etMP024Fun);
        lote_mp024=(EditText)findViewById(R.id.etLoteMP2);
        mp078=(EditText)findViewById(R.id.etMP078Fun);
        lote_mp078=(EditText)findViewById(R.id.etLoteMP3);
        cj02=(EditText)findViewById(R.id.etCj02Fun);
        lote_cj02=(EditText)findViewById(R.id.etLoteMP4);
        agua=(EditText)findViewById(R.id.etAguaFun);
        lote_crema=(EditText)findViewById(R.id.etLoteCremaFun);
        cantidad_crema=(EditText)findViewById(R.id.etCantiCremaFun);
        lote_famiRepro=(EditText)findViewById(R.id.etLoteFamiRepFun);
        cantidad_reproceso=(EditText)findViewById(R.id.etCantiFamiRepFun);
        kilos_FamiRepro=(EditText)findViewById(R.id.etkilosPendiCuajada);

        //******************    Fin Edit Text    ****************//

        //******************    Text Viewes    ****************//
        Peso_tot=(TextView)findViewById(R.id.tvFPesoTotal);
        tvF1=(TextView)findViewById(R.id.textView33);
        tvF2=(TextView)findViewById(R.id.textView34);
        tvF3=(TextView)findViewById(R.id.textView37);
        tvF4=(TextView)findViewById(R.id.textView39);
        tvF5=(TextView)findViewById(R.id.textView51);
        tvF6=(TextView)findViewById(R.id.textView53);
        tvF7=(TextView)findViewById(R.id.textView9);
        tvF8=(TextView)findViewById(R.id.textView49);
        lote1=(TextView)findViewById(R.id.textView35);
        lote2=(TextView)findViewById(R.id.textView36);
        lote3=(TextView)findViewById(R.id.textView38);
        lote4=(TextView)findViewById(R.id.textView78);
        lote5=(TextView)findViewById(R.id.textView52);
        lote6=(TextView)findViewById(R.id.textView54);
        lote7=(TextView)findViewById(R.id.textView76);
        lote8=(TextView)findViewById(R.id.textView77);
        tvF9=(TextView)findViewById(R.id.textView75);


        Fecha=(TextView)findViewById(R.id.tvFechaFun);
        Fecha.setText(FechaH.Hoy());

        Linea=(TextView)findViewById(R.id.tvNumLinea);
        Linea.setText(Variables.getLinea_fundido()+"       ");

        Fundida=(TextView)findViewById(R.id.tvNumFundida);


        int resul=con.DAOF_num_fundida(Variables.getLinea_fundido(),Fecha.getText().toString());
        if (Variables.getLinea_fundido()==1 && resul==0){
            resul+=1;
        }
        else if(Variables.getLinea_fundido()==1 && resul!=0){
            resul+=2;
        }
        else if(Variables.getLinea_fundido()==2){
            resul+=2;
        }
        Fundida.setText(""+resul);

        Lote=(TextView)findViewById(R.id.tvLoteFundido);
        Lote.setText(Fundida.getText().toString()+DiaJ.Dame_dia_J_y_anio());

        //******************    Inicio Switch     ****************//

        tinas=(Switch)findViewById(R.id.swTinaFundido);
        tinas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if (isChecked) {
                    AddCuajada.setVisibility(View.VISIBLE);

                } else {
                    AddCuajada.setVisibility(View.INVISIBLE);

                }

            }
        });
        //******************    Spinners    ****************//

        spTipoCuajada1 = (Spinner) findViewById(R.id.spTipoCj1);
        tipo_cuajada_1=getResources().getStringArray(R.array.tipo_cuajada);
        ArrayAdapter<String> adpcj1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipo_cuajada_1);
        adpcj1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoCuajada1.setAdapter(adpcj1);

        spTipoCuajada2 = (Spinner) findViewById(R.id.spTipoCj2);
        tipo_cuajada_2=getResources().getStringArray(R.array.tipo_cuajada);
        ArrayAdapter<String> adpcj2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipo_cuajada_2);
        adpcj2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoCuajada2.setAdapter(adpcj2);

        spTipoCuajada3 = (Spinner) findViewById(R.id.spTipoCj3);
        tipo_cuajada_3=getResources().getStringArray(R.array.tipo_cuajada);
        ArrayAdapter<String> adpcj3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipo_cuajada_3);
        adpcj3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoCuajada3.setAdapter(adpcj3);

        spFamiliaFun = (Spinner) findViewById(R.id.spFamiliaFundido);
        familia_fundido=getResources().getStringArray(R.array.nombre_familia_fundido);
        ArrayAdapter<String> familiaAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, familia_fundido);
        familiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFamiliaFun.setAdapter(familiaAdapter);

        spTipoCrema = (Spinner) findViewById(R.id.spTipoCrema);
        tipo_Crema=getResources().getStringArray(R.array.tipo_crema);
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipo_Crema);
        adapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoCrema.setAdapter(adapt);

        spTipoCrema2 = (Spinner) findViewById(R.id.spTipoCrema2);
        tipo_Crema2=getResources().getStringArray(R.array.tipo_crema2);
        ArrayAdapter<String> adapt2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, tipo_Crema2);
        adapt2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoCrema2.setAdapter(adapt2);


        spFamiliaReproceso = (Spinner) findViewById(R.id.spFamiliaReproceso);
        familia_reprocdso=getResources().getStringArray(R.array.nombre_familia_reproceso);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, familia_reprocdso);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFamiliaReproceso.setAdapter(ad);

        spTexturizador = (Spinner) findViewById(R.id.spTipoTexturizador);
        texturizador=getResources().getStringArray(R.array.nombre_familia_texturizador_Fundido);
        ArrayAdapter<String> adatex = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, texturizador);
        adatex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTexturizador.setAdapter(adatex);

        spTipoCuajada1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo_cj_1_sel=tipo_cuajada_1[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTipoCuajada2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo_cj_2_sel=tipo_cuajada_2[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTipoCuajada3.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipo_cj_3_sel=tipo_cuajada_3[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spTexturizador.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                texturizador_sel=texturizador[position];
                if(position==0) {
                peso_texturizador=10.8;
                }
                else if(position==1) {
                    peso_texturizador=16.3;
                }
                else if(position==2) {
                    peso_texturizador=25.95;
                }
                else if(position==3) {
                    peso_texturizador=3.05;
                }
                else if(position==4) {
                    peso_texturizador=9.52;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spFamiliaFun.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                spTipoCrema2.setSelection(0);

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                familia_sel=familia_fundido[position];
                if(position==0){
                    spTipoCrema2.setEnabled(true);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act)
                    );
                    cambia_editext(true,true,false,false,true,true,true,true,false,false,false,false,false,false,false,false,false,true,true);

                }
                else if(position==1){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
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
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false);

                }
                else if(position==2){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
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
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false);

                }
                else if(position==3){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false);

                }
                else if(position==4){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
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
                    cambia_editext(true,true,false,false,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false);

                }
                else if(position==5){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
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
                    cambia_editext(true,true,true,true,true,true,false,false,true,true,false,false,false,false,false,false,false,false,false);

                }
                else if(position==6){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,true,true,true,true,false,false,true,true,true,true,false,false,false,false,false,false,false);

                }
                else if(position==7){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,false,false,true,true,false,false,false,false,false,false,false,false,false,false,true,false,false);

                }
                else if(position==8){
                    spTipoCrema2.setEnabled(false);
                    limpia_campos();
                    cambia_color_textos(getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.act),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac),
                            getResources().getColor(R.color.desac)
                    );
                    cambia_editext(true,true,false,false,true,true,false,false,false,false,false,false,true,true,true,true,false,false,false);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spTipoCrema.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                tipo_crema_sel=tipo_Crema[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spTipoCrema2.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                tipo_crema_sel2=tipo_Crema2[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spFamiliaReproceso.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                familia_repro_sel=familia_reprocdso[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        //******************  FIN  Spinners    ****************//

        //******************    Inicio Buttons    ****************//

        AddCuajada=(ImageButton)findViewById(R.id.btnAddCuaj);
        AddCuajada.setVisibility(View.INVISIBLE);
        AddCuajada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) rlAddtina.getLayoutParams();
                int rr=params.width;

                switch (bandera) {
                    case 0:
                        AddCuajada.setImageResource(R.drawable.add3);
                        ResizeAnimation res = new ResizeAnimation(rlAddtina, params.width, params.height, 1, 1);
                        rlAddtina.startAnimation(res);

                        spTipoCuajada1.setSelection(0);
                        spTipoCuajada2.setSelection(0);
                        spTipoCuajada3.setSelection(0);

                        lote_cj01_1.setText("");
                        lote_cj01_2.setText("");
                        lote_cj01_3.setText("");

                        cj01_3.setText("");
                        cj01_1.setText("");
                        cj01_2.setText("");

                        ph_cj01_1.setText("");
                        ph_cj01_2.setText("");
                        ph_cj01_3.setText("");

                        bandera=1;
                        break;
                    case 1:
                        AddCuajada.setImageResource(R.drawable.add3);
                        ResizeAnimation res1 = new ResizeAnimation(rlAddtina, params.width, params.height, ViewGroup.LayoutParams.WRAP_CONTENT, 75);
                        rlAddtina.startAnimation(res1);
                        bandera=2;
                        break;
                    case 2:
                        AddCuajada.setImageResource(R.drawable.add3);
                        ResizeAnimation res2 = new ResizeAnimation(rlAddtina, params.width, params.height, ViewGroup.LayoutParams.WRAP_CONTENT, 125);
                        rlAddtina.startAnimation(res2);
                        bandera=3;
                        break;
                    case 3:
                        AddCuajada.setImageResource(R.drawable.menos);
                        ResizeAnimation res3 = new ResizeAnimation(rlAddtina, params.width, params.height, ViewGroup.LayoutParams.WRAP_CONTENT, 200);
                        rlAddtina.startAnimation(res3);
                        bandera=0;
                        break;

                    default:
                        break;
                }

            }
        });

        Calcula_peso=(Button)findViewById(R.id.btnCalculaPeso);
        Calcula_peso.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!(cantidad_crema2.getText().toString().isEmpty())){
                    canti_crema2=Double.parseDouble(cantidad_crema2.getText().toString());
                }
                else{canti_crema2=0;}

                if (!(cj01.getText().toString().isEmpty())){
                cantidad_cj01=Double.parseDouble(cj01.getText().toString());
                }
                else{cantidad_cj01=0;}

                if (!(cj01_1.getText().toString().isEmpty())){
                    cantidad_cj01_1=Double.parseDouble(cj01_1.getText().toString());
                }
                else{cantidad_cj01_1=0;}

                if (!(cj01_2.getText().toString().isEmpty())){
                    cantidad_cj01_2=Double.parseDouble(cj01_2.getText().toString());
                }
                else{cantidad_cj01_2=0;}

                if (!(cj01_3.getText().toString().isEmpty())){
                    cantidad_cj01_3=Double.parseDouble(cj01_3.getText().toString());
                }
                else{cantidad_cj01_3=0;}


                if (!(cj011.getText().toString().isEmpty())){
                cantidad_cj011=Double.parseDouble(cj011.getText().toString());
                 }
                else{cantidad_cj011=0;}
                if (!(ad1.getText().toString().isEmpty())){
                cantidad_mp005=Double.parseDouble(ad1.getText().toString());

                }
                else{cantidad_mp005=0;}
                if (!(ad2.getText().toString().isEmpty())){
                cantidad_mp015=Double.parseDouble(ad2.getText().toString());
                    tot_mp015=cantidad_mp015/1000;
                }
                else{tot_mp015=0;}
                if (!(ad3.getText().toString().isEmpty())){
                cantidad_s0101=Double.parseDouble(ad3.getText().toString());
                tot_s0101=cantidad_s0101/1000;
                }
                else{tot_s0101=0;}
                if (!(ad4.getText().toString().isEmpty())){
                cantidad_mp007=Double.parseDouble(ad4.getText().toString());}
                else{cantidad_mp007=0;}
                if (!(sa01.getText().toString().isEmpty())){
                cantidad_sa01=Double.parseDouble(sa01.getText().toString());}
                else{cantidad_sa01=0;}
                if (!(mp024.getText().toString().isEmpty())){
                cantidad_mp024=Double.parseDouble(mp024.getText().toString());}
                else{cantidad_mp024=0;}
                if (!(cantidad_crema.getText().toString().isEmpty())){
                cantidad_int_crema=Double.parseDouble(cantidad_crema.getText().toString());}
                else{cantidad_int_crema=0;}
                if (!(cantidad_reproceso.getText().toString().isEmpty())){
                cantidad_familia_repro=Double.parseDouble(cantidad_reproceso.getText().toString());}
                else{cantidad_familia_repro=0;}

                valor_total=peso_texturizador+cantidad_cj01+cantidad_cj01_1+cantidad_cj01_2+cantidad_cj01_3+canti_crema2+cantidad_cj011+cantidad_mp005+tot_mp015+tot_s0101+cantidad_mp007+cantidad_sa01+cantidad_mp024+cantidad_int_crema+cantidad_familia_repro;

                //DecimalFormat precision = new DecimalFormat("0.00");

                //Peso_tot.setText(precision.format(valor_total));
                //Peso_tot.setText(String.format( "%.2f", valor_total ) );
                Peso_tot.setText(""+round(valor_total,2));

            }
        });
        GuardarF=(Button)findViewById(R.id.btnGuardaFundido);
        GuardarF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                boolean exitoso= con.DAOFundido(Lote.getText().toString(), Linea.getText().toString(), FechaH.Hoy_hora(), Fundida.getText().toString(),
                        familia_sel, cj01.getText().toString(), lote_cj01.getText().toString(), ph_cj01.getText().toString(), cj011.getText().toString(),
                        lote_cj011.getText().toString(), ph_cj011.getText().toString(),
                        ad1.getText().toString(), lot1.getText().toString(), ad2.getText().toString(), lot2.getText().toString(), ad3.getText().toString(), lot3.getText().toString(),
                        ad4.getText().toString(), lot4.getText().toString(), sa01.getText().toString(), lote_sa01.getText().toString(), mp024.getText().toString(),
                        lote_mp024.getText().toString(), mp078.getText().toString(), lote_mp078.getText().toString(), cj02.getText().toString(),
                        lote_cj02.getText().toString(), agua.getText().toString(),tipo_crema_sel, lote_crema.getText().toString(),
                        cantidad_crema.getText().toString(), familia_repro_sel, lote_famiRepro.getText().toString(), cantidad_reproceso.getText().toString(),
                        tempe_final.getText().toString(),Peso_tot.getText().toString(),texturizador_sel,lote_textu.getText().toString(),
                        cj01_1.getText().toString(),lote_cj01_1.getText().toString(),ph_cj01_1.getText().toString(),cj01_2.getText().toString(),lote_cj01_2.getText().toString(),ph_cj01_2.getText().toString(),
                        tipo_crema_sel2,lote_crema2.getText().toString(),cantidad_crema2.getText().toString(),tipo_cj_1_sel,tipo_cj_2_sel,tipo_cj_3_sel,cj01_3.getText().toString(),
                        lote_cj01_3.getText().toString(),ph_cj01_3.getText().toString(), FechaH.Hoy());

                if(exitoso){
                    GuardaFundidoSync task=new GuardaFundidoSync();
                    task.execute();
                    Alerta(getResources().getString(R.string.Alerta_Guardado));
                    PaginaMonitor();
                    int resul=con.DAOF_num_fundida(Variables.getLinea_fundido(),Fecha.getText().toString());
                    if (Variables.getLinea_fundido()==1 && resul==0){
                        resul+=1;
                    }
                    else if(Variables.getLinea_fundido()==1 && resul!=0){
                        resul+=2;
                    }
                    else if(Variables.getLinea_fundido()==2){
                        resul+=2;
                    }
                    Fundida.setText(""+resul);
                    Lote.setText(Fundida.getText().toString()+DiaJ.Dame_dia_J_y_anio());
                }
                else{
                    Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                }

            }
        });

        //******************    Fin Buttons   ****************//



    if(var.isFromFundido()){
        llenarValoresBusqueda(var.getLoteFundido());
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

    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Fundido.this);

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
                                    int tv9,int tv10,int tv11,int tv12,int tv13,int tv14,int tv15,int tv16,int tv17, int tv18,int tv19,int tv20){

        tvF1.setTextColor(tv1);
        tvF2.setTextColor(tv2);
        tvF3.setTextColor(tv3);
        tvF4.setTextColor(tv4);
        tvF5.setTextColor(tv5);
        tvF6.setTextColor(tv6);
        tvF7.setTextColor(tv7);
        tvF8.setTextColor(tv8);
        tvF9.setTextColor(tv9);
        lote1.setTextColor(tv10);
        lote2.setTextColor(tv11);
        lote3.setTextColor(tv12);
        lote4.setTextColor(tv13);
        lote5.setTextColor(tv14);
        lote6.setTextColor(tv15);
        lote7.setTextColor(tv16);
        lote8.setTextColor(tv17);
        tipo_cre.setTextColor(tv18);
        lote_tipocre.setTextColor(tv19);
        cantidadCrem.setTextColor(tv20);

    }

    public void cambia_editext(boolean swTex1,boolean swTex2,boolean swTex3,boolean swTex4,boolean swTex5,
                              boolean swTex6,boolean swTex7,boolean swTex8,boolean swTex9,boolean swTex10,
                              boolean swTex11,boolean swTex12,boolean swTex13,boolean swTex14,boolean swTex15,boolean swTex16,boolean swTex17,boolean swTex18,boolean swTex19 ){
        ad1.setEnabled(swTex1);
        lot1.setEnabled(swTex2);
        ad2.setEnabled(swTex3);
        lot2.setEnabled(swTex4);
        ad3.setEnabled(swTex5);
        lot3.setEnabled(swTex6);
        ad4.setEnabled(swTex7);
        lot4.setEnabled(swTex8);
        sa01.setEnabled(swTex9);
        lote_sa01.setEnabled(swTex10);
        mp024.setEnabled(swTex11);
        lote_mp024.setEnabled(swTex12);
        mp078.setEnabled(swTex13);
        lote_mp078.setEnabled(swTex14);
        cj02.setEnabled(swTex15);
        lote_cj02.setEnabled(swTex16);
        agua.setEnabled(swTex17);
        lote_crema2.setEnabled(swTex18);
        cantidad_crema2.setEnabled(swTex19);
    }

    public void limpia_campos(){
        ad1.setText("");
        lot1.setText("");
        ad2.setText("");
        lot2.setText("");
        ad3.setText("");
        lot3.setText("");
        ad4.setText("");
        lot4.setText("");
        sa01.setText("");
        lote_sa01.setText("");
        mp024.setText("");
        lote_mp024.setText("");
        mp078.setText("");
        lote_mp078.setText("");
        cj02.setText("");
        lote_cj02.setText("");
        agua.setText("");
        lote_crema2.setText("");
        cantidad_crema2.setText("");
    }

    class CurrencyTextWatcher3decimales implements TextWatcher {

        boolean mEditing;

        public CurrencyTextWatcher3decimales() {
            mEditing = false;
        }

        public synchronized void afterTextChanged(Editable s) {
            if(!mEditing) {
                mEditing = true;

                String digits = s.toString().replaceAll("\\D", "");
                DecimalFormat df=new DecimalFormat("###,###.###");
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

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public class GuardaFundidoSync extends AsyncTask<String, Void, Boolean>

    {
        private final ProgressDialog dialog = new ProgressDialog(Fundido.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Enviando datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args)

        {



            final String NAMESPACE = "http://serv_gsj.net/";
            //final String URL="http://"+Variables.getIp_servidor()+"/ServicioClientes.asmx";
            final String URL="http://"+Variables.getIp_servidor()+"/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertaFundido";
            final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
            final int time=20000,time2=190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);


            request.addProperty("linea", Linea.getText().toString());
            /*request.addProperty("fecha", ""+FechaH.Hoy_hora());*/
            request.addProperty("fecha", ""+FechaH.Hoy_hora());
            Log.i("","La fecha es:    "+""+FechaH.Hoy_hora());
            request.addProperty("num_fundida", Fundida.getText().toString());
            request.addProperty("lote", Lote.getText().toString());
            request.addProperty("familia", familia_sel);
            request.addProperty("cj01", cj01.getText().toString());
            request.addProperty("lote_cj01", lote_cj01.getText().toString());
            request.addProperty("ph_cj01", ph_cj01.getText().toString());
            request.addProperty("cj011", cj011.getText().toString());
            request.addProperty("lote_cj011", lote_cj011.getText().toString());
            request.addProperty("ph_cj011", ph_cj011.getText().toString());
            request.addProperty("mp005", ad1.getText().toString());
            request.addProperty("lote_mp005", lot1.getText().toString());
            request.addProperty("mp015", ad2.getText().toString());
            request.addProperty("lote_mp015", lot2.getText().toString());
            request.addProperty("s0101", ad3.getText().toString());
            request.addProperty("lote_s0101", lot3.getText().toString());
            request.addProperty("mp007", ad4.getText().toString());
            request.addProperty("lote_mp007", lot4.getText().toString());
            request.addProperty("sa01", sa01.getText().toString());
            request.addProperty("lote_sa01", lote_sa01.getText().toString());
            request.addProperty("mp024", mp024.getText().toString());
            request.addProperty("lote_mp024", lote_mp024.getText().toString());
            request.addProperty("mp078", mp078.getText().toString());
            request.addProperty("lote_mp078", lote_mp078.getText().toString());
            request.addProperty("cj02", cj02.getText().toString());
            request.addProperty("lote_cj02", lote_cj02.getText().toString());
            request.addProperty("agua", agua.getText().toString());
            request.addProperty("tipo_crema", tipo_crema_sel);
            request.addProperty("lote_tipo_crema", lote_crema.getText().toString());
            request.addProperty("cantidad_crema", cantidad_crema.getText().toString());
            request.addProperty("familia_reproceso", familia_repro_sel);
            request.addProperty("lote_fami_repro", lote_famiRepro.getText().toString());
            request.addProperty("cantidad_fami_repro", cantidad_reproceso.getText().toString());
            request.addProperty("temperatura", tempe_final.getText().toString());
            request.addProperty("peso_total", Peso_tot.getText().toString());
            request.addProperty("texturizador", texturizador_sel);
            request.addProperty("lote_texturizador", lote_textu.getText().toString());
            request.addProperty("cj01_1", cj01_1.getText().toString());
            request.addProperty("lote_cj01_1", lote_cj01_1.getText().toString());
            request.addProperty("ph_cj01_1", ph_cj01_1.getText().toString());
            request.addProperty("cj01_2", cj01_2.getText().toString());
            request.addProperty("lote_cj01_2", lote_cj01_2.getText().toString());
            request.addProperty("ph_cj01_2", ph_cj01_2.getText().toString());
            request.addProperty("tipo_crema2", tipo_crema_sel2);
            request.addProperty("lote_tipo_crema2", lote_crema2.getText().toString());
            request.addProperty("cantidad_crema2", cantidad_crema2.getText().toString());
            request.addProperty("tipo_cuajada_1", tipo_cj_1_sel);
            request.addProperty("tipo_cuajada_2", tipo_cj_2_sel);
            request.addProperty("tipo_cuajada_3", tipo_cj_3_sel);
            request.addProperty("cj01_3", cj01_3.getText().toString());
            request.addProperty("lote_cj01_3", lote_cj01_3.getText().toString());
            request.addProperty("ph_cj01_3", ph_cj01_3.getText().toString());

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL,time);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
                String mensaje = resultado_xml.toString();
                //transporte.getConnection().disconnect();
//transporte.getServiceConnection().disconnect();
//transporte.reset();
// transporte.getConnection().disconnect();
//transporte.getServiceConnection().disconnect();
//transporte.reset();
                return mensaje.contentEquals("true");


            }
            catch (Exception e)
            {
                Log.i("Error","Error de Sincronizacion:  "+e);

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
                Toast.makeText(Fundido.this, "Sincronización Exitosa", Toast.LENGTH_SHORT).show();


            }

            else
            {
                Toast.makeText(Fundido.this, "Error de Sincronización", Toast.LENGTH_SHORT).show();
            }
        }}


    public void PaginaMonitor(){


        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://"+Variables.getIp_servidor()+"/SignalRTest/simplechat.aspx?val=123");


        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
    public void llenarValoresBusqueda(String lote) {

        cursor= con.DAOLLenarFundido(lote);

        Lote.setText(cursor.getString(cursor.getColumnIndex("lote")));
        Linea.setText(cursor.getString(cursor.getColumnIndex("linea")));
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_hoy")));

    }

}
