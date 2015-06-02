package DTO;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.joda.time.Days.SIX;

/**
 * Created by Sistemas on 09/03/2015.
 */
public class Dia_Juliano {


    private int year,yearHoy,MontHoy,DayHoy,month,day,dia_semana,num_dias;
    Calendar Hoy = new GregorianCalendar();

    public String Dame_dia_J_y_anio() {

        Hoy.getTime();
        MontHoy=(Hoy.get(Calendar.MONTH))+1;
        DayHoy=Hoy.get(Calendar.DATE);
        yearHoy = Hoy.get(Calendar.YEAR);
        DateTime start = new DateTime(yearHoy, 1, 1, 0, 0, 0, 0);
        DateTime end = new DateTime(yearHoy, MontHoy, DayHoy, 0, 0, 0, 0);

        Days days = Days.daysBetween(start, end);
        int dias=(days.getDays())+1;
        String anio=Integer.toString(yearHoy);
        String sub=anio.substring(2, 4);
        return ""+dias+sub;
    }

    public String Dame_dia_J_y_anio_Empaque(int juliano, boolean bafar) {

        Hoy.getTime();
        MontHoy=(Hoy.get(Calendar.MONTH))+1;
        DayHoy=Hoy.get(Calendar.DATE);
        yearHoy = Hoy.get(Calendar.YEAR);
        DateTime start = new DateTime(yearHoy, 1, 1, 0, 0, 0, 0);
        DateTime end = new DateTime(yearHoy, MontHoy, DayHoy, 0, 0, 0, 0);
        String letra_dia="";


        dia_semana=Hoy.get(Calendar.DAY_OF_WEEK);

        Log.i("El dia:   ","El dia es.........  "+dia_semana);

        Days days = Days.daysBetween(start, end);

        if (bafar) {

            if (dia_semana == 7) {

                num_dias = (days.getDays()) + 1;

            } else {
                num_dias = (days.getDays()) + (8 - dia_semana);
            }
        }

        /*else{
            if (dia_semana == 3) {

                num_dias = (days.getDays()) + 1;

            }
            else if (dia_semana == 2){
                num_dias = (days.getDays()) + 2;
            }
            else {
                num_dias = (days.getDays()) + (11 - dia_semana);
            }
        }*/

        //FUNCION DISEÑADA SOLO PARA LOS DIAS DEL AÑO 2015
        int i;
        //Para este ciclo inicial coloque en 1 la i y en letra D por que el primer juliano del año es jueves
        for (i=1; i<=365; i+=7){
            if (juliano==i){
                letra_dia="D";
            }
        }

        for (i=2; i<=365; i+=7){
            if (juliano==i){
                letra_dia="E";
            }
        }
        for (i=3; i<=365; i+=7){
            if (juliano==i){
                letra_dia="F";
            }
        }

        for (i=5; i<=365; i+=7){
            if (juliano==i){
                letra_dia="A";
            }
        }

        for (i=6; i<=365; i+=7){
            if (juliano==i){
                letra_dia="B";
            }
        }

        for (i=7; i<=365; i+=7){
            if (juliano==i){
                letra_dia="C";
            }
        }
        Log.i("El dia:   ","El juliano es:  .........  "+days.getDays()+1);
        Log.i("El dia:   ","El dia adelantado a sabado:  .........  "+num_dias);
        Log.i("El dia:   ","La letra de la semana es  :  .........  "+letra_dia);

        String anio=Integer.toString(yearHoy);
        String sub=anio.substring(2, 4);
        return ""+num_dias+sub+"-"+letra_dia;
    }




}