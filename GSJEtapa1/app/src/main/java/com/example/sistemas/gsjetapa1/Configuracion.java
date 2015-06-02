package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;

public class Configuracion extends Activity {

    private Button guarda;

    private EditText ip_actual,nueva_ip;
   private TextView fecha;
    private AlertDialog myalertDialog=null;
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracion);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();

        //******************    Text View    ****************//

        fecha=(TextView)findViewById(R.id.tvptFecha);
        fecha.setText(FechaH.Hoy());


//******************    Edit Text    ****************//

        ip_actual=(EditText)findViewById(R.id.etConfIPActual);
        ip_actual.setText(con.DAOSelecConfigIP());
        nueva_ip=(EditText)findViewById(R.id.etConfNuevaIP);


        guarda=(Button)findViewById(R.id.btnSaveConfig);
        guarda.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                if (nueva_ip.getText().toString().isEmpty()){
                    Alerta(getResources().getString(R.string.ip_vacia));
                }
                else {
                    boolean exitoso = con.DAOConfigIP(nueva_ip.getText().toString(),
                            fecha.getText().toString());

                    if (exitoso) {
                        ip_actual.setText(con.DAOSelecConfigIP());
                        Alerta(getResources().getString(R.string.Alerta_Guardado));

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }
            }
        });



    }




    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Configuracion.this);

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