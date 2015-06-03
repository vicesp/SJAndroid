package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;
import DTO.fecha_Caducidad;


public class Empaque extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private String [] maquina_usar,listaProductos;
    private String maquina_usar_select;
    private Spinner spMaquinaUsar;
    private TextView Fecha,codigo_prod,nombre_pt,lote_empaque,tvLote_fondo,tvLote_tapa,tvLote_funda,tvP_entregadas,caducidad;
    private EditText lote_origen,p_reproceso,temp,hora_inicioPT,cod_restos,lote_restos,cantidad_restos,vacio_ulma,gas_ulma,piezas_emp,piezas_calidad;
    private EditText temp_formado_ulma,temp_sellado_ulma,oxigeno_ulma,vacio_ultravac,temp_ultravac,hora_fin_ultravac,lote_fondo,lote_tapa,lote_funda,observaciones;
    private Button Guardar;
    private int num_resto=0,num_empaque=0,total=0,longi_origen=0,dia_juliano_origen=0;
    private Button btn_listviewdialog=null;
    private EditText txt_item=null;

    private String Nombre_PT[];
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private static fecha_Caducidad f_caducidad;

    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empaque);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        f_caducidad=new fecha_Caducidad();


        //******************    Text Viewes    ****************//

        caducidad=(TextView)findViewById(R.id.tvECaducidad);

        Fecha=(TextView)findViewById(R.id.tveFecha);
        Fecha.setText(FechaH.Hoy());

        codigo_prod=(TextView)findViewById(R.id.tveCodigoProducto);
        codigo_prod.setVisibility(View.INVISIBLE);

        nombre_pt=(TextView)findViewById(R.id.tveNombrePT);
        lote_empaque=(TextView)findViewById(R.id.tveLoteEmpaque);

        tvLote_fondo=(TextView)findViewById(R.id.textView166);
        tvLote_tapa=(TextView)findViewById(R.id.textView167);
        tvLote_funda=(TextView)findViewById(R.id.textView168);

        tvP_entregadas=(TextView)findViewById(R.id.tvePEntregadasA);

        //******************    Edit Text    ****************//
        lote_origen=(EditText)findViewById(R.id.eteLoteOrigen);
        lote_origen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (lote_origen.getText().length()==5){
                    btn_listviewdialog.setEnabled(true);
                    codigo_prod.setVisibility(View.VISIBLE);
                    codigo_prod.setText("De clic aqui");
                }
                else{btn_listviewdialog.setEnabled(false);
                    codigo_prod.setVisibility(View.INVISIBLE);
                    nombre_pt.setText("");
                    lote_empaque.setText("");
                    codigo_prod.setText("");
                    caducidad.setText("");

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        piezas_emp=(EditText)findViewById(R.id.tvePEmpacadas);
        piezas_calidad=(EditText)findViewById(R.id.etePCalidad);



        p_reproceso=(EditText)findViewById(R.id.tvePiezasRepro);
        temp=(EditText)findViewById(R.id.tveTempePT);
        temp.addTextChangedListener(new CurrencyTextWatcher1decimal());

        hora_inicioPT=(EditText)findViewById(R.id.tveHoraIniPT);
        hora_inicioPT.addTextChangedListener(new CurrencyTextWatcherHoras());

        cod_restos=(EditText)findViewById(R.id.tveCodigoPUsados);
        lote_restos=(EditText)findViewById(R.id.tveLoteRestos);
        cantidad_restos=(EditText)findViewById(R.id.tveCantidadRestos);

        cantidad_restos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (cantidad_restos.getText().toString().isEmpty() && piezas_emp.getText().toString().isEmpty()){
                    tvP_entregadas.setText("");
                }

                else if (cantidad_restos.getText().toString().isEmpty()){
                    num_resto=0;
                    tvP_entregadas.setText(""+(num_resto+num_empaque));
                }
                else {
                    if (!(piezas_emp.getText().toString().isEmpty())){
                        num_empaque=Integer.parseInt(piezas_emp.getText().toString());
                    }
                    else{num_empaque=0;}

                    num_resto=Integer.parseInt(cantidad_restos.getText().toString());
                    tvP_entregadas.setText(""+(num_resto+num_empaque));

                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        piezas_emp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (piezas_emp.getText().toString().isEmpty() && cantidad_restos.getText().toString().isEmpty()){
                    tvP_entregadas.setText("");
                }
                else if (piezas_emp.getText().toString().isEmpty()){
                    num_empaque=0;
                    tvP_entregadas.setText(""+(num_resto+num_empaque));
                }
                else {
                    if (!(cantidad_restos.getText().toString().isEmpty())){
                        num_resto=Integer.parseInt(cantidad_restos.getText().toString());
                    }
                    else{num_resto=0;}

                    num_empaque=Integer.parseInt(piezas_emp.getText().toString());
                    tvP_entregadas.setText(""+(num_resto+num_empaque));

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vacio_ulma=(EditText)findViewById(R.id.eteVacioULMA);
        gas_ulma=(EditText)findViewById(R.id.eteGasULMA);
        temp_formado_ulma=(EditText)findViewById(R.id.eteTempULMA);
        temp_formado_ulma.addTextChangedListener(new CurrencyTextWatcher1decimal());

        temp_sellado_ulma=(EditText)findViewById(R.id.eteTempSellado);
        temp_sellado_ulma.addTextChangedListener(new CurrencyTextWatcher1decimal());

        oxigeno_ulma=(EditText)findViewById(R.id.eteOxigenoULMA);
        vacio_ultravac=(EditText)findViewById(R.id.eteVacioUltravac);
        temp_ultravac=(EditText)findViewById(R.id.eteTempUltravac);
        temp_ultravac.addTextChangedListener(new CurrencyTextWatcher1decimal());

        hora_fin_ultravac=(EditText)findViewById(R.id.eteHoraFinUltravac);
        hora_fin_ultravac.addTextChangedListener(new CurrencyTextWatcherHoras());

        lote_fondo=(EditText)findViewById(R.id.eteLoteFondoMat);
        lote_tapa=(EditText)findViewById(R.id.eteLoteTapaMat);
        lote_funda=(EditText)findViewById(R.id.eteLoteFundaMat);
        observaciones=(EditText)findViewById(R.id.eteObservaciones);



        //******************    Spinners    ****************//

        spMaquinaUsar = (Spinner) findViewById(R.id.speMaquinaUsar);
        maquina_usar=getResources().getStringArray(R.array.maquina_usar);
        ArrayAdapter<String> adap = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, maquina_usar);
        adap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spMaquinaUsar.setAdapter(adap);

        spMaquinaUsar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(25);
                maquina_usar_select=maquina_usar[position];
                if (position==0 || position==1){
                    lote_funda.setEnabled(false);
                    tvLote_funda.setTextColor(getResources().getColor(R.color.desac));
                    lote_fondo.setEnabled(true);
                    tvLote_fondo.setTextColor(getResources().getColor(R.color.act));
                    lote_tapa.setEnabled(true);
                    tvLote_tapa.setTextColor(getResources().getColor(R.color.act));
                }
                else if (position==2){
                    lote_funda.setEnabled(true);
                    tvLote_funda.setTextColor(getResources().getColor(R.color.act));
                    lote_fondo.setEnabled(false);
                    tvLote_fondo.setTextColor(getResources().getColor(R.color.desac));
                    lote_tapa.setEnabled(false);
                    tvLote_tapa.setTextColor(getResources().getColor(R.color.desac));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        //******************    Button    ****************//
        Guardar=(Button)findViewById(R.id.btnGurdaEmpaque);
        Guardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (var.isFromEmpaque()) {
                    boolean exitoso = con.DAOActualizarEmpaque(lote_origen.getText().toString(), FechaH.Hoy(), codigo_prod.getText().toString(), nombre_pt.getText().toString()
                            , lote_empaque.getText().toString(), tvP_entregadas.getText().toString(), p_reproceso.getText().toString(), temp.getText().toString(), hora_inicioPT.getText().toString()
                            , cod_restos.getText().toString(), lote_restos.getText().toString(), cantidad_restos.getText().toString(), maquina_usar_select, vacio_ulma.getText().toString()
                            , gas_ulma.getText().toString(), temp_formado_ulma.getText().toString(), temp_sellado_ulma.getText().toString(), oxigeno_ulma.getText().toString()
                            , vacio_ultravac.getText().toString(), temp_ultravac.getText().toString(), hora_fin_ultravac.getText().toString(), lote_fondo.getText().toString(), lote_tapa.getText().toString()
                            , lote_funda.getText().toString(), observaciones.getText().toString(), piezas_emp.getText().toString(), piezas_calidad.getText().toString());
                    if(exitoso){
                        limpia_campos();
                        var.setFromEmpaque(false);
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                    }
                    else{
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                }
                else {
                    boolean exitoso = con.DAOEmpaque(lote_origen.getText().toString(), FechaH.Hoy(), codigo_prod.getText().toString(), nombre_pt.getText().toString()
                            , lote_empaque.getText().toString(), tvP_entregadas.getText().toString(), p_reproceso.getText().toString(), temp.getText().toString(), hora_inicioPT.getText().toString()
                            , cod_restos.getText().toString(), lote_restos.getText().toString(), cantidad_restos.getText().toString(), maquina_usar_select, vacio_ulma.getText().toString()
                            , gas_ulma.getText().toString(), temp_formado_ulma.getText().toString(), temp_sellado_ulma.getText().toString(), oxigeno_ulma.getText().toString()
                            , vacio_ultravac.getText().toString(), temp_ultravac.getText().toString(), hora_fin_ultravac.getText().toString(), lote_fondo.getText().toString(), lote_tapa.getText().toString()
                            , lote_funda.getText().toString(), observaciones.getText().toString(), piezas_emp.getText().toString(), piezas_calidad.getText().toString());
                    if(exitoso){
                        limpia_campos();
                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                    }
                    else{
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }

            }
        });

        btn_listviewdialog=(Button)findViewById(R.id.btnECP);
        btn_listviewdialog.setEnabled(false);
        btn_listviewdialog.setOnClickListener(this);
        btn_listviewdialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder myDialog = new AlertDialog.Builder(Empaque.this);

                Nombre_PT=getProductosArray(con.DAOGetTodosProductos());
                //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
                final EditText editText = new EditText(Empaque.this);
                final ListView listview=new ListView(Empaque.this);
                editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
                array_sort=new ArrayList<String> (Arrays.asList(Nombre_PT));
                LinearLayout layout = new LinearLayout(Empaque.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(editText);
                layout.addView(listview);
                myDialog.setView(layout);
                CustomAlertAdapter arrayAdapter=new CustomAlertAdapter(Empaque.this, array_sort);
                listview.setAdapter(arrayAdapter);

                listview.setOnItemClickListener(Empaque.this);
                editText.addTextChangedListener(new TextWatcher()
                {
                    public void afterTextChanged(Editable s){

                    }
                    public void beforeTextChanged(CharSequence s,
                                                  int start, int count, int after){

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        textlength = editText.getText().length();
                        array_sort.clear();
                        for (int i = 0; i < Nombre_PT.length; i++)
                        {
                            if (textlength <= Nombre_PT[i].length())
                            {

                                if(Nombre_PT[i].toLowerCase().contains(editText.getText().toString().toLowerCase().trim()))
                                {
                                    array_sort.add(Nombre_PT[i]);
                                }
                            }
                        }
                        listview.setAdapter(new CustomAlertAdapter(Empaque.this, array_sort));
                    }
                });
                myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                myalertDialog=myDialog.show();

            }
        });


        if(var.isFromEmpaque())
        {
            llenarValoresBusqueda(var.getLoteEmpaque());
        }
        else {

            limpia_campos();
        }


    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        myalertDialog.dismiss();

        String strName=array_sort.get(position);

        codigo_prod.setText(strName.substring(0,4));

        //longi_origen=lote_origen.getText().length();
        //lote_origen.getText().toString().substring()

        String x=lote_origen.getText().toString().substring(0,3);

        if(x.substring(0,2)=="00"){
            dia_juliano_origen = Integer.parseInt(lote_origen.getText().toString().substring(2,3));
        }
        else if (x.substring(0,1)=="0"){
            lote_origen.getText().toString().substring(1,3);
        }
        else {
            dia_juliano_origen = Integer.parseInt(x);
        }

        lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,true));

        /*if (strName.substring(0,4)=="OX07" || strName.substring(0,4)=="OX13"){
            lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,true));
        }
        else {
                 lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,false));
             }*/

        caducidad.setText(f_caducidad.Dame_caducidad(codigo_prod.getText().toString()));
        //Log.i("","fecha convertida:   "+f_caducidad.Dame_caducidad("AS21"));

        nombre_pt.setText(strName.substring(5,strName.length()));


    }

    @Override
    public void onClick(View v) {

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

    public class GuardaEmpaqueSync extends AsyncTask<String, Void, Boolean>

    {
        private final ProgressDialog dialog = new ProgressDialog(Empaque.this);

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
            final String URL="http://"+ Variables.getIp_servidor()+"/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertaEmpaque";
            final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
            final int time=20000,time2=190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);



            /*Para tabla cuajado*/
            request.addProperty("lote_origen", lote_origen.getText().toString());
            request.addProperty("FechaH", ""+FechaH.Hoy_hora());
            request.addProperty("codigo_prod", codigo_prod.getText().toString());
            request.addProperty("nombre_pt", nombre_pt.getText().toString());
            request.addProperty("lote_empaque", lote_empaque.getText().toString());
            request.addProperty("tvP_entregadas", tvP_entregadas.getText().toString());
            request.addProperty("p_reproceso", p_reproceso.getText().toString());
            request.addProperty("temp", temp.getText().toString());
            request.addProperty("hora_inicioPT", hora_inicioPT.getText().toString());
            request.addProperty("cod_restos", cod_restos.getText().toString());
            request.addProperty("lote_restos", lote_restos.getText().toString());
            request.addProperty("cantidad_restos", cantidad_restos.getText().toString());
            request.addProperty("maquina_usar_select", maquina_usar_select);
            request.addProperty("vacio_ulma", vacio_ulma.getText().toString());
            request.addProperty("gas_ulma", gas_ulma.getText().toString());
            request.addProperty("temp_formado_ulma", temp_formado_ulma.getText().toString());
            request.addProperty("temp_sellado_ulma", temp_sellado_ulma.getText().toString());
            request.addProperty("oxigeno_ulma", oxigeno_ulma.getText().toString());
            request.addProperty("vacio_ultravac", vacio_ultravac.getText().toString());
            request.addProperty("temp_ultravac", temp_ultravac.getText().toString());
            request.addProperty("hora_fin_ultravac", hora_fin_ultravac.getText().toString());
            request.addProperty("lote_fondo", lote_fondo.getText().toString());
            request.addProperty("lote_tapa", lote_tapa.getText().toString());
            request.addProperty("lote_funda", lote_funda.getText().toString());
            request.addProperty("observaciones", observaciones.getText().toString());
            request.addProperty("piezas_emp", piezas_emp.getText().toString());
            request.addProperty("piezas_calidad", piezas_calidad.getText().toString());


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(URL,time);

            try
            {
                transporte.call(SOAP_ACTION, envelope);

                SoapPrimitive resultado_xml =(SoapPrimitive)envelope.getResponse();
                String mensaje = resultado_xml.toString();
                if(mensaje.contentEquals("true")){
                    //transporte.getConnection().disconnect();

                    //transporte.getServiceConnection().disconnect();

                    //transporte.reset();
                    return true;
                }
                else{
                    // transporte.getConnection().disconnect();
                    //transporte.getServiceConnection().disconnect();
                    //transporte.reset();
                    Log.i("Mensaje", "Mensaje SOAP:    " + mensaje);
                    return false;
                }




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
                Toast.makeText(Empaque.this, "Sincronización Exitosa", Toast.LENGTH_SHORT).show();


            }

            else
            {
                Toast.makeText(Empaque.this, "Error de Sincronización", Toast.LENGTH_SHORT).show();
            }
        }}

    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Empaque.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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

    public void limpia_campos(){
        codigo_prod.setVisibility(View.INVISIBLE);
        caducidad.setText("");
        codigo_prod.setText("");
        nombre_pt.setText("");
        lote_empaque.setText("");
        lote_origen.setText("");

        piezas_emp.setText("");
        piezas_calidad.setText("");
        p_reproceso.setText("");
        temp.setText("");
        hora_inicioPT.setText("");
        cod_restos.setText("");
        lote_restos.setText("");
        cantidad_restos.setText("");
        tvP_entregadas.setText("0");
        vacio_ulma.setText("");
        gas_ulma.setText("");
        temp_formado_ulma.setText("");
        temp_sellado_ulma.setText("");
        oxigeno_ulma.setText("");

        vacio_ultravac.setText("");
        temp_ultravac.setText("");
        hora_fin_ultravac.setText("");

        lote_fondo.setText("");
        lote_tapa.setText("");
        lote_funda.setText("");

        observaciones.setText("");
        lote_origen.setEnabled(true);
        btn_listviewdialog.setVisibility(View.VISIBLE);
    }


    public String[] getProductosArray(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {

            listaProductos = con.producto;
        }
        return listaProductos;
    }

    public void llenarValoresBusqueda(String lote)
    {

        lote_origen.setEnabled(false);
        btn_listviewdialog.setVisibility(View.INVISIBLE);

        cursor = con.DAOLLenarEmpaque(lote);
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha")));
        lote_origen.setText(cursor.getString(cursor.getColumnIndex("lote_origen")));
        codigo_prod.setText(cursor.getString(cursor.getColumnIndex("cod_prod")));
        nombre_pt.setText(cursor.getString(cursor.getColumnIndex("prod_terminado")));
        lote_empaque.setText(cursor.getString(cursor.getColumnIndex("lote")));
        caducidad.setText(f_caducidad.Dame_caducidad(codigo_prod.getText().toString()));
        p_reproceso.setText(cursor.getString(cursor.getColumnIndex("piezas_reproceso")));
        temp.setText(cursor.getString(cursor.getColumnIndex("temp_pt")));
        temp_formado_ulma.setText(cursor.getString(cursor.getColumnIndex("temp_ulma")));
        hora_inicioPT.setText(cursor.getString(cursor.getColumnIndex("hora_inicio_pt")));

        if (cursor.getString(cursor.getColumnIndex("maquina_usar")).equals("ULMA Optima")) {
            spMaquinaUsar.setSelection(0);
        }
        else if(cursor.getString(cursor.getColumnIndex("maquina_usar")).equals("ULMA Mini"))
        {
            spMaquinaUsar.setSelection(1);
        }
        else{
            spMaquinaUsar.setSelection(2);
    }



        cod_restos.setText(cursor.getString(cursor.getColumnIndex("cod_prod_restos")));
        lote_restos.setText(cursor.getString(cursor.getColumnIndex("lote_restos")));
        cantidad_restos.setText(cursor.getString(cursor.getColumnIndex("cantidad_restos")));
        vacio_ulma.setText(cursor.getString(cursor.getColumnIndex("vacio_ulma")));
        gas_ulma.setText(cursor.getString(cursor.getColumnIndex("gas_ulma")));
        temp_sellado_ulma.setText(cursor.getString(cursor.getColumnIndex("temp_sellado_ulma")));
        oxigeno_ulma.setText(cursor.getString(cursor.getColumnIndex("oxigeno_ulma")));
        vacio_ultravac.setText(cursor.getString(cursor.getColumnIndex("vacio_ultra")));
        temp_ultravac.setText(cursor.getString(cursor.getColumnIndex("temp_ultra")));
        hora_fin_ultravac.setText(cursor.getString(cursor.getColumnIndex("hora_fin_ultra")));
        lote_fondo.setText(cursor.getString(cursor.getColumnIndex("lote_fondo")));
        lote_tapa.setText(cursor.getString(cursor.getColumnIndex("lote_tapa")));
        lote_funda.setText(cursor.getString(cursor.getColumnIndex("lote_funda")));
        observaciones.setText(cursor.getString(cursor.getColumnIndex("observaciones")));
        piezas_emp.setText(cursor.getString(cursor.getColumnIndex("piezas_empacadas")));
        piezas_calidad.setText(cursor.getString(cursor.getColumnIndex("piezas_calidad")));




    }
}
