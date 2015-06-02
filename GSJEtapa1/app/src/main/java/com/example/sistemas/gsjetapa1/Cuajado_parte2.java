package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;

public class Cuajado_parte2 extends Activity{

    private Button Save,Regresa;
    private boolean banderaA=false,banderaB=false,banderaC=false;
    private TextView fecha,lote,tvNumMoldes,tvKilosPend;
    private String leche_tina;
    private Switch prensado;
    private double num_leche_tina,total,num_litros_suero,num_pasta_obt;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private EditText grasaCrema,cremaEstandarizada, porce_humedad;
    private EditText horaInicioDesue,litrosSuero,phDesuerado,solidosDesuerado,pastaCuaj,nummoldesCuaj,KilosCuaj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuajado_parte2);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();

        prensado=(Switch)findViewById(R.id.switch1);

        fecha=(TextView)findViewById(R.id.tvCfechapend);
        fecha.setText(FechaH.Hoy());
        lote=(TextView)findViewById(R.id.tvCLotePendiente);
        lote.setText(Variables.getLopen());

        leche_tina=con.DAOC_select_leche_tina(lote.getText().toString());

        grasaCrema=(EditText)findViewById(R.id.etCPporGrasa);
        grasaCrema.addTextChangedListener(new CurrencyTextWatcher());

        porce_humedad=(EditText)findViewById(R.id.etCporceHumedad);
        porce_humedad.addTextChangedListener(new CurrencyTextWatcher2decimal());

        cremaEstandarizada=(EditText)findViewById(R.id.etCPcremaKilos);

        horaInicioDesue=(EditText)findViewById(R.id.etChoraIniDes);
        horaInicioDesue.addTextChangedListener(new CurrencyTextWatcherHoras());
        litrosSuero=(EditText)findViewById(R.id.etCltsSuero);


        phDesuerado=(EditText)findViewById(R.id.etCphDesupen);
        phDesuerado.addTextChangedListener(new CurrencyTextWatcher());

        solidosDesuerado=(EditText)findViewById(R.id.etCsolitot);
        solidosDesuerado.addTextChangedListener(new CurrencyTextWatcher());

        pastaCuaj=(EditText)findViewById(R.id.etCpastobt);
        nummoldesCuaj=(EditText)findViewById(R.id.etCnumermorl);
        KilosCuaj=(EditText)findViewById(R.id.etCkilpendient);
        tvNumMoldes=(TextView)findViewById(R.id.textView190);
        tvKilosPend=(TextView)findViewById(R.id.textView191);

        tvNumMoldes.setTextColor(getResources().getColor(R.color.desac));
        tvKilosPend.setTextColor(getResources().getColor(R.color.desac));
        nummoldesCuaj.setEnabled(false);
        KilosCuaj.setEnabled(false);

        prensado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    tvNumMoldes.setTextColor(getResources().getColor(R.color.act));
                    tvKilosPend.setTextColor(getResources().getColor(R.color.act));
                    nummoldesCuaj.setEnabled(true);
                    KilosCuaj.setEnabled(true);
                }else{
                    tvNumMoldes.setTextColor(getResources().getColor(R.color.desac));
                    tvKilosPend.setTextColor(getResources().getColor(R.color.desac));
                    nummoldesCuaj.setEnabled(false);
                    KilosCuaj.setEnabled(false);
                }

            }
        });

        pastaCuaj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if((leche_tina.isEmpty() || pastaCuaj.getText().toString().isEmpty())){

                    litrosSuero.setText("");

                }
                else {
                    num_leche_tina=Double.parseDouble(leche_tina);
                    num_pasta_obt=Double.parseDouble(pastaCuaj.getText().toString());
                    total=num_leche_tina-num_pasta_obt;
                    litrosSuero.setText(""+total);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Save=(Button)findViewById(R.id.btnCGuardapendi);
        Save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean exito=con.DAOCuajado_parte2(lote.getText().toString(),grasaCrema.getText().toString(),cremaEstandarizada.getText().toString(),horaInicioDesue.getText().toString(),
                        litrosSuero.getText().toString(),phDesuerado.getText().toString(),solidosDesuerado.getText().toString(),
                        pastaCuaj.getText().toString(),nummoldesCuaj.getText().toString(),KilosCuaj.getText().toString(),porce_humedad.getText().toString());

                if (exito){
                    GuardaCuajado_Parte2Sync task=new GuardaCuajado_Parte2Sync();
                    task.execute();
                    Alerta(getResources().getString(R.string.Alerta_Guardado));}
                else{Alerta(getResources().getString(R.string.Alerta_NoGuardado));}


            }
        });

        Regresa=(Button)findViewById(R.id.btnRegCuajPen);
        Regresa.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();startActivity(new Intent(Cuajado_parte2.this,Cuajado_lista.class));
            }
        });

    }

    public class GuardaCuajado_Parte2Sync extends AsyncTask<String, Void, Boolean>

    {
        private final ProgressDialog dialog = new ProgressDialog(Cuajado_parte2.this);

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
            final String METHOD_NAME = "insertaCuajado_parte2";
            final String SOAP_ACTION = NAMESPACE+METHOD_NAME;
            final int time=20000,time2=190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            /*Para tabla cuajado*/
            request.addProperty("lote", lote.getText().toString());
            request.addProperty("grasaCrema", ""+grasaCrema.getText().toString());
            request.addProperty("cremaEstandarizada", cremaEstandarizada.getText().toString());
            request.addProperty("horaInicioDesue", horaInicioDesue.getText().toString());
            request.addProperty("litrosSuero", litrosSuero.getText().toString());
            request.addProperty("phDesuerado", phDesuerado.getText().toString());
            request.addProperty("solidosDesuerado", solidosDesuerado.getText().toString());
            request.addProperty("pastaCuaj", pastaCuaj.getText().toString());
            request.addProperty("nummoldesCuaj", nummoldesCuaj.getText().toString());
            request.addProperty("KilosCuaj", KilosCuaj.getText().toString());
            request.addProperty("porce_humedad", porce_humedad.getText().toString());



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
                Toast.makeText(Cuajado_parte2.this, "Sincronización Exitosa", Toast.LENGTH_SHORT).show();


            }

            else
            {
                Toast.makeText(Cuajado_parte2.this, "Error de Sincronización", Toast.LENGTH_SHORT).show();
            }
        }}


    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cuajado_parte2.this);
        alertDialogBuilder.setTitle("Aviso");
        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

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

    class CurrencyTextWatcher2decimal implements TextWatcher {

        boolean mEditing;

        public CurrencyTextWatcher2decimal() {
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


}