package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Laboratorio_Calidad extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private TextView Fecha,codigo_prod, producto;
    private Button btn_listviewdialog;
    private ImageButton Guardar;
    private CheckBox check1, check2, check3;
    private Switch swApa, swCo, swSa, swAro, swRall, swHeb, swRem;
    private EditText Lote, observaciones_sabor, observaciones_rallado, observaciones_fundido, observaciones_hebrado,
            humedad, ph, grasa_total, humRem, phRem, grasRem;
    private Spinner spinnerDiez;

    private String Nombre_PT[], numero;
    private String [] listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laboratorio_calidad);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();


        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());
        codigo_prod = (TextView)findViewById(R.id.tvLCCodigo1);
        producto=(TextView)findViewById(R.id.tvLCProducto1);

        /********** Spinner ****************/
        spinnerDiez=(Spinner)findViewById(R.id.spinner);
        spinnerFiller();
        spinnerDiez.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub

                switch (position)
                {
                    case 0:{
                        numero="0";
                    }
                    case 1:{
                        numero="10";
                    }
                    case 2:{
                        numero="20";
                    }
                    case 3:{
                        numero="30";
                    }
                    case 4:{
                        numero="40";
                    }
                    case 5:{
                        numero="50";
                    }
                    case 6:{
                        numero="60";
                    }
                    case 7:{
                        numero="70";
                    }
                    case 8:{
                        numero="80";
                    }
                    case 9:{
                        numero="90";
                    }
                    case 10:{
                        numero="100";
                    }

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        /********** Image Button *****************/
        Guardar=(ImageButton)findViewById(R.id.guardarBtn);
        Guardar.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           boolean exitoso = con.DAOLaboratorioCalidad(Fecha.getText().toString(), Lote.getText().toString(), "", producto.getText().toString(),
                                                   codigo_prod.getText().toString(), switchTexter(swApa.isChecked()), switchTexter(swSa.isChecked()),
                                                   switchTexter(swCo.isChecked()), switchTexter(swAro.isChecked()), observaciones_sabor.getText().toString(),
                                                   switchTexter(swRall.isChecked()), observaciones_rallado.getText().toString(), numero, observaciones_fundido.getText().toString(),
                                                   switchTexter(swHeb.isChecked()), observaciones_hebrado.getText().toString(), getGrasa(), humedad.getText().toString(), ph.getText().toString(),
                                                   grasa_total.getText().toString(), humRem.getText().toString(), phRem.getText().toString(), grasRem.getText().toString(),
                                                   switchTexter(swRem.isChecked()), "");
                                           if(exitoso){

                                               Alerta(getResources().getString(R.string.Alerta_Guardado));
                                           }
                                           else{
                                               Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                                           }
                                       }
                                   }
        );


        /********** Switches *****************/
        swApa=(Switch)findViewById(R.id.switchApariencia);
        swSa=(Switch)findViewById(R.id.switchSabor);
        swCo=(Switch)findViewById(R.id.switchColor);
        swAro=(Switch)findViewById(R.id.switchAroma);
        swRall=(Switch)findViewById(R.id.switchRallado);
        swHeb=(Switch)findViewById(R.id.switchHebrado);
        swRem = (Switch)findViewById(R.id.switchRemuestreo);
        swRem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    humRem.setEnabled(true);
                    grasRem.setEnabled(true);
                    phRem.setEnabled(true);
                }
                else {
                    humRem.setEnabled(false);
                    grasRem.setEnabled(false);
                    phRem.setEnabled(false);
                }

            }
        });

        /************Edit Texts***************/
        Lote =(EditText)findViewById(R.id.tvLCLotePendiente);
        observaciones_sabor=(EditText)findViewById(R.id.editText17);
        observaciones_rallado=(EditText)findViewById(R.id.editText7);
        observaciones_fundido=(EditText)findViewById(R.id.editText8);
        observaciones_hebrado=(EditText)findViewById(R.id.editText9);
        humedad=(EditText)findViewById(R.id.editText10);
        ph=(EditText)findViewById(R.id.editText11);
        grasa_total=(EditText)findViewById(R.id.editText13);
        humRem=(EditText)findViewById(R.id.editText14);
        grasRem=(EditText)findViewById(R.id.editText15);
        phRem=(EditText)findViewById(R.id.editText16);

        /*********** Check Boxes **************/
        check1 =(CheckBox)findViewById(R.id.checkBox);
        check1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check1.setChecked(true);
                check2.setChecked(false);
                check3.setChecked(false);
            }});
        check2 =(CheckBox)findViewById(R.id.checkBox2);
        check2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check1.setChecked(false);
                check2.setChecked(true);
                check3.setChecked(false);
            }});
        check3 =(CheckBox)findViewById(R.id.checkBox3);
        check3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check1.setChecked(false);
                check2.setChecked(false);
                check3.setChecked(true);
            }});

        /*********** Buttons *****************/
        btn_listviewdialog=(Button)findViewById(R.id.btnECP);
        btn_listviewdialog.setOnClickListener(this);
        btn_listviewdialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder myDialog = new AlertDialog.Builder(Laboratorio_Calidad.this);

                Nombre_PT = getProductosArray(con.DAOGetTodosProductos());
                //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
                final EditText editText = new EditText(Laboratorio_Calidad.this);
                final ListView listview = new ListView(Laboratorio_Calidad.this);
                editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
                array_sort = new ArrayList<String>(Arrays.asList(Nombre_PT));
                LinearLayout layout = new LinearLayout(Laboratorio_Calidad.this);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(editText);
                layout.addView(listview);
                myDialog.setView(layout);
                CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Laboratorio_Calidad.this, array_sort);
                listview.setAdapter(arrayAdapter);

                listview.setOnItemClickListener(Laboratorio_Calidad.this);
                editText.addTextChangedListener(new TextWatcher() {
                    public void afterTextChanged(Editable s) {

                    }

                    public void beforeTextChanged(CharSequence s,
                                                  int start, int count, int after) {

                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        textlength = editText.getText().length();
                        array_sort.clear();
                        for (int i = 0; i < Nombre_PT.length; i++) {
                            if (textlength <= Nombre_PT[i].length()) {

                                if (Nombre_PT[i].toLowerCase().contains(editText.getText().toString().toLowerCase().trim())) {
                                    array_sort.add(Nombre_PT[i]);
                                }
                            }
                        }
                        listview.setAdapter(new CustomAlertAdapter(Laboratorio_Calidad.this, array_sort));
                    }
                });
                myDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                myalertDialog = myDialog.show();

            }
        });

        if(var.isFromLaboratorio())
        {
            llenarValoresBusqueda(var.getLoteLaboratorio());

        }
        else {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_laboratorio__calidad, menu);
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
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        myalertDialog.dismiss();


        String strName=array_sort.get(position);

        codigo_prod.setText(strName.substring(0, 4));
