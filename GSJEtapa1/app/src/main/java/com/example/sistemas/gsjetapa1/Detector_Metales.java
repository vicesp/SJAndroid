package com.example.sistemas.gsjetapa1;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.security.Guard;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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

    private TextView Fecha, Usuario;

    private EditText etAC1, etAC2, etAC3, etAC4, etAC5, etAC6, etAC7, etAC8, etAC9, etAC10, etAC11, etAC12, etAC13, etAC14, etAC15, etLote;
    private ImageButton Guardar;
    private Button btnInfo, btnHR1, btnHR2, btnHR3, btnHR4, btnHR5, btnHR6, btnHR7, btnHR8, btnHR9, btnHR10, btnHR11, btnHR12, btnHR13, btnHR14, btnHR15,btnProd1, btnProd2,
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
        Usuario=(TextView)findViewById(R.id.tvNombre);
        Usuario.setText(var.getNombre_usuario());

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
        Guardar=(ImageButton)findViewById(R.id.btnGuardar);
        Guardar.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                if (var.isFromDetector()) {
                    boolean exitoso = con.DAOActualizarDetectorMetales(etLote.getText().toString(), Usuario.getText().toString(), FechaH.Hoy(), FechaH.Hoy_hora(), btnHR1.getText().toString(), btnHR2.getText().toString(),
                            btnHR3.getText().toString(), btnHR4.getText().toString(), btnHR5.getText().toString(), btnHR6.getText().toString(), btnHR7.getText().toString(),
                            btnHR8.getText().toString(), btnHR9.getText().toString(), btnHR10.getText().toString(), btnHR11.getText().toString(), btnHR12.getText().toString(),
                            btnHR13.getText().toString(), btnHR14.getText().toString(), btnHR15.getText().toString(), btnProd1.getText().toString(), btnProd2.getText().toString(),
                            btnProd3.getText().toString(), btnProd4.getText().toString(), btnProd5.getText().toString(), btnProd6.getText().toString(), btnProd7.getText().toString(),
                            btnProd8.getText().toString(), btnProd9.getText().toString(), btnProd10.getText().toString(), btnProd11.getText().toString(), btnProd12.getText().toString(),
                            btnProd13.getText().toString(), btnProd14.getText().toString(), btnProd15.getText().toString(), switchTexter(swPacA1.isChecked()), switchTexter(swPacB1.isChecked()),
                            switchTexter(swPacA2.isChecked()), switchTexter(swPacB2.isChecked()), switchTexter(swPacA3.isChecked()), switchTexter(swPacB3.isChecked()), switchTexter(swPacA4.isChecked()),
                            switchTexter(swPacB4.isChecked()), switchTexter(swPacA5.isChecked()), switchTexter(swPacB5.isChecked()), switchTexter(swPacA6.isChecked()),
                            switchTexter(swPacB6.isChecked()), switchTexter(swPacA7.isChecked()), switchTexter(swPacB7.isChecked()), switchTexter(swPacA8.isChecked()),
                            switchTexter(swPacB8.isChecked()), switchTexter(swPacA9.isChecked()), switchTexter(swPacB9.isChecked()), switchTexter(swPacA10.isChecked()), switchTexter(swPacB10.isChecked()),
                            switchTexter(swPacA11.isChecked()), switchTexter(swPacB11.isChecked()), switchTexter(swPacA12.isChecked()), switchTexter(swPacB12.isChecked()), switchTexter(swPacA13.isChecked()),
                            switchTexter(swPacB13.isChecked()), switchTexter(swPacA14.isChecked()), switchTexter(swPacB14.isChecked()), switchTexter(swPacA15.isChecked()),
                            switchTexter(swPacB15.isChecked()), switchTexter(swAI1.isChecked()), switchTexter(swAI2.isChecked()), switchTexter(swAI3.isChecked()), switchTexter(swAI4.isChecked()),
                            switchTexter(swAI5.isChecked()), switchTexter(swAI6.isChecked()), switchTexter(swAI7.isChecked()), switchTexter(swAI8.isChecked()), switchTexter(swAI9.isChecked()),
                            switchTexter(swAI10.isChecked()), switchTexter(swAI11.isChecked()), switchTexter(swAI12.isChecked()), switchTexter(swAI13.isChecked()), switchTexter(swAI14.isChecked()),
                            switchTexter(swAI15.isChecked()), switchTexter(swAI16.isChecked()), switchTexter(swAI17.isChecked()), switchTexter(swAI18.isChecked()), switchTexter(swAI19.isChecked()),
                            switchTexter(swAI20.isChecked()), switchTexter(swAI21.isChecked()), switchTexter(swAI22.isChecked()), switchTexter(swAI23.isChecked()), switchTexter(swAI24.isChecked()),
                            switchTexter(swAI25.isChecked()), switchTexter(swAI26.isChecked()), switchTexter(swAI27.isChecked()), switchTexter(swAI28.isChecked()), switchTexter(swAI29.isChecked()),
                            switchTexter(swAI30.isChecked()), switchTexter(swAI31.isChecked()), switchTexter(swAI32.isChecked()), switchTexter(swAI33.isChecked()), switchTexter(swAI34.isChecked()),
                            switchTexter(swAI35.isChecked()), switchTexter(swAI36.isChecked()), switchTexter(swAI37.isChecked()), switchTexter(swAI38.isChecked()), switchTexter(swAI39.isChecked()),
                            switchTexter(swAI40.isChecked()), switchTexter(swAI41.isChecked()), switchTexter(swAI42.isChecked()), switchTexter(swAI43.isChecked()),
                            switchTexter(swAI44.isChecked()), switchTexter(swAI45.isChecked()), switchTexter(swF1.isChecked()), switchTexter(swF2.isChecked()), switchTexter(swF3.isChecked()),
                            switchTexter(swF4.isChecked()), switchTexter(swF5.isChecked()), switchTexter(swF6.isChecked()), switchTexter(swF7.isChecked()), switchTexter(swF8.isChecked()),
                            switchTexter(swF9.isChecked()), switchTexter(swF10.isChecked()), switchTexter(swF11.isChecked()), switchTexter(swF12.isChecked()), switchTexter(swF13.isChecked()),
                            switchTexter(swF14.isChecked()), switchTexter(swF15.isChecked()), switchTexter(swF16.isChecked()), switchTexter(swF17.isChecked()), switchTexter(swF18.isChecked()),
                            switchTexter(swF19.isChecked()), switchTexter(swF20.isChecked()), switchTexter(swF21.isChecked()), switchTexter(swF22.isChecked()), switchTexter(swF23.isChecked()),
                            switchTexter(swF24.isChecked()), switchTexter(swF25.isChecked()), switchTexter(swF26.isChecked()), switchTexter(swF27.isChecked()), switchTexter(swF28.isChecked()),
                            switchTexter(swF29.isChecked()), switchTexter(swF30.isChecked()), switchTexter(swF31.isChecked()), switchTexter(swF32.isChecked()), switchTexter(swF33.isChecked()),
                            switchTexter(swF34.isChecked()), switchTexter(swF35.isChecked()), switchTexter(swF36.isChecked()), switchTexter(swF37.isChecked()), switchTexter(swF38.isChecked()),
                            switchTexter(swF39.isChecked()), switchTexter(swF40.isChecked()), switchTexter(swF41.isChecked()), switchTexter(swF42.isChecked()), switchTexter(swF43.isChecked()),
                            switchTexter(swF44.isChecked()), switchTexter(swF45.isChecked()), switchTexter(swNF1.isChecked()), switchTexter(swNF2.isChecked()), switchTexter(swNF3.isChecked()),
                            switchTexter(swNF4.isChecked()), switchTexter(swNF5.isChecked()), switchTexter(swNF6.isChecked()), switchTexter(swNF7.isChecked()), switchTexter(swNF8.isChecked()),
                            switchTexter(swNF9.isChecked()), switchTexter(swNF10.isChecked()), switchTexter(swNF11.isChecked()), switchTexter(swNF12.isChecked()), switchTexter(swNF13.isChecked()),
                            switchTexter(swNF14.isChecked()), switchTexter(swNF15.isChecked()), switchTexter(swNF16.isChecked()), switchTexter(swNF17.isChecked()), switchTexter(swNF18.isChecked()),
                            switchTexter(swNF19.isChecked()), switchTexter(swNF20.isChecked()), switchTexter(swNF21.isChecked()), switchTexter(swNF22.isChecked()), switchTexter(swNF23.isChecked()),
                            switchTexter(swNF24.isChecked()), switchTexter(swNF25.isChecked()), switchTexter(swNF26.isChecked()), switchTexter(swNF27.isChecked()), switchTexter(swNF28.isChecked()),
                            switchTexter(swNF29.isChecked()), switchTexter(swNF30.isChecked()), switchTexter(swNF31.isChecked()), switchTexter(swNF32.isChecked()), switchTexter(swNF33.isChecked()),
                            switchTexter(swNF34.isChecked()), switchTexter(swNF35.isChecked()), switchTexter(swNF36.isChecked()), switchTexter(swNF37.isChecked()), switchTexter(swNF38.isChecked()),
                            switchTexter(swNF39.isChecked()), switchTexter(swNF40.isChecked()), switchTexter(swNF41.isChecked()), switchTexter(swNF42.isChecked()), switchTexter(swNF43.isChecked()),
                            switchTexter(swNF44.isChecked()), switchTexter(swNF45.isChecked()), etAC1.getText().toString(), etAC2.getText().toString(), etAC3.getText().toString(), etAC4.getText().toString(),
                            etAC5.getText().toString(), etAC6.getText().toString(), etAC7.getText().toString(), etAC8.getText().toString(), etAC9.getText().toString(), etAC10.getText().toString(),
                            etAC11.getText().toString(), etAC12.getText().toString(), etAC13.getText().toString(), etAC14.getText().toString(), etAC15.getText().toString(), "", "", "");
                    if (exitoso) {

                        Alerta(getResources().getString(R.string.Alerta_Actualizado));
                        confInicial();
                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoActualizado));
                    }
                } else {
                    boolean exitoso = con.DAODetectorMetales(etLote.getText().toString(), Usuario.getText().toString(), FechaH.Hoy(), FechaH.Hoy_hora(), btnHR1.getText().toString(), btnHR2.getText().toString(),
                            btnHR3.getText().toString(), btnHR4.getText().toString(), btnHR5.getText().toString(), btnHR6.getText().toString(), btnHR7.getText().toString(),
                            btnHR8.getText().toString(), btnHR9.getText().toString(), btnHR10.getText().toString(), btnHR11.getText().toString(), btnHR12.getText().toString(),
                            btnHR13.getText().toString(), btnHR14.getText().toString(), btnHR15.getText().toString(), btnProd1.getText().toString(), btnProd2.getText().toString(),
                            btnProd3.getText().toString(), btnProd4.getText().toString(), btnProd5.getText().toString(), btnProd6.getText().toString(), btnProd7.getText().toString(),
                            btnProd8.getText().toString(), btnProd9.getText().toString(), btnProd10.getText().toString(), btnProd11.getText().toString(), btnProd12.getText().toString(),
                            btnProd13.getText().toString(), btnProd14.getText().toString(), btnProd15.getText().toString(), switchTexter(swPacA1.isChecked()), switchTexter(swPacB1.isChecked()),
                            switchTexter(swPacA2.isChecked()), switchTexter(swPacB2.isChecked()), switchTexter(swPacA3.isChecked()), switchTexter(swPacB3.isChecked()), switchTexter(swPacA4.isChecked()),
                            switchTexter(swPacB4.isChecked()), switchTexter(swPacA5.isChecked()), switchTexter(swPacB5.isChecked()), switchTexter(swPacA6.isChecked()),
                            switchTexter(swPacB6.isChecked()), switchTexter(swPacA7.isChecked()), switchTexter(swPacB7.isChecked()), switchTexter(swPacA8.isChecked()),
                            switchTexter(swPacB8.isChecked()), switchTexter(swPacA9.isChecked()), switchTexter(swPacB9.isChecked()), switchTexter(swPacA10.isChecked()), switchTexter(swPacB10.isChecked()),
                            switchTexter(swPacA11.isChecked()), switchTexter(swPacB11.isChecked()), switchTexter(swPacA12.isChecked()), switchTexter(swPacB12.isChecked()), switchTexter(swPacA13.isChecked()),
                            switchTexter(swPacB13.isChecked()), switchTexter(swPacA14.isChecked()), switchTexter(swPacB14.isChecked()), switchTexter(swPacA15.isChecked()),
                            switchTexter(swPacB15.isChecked()), switchTexter(swAI1.isChecked()), switchTexter(swAI2.isChecked()), switchTexter(swAI3.isChecked()), switchTexter(swAI4.isChecked()),
                            switchTexter(swAI5.isChecked()), switchTexter(swAI6.isChecked()), switchTexter(swAI7.isChecked()), switchTexter(swAI8.isChecked()), switchTexter(swAI9.isChecked()),
                            switchTexter(swAI10.isChecked()), switchTexter(swAI11.isChecked()), switchTexter(swAI12.isChecked()), switchTexter(swAI13.isChecked()), switchTexter(swAI14.isChecked()),
                            switchTexter(swAI15.isChecked()), switchTexter(swAI16.isChecked()), switchTexter(swAI17.isChecked()), switchTexter(swAI18.isChecked()), switchTexter(swAI19.isChecked()),
                            switchTexter(swAI20.isChecked()), switchTexter(swAI21.isChecked()), switchTexter(swAI22.isChecked()), switchTexter(swAI23.isChecked()), switchTexter(swAI24.isChecked()),
                            switchTexter(swAI25.isChecked()), switchTexter(swAI26.isChecked()), switchTexter(swAI27.isChecked()), switchTexter(swAI28.isChecked()), switchTexter(swAI29.isChecked()),
                            switchTexter(swAI30.isChecked()), switchTexter(swAI31.isChecked()), switchTexter(swAI32.isChecked()), switchTexter(swAI33.isChecked()), switchTexter(swAI34.isChecked()),
                            switchTexter(swAI35.isChecked()), switchTexter(swAI36.isChecked()), switchTexter(swAI37.isChecked()), switchTexter(swAI38.isChecked()), switchTexter(swAI39.isChecked()),
                            switchTexter(swAI40.isChecked()), switchTexter(swAI41.isChecked()), switchTexter(swAI42.isChecked()), switchTexter(swAI43.isChecked()),
                            switchTexter(swAI44.isChecked()), switchTexter(swAI45.isChecked()), switchTexter(swF1.isChecked()), switchTexter(swF2.isChecked()), switchTexter(swF3.isChecked()),
                            switchTexter(swF4.isChecked()), switchTexter(swF5.isChecked()), switchTexter(swF6.isChecked()), switchTexter(swF7.isChecked()), switchTexter(swF8.isChecked()),
                            switchTexter(swF9.isChecked()), switchTexter(swF10.isChecked()), switchTexter(swF11.isChecked()), switchTexter(swF12.isChecked()), switchTexter(swF13.isChecked()),
                            switchTexter(swF14.isChecked()), switchTexter(swF15.isChecked()), switchTexter(swF16.isChecked()), switchTexter(swF17.isChecked()), switchTexter(swF18.isChecked()),
                            switchTexter(swF19.isChecked()), switchTexter(swF20.isChecked()), switchTexter(swF21.isChecked()), switchTexter(swF22.isChecked()), switchTexter(swF23.isChecked()),
                            switchTexter(swF24.isChecked()), switchTexter(swF25.isChecked()), switchTexter(swF26.isChecked()), switchTexter(swF27.isChecked()), switchTexter(swF28.isChecked()),
                            switchTexter(swF29.isChecked()), switchTexter(swF30.isChecked()), switchTexter(swF31.isChecked()), switchTexter(swF32.isChecked()), switchTexter(swF33.isChecked()),
                            switchTexter(swF34.isChecked()), switchTexter(swF35.isChecked()), switchTexter(swF36.isChecked()), switchTexter(swF37.isChecked()), switchTexter(swF38.isChecked()),
                            switchTexter(swF39.isChecked()), switchTexter(swF40.isChecked()), switchTexter(swF41.isChecked()), switchTexter(swF42.isChecked()), switchTexter(swF43.isChecked()),
                            switchTexter(swF44.isChecked()), switchTexter(swF45.isChecked()), switchTexter(swNF1.isChecked()), switchTexter(swNF2.isChecked()), switchTexter(swNF3.isChecked()),
                            switchTexter(swNF4.isChecked()), switchTexter(swNF5.isChecked()), switchTexter(swNF6.isChecked()), switchTexter(swNF7.isChecked()), switchTexter(swNF8.isChecked()),
                            switchTexter(swNF9.isChecked()), switchTexter(swNF10.isChecked()), switchTexter(swNF11.isChecked()), switchTexter(swNF12.isChecked()), switchTexter(swNF13.isChecked()),
                            switchTexter(swNF14.isChecked()), switchTexter(swNF15.isChecked()), switchTexter(swNF16.isChecked()), switchTexter(swNF17.isChecked()), switchTexter(swNF18.isChecked()),
                            switchTexter(swNF19.isChecked()), switchTexter(swNF20.isChecked()), switchTexter(swNF21.isChecked()), switchTexter(swNF22.isChecked()), switchTexter(swNF23.isChecked()),
                            switchTexter(swNF24.isChecked()), switchTexter(swNF25.isChecked()), switchTexter(swNF26.isChecked()), switchTexter(swNF27.isChecked()), switchTexter(swNF28.isChecked()),
                            switchTexter(swNF29.isChecked()), switchTexter(swNF30.isChecked()), switchTexter(swNF31.isChecked()), switchTexter(swNF32.isChecked()), switchTexter(swNF33.isChecked()),
                            switchTexter(swNF34.isChecked()), switchTexter(swNF35.isChecked()), switchTexter(swNF36.isChecked()), switchTexter(swNF37.isChecked()), switchTexter(swNF38.isChecked()),
                            switchTexter(swNF39.isChecked()), switchTexter(swNF40.isChecked()), switchTexter(swNF41.isChecked()), switchTexter(swNF42.isChecked()), switchTexter(swNF43.isChecked()),
                            switchTexter(swNF44.isChecked()), switchTexter(swNF45.isChecked()), etAC1.getText().toString(), etAC2.getText().toString(), etAC3.getText().toString(), etAC4.getText().toString(),
                            etAC5.getText().toString(), etAC6.getText().toString(), etAC7.getText().toString(), etAC8.getText().toString(), etAC9.getText().toString(), etAC10.getText().toString(),
                            etAC11.getText().toString(), etAC12.getText().toString(), etAC13.getText().toString(), etAC14.getText().toString(), etAC15.getText().toString(), "", "", "");
                    if (exitoso) {

                        Alerta(getResources().getString(R.string.Alerta_Guardado));
                        confInicial();
                    } else {
                        Alerta(getResources().getString(R.string.Alerta_NoGuardado));
                    }
                }

            }
        });

        btnInfo=(Button)findViewById(R.id.btnInfo);
        btnInfo.setOnClickListener(new View.OnClickListener(){@Override
        public void onClick(View v){
            startActivity(new Intent(Detector_Metales.this, Info_Detector.class));

        }
        });

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

        btnHR1.setFocusable(true);
        //btnHR1.setFocusableInTouchMode(true);
        //btnHR1.requestFocus();
        btnHR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR1.setText(FechaH.Hora());
                btnHR1.setEnabled(false);
                btnHR2.setEnabled(true);
                btnProd2.setEnabled(true);
                etAC2.setEnabled(true);
                swPacA2.setEnabled(true);
                swPacB2.setEnabled(true);
                swAI4.setEnabled(true);
                swAI5.setEnabled(true);
                swAI6.setEnabled(true);
                swF4.setEnabled(true);
                swF5.setEnabled(true);
                swF6.setEnabled(true);
                swNF4.setEnabled(true);
                swNF5.setEnabled(true);
                swNF6.setEnabled(true);
                etAC1.requestFocus();

                if (var.isFromDetector()) {
                    timeSetter(1);
                    btnHR1.setEnabled(true);
                }

            }
        });

        btnHR2.setFocusable(true);
        //btnHR2.setFocusableInTouchMode(true);
        //btnHR2.requestFocus();
        btnHR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnHR2.setText(FechaH.Hora());
                btnHR2.setEnabled(false);
                btnHR3.setEnabled(true);
                btnProd3.setEnabled(true);
                etAC3.setEnabled(true);
                swPacA3.setEnabled(true);
                swPacB3.setEnabled(true);
                swAI7.setEnabled(true);
                swAI8.setEnabled(true);
                swAI9.setEnabled(true);
                swF7.setEnabled(true);
                swF8.setEnabled(true);
                swF9.setEnabled(true);
                swNF7.setEnabled(true);
                swNF8.setEnabled(true);
                swNF9.setEnabled(true);
                etAC2.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(2);
                    btnHR2.setEnabled(true);
                }
            }
        });
        btnHR3.setFocusable(true);
        //btnHR3.setFocusableInTouchMode(true);
        //btnHR3.requestFocus();
        btnHR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR3.setText(FechaH.Hora());
                btnHR3.setEnabled(false);
                btnHR4.setEnabled(true);
                btnProd4.setEnabled(true);
                etAC4.setEnabled(true);
                swPacA4.setEnabled(true);
                swPacB4.setEnabled(true);
                swAI10.setEnabled(true);
                swAI11.setEnabled(true);
                swAI12.setEnabled(true);
                swF10.setEnabled(true);
                swF11.setEnabled(true);
                swF12.setEnabled(true);
                swNF10.setEnabled(true);
                swNF11.setEnabled(true);
                swNF12.setEnabled(true);
                etAC3.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(3);
                    btnHR3.setEnabled(true);
                }
            }
        });
        btnHR4.setFocusable(true);
        //btnHR4.setFocusableInTouchMode(true);
        //btnHR4.requestFocus();
        btnHR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR4.setText(FechaH.Hora());
                btnHR4.setEnabled(false);
                btnHR5.setEnabled(true);
                btnProd5.setEnabled(true);
                etAC5.setEnabled(true);
                swPacA5.setEnabled(true);
                swPacB5.setEnabled(true);
                swAI13.setEnabled(true);
                swAI14.setEnabled(true);
                swAI15.setEnabled(true);
                swF13.setEnabled(true);
                swF14.setEnabled(true);
                swF15.setEnabled(true);
                swNF13.setEnabled(true);
                swNF14.setEnabled(true);
                swNF15.setEnabled(true);
                etAC4.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(4);
                    btnHR4.setEnabled(true);
                }
            }
        });
        btnHR5.setFocusable(true);
        //btnHR5.setFocusableInTouchMode(true);
        //btnHR5.requestFocus();
        btnHR5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR5.setText(FechaH.Hora());
                btnHR5.setEnabled(false);
                btnHR6.setEnabled(true);
                btnProd6.setEnabled(true);
                etAC6.setEnabled(true);
                swPacA6.setEnabled(true);
                swPacB6.setEnabled(true);
                swAI16.setEnabled(true);
                swAI17.setEnabled(true);
                swAI18.setEnabled(true);
                swF16.setEnabled(true);
                swF17.setEnabled(true);
                swF18.setEnabled(true);
                swNF16.setEnabled(true);
                swNF17.setEnabled(true);
                swNF18.setEnabled(true);
                etAC5.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(5);
                    btnHR5.setEnabled(true);
                }
            }
        });
        btnHR6.setFocusable(true);
        //btnHR6.setFocusableInTouchMode(true);
        //btnHR6.requestFocus();
        btnHR6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR6.setText(FechaH.Hora());
                btnHR6.setEnabled(false);
                btnHR7.setEnabled(true);
                btnProd7.setEnabled(true);
                etAC7.setEnabled(true);
                swPacA7.setEnabled(true);
                swPacB7.setEnabled(true);
                swAI19.setEnabled(true);
                swAI20.setEnabled(true);
                swAI21.setEnabled(true);
                swF19.setEnabled(true);
                swF20.setEnabled(true);
                swF21.setEnabled(true);
                swNF19.setEnabled(true);
                swNF20.setEnabled(true);
                swNF21.setEnabled(true);
                etAC6.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(6);
                    btnHR6.setEnabled(true);
                }
            }
        });
        btnHR7.setFocusable(true);
        //btnHR7.setFocusableInTouchMode(true);
        //btnHR7.requestFocus();
        btnHR7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR7.setText(FechaH.Hora());
                btnHR7.setEnabled(false);
                btnHR8.setEnabled(true);
                btnProd8.setEnabled(true);
                etAC8.setEnabled(true);
                swPacA8.setEnabled(true);
                swPacB8.setEnabled(true);
                swAI22.setEnabled(true);
                swAI23.setEnabled(true);
                swAI24.setEnabled(true);
                swF22.setEnabled(true);
                swF23.setEnabled(true);
                swF24.setEnabled(true);
                swNF22.setEnabled(true);
                swNF23.setEnabled(true);
                swNF24.setEnabled(true);
                etAC7.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(7);
                    btnHR7.setEnabled(true);
                }
            }
        });
        btnHR8.setFocusable(true);
        //btnHR8.setFocusableInTouchMode(true);
        //btnHR8.requestFocus();
        btnHR8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR8.setText(FechaH.Hora());
                btnHR8.setEnabled(false);
                btnHR9.setEnabled(true);
                btnProd9.setEnabled(true);
                etAC9.setEnabled(true);
                swPacA9.setEnabled(true);
                swPacB9.setEnabled(true);
                swAI25.setEnabled(true);
                swAI26.setEnabled(true);
                swAI27.setEnabled(true);
                swF25.setEnabled(true);
                swF26.setEnabled(true);
                swF27.setEnabled(true);
                swNF25.setEnabled(true);
                swNF26.setEnabled(true);
                swNF27.setEnabled(true);
                etAC8.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(8);
                    btnHR8.setEnabled(true);
                }

            }
        });
        btnHR9.setFocusable(true);
        //btnHR9.setFocusableInTouchMode(true);
        //btnHR9.requestFocus();
        btnHR9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR9.setText(FechaH.Hora());
                btnHR9.setEnabled(false);
                btnHR10.setEnabled(true);
                btnProd10.setEnabled(true);
                etAC10.setEnabled(true);
                swPacA10.setEnabled(true);
                swPacB10.setEnabled(true);
                swAI28.setEnabled(true);
                swAI29.setEnabled(true);
                swAI30.setEnabled(true);
                swF28.setEnabled(true);
                swF29.setEnabled(true);
                swF30.setEnabled(true);
                swNF28.setEnabled(true);
                swNF29.setEnabled(true);
                swNF30.setEnabled(true);
                etAC9.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(9);
                    btnHR9.setEnabled(true);
                }
            }
        });
        btnHR10.setFocusable(true);
        //btnHR10.setFocusableInTouchMode(true);
        //btnHR10.requestFocus();
        btnHR10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR10.setText(FechaH.Hora());
                btnHR10.setEnabled(false);
                btnHR11.setEnabled(true);
                btnProd11.setEnabled(true);
                etAC11.setEnabled(true);
                swPacA11.setEnabled(true);
                swPacB11.setEnabled(true);
                swAI31.setEnabled(true);
                swAI32.setEnabled(true);
                swAI33.setEnabled(true);
                swF31.setEnabled(true);
                swF32.setEnabled(true);
                swF33.setEnabled(true);
                swNF31.setEnabled(true);
                swNF32.setEnabled(true);
                swNF33.setEnabled(true);
                etAC10.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(10);
                    btnHR10.setEnabled(true);
                }
            }
        });
        btnHR11.setFocusable(true);
        //btnHR11.setFocusableInTouchMode(true);
        //btnHR11.requestFocus();
        btnHR11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR11.setText(FechaH.Hora());
                btnHR11.setEnabled(false);
                btnHR12.setEnabled(true);
                btnProd12.setEnabled(true);
                etAC12.setEnabled(true);
                swPacA12.setEnabled(true);
                swPacB12.setEnabled(true);
                swAI34.setEnabled(true);
                swAI35.setEnabled(true);
                swAI36.setEnabled(true);
                swF34.setEnabled(true);
                swF35.setEnabled(true);
                swF36.setEnabled(true);
                swNF34.setEnabled(true);
                swNF35.setEnabled(true);
                swNF36.setEnabled(true);
                etAC11.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(11);
                    btnHR11.setEnabled(true);
                }
            }
        });
        btnHR12.setFocusable(true);
        // btnHR12.setFocusableInTouchMode(true);
        //btnHR12.requestFocus();
        btnHR12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR12.setText(FechaH.Hora());
                btnHR12.setEnabled(false);
                btnHR13.setEnabled(true);
                btnProd13.setEnabled(true);
                etAC13.setEnabled(true);
                swPacA13.setEnabled(true);
                swPacB13.setEnabled(true);
                swAI37.setEnabled(true);
                swAI38.setEnabled(true);
                swAI39.setEnabled(true);
                swF37.setEnabled(true);
                swF38.setEnabled(true);
                swF39.setEnabled(true);
                swNF37.setEnabled(true);
                swNF38.setEnabled(true);
                swNF39.setEnabled(true);
                etAC12.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(12);
                    btnHR12.setEnabled(true);
                }
            }
        });
        btnHR13.setFocusable(true);
        // btnHR13.setFocusableInTouchMode(true);
        //btnHR13.requestFocus();
        btnHR13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR13.setText(FechaH.Hora());
                btnHR13.setEnabled(false);
                btnHR14.setEnabled(true);
                btnProd14.setEnabled(true);
                etAC14.setEnabled(true);
                swPacA14.setEnabled(true);
                swPacB14.setEnabled(true);
                swAI40.setEnabled(true);
                swAI41.setEnabled(true);
                swAI42.setEnabled(true);
                swF40.setEnabled(true);
                swF41.setEnabled(true);
                swF42.setEnabled(true);
                swNF40.setEnabled(true);
                swNF41.setEnabled(true);
                swNF42.setEnabled(true);
                etAC13.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(13);
                    btnHR13.setEnabled(true);
                }
            }
        });
        btnHR14.setFocusable(true);
        //btnHR14.setFocusableInTouchMode(true);
        //btnHR14.requestFocus();
        btnHR14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR14.setText(FechaH.Hora());
                btnHR14.setEnabled(false);
                btnHR15.setEnabled(true);
                btnProd15.setEnabled(true);
                etAC15.setEnabled(true);
                swPacA15.setEnabled(true);
                swPacB15.setEnabled(true);
                swAI43.setEnabled(true);
                swAI44.setEnabled(true);
                swAI45.setEnabled(true);
                swF43.setEnabled(true);
                swF44.setEnabled(true);
                swF45.setEnabled(true);
                swNF43.setEnabled(true);
                swNF44.setEnabled(true);
                swNF45.setEnabled(true);
                etAC14.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(14);
                    btnHR14.setEnabled(true);
                }
            }
        });
        btnHR15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHR15.setText(FechaH.Hora());
                btnHR15.setEnabled(false);
                etAC15.requestFocus();
                if (var.isFromDetector()) {
                    timeSetter(15);
                    btnHR15.setEnabled(true);
                }


            }
        });

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

        btnProd15 = (Button) findViewById(R.id.btnProd15);


        btnProd1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(1);
                etAC1.requestFocus();
            }
        });
        btnProd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(2);
                etAC2.requestFocus();
            }
        });
        btnProd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(3);
                etAC3.requestFocus();
            }
        });
        btnProd4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(4);
                etAC4.requestFocus();
            }
        });
        btnProd5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(5);
                etAC5.requestFocus();
            }
        });
        btnProd6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(6);
                etAC6.requestFocus();
            }
        });
        btnProd7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(7);
                etAC7.requestFocus();
            }
        });
        btnProd8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(8);
                etAC8.requestFocus();
            }
        });
        btnProd9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(9);
                etAC9.requestFocus();
            }
        });
        btnProd10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(10);
                etAC10.requestFocus();
            }
        });
        btnProd11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(11);
                etAC11.requestFocus();
            }
        });
        btnProd12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(12);
                etAC12.requestFocus();
            }
        });
        btnProd13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(13);
                etAC13.requestFocus();
            }
        });
        btnProd14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(14);
                etAC14.requestFocus();
            }
        });
        btnProd15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchView(15);
                etAC15.requestFocus();

            }
        });



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
        etLote=(EditText)findViewById(R.id.etLote);


        etAC1.setFocusable(true);
        etAC2.setFocusable(true);
        etAC3.setFocusable(true);
        etAC4.setFocusable(true);
        etAC5.setFocusable(true);
        etAC6.setFocusable(true);
        etAC7.setFocusable(true);
        etAC8.setFocusable(true);
        etAC9.setFocusable(true);
        etAC10.setFocusable(true);
        etAC11.setFocusable(true);
        etAC12.setFocusable(true);
        etAC13.setFocusable(true);
        etAC14.setFocusable(true);
        etAC15.setFocusable(true);
        etLote.setFocusable(true);


        if(var.isFromDetector()){
            llenarValoresBusqueda(var.getLoteDetector());

        }
        else {
            Guardar.setImageResource(R.drawable.guarda);
            confInicial();
        }

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

        etAC2.setEnabled(false);
        etAC3.setEnabled(false);
        etAC4.setEnabled(false);
        etAC5.setEnabled(false);
        etAC6.setEnabled(false);
        etAC7.setEnabled(false);
        etAC8.setEnabled(false);
        etAC9.setEnabled(false);
        etAC10.setEnabled(false);
        etAC11.setEnabled(false);
        etAC12.setEnabled(false);
        etAC13.setEnabled(false);
        etAC14.setEnabled(false);
        etAC15.setEnabled(false);

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
    public boolean textSwitcher(String affirmation)
    {
        if(affirmation.equals("si"))
        {
            return true;
        }
        else{return false;}

    }
    public void Alerta(String mensaje){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Detector_Metales.this);

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(mensaje);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {

            }

        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void llenarValoresBusqueda(String lote){

        cursor = con.DAOLLenarDetector(lote);
        etLote.setText(lote);
        btnHR1.setText(cursor.getString(cursor.getColumnIndex("hr_campo1")));
        btnHR2.setText(cursor.getString(cursor.getColumnIndex("hr_campo2")));
        btnHR3.setText(cursor.getString(cursor.getColumnIndex("hr_campo3")));
        btnHR4.setText(cursor.getString(cursor.getColumnIndex("hr_campo4")));
        btnHR5.setText(cursor.getString(cursor.getColumnIndex("hr_campo5")));
        btnHR6.setText(cursor.getString(cursor.getColumnIndex("hr_campo6")));
        btnHR7.setText(cursor.getString(cursor.getColumnIndex("hr_campo7")));
        btnHR8.setText(cursor.getString(cursor.getColumnIndex("hr_campo8")));
        btnHR9.setText(cursor.getString(cursor.getColumnIndex("hr_campo9")));
        btnHR10.setText(cursor.getString(cursor.getColumnIndex("hr_campo10")));
        btnHR11.setText(cursor.getString(cursor.getColumnIndex("hr_campo11")));
        btnHR12.setText(cursor.getString(cursor.getColumnIndex("hr_campo12")));
        btnHR13.setText(cursor.getString(cursor.getColumnIndex("hr_campo13")));
        btnHR14.setText(cursor.getString(cursor.getColumnIndex("hr_campo14")));
        btnHR15.setText(cursor.getString(cursor.getColumnIndex("hr_campo15")));
        btnProd1.setText(cursor.getString(cursor.getColumnIndex("prod_campo1")));
        btnProd2.setText(cursor.getString(cursor.getColumnIndex("prod_campo2")));
        btnProd3.setText(cursor.getString(cursor.getColumnIndex("prod_campo3")));
        btnProd4.setText(cursor.getString(cursor.getColumnIndex("prod_campo4")));
        btnProd5.setText(cursor.getString(cursor.getColumnIndex("prod_campo5")));
        btnProd6.setText(cursor.getString(cursor.getColumnIndex("prod_campo6")));
        btnProd7.setText(cursor.getString(cursor.getColumnIndex("prod_campo7")));
        btnProd8.setText(cursor.getString(cursor.getColumnIndex("prod_campo8")));
        btnProd9.setText(cursor.getString(cursor.getColumnIndex("prod_campo9")));
        btnProd10.setText(cursor.getString(cursor.getColumnIndex("prod_campo10")));
        btnProd11.setText(cursor.getString(cursor.getColumnIndex("prod_campo11")));
        btnProd12.setText(cursor.getString(cursor.getColumnIndex("prod_campo12")));
        btnProd13.setText(cursor.getString(cursor.getColumnIndex("prod_campo13")));
        btnProd14.setText(cursor.getString(cursor.getColumnIndex("prod_campo14")));
        btnProd15.setText(cursor.getString(cursor.getColumnIndex("prod_campo15")));
        etAC1.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo1")));
        etAC2.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo2")));
        etAC3.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo3")));
        etAC4.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo4")));
        etAC5.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo5")));
        etAC6.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo6")));
        etAC7.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo7")));
        etAC8.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo8")));
        etAC9.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo9")));
        etAC10.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo10")));
        etAC11.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo11")));
        etAC12.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo12")));
        etAC13.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo13")));
        etAC14.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo14")));
        etAC15.setText(cursor.getString(cursor.getColumnIndex("accion_correctiva_campo15")));
        swAI1.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo1"))));
        swAI2.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo2"))));
        swAI3.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo3"))));
        swAI4.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo4"))));
        swAI5.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo5"))));
        swAI6.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo6"))));
        swAI7.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo7"))));
        swAI8.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo8"))));
        swAI9.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo9"))));
        swAI10.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo10"))));
        swAI11.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo11"))));
        swAI12.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo12"))));
        swAI13.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo13"))));
        swAI14.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo14"))));
        swAI15.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo15"))));
        swAI16.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo16"))));
        swAI17.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo17"))));
        swAI18.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo18"))));
        swAI19.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo19"))));
        swAI20.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo20"))));
        swAI21.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo21"))));
        swAI22.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo22"))));
        swAI23.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo23"))));
        swAI24.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo24"))));
        swAI25.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo25"))));
        swAI26.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo26"))));
        swAI27.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo27"))));
        swAI28.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo28"))));
        swAI29.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo29"))));
        swAI30.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo30"))));
        swAI31.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo31"))));
        swAI32.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo32"))));
        swAI33.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo33"))));
        swAI34.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo34"))));
        swAI35.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo35"))));
        swAI36.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo36"))));
        swAI37.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo37"))));
        swAI38.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo38"))));
        swAI39.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo39"))));
        swAI40.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo40"))));
        swAI41.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo41"))));
        swAI42.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo42"))));
        swAI43.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo43"))));
        swAI44.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo44"))));
        swAI45.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ac_inox_campo45"))));
        swF1.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo1"))));
        swF2.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo2"))));
        swF3.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo3"))));
        swF4.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo4"))));
        swF5.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo5"))));
        swF6.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo6"))));
        swF7.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo7"))));
        swF8.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo8"))));
        swF9.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo9"))));
        swF10.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo10"))));
        swF11.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo11"))));
        swF12.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo12"))));
        swF13.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo13"))));
        swF14.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo14"))));
        swF15.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo15"))));
        swF16.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo16"))));
        swF17.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo17"))));
        swF18.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo18"))));
        swF19.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo19"))));
        swF20.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo20"))));
        swF21.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo21"))));
        swF22.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo22"))));
        swF23.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo23"))));
        swF24.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo24"))));
        swF25.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo25"))));
        swF26.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo26"))));
        swF27.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo27"))));
        swF28.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo28"))));
        swF29.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo29"))));
        swF30.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo30"))));
        swF31.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo31"))));
        swF32.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo32"))));
        swF33.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo33"))));
        swF34.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo34"))));
        swF35.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo35"))));
        swF36.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo36"))));
        swF37.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo37"))));
        swF38.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo38"))));
        swF39.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo39"))));
        swF40.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo40"))));
        swF41.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo41"))));
        swF42.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo42"))));
        swF43.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo43"))));
        swF44.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo44"))));
        swF45.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("ferroso_campo45"))));
        swNF1.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo1"))));
        swNF2.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo2"))));
        swNF3.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo3"))));
        swNF4.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo4"))));
        swNF5.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo5"))));
        swNF6.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo6"))));
        swNF7.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo7"))));
        swNF8.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo8"))));
        swNF9.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo9"))));
        swNF10.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo10"))));
        swNF11.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo11"))));
        swNF12.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo12"))));
        swNF13.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo13"))));
        swNF14.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo14"))));
        swNF15.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo15"))));
        swNF16.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo16"))));
        swNF17.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo17"))));
        swNF18.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo18"))));
        swNF19.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo19"))));
        swNF20.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo20"))));
        swNF21.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo21"))));
        swNF22.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo22"))));
        swNF23.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo23"))));
        swNF24.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo24"))));
        swNF25.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo25"))));
        swNF26.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo26"))));
        swNF27.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo27"))));
        swNF28.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo28"))));
        swNF29.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo29"))));
        swNF30.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo30"))));
        swNF31.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo31"))));
        swNF32.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo32"))));
        swNF33.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo33"))));
        swNF34.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo34"))));
        swNF35.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo35"))));
        swNF36.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo36"))));
        swNF37.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo37"))));
        swNF38.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo38"))));
        swNF39.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo39"))));
        swNF40.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo40"))));
        swNF41.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo41"))));
        swNF42.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo42"))));
        swNF43.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo43"))));
        swNF44.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo44"))));
        swNF45.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("no_ferroso_campo45"))));
        swPacA1.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo1"))));
        swPacA2.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo2"))));
        swPacA3.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo3"))));
        swPacA4.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo4"))));
        swPacA5.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo5"))));
        swPacA6.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo6"))));
        swPacA7.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo7"))));
        swPacA8.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo8"))));
        swPacA9.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo9"))));
        swPacA10.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo10"))));
        swPacA11.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo11"))));
        swPacA12.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo12"))));
        swPacA13.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo13"))));
        swPacA14.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo14"))));
        swPacA15.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_a_campo15"))));
        swPacB1.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo1"))));
        swPacB2.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo2"))));
        swPacB3.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo3"))));
        swPacB4.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo4"))));
        swPacB5.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo5"))));
        swPacB6.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo6"))));
        swPacB7.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo7"))));
        swPacB8.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo8"))));
        swPacB9.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo9"))));
        swPacB10.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo10"))));
        swPacB11.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo11"))));
        swPacB12.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo12"))));
        swPacB13.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo13"))));
        swPacB14.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo14"))));
        swPacB15.setChecked(textSwitcher(cursor.getString(cursor.getColumnIndex("paquete_b_campo15"))));
    }

    public void timeSetter(final int which) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(Detector_Metales.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int Hour, int Minute) {
                String selectedMinute = "" + Minute;
                String selectedHour = "" + Hour;
                if (Minute < 10) {
                    selectedMinute = "0" + selectedMinute;
                }
                if (Hour < 10) {
                    selectedHour = "0" + selectedHour;
                }
                switch (which) {

                    case 1:
                        btnHR1.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 2:
                        btnHR2.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 3:
                        btnHR3.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 4:
                        btnHR4.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 5:
                        btnHR5.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 6:
                        btnHR6.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 7:
                        btnHR7.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 8:
                        btnHR8.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 9:
                        btnHR9.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 10:
                        btnHR10.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 11:
                        btnHR11.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 12:
                        btnHR12.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 13:
                        btnHR13.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 14:
                        btnHR14.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    case 15:
                        btnHR15.setText("" + selectedHour + ":" + selectedMinute);
                        break;
                    default:
                        break;
                }

            }
        }


                , hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Actualice el tiempo");
        mTimePicker.show();
    }
}

