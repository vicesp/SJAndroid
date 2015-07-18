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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import DAO.consultas;
import DTO.Dia_Juliano;
import DTO.Fecha_Hoy;
import DTO.Variables;


public class Detector_Metales extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static Fecha_Hoy FechaH;
    private static Dia_Juliano DiaJ;
    private static consultas con;
    private static Variables var;

    private String Nombre_PT[], codigoProdActual;
    private String [] listaProductos;
    private ArrayList<String> array_sort;
    int textlength=0;
    private AlertDialog myalertDialog=null;
    protected Cursor cursor;

    int prodButton;

    private TextView Fecha;

    private EditText etAC1, etAC2, etAC3, etAC4, etAC5, etAC6, etAC7, etAC8, etAC9, etAC10, etAC11, etAC12, etAC13, etAC14, etAC15;
    private Button btnHR1, btnHR2, btnHR3, btnHR4, btnHR5, btnHR6, btnHR7, btnHR8, btnHR9, btnHR10, btnHR11, btnHR12, btnHR13, btnHR14, btnHR15,btnProd1, btnProd2,
            btnProd3, btnProd4, btnProd5, btnProd6, btnProd7, btnProd8, btnProd9, btnProd10, btnProd11, btnProd12, btnProd13, btnProd14, btnProd15;
    private Switch swPacA1, swPacA2, swPacA3, swPacA4, swPacA5, swPacA6, swPacA7, swPacA8, swPacA9, swPacA10, swPacA11, swPacA12, swPacA13, swPacA14, swPacA15,
            swPacB1, swPacB2, swPacB3, swPacB4, swPacB5, swPacB6, swPacB7, swPacB8, swPacB9, swPacB10, swPacB11, swPacB12, swPacB13, swPacB14, swPacB15, swAI1,
            swAI2, swAI3, swAI4, swAI5, swAI6, swAI7, swAI8, swAI9, swAI10, swAI11, swAI12, swAI13, swAI14, swAI15, swAI16, swAI17, swAI18, swAI19, swAI20,
            swAI21, swAI22, swAI23, swAI24, swAI25, swAI26, swAI27, swAI28, swAI29, swAI30, swAI31, swAI32, swAI33, swAI34, swAI35, swAI36, swAI37, swAI38,
            swAI39, swAI40, swAI41, swAI42, swAI43, swAI44, swAI45, swF1, swF2, swF3, swF4, swF5, swF6, swF7, swF8, swF9, swF10, swF11, swF12, swF13, swF14,
            swF15, swF16, swF17, swF18, swF19, swF20, swF21, swF22, swF23, swF24, swF25, swF26, swF27, swF28, swF29, swF30, swF31, swF32, swF33, swF34, swF35,
            swF36, swF37, swF38, swF39, swF40, swF41, swF42, swF43, swF44, swF45, swNF1, swNF2, swNF3, swNF4, swNF5, swNF6, swNF7, swNF8, swNF9, swNF10, swNF11,
            swNF12, swNF13, swNF14, swNF15, swNF16, swNF17, swNF18, swNF19, swNF20, swNF21, swNF22, swNF23, swNF24, swNF25, swNF26, swNF27, swNF28, swNF29, swNF30, 
            swNF31, swNF32, swNF33, swNF34, swNF35, swNF36, swNF37, swNF38, swNF39, swNF40, swNF41, swNF42, swNF43, swNF44, swNF45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detector_metales);

        FechaH=new Fecha_Hoy();
        DiaJ=new Dia_Juliano();
        con=new consultas();
        var = new Variables();

        /*********** Text Views **************/
        Fecha = (TextView) findViewById(R.id.tvExpFecha);
        Fecha.setText(FechaH.Hoy());

        /***************** Switches ********************/
        swPacA1 = (Switch) findViewById(R.id.swPacA1);
        swPacA2 = (Switch) findViewById(R.id.swPacA2);
        swPacA3 = (Switch) findViewById(R.id.swPacA3);
        swPacA4 = (Switch) findViewById(R.id.swPacA4);
        swPacA5 = (Switch) findViewById(R.id.swPacA5);
        swPacA6 = (Switch) findViewById(R.id.swPacA6);
        swPacA7 = (Switch) findViewById(R.id.swPacA7);
        swPacA8 = (Switch) findViewById(R.id.swPacA8);
        swPacA9 = (Switch) findViewById(R.id.swPacA9);
        swPacA10 = (Switch) findViewById(R.id.swPacA10);
        swPacA11 = (Switch) findViewById(R.id.swPacA11);
        swPacA12 = (Switch) findViewById(R.id.swPacA12);
        swPacA13 = (Switch) findViewById(R.id.swPacA13);
        swPacA14 = (Switch) findViewById(R.id.swPacA14);
        swPacA15 = (Switch) findViewById(R.id.swPacA15);

        swPacB1 = (Switch) findViewById(R.id.swPacB1);
        swPacB2 = (Switch) findViewById(R.id.swPacB2);
        swPacB3 = (Switch) findViewById(R.id.swPacB3);
        swPacB4 = (Switch) findViewById(R.id.swPacB4);
        swPacB5 = (Switch) findViewById(R.id.swPacB5);
        swPacB6 = (Switch) findViewById(R.id.swPacB6);
        swPacB7 = (Switch) findViewById(R.id.swPacB7);
        swPacB8 = (Switch) findViewById(R.id.swPacB8);
        swPacB9 = (Switch) findViewById(R.id.swPacB9);
        swPacB10 = (Switch) findViewById(R.id.swPacB10);
        swPacB11 = (Switch) findViewById(R.id.swPacB11);
        swPacB12 = (Switch) findViewById(R.id.swPacB12);
        swPacB13 = (Switch) findViewById(R.id.swPacB13);
        swPacB14 = (Switch) findViewById(R.id.swPacB14);
        swPacB15 = (Switch) findViewById(R.id.swPacB15);

        swAI1 = (Switch) findViewById(R.id.swAI1);
        swAI2 = (Switch) findViewById(R.id.swAI2);
        swAI3 = (Switch) findViewById(R.id.swAI3);
        swAI4 = (Switch) findViewById(R.id.swAI4);
        swAI5 = (Switch) findViewById(R.id.swAI5);
        swAI6 = (Switch) findViewById(R.id.swAI6);
        swAI7 = (Switch) findViewById(R.id.swAI7);
        swAI8 = (Switch) findViewById(R.id.swAI8);
        swAI9 = (Switch) findViewById(R.id.swAI9);
        swAI10 = (Switch) findViewById(R.id.swAI10);
        swAI11 = (Switch) findViewById(R.id.swAI11);
        swAI12 = (Switch) findViewById(R.id.swAI12);
        swAI13 = (Switch) findViewById(R.id.swAI13);
        swAI14 = (Switch) findViewById(R.id.swAI14);
        swAI15 = (Switch) findViewById(R.id.swAI15);
        swAI16 = (Switch) findViewById(R.id.swAI16);
        swAI17 = (Switch) findViewById(R.id.swAI17);
        swAI18 = (Switch) findViewById(R.id.swAI18);
        swAI19 = (Switch) findViewById(R.id.swAI19);
        swAI20 = (Switch) findViewById(R.id.swAI20);
        swAI21 = (Switch) findViewById(R.id.swAI21);
        swAI22 = (Switch) findViewById(R.id.swAI22);
        swAI23 = (Switch) findViewById(R.id.swAI23);
        swAI24 = (Switch) findViewById(R.id.swAI24);
        swAI25 = (Switch) findViewById(R.id.swAI25);
        swAI26 = (Switch) findViewById(R.id.swAI26);
        swAI27 = (Switch) findViewById(R.id.swAI27);
        swAI28 = (Switch) findViewById(R.id.swAI28);
        swAI29 = (Switch) findViewById(R.id.swAI29);
        swAI30 = (Switch) findViewById(R.id.swAI30);
        swAI31 = (Switch) findViewById(R.id.swAI31);
        swAI32 = (Switch) findViewById(R.id.swAI32);
        swAI33 = (Switch) findViewById(R.id.swAI33);
        swAI34 = (Switch) findViewById(R.id.swAI34);
        swAI35 = (Switch) findViewById(R.id.swAI35);
        swAI36 = (Switch) findViewById(R.id.swAI36);
        swAI37 = (Switch) findViewById(R.id.swAI37);
        swAI38 = (Switch) findViewById(R.id.swAI38);
        swAI39 = (Switch) findViewById(R.id.swAI39);
        swAI40 = (Switch) findViewById(R.id.swAI40);
        swAI41 = (Switch) findViewById(R.id.swAI41);
        swAI42 = (Switch) findViewById(R.id.swAI42);
        swAI43 = (Switch) findViewById(R.id.swAI43);
        swAI44 = (Switch) findViewById(R.id.swAI44);
        swAI45 = (Switch) findViewById(R.id.swAI45);

        swF1 = (Switch) findViewById(R.id.swF1);
        swF2 = (Switch) findViewById(R.id.swF2);
        swF3 = (Switch) findViewById(R.id.swF3);
        swF4 = (Switch) findViewById(R.id.swF4);
        swF5 = (Switch) findViewById(R.id.swF5);
        swF6 = (Switch) findViewById(R.id.swF6);
        swF7 = (Switch) findViewById(R.id.swF7);
        swF8 = (Switch) findViewById(R.id.swF8);
        swF9 = (Switch) findViewById(R.id.swF9);
        swF10 = (Switch) findViewById(R.id.swF10);
        swF11 = (Switch) findViewById(R.id.swF11);
        swF12 = (Switch) findViewById(R.id.swF12);
        swF13 = (Switch) findViewById(R.id.swF13);
        swF14 = (Switch) findViewById(R.id.swF14);
        swF15 = (Switch) findViewById(R.id.swF15);
        swF16 = (Switch) findViewById(R.id.swF16);
        swF17 = (Switch) findViewById(R.id.swF17);
        swF18 = (Switch) findViewById(R.id.swF18);
        swF19 = (Switch) findViewById(R.id.swF19);
        swF20 = (Switch) findViewById(R.id.swF20);
        swF21 = (Switch) findViewById(R.id.swF21);
        swF22 = (Switch) findViewById(R.id.swF22);
        swF23 = (Switch) findViewById(R.id.swF23);
        swF24 = (Switch) findViewById(R.id.swF24);
        swF25 = (Switch) findViewById(R.id.swF25);
        swF26 = (Switch) findViewById(R.id.swF26);
        swF27 = (Switch) findViewById(R.id.swF27);
        swF28 = (Switch) findViewById(R.id.swF28);
        swF29 = (Switch) findViewById(R.id.swF29);
        swF30 = (Switch) findViewById(R.id.swF30);
        swF31 = (Switch) findViewById(R.id.swF31);
        swF32 = (Switch) findViewById(R.id.swF32);
        swF33 = (Switch) findViewById(R.id.swF33);
        swF34 = (Switch) findViewById(R.id.swF34);
        swF35 = (Switch) findViewById(R.id.swF35);
        swF36 = (Switch) findViewById(R.id.swF36);
        swF37 = (Switch) findViewById(R.id.swF37);
        swF38 = (Switch) findViewById(R.id.swF38);
        swF39 = (Switch) findViewById(R.id.swF39);
        swF40 = (Switch) findViewById(R.id.swF40);
        swF41 = (Switch) findViewById(R.id.swF41);
        swF42 = (Switch) findViewById(R.id.swF42);
        swF43 = (Switch) findViewById(R.id.swF43);
        swF44 = (Switch) findViewById(R.id.swF44);
        swF45 = (Switch) findViewById(R.id.swF45);

        swNF1 = (Switch) findViewById(R.id.swNF1);
        swNF2 = (Switch) findViewById(R.id.swNF2);
        swNF3 = (Switch) findViewById(R.id.swNF3);
        swNF4 = (Switch) findViewById(R.id.swNF4);
        swNF5 = (Switch) findViewById(R.id.swNF5);
        swNF6 = (Switch) findViewById(R.id.swNF6);
        swNF7 = (Switch) findViewById(R.id.swNF7);
        swNF8 = (Switch) findViewById(R.id.swNF8);
        swNF9 = (Switch) findViewById(R.id.swNF9);
        swNF10 = (Switch) findViewById(R.id.swNF10);
        swNF11 = (Switch) findViewById(R.id.swNF11);
        swNF12 = (Switch) findViewById(R.id.swNF12);
        swNF13 = (Switch) findViewById(R.id.swNF13);
        swNF14 = (Switch) findViewById(R.id.swNF14);
        swNF15 = (Switch) findViewById(R.id.swNF15);
        swNF16 = (Switch) findViewById(R.id.swNF16);
        swNF17 = (Switch) findViewById(R.id.swNF17);
        swNF18 = (Switch) findViewById(R.id.swNF18);
        swNF19 = (Switch) findViewById(R.id.swNF19);
        swNF20 = (Switch) findViewById(R.id.swNF20);
        swNF21 = (Switch) findViewById(R.id.swNF21);
        swNF22 = (Switch) findViewById(R.id.swNF22);
        swNF23 = (Switch) findViewById(R.id.swNF23);
        swNF24 = (Switch) findViewById(R.id.swNF24);
        swNF25 = (Switch) findViewById(R.id.swNF25);
        swNF26 = (Switch) findViewById(R.id.swNF26);
        swNF27 = (Switch) findViewById(R.id.swNF27);
        swNF28 = (Switch) findViewById(R.id.swNF28);
        swNF29 = (Switch) findViewById(R.id.swNF29);
        swNF30 = (Switch) findViewById(R.id.swNF30);
        swNF31 = (Switch) findViewById(R.id.swNF31);
        swNF32 = (Switch) findViewById(R.id.swNF32);
        swNF33 = (Switch) findViewById(R.id.swNF33);
        swNF34 = (Switch) findViewById(R.id.swNF34);
        swNF35 = (Switch) findViewById(R.id.swNF35);
        swNF36 = (Switch) findViewById(R.id.swNF36);
        swNF37 = (Switch) findViewById(R.id.swNF37);
        swNF38 = (Switch) findViewById(R.id.swNF38);
        swNF39 = (Switch) findViewById(R.id.swNF39);
        swNF40 = (Switch) findViewById(R.id.swNF40);
        swNF41 = (Switch) findViewById(R.id.swNF41);
        swNF42 = (Switch) findViewById(R.id.swNF42);
        swNF43 = (Switch) findViewById(R.id.swNF43);
        swNF44 = (Switch) findViewById(R.id.swNF44);
        swNF45 = (Switch) findViewById(R.id.swNF45);

        //******************    Button    ****************//
        btnHR1=(Button)findViewById(R.id.btnHR1);
        btnHR2=(Button)findViewById(R.id.btnHR2);
        btnHR3=(Button)findViewById(R.id.btnHR3);
        btnHR4=(Button)findViewById(R.id.btnHR4);
        btnHR5=(Button)findViewById(R.id.btnHR5);
        btnHR6=(Button)findViewById(R.id.btnHR6);
        btnHR7=(Button)findViewById(R.id.btnHR7);
        btnHR8=(Button)findViewById(R.id.btnHR8);
        btnHR9=(Button)findViewById(R.id.btnHR9);
        btnHR10=(Button)findViewById(R.id.btnHR10);
        btnHR11=(Button)findViewById(R.id.btnHR11);
        btnHR12=(Button)findViewById(R.id.btnHR12);
        btnHR13=(Button)findViewById(R.id.btnHR13);
        btnHR14=(Button)findViewById(R.id.btnHR14);
        btnHR15=(Button)findViewById(R.id.btnHR15);

        btnHR1.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR1.setText(FechaH.Hora());
        }});
        btnHR2.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR2.setText(FechaH.Hora());
        }});
        btnHR3.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR3.setText(FechaH.Hora());
        }});
        btnHR4.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR4.setText(FechaH.Hora());
        }});
        btnHR5.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR5.setText(FechaH.Hora());
        }});
        btnHR6.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR6.setText(FechaH.Hora());
        }});
        btnHR7.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR7.setText(FechaH.Hora());
        }});
        btnHR8.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR8.setText(FechaH.Hora());
        }});
        btnHR9.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR9.setText(FechaH.Hora());
        }});
        btnHR10.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR10.setText(FechaH.Hora());
        }});
        btnHR11.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR11.setText(FechaH.Hora());
        }});
        btnHR12.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR12.setText(FechaH.Hora());
        }});
        btnHR13.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR13.setText(FechaH.Hora());
        }});
        btnHR14.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR14.setText(FechaH.Hora());
        }});
        btnHR15.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){
            btnHR15.setText(FechaH.Hora());
        }});

        btnProd1=(Button)findViewById(R.id.btnProd1);
        btnProd2=(Button)findViewById(R.id.btnProd2);
        btnProd3=(Button)findViewById(R.id.btnProd3);
        btnProd4=(Button)findViewById(R.id.btnProd4);
        btnProd5=(Button)findViewById(R.id.btnProd5);
        btnProd6=(Button)findViewById(R.id.btnProd6);
        btnProd7=(Button)findViewById(R.id.btnProd7);
        btnProd8=(Button)findViewById(R.id.btnProd8);
        btnProd9=(Button)findViewById(R.id.btnProd9);
        btnProd10=(Button)findViewById(R.id.btnProd10);
        btnProd11=(Button)findViewById(R.id.btnProd11);
        btnProd12=(Button)findViewById(R.id.btnProd12);
        btnProd13=(Button)findViewById(R.id.btnProd13);
        btnProd14=(Button)findViewById(R.id.btnProd14);
        btnProd15=(Button)findViewById(R.id.btnProd15);

        btnProd1.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(1);}});
        btnProd2.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(2);}});
        btnProd3.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(3);}});
        btnProd4.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(4);}});
        btnProd5.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(5);}});
        btnProd6.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(6);}});
        btnProd7.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(7);}});
        btnProd8.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(8);}});
        btnProd9.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(9);}});
        btnProd10.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(10);}});
        btnProd11.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(11);}});
        btnProd12.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(12);}});
        btnProd13.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(13);}});
        btnProd14.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(14);}});
        btnProd15.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v){launchView(15);}});

        /******** Edit Text *******/
        etAC1=(EditText)findViewById(R.id.etAC1);
        etAC2=(EditText)findViewById(R.id.etAC2);
        etAC3=(EditText)findViewById(R.id.etAC3);
        etAC4=(EditText)findViewById(R.id.etAC4);
        etAC5=(EditText)findViewById(R.id.etAC5);
        etAC6=(EditText)findViewById(R.id.etAC6);
        etAC7=(EditText)findViewById(R.id.etAC7);
        etAC8=(EditText)findViewById(R.id.etAC8);
        etAC9=(EditText)findViewById(R.id.etAC9);
        etAC10=(EditText)findViewById(R.id.etAC10);
        etAC11=(EditText)findViewById(R.id.etAC11);
        etAC12=(EditText)findViewById(R.id.etAC12);
        etAC13=(EditText)findViewById(R.id.etAC13);
        etAC14=(EditText)findViewById(R.id.etAC14);
        etAC15=(EditText)findViewById(R.id.etAC15);

        confInicial();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detector__metales, menu);
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
    public void launchView(int from)
    {
        prodButton=from;
        AlertDialog.Builder myDialog = new AlertDialog.Builder(Detector_Metales.this);

        Nombre_PT = getProductosArray(con.DAOGetTodosProductos("",0));


        //Log.i(con.DAOGetProductos().,getResources().getStringArray(R.array.nombre_PT)[0]);
        final EditText editText = new EditText(Detector_Metales.this);
        final ListView listview = new ListView(Detector_Metales.this);
        editText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.abc_ic_search_api_mtrl_alpha, 0, 0, 0);
        array_sort = new ArrayList<String>(Arrays.asList(Nombre_PT));
        LinearLayout layout = new LinearLayout(Detector_Metales.this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(editText);
        layout.addView(listview);
        myDialog.setView(layout);
        CustomAlertAdapter arrayAdapter = new CustomAlertAdapter(Detector_Metales.this, array_sort);
        listview.setAdapter(arrayAdapter);

        listview.setOnItemClickListener(Detector_Metales.this);
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
                listview.setAdapter(new CustomAlertAdapter(Detector_Metales.this, array_sort));
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
    @Override
    public void onItemClick(AdapterView arg0, View arg1, int position, long arg3) {

        myalertDialog.dismiss();
        String strName = array_sort.get(position);

        switch (prodButton) {
            case 1:
                btnProd1.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 2:
                btnProd2.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 3:
                btnProd3.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 4:
                btnProd4.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 5:
                btnProd5.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 6:
                btnProd6.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 7:
                btnProd7.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 8:
                btnProd8.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 9:
                btnProd9.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 10:
                btnProd10.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 11:
                btnProd11.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 12:
                btnProd12.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 13:
                btnProd13.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 14:
                btnProd14.setText(strName.substring(0, strName.indexOf('-')));
                break;
            case 15:
                btnProd15.setText(strName.substring(0, strName.indexOf('-')));
                break;
            default:break;
        }



    }
    @Override
    public void onClick(View v) {

    }
    public void confInicial(){
        btnProd2.setEnabled(false);
        btnProd3.setEnabled(false);
        btnProd4.setEnabled(false);
        btnProd5.setEnabled(false);
        btnProd6.setEnabled(false);
        btnProd7.setEnabled(false);
        btnProd8.setEnabled(false);
        btnProd9.setEnabled(false);
        btnProd10.setEnabled(false);
        btnProd11.setEnabled(false);
        btnProd12.setEnabled(false);
        btnProd13.setEnabled(false);
        btnProd14.setEnabled(false);
        btnProd15.setEnabled(false);

        btnHR2.setEnabled(false);
        btnHR3.setEnabled(false);
        btnHR4.setEnabled(false);
        btnHR5.setEnabled(false);
        btnHR6.setEnabled(false);
        btnHR7.setEnabled(false);
        btnHR8.setEnabled(false);
        btnHR9.setEnabled(false);
        btnHR10.setEnabled(false);
        btnHR11.setEnabled(false);
        btnHR12.setEnabled(false);
        btnHR13.setEnabled(false);
        btnHR14.setEnabled(false);
        btnHR15.setEnabled(false);

        swNF4.setEnabled(false);
        swNF5.setEnabled(false);
        swNF6.setEnabled(false);
        swNF7.setEnabled(false);
        swNF8.setEnabled(false);
        swNF9.setEnabled(false);
        swNF10.setEnabled(false);
        swNF11.setEnabled(false);
        swNF12.setEnabled(false);
        swNF13.setEnabled(false);
        swNF14.setEnabled(false);
        swNF15.setEnabled(false);
        swNF16.setEnabled(false);
        swNF17.setEnabled(false);
        swNF18.setEnabled(false);
        swNF19.setEnabled(false);
        swNF20.setEnabled(false);
        swNF21.setEnabled(false);
        swNF22.setEnabled(false);
        swNF23.setEnabled(false);
        swNF24.setEnabled(false);
        swNF25.setEnabled(false);
        swNF26.setEnabled(false);
        swNF27.setEnabled(false);
        swNF28.setEnabled(false);
        swNF29.setEnabled(false);
        swNF30.setEnabled(false);
        swNF31.setEnabled(false);
        swNF32.setEnabled(false);
        swNF33.setEnabled(false);
        swNF34.setEnabled(false);
        swNF35.setEnabled(false);
        swNF36.setEnabled(false);
        swNF37.setEnabled(false);
        swNF38.setEnabled(false);
        swNF39.setEnabled(false);
        swNF40.setEnabled(false);
        swNF41.setEnabled(false);
        swNF42.setEnabled(false);
        swNF43.setEnabled(false);
        swNF44.setEnabled(false);
        swNF45.setEnabled(false);


        swF4.setEnabled(false);
        swF5.setEnabled(false);
        swF6.setEnabled(false);
        swF7.setEnabled(false);
        swF8.setEnabled(false);
        swF9.setEnabled(false);
        swF10.setEnabled(false);
        swF11.setEnabled(false);
        swF12.setEnabled(false);
        swF13.setEnabled(false);
        swF14.setEnabled(false);
        swF15.setEnabled(false);
        swF16.setEnabled(false);
        swF17.setEnabled(false);
        swF18.setEnabled(false);
        swF19.setEnabled(false);
        swF20.setEnabled(false);
        swF21.setEnabled(false);
        swF22.setEnabled(false);
        swF23.setEnabled(false);
        swF24.setEnabled(false);
        swF25.setEnabled(false);
        swF26.setEnabled(false);
        swF27.setEnabled(false);
        swF28.setEnabled(false);
        swF29.setEnabled(false);
        swF30.setEnabled(false);
        swF31.setEnabled(false);
        swF32.setEnabled(false);
        swF33.setEnabled(false);
        swF34.setEnabled(false);
        swF35.setEnabled(false);
        swF36.setEnabled(false);
        swF37.setEnabled(false);
        swF38.setEnabled(false);
        swF39.setEnabled(false);
        swF40.setEnabled(false);
        swF41.setEnabled(false);
        swF42.setEnabled(false);
        swF43.setEnabled(false);
        swF44.setEnabled(false);
        swF45.setEnabled(false);

        swAI4.setEnabled(false);
        swAI5.setEnabled(false);
        swAI6.setEnabled(false);
        swAI7.setEnabled(false);
        swAI8.setEnabled(false);
        swAI9.setEnabled(false);
        swAI10.setEnabled(false);
        swAI11.setEnabled(false);
        swAI12.setEnabled(false);
        swAI13.setEnabled(false);
        swAI14.setEnabled(false);
        swAI15.setEnabled(false);
        swAI16.setEnabled(false);
        swAI17.setEnabled(false);
        swAI18.setEnabled(false);
        swAI19.setEnabled(false);
        swAI20.setEnabled(false);
        swAI21.setEnabled(false);
        swAI22.setEnabled(false);
        swAI23.setEnabled(false);
        swAI24.setEnabled(false);
        swAI25.setEnabled(false);
        swAI26.setEnabled(false);
        swAI27.setEnabled(false);
        swAI28.setEnabled(false);
        swAI29.setEnabled(false);
        swAI30.setEnabled(false);
        swAI31.setEnabled(false);
        swAI32.setEnabled(false);
        swAI33.setEnabled(false);
        swAI34.setEnabled(false);
        swAI35.setEnabled(false);
        swAI36.setEnabled(false);
        swAI37.setEnabled(false);
        swAI38.setEnabled(false);
        swAI39.setEnabled(false);
        swAI40.setEnabled(false);
        swAI41.setEnabled(false);
        swAI42.setEnabled(false);
        swAI43.setEnabled(false);
        swAI44.setEnabled(false);
        swAI45.setEnabled(false);

        swPacA2.setEnabled(false);
        swPacA3.setEnabled(false);
        swPacA4.setEnabled(false);
        swPacA5.setEnabled(false);
        swPacA6.setEnabled(false);
        swPacA7.setEnabled(false);
        swPacA8.setEnabled(false);
        swPacA9.setEnabled(false);
        swPacA10.setEnabled(false);
        swPacA11.setEnabled(false);
        swPacA12.setEnabled(false);
        swPacA13.setEnabled(false);
        swPacA14.setEnabled(false);
        swPacA15.setEnabled(false);

        swPacB2.setEnabled(false);
        swPacB3.setEnabled(false);
        swPacB4.setEnabled(false);
        swPacB5.setEnabled(false);
        swPacB6.setEnabled(false);
        swPacB7.setEnabled(false);
        swPacB8.setEnabled(false);
        swPacB9.setEnabled(false);
        swPacB10.setEnabled(false);
        swPacB11.setEnabled(false);
        swPacB12.setEnabled(false);
        swPacB13.setEnabled(false);
        swPacB14.setEnabled(false);
        swPacB15.setEnabled(false);
    }
}

