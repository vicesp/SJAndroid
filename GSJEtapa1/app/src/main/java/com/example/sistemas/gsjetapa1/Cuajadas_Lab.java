package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Cuajadas_Lab extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private Cursor cursor;
    private static String tinaCuajadas;

    private TextView Fecha;
    private Button btnBack;
    private ImageButton guarda;
    private CheckBox checkBox, manCheck, chedCheck, stiCheck;
    private Spinner spinCuajadas;
    private EditText Lote, humCuaj, grasCuaj, phCuaj, phSue, acSue, stSue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuajadas__lab);

        FechaH = new Fecha_Hoy();
        DiaJ = new Dia_Juliano();
        con = new consultas();
        var = new Variables();

        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());

        /*********** Spinners **************/
        spinCuajadas = (Spinner) findViewById(R.id.spinCuajada);

        spinnerFiller();
        spinCuajadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view,
                                                                              int position, long id) {
                                                       // TODO Auto-generated method stub

                                                       ((TextView) parent.getChildAt(0)).setTextSize(22);
                                                       switch (position) {
                                                           case 0:
                                                               tinaCuajadas = "1";
                                                               break;
                                                           case 1:
                                                               tinaCuajadas = "2";
                                                               break;
                                                           case 2:
                                                               tinaCuajadas = "3";
                                                               break;
                                                           case 3:
                                                               tinaCuajadas = "4";
                                                               break;
                                                           case 4:
                                                               tinaCuajadas = "5";
                                                               break;
                                                           case 5:
                                                               tinaCuajadas = "6";
                                                               break;
                                                           case 6:
                                                               tinaCuajadas = "7";
                                                               break;
                                                           case 7:
                                                               tinaCuajadas = "8";
                                                               break;
                                                           case 8:
                                                               tinaCuajadas = "9";
                                                               break;
                                                           case 9:
                                                               tinaCuajadas = "10";
                                                               break;
                                                           case 10:
                                                               tinaCuajadas = "11";
                                                               break;
                                                           case 11:
                                                               tinaCuajadas = "12";
                                                               break;
                                                           case 12:
                                                               tinaCuajadas = "13";
                                                               break;
                                                           case 13:
                                                               tinaCuajadas = "14";
                                                               break;
                                                           case 14:
                                                               tinaCuajadas = "15";
                                                               break;
                                                           case 15:
                                                               tinaCuajadas = "16";
                                                               break;


                                                       }

                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       // TODO Auto-generated method stub

                                                   }
                                               }
        );




            /*********** Buttons **************/

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (var.isFromCuajadas()) {
                    var.setFromAdminCuajadas(true);
                    finish();
                    startActivity(new Intent(Cuajadas_Lab.this, Realizados.class));
                } else {
                    finish();
                    startActivity(new Intent(Cuajadas_Lab.this, Panel_Lab.class));

                }

            }
        });
        guarda = (ImageButton) findViewById(R.id.guardarBtn);
        guarda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!(checkBox.isChecked())) {
                    grasCuaj.setText("");
                }
                if (var.isFromCuajadas()) {
                    boolean exitoso = con.DAOActualizaCuajadasLab(var.getLoteCuajadas(), Fecha.getText().toString(), humCuaj.getText().toString(), grasCuaj.getText().toString(),
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(), stSue.getText().toString(), switchTexter(checkBox.isChecked()), tinaCuajadas, checkTexters(), Lote.getText().toString());
                    if (exitoso) {
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                } else {
                    boolean exitoso = con.DAOCuajadasLab(Lote.getText().toString(), FechaH.Hoy_hora(), humCuaj.getText().toString(), grasCuaj.getText().toString(),
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(),
                            stSue.getText().toString(), FechaH.Hoy(), switchTexter(checkBox.isChecked()), tinaCuajadas, checkTexters());
                    if (exitoso) {

                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                        vaciarTodo();
                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }
            }
        });
        /************Edit Texts***************/
        Lote = (EditText) findViewById(R.id.tvLCLotePendiente);
        humCuaj = (EditText) findViewById(R.id.editText24);
        grasCuaj = (EditText) findViewById(R.id.editText7);
        phCuaj = (EditText) findViewById(R.id.editText25);
        phSue = (EditText) findViewById(R.id.editText28);
        acSue = (EditText) findViewById(R.id.editText31);
        stSue = (EditText) findViewById(R.id.editText30);

        /*********** CheckBox *************/
        checkBox = (CheckBox) findViewById(R.id.grasaCB);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    grasCuaj.setVisibility(View.VISIBLE);
                } else {
                    grasCuaj.setVisibility(View.INVISIBLE);
                }

            }

        });
        manCheck = (CheckBox) findViewById(R.id.manCheck);
        manCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stiCheck.setChecked(false);
                chedCheck.setChecked(false);
                manCheck.setChecked(true);

            }

        });
        chedCheck = (CheckBox) findViewById(R.id.chedCheck);
        chedCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stiCheck.setChecked(false);
                chedCheck.setChecked(true);
                manCheck.setChecked(false);

            }

        });
        stiCheck = (CheckBox) findViewById(R.id.stiCheck);
        stiCheck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                stiCheck.setChecked(true);
                chedCheck.setChecked(false);
                manCheck.setChecked(false);


            }

        });


        if (var.isFromCuajadas()) {
            //Lote.setEnabled(false);
            llenarValoresBusqueda(var.getLoteCuajadas());

        } else {
            guarda.setImageResource(R.drawable.guarda);
        }

    }
    //16 y 20 cuajadas
    //16 crema
    //22 17 16

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cuajadas__lab, menu);
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

    public void Alerta(String mensaje) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cuajadas_Lab.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                if (var.isFromCuajadas()) {
                    var.setFromAdminCuajadas(true);
                    finish();
                    startActivity(new Intent(Cuajadas_Lab.this, Realizados.class));
                }

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public void llenarValoresBusqueda(String lote) {

        cursor = con.DAOLLenarCuajadasLab(lote);
        Lote.setText(cursor.getString(cursor.getColumnIndex("lote")));
        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_hoy")));
        humCuaj.setText(cursor.getString(cursor.getColumnIndex("hum_cuaj")));

        grasCuaj.setText(cursor.getString(cursor.getColumnIndex("gras_cuaj")));
        if (cursor.getString(cursor.getColumnIndex("grasa_check")).equals("si")) {
            checkBox.setChecked(true);
            grasCuaj.setVisibility(View.VISIBLE);
        }

        phCuaj.setText(cursor.getString(cursor.getColumnIndex("ph_cuaj")));
        phSue.setText(cursor.getString(cursor.getColumnIndex("ph_sue")));
        acSue.setText(cursor.getString(cursor.getColumnIndex("ac_sue")));
        stSue.setText(cursor.getString(cursor.getColumnIndex("st_sue")));
        spinCuajadas.setSelection(getPosition(cursor.getString(cursor.getColumnIndex("tina_cuajada"))));
        textCheckers(cursor.getString(cursor.getColumnIndex("tipo_cuajada")));

    }
    public int getPosition(String value){
      switch (value){
          case "1":return 0;
          case "2":return 1;
          case "3":return 2;
          case "4":return 3;
          case "5":return 4;
          case "6":return 5;
          case "7":return 6;
          case "8":return 7;
          case "9":return 8;
          case "10":return 9;
          case "11":return 10;
          case "12":return 11;
          case "13":return 12;
          case "14":
              return 13;
          case "15":return 14;


          default:return 0;
      }
    }
    public void vaciarTodo() {
        Lote.setText("");
        humCuaj.setText("");
        grasCuaj.setText("");
        grasCuaj.setVisibility(View.INVISIBLE);
        checkBox.setChecked(false);
        phCuaj.setText("");
        phSue.setText("");
        acSue.setText("");
        stSue.setText("");
        spinCuajadas.setSelection(0);
        manCheck.setChecked(false);
        chedCheck.setChecked(false);
        stiCheck.setChecked(false);

    }

    public String switchTexter(boolean affirmation) {
        if (affirmation) {
            return "si";
        } else {
            return "no";
        }
    }

    private void spinnerFiller() {
        // Construct the data source
        ArrayList<String> valuesSpinner = new ArrayList<String>();
        valuesSpinner.add("1");
        valuesSpinner.add("2");
        valuesSpinner.add("3");
        valuesSpinner.add("4");
        valuesSpinner.add("5");
        valuesSpinner.add("6");
        valuesSpinner.add("7");
        valuesSpinner.add("8");
        valuesSpinner.add("9");
        valuesSpinner.add("10");
        valuesSpinner.add("11");
        valuesSpinner.add("12");
        valuesSpinner.add("13");
        valuesSpinner.add("14");
        valuesSpinner.add("15");


        // Create the adapter to convert the array to views
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, valuesSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Attach the adapter to a ListView
        spinCuajadas.setAdapter(adapter);


    }

    public String checkTexters() {
        if (manCheck.isChecked()) {
            return "Manchego";
        } else if (chedCheck.isChecked()) {
            return "Cheddar";
        } else if (stiCheck.isChecked()) {
            return "STI 0%";
        } else {
            return null;
        }
    }

    public void textCheckers(String value) {
        if (value.equals("Manchego")) {
            manCheck.setChecked(true);
        } else if (value.equals("Cheddar")) {
            chedCheck.setChecked(true);
        } else if (value.equals("STI 0%")) {
            stiCheck.setChecked(true);
        } else {
            //posible impletacion de alguna alerta
        }
    }

    public class GuardaCuajadasLaboratorio extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(Cuajadas_Lab.this);

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Enviando Datos...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args) {
            final String NAMESPACE = "http://serv_gsj.net/";
            final String URL = "http://" + Variables.getIp_servidor() + "/ServicioWebSoap/ServicioClientes.asmx";
            final String METHOD_NAME = "insertaCuajadasLab";
            final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
            final int time = 20000, time2 = 190000;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            request.addProperty("lote", Lote.getText().toString());
            request.addProperty("fecha", Fecha.getText().toString());
            request.addProperty("num_tina", tinaCuajadas);
            request.addProperty("tipo_cuajada", checkTexters());
            request.addProperty("humedad", humCuaj.getText().toString());
            request.addProperty("ph_cuajada", phCuaj.getText().toString());
            request.addProperty("grasa", grasCuaj.getText().toString());
            request.addProperty("ph_suero", phSue.getText().toString());
            request.addProperty("acidez", acSue.getText().toString());
            request.addProperty("solidos", stSue.getText().toString());


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
                Toast.makeText(Cuajadas_Lab.this, "Sincronizacion Exitosa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Cuajadas_Lab.this, "Error de Sincronizacion", Toast.LENGTH_SHORT).show();
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