/*
        //longi_origen=lote_origen.getText().length();
        //lote_origen.getText().toString().substring()

        String x=lote_origen.getText().toString().substring(0,3);

        if(x.substring(0,2)=="00"){
            dia_juliano_origen = Integer.parseInt(lote_origen.getText().toString().substring(2,3));
        }
        else if (x.substring(0,1)=="0"){
            lote_origen.getText().toString().substring(1,3);
        }
        else {
            dia_juliano_origen = Integer.parseInt(x);
        }

        lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,true));

        /*if (strName.substring(0,4)=="OX07" || strName.substring(0,4)=="OX13"){
            lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,true));
        }
        else {
                 lote_empaque.setText(strName.substring(0, 4) + DiaJ.Dame_dia_J_y_anio_Empaque(dia_juliano_origen,false));
             }

        caducidad.setText(f_caducidad.Dame_caducidad(codigo_prod.getText().toString()));
        //Log.i("","fecha convertida:   "+f_caducidad.Dame_caducidad("AS21"));

        nombre_pt.setText(strName.substring(5,strName.length()));*/

    }
    @Override
    public void onClick(View v) {

    }

    public String[] getProductosArray(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {

            listaProductos = con.producto;
        }
        return listaProductos;
    }

    private void spinnerFiller() {
        // Construct the data source
        ArrayList<String> valuesSpinner = new ArrayList<String>();
        valuesSpinner.add("0");
        valuesSpinner.add("10");
        valuesSpinner.add("20");
        valuesSpinner.add("30");
        valuesSpinner.add("40");
        valuesSpinner.add("50");
        valuesSpinner.add("60");
        valuesSpinner.add("70");
        valuesSpinner.add("80");
        valuesSpinner.add("90");
        valuesSpinner.add("100");

        // Create the adapter to convert the array to views
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,valuesSpinner);
        // Attach the adapter to a ListView
        spinnerDiez.setAdapter(adapter);

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
    public String getGrasa()
    {

        if (check1.isChecked()){
            return "+1";
        }
        else if (check2.isChecked()){
            return "+2";
        }
        else if (check3.isChecked()){
            return "+3";
        }
        else
        {return null;}


    }
    public void checkCheckers(String check){

        if(check.equals("+1"))
        {
            check1.setChecked(true);
        }
        else if(check.equals("+2")) {
        check2.setChecked(true);
        }
        else if (check.equals("+3")){
            check3.setChecked(true);
        }


    }
    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Laboratorio_Calidad.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void llenarValoresBusqueda(String lote) {
        cursor = con.DAOLLenarLaboratorio(lote);
        Lote.setText(lote);
        Lote.setEnabled(false);

        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha")));
        producto.setText(cursor.getString(cursor.getColumnIndex("producto")));
        codigo_prod.setText(cursor.getString(cursor.getColumnIndex("codigo")));
        observaciones_sabor.setText(cursor.getString(cursor.getColumnIndex("observaciones_sabor")));
        observaciones_rallado.setText(cursor.getString(cursor.getColumnIndex("observaciones_rallado")));
        observaciones_fundido.setText(cursor.getString(cursor.getColumnIndex("observaciones_fundido")));
        observaciones_hebrado.setText(cursor.getString(cursor.getColumnIndex("observaciones_hebrado")));
        humedad.setText(cursor.getString(cursor.getColumnIndex("humedad")));
        ph.setText(cursor.getString(cursor.getColumnIndex("ph")));
        grasa_total.setText(cursor.getString(cursor.getColumnIndex("grasa_total")));
        humRem.setText(cursor.getString(cursor.getColumnIndex("humedad_remuestreo")));
        phRem.setText(cursor.getString(cursor.getColumnIndex("ph_remuestreo")));
        grasRem.setText(cursor.getString(cursor.getColumnIndex("grasa_remuestreo")));

        swSa.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("sabor"))));
        swApa.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("apariencia"))));
        swCo.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("color"))));
        swAro.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("aroma"))));
        swRall.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("rallado"))));
        swHeb.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("hebrado"))));
        swRem.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("necesidad_remuestreo"))));

        checkCheckers(cursor.getString(cursor.getColumnIndex("grasa_residual")));


        Log.i("Remuestreo", cursor.getString(cursor.getColumnIndex("necesidad_remuestreo")));

        var.setFromLaboratorio(false);

    }
}
