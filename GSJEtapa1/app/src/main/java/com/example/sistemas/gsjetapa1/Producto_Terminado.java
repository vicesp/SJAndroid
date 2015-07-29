package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;

public class Producto_Terminado extends Activity implements OnClickListener, OnItemClickListener{

    private Button btn_listviewdialog=null;
    private ImageButton Guardar;
    private int num_viaj_consecutivo=0, piezas;
    private double total;
    private EditText txt_item=null,NumPiezas, codigo_pt, num_fundida;
    private TextView LotePT,fecha,numero_viaje,tot_kil_obt,titulo_num_piezas;
    private String Nombre_PT[];
    private String[] listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private static Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.producto_terminado);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        //******************    Text View    ****************//

        fecha=(TextView)findViewById(R.id.tvptFecha);
        fecha.setText(FechaH.Hoy());
        LotePT=(TextView)findViewById(R.id.tvptLote);

        titulo_num_piezas=(TextView)findViewById(R.id.tvptTitulo_piezas);

        tot_kil_obt=(TextView)findViewById(R.id.tvptKilTotObt);

        numero_viaje=(TextView)findViewById(R.id.tvptnumeroviaj);

        num_viaj_consecutivo=con.DAOPT_numero_viaje(FechaH.Hoy());
        num_viaj_consecutivo+=1;
        numero_viaje.setText(""+num_viaj_consecutivo);



