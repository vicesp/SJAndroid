package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.SweepGradient;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Crema_Lab extends ActionBarActivity {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;
    private Cursor cursor;

    private TextView Fecha;
    private Button btnBack;
    private ImageButton guarda;
    private EditText Lote, obsSa, obsCo, obsAro, obsEsc, obsFlu, ph, solidos, acidez, grasa;
    private Switch sabor, color, aroma, escurrimiento, fluidez;


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


        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());



        /*********** Buttons **************/

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
                    boolean exitoso = con.DAOActualizaCremaLab(Lote.getText().toString(),Fecha.getText().toString(),switchTexter(sabor.isChecked()),obsSa.getText().toString(), switchTexter(color.isChecked()), obsCo.getText().toString(),
                            switchTexter(aroma.isChecked()),obsAro.getText().toString(),switchTexter(escurrimiento.isChecked()), obsEsc.getText().toString(), switchTexter(fluidez.isChecked()), obsFlu.getText().toString(),ph.getText().toString(),
                            solidos.getText().toString(), acidez.getText().toString(), grasa.getText().toString());

                    if(exitoso){
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                } else{
                    boolean exitoso = con.DAOCremaLab(Lote.getText().toString(), FechaH.Hoy_hora(),switchTexter(sabor.isChecked()),obsSa.getText().toString(), switchTexter(color.isChecked()), obsCo.getText().toString(),
                            switchTexter(aroma.isChecked()),obsAro.getText().toString(),switchTexter(escurrimiento.isChecked()), obsEsc.getText().toString(), switchTexter(fluidez.isChecked()), obsFlu.getText().toString(),ph.getText().toString(),
                            solidos.getText().toString(), acidez.getText().toString(), grasa.getText().toString(),Fecha.getText().toString());
                if(exitoso){
                    Alerta(getResources().getString(R.string.Alerta_Guardado));
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
            Lote.setEnabled(false);
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
    }
}
