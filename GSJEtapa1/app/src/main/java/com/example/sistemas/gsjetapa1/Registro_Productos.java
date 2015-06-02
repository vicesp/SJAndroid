package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import DAO.consultas;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Registro_Productos extends ActionBarActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener {

    private static Fecha_Hoy FechaH;
    private static consultas con;

    private Boolean isEdit = true;

    private Button Guardar, Eliminar;
    private TextView Fecha, indicadorText;
    private EditText codigoProducto, descProducto, diasCaducidad;
    SearchView Buscador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_productos);
AlertaInicial("Que desea hacer?");
        FechaH = new Fecha_Hoy();
        con = new consultas();

        /***********  Button   *************/
        Guardar = (Button)findViewById(R.id.btnSavePT);
        Guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isEdit) {

                    if (con.DAOActualizarProductos(codigoProducto.getText().toString(), descProducto.getText().toString(), diasCaducidad.getText().toString(), 0)) {
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        vaciarCampos();

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));

                    }


                }
                else
                {
                    if (con.DAOGuardarProducto(codigoProducto.getText().toString(), descProducto.getText().toString(), diasCaducidad.getText().toString())) {
                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                        vaciarCampos();

                    }
                    else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));

                    }

                }
            }
        });
        Eliminar = (Button)findViewById(R.id.buttonEliminar);
        Eliminar.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                    if (con.DAOActualizarProductos(codigoProducto.getText().toString(), descProducto.getText().toString(), diasCaducidad.getText().toString(),1)) {
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        vaciarCampos();

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));

                    }

                }



        });

        /***********  TextViews   *************/
        Fecha = (TextView)findViewById(R.id.tvptFecha);
        Fecha.setText(FechaH.Hoy());

        indicadorText= (TextView)findViewById(R.id.textView18);

        /***********  EditText   ************/
        codigoProducto =(EditText)findViewById(R.id.editText4);
        descProducto=(EditText)findViewById(R.id.editText5);
        diasCaducidad=(EditText)findViewById(R.id.editText6);

        /***********  SearchView   ************/
        Buscador=(SearchView)findViewById(R.id.searchCodigo);
        setupSearchView();

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //listConsultas.clearTextFilter();
        } else {
            codigoProducto.setText(newText.toUpperCase());
            descProducto.setText(con.DAOGetProdcuto(newText.toUpperCase())[0]);
            diasCaducidad.setText(con.DAOGetProdcuto(newText.toUpperCase())[1]);
        }
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Variables.setLote_pendiente(((TextView)view.findViewById(R.id.tvItem)).getText().toString());
        Variables.setFromSearch(true);
        Variables.setLoteTexturizador(((TextView)view.findViewById(R.id.tvItem)).getText().toString());
        // Variables.setLopen(((TextView)view.findViewById(R.id.tvItem)).getText().toString());
        finish();startActivity(new Intent(Registro_Productos.this, Texturizador.class));
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


    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registro_Productos.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void vaciarCampos()
    {

        diasCaducidad.setText("");
        descProducto.setText("");
        codigoProducto.setText("");
        Buscador.setQuery("",true);

    }

    public void AlertaInicial(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registro_Productos.this);

        alertDialogBuilder.setTitle("Elija una opcion:");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Editar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                indicadorText.setText("Editar producto existente");

                isEdit = true;

            }


        });
        alertDialogBuilder.setNegativeButton("Crear", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                indicadorText.setText("Agregar Producto Nuevo");
                Buscador.setVisibility(View.INVISIBLE);
                Eliminar.setVisibility(View.INVISIBLE);
                isEdit=false;

            }


        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void setupSearchView() {
            Buscador.setIconifiedByDefault(false);
            Buscador.setOnQueryTextListener(this);
            Buscador.setSubmitButtonEnabled(false);

    }

}