//******************    Edit Text    ****************//

        NumPiezas=(EditText)findViewById(R.id.tvptNumPiezas);
        NumPiezas.setEnabled(false);

        num_fundida=(EditText)findViewById(R.id.etPPNumfundida);

        codigo_pt=(EditText)findViewById(R.id.etCodigoPT);


        txt_item=(EditText)findViewById(R.id.etCodigoPT);
        btn_listviewdialog=(Button)findViewById(R.id.btnCodigoPT);
        btn_listviewdialog.setOnClickListener(this);

        NumPiezas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (codigo_pt.getText().toString().isEmpty() || NumPiezas.getText().toString().isEmpty()){
                    //Alerta(getResources().getString(R.string.Alerta_PT_campoVacio));
                    tot_kil_obt.setText("");
                }
                else{
                    String cPT;
                    cPT=codigo_pt.getText().toString().substring(0, 4);
                    piezas=Integer.parseInt(NumPiezas.getText().toString());

                if (cPT.equals("QA03") || cPT.equals("QA04")){
                    total=piezas*0;
                    //tot_kil_obt.setText(""+total);
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("MF03")){
                    total=piezas*0.125;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CH06")|| cPT.equals("OX18")){
                    total=piezas*0.18;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CV06")|| cPT.equals("OX29")){
                    total=piezas*0.2;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CF01")|| cPT.equals("MF02")|| cPT.equals("RJ01")|| cPT.equals("RJ02")|| cPT.equals("RJ05")|| cPT.equals("RJ07")|| cPT.equals("RJ09")){
                    total=piezas*0.25;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AS07")|| cPT.equals("AS08")|| cPT.equals("AS11")|| cPT.equals("AS12")||
                        cPT.equals("AS17")|| cPT.equals("AS18")|| cPT.equals("AS21")|| cPT.equals("CV07")||
                        cPT.equals("FC03")|| cPT.equals("FR02")|| cPT.equals("MG02")|| cPT.equals("MZ03")||
                        cPT.equals("MZ04")|| cPT.equals("MZ07")|| cPT.equals("MZ09")|| cPT.equals("MZ10")||
                        cPT.equals("MZ11")|| cPT.equals("MZ13")|| cPT.equals("MZ14")|| cPT.equals("MZ17") ||
                        cPT.equals("MZ18")||cPT.equals("OL02")||cPT.equals("OX01")||cPT.equals("OX03")||
                        cPT.equals("OX04")||cPT.equals("OX11")||cPT.equals("OX16")||cPT.equals("OX23")||
                        cPT.equals("OX25")||cPT.equals("OX30")||cPT.equals("PA03")||cPT.equals("PA04")||
                        cPT.equals("PA05")||cPT.equals("PA07")||cPT.equals("PA08")||cPT.equals("PA09")||
                        cPT.equals("QR04")){
                    total=piezas*0.4;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AR03")|| cPT.equals("FC05")|| cPT.equals("FR03")|| cPT.equals("MF01")){
                    total=piezas*0.43;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CF02")|| cPT.equals("RE03")|| cPT.equals("RE04")){
                    total=piezas*0.45;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("YO03")|| cPT.equals("YO04")){
                    total=piezas*0.454;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AA01")|| cPT.equals("AS01")||cPT.equals("AS14")||cPT.equals("CH01")||
                        cPT.equals("CH04")||cPT.equals("CV00")||cPT.equals("CV01")||cPT.equals("CV04")||
                        cPT.equals("MZ05")||cPT.equals("MZ06")||cPT.equals("MZ15")||cPT.equals("MZ16")||
                        cPT.equals("OA02")||cPT.equals("OL01")||cPT.equals("OX06")||cPT.equals("OX14")){
                    total=piezas*0.5;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AR01")){
                    total=piezas*0.6;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AS16")|| cPT.equals("AS20")|| cPT.equals("CV08")){
                    total=piezas*0.8;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AS04")|| cPT.equals("AS09")|| cPT.equals("CC01")|| cPT.equals("OX17")||
                        cPT.equals("OX19")|| cPT.equals("OX21")|| cPT.equals("OX28")){
                    total=piezas*1;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AS06")){
                    total=piezas*1.3;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AR02")){
                    total=piezas*1.8;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("MZ20")|| cPT.equals("MZ21")){
                    total=piezas*2.27;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CH03")){
                    total=piezas*2.8471;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("AD01")|| cPT.equals("AS02")|| cPT.equals("CV05")|| cPT.equals("OL03")||
                        cPT.equals("OX05")|| cPT.equals("OX07")|| cPT.equals("OX08")|| cPT.equals("OX12")||
                        cPT.equals("OX13")|| cPT.equals("OX26")|| cPT.equals("OX27")|| cPT.equals("QR01")||
                        cPT.equals("QR02")|| cPT.equals("QR03")){
                    total=piezas*3;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("CR03")){
                    total=piezas*4;
                    tot_kil_obt.setText(""+round(total,2));
                }
                else if (cPT.equals("OX20")){
                    total=piezas*5;
                    tot_kil_obt.setText(""+round(total,2));
                }

               }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Guardar=(ImageButton)findViewById(R.id.btnSavePT);
        Guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (codigo_pt.getText().toString().isEmpty()){
                    Alerta(getResources().getString(R.string.Alerta_PT_campoVacio));
                }
                else {
                    if(var.isFromProducto()){
                        boolean exitoso = con.DAOActualizarPT(LotePT.getText().toString(),
                                fecha.getText().toString(),
                                codigo_pt.getText().toString(),
                                numero_viaje.getText().toString(),
                                NumPiezas.getText().toString(), tot_kil_obt.getText().toString(),
                                num_fundida.getText().toString());
                        if(exitoso){
                            Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        }
                        else{
                            Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                        }
                    }
                    else {
                        boolean exitoso = con.DAOPT(LotePT.getText().toString(),
                                fecha.getText().toString(),
                                codigo_pt.getText().toString().substring(0, 4),
                                numero_viaje.getText().toString(),
                                NumPiezas.getText().toString(), tot_kil_obt.getText().toString(), num_fundida.getText().toString(), FechaH.Hoy_hora());

                        if (exitoso) {
                            Alerta(getResources().getString(R.string.Alerta_Guardado));
                            num_viaj_consecutivo = con.DAOPT_numero_viaje(FechaH.Hoy());
                            num_viaj_consecutivo += 1;
                            numero_viaje.setText("" + num_viaj_consecutivo);
                            NumPiezas.setText("");
                            tot_kil_obt.setText("");
                        } else {
                            Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                        }
                    }
                }
            }
        });

        btn_listviewdialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                launchView();

            }
        });


        if (var.isFromProducto()){
            btn_listviewdialog.setEnabled(false);
            NumPiezas.setEnabled(true);
            titulo_num_piezas.setTextColor(getResources().getColor(R.color.act));
            llenarValoresBusqueda(var.getLoteProducto());

        }
        else{
            Guardar.setImageResource(R.drawable.guarda);

        }


    }


    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        myalertDialog.dismiss();

        String strName=array_sort.get(position);
        txt_item.setText(strName);
        LotePT.setText(codigo_pt.getText().toString().substring(0,4)+DiaJ.Dame_dia_J_y_anio());
        titulo_num_piezas.setTextColor(getResources().getColor(R.color.act));

        NumPiezas.setEnabled(true);
        NumPiezas.setText("");



    }

    @Override
    public void onClick(View v) {

    }

    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Producto_Terminado.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void llenarValoresBusqueda(String lote){
        cursor = con.DAOLLenarProducto(lote);

        LotePT.setText(lote);
        fecha.setText(cursor.getString(cursor.getColumnIndex("fecha")));
        codigo_pt.setText(cursor.getString(cursor.getColumnIndex("codigo_pt")));
        numero_viaje.setText(cursor.getString(cursor.getColumnIndex("num_viaje")));
        tot_kil_obt.setText(cursor.getString(cursor.getColumnIndex("kilos")));
        NumPiezas.setText(cursor.getString(cursor.getColumnIndex("num_piezas")));
        num_fundida.setText(cursor.getString(cursor.getColumnIndex("numero_fundida")));



    }
    public void launchView()
    {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Producto_Terminado.this);

        Nombre_PT = getProductosArray(con.DAOGetTodosProductos("",1));
        




        //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
        final EditText editText = new EditText(Producto_Terminado.this);
        final ListView listview = new ListView(Producto_Terminado.this);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
        array_sort = new ArrayList<String>(Arrays.asList(Nombre_PT));
        LinearLayout layout = new LinearLayout(Producto_Terminado.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText);
        layout.addView(listview);
        myDialog.setView(layout);
        CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Producto_Terminado.this, array_sort);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(Producto_Terminado.this);
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
                listview.setAdapter(new CustomAlertAdapter(Producto_Terminado.this, array_sort));
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

    public class GuardaProductoTerminado extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(Producto_Terminado.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Enviando Datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args) {
            final String NAMESPACE = "http://serv_gsj.net/";
            final String URL = "http://" + Variables.getIp_servidor() + "/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertaProductoTerminado";
            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
            final int time = 20000, time2 = 190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("lote", LotePT.getText().toString());
            request.addProperty("fecha", fecha.getText().toString());
            request.addProperty("numero_fundida", num_fundida.getText().toString());
            request.addProperty("codigo_pp", codigo_pt.getText().toString().substring(0, 4));
            request.addProperty("numero_viaje", numero_viaje.getText().toString());
            request.addProperty("numero_piezas", NumPiezas.getText().toString());
            request.addProperty("kilos_obtenidos", tot_kil_obt.getText().toString());


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;

            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL, time);

            try {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_XML = (SoapPrimitive) envelope.getResponse();
                String mensaje = resultado_XML.toString();

                if (mensaje.contentEquals("true")) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                Log.i("Error", "Error de sincronizacion:  " + e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (success) {
                Toast.makeText(Producto_Terminado.this, "Sincronizacion Exitosa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Producto_Terminado.this, "Error de Sincronizacion", Toast.LENGTH_SHORT).show();
            }
        }

        public void PaginaMonitor() {
            WebView myWebView = (WebView) findViewById(R.id.webView);
            myWebView.loadUrl("http://" + Variables.getIp_servidor() + "SignalRTest/simplechat.aspx?val=123");

            WebSettings webSettings = myWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }
    }


}