package com.example.sistemas.gsjetapa1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import DAO.consultas;
import DTO.Variables;

/**
 * Created by Sistemas on 25/02/2015.
 */
public class Login extends Activity {

    Button Ingresar;
    EditText Usuario, Clave;
    int tipoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Variables.setContextoGral(getApplicationContext());
        Variables.setNombre_usuario("");

        Usuario = (EditText)findViewById(R.id.etUsuario);
        Clave = (EditText)findViewById(R.id.etContrasena);

        Ingresar = (Button)findViewById(R.id.ibLogin);
        Ingresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub



                consultas con = new consultas();
                tipoUsuario=con.DAOLogin(Usuario.getText().toString(), Clave.getText().toString());

                if(tipoUsuario==1)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Exportar.class));

                }

                else if(tipoUsuario==2)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    Variables.setNum_tina_mostrar(0);
                    Variables.setNumero_tina_A(0);
                    Variables.setNumero_tina_B(0);
                    Variables.setNumero_tina_C(0);

                    finish();startActivity(new Intent(Login.this, Cuajado_sel_tina.class));

                }
                else if(tipoUsuario==3)
                   {
                       Variables.setFromFundido(false);
                       finish();startActivity(new Intent(Login.this, Fundido.class));
                       Variables.setLinea_fundido(1);
                   }
                else if(tipoUsuario==4)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Producto_Terminado.class));
                }
                else if(tipoUsuario==5)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    Variables.setFromSearch(false);
                    finish();startActivity(new Intent(Login.this, Texturizador.class));
                }
                else if(tipoUsuario==6)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    Variables.setFromEmpaque(false);
                    finish();startActivity(new Intent(Login.this, Empaque.class));
                }
                else if(tipoUsuario==7)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    Variables.setFromFundido(false);
                    finish();startActivity(new Intent(Login.this, Fundido.class));
                    Variables.setLinea_fundido(2);
                }
                else if(tipoUsuario==8)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Configuracion.class));

                }
                else if (tipoUsuario==9)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));
                }
                else if (tipoUsuario== 10 )
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));
                }
                else if (tipoUsuario== 11 )
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));
                }
                else if (tipoUsuario== 12 )
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));
                }
                else if (tipoUsuario== 13 )
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));
                }
                else if (tipoUsuario==14)
                {

                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Laboratorio_Calidad.class));

                }
                else if (tipoUsuario==15)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));

                }
                else if (tipoUsuario==16)
                {
                    Variables.setNombre_usuario(Usuario.getText().toString());
                    finish();startActivity(new Intent(Login.this, Administrador.class));

                }



                else {Alerta();}

            }
        });


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

    public void Alerta(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(R.string.Alerta_Contrasena);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {


            }

        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }
}
