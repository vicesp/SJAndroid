package DAO;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import DTO.Variables;
import config.DataBaseHelper;

/**
 * Created by Sistemas on 25/02/2015.
 */
public class consultas {
    protected SQLiteDatabase db;
    protected Cursor cursor;
    public String[] lote,producto,empaque, cuajado;

    DataBaseHelper myDbHelper = new DataBaseHelper(Variables.getContextoGral());
    /****************   Principio Conexion Base de datos       *************/
    public void creaDB(){
        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }
    }


    public void AbreDB(){

        try {

            myDbHelper.openDataBase();

        }catch(SQLException sqle){

            throw sqle;

        }
    }
    /****************   Fin Conexion Base de datos       *************/

    /****************    Consulta para Login      *************/
    public int DAOLogin(String usuario, String clave){
        creaDB();
        AbreDB();

        db = myDbHelper.getWritableDatabase();
        cursor= db.rawQuery("select id_tipo_usuario from usuarios where cuenta='"+usuario+"' and clave= '"+clave+"' ", null);


        if (cursor.moveToPosition(0)) {

            //cursor.close();
            myDbHelper.close();
            db.close();
            int tipoUser;
            return tipoUser=cursor.getInt(cursor.getColumnIndex("id_tipo_usuario"));
            //return 1;

        }else{
            cursor.close();
            myDbHelper.close();
            db.close();
            return 0;

        }



    }

    /****************    Consulta para Numero consecutivo Texturizador      *************/
    public int DAOTextu_consecutivo(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        //cursor= db.rawQuery("SELECT max(num_consecutivo) from texturizador WHERE fecha='"+fecha+"' ", null);
        cursor= db.rawQuery("SELECT max(num_consecutivo) as num from texturizador WHERE fecha='"+fecha+"' ", null);
        //SELECT max(num_consecutivo) from texturizador WHERE fecha='15-03-2015'
        if (cursor.moveToPosition(0)) {

            //cursor.close();
            myDbHelper.close();
            db.close();
            int cons;
            cons=cursor.getInt(cursor.getColumnIndex("num"));

              return cons;



        }else{
            cursor.close();
            myDbHelper.close();
            db.close();
            return 0;

        }



    }
    /****************    Consulta para ELIMINAR Cuajado      *************/

    public boolean DAOC_elimina_registro(String lote){
        cursor=null;

        db = myDbHelper.getWritableDatabase();

        try {


            db.execSQL("DELETE FROM cuajado WHERE lote='"+lote+"'");

            return true;
        }
        catch (Exception e){

            return false;

        }


    }

    /****************    Consulta para Insert Cuajado      *************/
    public boolean DAOCuajado(String lote,String silo,String num_tina,String familia,String fecha,String leche_silo,
                              String porcen_grasa_leche,String ph_leche,String porce_proteina,
                              String leche_tina,String porce_grasa_leche_tina,
                              String porce_prot_tina,String Temp_adi_cuajo,String ph_pasta_coag,String hora_adi_cuajo,
                              String temp_cocido, String num_equipo, int estatus_guardado, int estatus_pendiente){

        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            cursor= db.rawQuery("select lote from cuajado where lote='"+lote+"'", null);


            if (cursor.moveToPosition(0)) {

                db.execSQL("UPDATE cuajado SET silo='"+silo+"',familia='"+familia+"',leche_silo='"+leche_silo+"',porcen_grasa_leche='"+porcen_grasa_leche+"',ph_leche='"+ph_leche+"',porce_proteina='"+porce_proteina+"',  " +
                        "leche_tina='"+leche_tina+"',porce_grasa_leche_tina='"+porce_grasa_leche_tina+"',porce_prot_tina='"+porce_prot_tina+"',Temp_adi_cuajo='"+Temp_adi_cuajo+"'," +
                        "ph_pasta_coag='"+ph_pasta_coag+"',hora_adi_cuajo='"+hora_adi_cuajo+"',temp_cocido='"+temp_cocido+"', estatus_guardado='"+estatus_guardado+"',estatus_pendiente='"+estatus_pendiente+"'" +
                        "WHERE lote='"+lote+"'");


                myDbHelper.close();
                db.close();


            }

            else{

                db.execSQL("INSERT INTO cuajado (" +
                        "lote,silo,num_tina,familia,fecha,leche_silo,porcen_grasa_leche,ph_leche,porce_proteina," +
                        "leche_tina,porce_grasa_leche_tina,porce_prot_tina,Temp_adi_cuajo," +
                        "ph_pasta_coag,hora_adi_cuajo,temp_cocido, num_equipo, estatus_guardado,estatus_pendiente) " +
                        "VALUES ('"+lote+"','"
                        + silo + "',"
                        + num_tina + ",'"
                        + familia + "','"
                        + fecha + "','"
                        + leche_silo + "','"
                        + porcen_grasa_leche + "','"
                        + ph_leche + "','"
                        + porce_proteina + "','"
                        + leche_tina + "','"
                        + porce_grasa_leche_tina + "','"
                        + porce_prot_tina + "','"
                        + Temp_adi_cuajo + "','"
                        + ph_pasta_coag + "','"
                        + hora_adi_cuajo + "','"
                        + temp_cocido + "','"
                        + num_equipo + "',"
                        + estatus_guardado + ","
                        + estatus_pendiente
                        + ")");

                cursor.close();
                myDbHelper.close();
                db.close();


            }




            return true;
        }
        catch (Exception e){

            return false;

        }
    }

    /****************    Consulta para Insert Cuajado Parte 2     *************/
    public boolean DAOCuajado_parte2(String lote,String porce_crema,String crema_kilos,String hora_inicio_desuerado,String litros_suero,String ph_desuerado,String solidos_totales,String pasta_obtenida,
                                     String numero_moldes,String kilos_pendientes, String porcen_humedad){

        cursor=null;

        db = myDbHelper.getWritableDatabase();

        try {


            db.execSQL("UPDATE cuajado SET " +
                    "porce_grasa_crema='"+porce_crema+"',crema_kilos='"+crema_kilos+"',hora_inicio_desuerado='"+hora_inicio_desuerado+"',litros_suero='"+litros_suero+"',ph_desuerado='"+ph_desuerado+"',solidos_totales='"+solidos_totales+"'" +
                    ",pasta_obtenida='"+pasta_obtenida+"',numero_moldes='"+numero_moldes+"',kilos_pendientes='"+kilos_pendientes+"',porcentaje_humedad='"+porcen_humedad+"',estatus_pendiente=1 where lote='"+lote+"'");

            return true;
        }
        catch (Exception e){

            return false;

        }
    }



        /****************    Consulta para Cuajado - Aditivos      *************/
    public boolean DAOCuajadoAditivos(String lote_cuajado, String mp001,String lote_mp001,String mp003,String lote_mp003,String mp002,String lote_mp002,
                              String mp006,String lote_mp006,String mp011, String lote_mp011,
                              String mp021,String lote_mp021,String cr01,String lote_cr01,
                              String mp025,String lote_mp025,String mp062,String lote_mp062,
                              String mp070, String lote_mp070, String mp071, String lote_mp071, String mp072, String lote_mp072, String le04, String lote_le04,
                              String le03, String lote_le03,int estatus_guardado){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            cursor= db.rawQuery("select lote_cuajado from cuajado_aditivos where lote_cuajado='"+lote_cuajado+"'", null);


            if (cursor.moveToPosition(0)) {

                db.execSQL("UPDATE cuajado_aditivos SET lote_cuajado='"+lote_cuajado+"',mp001='"+mp001+"',lote_mp001='"+lote_mp001+"',mp003='"+mp003+"',lote_mp003='"+lote_mp003+"',mp002='"+mp002+"',lote_mp002='"+lote_mp002+"',mp006='"+mp006+"',lote_mp006='"+lote_mp006+"'," +
                                "mp011='"+mp011+"',lote_mp011='"+lote_mp011+"',mp021='"+mp021+"',lote_mp021='"+lote_mp021+"',cr01='"+cr01+"',lote_cr01='"+lote_cr01+"'," +
                                "mp025='"+mp025+"',lote_mp025='"+lote_mp025+"',mp062='"+mp062+"', lote_mp062='"+lote_mp062+"' ,mp070='"+mp070+"' ,lote_mp070='"+lote_mp070+"' ,mp071='"+mp071+"' ,lote_mp071='"+lote_mp071+"' ,mp072='"+mp072+"',lote_mp072='"+lote_mp072+"' ," +
                        "le04='"+le04+"' ,lote_le04='"+lote_le04+"' ,le03='"+le03+"' ,lote_le03='"+lote_le03+"',estatus_guardado="+estatus_guardado+""+
                        " WHERE lote_cuajado='"+lote_cuajado+"'");


                myDbHelper.close();
                db.close();
                return true;


            }

            else {

                db.execSQL("INSERT INTO cuajado_aditivos (" +
                        " lote_cuajado,mp001,lote_mp001,mp003,lote_mp003,mp002,lote_mp002,mp006,lote_mp006," +
                        "mp011,lote_mp011,mp021,lote_mp021,cr01,lote_cr01," +
                        "mp025,lote_mp025,mp062, lote_mp062 ,mp070 ,lote_mp070 ,mp071 ,lote_mp071 ,mp072 ,lote_mp072 ,le04 ,lote_le04 ,le03 ,lote_le03,estatus_guardado) " +
                        "VALUES ('" + lote_cuajado + "','"
                        + mp001 + "','"
                        + lote_mp001 + "','"
                        + mp003 + "','"
                        + lote_mp003 + "','"
                        + mp002 + "','"
                        + lote_mp002 + "','"
                        + mp006 + "','"
                        + lote_mp006 + "','"
                        + mp011 + "','"
                        + lote_mp011 + "','"
                        + mp021 + "','"
                        + lote_mp021 + "','"
                        + cr01 + "','"
                        + lote_cr01 + "','"
                        + mp025 + "','"
                        + lote_mp025 + "','"
                        + mp062 + "','"
                        + lote_mp062 + "','"
                        + mp070 + "','"
                        + lote_mp070 + "','"
                        + mp071 + "','"
                        + lote_mp071 + "','"
                        + mp072 + "','"
                        + lote_mp072 + "','"
                        + le04 + "','"
                        + lote_le04 + "','"
                        + le03 + "','"
                        + lote_le03 + "',"
                        + estatus_guardado
                        + ")");

                return true;
            }
        }
        catch (Exception e){
            Log.i("Error","Error consulta cuajado-Aditivos:   "+e);
            return false;
        }
    }

    /****************    Consulta para empaque     *************/
    public boolean DAOEmpaque(String lote_origen, String fecha,String cod_prod,String prod_terminado,String lote,String piezas_almacen,String piezas_reproceso,
                                      String temp_pt,String hora_inicio_pt,String cod_prod_restos, String lote_restos,
                                      String cantidad_restos,String maquina_usar,String vacio_ulma,String gas_ulma,
                                      String temp_ulma,String temp_sellado_ulma,String oxigeno_ulma,String vacio_ultra,
                                      String temp_ultra, String hora_fin_ultra, String lote_fondo, String lote_tapa, String lote_funda, String observaciones,
                                      String piezas_empacadas, String piezas_calidad){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO empaque (" +
                    " lote_origen,fecha,cod_prod,prod_terminado,lote,piezas_almacen,piezas_reproceso,temp_pt,hora_inicio_pt,cod_prod_restos," +
                    "lote_restos,cantidad_restos,maquina_usar,vacio_ulma,gas_ulma,temp_ulma,temp_sellado_ulma,oxigeno_ulma,vacio_ultra," +
                    "temp_ultra,hora_fin_ultra,lote_fondo,lote_tapa,lote_funda,observaciones,piezas_empacadas,piezas_calidad)" +
                    "VALUES ('" + lote_origen + "','"
                    + fecha + "','"
                    + cod_prod + "','"
                    + prod_terminado + "','"
                    + lote + "','"
                    + piezas_almacen + "','"
                    + piezas_reproceso + "','"
                    + temp_pt + "','"
                    + hora_inicio_pt + "','"
                    + cod_prod_restos + "','"
                    + lote_restos + "','"
                    + cantidad_restos + "','"
                    + maquina_usar + "','"
                    + vacio_ulma + "','"
                    + gas_ulma + "','"
                    + temp_ulma + "','"
                    + temp_sellado_ulma + "','"
                    + oxigeno_ulma + "','"
                    + vacio_ultra + "','"
                    + temp_ultra + "','"
                    + hora_fin_ultra + "','"
                    + lote_fondo + "','"
                    + lote_tapa + "','"
                    + lote_funda + "','"
                    + observaciones + "','"
                    + piezas_empacadas + "','"
                    + piezas_calidad
                    + "')");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    /****************    Consulta para actualizar empaque     *************/
    public boolean DAOActualizarEmpaque(String lote_origen, String fecha,String cod_prod,String prod_terminado,String lote,String piezas_almacen,String piezas_reproceso,
                              String temp_pt,String hora_inicio_pt,String cod_prod_restos, String lote_restos,
                              String cantidad_restos,String maquina_usar,String vacio_ulma,String gas_ulma,
                              String temp_ulma,String temp_sellado_ulma,String oxigeno_ulma,String vacio_ultra,
                              String temp_ultra, String hora_fin_ultra, String lote_fondo, String lote_tapa, String lote_funda, String observaciones,
                              String piezas_empacadas, String piezas_calidad){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE empaque SET" +
                            " fecha = '" + fecha + "', "+
                            " cod_prod = '" + cod_prod + "', "+
                            " prod_terminado = '" + prod_terminado + "', "+
                            " lote = '" + lote + "', "+
                            " piezas_almacen = '" + piezas_almacen + "', "+
                            " piezas_reproceso = '" + piezas_reproceso + "', "+
                            " temp_pt = '" + temp_pt + "', "+
                            " hora_inicio_pt = '" + hora_inicio_pt + "', "+
                            " lote_restos = '" + lote_restos + "', "+
                            " cantidad_restos = '" + cantidad_restos + "', "+
                            " maquina_usar = '" + maquina_usar + "', "+
                            " vacio_ulma = '" + vacio_ulma + "', "+
                            " gas_ulma = '" + gas_ulma + "', "+
                            " temp_ulma = '" + temp_ulma + "', "+
                            " temp_sellado_ulma = '" + temp_sellado_ulma + "', "+
                            " oxigeno_ulma = '" + oxigeno_ulma + "', "+
                            " temp_ultra = '" + temp_ultra + "', "+
                            " hora_fin_ultra = '" + hora_fin_ultra + "', "+
                            " lote_fondo = '" + lote_fondo + "', "+
                            " lote_tapa = '" + lote_tapa + "', "+
                            " lote_funda = '" + lote_funda + "', "+
                            " observaciones = '" + observaciones + "', "+
                            " piezas_empacadas = '" + piezas_empacadas + "', "+
                            " piezas_calidad = '" + piezas_calidad + "', "+
                            " vacio_ultra = '" + vacio_ultra + "', "+
                            " cod_prod_restos = '" + cod_prod_restos + "', "+
                            " lote_origen = '" + lote_origen + "' WHERE lote = '"+lote+"';"
            );


            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /*************** Obtener tods productos empaque     *************/

    public ArrayList<consultas> DAOGetTodosProductos()
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT codigo_producto, nombre_producto FROM cat_productos WHERE eliminado = 0" + "", null);
        ArrayList<consultas> productosArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {

                consultas lista = new consultas();
                lista.producto=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.producto[x]=cursor.getString(cursor.getColumnIndex("codigo_producto"))+"-"+cursor.getString(cursor.getColumnIndex("nombre_producto"));
                    cursor.moveToNext();
                }

                productosArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return productosArray;
    }

    /*************** Obtener producto empaque     *************/
    public String[] DAOGetProdcuto(String codigo)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;

        cursor = db.rawQuery("SELECT nombre_producto, caducidad, eliminado FROM cat_productos WHERE codigo_producto = '" + codigo + "' AND eliminado = 0;", null);
        ArrayList<consultas> productosArray = new ArrayList<consultas>();
        consultas lista = new consultas();
        lista.producto=new String[3];


        if (cursor != null ) {
            if  (cursor.moveToFirst()) {

                lista.producto[0]=cursor.getString(cursor.getColumnIndex("nombre_producto"));
                lista.producto[1]=cursor.getString(cursor.getColumnIndex("caducidad"));
                lista.producto[2]=cursor.getString(cursor.getColumnIndex("eliminado"));

            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return lista.producto;
    }

    /*********** Actualizar Productos **************/
    public boolean DAOActualizarProductos(String codigo, String nombre, String caducidad, int eliminar) {
        boolean check = false;
        cursor=null;
        db = myDbHelper.getWritableDatabase();
        try{


                db.execSQL("UPDATE cat_productos SET codigo_producto = '"+codigo+"', nombre_producto = '"+nombre+"', caducidad = '"+caducidad+"', eliminado = "+eliminar+" WHERE codigo_producto ='"+codigo+"';");


            check= true;
        }
        catch(Exception e)
        {
            check = false;
        }
        return check;
    }

    /*********** Obtener Caducidad Productos **************/
    public int DAOGetCaducidadProductos(String codigo)
    {int intCaducidad = 0;
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT caducidad " + "FROM cat_productos " + "WHERE codigo_producto = '" + codigo + "'", null);
        
        if (cursor.moveToNext()) {
            intCaducidad = cursor.getInt(cursor.getColumnIndex("caducidad"));
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return intCaducidad;


    }

    /*********** Guardar Nuevo Producto **************/

    public boolean DAOGuardarProducto(String codigo, String descripcion, String caducidad)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        try {

            db.execSQL("INSERT INTO cat_productos(codigo_producto, nombre_producto, caducidad, eliminado) VALUES ('" + codigo + "','" + descripcion + "','" + caducidad + "', 0)" );
        return true;
        }
        catch(Exception e){

        return false;
    }
    }

    /****************    Consulta para Llenar la lista de lotes Empaque_Realizdo    *************/
    public ArrayList<consultas> DAOListaEmpaqueRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM empaque WHERE fecha ='" +
                fecha + "'", null);
        Log.i("",fecha);
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"));
                    cursor.moveToNext();
                }
                empaqueArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return empaqueArray;
    }
    /****************    Consulta para Llenar EMPAQUE si se viene de realizados    *************/
    public Cursor DAOLLenarEmpaque(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT lote_origen, fecha, cod_prod, prod_terminado, lote, piezas_almacen, piezas_reproceso" +
                    ", temp_pt, hora_inicio_pt, cod_prod_restos, lote_restos, cantidad_restos, maquina_usar, vacio_ulma, gas_ulma" +
                    ", temp_ulma, temp_sellado_ulma, oxigeno_ulma, vacio_ultra, temp_ultra, hora_fin_ultra, lote_fondo, lote_tapa " +
                    ", lote_funda, observaciones, piezas_empacadas, piezas_calidad " +
                    "FROM empaque WHERE lote ='" +
                    lote + "'", null);
            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                return cursor;




            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return null;

            }
        }

        catch (Exception e){
            return null;

        }
    }


    /****************    Consulta para Fundido     *************/
    public boolean DAOFundido(String lote, String linea,String fecha,String num_fundida,String familia,String cj01,String lote_cj01,
                              String ph_cj01,String cj011,String lote_cj011, String ph_cj011,
                              String mp005,String lote_mp005,String mp015,String lote_mp015,
                              String s0101,String lote_s0101,String mp007,String lote_mp007,
                              String sa01, String lote_sa01, String mp024, String lote_mp024, String mp078, String lote_mp078, String cj02,
                              String lote_cj02,String agua,String tipo_crema,String lote_tipo_crema,String cantidad_crema,String familia_reproceso,
                              String lote_fami_repro, String cantidad_fami_repro, String temperatura, String peso_total, String texturizador, String lote_texturizador,
                              String cj01_1,String lote_cj01_1,String ph_cj01_1,String cj01_2,String lote_cj01_2,String ph_cj01_2,String tipo_crema2,String lote_crema2,String cantidad_crema2,
                              String tipo_cuajada_1,String tipo_cuajada_2,String tipo_cuajada_3,String cj01_3,String lote_cj01_3,String ph_cj01_3){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO fundido (" +
                    " linea,fecha,num_fundida,lote,familia,cj01,lote_cj01,ph_cj01,cj011,lote_cj011,ph_cj011,mp005,lote_mp005,mp015,lote_mp015," +
                    "s0101,lote_s0101,mp007,lote_mp007,sa01,lote_sa01,mp024,lote_mp024,mp078,lote_mp078,cj02,lote_cj02,agua,tipo_crema," +
                    "lote_tipo_crema, cantidad_crema, familia_reproceso,lote_fami_repro,cantidad_fami_repro,temperatura,peso_total,texturizador,lote_texturizador," +
                    "cj01_1,lote_cj01_1,ph_cj01_1,cj01_2,lote_cj01_2,ph_cj01_2,tipo_crema2,lote_tipo_crema2,cantidad_crema2, tipo_cuajada_1, tipo_cuajada_2, tipo_cuajada_3, cj01_3, lote_cj01_3, ph_cj01_3) " +
                    "VALUES (" + linea + ",'"
                    + fecha + "','"
                    + num_fundida + "','"
                    + lote + "','"
                    + familia + "','"
                    + cj01 + "','"
                    + lote_cj01 + "','"
                    + ph_cj01 + "','"
                    + cj011 + "','"
                    + lote_cj011 + "','"
                    + ph_cj011 + "','"
                    + mp005 + "','"
                    + lote_mp005 + "','"
                    + mp015 + "','"
                    + lote_mp015 + "','"
                    + s0101 + "','"
                    + lote_s0101 + "','"
                    + mp007 + "','"
                    + lote_mp007 + "','"
                    + sa01 + "','"
                    + lote_sa01 + "','"
                    + mp024 + "','"
                    + lote_mp024 + "','"
                    + mp078 + "','"
                    + lote_mp078 + "','"
                    + cj02 + "','"
                    + lote_cj02 + "','"
                    + agua + "','"
                    + tipo_crema + "','"
                    + lote_tipo_crema + "','"
                    + cantidad_crema + "','"
                    + familia_reproceso + "','"
                    + lote_fami_repro + "','"
                    + cantidad_fami_repro + "','"
                    + temperatura + "','"
                    + peso_total + "','"
                    + texturizador + "','"
                    + lote_texturizador + "','"
                    + cj01_1 + "','"
                    + lote_cj01_1 + "','"
                    + ph_cj01_1 + "','"
                    + cj01_2 + "','"
                    + lote_cj01_2 + "','"
                    + ph_cj01_2 + "','"
                    + tipo_crema2 + "','"
                    + lote_crema2 + "','"
                    + cantidad_crema2 + "','"
                    + tipo_cuajada_1 + "','"
                    + tipo_cuajada_2 + "','"
                    + tipo_cuajada_3 + "','"
                    + cj01_3 + "','"
                    + lote_cj01_3 + "','"
                    + ph_cj01_3
                    + "')");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /****************    Consulta para Llenar la lista de lotes Fundido-Realizado    *************/
    public ArrayList<consultas> DAOListaFundidoRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM fundido WHERE fecha ='" +
                fecha + "'", null);
        Log.i("","SELECT lote " +
                "FROM fundido WHERE fecha ='"+fecha+"'");
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"));
                    cursor.moveToNext();
                }
                empaqueArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return empaqueArray;
    }

    /****************    Consulta para Texturizador     *************/
    public boolean DAOTexturizador(String lote, String fecha,String texturizador,String mp002,String lote_mp002,String mp003,String lote_mp003,
                                   String mp004,String lote_mp004,String mp005, String lote_mp005,
                                   String mp006,String lote_mp006,String mp007,String lote_mp007,
                                   String mp008,String lote_mp008,String mp009,String lote_mp009,
                                   String mp010,String lote_mp010,String mp021,String lote_mp021,String mp025,
                              String lote_mp025,String mp026,String lote_mp026,String mp027,String lote_mp027,
                                   String mp028,String lote_mp028,String mp031,String lote_mp031,String mp012,
                              String lote_mp012,String mp013,String lote_mp013,String mp014,String lote_mp014, String kilos_totales, int num_consecutivo){
        cursor=null;
        db = myDbHelper.getWritableDatabase();



        try {
            db.execSQL("INSERT INTO texturizador (" +
                    " lote,fecha,texturizador,mp002,lote_mp002,mp003,lote_mp003,mp004,lote_mp004,mp005,lote_mp005,mp006,lote_mp006,mp007,lote_mp007," +
                    "mp008,lote_mp008,mp009,lote_mp009,mp010,lote_mp010,mp021,lote_mp021,mp025,lote_mp025,mp026,lote_mp026,mp027,lote_mp027,mp028," +
                    "lote_mp028,mp031,lote_mp031,mp012,lote_mp012,mp013,lote_mp013,mp014,lote_mp014,kilos_totales,num_consecutivo) " +
                    "VALUES ('" + lote + "','"
                    + fecha + "','"
                    + texturizador + "','"
                    + mp002 + "','"
                    + lote_mp002 + "','"
                    + mp003 + "','"
                    + lote_mp003 + "','"
                    + mp004 + "','"
                    + lote_mp004 + "','"
                    + mp005 + "','"
                    + lote_mp005 + "','"
                    + mp006 + "','"
                    + lote_mp006 + "','"
                    + mp007 + "','"
                    + lote_mp007 + "','"
                    + mp008 + "','"
                    + lote_mp008 + "','"
                    + mp009 + "','"
                    + lote_mp009 + "','"
                    + mp010 + "','"
                    + lote_mp010 + "','"
                    + mp021 + "','"
                    + lote_mp021 + "','"
                    + mp025 + "','"
                    + lote_mp025 + "','"
                    + mp026 + "','"
                    + lote_mp026 + "','"
                    + mp027 + "','"
                    + lote_mp027 + "','"
                    + mp028 + "','"
                    + lote_mp028 + "','"
                    + mp031 + "','"
                    + lote_mp031 + "','"
                    + mp012 + "','"
                    + lote_mp012 + "','"
                    + mp013 + "','"
                    + lote_mp013 + "','"
                    + mp014 + "','"
                    + lote_mp014 + "','"
                    + kilos_totales + "',"
                    + num_consecutivo
                    + ")");

            return true;
        }
        catch (Exception e){


            return false;
        }
    }
    //***************    Consulta para Actualizar Texturizador *********/


    public boolean DAOActualizarTexturizador(String lote, String fecha,String texturizador,String mp002,String lote_mp002,String mp003,String lote_mp003,
                                       String mp004,String lote_mp004,String mp005, String lote_mp005,
                                       String mp006,String lote_mp006,String mp007,String lote_mp007,
                                       String mp008,String lote_mp008,String mp009,String lote_mp009,
                                       String mp010,String lote_mp010,String mp021,String lote_mp021,String mp025,
                                       String lote_mp025,String mp026,String lote_mp026,String mp027,String lote_mp027,
                                       String mp028,String lote_mp028,String mp031,String lote_mp031,String mp012,
                                       String lote_mp012,String mp013,String lote_mp013,String mp014,String lote_mp014, String kilos_totales, int num_consecutivo)
    {

        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE texturizador SET "
                    + "fecha = '" + fecha + "',"
                    + "texturizador = '" + texturizador + "',"
                    + "mp002 = '" + mp002 + "',"
                    + "lote_mp002 = '" + lote_mp002 + "',"
                    + "mp003 = '" + mp003 + "',"
                    + "lote_mp003 = '" + lote_mp003 + "',"
                    + "mp004 = '" + mp004 + "',"
                    + "lote_mp004 = '" + lote_mp004 + "',"
                    + "mp005 = '" + mp005 + "',"
                    + "lote_mp005 = '" + lote_mp005 + "',"
                    + "mp006 = '" + mp006 + "',"
                    + "lote_mp006 = '" + lote_mp006 + "',"
                    + "mp007 = '" + mp007 + "',"
                    + "lote_mp007 = '" + lote_mp007 + "',"
                    + "mp008 = '" + mp008 + "',"
                    + "lote_mp008 = '" + lote_mp008 + "',"
                    + "mp009 = '" + mp009 + "',"
                    + "lote_mp009 = '" + lote_mp009 + "',"
                    + "mp010 = '" + mp010 + "',"
                    + "lote_mp010 = '" + lote_mp010 + "',"
                    + "mp021 = '" + mp021 + "',"
                    + "lote_mp021 = '" + lote_mp021 + "',"
                    + "mp025 = '" + mp025 + "',"
                    + "lote_mp025 = '" + lote_mp025 + "',"
                    + "mp026 = '" + mp026 + "',"
                    + "lote_mp026 = '" + lote_mp026 + "',"
                    + "mp027 = '" + mp027 + "',"
                    + "lote_mp027 = '" + lote_mp027 + "',"
                    + "mp028 = '" + mp028 + "',"
                    + "lote_mp028 = '" + lote_mp028 + "',"
                    + "mp031 = '" + mp031 + "',"
                    + "lote_mp031 = '" + lote_mp031 + "',"
                    + "mp012 = '" + mp012 + "',"
                    + "lote_mp012 = '" + lote_mp012 + "',"
                    + "mp013 = '" + mp013 + "',"
                    + "lote_mp013 = '" + lote_mp013 + "',"
                    + "mp014 = '" + mp014 + "',"
                    + "lote_mp014 = '" + lote_mp014 + "',"
                    + "kilos_totales = '" + kilos_totales + "',"
                    + "num_consecutivo = '" + num_consecutivo +
                    "' WHERE lote = '" + lote + "'");


            return true;
        }
        catch (Exception e){


            return false;
        }

    }
    //***************    Consulta para llenar TexturizadorSpinner *********/

    public ArrayList<consultas> DAODescripcionTexturizador(){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT descripcion " +
                "FROM cat_texturizador" +
                "", null);
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> descripcionArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.producto=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.producto[x]=cursor.getString(cursor.getColumnIndex("descripcion"));
                    cursor.moveToNext();
                }

                descripcionArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return descripcionArray;
    }

    //*********   Consulta activar/desactivar switches  *****/
    public Boolean DAOSwitchBool(String columna,String id)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT "+columna+ " from texturizador_actualizador WHERE id='"+id+"' ", null);

            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                int cons;
                cons=cursor.getInt(cursor.getColumnIndex(columna));
                return cons == 1;


            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return false;

            }
        }
        catch (Exception e){
            return false;
        }

    }

    //*********   Consulta para llenar valores actuales Texturizador  *****/

    public String DAOValoresActuales(String columna,String id)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT "+columna+ " from texturizador_actualizador WHERE id='"+id+"' ", null);

            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                String cons;
                cons=cursor.getString(cursor.getColumnIndex(columna));
                return cons;



            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return "";

            }
        }
        catch (Exception e){
            return "";
        }

    }

    /**************** Consulta para actualizar nuevos valores texturizador_edit ***/

    public Boolean DAOGuardarValores(String columna, int id, String valorNuevo, String columnaBool ,int boolVal)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        if (valorNuevo.trim().length() != 0) {
            try {
                db.execSQL("UPDATE texturizador_actualizador SET " + columnaBool + " = '" + boolVal + "' WHERE id = '" + id + "' ");
                db.execSQL("UPDATE texturizador_actualizador SET " + columna + " = '" + valorNuevo + "' WHERE id = '" + id + "' ");

                return true;

            } catch (Exception e) {
                return false;

            }
        }
        else {
            db.execSQL("UPDATE texturizador_actualizador SET " + columnaBool + " = '" + boolVal + "' WHERE id = '" + id + "' ");
            return true;
        }
    }


    /****************    Consulta para Llenar la lista de lotes TEXTURIZADOR_REALIZADOS    *************/
    public ArrayList<consultas> DAOListaTexturizadorRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM texturizador WHERE fecha ='" +
                fecha + "'", null);
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> pacientesArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.lote=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.lote[x]=cursor.getString(cursor.getColumnIndex("lote"));
                    cursor.moveToNext();
                }

                pacientesArray.add(lista);

            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return pacientesArray;
    }

    /****************    Consulta para Producto llenar Texturizador con Busqueda     *************/

    public Cursor DAOLLenarTexturizador(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha, lote_mp002, lote_mp003, lote_mp004, lote_mp005, lote_mp006, lote_mp007" +
                    ", lote_mp008, lote_mp009, lote_mp010, lote_mp021, lote_mp025, lote_mp026, lote_mp027, lote_mp028" +
                    ", lote_mp031, lote_mp012, lote_mp013, lote_mp014, kilos_totales " +
                    "FROM texturizador WHERE lote ='" +
                    lote + "'", null);
            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                return cursor;




            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return null;

            }
        }

        catch (Exception e){
        return null;

        }
    }

    /****************    Consulta para Producto Terminado     *************/
    public boolean DAOPT(String lote,String fecha, String codigo_pt, String num_viaje, String num_piezas, String kilos, String num_fundida){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO PT (" +
                    " lote,fecha,codigo_pt,num_viaje,num_piezas,kilos,numero_fundida) " +
                    "VALUES ('" + lote + "','"
                    + fecha + "','"
                    + codigo_pt + "','"
                    + num_viaje + "','"
                    + num_piezas + "','"
                    + kilos + "','"
                    + num_fundida
                    + "')");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /****************    Consulta para Numero Viaje de Producto Terminado     *************/
    public int DAOPT_numero_viaje(String fecha){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT max(num_viaje) as num from PT WHERE fecha='"+fecha+"' ", null);

            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                int cons;
                cons = cursor.getInt(cursor.getColumnIndex("num"));
                 return cons;



            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return 1;

            }
        }
        catch (Exception e){
            return 0;
        }
    }

    /****************    Consulta para Numero Fundida Fundido     *************/
    public int DAOF_num_fundida(int linea, String fecha){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT max(num_fundida) as num from fundido WHERE fecha LIKE '"+fecha+"%' and linea="+linea+" ", null);

            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                int cons;
                cons=cursor.getInt(cursor.getColumnIndex("num"));
                return cons;



            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return 0;

            }
        }
        catch (Exception e){
            return 0;
        }
    }

    /****************    Consulta para Numero de Tina CUAJADO     *************/
    public int DAOC_Numero_tina(String fecha){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT max(num_tina) as num from cuajado WHERE fecha='"+fecha+"' and estatus_guardado=0", null);
            int cons=0;
            if (cursor.moveToPosition(0)) {
                if (cursor.getColumnIndex("num")!=0){
                    myDbHelper.close();
                    db.close();
                    cons=cursor.getInt(cursor.getColumnIndex("num"));
                }else{
                    cursor= db.rawQuery("SELECT max(num_tina) as num from cuajado WHERE fecha='"+fecha+"' and estatus_guardado=1 ", null);
                    if (cursor.moveToPosition(0)) {
                    cons=cursor.getInt(cursor.getColumnIndex("num"));
                    }

                }



                return cons;



            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return 0;

            }
        }
        catch (Exception e){
            return 0;
        }
    }

    /****************    Consulta para Llenar cuajado     *************/
    public int DAOC_llena_info_sin_guardar(String numero_equipo, String fecha){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT * FROM cuajado WHERE num_equipo='"+numero_equipo+"' and fecha='"+fecha+"' and estatus_guardado=0 ", null);

            if (cursor.moveToPosition(0)) {
                myDbHelper.close();
                db.close();
                Variables.setLecheSilo(cursor.getString(cursor.getColumnIndex("leche_silo")));
                Variables.setGrasaLecheSilo(cursor.getString(cursor.getColumnIndex("porcen_grasa_leche")));
                Variables.setPhLeche(cursor.getString(cursor.getColumnIndex("ph_leche")));
                Variables.setProteinaLecheSilo(cursor.getString(cursor.getColumnIndex("porce_proteina")));
                Variables.setGrasaCrema(cursor.getString(cursor.getColumnIndex("porce_grasa_crema")));
                Variables.setCremaEstandarizada(cursor.getString(cursor.getColumnIndex("crema_kilos")));
                Variables.setLecheTina(cursor.getString(cursor.getColumnIndex("leche_tina")));
                Variables.setGrasaLecheTina(cursor.getString(cursor.getColumnIndex("porce_grasa_leche_tina")));
                Variables.setProteinaTina(cursor.getString(cursor.getColumnIndex("porce_prot_tina")));
                Variables.setTempCoagulacion(cursor.getString(cursor.getColumnIndex("Temp_adi_cuajo")));
                Variables.setPhPasta(cursor.getString(cursor.getColumnIndex("ph_pasta_coag")));
                Variables.setHoraAdicionCuajo(cursor.getString(cursor.getColumnIndex("hora_adi_cuajo")));
                Variables.setTempCocido(cursor.getString(cursor.getColumnIndex("temp_cocido")));
                Variables.setNum_tina_mostrar(cursor.getInt(cursor.getColumnIndex("num_tina")));



                return 1;

            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                Variables.setLecheSilo("");
                Variables.setGrasaLecheSilo("");
                Variables.setPhLeche("");
                Variables.setProteinaLecheSilo("");
                Variables.setGrasaCrema("");
                Variables.setCremaEstandarizada("");
                Variables.setLecheTina("");
                Variables.setGrasaLecheTina("");
                Variables.setProteinaTina("");
                Variables.setTempCoagulacion("");
                Variables.setPhPasta("");
                Variables.setHoraAdicionCuajo("");
                Variables.setTempCocido("");
                Variables.setNum_tina_mostrar(1);
                return 0;

            }
        }
        catch (Exception e){
            return 0;
        }
    }

    /****************    Consulta para Llenar cuajado  ADITIVOS   *************/
    public int DAOC_llena_info_sin_guardar_aditivos(String lote_cuajado){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT * FROM cuajado_aditivos WHERE lote_cuajado='"+lote_cuajado+"' and estatus_guardado=0 ", null);

            if (cursor.moveToPosition(0)) {
                myDbHelper.close();
                db.close();
                Variables.setAdi1(cursor.getString(cursor.getColumnIndex("mp001")));
                Variables.setAdi2(cursor.getString(cursor.getColumnIndex("mp003")));
                Variables.setAdi3(cursor.getString(cursor.getColumnIndex("mp002")));
                Variables.setAdi4(cursor.getString(cursor.getColumnIndex("mp006")));
                Variables.setAdi5(cursor.getString(cursor.getColumnIndex("mp011")));
                Variables.setAdi6(cursor.getString(cursor.getColumnIndex("mp021")));
                Variables.setAdi7(cursor.getString(cursor.getColumnIndex("cr01")));
                Variables.setAdi8(cursor.getString(cursor.getColumnIndex("mp025")));
                Variables.setAdi9(cursor.getString(cursor.getColumnIndex("mp062")));
                Variables.setAdi10(cursor.getString(cursor.getColumnIndex("mp070")));
                Variables.setAdi11(cursor.getString(cursor.getColumnIndex("mp071")));
                Variables.setAdi12(cursor.getString(cursor.getColumnIndex("mp072")));
                Variables.setAdi13(cursor.getString(cursor.getColumnIndex("le04")));
                Variables.setAdi14(cursor.getString(cursor.getColumnIndex("le03")));

                if (cursor.getString(cursor.getColumnIndex("lote_mp001")).isEmpty()){

                }else{
                    Variables.setLote1(cursor.getString(cursor.getColumnIndex("lote_mp001")));
                }

                if (cursor.getString(cursor.getColumnIndex("lote_mp003")).isEmpty()){

                }else{
                    Variables.setLote2(cursor.getString(cursor.getColumnIndex("lote_mp003")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp002")).isEmpty()){

                }else{
                    Variables.setLote3(cursor.getString(cursor.getColumnIndex("lote_mp002")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp006")).isEmpty()){

                }else{
                    Variables.setLote4(cursor.getString(cursor.getColumnIndex("lote_mp006")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp011")).isEmpty()){

                }else{
                    Variables.setLote5(cursor.getString(cursor.getColumnIndex("lote_mp011")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp021")).isEmpty()){

                }else{
                    Variables.setLote6(cursor.getString(cursor.getColumnIndex("lote_mp021")));
                }

                if (cursor.getString(cursor.getColumnIndex("lote_cr01")).isEmpty()){

                }else{
                    Variables.setLote7(cursor.getString(cursor.getColumnIndex("lote_cr01")));
                }

                if (cursor.getString(cursor.getColumnIndex("lote_mp025")).isEmpty()){

                }else{
                    Variables.setLote8(cursor.getString(cursor.getColumnIndex("lote_mp025")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp062")).isEmpty()){

                }else{
                    Variables.setLote9(cursor.getString(cursor.getColumnIndex("lote_mp062")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp070")).isEmpty()){

                }else{
                    Variables.setLote10(cursor.getString(cursor.getColumnIndex("lote_mp070")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp071")).isEmpty()){

                }else{
                    Variables.setLote11(cursor.getString(cursor.getColumnIndex("lote_mp071")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_mp072")).isEmpty()){

                }else{
                    Variables.setLote12(cursor.getString(cursor.getColumnIndex("lote_mp072")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_le04")).isEmpty()){

                }else{
                    Variables.setLote13(cursor.getString(cursor.getColumnIndex("lote_le04")));
                }


                if (cursor.getString(cursor.getColumnIndex("lote_le03")).isEmpty()){

                }else{
                    Variables.setLote14(cursor.getString(cursor.getColumnIndex("lote_le03")));
                }



                return 1;

            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                Variables.setAdi1("");
                Variables.setAdi2("");
                Variables.setAdi3("");
                Variables.setAdi4("");
                Variables.setAdi5("");
                Variables.setAdi6("");
                Variables.setAdi7("");
                Variables.setAdi8("");
                Variables.setAdi9("");
                Variables.setAdi10("");
                Variables.setAdi11("");
                Variables.setAdi12("");
                Variables.setAdi13("");
                Variables.setAdi14("");
                /*Variables.setLote1("");
                Variables.setLote2("");
                Variables.setLote3("");
                Variables.setLote4("");
                Variables.setLote5("");
                Variables.setLote6("");
                Variables.setLote7("");
                Variables.setLote8("");
                Variables.setLote9("");
                Variables.setLote10("");
                Variables.setLote11("");
                Variables.setLote12("");
                Variables.setLote13("");
                Variables.setLote14("");*/
                return 0;

            }
        }
        catch (Exception e){
            return 0;
        }
    }

    /****************    Consulta para Seleccionar leche en tina Cuajado  *************/
    public String DAOC_select_leche_tina(String lote){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            cursor= db.rawQuery("SELECT leche_tina FROM cuajado WHERE lote='"+lote+"'", null);

            if (cursor.moveToPosition(0)) {
                myDbHelper.close();
                db.close();

                return cursor.getString(cursor.getColumnIndex("leche_tina"));

            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return "";

            }
        }
        catch (Exception e){
            return "";
        }
    }

    /****************    Consulta para Llenar la lista de pendientes CUAJADO    *************/
    public ArrayList<consultas> DAOListaLotes(){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM cuajado WHERE estatus_pendiente=0" +
                "", null);
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/



        ArrayList<consultas> pacientesArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.lote=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.lote[x]=cursor.getString(cursor.getColumnIndex("lote"));
                    cursor.moveToNext();
                }

                pacientesArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return pacientesArray;
    }

    /****************    Consulta para Llenar la lista de lotes Cuajado_Realizados    *************/
    public ArrayList<consultas> DAOListaCuajadoRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM cuajado WHERE fecha ='" +
                fecha + "'", null);

                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.cuajado = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.cuajado[x]=cursor.getString(cursor.getColumnIndex("lote"));
                    cursor.moveToNext();
                }
                empaqueArray.add(lista);
            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return empaqueArray;
    }
    /****************    Consulta para Llenar Cuajado si se viene de realizados    *************/
    public Cursor DAOLLenarCuajado(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha, fecha, lote, silo, num_equipo, num_tina, familia" +
                    ", leche_silo, ph_leche, porcen_grasa_leche, porce_proteina, leche_tina, porce_grasa_leche_tina, porce_prot_tina, crema_kilos" +
                    ", porce_grasa_crema, temp_adi_cuajo, ph_pasta_coag, hora_adi_cuajo, temp_cocido, estatus_guardado, estatus_pendiente, hora_inicio_desuerado " +
                    ", litros_suero, ph_desuerado, solidos_totales, pasta_obtenida, numero_moldes, kilos_pendientes, porcentaje_humedad " +
                    "FROM cuajado WHERE lote ='" +
                    lote + "'", null);
            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                return cursor;




            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return null;

            }
        }

        catch (Exception e){
            return null;

        }
    }

    /****************    Consulta para Cuajado Aditivos si viene de realizados      *************/
    public Cursor DAOLLenarCuajadoAditivos(String lote){
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT lote_cuajado, mp001, mp003, mp002, mp006, mp011, mp021" +
                    ", cr01, mp025, mp062, mp070, mp071, mp072, le04, le03" +
                    ", lote_mp001, lote_mp003, lote_mp002, lote_mp006, lote_mp011, lote_mp021, lote_cr01, lote_mp025, lote_mp062 " +
                    ", lote_mp070, lote_mp071, lote_mp072, lote_le04, lote_le03, estatus_guardado " +
                    "FROM cuajado_aditivos WHERE lote_cuajado ='" +
                    lote + "'", null);
            if (cursor.moveToPosition(0)) {

                //cursor.close();
                myDbHelper.close();
                db.close();
                return cursor;




            }else{
                cursor.close();
                myDbHelper.close();
                db.close();
                return null;

            }
        }

        catch (Exception e){
            return null;

        }
    }

    /****************    Consulta para UPDATE Cuajado      *************/
    public boolean DAOActualizarCuajado(String lote,String silo,String num_tina,String familia,String fecha,String leche_silo,
                              String porcen_grasa_leche,String ph_leche,String porce_proteina,
                              String leche_tina,String porce_grasa_leche_tina,
                              String porce_prot_tina,String Temp_adi_cuajo,String ph_pasta_coag,String hora_adi_cuajo,
                              String temp_cocido, String num_equipo, int estatus_guardado, int estatus_pendiente){


        db = myDbHelper.getWritableDatabase();

        try {
                db.execSQL("UPDATE cuajado SET " +
                        "fecha = " + "'" + fecha + "', lote = '" + lote + "',silo='" + silo + "',num_equipo = '" + num_equipo + "',familia='" + familia + "',leche_silo='" + leche_silo +
                        "',ph_leche='" + ph_leche + "',porcen_grasa_leche='" + porcen_grasa_leche + "',porce_proteina='" + porce_proteina + "', " +
                        "leche_tina='" + leche_tina + "',porce_grasa_leche_tina='" + porce_grasa_leche_tina + "',porce_prot_tina='" + porce_prot_tina /*+
                        "',crema_kilos='" + crema_kilos +"',porce_grasa_crema='"+porce_grasa_crema*/ + "',Temp_adi_cuajo='" + Temp_adi_cuajo + "'," +
                        "ph_pasta_coag='" + ph_pasta_coag + "',hora_adi_cuajo='" + hora_adi_cuajo + "',temp_cocido='" + temp_cocido + "', estatus_guardado='" + estatus_guardado +
                        "',estatus_pendiente='" + estatus_pendiente /*+"',hora_inicio_desuerado='"+hora_inicio_desuerado+"',litros_suero='"+litros_suero+
                        "',ph_desuerado='"+ph_desuerado+"',solidos_totales='"+solidos_totales+"',pasta_obtenida='"+pasta_obtenida+"',numero_moldes='"+numero_moldes+
                        "',kilos_pendientes='"+kilos_pendientes+"',porcentaje_humedad='"+porcentaje_humedad+"' "*/ +
                        "' WHERE lote='" + lote + "';");
                myDbHelper.close();
                db.close();
            return true;
            }
        catch (Exception e)
        {
           return false;
        }

    }
    /****************    Consulta para UPDATE Cuajado - Aditivos      *************/
    public boolean DAOActualizarCuajadoAditivos(String lote_cuajado, String mp001,String lote_mp001,String mp003,String lote_mp003,String mp002,String lote_mp002,
                                      String mp006,String lote_mp006,String mp011, String lote_mp011,
                                      String mp021,String lote_mp021,String cr01,String lote_cr01,
                                      String mp025,String lote_mp025,String mp062,String lote_mp062,
                                      String mp070, String lote_mp070, String mp071, String lote_mp071, String mp072, String lote_mp072, String le04, String lote_le04,
                                      String le03, String lote_le03,int estatus_guardado){

        db = myDbHelper.getWritableDatabase();
        try {
                db.execSQL("UPDATE cuajado_aditivos SET lote_cuajado='"+lote_cuajado+"',mp001='"+mp001+"',lote_mp001='"+lote_mp001+"',mp003='"+mp003+"',lote_mp003='"+lote_mp003+"',mp002='"+mp002+"',lote_mp002='"+lote_mp002+"',mp006='"+mp006+"',lote_mp006='"+lote_mp006+"'," +
                        "mp011='"+mp011+"',lote_mp011='"+lote_mp011+"',mp021='"+mp021+"',lote_mp021='"+lote_mp021+"',cr01='"+cr01+"',lote_cr01='"+lote_cr01+"'," +
                        "mp025='"+mp025+"',lote_mp025='"+lote_mp025+"',mp062='"+mp062+"', lote_mp062='"+lote_mp062+"' ,mp070='"+mp070+"' ,lote_mp070='"+lote_mp070+"' ,mp071='"+mp071+"' ,lote_mp071='"+lote_mp071+"' ,mp072='"+mp072+"',lote_mp072='"+lote_mp072+"' ," +
                        "le04='"+le04+"' ,lote_le04='"+lote_le04+"' ,le03='"+le03+"' ,lote_le03='"+lote_le03+"',estatus_guardado='"+estatus_guardado+"'"+
                        " WHERE lote_cuajado='"+lote_cuajado+"';");
                myDbHelper.close();
                db.close();
                return true;
            }
        catch (Exception e){
            return false;
        }
    }



    /****************    Consulta para llenar la bitacora      *************/

    public void DAOConsultaBitacora(String nombre, String modulo, String datos, String observaciones,String fecha)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO bitacora (nombre_usuario,modulo_modificado,datos_cambiados,observaciones,fecha) VALUES ('"
                    + nombre + "','"
                    + modulo + "','"
                    + datos + "','"
                    + observaciones + "','"
                    + fecha + "');");
            myDbHelper.close();
            db.close();
        }
        catch (Exception e)
        {}
    }



    /****************    Consulta para Configuracion IP      *************/
    public boolean DAOConfigIP(String ip_actual,String fecha){

        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            cursor= db.rawQuery("select ip_actual from configuracion", null);

            if (cursor.moveToPosition(0)) {

                db.execSQL("UPDATE configuracion SET ip_actual='"+ip_actual+"',fecha_modificacion='"+fecha+"'");

                myDbHelper.close();
                db.close();
            }

            else{

                db.execSQL("INSERT INTO configuracion (" +
                        "ip_actual,fecha_modificacion) " +
                        "VALUES ('"+ip_actual+"','"
                        + fecha
                        + "')");

                cursor.close();
                myDbHelper.close();
                db.close();

            }

            return true;
        }
        catch (Exception e){

            return false;

        }
    }

    /****************    Consulta para Select Configuracion IP      *************/
    public String DAOSelecConfigIP(){

        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            cursor= db.rawQuery("select ip_actual from configuracion", null);

            if (cursor.moveToPosition(0)) {



                myDbHelper.close();
                db.close();
                return cursor.getString(cursor.getColumnIndex("ip_actual"));

            }

            else{


                cursor.close();
                myDbHelper.close();
                db.close();
                return "";
            }


        }
        catch (Exception e){

            return "";

        }
    }

}
