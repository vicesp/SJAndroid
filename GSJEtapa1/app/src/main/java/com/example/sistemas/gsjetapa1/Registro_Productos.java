package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Registro_Productos extends ActionBarActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemSelectedListener, SearchView.OnQueryTextListener, View.OnClickListener {

    private static Fecha_Hoy FechaH;
    private static consultas con;

    private Boolean isEdit = true;

    private Button Eliminar, desplegarProductos,backButton;
    private ImageButton Guardar;
    private TextView Fecha, indicadorText;
    private EditText codigoProducto, descProducto, diasCaducidad;

    private String[] Nombre_PT,listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_productos);
AlertaInicial("Que desea hacer?");
        FechaH = new Fecha_Hoy();
        con = new consultas();

        /***********  Button   *************/
        Guardar = (ImageButton)findViewById(R.id.btnSavePT);
        Guardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (isEdit) {

                    if (con.DAOActualizarProductos(codigoProducto.getText().toString(), descProducto.getText().toString(), diasCaducidad.getText().toString(), 0)) {
                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        vaciarCampos();

                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));

                    }


                } else {
                    if (con.DAOGuardarProducto(codigoProducto.getText().toString(), descProducto.getText().toString(), diasCaducidad.getText().toString())) {
                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                        vaciarCampos();

                    } else {
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


        desplegarProductos=(Button)findViewById(R.id.btntBuscar);
        desplegarProductos.setOnClickListener(this);
        desplegarProductos.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder myDialog = new AlertDialog.Builder(Registro_Productos.this);

                Nombre_PT=getProductosArray(con.DAOGetTodosProductos());
                //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
                final EditText editText = new EditText(Registro_Productos.this);
                final ListView listview=new ListView(Registro_Productos.this);
                editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
                array_sort=new ArrayList<String> (Arrays.asList(Nombre_PT));
                LinearLayout layout = new LinearLayout(Registro_Productos.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(editText);
                layout.addView(listview);
                myDialog.setView(layout);
                CustomAlertAdapter arrayAdapter=new CustomAlertAdapter(Registro_Productos.this, array_sort);
                listview.setAdapter(arrayAdapter);

                listview.setOnItemClickListener(Registro_Productos.this);
                editText.addTextChangedListener(new TextWatcher()
                {
                    public void afterTextChanged(Editable s){

                    }
                    public void beforeTextChanged(CharSequence s,
                                                  int start, int count, int after){

                    }
                    public void onTextChanged(CharSequence s, int start, int before, int count)
                    {
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        textlength = editText.getText().length();
                        array_sort.clear();
                        for (int i = 0; i < Nombre_PT.length; i++)
                        {
                            if (textlength <= Nombre_PT[i].length())
                            {

                                if(Nombre_PT[i].toLowerCase().contains(editText.getText().toString().toLowerCase().trim()))
                                {
                                    array_sort.add(Nombre_PT[i]);
                                }
                            }
                        }
                        listview.setAdapter(new CustomAlertAdapter(Registro_Productos.this, array_sort));
                    }
                });
                myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                myalertDialog=myDialog.show();

            }
        });


        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();startActivity(new Intent(Registro_Productos.this, Administrador.class));

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
    public boolean onQueryTextChange(String newText) {return false;
    }

    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {
        myalertDialog.dismiss();
        String strName=array_sort.get(position);
        codigoProducto.setText(strName.substring(0, 4));
        descProducto.setText(con.DAOGetProdcuto(strName.substring(0, 4))[0]);
        diasCaducidad.setText(con.DAOGetProdcuto(strName.substring(0, 4))[1]);
        //longi_origen=lote_origen.getText().length();
        //lote_origen.getText().toString().substring()
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
    @Override
    public void onClick(View v) {

    }


    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Registro_Productos.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

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
                desplegarProductos.setVisibility(View.INVISIBLE);
                desplegarProductos.setEnabled(false);
                Guardar.setImageResource(R.drawable.guarda);
                Eliminar.setVisibility(View.INVISIBLE);
                isEdit = false;

            }


        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public String[] getProductosArray(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {

            listaProductos = con.producto;
        }
        return listaProductos;
    }

}

