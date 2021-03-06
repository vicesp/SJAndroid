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


            db.execSQL("DELETE FROM cuajado WHERE lote='" + lote + "'");

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
                              String temp_cocido, String num_equipo, int estatus_guardado, int estatus_pendiente, String fecha_hoy){

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
                        "ph_pasta_coag,hora_adi_cuajo,temp_cocido, num_equipo, estatus_guardado,estatus_pendiente, fecha_hoy) " +
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
                        + estatus_pendiente+",'"
                        +fecha_hoy+"' "
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
                                      String piezas_empacadas, String piezas_calidad, String fecha_hoy){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO empaque (" +
                    " lote_origen,fecha,cod_prod,prod_terminado,lote,piezas_almacen,piezas_reproceso,temp_pt,hora_inicio_pt,cod_prod_restos," +
                    "lote_restos,cantidad_restos,maquina_usar,vacio_ulma,gas_ulma,temp_ulma,temp_sellado_ulma,oxigeno_ulma,vacio_ultra," +
                    "temp_ultra,hora_fin_ultra,lote_fondo,lote_tapa,lote_funda,observaciones,piezas_empacadas,piezas_calidad, fecha_hoy)" +
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
                    + piezas_calidad+"','"
                    +fecha_hoy
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

    public ArrayList<consultas> DAOGetTodosProductos(String codigo_familia, int fromWhere)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
       /* if (fromWhere == 0) {
            cursor = db.rawQuery("SELECT codigo_producto, nombre_producto FROM familias", null);
        }
        else {
            cursor = db.rawQuery("SELECT codigo_producto, nombre_producto FROM cat_productos WHERE eliminado = 0 AND codigo_familia IS NOT NULL", null);
        }*/
        if (fromWhere==0) {
            cursor = db.rawQuery("SELECT codigo_producto, nombre_producto FROM cat_productos WHERE eliminado = 0 AND codigo_familia IS NOT NULL", null);
        }
        else{
            cursor = db.rawQuery("SELECT codigo_producto, nombre_producto FROM cat_productos WHERE eliminado = 0", null);

        }

        ArrayList<consultas> productosArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if (cursor.moveToFirst()) {
                Log.i("LLgeue","aca");
                consultas lista = new consultas();
                lista.producto = new String[cursor.getCount()];

                for (int x = 0; x < cursor.getCount(); x++) {

                    lista.producto[x] = cursor.getString(cursor.getColumnIndex("codigo_producto")) + "-" + cursor.getString(cursor.getColumnIndex("nombre_producto"));
                    cursor.moveToNext();
                }

                productosArray.add(lista);
            }
        }
         if (productosArray.isEmpty()){
            Log.i("LLgeue","aqui");
            consultas lista = new consultas();
            lista.producto = new String[1];

            lista.producto[0]="No Hay Productos en esa familia- favor de editarlos";
            productosArray.add(lista);

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

        cursor = db.rawQuery("SELECT nombre_producto, caducidad, eliminado, codigo_familia FROM cat_productos WHERE codigo_producto = '" + codigo + "' AND eliminado = 0;", null);
        ArrayList<consultas> productosArray = new ArrayList<consultas>();
        consultas lista = new consultas();
        lista.producto=new String[4];


        if (cursor != null ) {
            if  (cursor.moveToFirst()) {

                lista.producto[0]=cursor.getString(cursor.getColumnIndex("nombre_producto"));
                lista.producto[1]=cursor.getString(cursor.getColumnIndex("caducidad"));
                lista.producto[2]=cursor.getString(cursor.getColumnIndex("eliminado"));
                lista.producto[3]=cursor.getString(cursor.getColumnIndex("codigo_familia"));

            }
        }
        cursor.close();
        myDbHelper.close();
        db.close();
        return lista.producto;
    }

    /*********** Actualizar Productos **************/
    public boolean DAOActualizarProductos(String codigo, String nombre, String caducidad, int eliminar, String familia) {
        boolean check = false;
        cursor=null;
        db = myDbHelper.getWritableDatabase();
        try{


                db.execSQL("UPDATE cat_productos SET codigo_producto = '"+codigo+"', nombre_producto = '"+nombre+"', caducidad = '"+caducidad+"', eliminado = "+eliminar+", codigo_familia = '"+familia+"' WHERE codigo_producto ='"+codigo+"';");


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

    public boolean DAOGuardarProducto(String codigo, String descripcion, String caducidad, String familia)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        try {

            db.execSQL("INSERT INTO cat_productos(codigo_producto, nombre_producto, caducidad, eliminado, codigo_familia) VALUES ('" + codigo + "','" + descripcion + "','" + caducidad + "', 0, '"+familia+"');" );
        return true;
        }
        catch(Exception e){

        return false;
    }
    }

    /*********** Actualizar Familias **************/
    public boolean DAOActualizarFamilia(String codigo, String nombre, int eliminar, int cuajado, int fundido, String codigoNuevo) {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
try {
            db.execSQL("UPDATE cat_familias SET codigo_familia = '" + codigoNuevo + "', nombre_familia = '" + nombre + "', eliminado = " + eliminar + ", cuajado = " + cuajado + ", fundido = " + fundido + " WHERE codigo_familia ='" + codigo + "';");
            return true;
        }
        catch(Exception e)
        {
            return false;
        }

    }
    /*********** Guardar Nueva Familia **************/

    public boolean DAOGuardarFamilia(String codigo, String nombre, int cuajado, int fundido)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        try {

            db.execSQL("INSERT INTO cat_familias (codigo_familia, nombre_familia, cuajado, fundido) VALUES ('" + codigo + "','" + nombre + "','"+cuajado+"','"+fundido+"' );" );
            return true;
        }
        catch(Exception e){
            Log.i("Error:", e.toString());
            return false;
        }
    }

    /****************    Consulta para Llenar la lista de lotes Empaque_Realizdo    *************/
    public ArrayList<consultas> DAOListaEmpaqueRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM empaque WHERE fecha_hoy ='" +
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
                              String tipo_cuajada_1,String tipo_cuajada_2,String tipo_cuajada_3,String cj01_3,String lote_cj01_3,String ph_cj01_3, String fecha_hoy, String tina, int bandera){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO fundido (" +
                    " linea,fecha,num_fundida,lote,familia,cj01,lote_cj01,ph_cj01,cj011,lote_cj011,ph_cj011,mp005,lote_mp005,mp015,lote_mp015," +
                    "s0101,lote_s0101,mp007,lote_mp007,sa01,lote_sa01,mp024,lote_mp024,mp078,lote_mp078,cj02,lote_cj02,agua,tipo_crema," +
                    "lote_tipo_crema, cantidad_crema, familia_reproceso,lote_fami_repro,cantidad_fami_repro,temperatura,peso_total,texturizador,lote_texturizador," +
                    "cj01_1,lote_cj01_1,ph_cj01_1,cj01_2,lote_cj01_2,ph_cj01_2,tipo_crema2,lote_tipo_crema2,cantidad_crema2, tipo_cuajada_1, tipo_cuajada_2, tipo_cuajada_3," +
                    " cj01_3, lote_cj01_3, ph_cj01_3, fecha_hoy, tina, bandera) " +
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
                    + ph_cj01_3+"','"
                    + fecha_hoy+"','"
                    +tina+"','"+
                    +bandera+
                    "')");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean DAOActualizarFundido(String lote, String linea,String fecha,String num_fundida,String familia,String cj01,String lote_cj01,
                              String ph_cj01,String cj011,String lote_cj011, String ph_cj011,
                              String mp005,String lote_mp005,String mp015,String lote_mp015,
                              String s0101,String lote_s0101,String mp007,String lote_mp007,
                              String sa01, String lote_sa01, String mp024, String lote_mp024, String mp078, String lote_mp078, String cj02,
                              String lote_cj02,String agua,String tipo_crema,String lote_tipo_crema,String cantidad_crema,String familia_reproceso,
                              String lote_fami_repro, String cantidad_fami_repro, String temperatura, String peso_total, String texturizador, String lote_texturizador,
                              String cj01_1,String lote_cj01_1,String ph_cj01_1,String cj01_2,String lote_cj01_2,String ph_cj01_2,String tipo_crema2,String lote_crema2,String cantidad_crema2,
                              String tipo_cuajada_1,String tipo_cuajada_2,String tipo_cuajada_3,String cj01_3,String lote_cj01_3,String ph_cj01_3, String fecha_hoy, String tina, int bandera) {


        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE fundido SET "+
                     "linea='"+linea+"',"+
                     "fecha='"+fecha+"',"+
                     "num_fundida='"+num_fundida+"',"+
                     "lote='"+lote+"',"+
                     "familia='"+familia+"',"+
                     "cj01='"+cj01+"',"+
                     "lote_cj01='"+lote_cj01+"',"+
                     "ph_cj01='"+ph_cj01+"',"+
                     "cj011='"+cj011+"',"+
                     "lote_cj011='"+lote_cj011+"',"+
                     "ph_cj011='"+ph_cj011+"',"+
                     "mp005='"+mp005+"',"+
                     "lote_mp005='"+lote_mp005+"',"+
                     "mp015='"+mp015+"',"+
                     "lote_mp015='"+lote_mp015+"',"+
                     "s0101='"+s0101+"',"+
                     "lote_s0101='"+lote_s0101+"',"+
                     "mp007='"+mp007+"',"+
                     "lote_mp007='"+lote_mp007+"',"+
                     "sa01='"+sa01+"',"+
                     "lote_sa01='"+lote_sa01+"',"+
                     "mp024='"+mp024+"',"+
                     "lote_mp024='"+lote_mp024+"',"+
                     "mp078='"+mp078+"',"+
                     "lote_mp078='"+lote_mp078+"',"+
                     "cj02='"+cj02+"',"+
                     "lote_cj02='"+lote_cj02+"',"+
                     "agua='"+agua+"',"+
                     "tipo_crema='"+tipo_crema+"',"+
                     "lote_tipo_crema='"+lote_tipo_crema+"',"+
                     "cantidad_crema='"+cantidad_crema+"',"+
                     "familia_reproceso='"+familia_reproceso+"',"+
                     "lote_fami_repro='"+lote_fami_repro+"',"+
                     "cantidad_fami_repro='"+cantidad_fami_repro+"',"+
                     "temperatura='"+temperatura+"',"+
                     "peso_total='"+peso_total+"',"+
                     "texturizador='"+texturizador+"',"+
                     "lote_texturizador='"+lote_texturizador+"',"+
                     "cj01_1='"+cj01_1+"',"+
                     "lote_cj01_1='"+lote_cj01_1+"',"+
                     "ph_cj01_1='"+ph_cj01_1+"',"+
                     "cj01_2='"+cj01_2+"',"+
                     "lote_cj01_2='"+lote_cj01_2+"',"+
                     "ph_cj01_2='"+ph_cj01_2+"',"+
                     "tipo_crema2='"+tipo_crema2+"',"+
                     "lote_tipo_crema2='"+lote_crema2+"',"+
                     "cantidad_crema2='"+cantidad_crema2+"',"+
                     "tipo_cuajada_1='"+tipo_cuajada_1+"',"+
                     "tipo_cuajada_2='"+tipo_cuajada_2+"',"+
                     "tipo_cuajada_3='"+tipo_cuajada_3+"',"+
                     "cj01_3='"+cj01_3+"',"+
                     "lote_cj01_3='"+lote_cj01_3+"',"+
                     "ph_cj01_3='"+ph_cj01_3+"',"+
                     "fecha_hoy='"+fecha_hoy+"',"+
                     "tina='"+tina+"',"+
                            "bandera='" + bandera + "'" +
                            " WHERE lote = " + lote + ";"
            );
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


        /****************    Consulta para Llenar Fundido de busqueda   *************/
    public Cursor DAOLLenarFundido(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT linea, num_fundida, fecha, lote, familia, cj01, ph_cj01" +
                    ", lote_cj01, tipo_cuajada_1, cj01_1, ph_cj01_1, lote_cj01_1, tipo_cuajada_2, cj01_2, ph_cj01_2" +
                    ", lote_cj01_2, tipo_cuajada_3, cj01_3, ph_cj01_3, lote_cj01_3, cj011, lote_cj011, ph_cj011, texturizador " +
                    ", lote_texturizador, tipo_crema, lote_tipo_crema, cantidad_crema, tipo_crema2, lote_tipo_crema2, cantidad_crema2"+
                    ", mp005, mp015, s0101, mp007, sa01, mp024, mp078, cj02, agua, familia_reproceso, lote_fami_repro, cantidad_fami_repro"+
                    ", temperatura, peso_total, lote_mp005, lote_mp015, lote_s0101, lote_mp007, lote_sa01, lote_mp024, lote_mp078"+
                    ", lote_cj02, fecha_hoy, tina, bandera "+
                    "FROM fundido WHERE lote ='" +
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



    /****************    Consulta para Llenar la lista de lotes Fundido-Realizado    *************/
    public ArrayList<consultas> DAOListaFundidoRealizado(String fecha){


        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM fundido WHERE fecha_hoy ='" +
                fecha + "'", null);

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
                                   String mp010,String lote_mp010,String mp025,
                              String lote_mp025,String mp026,String lote_mp026,String mp027,String lote_mp027,
                                   String mp028,String lote_mp028,String mp031,String lote_mp031,String mp012,
                              String lote_mp012,String mp013,String lote_mp013,String mp014,String lote_mp014, String kilos_totales, int num_consecutivo, String fecha_hoy){
        cursor=null;
        db = myDbHelper.getWritableDatabase();



        try {
            db.execSQL("INSERT INTO texturizador (" +
                    " lote,fecha,texturizador,mp002,lote_mp002,mp003,lote_mp003,mp004,lote_mp004,mp005,lote_mp005,mp006,lote_mp006,mp007,lote_mp007," +
                    "mp008,lote_mp008,mp009,lote_mp009,mp010,lote_mp010,mp025,lote_mp025,mp026,lote_mp026,mp027,lote_mp027,mp028," +
                    "lote_mp028,mp031,lote_mp031,mp012,lote_mp012,mp013,lote_mp013,mp014,lote_mp014,kilos_totales,num_consecutivo, fecha_hoy) " +
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
                    + num_consecutivo+",'"
                    +fecha_hoy
                    + "')");

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
                                       String mp010,String lote_mp010,String mp025,
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
            cursor= db.rawQuery("SELECT "+columna+ " from texturizador_actualizador WHERE id='"+id+"' AND eliminado=0 ", null);

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
                "FROM texturizador WHERE fecha_hoy ='" +
                fecha + "'", null);
                /*"WHERE p.id_sexo=1 order by p.apellido_paterno ASC", null);*/
        ArrayList<consultas> pacientesArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"));
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
                    ", lote_mp008, lote_mp009, lote_mp010, lote_mp025, lote_mp026, lote_mp027, lote_mp028" +
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
    public boolean DAOPT(String lote,String fecha, String codigo_pt, String num_viaje, String num_piezas, String kilos, String num_fundida,String fecha_hora){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO PT (" +
                    " lote,fecha,codigo_pt,num_viaje,num_piezas,kilos,numero_fundida, fecha_hoy) " +
                    "VALUES ('" + lote + "','"
                    + fecha + "','"
                    + codigo_pt + "','"
                    + num_viaje + "','"
                    + num_piezas + "','"
                    + kilos + "','"
                    + num_fundida+"','"
                    +fecha_hora
                    + "')");

            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    /****************    Consulta para actualizar Prpducto Terminado   *************/

    public boolean DAOActualizarPT(String lote,String fecha, String codigo_pt,
                                   String num_viaje, String num_piezas, String kilos,
                                   String num_fundida){

        db = myDbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE PT SET fecha = '"+fecha+"', lote = '"+lote+"', codigo_pt='"+codigo_pt+"', num_viaje='"+num_viaje+"',"+
                    "num_piezas='"+num_piezas+"', kilos='"+kilos+"', numero_fundida='"+num_fundida+"' "+
                    "WHERE lote ='"+lote+"';");
            myDbHelper.close();
            db.close();
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

    /****************    Consulta para Llenar Producto si se viene de realizados    *************/
    public Cursor DAOLLenarProducto(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha, codigo_pt, num_viaje, num_piezas, kilos, numero_fundida FROM PT WHERE lote ='" +
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

    /****************    Consulta para Llenar la lista de lotes TEXTURIZADOR_REALIZADOS    *************/
    public ArrayList<consultas> DAOListaProductoRealizado(String fecha){

            db = myDbHelper.getWritableDatabase();
            cursor=null;
            cursor = db.rawQuery("SELECT lote " +
                    "FROM PT WHERE fecha ='" +
                    fecha + "'", null);
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
                "FROM cuajado WHERE fecha_hoy ='" +
                fecha + "'", null);
        Log.i("Fecha",fecha);
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
    /****************    Consulta para Llenar Cuajado si se viene de realizados    *************/
    public Cursor DAOLLenarCuajado(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha, fecha_hoy, lote, silo, num_equipo, num_tina, familia" +
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

    /****************    Consulta para Laboratorio Calidad     *************/
    public boolean DAOLaboratorioCalidad(String fecha, String lote,String familia,String producto,String codigo_prod, String codigo_fam,String apariencia,String sabor,
                              String color,String aroma,String observaciones_sabor, String rallado,
                              String observaciones_rallado,String fundido,String observaciones_fundido,String hebrado,
                              String observaciones_hebrado,String grasa_residual,String humedad,String ph,
                              String grasa_total, String humedad_remuestreo, String ph_remuestreo, String grasa_remuestreo, String necesidad_remuestreo, String observaciones,
                                         String ralladoqr, String observaciones_ralladoqr, String observaciones_apariencia, String fecha_hoy, String observaciones_color, String tajo)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            db.execSQL("INSERT INTO laboratorio_calidad (fecha, lote, familia, producto, codigo_prod, codigo_fam, apariencia,sabor," +
                            "color, aroma, observaciones_sabor, rallado, observaciones_rallado, fundido, observaciones_fundido," +
                            "hebrado, observaciones_hebrado, grasa_residual, humedad, ph, grasa_total, humedad_remuestreo,ph_remuestreo, grasa_remuestreo," +
                            "necesidad_remuestreo, ralladoqr, observaciones_ralladoqr, observaciones_apariencia,fecha_hoy, observaciones_color, tajo) VALUES ('" +

                            fecha + "','" +
                            lote + "','" +
                            familia + "','" +
                            producto + "','" +
                            codigo_prod + "','" +
                            codigo_fam + "','" +
                            apariencia + "','" +
                            sabor + "','" +
                            color + "','" +
                            aroma + "','" +
                            observaciones_sabor + "','" +
                            rallado + "','" +
                            observaciones_rallado + "','" +
                            fundido + "','" +
                            observaciones_fundido + "','" +
                            hebrado + "','" +
                            observaciones_hebrado + "','" +
                            grasa_residual + "','" +
                            humedad + "','" +
                            ph + "','" +
                            grasa_total + "','" +
                            humedad_remuestreo + "','" +
                            ph_remuestreo + "','" +
                            grasa_remuestreo + "','" +
                            necesidad_remuestreo + "','" +
                            ralladoqr + "','" +
                            observaciones_ralladoqr + "','" +
                            observaciones_apariencia + "','"
                            + fecha_hoy + "','" +
                            observaciones_color + "','" +
                            tajo +
                            "');"
            );
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para actualizar Laboratorio Calidad   *************/

    public boolean DAOActualizarLaboratorioCalidad(
            String fecha, String lote,String familia,String producto,String codigo_prod,
            String codigo_fam,String apariencia,String sabor,
            String color,String aroma,String observaciones_sabor, String rallado,
            String observaciones_rallado,String fundido,String observaciones_fundido,String hebrado,
            String observaciones_hebrado,String grasa_residual,String humedad,String ph,
            String grasa_total, String humedad_remuestreo, String ph_remuestreo, String grasa_remuestreo,
            String necesidad_remuestreo, String observaciones, String ralladoqr, String observaciones_ralldoqr,
            String observaciones_apariencia, String observaciones_color, String tajo, String loteNuevo, String codigo_prodNuevo){

        db = myDbHelper.getWritableDatabase();
        try {db.execSQL("UPDATE laboratorio_calidad SET " +
                "lote = '" + loteNuevo + "', " +
                "familia='" + familia + "', " +
                "producto='" + producto + "'," +
                "codigo_prod='" + codigo_prodNuevo + "', " +
                "codigo_fam='" + codigo_fam + "', " +
                "apariencia='" + apariencia + "'," +
                "sabor='" + sabor + "'," +
                "color='" + color + "', " +
                "aroma='" + aroma + "', " +
                "observaciones_sabor='" + observaciones_sabor + "', " +
                "rallado='" + rallado + "'," +
                "observaciones_rallado='" + observaciones_rallado + "', " +
                "fundido='" + fundido + "', " +
                "observaciones_fundido='" + observaciones_fundido + "'," +
                "hebrado='" + hebrado + "', " +
                "observaciones_hebrado='" + observaciones_hebrado + "', grasa_residual='" + grasa_residual + "'," +
                "humedad='" + humedad + "', " +
                "ph='" + ph + "', " +
                "grasa_total='" + grasa_total + "', " +
                "humedad_remuestreo='" + humedad_remuestreo + "'," +
                "ph_remuestreo='" + ph_remuestreo + "', " +
                "grasa_remuestreo='" + grasa_remuestreo + "'," +
                "necesidad_remuestreo='" + necesidad_remuestreo + "'," +
                "ralladoqr='" + ralladoqr + "', " +
                "observaciones_ralladoqr='" + observaciones_ralldoqr + "'," +
                "observaciones_apariencia='" + observaciones_apariencia + "'" + ", " +
                "observaciones_color = '"+observaciones_color+ "', " +
                "tajo = '"+tajo+"'"+
                "WHERE fecha_hoy = '" + fecha + "' AND codigo_prod = '" + codigo_prod + "' AND lote = '"+lote+"';");

            myDbHelper.close();
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }



    /****************    Consulta para Llenar la lista de lotes Laboratorio    *************/
    public ArrayList<consultas> DAOListaLaboratorioRealizado(String fecha){
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote, codigo_prod, producto " +
                "FROM laboratorio_calidad WHERE fecha_hoy ='" +
                fecha + "'", null);
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"))+"-"+cursor.getString(cursor.getColumnIndex("codigo_prod"))+"/" + cursor.getString(cursor.getColumnIndex("producto"));
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

    /****************    Consulta para LLenar Laboratorio Calidad     *************/
    public Cursor DAOLLenarLaboratorio(String lote, String codigo_prod) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha, lote, familia, producto, codigo_prod, codigo_fam, apariencia, sabor" +
                    ", color, aroma, observaciones_sabor, rallado, observaciones_rallado, fundido, observaciones_fundido, hebrado" +
                    ", observaciones_hebrado, grasa_residual, humedad, ph, grasa_total, humedad_remuestreo, ph_remuestreo, grasa_remuestreo, necesidad_remuestreo," +
                    " ralladoqr, observaciones_ralladoqr, observaciones_apariencia, fecha_hoy, observaciones_color, tajo " +

                    "FROM laboratorio_calidad WHERE lote ='" + lote + "' AND codigo_prod ='"+codigo_prod+"';", null);
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
    /****************    Consulta para Obtener Familias     *************/
    public Cursor DAOGetCursorTodosFamilias(String codigo)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT codigo_familia, tajo  FROM cat_productos WHERE codigo_producto =  '" + codigo+"';", null);
        if (cursor.moveToPosition(0)) {

            //cursor.close();
            myDbHelper.close();
            db.close();
            Log.i("Cursor", cursor.getString(cursor.getColumnIndex("tajo")));
            return cursor;




        }else{
            cursor.close();
            myDbHelper.close();
            db.close();
            return null;

        }
    }
    /****************    Consulta para Obtener Familias     *************/
    public Cursor DAOGetCursorFamilia(String codigo)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT cuajado, fundido  FROM cat_familias WHERE codigo_familia =  '" + codigo+"';", null);
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
    /****************    Consulta para Obtener Nombre Familias     *************/
    public String DAOGetNombreFamilia(String codigo)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT nombre_familia FROM cat_familias WHERE codigo_familia =  '" + codigo+"';", null);
        if (cursor.moveToPosition(0)) {

            //cursor.close();
            myDbHelper.close();
            db.close();

            return cursor.getString(cursor.getColumnIndex("nombre_familia"));




        }else{
            cursor.close();
            myDbHelper.close();
            db.close();
            return null;

        }
    }

    /****************    Consulta para Obtener Familias     *************/
    public ArrayList<consultas> DAOGetTodosFamilias(boolean forFundido, boolean forCuajado)
    {
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        if(forFundido){
            cursor = db.rawQuery("SELECT codigo_familia, nombre_familia FROM cat_familias WHERE eliminado = 0 AND fundido =1 " + "", null);
        }
        else if(forCuajado){
            cursor = db.rawQuery("SELECT codigo_familia, nombre_familia FROM cat_familias WHERE eliminado = 0 AND cuajado = 1 " + "", null);
        }
        else {
            cursor = db.rawQuery("SELECT codigo_familia, nombre_familia FROM cat_familias WHERE eliminado = 0 " + "", null);
        }
        ArrayList<consultas> productosArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {

                consultas lista = new consultas();
                lista.producto=new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {

                    lista.producto[x]=cursor.getString(cursor.getColumnIndex("codigo_familia"))+"-"+cursor.getString(cursor.getColumnIndex("nombre_familia"));
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


    /****************    Consulta para Crema lab      *************/

    public Boolean DAOCremaLab(String lote, String fecha, String sabor, String sabor_observaciones, String color, String color_observaciones,
                               String aroma, String aroma_observaciones, String escurrimiento, String escurrimiento_observaciones,
                                String fluidez, String fluidez_observaciones, String ph, String solidos, String acidez, String grasa, String fecha_hoy,
                               String producto, String codigo_producto){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            db.execSQL("INSERT INTO crema_lab (lote, fecha, sabor, sabor_observaciones, color, color_observaciones, aroma,aroma_observaciones," +
                    " escurrimiento, escurrimiento_observaciones, fluidez, fluidez_observaciones, ph, solidos, acidez, grasa, fecha_hoy, producto, codigo_producto" +
                    ") " +
                    "VALUES ('"+
                    lote+"','"+
                    fecha+"','"+
                    sabor+"','"+
                    sabor_observaciones+"','"+
                    color+"','"+
                    color_observaciones+"','"+
                    aroma+"','"+
                    aroma_observaciones+"','"+
                    escurrimiento +"','"+
                    escurrimiento_observaciones+"','"+
                    fluidez+"','"+
                    fluidez_observaciones+"','"+
                    ph+"','"+
                    solidos+"','"+
                    acidez+"','"+
                    grasa+"','"+
                    fecha_hoy+"','"+
                    producto+"','"+
                    codigo_producto+

                    "')");
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para ActualizarCrema lab      *************/

    public Boolean DAOActualizaCremaLab(String lote, String fecha, String sabor, String sabor_observaciones, String color, String color_observaciones,
                                        String aroma, String aroma_observaciones, String escurrimiento, String escurrimiento_observaciones,
                                        String fluidez, String fluidez_observaciones, String ph, String solidos, String acidez, String grasa, String loteNuevo,
                                        String producto, String codigo_producto){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            db.execSQL("UPDATE crema_lab SET lote = '"+loteNuevo+"'," +
                    " sabor= '"+sabor+"'," +
                    " sabor_observaciones= '"+sabor_observaciones+"', " +
                    " color= '"+color+"'," +
                    " color_observaciones='"+color_observaciones+"'," +
                    " aroma='"+aroma+"'," +
                    " aroma_observaciones='"+aroma_observaciones+"'," +
                    " escurrimiento='"+escurrimiento+"'," +
                    " escurrimiento_observaciones='"+escurrimiento_observaciones+"'," +
                    " fluidez='"+fluidez+"'," +
                    " fluidez_observaciones='"+fluidez_observaciones+"'," +
                    " ph='"+ph+"'," +
                    " solidos='"+solidos+"'," +
                    " acidez='"+acidez+"'," +
                    " grasa='"+grasa +"'," +
                    " producto='"+producto+"'," +
                    " codigo_producto='"+codigo_producto+
                    "' " +
                    " WHERE lote = '"+lote+"';" +

            "");
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para Llenar la lista de lotes Laboratorio Crema   *************/
    public ArrayList<consultas> DAOListaCremaLabRealizado(String fecha){
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote, codigo_producto, producto " +
                "FROM crema_lab WHERE fecha_hoy ='" +
                fecha + "'", null);
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"))+"-"+cursor.getString(cursor.getColumnIndex("codigo_producto"))+"/" + cursor.getString(cursor.getColumnIndex("producto"));
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
    /****************    Consulta para LLenar Crema Lab     *************/
    public Cursor DAOLLenarCremaLab(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT lote, sabor, sabor_observaciones, color, color_observaciones, aroma ,aroma_observaciones, " +
                    "escurrimiento, escurrimiento_observaciones, fluidez, fluidez_observaciones, ph, solidos, acidez, grasa, fecha_hoy," +
                    "producto, codigo_producto " +
                    "FROM crema_lab WHERE lote ='" + lote +"';", null);
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
    /****************    Consulta para Cuajadas lab      *************/

    public Boolean DAOCuajadasLab(String lote, String fecha, String hum_cuaj, String gras_cuaj, String ph_cuaj, String ph_sue,
                                  String ac_sue, String st_sue, String fecha_hoy, String grasa_check, String tina_cuajada, String tipo_cuajada) {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            db.execSQL("INSERT INTO cuajadas_lab (lote, fecha, hum_cuaj, gras_cuaj, ph_cuaj, ph_sue, ac_sue,st_sue, fecha_hoy, grasa_check, tina_cuajada, tipo_cuajada) " +
                    "VALUES ('"+
                    lote+"','"+
                    fecha+"','"+
                    hum_cuaj+"','"+
                    gras_cuaj+"','"+
                    ph_cuaj+"','"+
                    ph_sue+"','"+
                    ac_sue+"','"+
                    st_sue+"','"+
                    fecha_hoy+"','"+
                    grasa_check+"','"+
                    tina_cuajada+"','"+
                    tipo_cuajada +
                    "')");
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para ActualizarCuajadas lab      *************/

    public Boolean DAOActualizaCuajadasLab(String lote, String fecha, String hum_cuaj, String gras_cuaj, String ph_cuaj, String ph_sue,
                                           String ac_sue, String st_sue, String grasa_check, String tina_cuajada, String tipo_cuajada, String loteNuevo) {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE cuajadas_lab SET " +
                    " lote = '"+loteNuevo+"'," +
                    " hum_cuaj= '"+hum_cuaj+"'," +
                    " gras_cuaj= '"+gras_cuaj+"', " +
                    " ph_cuaj= '"+ph_cuaj+"'," +
                    " ph_sue='"+ph_sue+"'," +
                    " ac_sue='"+ac_sue+"'," +
                    " st_sue='"+st_sue+"'," +
                    " grasa_check='"+grasa_check+"',"+
                    " tina_cuajada='"+tina_cuajada+"',"+
                    " tipo_cuajada='" + tipo_cuajada + "' " +
                    "WHERE lote = '"+lote+"';" +

                    "");
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para Llenar la lista de lotes Laboratorio Cuajadas   *************/
    public ArrayList<consultas> DAOListaCuajadasLabRealizado(String fecha){
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote, tina_cuajada " +
                "FROM cuajadas_lab WHERE fecha_hoy ='" +
                fecha + "'", null);
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x] = cursor.getString(cursor.getColumnIndex("lote")) + "-Tina No. " + cursor.getString(cursor.getColumnIndex("tina_cuajada"));
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
    /****************    Consulta para LLenar Cuajadas Lab     *************/
    public Cursor DAOLLenarCuajadasLab(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT fecha_hoy, hum_cuaj, gras_cuaj, ph_cuaj, ph_sue, ac_sue, st_sue, grasa_check, tina_cuajada, tipo_cuajada, lote " +
                    "FROM cuajadas_lab WHERE lote ='" + lote +"';", null);
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

    /****************    Consulta para Requeson Lab    *************/
    public boolean DAORequesonLab(String fecha, String lote,String familia,String producto,String codigo_prod, String codigo_fam,String apariencia,
                                  String sabor, String color,String aroma,String observaciones_sabor,String humedad, String ph, String grasa_total,
                                  String humedad_remuestreo, String ph_remuestreo, String grasa_remuestreo, String necesidad_remuestreo,
                                  String observaciones_apariencia, String fecha_hoy, String observaciones_color, String untabilidad, String observaciones_untabilidad)
    {
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {

            db.execSQL("INSERT INTO requeson_lab (fecha, lote, familia, producto, codigo_prod, codigo_fam, apariencia,sabor," +
                            "color, aroma, observaciones_sabor,humedad, ph, grasa_total, humedad_remuestreo,ph_remuestreo," +
                            " grasa_remuestreo,necesidad_remuestreo, observaciones_apariencia,fecha_hoy, observaciones_color, untabilidad, observaciones_untabilidad) " +
                            "VALUES ('" +


                            fecha + "','" +
                            lote + "','" +
                            familia + "','" +
                            producto + "','" +
                            codigo_prod + "','" +
                            codigo_fam + "','" +
                            apariencia + "','" +
                            sabor + "','" +
                            color + "','" +
                            aroma + "','" +
                            observaciones_sabor + "','" +
                            humedad + "','" +
                            ph + "','" +
                            grasa_total + "','" +
                            humedad_remuestreo + "','" +
                            ph_remuestreo + "','" +
                            grasa_remuestreo + "','" +
                            necesidad_remuestreo + "','" +
                            observaciones_apariencia + "','" +
                            fecha_hoy + "','" +
                            observaciones_color + "','" +
                            untabilidad+"','" +
                            observaciones_untabilidad+"'" +
                            ");"
            );
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }
    }
    /****************    Consulta para actualizar RequesonLab  *************/

    public boolean DAOActualizarRequesonLab(String fecha, String lote,String familia,String producto,String codigo_prod, String codigo_fam,String apariencia,
                                            String sabor, String color,String aroma,String observaciones_sabor,String humedad, String ph, String grasa_total,
                                            String humedad_remuestreo, String ph_remuestreo, String grasa_remuestreo, String necesidad_remuestreo,
                                            String observaciones_apariencia, String observaciones_color, String untabilidad, String observaciones_untabilidad, String loteNuevo){

        db = myDbHelper.getWritableDatabase();

        try {db.execSQL("UPDATE requeson_lab SET " +
                "lote = '" + loteNuevo + "', " +
                "familia='" + familia + "', " +
                "producto='" + producto + "'," +
                "codigo_prod='" + codigo_prod + "', " +
                "codigo_fam='" + codigo_fam + "', " +
                "apariencia='" + apariencia + "'," +
                "sabor='" + sabor + "'," +
                "color='" + color + "', " +
                "aroma='" + aroma + "', " +
                "observaciones_sabor='" + observaciones_sabor + "', " +
                "humedad='" + humedad + "', " +
                "ph='" + ph + "', " +
                "grasa_total='" + grasa_total + "', " +
                "humedad_remuestreo='" + humedad_remuestreo + "'," +
                "ph_remuestreo='" + ph_remuestreo + "', " +
                "grasa_remuestreo='" + grasa_remuestreo + "'," +
                "necesidad_remuestreo='" + necesidad_remuestreo + "'," +
                "observaciones_apariencia='" + observaciones_apariencia + "'" + ", " +
                "observaciones_color = '"+observaciones_color+ "', " +
                "untabilidad='"+untabilidad+"',"+
                "observaciones_untabilidad='"+observaciones_untabilidad+"' "+
                 "WHERE lote = '"+lote+"';");

            myDbHelper.close();
            db.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }



    /****************    Consulta para Llenar la lista de Requeson Lab   *************/
    public ArrayList<consultas> DAOListaRequesonLab(String fecha){
        db = myDbHelper.getWritableDatabase();
        cursor=null;
        cursor = db.rawQuery("SELECT lote, codigo_prod, producto " +
                "FROM requeson_lab WHERE fecha_hoy ='" +
                fecha + "'", null);
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for(int x=0;x< cursor.getCount();x++)
                {
                    lista.empaque[x]=cursor.getString(cursor.getColumnIndex("lote"))+"-"+cursor.getString(cursor.getColumnIndex("codigo_prod"))+"/" + cursor.getString(cursor.getColumnIndex("producto"));
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

    /****************    Consulta para LLenar Requeson    *************/
    public Cursor DAOLLenarRequesonLab(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT lote, familia, producto, codigo_prod, codigo_fam, apariencia,sabor," +
                                    "color, aroma, observaciones_sabor,humedad, ph, grasa_total, humedad_remuestreo,ph_remuestreo,"+
                                    "grasa_remuestreo,necesidad_remuestreo, observaciones_apariencia,fecha_hoy, observaciones_color," +
                                    "untabilidad, observaciones_untabilidad " +

                    "FROM requeson_lab WHERE lote ='" + lote + "';", null);
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
    /****************    Consulta para Detector de Metales    *************/

    public boolean DAODetectorMetales(String lote, String usuario,	String fecha_hoy,	String fecha,	String hr_campo1,	String hr_campo2,	String hr_campo3,	String hr_campo4,
                                      String hr_campo5,	String hr_campo6,	String hr_campo7,	String hr_campo8,	String hr_campo9,	String hr_campo10,	String hr_campo11,
                                      String hr_campo12,	String hr_campo13,	String hr_campo14,	String hr_campo15,	String prod_campo1,	String prod_campo2,	String prod_campo3,
                                      String prod_campo4,	String prod_campo5,	String prod_campo6,	String prod_campo7,	String prod_campo8,	String prod_campo9,	String prod_campo10,
                                      String prod_campo11,	String prod_campo12,	String prod_campo13,	String prod_campo14,	String prod_campo15,	String paquete_a_campo1,
                                      String paquete_b_campo1,	String paquete_a_campo2,	String paquete_b_campo2,	String paquete_a_campo3,	String paquete_b_campo3,	String paquete_a_campo4,
                                      String paquete_b_campo4,	String paquete_a_campo5,	String paquete_b_campo5,	String paquete_a_campo6,	String paquete_b_campo6,	String paquete_a_campo7,	
                                      String paquete_b_campo7,	String paquete_a_campo8,	String paquete_b_campo8,	String paquete_a_campo9,	String paquete_b_campo9,	String paquete_a_campo10,	
                                      String paquete_b_campo10,	String paquete_a_campo11,	String paquete_b_campo11,	String paquete_a_campo12,	String paquete_b_campo12,	String paquete_a_campo13,	
                                      String paquete_b_campo13,	String paquete_a_campo14,	String paquete_b_campo14,	String paquete_a_campo15,	String paquete_b_campo15,	String ac_inox_campo1,	
                                      String ac_inox_campo2,	String ac_inox_campo3,	String ac_inox_campo4,	String ac_inox_campo5,	String ac_inox_campo6,	String ac_inox_campo7,	String ac_inox_campo8,	
                                      String ac_inox_campo9,	String ac_inox_campo10,	String ac_inox_campo11,	String ac_inox_campo12,	String ac_inox_campo13,	String ac_inox_campo14,	String ac_inox_campo15,	
                                      String ac_inox_campo16,	String ac_inox_campo17,	String ac_inox_campo18,	String ac_inox_campo19,	String ac_inox_campo20,	String ac_inox_campo21,	String ac_inox_campo22,	
                                      String ac_inox_campo23,	String ac_inox_campo24,	String ac_inox_campo25,	String ac_inox_campo26,	String ac_inox_campo27,	String ac_inox_campo28,	String ac_inox_campo29,	
                                      String ac_inox_campo30,	String ac_inox_campo31,	String ac_inox_campo32,	String ac_inox_campo33,	String ac_inox_campo34,	String ac_inox_campo35,	String ac_inox_campo36,	
                                      String ac_inox_campo37,	String ac_inox_campo38,	String ac_inox_campo39,	String ac_inox_campo40,	String ac_inox_campo41,	String ac_inox_campo42,	String ac_inox_campo43,	
                                      String ac_inox_campo44,	String ac_inox_campo45,	String ferroso_campo1,	String ferroso_campo2,	String ferroso_campo3,	String ferroso_campo4,	String ferroso_campo5,	
                                      String ferroso_campo6,	String ferroso_campo7,	String ferroso_campo8,	String ferroso_campo9,	String ferroso_campo10,	String ferroso_campo11,	String ferroso_campo12,	
                                      String ferroso_campo13,	String ferroso_campo14,	String ferroso_campo15,	String ferroso_campo16,	String ferroso_campo17,	String ferroso_campo18,	String ferroso_campo19,	
                                      String ferroso_campo20,	String ferroso_campo21,	String ferroso_campo22,	String ferroso_campo23,	String ferroso_campo24,	String ferroso_campo25,	String ferroso_campo26,	
                                      String ferroso_campo27,	String ferroso_campo28,	String ferroso_campo29,	String ferroso_campo30,	String ferroso_campo31,	String ferroso_campo32,	String ferroso_campo33,	
                                      String ferroso_campo34,	String ferroso_campo35,	String ferroso_campo36,	String ferroso_campo37,	String ferroso_campo38,	String ferroso_campo39,	String ferroso_campo40,	
                                      String ferroso_campo41,	String ferroso_campo42,	String ferroso_campo43,	String ferroso_campo44,	String ferroso_campo45,	String no_ferroso_campo1,	String no_ferroso_campo2,	
                                      String no_ferroso_campo3,	String no_ferroso_campo4,	String no_ferroso_campo5,	String no_ferroso_campo6,	String no_ferroso_campo7,	String no_ferroso_campo8,	
                                      String no_ferroso_campo9,	String no_ferroso_campo10,	String no_ferroso_campo11,	String no_ferroso_campo12,	String no_ferroso_campo13,	String no_ferroso_campo14,	
                                      String no_ferroso_campo15,	String no_ferroso_campo16,	String no_ferroso_campo17,	String no_ferroso_campo18,	String no_ferroso_campo19,	String no_ferroso_campo20,	
                                      String no_ferroso_campo21,	String no_ferroso_campo22,	String no_ferroso_campo23,	String no_ferroso_campo24,	String no_ferroso_campo25,	String no_ferroso_campo26,	
                                      String no_ferroso_campo27,	String no_ferroso_campo28,	String no_ferroso_campo29,	String no_ferroso_campo30,	String no_ferroso_campo31,	String no_ferroso_campo32,	
                                      String no_ferroso_campo33,	String no_ferroso_campo34,	String no_ferroso_campo35,	String no_ferroso_campo36,	String no_ferroso_campo37,	String no_ferroso_campo38,	
                                      String no_ferroso_campo39,	String no_ferroso_campo40,	String no_ferroso_campo41,	String no_ferroso_campo42,	String no_ferroso_campo43,	String no_ferroso_campo44,	
                                      String no_ferroso_campo45,	String accion_correctiva_campo1,	String accion_correctiva_campo2,	String accion_correctiva_campo3,	String accion_correctiva_campo4,	
                                      String accion_correctiva_campo5,	String accion_correctiva_campo6,	String accion_correctiva_campo7,	String accion_correctiva_campo8,	String accion_correctiva_campo9,	
                                      String accion_correctiva_campo10,	String accion_correctiva_campo11,	String accion_correctiva_campo12,	String accion_correctiva_campo13,	String accion_correctiva_campo14,	
                                      String accion_correctiva_campo15,	String codigo,	String actualizacion,	String vigencia){
        cursor=null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("INSERT INTO detector_metales (lote, usuario, fecha_hoy, fecha, hr_campo1, hr_campo2, hr_campo3, hr_campo4, " +
                    "hr_campo5, hr_campo6, hr_campo7, hr_campo8, hr_campo9, hr_campo10, hr_campo11, " +
                    "hr_campo12, hr_campo13, hr_campo14, hr_campo15, prod_campo1, prod_campo2, " +
                    "prod_campo3, prod_campo4, prod_campo5, prod_campo6, prod_campo7, " +
                    "prod_campo8, prod_campo9, prod_campo10, prod_campo11, prod_campo12, " +
                    "prod_campo13, prod_campo14, prod_campo15, paquete_a_campo1, paquete_b_campo1, " +
                    "paquete_a_campo2, paquete_b_campo2, paquete_a_campo3, paquete_b_campo3, " +
                    "paquete_a_campo4, paquete_b_campo4, paquete_a_campo5, paquete_b_campo5, " +
                    "paquete_a_campo6, paquete_b_campo6, paquete_a_campo7, paquete_b_campo7, " +
                    "paquete_a_campo8, paquete_b_campo8, paquete_a_campo9, paquete_b_campo9, " +
                    "paquete_a_campo10, paquete_b_campo10, paquete_a_campo11, paquete_b_campo11, " +
                    "paquete_a_campo12, paquete_b_campo12, paquete_a_campo13, paquete_b_campo13, " +
                    "paquete_a_campo14, paquete_b_campo14, paquete_a_campo15, paquete_b_campo15, " +
                    "ac_inox_campo1, ac_inox_campo2, ac_inox_campo3, ac_inox_campo4, ac_inox_campo5, " +
                    "ac_inox_campo6, ac_inox_campo7, ac_inox_campo8, ac_inox_campo9, ac_inox_campo10, " +
                    "ac_inox_campo11, ac_inox_campo12, ac_inox_campo13, ac_inox_campo14, " +
                    "ac_inox_campo15, ac_inox_campo16, ac_inox_campo17, ac_inox_campo18, " +
                    "ac_inox_campo19, ac_inox_campo20, ac_inox_campo21, ac_inox_campo22, " +
                    "ac_inox_campo23, ac_inox_campo24, ac_inox_campo25, ac_inox_campo26, " +
                    "ac_inox_campo27, ac_inox_campo28, ac_inox_campo29, ac_inox_campo30, " +
                    "ac_inox_campo31, ac_inox_campo32, ac_inox_campo33, ac_inox_campo34, " +
                    "ac_inox_campo35, ac_inox_campo36, ac_inox_campo37, ac_inox_campo38, " +
                    "ac_inox_campo39, ac_inox_campo40, ac_inox_campo41, ac_inox_campo42, " +
                    "ac_inox_campo43, ac_inox_campo44, ac_inox_campo45, ferroso_campo1, " +
                    "ferroso_campo2, ferroso_campo3, ferroso_campo4, ferroso_campo5, ferroso_campo6, " +
                    "ferroso_campo7, ferroso_campo8, ferroso_campo9, ferroso_campo10, ferroso_campo11, " +
                    "ferroso_campo12, ferroso_campo13, ferroso_campo14, ferroso_campo15, " +
                    "ferroso_campo16, ferroso_campo17, ferroso_campo18, ferroso_campo19, " +
                    "ferroso_campo20, ferroso_campo21, ferroso_campo22, ferroso_campo23, " +
                    "ferroso_campo24, ferroso_campo25, ferroso_campo26, ferroso_campo27, " +
                    "ferroso_campo28, ferroso_campo29, ferroso_campo30, ferroso_campo31, " +
                    "ferroso_campo32, ferroso_campo33, ferroso_campo34, ferroso_campo35, " +
                    "ferroso_campo36, ferroso_campo37, ferroso_campo38, ferroso_campo39, " +
                    "ferroso_campo40, ferroso_campo41, ferroso_campo42, ferroso_campo43, " +
                    "ferroso_campo44, ferroso_campo45, no_ferroso_campo1, no_ferroso_campo2, " +
                    "no_ferroso_campo3, no_ferroso_campo4, no_ferroso_campo5, no_ferroso_campo6, " +
                    "no_ferroso_campo7, no_ferroso_campo8, no_ferroso_campo9, no_ferroso_campo10, " +
                    "no_ferroso_campo11, no_ferroso_campo12, no_ferroso_campo13, no_ferroso_campo14, " +
                    "no_ferroso_campo15, no_ferroso_campo16, no_ferroso_campo17, no_ferroso_campo18, " +
                    "no_ferroso_campo19, no_ferroso_campo20, no_ferroso_campo21, no_ferroso_campo22, " +
                    "no_ferroso_campo23, no_ferroso_campo24, no_ferroso_campo25, no_ferroso_campo26, " +
                    "no_ferroso_campo27, no_ferroso_campo28, no_ferroso_campo29, no_ferroso_campo30, " +
                    "no_ferroso_campo31, no_ferroso_campo32, no_ferroso_campo33, no_ferroso_campo34, " +
                    "no_ferroso_campo35, no_ferroso_campo36, no_ferroso_campo37, no_ferroso_campo38, " +
                    "no_ferroso_campo39, no_ferroso_campo40, no_ferroso_campo41, no_ferroso_campo42, " +
                    "no_ferroso_campo43, no_ferroso_campo44, no_ferroso_campo45, accion_correctiva_campo1, " +
                    "accion_correctiva_campo2, accion_correctiva_campo3, accion_correctiva_campo4, " +
                    "accion_correctiva_campo5, accion_correctiva_campo6, accion_correctiva_campo7, " +
                    "accion_correctiva_campo8, accion_correctiva_campo9, accion_correctiva_campo10, " +
                    "accion_correctiva_campo11, accion_correctiva_campo12, accion_correctiva_campo13, " +
                    "accion_correctiva_campo14, accion_correctiva_campo15, codigo, actualizacion, vigencia)" +
                    " VALUES ('"+lote+"','"+usuario+"','"+fecha_hoy+"','"+fecha+"','"+hr_campo1+"','"+hr_campo2+"','"+hr_campo3+"','"+
                    hr_campo4+"','"+hr_campo5+"','"+hr_campo6+"','"+hr_campo7+"','"+hr_campo8+"','"+hr_campo9+"','"+
                    hr_campo10+"','"+hr_campo11+"','"+hr_campo12+"','"+hr_campo13+"','"+hr_campo14+"','"+hr_campo15+"','"+
                    prod_campo1+"','"+prod_campo2+"','"+prod_campo3+"','"+prod_campo4+"','"+prod_campo5+"','"+prod_campo6+"','"+
                    prod_campo7+"','"+prod_campo8+"','"+prod_campo9+"','"+prod_campo10+"','"+prod_campo11+"','"+prod_campo12+"','"+
                    prod_campo13+"','"+prod_campo14+"','"+prod_campo15+"','"+paquete_a_campo1+"','"+paquete_b_campo1+"','"+
                    paquete_a_campo2+"','"+paquete_b_campo2+"','"+paquete_a_campo3+"','"+paquete_b_campo3+"','"+paquete_a_campo4+"','"+
                    paquete_b_campo4+"','"+paquete_a_campo5+"','"+paquete_b_campo5+"','"+paquete_a_campo6+"','"+paquete_b_campo6+"','"+
                    paquete_a_campo7+"','"+paquete_b_campo7+"','"+paquete_a_campo8+"','"+paquete_b_campo8+"','"+paquete_a_campo9+"','"+
                    paquete_b_campo9+"','"+paquete_a_campo10+"','"+paquete_b_campo10+"','"+paquete_a_campo11+"','"+paquete_b_campo11+"','"+
                    paquete_a_campo12+"','"+paquete_b_campo12+"','"+paquete_a_campo13+"','"+paquete_b_campo13+"','"+paquete_a_campo14+"','"+
                    paquete_b_campo14+"','"+paquete_a_campo15+"','"+paquete_b_campo15+"','"+ac_inox_campo1+"','"+ac_inox_campo2+"','"+
                    ac_inox_campo3+"','"+ac_inox_campo4+"','"+ac_inox_campo5+"','"+ac_inox_campo6+"','"+ac_inox_campo7+"','"+ac_inox_campo8+"','"+
                    ac_inox_campo9+"','"+ac_inox_campo10+"','"+ac_inox_campo11+"','"+ac_inox_campo12+"','"+ac_inox_campo13+"','"+
                    ac_inox_campo14+"','"+ac_inox_campo15+"','"+ac_inox_campo16+"','"+ac_inox_campo17+"','"+ac_inox_campo18+"','"+
                    ac_inox_campo19+"','"+ac_inox_campo20+"','"+ac_inox_campo21+"','"+ac_inox_campo22+"','"+ac_inox_campo23+"','"+
                    ac_inox_campo24+"','"+ac_inox_campo25+"','"+ac_inox_campo26+"','"+ac_inox_campo27+"','"+ac_inox_campo28+"','"+
                    ac_inox_campo29+"','"+ac_inox_campo30+"','"+ac_inox_campo31+"','"+ac_inox_campo32+"','"+ac_inox_campo33+"','"+
                    ac_inox_campo34+"','"+ac_inox_campo35+"','"+ac_inox_campo36+"','"+ac_inox_campo37+"','"+ac_inox_campo38+"','"+
                    ac_inox_campo39+"','"+ac_inox_campo40+"','"+ac_inox_campo41+"','"+ac_inox_campo42+"','"+ac_inox_campo43+"','"+
                    ac_inox_campo44+"','"+ac_inox_campo45+"','"+ferroso_campo1+"','"+ferroso_campo2+"','"+ferroso_campo3+"','"+
                    ferroso_campo4+"','"+ferroso_campo5+"','"+ferroso_campo6+"','"+ferroso_campo7+"','"+ferroso_campo8+"','"+
                    ferroso_campo9+"','"+ferroso_campo10+"','"+ferroso_campo11+"','"+ferroso_campo12+"','"+ferroso_campo13+"','"+
                    ferroso_campo14+"','"+ferroso_campo15+"','"+ferroso_campo16+"','"+ferroso_campo17+"','"+ferroso_campo18+"','"+
                    ferroso_campo19+"','"+ferroso_campo20+"','"+ferroso_campo21+"','"+ferroso_campo22+"','"+ferroso_campo23+"','"+
                    ferroso_campo24+"','"+ferroso_campo25+"','"+ferroso_campo26+"','"+ferroso_campo27+"','"+ferroso_campo28+"','"+
                    ferroso_campo29+"','"+ferroso_campo30+"','"+ferroso_campo31+"','"+ferroso_campo32+"','"+ferroso_campo33+"','"+
                    ferroso_campo34+"','"+ferroso_campo35+"','"+ferroso_campo36+"','"+ferroso_campo37+"','"+ferroso_campo38+"','"+
                    ferroso_campo39+"','"+ferroso_campo40+"','"+ferroso_campo41+"','"+ferroso_campo42+"','"+ferroso_campo43+"','"+
                    ferroso_campo44+"','"+ferroso_campo45+"','"+no_ferroso_campo1+"','"+no_ferroso_campo2+"','"+no_ferroso_campo3+"','"+
                    no_ferroso_campo4+"','"+no_ferroso_campo5+"','"+no_ferroso_campo6+"','"+no_ferroso_campo7+"','"+no_ferroso_campo8+"','"+
                    no_ferroso_campo9+"','"+no_ferroso_campo10+"','"+no_ferroso_campo11+"','"+no_ferroso_campo12+"','"+no_ferroso_campo13+"','"+
                    no_ferroso_campo14+"','"+no_ferroso_campo15+"','"+no_ferroso_campo16+"','"+no_ferroso_campo17+"','"+no_ferroso_campo18+"','"+
                    no_ferroso_campo19+"','"+no_ferroso_campo20+"','"+no_ferroso_campo21+"','"+no_ferroso_campo22+"','"+no_ferroso_campo23+"','"+
                    no_ferroso_campo24+"','"+no_ferroso_campo25+"','"+no_ferroso_campo26+"','"+no_ferroso_campo27+"','"+no_ferroso_campo28+"','"+
                    no_ferroso_campo29+"','"+no_ferroso_campo30+"','"+no_ferroso_campo31+"','"+no_ferroso_campo32+"','"+no_ferroso_campo33+"','"+
                    no_ferroso_campo34+"','"+no_ferroso_campo35+"','"+no_ferroso_campo36+"','"+no_ferroso_campo37+"','"+no_ferroso_campo38+"','"+
                    no_ferroso_campo39+"','"+no_ferroso_campo40+"','"+no_ferroso_campo41+"','"+no_ferroso_campo42+"','"+no_ferroso_campo43+"','"+
                    no_ferroso_campo44+"','"+no_ferroso_campo45+"','"+accion_correctiva_campo1+"','"+accion_correctiva_campo2+"','"+
                    accion_correctiva_campo3+"','"+accion_correctiva_campo4+"','"+accion_correctiva_campo5+"','"+accion_correctiva_campo6+"','"+
                    accion_correctiva_campo7+"','"+accion_correctiva_campo8+"','"+accion_correctiva_campo9+"','"+accion_correctiva_campo10+"','"+
                    accion_correctiva_campo11+"','"+accion_correctiva_campo12+"','"+accion_correctiva_campo13+"','"+accion_correctiva_campo14+"','"+
                    accion_correctiva_campo15+"','"+codigo+"','"+actualizacion+"','"+vigencia+"');");
                    return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /**
     * *************    Consulta para Actualizar Detector de Metales    ************
     */

    public boolean DAOActualizarDetectorMetales(String lote, String usuario, String fecha_hoy, String fecha, String hr_campo1, String hr_campo2, String hr_campo3, String hr_campo4,
                                                String hr_campo5, String hr_campo6, String hr_campo7, String hr_campo8, String hr_campo9, String hr_campo10, String hr_campo11,
                                                String hr_campo12, String hr_campo13, String hr_campo14, String hr_campo15, String prod_campo1, String prod_campo2, String prod_campo3,
                                                String prod_campo4, String prod_campo5, String prod_campo6, String prod_campo7, String prod_campo8, String prod_campo9, String prod_campo10,
                                                String prod_campo11, String prod_campo12, String prod_campo13, String prod_campo14, String prod_campo15, String paquete_a_campo1,
                                                String paquete_b_campo1, String paquete_a_campo2, String paquete_b_campo2, String paquete_a_campo3, String paquete_b_campo3, String paquete_a_campo4,
                                                String paquete_b_campo4, String paquete_a_campo5, String paquete_b_campo5, String paquete_a_campo6, String paquete_b_campo6, String paquete_a_campo7,
                                                String paquete_b_campo7, String paquete_a_campo8, String paquete_b_campo8, String paquete_a_campo9, String paquete_b_campo9, String paquete_a_campo10,
                                                String paquete_b_campo10, String paquete_a_campo11, String paquete_b_campo11, String paquete_a_campo12, String paquete_b_campo12, String paquete_a_campo13,
                                                String paquete_b_campo13, String paquete_a_campo14, String paquete_b_campo14, String paquete_a_campo15, String paquete_b_campo15, String ac_inox_campo1,
                                                String ac_inox_campo2, String ac_inox_campo3, String ac_inox_campo4, String ac_inox_campo5, String ac_inox_campo6, String ac_inox_campo7, String ac_inox_campo8,
                                                String ac_inox_campo9, String ac_inox_campo10, String ac_inox_campo11, String ac_inox_campo12, String ac_inox_campo13, String ac_inox_campo14, String ac_inox_campo15,
                                                String ac_inox_campo16, String ac_inox_campo17, String ac_inox_campo18, String ac_inox_campo19, String ac_inox_campo20, String ac_inox_campo21, String ac_inox_campo22,
                                                String ac_inox_campo23, String ac_inox_campo24, String ac_inox_campo25, String ac_inox_campo26, String ac_inox_campo27, String ac_inox_campo28, String ac_inox_campo29,
                                                String ac_inox_campo30, String ac_inox_campo31, String ac_inox_campo32, String ac_inox_campo33, String ac_inox_campo34, String ac_inox_campo35, String ac_inox_campo36,
                                                String ac_inox_campo37, String ac_inox_campo38, String ac_inox_campo39, String ac_inox_campo40, String ac_inox_campo41, String ac_inox_campo42, String ac_inox_campo43,
                                                String ac_inox_campo44, String ac_inox_campo45, String ferroso_campo1, String ferroso_campo2, String ferroso_campo3, String ferroso_campo4, String ferroso_campo5,
                                                String ferroso_campo6, String ferroso_campo7, String ferroso_campo8, String ferroso_campo9, String ferroso_campo10, String ferroso_campo11, String ferroso_campo12,
                                                String ferroso_campo13, String ferroso_campo14, String ferroso_campo15, String ferroso_campo16, String ferroso_campo17, String ferroso_campo18, String ferroso_campo19,
                                                String ferroso_campo20, String ferroso_campo21, String ferroso_campo22, String ferroso_campo23, String ferroso_campo24, String ferroso_campo25, String ferroso_campo26,
                                                String ferroso_campo27, String ferroso_campo28, String ferroso_campo29, String ferroso_campo30, String ferroso_campo31, String ferroso_campo32, String ferroso_campo33,
                                                String ferroso_campo34, String ferroso_campo35, String ferroso_campo36, String ferroso_campo37, String ferroso_campo38, String ferroso_campo39, String ferroso_campo40,
                                                String ferroso_campo41, String ferroso_campo42, String ferroso_campo43, String ferroso_campo44, String ferroso_campo45, String no_ferroso_campo1, String no_ferroso_campo2,
                                                String no_ferroso_campo3, String no_ferroso_campo4, String no_ferroso_campo5, String no_ferroso_campo6, String no_ferroso_campo7, String no_ferroso_campo8,
                                                String no_ferroso_campo9, String no_ferroso_campo10, String no_ferroso_campo11, String no_ferroso_campo12, String no_ferroso_campo13, String no_ferroso_campo14,
                                                String no_ferroso_campo15, String no_ferroso_campo16, String no_ferroso_campo17, String no_ferroso_campo18, String no_ferroso_campo19, String no_ferroso_campo20,
                                                String no_ferroso_campo21, String no_ferroso_campo22, String no_ferroso_campo23, String no_ferroso_campo24, String no_ferroso_campo25, String no_ferroso_campo26,
                                                String no_ferroso_campo27, String no_ferroso_campo28, String no_ferroso_campo29, String no_ferroso_campo30, String no_ferroso_campo31, String no_ferroso_campo32,
                                                String no_ferroso_campo33, String no_ferroso_campo34, String no_ferroso_campo35, String no_ferroso_campo36, String no_ferroso_campo37, String no_ferroso_campo38,
                                                String no_ferroso_campo39, String no_ferroso_campo40, String no_ferroso_campo41, String no_ferroso_campo42, String no_ferroso_campo43, String no_ferroso_campo44,
                                                String no_ferroso_campo45, String accion_correctiva_campo1, String accion_correctiva_campo2, String accion_correctiva_campo3, String accion_correctiva_campo4,
                                                String accion_correctiva_campo5, String accion_correctiva_campo6, String accion_correctiva_campo7, String accion_correctiva_campo8, String accion_correctiva_campo9,
                                                String accion_correctiva_campo10, String accion_correctiva_campo11, String accion_correctiva_campo12, String accion_correctiva_campo13, String accion_correctiva_campo14,
                                                String accion_correctiva_campo15, String codigo, String actualizacion, String vigencia) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE detector_metales SET " +
                    "usuario='" + usuario + "'," +
                    "fecha_hoy='" + fecha_hoy + "'," +
                    "fecha='" + fecha + "'," +
                    "hr_campo1='" + hr_campo1 + "'," +
                    "hr_campo2='" + hr_campo2 + "'," +
                    "hr_campo3='" + hr_campo3 + "'," +
                    "hr_campo4='" + hr_campo4 + "'," +
                    "hr_campo5='" + hr_campo5 + "'," +
                    "hr_campo6='" + hr_campo6 + "'," +
                    "hr_campo7='" + hr_campo7 + "'," +
                    "hr_campo8='" + hr_campo8 + "'," +
                    "hr_campo9='" + hr_campo9 + "'," +
                    "hr_campo10='" + hr_campo10 + "'," +
                    "hr_campo11='" + hr_campo11 + "'," +
                    "hr_campo12='" + hr_campo12 + "'," +
                    "hr_campo13='" + hr_campo13 + "'," +
                    "hr_campo14='" + hr_campo14 + "'," +
                    "hr_campo15='" + hr_campo15 + "'," +
                    "prod_campo1='" + prod_campo1 + "'," +
                    "prod_campo2='" + prod_campo2 + "'," +
                    "prod_campo3='" + prod_campo3 + "'," +
                    "prod_campo4='" + prod_campo4 + "'," +
                    "prod_campo5='" + prod_campo5 + "'," +
                    "prod_campo6='" + prod_campo6 + "'," +
                    "prod_campo7='" + prod_campo7 + "'," +
                    "prod_campo8='" + prod_campo8 + "'," +
                    "prod_campo9='" + prod_campo9 + "'," +
                    "prod_campo10='" + prod_campo10 + "'," +
                    "prod_campo11='" + prod_campo11 + "'," +
                    "prod_campo12='" + prod_campo12 + "'," +
                    "prod_campo13='" + prod_campo13 + "'," +
                    "prod_campo14='" + prod_campo14 + "'," +
                    "prod_campo15='" + prod_campo15 + "'," +
                    "paquete_a_campo1='" + paquete_a_campo1 + "'," +
                    "paquete_b_campo1='" + paquete_b_campo1 + "'," +
                    "paquete_a_campo2='" + paquete_a_campo2 + "'," +
                    "paquete_b_campo2='" + paquete_b_campo2 + "'," +
                    "paquete_a_campo3='" + paquete_a_campo3 + "'," +
                    "paquete_b_campo3='" + paquete_b_campo3 + "'," +
                    "paquete_a_campo4='" + paquete_a_campo4 + "'," +
                    "paquete_b_campo4='" + paquete_b_campo4 + "'," +
                    "paquete_a_campo5='" + paquete_a_campo5 + "'," +
                    "paquete_b_campo5='" + paquete_b_campo5 + "'," +
                    "paquete_a_campo6='" + paquete_a_campo6 + "'," +
                    "paquete_b_campo6='" + paquete_b_campo6 + "'," +
                    "paquete_a_campo7='" + paquete_a_campo7 + "'," +
                    "paquete_b_campo7='" + paquete_b_campo7 + "'," +
                    "paquete_a_campo8='" + paquete_a_campo8 + "'," +
                    "paquete_b_campo8='" + paquete_b_campo8 + "'," +
                    "paquete_a_campo9='" + paquete_a_campo9 + "'," +
                    "paquete_b_campo9='" + paquete_b_campo9 + "'," +
                    "paquete_a_campo10='" + paquete_a_campo10 + "'," +
                    "paquete_b_campo10='" + paquete_b_campo10 + "'," +
                    "paquete_a_campo11='" + paquete_a_campo11 + "'," +
                    "paquete_b_campo11='" + paquete_b_campo11 + "'," +
                    "paquete_a_campo12='" + paquete_a_campo12 + "'," +
                    "paquete_b_campo12='" + paquete_b_campo12 + "'," +
                    "paquete_a_campo13='" + paquete_a_campo13 + "'," +
                    "paquete_b_campo13='" + paquete_b_campo13 + "'," +
                    "paquete_a_campo14='" + paquete_a_campo14 + "'," +
                    "paquete_b_campo14='" + paquete_b_campo14 + "'," +
                    "paquete_a_campo15='" + paquete_a_campo15 + "'," +
                    "paquete_b_campo15='" + paquete_b_campo15 + "'," +
                    "ac_inox_campo1='" + ac_inox_campo1 + "'," +
                    "ac_inox_campo2='" + ac_inox_campo2 + "'," +
                    "ac_inox_campo3='" + ac_inox_campo3 + "'," +
                    "ac_inox_campo4='" + ac_inox_campo4 + "'," +
                    "ac_inox_campo5='" + ac_inox_campo5 + "'," +
                    "ac_inox_campo6='" + ac_inox_campo6 + "'," +
                    "ac_inox_campo7='" + ac_inox_campo7 + "'," +
                    "ac_inox_campo8='" + ac_inox_campo8 + "'," +
                    "ac_inox_campo9='" + ac_inox_campo9 + "'," +
                    "ac_inox_campo10='" + ac_inox_campo10 + "'," +
                    "ac_inox_campo11='" + ac_inox_campo11 + "'," +
                    "ac_inox_campo12='" + ac_inox_campo12 + "'," +
                    "ac_inox_campo13='" + ac_inox_campo13 + "'," +
                    "ac_inox_campo14='" + ac_inox_campo14 + "'," +
                    "ac_inox_campo15='" + ac_inox_campo15 + "'," +
                    "ac_inox_campo16='" + ac_inox_campo16 + "'," +
                    "ac_inox_campo17='" + ac_inox_campo17 + "'," +
                    "ac_inox_campo18='" + ac_inox_campo18 + "'," +
                    "ac_inox_campo19='" + ac_inox_campo19 + "'," +
                    "ac_inox_campo20='" + ac_inox_campo20 + "'," +
                    "ac_inox_campo21='" + ac_inox_campo21 + "'," +
                    "ac_inox_campo22='" + ac_inox_campo22 + "'," +
                    "ac_inox_campo23='" + ac_inox_campo23 + "'," +
                    "ac_inox_campo24='" + ac_inox_campo24 + "'," +
                    "ac_inox_campo25='" + ac_inox_campo25 + "'," +
                    "ac_inox_campo26='" + ac_inox_campo26 + "'," +
                    "ac_inox_campo27='" + ac_inox_campo27 + "'," +
                    "ac_inox_campo28='" + ac_inox_campo28 + "'," +
                    "ac_inox_campo29='" + ac_inox_campo29 + "'," +
                    "ac_inox_campo30='" + ac_inox_campo30 + "'," +
                    "ac_inox_campo31='" + ac_inox_campo31 + "'," +
                    "ac_inox_campo32='" + ac_inox_campo32 + "'," +
                    "ac_inox_campo33='" + ac_inox_campo33 + "'," +
                    "ac_inox_campo34='" + ac_inox_campo34 + "'," +
                    "ac_inox_campo35='" + ac_inox_campo35 + "'," +
                    "ac_inox_campo36='" + ac_inox_campo36 + "'," +
                    "ac_inox_campo37='" + ac_inox_campo37 + "'," +
                    "ac_inox_campo38='" + ac_inox_campo38 + "'," +
                    "ac_inox_campo39='" + ac_inox_campo39 + "'," +
                    "ac_inox_campo40='" + ac_inox_campo40 + "'," +
                    "ac_inox_campo41='" + ac_inox_campo41 + "'," +
                    "ac_inox_campo42='" + ac_inox_campo42 + "'," +
                    "ac_inox_campo43='" + ac_inox_campo43 + "'," +
                    "ac_inox_campo44='" + ac_inox_campo44 + "'," +
                    "ac_inox_campo45='" + ac_inox_campo45 + "'," +
                    "ferroso_campo1='" + ferroso_campo1 + "'," +
                    "ferroso_campo2='" + ferroso_campo2 + "'," +
                    "ferroso_campo3='" + ferroso_campo3 + "'," +
                    "ferroso_campo4='" + ferroso_campo4 + "'," +
                    "ferroso_campo5='" + ferroso_campo5 + "'," +
                    "ferroso_campo6='" + ferroso_campo6 + "'," +
                    "ferroso_campo7='" + ferroso_campo7 + "'," +
                    "ferroso_campo8='" + ferroso_campo8 + "'," +
                    "ferroso_campo9='" + ferroso_campo9 + "'," +
                    "ferroso_campo10='" + ferroso_campo10 + "'," +
                    "ferroso_campo11='" + ferroso_campo11 + "'," +
                    "ferroso_campo12='" + ferroso_campo12 + "'," +
                    "ferroso_campo13='" + ferroso_campo13 + "'," +
                    "ferroso_campo14='" + ferroso_campo14 + "'," +
                    "ferroso_campo15='" + ferroso_campo15 + "'," +
                    "ferroso_campo16='" + ferroso_campo16 + "'," +
                    "ferroso_campo17='" + ferroso_campo17 + "'," +
                    "ferroso_campo18='" + ferroso_campo18 + "'," +
                    "ferroso_campo19='" + ferroso_campo19 + "'," +
                    "ferroso_campo20='" + ferroso_campo20 + "'," +
                    "ferroso_campo21='" + ferroso_campo21 + "'," +
                    "ferroso_campo22='" + ferroso_campo22 + "'," +
                    "ferroso_campo23='" + ferroso_campo23 + "'," +
                    "ferroso_campo24='" + ferroso_campo24 + "'," +
                    "ferroso_campo25='" + ferroso_campo25 + "'," +
                    "ferroso_campo26='" + ferroso_campo26 + "'," +
                    "ferroso_campo27='" + ferroso_campo27 + "'," +
                    "ferroso_campo28='" + ferroso_campo28 + "'," +
                    "ferroso_campo29='" + ferroso_campo29 + "'," +
                    "ferroso_campo30='" + ferroso_campo30 + "'," +
                    "ferroso_campo31='" + ferroso_campo31 + "'," +
                    "ferroso_campo32='" + ferroso_campo32 + "'," +
                    "ferroso_campo33='" + ferroso_campo33 + "'," +
                    "ferroso_campo34='" + ferroso_campo34 + "'," +
                    "ferroso_campo35='" + ferroso_campo35 + "'," +
                    "ferroso_campo36='" + ferroso_campo36 + "'," +
                    "ferroso_campo37='" + ferroso_campo37 + "'," +
                    "ferroso_campo38='" + ferroso_campo38 + "'," +
                    "ferroso_campo39='" + ferroso_campo39 + "'," +
                    "ferroso_campo40='" + ferroso_campo40 + "'," +
                    "ferroso_campo41='" + ferroso_campo41 + "'," +
                    "ferroso_campo42='" + ferroso_campo42 + "'," +
                    "ferroso_campo43='" + ferroso_campo43 + "'," +
                    "ferroso_campo44='" + ferroso_campo44 + "'," +
                    "ferroso_campo45='" + ferroso_campo45 + "'," +
                    "no_ferroso_campo1='" + no_ferroso_campo1 + "'," +
                    "no_ferroso_campo2='" + no_ferroso_campo2 + "'," +
                    "no_ferroso_campo3='" + no_ferroso_campo3 + "'," +
                    "no_ferroso_campo4='" + no_ferroso_campo4 + "'," +
                    "no_ferroso_campo5='" + no_ferroso_campo5 + "'," +
                    "no_ferroso_campo6='" + no_ferroso_campo6 + "'," +
                    "no_ferroso_campo7='" + no_ferroso_campo7 + "'," +
                    "no_ferroso_campo8='" + no_ferroso_campo8 + "'," +
                    "no_ferroso_campo9='" + no_ferroso_campo9 + "'," +
                    "no_ferroso_campo10='" + no_ferroso_campo10 + "'," +
                    "no_ferroso_campo11='" + no_ferroso_campo11 + "'," +
                    "no_ferroso_campo12='" + no_ferroso_campo12 + "'," +
                    "no_ferroso_campo13='" + no_ferroso_campo13 + "'," +
                    "no_ferroso_campo14='" + no_ferroso_campo14 + "'," +
                    "no_ferroso_campo15='" + no_ferroso_campo15 + "'," +
                    "no_ferroso_campo16='" + no_ferroso_campo16 + "'," +
                    "no_ferroso_campo17='" + no_ferroso_campo17 + "'," +
                    "no_ferroso_campo18='" + no_ferroso_campo18 + "'," +
                    "no_ferroso_campo19='" + no_ferroso_campo19 + "'," +
                    "no_ferroso_campo20='" + no_ferroso_campo20 + "'," +
                    "no_ferroso_campo21='" + no_ferroso_campo21 + "'," +
                    "no_ferroso_campo22='" + no_ferroso_campo22 + "'," +
                    "no_ferroso_campo23='" + no_ferroso_campo23 + "'," +
                    "no_ferroso_campo24='" + no_ferroso_campo24 + "'," +
                    "no_ferroso_campo25='" + no_ferroso_campo25 + "'," +
                    "no_ferroso_campo26='" + no_ferroso_campo26 + "'," +
                    "no_ferroso_campo27='" + no_ferroso_campo27 + "'," +
                    "no_ferroso_campo28='" + no_ferroso_campo28 + "'," +
                    "no_ferroso_campo29='" + no_ferroso_campo29 + "'," +
                    "no_ferroso_campo30='" + no_ferroso_campo30 + "'," +
                    "no_ferroso_campo31='" + no_ferroso_campo31 + "'," +
                    "no_ferroso_campo32='" + no_ferroso_campo32 + "'," +
                    "no_ferroso_campo33='" + no_ferroso_campo33 + "'," +
                    "no_ferroso_campo34='" + no_ferroso_campo34 + "'," +
                    "no_ferroso_campo35='" + no_ferroso_campo35 + "'," +
                    "no_ferroso_campo36='" + no_ferroso_campo36 + "'," +
                    "no_ferroso_campo37='" + no_ferroso_campo37 + "'," +
                    "no_ferroso_campo38='" + no_ferroso_campo38 + "'," +
                    "no_ferroso_campo39='" + no_ferroso_campo39 + "'," +
                    "no_ferroso_campo40='" + no_ferroso_campo40 + "'," +
                    "no_ferroso_campo41='" + no_ferroso_campo41 + "'," +
                    "no_ferroso_campo42='" + no_ferroso_campo42 + "'," +
                    "no_ferroso_campo43='" + no_ferroso_campo43 + "'," +
                    "no_ferroso_campo44='" + no_ferroso_campo44 + "'," +
                    "no_ferroso_campo45='" + no_ferroso_campo45 + "'," +
                    "accion_correctiva_campo1='" + accion_correctiva_campo1 + "'," +
                    "accion_correctiva_campo2='" + accion_correctiva_campo2 + "'," +
                    "accion_correctiva_campo3='" + accion_correctiva_campo3 + "'," +
                    "accion_correctiva_campo4='" + accion_correctiva_campo4 + "'," +
                    "accion_correctiva_campo5='" + accion_correctiva_campo5 + "'," +
                    "accion_correctiva_campo6='" + accion_correctiva_campo6 + "'," +
                    "accion_correctiva_campo7='" + accion_correctiva_campo7 + "'," +
                    "accion_correctiva_campo8='" + accion_correctiva_campo8 + "'," +
                    "accion_correctiva_campo9='" + accion_correctiva_campo9 + "'," +
                    "accion_correctiva_campo10='" + accion_correctiva_campo10 + "'," +
                    "accion_correctiva_campo11='" + accion_correctiva_campo11 + "'," +
                    "accion_correctiva_campo12='" + accion_correctiva_campo12 + "'," +
                    "accion_correctiva_campo13='" + accion_correctiva_campo13 + "'," +
                    "accion_correctiva_campo14='" + accion_correctiva_campo14 + "'," +
                    "accion_correctiva_campo15='" + accion_correctiva_campo15 + "'," +
                    "codigo='" + codigo + "'," +
                    "actualizacion='" + actualizacion + "'," +
                    "vigencia='" + vigencia + "' WHERE lote = " + lote);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /****************    Consulta para Llenar la lista de lotes Detector de Metales *************/
    public ArrayList<consultas> DAOListaDetectorRealizado(String fecha) {
        db = myDbHelper.getWritableDatabase();
        cursor = null;
        cursor = db.rawQuery("SELECT lote " +
                "FROM detector_metales WHERE fecha_hoy ='" +
                fecha + "'", null);
        ArrayList<consultas> empaqueArray = new ArrayList<consultas>();

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                consultas lista = new consultas();

                lista.empaque = new String[cursor.getCount()];

                for (int x = 0; x < cursor.getCount(); x++) {
                    lista.empaque[x] = cursor.getString(cursor.getColumnIndex("lote"));
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
    /****************    Consulta para LLenar Detector    *************/
    public Cursor DAOLLenarDetector(String lote) {
        cursor = null;
        db = myDbHelper.getWritableDatabase();
        try {
            cursor = db.rawQuery("SELECT lote, usuario, fecha_hoy, fecha, hr_campo1, hr_campo2, hr_campo3, hr_campo4, " +
                    "hr_campo5, hr_campo6, hr_campo7, hr_campo8, hr_campo9, hr_campo10, hr_campo11, " +
                    "hr_campo12, hr_campo13, hr_campo14, hr_campo15, prod_campo1, prod_campo2, " +
                    "prod_campo3, prod_campo4, prod_campo5, prod_campo6, prod_campo7, " +
                    "prod_campo8, prod_campo9, prod_campo10, prod_campo11, prod_campo12, " +
                    "prod_campo13, prod_campo14, prod_campo15, paquete_a_campo1, paquete_b_campo1, " +
                    "paquete_a_campo2, paquete_b_campo2, paquete_a_campo3, paquete_b_campo3, " +
                    "paquete_a_campo4, paquete_b_campo4, paquete_a_campo5, paquete_b_campo5, " +
                    "paquete_a_campo6, paquete_b_campo6, paquete_a_campo7, paquete_b_campo7, " +
                    "paquete_a_campo8, paquete_b_campo8, paquete_a_campo9, paquete_b_campo9, " +
                    "paquete_a_campo10, paquete_b_campo10, paquete_a_campo11, paquete_b_campo11, " +
                    "paquete_a_campo12, paquete_b_campo12, paquete_a_campo13, paquete_b_campo13, " +
                    "paquete_a_campo14, paquete_b_campo14, paquete_a_campo15, paquete_b_campo15, " +
                    "ac_inox_campo1, ac_inox_campo2, ac_inox_campo3, ac_inox_campo4, ac_inox_campo5, " +
                    "ac_inox_campo6, ac_inox_campo7, ac_inox_campo8, ac_inox_campo9, ac_inox_campo10, " +
                    "ac_inox_campo11, ac_inox_campo12, ac_inox_campo13, ac_inox_campo14, " +
                    "ac_inox_campo15, ac_inox_campo16, ac_inox_campo17, ac_inox_campo18, " +
                    "ac_inox_campo19, ac_inox_campo20, ac_inox_campo21, ac_inox_campo22, " +
                    "ac_inox_campo23, ac_inox_campo24, ac_inox_campo25, ac_inox_campo26, " +
                    "ac_inox_campo27, ac_inox_campo28, ac_inox_campo29, ac_inox_campo30, " +
                    "ac_inox_campo31, ac_inox_campo32, ac_inox_campo33, ac_inox_campo34, " +
                    "ac_inox_campo35, ac_inox_campo36, ac_inox_campo37, ac_inox_campo38, " +
                    "ac_inox_campo39, ac_inox_campo40, ac_inox_campo41, ac_inox_campo42, " +
                    "ac_inox_campo43, ac_inox_campo44, ac_inox_campo45, ferroso_campo1, " +
                    "ferroso_campo2, ferroso_campo3, ferroso_campo4, ferroso_campo5, ferroso_campo6, " +
                    "ferroso_campo7, ferroso_campo8, ferroso_campo9, ferroso_campo10, ferroso_campo11, " +
                    "ferroso_campo12, ferroso_campo13, ferroso_campo14, ferroso_campo15, " +
                    "ferroso_campo16, ferroso_campo17, ferroso_campo18, ferroso_campo19, " +
                    "ferroso_campo20, ferroso_campo21, ferroso_campo22, ferroso_campo23, " +
                    "ferroso_campo24, ferroso_campo25, ferroso_campo26, ferroso_campo27, " +
                    "ferroso_campo28, ferroso_campo29, ferroso_campo30, ferroso_campo31, " +
                    "ferroso_campo32, ferroso_campo33, ferroso_campo34, ferroso_campo35, " +
                    "ferroso_campo36, ferroso_campo37, ferroso_campo38, ferroso_campo39, " +
                    "ferroso_campo40, ferroso_campo41, ferroso_campo42, ferroso_campo43, " +
                    "ferroso_campo44, ferroso_campo45, no_ferroso_campo1, no_ferroso_campo2, " +
                    "no_ferroso_campo3, no_ferroso_campo4, no_ferroso_campo5, no_ferroso_campo6, " +
                    "no_ferroso_campo7, no_ferroso_campo8, no_ferroso_campo9, no_ferroso_campo10, " +
                    "no_ferroso_campo11, no_ferroso_campo12, no_ferroso_campo13, no_ferroso_campo14, " +
                    "no_ferroso_campo15, no_ferroso_campo16, no_ferroso_campo17, no_ferroso_campo18, " +
                    "no_ferroso_campo19, no_ferroso_campo20, no_ferroso_campo21, no_ferroso_campo22, " +
                    "no_ferroso_campo23, no_ferroso_campo24, no_ferroso_campo25, no_ferroso_campo26, " +
                    "no_ferroso_campo27, no_ferroso_campo28, no_ferroso_campo29, no_ferroso_campo30, " +
                    "no_ferroso_campo31, no_ferroso_campo32, no_ferroso_campo33, no_ferroso_campo34, " +
                    "no_ferroso_campo35, no_ferroso_campo36, no_ferroso_campo37, no_ferroso_campo38, " +
                    "no_ferroso_campo39, no_ferroso_campo40, no_ferroso_campo41, no_ferroso_campo42, " +
                    "no_ferroso_campo43, no_ferroso_campo44, no_ferroso_campo45, accion_correctiva_campo1, " +
                    "accion_correctiva_campo2, accion_correctiva_campo3, accion_correctiva_campo4, " +
                    "accion_correctiva_campo5, accion_correctiva_campo6, accion_correctiva_campo7, " +
                    "accion_correctiva_campo8, accion_correctiva_campo9, accion_correctiva_campo10, " +
                    "accion_correctiva_campo11, accion_correctiva_campo12, accion_correctiva_campo13, " +
                    "accion_correctiva_campo14, accion_correctiva_campo15, codigo, actualizacion, vigencia "+
                    "FROM detector_metales WHERE lote ='" + lote + "';", null);
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
