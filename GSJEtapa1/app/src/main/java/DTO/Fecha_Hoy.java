package DTO;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Sistemas on 09/03/2015.
 */
public class Fecha_Hoy {

    public String Hoy(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String hoyConFormato = df.format(c.getTime());
        return  hoyConFormato;
    }

    public String Hoy_hora(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df_hora = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String hoyConFormato_hora = df_hora.format(c.getTime());
        return hoyConFormato_hora;
    }

}
