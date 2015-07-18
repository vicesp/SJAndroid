package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

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
    private static String tinaSuero, tinaCuajadas;

    private TextView Fecha;
    private Button btnBack;
    private ImageButton guarda;
    private CheckBox checkBox;
    private Spinner spinCuajadas, spinSuero;
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
        spinSuero = (Spinner) findViewById(R.id.spinSuero);
        spinnerFiller();
        spinCuajadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                   @Override
                                                   public void onItemSelected(AdapterView<?> parent, View view,
                                                                              int position, long id) {
                                                       // TODO Auto-generated method stub

                                                       ((TextView) parent.getChildAt(0)).setTextSize(22);
                                                       if(position==0){
                                                           tinaCuajadas="1";
                                                       }
                                                       else if(position==1){
                                                           tinaCuajadas="2";

                                                       }
                                                       else{
                                                            tinaCuajadas="3";
                                                       }

                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> parent) {
                                                       // TODO Auto-generated method stub

                                                   }
                                               }
        );
        spinSuero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                ((TextView) parent.getChildAt(0)).setTextSize(22);
                if(position==0){
                    tinaSuero="1";
                }
                else if(position==1){
                    tinaSuero="2";

                }
                else{
                    tinaSuero="3";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }});


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
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(), stSue.getText().toString(), switchTexter(checkBox.isChecked()),tinaCuajadas,tinaSuero,Lote.getText().toString());
                    if (exitoso) {
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                } else {
                    boolean exitoso = con.DAOCuajadasLab(Lote.getText().toString(), FechaH.Hoy_hora(), humCuaj.getText().toString(), grasCuaj.getText().toString(),
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(),
                            stSue.getText().toString(), FechaH.Hoy(), switchTexter(checkBox.isChecked()), tinaCuajadas, tinaSuero);
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
        if (var.isFromCuajadas()) {
            //Lote.setEnabled(false);
            llenarValoresBusqueda(var.getLoteCuajadas());

        } else {
            guarda.setImageResource(R.drawable.guarda);
        }

    }

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
        spinSuero.setSelection(getPosition(cursor.getString(cursor.getColumnIndex("tina_suero"))));

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
          case "14":return 13;
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
        spinSuero.setSelection(0);
    }

    public String switchTexter(boolean affirmation) {
        if (affirmation) {
            return "si";
        } else {
            return "no";
        }
    }

    public boolean textSwithcer(String affirmation) {
        if (affirmation.equals("si")) {
            return true;
        } else {
            return false;
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
        spinSuero.setAdapter(adapter);


    }
}
