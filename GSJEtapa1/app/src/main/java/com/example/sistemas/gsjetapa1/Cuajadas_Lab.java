package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Cuajadas_Lab extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private TextView Fecha;
    private Button btnBack;
    private ImageButton guarda;
    private CheckBox checkBox;
    private EditText Lote, humCuaj,grasCuaj,phCuaj, phSue, acSue, stSue;


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
        guarda=(ImageButton)findViewById(R.id.guardarBtn);
        guarda.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(var.isFromCrema()) {
                    boolean exitoso = con.DAOActualizaCremaLab(Lote.getText().toString(),Fecha.getText().toString(), humCuaj.getText().toString(), grasCuaj.getText().toString(),
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(), stSue.getText().toString());
                    if (exitoso) {

                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                }
                else {
                    boolean exitoso = con.DAOCremaLab(Lote.getText().toString(),FechaH.Hoy_hora() ,humCuaj.getText().toString(), grasCuaj.getText().toString(),
                            phCuaj.getText().toString(), phSue.getText().toString(), acSue.getText().toString(),
                            stSue.getText().toString(),FechaH.Hoy());
                    if (exitoso) {

                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }
            }});
        /************Edit Texts***************/
        Lote =(EditText)findViewById(R.id.tvLCLotePendiente);
        humCuaj =(EditText)findViewById(R.id.editText24);
        grasCuaj =(EditText)findViewById(R.id.editText7);
        phCuaj =(EditText)findViewById(R.id.editText25);
        phSue =(EditText)findViewById(R.id.editText28);
        acSue =(EditText)findViewById(R.id.editText31);
        stSue =(EditText)findViewById(R.id.editText30);

        /*********** CheckBox *************/
        checkBox = (CheckBox) findViewById(R.id.grasaCB);
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            if (checkBox.isChecked())
            {
                grasCuaj.setVisibility(View.VISIBLE);
            }
                else{
                grasCuaj.setVisibility(View.INVISIBLE);
            }

            }

        });

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_cuajadas__lab, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cuajadas_Lab.this);

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
