package DTO;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Sistemas on 09/03/2015.
 */
public class fecha_Caducidad {


    private int yearHoy,MontHoy,DayHoy,month,day,dia_semana,num_dias;
    Calendar HoyA = new GregorianCalendar();

    public String Dame_caducidad(String c) {

        HoyA.getTime();
        DateTime dateTime = new DateTime(HoyA);

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


        if (c.contentEquals("CR03")){
            dateTime = dateTime.plusDays(30);
        }
        else if (c.contentEquals("RE01")){
        dateTime = dateTime.plusDays(35);
        }
        else if (c.contentEquals("AS16") || c.contentEquals("AS20") ||c.contentEquals("AS21") ||c.contentEquals("MF01") ||c.contentEquals("MF02") ||c.contentEquals("MF03") ||c.contentEquals("MF04")){
            dateTime = dateTime.plusDays(45);
        }
        else if (c.contentEquals("CC01") || c.contentEquals("CC02") ||c.contentEquals("CC03") ||c.contentEquals("CC04") || c.contentEquals("CF01") ||c.contentEquals("CF02") ||
                c.contentEquals("CF03") ||c.contentEquals("MZ03") ||c.contentEquals("MZ04") ||c.contentEquals("MZ05") ||c.contentEquals("MZ22")
                ||c.contentEquals("MZ06") ||c.contentEquals("MZ07") ||c.contentEquals("MZ13") ||c.contentEquals("MZ14") ||c.contentEquals("MZ15") ||c.contentEquals("MZ16") ||
                c.contentEquals("MZ17") ||c.contentEquals("MZ18") ||c.contentEquals("MZ22") ||c.contentEquals("OX29") ||c.contentEquals("OX30") ||c.contentEquals("PA04") ||
                c.contentEquals("PA05") ||c.contentEquals("PA07") ||c.contentEquals("PA08") ||c.contentEquals("PA09") ||c.contentEquals("QR01") ||c.contentEquals("QR02") ||c.contentEquals("QR03") ||
                c.contentEquals("QR04") ||c.contentEquals("RE03") ||c.contentEquals("RE04") ||c.contentEquals("RE05") ||c.contentEquals("RJ09") ||c.contentEquals("YO06")){
            dateTime = dateTime.plusDays(60);
        }
        else if (c.contentEquals("PA06")){
            dateTime = dateTime.plusDays(80);
        }
        else if (c.contentEquals("QR03")){
            dateTime = dateTime.plusDays(180);
        }
        else{
            dateTime = dateTime.plusDays(90);
        }


        String fecha=""+dateTime;




        Log.i("","El agregado en dias:   "+dateTime);

        return ""+fecha.substring(8,10)+"-"+fecha.substring(5,7)+"-"+fecha.substring(0,4);
    }






}