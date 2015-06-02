package com.example.sistemas.gsjetapa1;

/**
 * Created by Sistemas on 05/03/2015.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;

public class Cuajado_sel_tina extends Activity{

    private Button ActivaA,ActivaB,ActivaC,IngresaA,IngresaB,IngresaC,pendientes,btnCerrarSec;
    private boolean banderaA=false,banderaB=false,banderaC=false;
    private TextView fecha;

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuajado_seleccion_tina);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();

        Variables.setTinaA(false);
        Variables.setTinaB(false);
        Variables.setTinaC(false);

        //******************    Text View    ****************//
        fecha=(TextView)findViewById(R.id.tvSFecha);
        fecha.setText(FechaH.Hoy());

        //******************    Botones    ****************//

        btnCerrarSec=(Button)findViewById(R.id.btnCerrarSec);
        btnCerrarSec.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Alerta();
            }
        });

        pendientes=(Button)findViewById(R.id.btnPendientes);
       ActivaA=(Button)findViewById(R.id.btnTinaA);
        ActivaB=(Button)findViewById(R.id.btnTinaB);
        ActivaC=(Button)findViewById(R.id.btnTinaC);

        IngresaA=(Button)findViewById(R.id.btnIngresaTA);
        IngresaA.setVisibility(View.INVISIBLE);
        IngresaB=(Button)findViewById(R.id.btnIngresaTB);
        IngresaB.setVisibility(View.INVISIBLE);
        IngresaC=(Button)findViewById(R.id.btnIngresaTC);
        IngresaC.setVisibility(View.INVISIBLE);

        pendientes.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cuajado_sel_tina.this, Cuajado_lista.class));
            }
        });

        ActivaA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banderaA==true){
                    ActivaA.setText("Tina A\nDesactivada");
                    IngresaA.setVisibility(View.INVISIBLE);
                    banderaA=false;
                    Variables.setTinaA(false);
                }
                else{
                    ActivaA.setText("Tina A\nActivada");
                    IngresaA.setVisibility(View.VISIBLE);
                    banderaA=true;
                    Variables.setTinaA(true);
                }


            }
        });

        IngresaA.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.setEquipo_tina("A");
                startActivity(new Intent(Cuajado_sel_tina.this, Cuajado.class));
            }
        });

        ActivaB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banderaB==true){
                    ActivaB.setText("Tina B\nDesactivada");
                    IngresaB.setVisibility(View.INVISIBLE);
                    banderaB=false;
                    Variables.setTinaB(false);
                }
                else{
                    ActivaB.setText("Tina B\nActivada");
                    IngresaB.setVisibility(View.VISIBLE);
                    banderaB=true;
                    Variables.setTinaB(true);
                }


            }
        });

        IngresaB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.setEquipo_tina("B");
                startActivity(new Intent(Cuajado_sel_tina.this, Cuajado.class));
            }
        });

        ActivaC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (banderaC==true){
                    ActivaC.setText("Tina C\nDesactivada");
                    IngresaC.setVisibility(View.INVISIBLE);
                    banderaC=false;
                    Variables.setTinaC(false);
                }
                else{
                    ActivaC.setText("Tina C\nActivada");
                    IngresaC.setVisibility(View.VISIBLE);
                    banderaC=true;
                    Variables.setTinaC(true);
                }


            }
        });

        IngresaC.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Variables.setEquipo_tina("C");
                startActivity(new Intent(Cuajado_sel_tina.this, Cuajado.class));
            }
        });

    }


    public void Alerta(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Cuajado_sel_tina.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(R.string.Alerta_Cerrar_sec_Cuajado);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {
                finish();


            }

        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }




}