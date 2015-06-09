package DTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemas.gsjetapa1.R;

import au.com.bytecode.opencsv.CSVWriter;
import config.DataBaseHelper;


public class AlertCalendar  {




    DataBaseHelper myDbHelper = new DataBaseHelper(Variables.getExportar());
    protected SQLiteDatabase db;
    protected Cursor cursor;
    private String armado;

	Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);
    public DatePickerDialog dialogee = new DatePickerDialog(Variables.getExportar(),
            new mDateSetListener(), mYear, mMonth, mDay);
    
    

    class mDateSetListener implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                int dayOfMonth) {
            // TODO Auto-generated method stub
            // getCalender();

            int mMonth = monthOfYear+1;

            String mes_mod;
            String dia_mop;

            if (dayOfMonth<10){dia_mop="0"+dayOfMonth;}
            else{dia_mop=""+dayOfMonth;}

            if (mMonth<10){mes_mod="0"+mMonth;}
            else{mes_mod=""+mMonth;}
            
            
            Variables.setFechaExportacion(dia_mop+"-"+mes_mod+"-"+year);


            ExportDatabaseCSVTask task=new ExportDatabaseCSVTask();
            task.execute();

            //Alerta();

            
        }
    }

    public void Alerta(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Variables.getExportar());

        alertDialogBuilder.setTitle("Aviso");

        alertDialogBuilder.setMessage(R.string.Alerta_Guardado);

        alertDialogBuilder.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog,int id) {


            }

        });


        AlertDialog alertDialog = alertDialogBuilder.create();


        alertDialog.show();

    }

    public String Consulta(){

        if (Variables.getTipo_consulta()==1) {
            armado = "select * from " + Variables.getNombre_tabla() + " where fecha LIKE '"+Variables.getFechaExportacion()+"%'";
            //armado = "select * from " + "bitacora" + " where fecha LIKE '"+Variables.getFechaExportacion()+"%'";
        }
        else{ //Para consulta cuajado junto con aditivos
            armado = "select c.fecha, c.lote, c.silo,c.num_equipo ,c.num_tina ,c.familia ,c.leche_silo ,c.ph_leche ,c.porcen_grasa_leche ," +
                    "c.porce_proteina ,c.leche_tina ,c.porce_grasa_leche_tina ,c.porce_prot_tina ,c.crema_kilos ,c.porce_grasa_crema ," +
                    "ca.mp001 ,ca.lote_mp001 ,ca.mp002 ,ca.lote_mp002 ,ca.mp003 ,ca.lote_mp003 ,ca.mp062 ,ca.lote_mp062 ,ca.mp070,ca.lote_mp070," +
                    "ca.mp071 ,ca.lote_mp071 ,ca.mp072 ,ca.lote_mp072 ,ca.cr01 ,ca.lote_cr01," +
                    "c.temp_adi_cuajo as temp_coag," +
                    "c.ph_pasta_coag ,c.hora_adi_cuajo ,c.temp_cocido as temp_des ,c.solidos_totales ,c.hora_inicio_desuerado ,c.pasta_obtenida ," +
                    "c.numero_moldes," +
                    "ca.mp006 ,ca.lote_mp006," +
                    "c.porcentaje_humedad ,c.ph_desuerado, c.litros_suero, c.kilos_pendientes , ca.mp011 , ca.mp021 , ca.mp025 , ca.le04 , ca.le03 , ca.lote_mp011,"+
                    "ca.lote_mp021,ca.lote_mp025 ,ca.lote_le04 ,ca.lote_le03 " +
                    "from cuajado c JOIN cuajado_aditivos ca ON c.lote=ca.lote_cuajado and c.fecha= '" + Variables.getFechaExportacion() + "'";
        }

        return armado;
    }

    public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean>

    {
        private final ProgressDialog dialog = new ProgressDialog(Variables.getExportar());

        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }

        protected Boolean doInBackground(final String... args)

        {

            DataBaseHelper dbhelper = new DataBaseHelper(Variables.getExportar()) ;
              // displays the data base path in your logcat
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");

            if (!exportDir.exists())
            {
                exportDir.mkdirs();
            }

            File file = new File(exportDir, Variables.getNombre_excel()+" "+Variables.getFechaExportacion()+".csv");

            try

            {
                try {
                    if (file.createNewFile()){
                        System.out.println("File is created!");
                        System.out.println("archivo.csv "+file.getAbsolutePath());
                    }else{
                        System.out.println("File already exists.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
                db = myDbHelper.getWritableDatabase();
                cursor= db.rawQuery(Consulta(), null);
                csvWrite.writeNext(cursor.getColumnNames());

                while(cursor.moveToNext())
                {
                    ArrayList<String> arrlist= new ArrayList<String>();
                    for (int i=0; i<cursor.getColumnCount();i++){
                        arrlist.add(cursor.getString(i));
                    }

                    String[] stockArr = new String[arrlist.size()];
                    stockArr = arrlist.toArray(stockArr);
                    csvWrite.writeNext(stockArr);

                }

                csvWrite.close();
                cursor.close();
                return true;

            }

            catch(SQLException sqlEx)
            {
                Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
                return false;
            }

            catch (IOException e)
            {
                Log.e("MainActivity", e.getMessage(), e);
                return false;
            }
        }

        protected void onPostExecute(final Boolean success)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }

            if (success)
            {
                Toast.makeText(Variables.getExportar(), "La información se guardó exitosamente", Toast.LENGTH_SHORT).show();

            }

            else
            {
                Toast.makeText(Variables.getExportar(), "Error, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
            }
        }}
}