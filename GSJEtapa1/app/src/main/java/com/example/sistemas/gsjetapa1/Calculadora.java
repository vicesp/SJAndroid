package com.example.sistemas.gsjetapa1;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


public class Calculadora extends ActionBarActivity {
private Button btnZero, btnOne, btnTwo, btnThree, btnFour,
        btnFive, btnSix, btnSeven, btnEight, btnNine,
        btnAC, btnEqual, btnPeriod, btnBackSpace, btnBack;
    private ImageButton btnMinus, btnPlus;
    private Double number=0.0;
    private boolean summing=false;
    private boolean resting=false;
    private boolean summingCheck=false;
    private boolean restingCheck=false;

    private TextView dispView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);



        btnZero=(Button)findViewById(R.id.btnZero);
        btnOne=(Button)findViewById(R.id.btnOne);
        btnTwo=(Button)findViewById(R.id.btnTwo);
        btnThree=(Button)findViewById(R.id.btnThree);
        btnFour=(Button)findViewById(R.id.btnFour);
        btnFive=(Button)findViewById(R.id.btnFive);
        btnSix=(Button)findViewById(R.id.btnSix);
        btnSeven=(Button)findViewById(R.id.btnSeven);
        btnEight=(Button)findViewById(R.id.btnEight);
        btnNine=(Button)findViewById(R.id.btnNine);
        btnAC=(Button)findViewById(R.id.btnAC);
        btnPlus=(ImageButton)findViewById(R.id.btnPlus);
        btnMinus=(ImageButton)findViewById(R.id.btnMinus);
        btnEqual=(Button)findViewById(R.id.btnEqual);
        btnPeriod=(Button)findViewById(R.id.btnPeriod);
        btnBackSpace=(Button)findViewById(R.id.btnBackSpace);
        btnBack=(Button)findViewById(R.id.btnBack);

        dispView=(TextView)findViewById(R.id.dispView);

        btnZero.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
            addNumber("0");
        }});

        btnOne.setOnClickListener(new View.OnClickListener(){
                @Override
        public void onClick(View v){addNumber("1");}});

        btnTwo.setOnClickListener(new View.OnClickListener(){
        @Override
            public void onClick(View v){addNumber("2");}});

        btnThree.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){addNumber("3");}});

        btnFour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){addNumber("4");}});

        btnFive.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){addNumber("5");}});

        btnSix.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){addNumber("6");}});

        btnSeven.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){addNumber("7");}});

        btnEight.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){addNumber("8");}});

        btnNine.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){addNumber("9");}});

        btnAC.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){dispView.setText("0");
}});

        btnPlus.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){
    dispView.setText(dispView.getText().toString()+"+");
}});

        btnMinus.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){
    dispView.setText(dispView.getText().toString()+"-");
}});

        btnEqual.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View v){

    realizeCalculation();


}});

        btnPeriod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){addNumber(".");}});
    btnBackSpace.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!(dispView.getText().toString().equals("0"))&&!(dispView.getText().toString().equals(""))) {
                dispView.setText(dispView.getText().toString().substring(0, dispView.getText().toString().length() - 1));
            }
        }
    });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculadora, menu);
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
public void addNumber(String num) {
    if (summing) {
        dispView.setText("");
        dispView.setText(dispView.getText().toString() + num);
    } else if (resting && restingCheck) {
        dispView.setText("");
        dispView.setText(dispView.getText().toString() + num);
    } else {
        if (!(dispView.getText().toString().equals("0"))) {
            dispView.setText(dispView.getText().toString() + num);
        } else {
            dispView.setText("");
            dispView.setText(dispView.getText().toString() + num);
        }
    }
}
    public void realizeCalculation(){
        number = 0.0;
        ArrayList<String> toSum = new ArrayList<String>();

        String display = dispView.getText().toString();
        dispView.setText("0");
        int x = 0;
        for(int i = 0, n = display.length() ; i < n ; i++) {
            char c = display.charAt(i);
            if (c == '+' || c == '-') {
                toSum.add(display.substring(x, i));
                //display =display.substring(i+1,display.length());
                toSum.add(display.substring(i, i + 1));
                x = i + 1;
            }
        }
        toSum.add(display.substring(x));

        if (toSum.get(0).equals("")){
            toSum.remove(0);
        }

        for (int y = 0, n = toSum.size();y<n;y++){
            if(toSum.get(y).equals("+")){
                if (y==1) {
                    number = number + (Double.parseDouble(toSum.get(y - 1)) + Double.parseDouble(toSum.get(y + 1)));
                }
                else {
                    number =number+Double.parseDouble(toSum.get(y + 1));
                }
            }
            if(toSum.get(y).equals("-")){
                if(y==1) {
                    try{
                    number = number + (Double.parseDouble(toSum.get(y - 1)) - Double.parseDouble(toSum.get(y + 1)));}
                    catch(Exception e){

                    }
                }
                else {
                    number =number-Double.parseDouble(toSum.get(y + 1));
                }
            }

            //y=y+1;

        }
        Log.i("Number", ("" + number).substring(("" + number).indexOf('.') + 1, ("" + number).length()));
        if((""+number).substring((""+number).indexOf('.')+1,(""+number).length()).equals("0")){
            dispView.setText((""+number).substring(0,(""+number).indexOf('.')));
        }
        else {
            dispView.setText("" + number);
        }
    }



}
