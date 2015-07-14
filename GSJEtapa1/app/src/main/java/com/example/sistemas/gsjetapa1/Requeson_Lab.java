package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Requeson_Lab extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private TextView Fecha,codigo_prod, codigo_fam,familia;
    private Button btn_listviewdialog, btnBack;
    private ImageButton Guardar;
    private CheckBox check1, check2, check3;
    private Switch swApa, swCo, swSa, swAro, swRem;
    private EditText Lote, observaciones_sabor,
            humedad, ph, grasa_total, humRem, phRem, grasRem, observaciones_apariencia,
            observaciones_color,observaciones_untabilidad;
    private String Nombre_PT[];
    private String [] listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requeson_lab);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.fechaText1);
        Fecha.setText(FechaH.Hoy());
        codigo_fam = (TextView)findViewById(R.id.tvLCCodigoProducto);
        codigo_prod=(TextView)findViewById(R.id.tvLCCodigoFamilia);
        familia=(TextView)findViewById(R.id.tvLCProducto1);


        /************Edit Texts***************/
        Lote =(EditText)findViewById(R.id.tvLCLotePendiente);
        observaciones_sabor=(EditText)findViewById(R.id.editText17);
        observaciones_apariencia=(EditText)findViewById(R.id.editText20);
        observaciones_color=(EditText)findViewById(R.id.editText21);
        humedad=(EditText)findViewById(R.id.editText10);
        ph=(EditText)findViewById(R.id.editText13);
        grasa_total=(EditText)findViewById(R.id.editText11);
        humRem=(EditText)findViewById(R.id.editText14);
        grasRem=(EditText)findViewById(R.id.editText15);
        phRem=(EditText)findViewById(R.id.editText16);
        observaciones_untabilidad=(EditText)findViewById(R.id.editText9);

        /********** Switches *****************/
        swApa=(Switch)findViewById(R.id.switchApariencia);
        swSa=(Switch)findViewById(R.id.switchSabor);
        swCo=(Switch)findViewById(R.id.switchColor);
        swAro=(Switch)findViewById(R.id.switchAroma);

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

        /********** Image Button *****************/
        Guardar=(ImageButton)findViewById(R.id.guardarBtn);
        Guardar.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {

                                           if (var.isFromRequeson()) {

                                               //con.DAOConsultaBitacora(Variables.getNombre_usuario(), "Laboratorio Calidad", generarDatosCambiados(), etObservaciones.getText().toString(), FechaH.Hoy_hora());


                                               boolean exitoso = con.DAOActualizarRequesonLab(Fecha.getText().toString(), var.getLoteRequeson(), btn_listviewdialog.getText().toString(), familia.getText().toString(),
                                                       codigo_prod.getText().toString(), codigo_fam.getText().toString(), switchTexter(swApa.isChecked()), switchTexter(swSa.isChecked()),
                                                       switchTexter(swCo.isChecked()), switchTexter(swAro.isChecked()), observaciones_sabor.getText().toString(),
                                                       humedad.getText().toString(), ph.getText().toString(), grasa_total.getText().toString(), humRem.getText().toString(),
                                                       phRem.getText().toString(), grasRem.getText().toString(), switchTexter(swRem.isChecked()), observaciones_apariencia.getText().toString(),
                                                       observaciones_color.getText().toString(), getUntabilidad(), observaciones_untabilidad.getText().toString(),Lote.getText().toString()
                                               );
                                               if (exitoso) {

                                                   Alerta(getResources().getString(R.string.Alerta_Actualizado));

                                               } else {
                                                   Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                                               }
                                           } else {


                                               boolean exitoso = con.DAORequesonLab(FechaH.Hoy_hora(), Lote.getText().toString(), btn_listviewdialog.getText().toString(), familia.getText().toString(),
                                                       codigo_prod.getText().toString(), codigo_fam.getText().toString(), switchTexter(swApa.isChecked()), switchTexter(swSa.isChecked()),
                                                       switchTexter(swCo.isChecked()), switchTexter(swAro.isChecked()), observaciones_sabor.getText().toString(),
                                                       humedad.getText().toString(), ph.getText().toString(), grasa_total.getText().toString(), humRem.getText().toString(),
                                                       phRem.getText().toString(), grasRem.getText().toString(), switchTexter(swRem.isChecked()), observaciones_apariencia.getText().toString(),
                                                       FechaH.Hoy(), observaciones_color.getText().toString(), getUntabilidad(), observaciones_untabilidad.getText().toString()
                                               );
                                               if (exitoso) {

                                                   Alerta(getResources().getString(R.string.Alerta_Guardado));

                                                   vaciarTodo();
                                               } else {
                                                   Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                                               }
                                           }
                                       }
                                   }
        );

        /*********** Check Boxes **************/
        check1 =(CheckBox)findViewById(R.id.checkBox);
        check1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                check1.setChecked(true);
                check2.setChecked(false);
                check3.setChecked(false);
            }
        });
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

                launchView(0);


            }
        });
        btnBack = (Button)findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(var.isFromRequeson()) {
                    var.setFromAdminRequeson(true);
                    finish();
                    startActivity(new Intent(Requeson_Lab.this, Realizados.class));
                }
                else{
                    finish();
                    startActivity(new Intent(Requeson_Lab.this, Panel_Lab.class));

                }

            }
        });


        if(var.isFromRequeson()){
            //Lote.setEnabled(false);
            llenarValoresBusqueda(var.getLoteRequeson());
        }
        else{
            Guardar.setImageResource(R.drawable.guarda);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_requeson__lab, menu);
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


        String strName = array_sort.get(position);
        codigo_prod.setText(strName.substring(0, strName.indexOf('-')));
        cursor = con.DAOGetCursorTodosFamilias(codigo_prod.getText().toString());

        btn_listviewdialog.setText(strName.substring(strName.indexOf('-') + 1, strName.length()));
        codigo_fam.setText(cursor.getString(cursor.getColumnIndex("codigo_familia")));
        familia.setText(con.DAOGetNombreFamilia(codigo_fam.getText().toString()));











    }

    @Override
    public void onClick(View v) {

    }
    public void launchView(int from)
    {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Requeson_Lab.this);

        Nombre_PT = getProductosArray(con.DAOGetTodosProductos(codigo_fam.getText().toString(),0));





        //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
        final EditText editText = new EditText(Requeson_Lab.this);
        final ListView listview = new ListView(Requeson_Lab.this);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
        array_sort = new ArrayList<String>(Arrays.asList(Nombre_PT));
        LinearLayout layout = new LinearLayout(Requeson_Lab.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText);
        layout.addView(listview);
        myDialog.setView(layout);
        CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Requeson_Lab.this, array_sort);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(Requeson_Lab.this);
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
                listview.setAdapter(new CustomAlertAdapter(Requeson_Lab.this, array_sort));
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
    public String[] getProductosArray(final ArrayList<consultas> genArray)
    {
        for(final consultas con: genArray)
        {

            listaProductos = con.producto;
        }
        return listaProductos;
    }
    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Requeson_Lab.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {
                if(var.isFromRequeson()){
                    var.setFromAdminRequeson(true);
                    finish();
                    startActivity(new Intent(Requeson_Lab.this, Realizados.class));
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
    public String getUntabilidad()
    {

        if (check1.isChecked()){
            return "Bueno";
        }
        else if (check2.isChecked()){
            return "Regular";
        }
        else if (check3.isChecked()){
            return "Malo";
        }
        else
        {return null;}


    }
    public void setUntabilidad(String untabilidad){
        if(untabilidad.equals("Bueno")){
            check1.setChecked(true);

        }
        else if(untabilidad.equals("Regular")){
            check2.setChecked(true);
        }
        else{
            check3.setChecked(true);

        }
    }
    public void llenarValoresBusqueda(String lote){
        cursor = con.DAOLLenarRequesonLab(lote);
        Lote.setText(lote);

        Fecha.setText(cursor.getString(cursor.getColumnIndex("fecha_hoy")));
        btn_listviewdialog.setText(cursor.getString(cursor.getColumnIndex("producto")));
        codigo_prod.setText(cursor.getString(cursor.getColumnIndex("codigo_prod")));
        familia.setText(cursor.getString(cursor.getColumnIndex("familia")));
        codigo_fam.setText(cursor.getString(cursor.getColumnIndex("codigo_fam")));
        observaciones_sabor.setText(cursor.getString(cursor.getColumnIndex("observaciones_sabor")));
        observaciones_apariencia.setText(cursor.getString(cursor.getColumnIndex("observaciones_apariencia")));
        observaciones_color.setText(cursor.getString(cursor.getColumnIndex("observaciones_color")));
        observaciones_untabilidad.setText(cursor.getString(cursor.getColumnIndex("observaciones_untabilidad")));
        humedad.setText(cursor.getString(cursor.getColumnIndex("humedad")));
        grasa_total.setText(cursor.getString(cursor.getColumnIndex("grasa_total")));
        ph.setText(cursor.getString(cursor.getColumnIndex("ph")));
        humRem.setText(cursor.getString(cursor.getColumnIndex("humedad_remuestreo")));
        phRem.setText(cursor.getString(cursor.getColumnIndex("ph_remuestreo")));
        grasRem.setText(cursor.getString(cursor.getColumnIndex("grasa_remuestreo")));
        swApa.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("apariencia"))));
        swCo.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("color"))));
        swSa.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("sabor"))));
        swAro.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("aroma"))));
        swRem.setChecked(textSwithcer(cursor.getString(cursor.getColumnIndex("necesidad_remuestreo"))));
        setUntabilidad(cursor.getString(cursor.getColumnIndex("untabilidad")));
    }
    public void vaciarTodo(){
        Lote.setText("");
        codigo_prod.setText("");
        observaciones_sabor.setText("");
        humedad.setText("");
        ph.setText("");
        grasa_total.setText("");
        humRem.setText("");
        phRem.setText("");
        grasRem.setText("");
        swCo.setChecked(false);
        swSa.setChecked(false);
        swAro.setChecked(false);
        swApa.setChecked(false);
        swRem.setChecked(false);
        btn_listviewdialog.setText("Seleccione un Producto");
        codigo_fam.setText("");
        observaciones_apariencia.setText("");
        check1.setChecked(false);
        check2.setChecked(false);
        check3.setChecked(false);
        observaciones_color.setText("");
        familia.setText(null);
        observaciones_untabilidad.setText(null);

    }

}
