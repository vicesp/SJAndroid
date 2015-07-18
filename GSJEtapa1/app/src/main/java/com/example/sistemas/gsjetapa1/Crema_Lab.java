package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.SweepGradient;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Crema_Lab extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private Cursor cursor;

    private TextView Fecha,codigo_prod;
    private Button btnBack,btnProd;
    private ImageButton guarda;
    private EditText Lote, obsSa, obsCo, obsAro, obsEsc, obsFlu, ph, solidos, acidez, grasa ;
    private Switch sabor, color, aroma, escurrimiento, fluidez;

    private String Nombre_PT[], Crema_PT[];
    private String [] listaProductos;
    private ArrayList<String> array_sort, filtroProductos;
    int textlength=0;
    private AlertDialog myalertDialog=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crema__lab);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();


        /*********** Edit Texts **************/
        Lote=(EditText)findViewById(R.id.tvLCLotePendiente);
        obsSa=(EditText)findViewById(R.id.editText17);
        obsCo=(EditText)findViewById(R.id.editText20);
        obsAro=(EditText)findViewById(R.id.editText21);
        obsEsc=(EditText)findViewById(R.id.editText22);
        obsFlu=(EditText)findViewById(R.id.editText23);
        ph=(EditText)findViewById(R.id.editText24);
        solidos=(EditText)findViewById(R.id.editText26);
        acidez=(EditText)findViewById(R.id.editText25);
        grasa=(EditText)findViewById(R.id.editText27);
        codigo_prod=(TextView)findViewById(R.id.codigoProducto);


        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());



        /*********** Buttons **************/

        btnProd=(Button)findViewById(R.id.btnProd);
        btnProd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                launchView();
            }});
        
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (var.isFromCrema()) {
                    var.setFromAdminCrema(true);
                    finish();
                    startActivity(new Intent(Crema_Lab.this, Realizados.class));
                } else {
                    finish();
                    startActivity(new Intent(Crema_Lab.this, Panel_Lab.class));

                }

            }
        });
        guarda=(ImageButton)findViewById(R.id.guardarBtn);
        guarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(var.isFromCrema()){
                    boolean exitoso = con.DAOActualizaCremaLab(var.getLoteCrema(),Fecha.getText().toString(),switchTexter(sabor.isChecked()),obsSa.getText().toString(), switchTexter(color.isChecked()), obsCo.getText().toString(),
                            switchTexter(aroma.isChecked()),obsAro.getText().toString(),switchTexter(escurrimiento.isChecked()), obsEsc.getText().toString(), switchTexter(fluidez.isChecked()), obsFlu.getText().toString(),ph.getText().toString(),
                            solidos.getText().toString(), acidez.getText().toString(), grasa.getText().toString(),Lote.getText().toString(),btnProd.getText().toString(), codigo_prod.getText().toString());

                    if(exitoso){
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                } else{
                    boolean exitoso = con.DAOCremaLab(Lote.getText().toString(), FechaH.Hoy_hora(),switchTexter(sabor.isChecked()),obsSa.getText().toString(), switchTexter(color.isChecked()), obsCo.getText().toString(),
                            switchTexter(aroma.isChecked()),obsAro.getText().toString(),switchTexter(escurrimiento.isChecked()), obsEsc.getText().toString(), switchTexter(fluidez.isChecked()), obsFlu.getText().toString(),ph.getText().toString(),
                            solidos.getText().toString(), acidez.getText().toString(), grasa.getText().toString(),Fecha.getText().toString(),btnProd.getText().toString(), codigo_prod.getText().toString());
                if(exitoso){
                    Alerta(getResources().getString(R.string.Alerta_Guardado));
                    vaciarTodo();
                } else {
                    Alerta(getResources().getString(R.string.Alerta_NoGuardado));

                }
                }
            }
        });

        /******* Switches *******/
        sabor = (Switch)findViewById(R.id.switchSabor);
        color = (Switch)findViewById(R.id.switchColor);
        aroma = (Switch)findViewById(R.id.switchAroma);
        escurrimiento = (Switch)findViewById(R.id.switchEscurrimiento);
        fluidez = (Switch)findViewById(R.id.switchFluidez);

        if(var.isFromCrema()){
            //Lote.setEnabled(false);
            llenarValoresBusqueda(var.getLoteCrema());
        }
        else{
            guarda.setImageResource(R.drawable.guarda);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crema__lab, menu);
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Crema_Lab.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {
                if (var.isFromCrema()){
                    var.setFromAdminCrema(true);
                    finish();
                    startActivity(new Intent(Crema_Lab.this, Realizados.class));
                }

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public String switchTexter(boolean affirmation){
        if(affirmation)
        {
            return "si";
        }
        else
        {
            return "no";
        }
    }
    public boolean textSwithcer(String affirmation)
    {
        if(affirmation.equals("si"))
        {
            return true;
        }
        else{return false;}

    }
    public void llenarValoresBusqueda(String lote) {
        cursor = con.DAOLLenarCremaLab(lote);
        Lote.setText(lote);
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_hoy")));
        sabor.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("sabor"))));
        obsSa.setText(cursor.getString(cursor.getColumnIndex("sabor_observaciones")));
        color.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("color"))));
        obsCo.setText(cursor.getString(cursor.getColumnIndex("color_observaciones")));
        aroma.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("aroma"))));
        obsAro.setText(cursor.getString(cursor.getColumnIndex("aroma_observaciones")));
        escurrimiento.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("escurrimiento"))));
        obsEsc.setText(cursor.getString(cursor.getColumnIndex("escurrimiento_observaciones")));
        fluidez.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("fluidez"))));
        obsFlu.setText(cursor.getString(cursor.getColumnIndex("fluidez_observaciones")));
        ph.setText(cursor.getString(cursor.getColumnIndex("ph")));
        solidos.setText(cursor.getString(cursor.getColumnIndex("solidos")));
        acidez.setText(cursor.getString(cursor.getColumnIndex("acidez")));
        grasa.setText(cursor.getString(cursor.getColumnIndex("grasa")));
        btnProd.setText(cursor.getString(cursor.getColumnIndex("producto")));
        codigo_prod.setText(cursor.getString(cursor.getColumnIndex("codigo_producto")));
    }
    public void vaciarTodo(){
        Lote.setText(null);
        sabor.setChecked(false);
        obsSa.setText(null);
        color.setChecked(false);
        obsCo.setText(null);
        aroma.setChecked(false);
        obsAro.setText(null);
        escurrimiento.setChecked(false);
        obsEsc.setText(null);
        fluidez.setChecked(false);
        obsFlu.setText(null);
        ph.setText(null);
        solidos.setText(null);
        acidez.setText(null);
        grasa.setText(null);
        btnProd.setText("Seleccionar Producto");
        codigo_prod.setText(null);

    }
    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        myalertDialog.dismiss();
        String strName = array_sort.get(position);
        codigo_prod.setText(strName.substring(0, strName.indexOf('-')));
        cursor = con.DAOGetCursorTodosFamilias(codigo_prod.getText().toString());
        btnProd.setText(strName.substring(strName.indexOf('-') + 1, strName.length()));

    }

    @Override
    public void onClick(View v) {

    }
    public void launchView()
    {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Crema_Lab.this);

        Nombre_PT = getProductosArray(con.DAOGetTodosProductos(null,0));
        int x = 0;
        for(int i =0; i<Nombre_PT.length; i++){
            if(Nombre_PT[i].substring(0,2).equals("CF")){
                x++;
            }
        }
        Crema_PT = new String[x];
        x=0;
        for(int i =0; i<Nombre_PT.length; i++){
            if(Nombre_PT[i].substring(0,2).equals("CF")){
                Crema_PT[x]=Nombre_PT[i];
                x++;
            }
        }



        //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
        final EditText editText = new EditText(Crema_Lab.this);
        final ListView listview = new ListView(Crema_Lab.this);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
        array_sort = new ArrayList<String>(Arrays.asList(Crema_PT));
        LinearLayout layout = new LinearLayout(Crema_Lab.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText);
        layout.addView(listview);
        myDialog.setView(layout);
        CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Crema_Lab.this, array_sort);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(Crema_Lab.this);
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
                for (int i = 0; i < Crema_PT.length; i++) {
                    if (textlength <= Crema_PT[i].length()) {

                        if (Crema_PT[i].toLowerCase().contains(editText.getText().toString().toLowerCase().trim())) {
                                array_sort.add(Crema_PT[i]);
                        }
                    }
                }
                listview.setAdapter(new CustomAlertAdapter(Crema_Lab.this, array_sort));
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
    public class GuardaLaboratorio extends AsyncTask<String, Void, Boolean>
    {
        private final ProgressDialog dialog = new ProgressDialog(Crema_Lab.this);

        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Enviando Datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args)
        {
            final String NAMESPACE = "http://serv_gsj.net/";
            final String URL = "http://" + Variables.getIp_servidor() + "/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertacremalab";
            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
            final int time = 20000, time2 = 190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("lote", Lote.getText().toString());
            request.addProperty("fecha", Fecha.getText().toString());
            request.addProperty("producto", btnProd.getText().toString());
            request.addProperty("codigo_prod",codigo_prod.getText().toString());
            request.addProperty("sabor", switchTexter(sabor.isChecked()));
            request.addProperty("color", switchTexter(color.isChecked()));
            request.addProperty("aroma", switchTexter(aroma.isChecked()));
            request.addProperty("observaciones_sabor", obsSa.getText().toString());
           /* request.addProperty("observaciones_color", obsCo(swHeb.isChecked()));
            request.addProperty("observaciones_aroma", obsAro.getText().toString());
            request.addProperty("escurrimiento", escurrimiento.getText().toString());
            request.addProperty("fluidez", fluidez.getText().toString());
            request.addProperty("observaciones_escurrimiento", ph.getText().toString());
            request.addProperty("observaciones_fluidez", grasa_total.getText().toString());
            request.addProperty("ph", humRem.getText().toString());
            request.addProperty("acidez", phRem.getText().toString());
            request.addProperty("solidos_totales", grasRem.getText().toString());
            request.addProperty("acidez", switchTexter(swRem.isChecked()));
            request.addProperty("grasa", switchTexter(swRallQR.isChecked()));*/


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
                Toast.makeText(Crema_Lab.this, "Sincronizacion Exitosa", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(Crema_Lab.this, "Error de Sincronizacion", Toast.LENGTH_SHORT).show();
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
