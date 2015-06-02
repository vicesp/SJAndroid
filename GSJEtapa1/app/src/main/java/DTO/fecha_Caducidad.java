package DTO;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Calendar;
import java.util.GregorianCalendar;

import DAO.consultas;

/**
 * Created by Sistemas on 09/03/2015.
 */
public class fecha_Caducidad {

    private static consultas con;
    private int yearHoy,MontHoy,DayHoy,month,day,dia_semana,num_dias;
    Calendar HoyA = new GregorianCalendar();

    public String Dame_caducidad(String c) {

        HoyA.getTime();
        DateTime dateTime = new DateTime(HoyA);
        con=new consultas();

        /*Hoy.getTime();
        MontHoy=(Hoy.get(Calendar.MONTH))+1;
        DayHoy=Hoy.get(Calendar.DATE);
        yearHoy = Hoy.get(Calendar.YEAR);
        DateTime start = new DateTime(yearHoy, 1, 1, 0, 0, 0, 0);
        DateTime end = new DateTime(yearHoy, MontHoy, DayHoy, 0, 0, 0, 0);*/

        dia_semana=HoyA.get(Calendar.DAY_OF_WEEK);

        Log.i("El dia:   ","El dia es.........  "+dia_semana);

       /* Days days = Days.daysBetween(start, end);*/



            if (dia_semana == 7) {

               // num_dias = (days.getDays()) + 1;

            } else {
               // num_dias = (days.getDays()) + (8 - dia_semana);
                dateTime = dateTime.plusDays(7 - dia_semana);
            }


        dateTime = dateTime.plusDays(con.DAOGetCaducidadProductos(c));

        String fecha=""+dateTime;




        Log.i("","El agregado en dias:   "+dateTime);

        return ""+fecha.substring(8,10)+"-"+fecha.substring(5,7)+"-"+fecha.substring(0,4);
    }






}