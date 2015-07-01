package DTO;

import android.content.Context;
import android.widget.EditText;


/**
 * Created by Sistemas on 25/02/2015.
 */
public class Variables {

    private static Context contextoGral;
    private static int linea_fundido;
    private static String equipo_tina;
    private static boolean tinaA;
    private static boolean pendiente_tina_A;
    private static boolean fromSearch;
    private static boolean fromEmpaque;
    private static boolean fromCuajado;
    private static boolean fromFundido;
    private static boolean fromLaboratorio;
    private static boolean fromProducto;
    private static boolean fromCrema;

    public static boolean isFromCuajadas() {
        return fromCuajadas;
    }

    public static void setFromCuajadas(boolean fromCuajadas) {
        Variables.fromCuajadas = fromCuajadas;
    }

    private static boolean fromCuajadas;
    private static boolean fromAdminLaboratorio;
    private static boolean fromAdminEmpaque;
    private static boolean fromAdminCuajado;
    private static boolean fromAdminTexturizador;
    private static boolean fromAdminFundido;
    private static boolean fromAdminProducto;
    private static boolean fromAdminCrema;

    public static boolean isFromAdminCuajadas() {
        return fromAdminCuajadas;
    }

    public static void setFromAdminCuajadas(boolean fromAdminCuajadas) {
        Variables.fromAdminCuajadas = fromAdminCuajadas;
    }

    private static boolean fromAdminCuajadas;
    private static String loteTexturizador;
    private static String loteEmpaque;
    private static String loteCuajado;
    private static String loteFundido;
    private static String loteLaboratorio;
    private static String loteCuajadas;

    public static String getLoteCrema() {
        return loteCrema;
    }

    public static void setLoteCrema(String loteCrema) {
        Variables.loteCrema = loteCrema;
    }

    private static String loteCrema;

    public static String getLoteCuajadas() {
        return loteCuajadas;
    }

    public static void setLoteCuajadas(String loteCuajadas) {
        Variables.loteCuajadas = loteCuajadas;
    }



    public static boolean isFromCrema() {
        return fromCrema;
    }

    public static void setFromCrema(boolean fromCrema) {
        Variables.fromCrema = fromCrema;
    }

    public static boolean isFromAdminCrema() {
        return fromAdminCrema;
    }

    public static void setFromAdminCrema(boolean fromAdminCrema) {
        Variables.fromAdminCrema = fromAdminCrema;
    }
    public static String getLoteProducto() {
        return loteProducto;
    }

    public static void setLoteProducto(String loteProducto) {
        Variables.loteProducto = loteProducto;
    }

    private static String loteProducto;
    public static String codProdLaboratorio;


    public static boolean isFromProducto() {
        return fromProducto;
    }

    public static void setFromProducto(boolean fromProducto) {
        Variables.fromProducto = fromProducto;
    }
    public static boolean isFromAdminProducto() {
        return fromAdminProducto;
    }

    public static void setFromAdminProducto(boolean fromAdminProducto) {
        Variables.fromAdminProducto = fromAdminProducto;
    }
    public static boolean isFromAdminTexturizador() {
        return fromAdminTexturizador;
    }

    public static void setFromAdminTexturizador(boolean fromAdminTexturizador) {
        Variables.fromAdminTexturizador = fromAdminTexturizador;
    }

    public static boolean isFromAdminCuajado() {
        return fromAdminCuajado;
    }

    public static void setFromAdminCuajado(boolean fromAdminCuajado) {
        Variables.fromAdminCuajado = fromAdminCuajado;
    }

    public static boolean isFromAdminEmpaque() {
        return fromAdminEmpaque;
    }

    public static void setFromAdminEmpaque(boolean fromAdminEmpaque) {
        Variables.fromAdminEmpaque = fromAdminEmpaque;
    }

    public static String getCodProdLaboratorio() {
        return codProdLaboratorio;
    }

    public static void setCodProdLaboratorio(String codProdLaboratorio) {
        Variables.codProdLaboratorio = codProdLaboratorio;
    }
    public static boolean isFromAdminFundido() {
        return fromAdminFundido;
    }

    public static void setFromAdminFundido(boolean fromAdminFundido) {
        Variables.fromAdminFundido = fromAdminFundido;
    }
    public static String getLoteLaboratorio() {
        return loteLaboratorio;
    }

    public static void setLoteLaboratorio(String loteLaboratorio) {
        Variables.loteLaboratorio = loteLaboratorio;
    }





    public static String getLoteTexturizador() {
        return loteTexturizador;
    }

    public static void setLoteTexturizador(String loteTexturizador) {
        Variables.loteTexturizador = loteTexturizador;
    }
    public static String getLoteEmpaque() {
        return loteEmpaque;
    }

    public static void setLoteEmpaque(String loteEmpaque) {
        Variables.loteEmpaque = loteEmpaque;
    }

    public static String getLoteFundido() {
        return loteFundido;
    }

    public static void setLoteFundido(String loteFundido) {
        Variables.loteFundido = loteFundido;
    }

    public static String getLoteCuajado() {
        return loteCuajado;
    }

    public static void setLoteCuajado(String loteCuajado) {
        Variables.loteCuajado = loteCuajado;
    }


    public static String getIp_servidor() {
        return ip_servidor;
    }

    public static void setIp_servidor(String ip_servidor) {
        Variables.ip_servidor = ip_servidor;
    }

    private static String ip_servidor;

    public static int getTipo_consulta() {
        return tipo_consulta;
    }

    public static void setTipo_consulta(int tipo_consulta) {
        Variables.tipo_consulta = tipo_consulta;
    }
    private static int tipo_consulta;

    public static String getNombre_excel() {
        return nombre_excel;
    }

    public static void setNombre_excel(String nombre_excel) {
        Variables.nombre_excel = nombre_excel;
    }

    public static String getNombre_tabla() {
        return nombre_tabla;
    }

    public static void setNombre_tabla(String nombre_tabla) {
        Variables.nombre_tabla = nombre_tabla;
    }

    private static String nombre_excel;
    private static String nombre_tabla;

    public static String getFechaExportacion() {
        return fechaExportacion;
    }

    public static void setFechaExportacion(String fechaExportacion) {
        Variables.fechaExportacion = fechaExportacion;
    }

    private static String fechaExportacion;

    public static Context getExportar() {
        return exportar;
    }

    public static void setExportar(Context exportar) {
        Variables.exportar = exportar;
    }

    private static Context exportar;

    public static int getNum_tina_mostrar() {
        return num_tina_mostrar;
    }

    public static void setNum_tina_mostrar(int num_tina_mostrar) {
        Variables.num_tina_mostrar = num_tina_mostrar;
    }

    private static int num_tina_mostrar;



    public static int getNumero_tina_A() {
        return numero_tina_A;
    }

    public static void setNumero_tina_A(int numero_tina_A) {
        Variables.numero_tina_A = numero_tina_A;
    }

    public static int getNumero_tina_B() {
        return numero_tina_B;
    }

    public static void setNumero_tina_B(int numero_tina_B) {
        Variables.numero_tina_B = numero_tina_B;
    }

    public static int getNumero_tina_C() {
        return numero_tina_C;
    }

    public static void setNumero_tina_C(int numero_tina_C) {
        Variables.numero_tina_C = numero_tina_C;
    }

    private static int numero_tina_A;
    private static int numero_tina_B;
    private static int numero_tina_C;


    public static String getLopen() {
        return lopen;
    }

    public static void setLopen(String lopen) {
        Variables.lopen = lopen;
    }

    private static String lopen;


    public static boolean isFromSearch()
    {
        return fromSearch;

    }
    public static void setFromSearch(boolean fromSearch)
    {

        Variables.fromSearch=fromSearch;
    }
    public static boolean isFromFundido() {
        return fromFundido;
    }

    public static void setFromFundido(boolean fromFundido) {
        Variables.fromFundido = fromFundido;
    }


    public static boolean isFromLaboratorio() {
        return fromLaboratorio;
    }
    public static void setFromLaboratorio(boolean fromLaboratorio) {
        Variables.fromLaboratorio = fromLaboratorio;
    }

    public static boolean isFromAdminLaboratorio() {
        return fromAdminLaboratorio;
    }

    public static void setFromAdminLaboratorio(boolean fromAdminLaboratorio) {
        Variables.fromAdminLaboratorio = fromAdminLaboratorio;
    }

    public static boolean isFromEmpaque()
    {
        return fromEmpaque;

    }
    public static void setFromEmpaque(boolean fromEmpaque)
    {

        Variables.fromEmpaque=fromEmpaque;
    }

    public static boolean isFromCuajado()
    {
        return fromCuajado;

    }
    public static void setFromCuajado(boolean fromCuajado)
    {
        Variables.fromCuajado=fromCuajado;
    }

    public static boolean isPendiente_tina_A() {
        return pendiente_tina_A;
    }

    public static void setPendiente_tina_A(boolean pendiente_tina_A) {
        Variables.pendiente_tina_A = pendiente_tina_A;
    }

    public static boolean isPendiente_tina_C() {
        return pendiente_tina_C;
    }

    public static void setPendiente_tina_C(boolean pendiente_tina_C) {
        Variables.pendiente_tina_C = pendiente_tina_C;
    }

    public static boolean isPendiente_tina_B() {
        return pendiente_tina_B;
    }

    public static void setPendiente_tina_B(boolean pendiente_tina_B) {
        Variables.pendiente_tina_B = pendiente_tina_B;
    }

    private static boolean pendiente_tina_B;
    private static boolean pendiente_tina_C;
    private static boolean tinaB;
    private static boolean tinaC;
    private static String num_tina;
    private static String lote;
    private static String silo;
    private static String familia;

    public static String getNombre_usuario() {
        return nombre_usuario;
    }

    public static void setNombre_usuario(String nombre_usuario) {
        Variables.nombre_usuario = nombre_usuario;
    }

    private static String nombre_usuario;



    private static String lecheSilo,grasaLecheSilo,phLeche,proteinaLecheSilo,grasaCrema,cremaEstandarizada,lecheTina,grasaLecheTina,proteinaTina;
    private static String adi1,lote1,adi2,lote2,adi3,lote3,adi4,lote4,adi5,lote5,adi6,lote6,adi7,lote7,adi8,lote8,adi9,lote9,adi10,lote10,adi11,lote11,adi12,lote12,adi13,lote13,adi14,lote14;

    public static String getNum_tina() {
        return num_tina;
    }

    public static void setNum_tina(String num_tina) {
        Variables.num_tina = num_tina;
    }

    public static String getLote() {
        return lote;
    }

    public static void setLote(String lote) {
        Variables.lote = lote;
    }

    public static String getSilo() {
        return silo;
    }

    public static void setSilo(String silo) {
        Variables.silo = silo;
    }

    public static String getFamilia() {
        return familia;
    }

    public static void setFamilia(String familia) {
        Variables.familia = familia;
    }

    public static String getLecheSilo() {
        return lecheSilo;
    }

    public static void setLecheSilo(String lecheSilo) {
        Variables.lecheSilo = lecheSilo;
    }

    public static String getGrasaLecheSilo() {
        return grasaLecheSilo;
    }

    public static void setGrasaLecheSilo(String grasaLecheSilo) {
        Variables.grasaLecheSilo = grasaLecheSilo;
    }

    public static String getPhLeche() {
        return phLeche;
    }

    public static void setPhLeche(String phLeche) {
        Variables.phLeche = phLeche;
    }

    public static String getProteinaLecheSilo() {
        return proteinaLecheSilo;
    }

    public static void setProteinaLecheSilo(String proteinaLecheSilo) {
        Variables.proteinaLecheSilo = proteinaLecheSilo;
    }

    public static String getGrasaCrema() {
        return grasaCrema;
    }

    public static void setGrasaCrema(String grasaCrema) {
        Variables.grasaCrema = grasaCrema;
    }

    public static String getCremaEstandarizada() {
        return cremaEstandarizada;
    }

    public static void setCremaEstandarizada(String cremaEstandarizada) {
        Variables.cremaEstandarizada = cremaEstandarizada;
    }

    public static String getLecheTina() {
        return lecheTina;
    }

    public static void setLecheTina(String lecheTina) {
        Variables.lecheTina = lecheTina;
    }

    public static String getGrasaLecheTina() {
        return grasaLecheTina;
    }

    public static void setGrasaLecheTina(String grasaLecheTina) {
        Variables.grasaLecheTina = grasaLecheTina;
    }

    public static String getProteinaTina() {
        return proteinaTina;
    }

    public static void setProteinaTina(String proteinaTina) {
        Variables.proteinaTina = proteinaTina;
    }

    public static String getAdi1() {
        return adi1;
    }

    public static void setAdi1(String adi1) {
        Variables.adi1 = adi1;
    }

    public static String getLote1() {
        return lote1;
    }

    public static void setLote1(String lote1) {
        Variables.lote1 = lote1;
    }

    public static String getAdi2() {
        return adi2;
    }

    public static void setAdi2(String adi2) {
        Variables.adi2 = adi2;
    }

    public static String getLote2() {
        return lote2;
    }

    public static void setLote2(String lote2) {
        Variables.lote2 = lote2;
    }

    public static String getAdi3() {
        return adi3;
    }

    public static void setAdi3(String adi3) {
        Variables.adi3 = adi3;
    }

    public static String getLote3() {
        return lote3;
    }

    public static void setLote3(String lote3) {
        Variables.lote3 = lote3;
    }

    public static String getAdi4() {
        return adi4;
    }

    public static void setAdi4(String adi4) {
        Variables.adi4 = adi4;
    }

    public static String getLote4() {
        return lote4;
    }

    public static void setLote4(String lote4) {
        Variables.lote4 = lote4;
    }

    public static String getAdi5() {
        return adi5;
    }

    public static void setAdi5(String adi5) {
        Variables.adi5 = adi5;
    }

    public static String getLote5() {
        return lote5;
    }

    public static void setLote5(String lote5) {
        Variables.lote5 = lote5;
    }

    public static String getAdi6() {
        return adi6;
    }

    public static void setAdi6(String adi6) {
        Variables.adi6 = adi6;
    }

    public static String getLote6() {
        return lote6;
    }

    public static void setLote6(String lote6) {
        Variables.lote6 = lote6;
    }

    public static String getAdi7() {
        return adi7;
    }

    public static void setAdi7(String adi7) {
        Variables.adi7 = adi7;
    }

    public static String getLote7() {
        return lote7;
    }

    public static void setLote7(String lote7) {
        Variables.lote7 = lote7;
    }

    public static String getAdi8() {
        return adi8;
    }

    public static void setAdi8(String adi8) {
        Variables.adi8 = adi8;
    }

    public static String getLote8() {
        return lote8;
    }

    public static void setLote8(String lote8) {
        Variables.lote8 = lote8;
    }

    public static String getAdi9() {
        return adi9;
    }

    public static void setAdi9(String adi9) {
        Variables.adi9 = adi9;
    }

    public static String getLote9() {
        return lote9;
    }

    public static void setLote9(String lote9) {
        Variables.lote9 = lote9;
    }

    public static String getAdi10() {
        return adi10;
    }

    public static void setAdi10(String adi10) {
        Variables.adi10 = adi10;
    }

    public static String getLote10() {
        return lote10;
    }

    public static void setLote10(String lote10) {
        Variables.lote10 = lote10;
    }

    public static String getAdi11() {
        return adi11;
    }

    public static void setAdi11(String adi11) {
        Variables.adi11 = adi11;
    }

    public static String getLote11() {
        return lote11;
    }

    public static void setLote11(String lote11) {
        Variables.lote11 = lote11;
    }

    public static String getAdi12() {
        return adi12;
    }

    public static void setAdi12(String adi12) {
        Variables.adi12 = adi12;
    }

    public static String getLote12() {
        return lote12;
    }

    public static void setLote12(String lote12) {
        Variables.lote12 = lote12;
    }

    public static String getAdi13() {
        return adi13;
    }

    public static void setAdi13(String adi13) {
        Variables.adi13 = adi13;
    }

    public static String getLote13() {
        return lote13;
    }

    public static void setLote13(String lote13) {
        Variables.lote13 = lote13;
    }

    public static String getAdi14() {
        return adi14;
    }

    public static void setAdi14(String adi14) {
        Variables.adi14 = adi14;
    }

    public static String getLote14() {
        return lote14;
    }

    public static void setLote14(String lote14) {
        Variables.lote14 = lote14;
    }

    public static String getTempCoagulacion() {
        return tempCoagulacion;
    }

    public static void setTempCoagulacion(String tempCoagulacion) {
        Variables.tempCoagulacion = tempCoagulacion;
    }

    public static String getPhPasta() {
        return phPasta;
    }

    public static void setPhPasta(String phPasta) {
        Variables.phPasta = phPasta;
    }

    public static String getHoraAdicionCuajo() {
        return horaAdicionCuajo;
    }

    public static void setHoraAdicionCuajo(String horaAdicionCuajo) {
        Variables.horaAdicionCuajo = horaAdicionCuajo;
    }

    public static String getTempCocido() {
        return tempCocido;
    }

    public static void setTempCocido(String tempCocido) {
        Variables.tempCocido = tempCocido;
    }

    public static String getHoraInicioDesue() {
        return horaInicioDesue;
    }

    public static void setHoraInicioDesue(String horaInicioDesue) {
        Variables.horaInicioDesue = horaInicioDesue;
    }

    public static String getLitrosSuero() {
        return litrosSuero;
    }

    public static void setLitrosSuero(String litrosSuero) {
        Variables.litrosSuero = litrosSuero;
    }

    public static String getPhDesuerado() {
        return phDesuerado;
    }

    public static void setPhDesuerado(String phDesuerado) {
        Variables.phDesuerado = phDesuerado;
    }

    public static String getSolidosDesuerado() {
        return solidosDesuerado;
    }

    public static void setSolidosDesuerado(String solidosDesuerado) {
        Variables.solidosDesuerado = solidosDesuerado;
    }

    public static String getPastaCuaj() {
        return pastaCuaj;
    }

    public static void setPastaCuaj(String pastaCuaj) {
        Variables.pastaCuaj = pastaCuaj;
    }

    public static String getNummoldesCuaj() {
        return nummoldesCuaj;
    }

    public static void setNummoldesCuaj(String nummoldesCuaj) {
        Variables.nummoldesCuaj = nummoldesCuaj;
    }

    public static String getKilosCuaj() {
        return KilosCuaj;
    }

    public static void setKilosCuaj(String kilosCuaj) {
        KilosCuaj = kilosCuaj;
    }

    private static String tempCoagulacion,phPasta,horaAdicionCuajo,tempCocido,horaInicioDesue,litrosSuero,phDesuerado,solidosDesuerado,pastaCuaj,nummoldesCuaj,KilosCuaj;

    public static boolean isTinaA() {
        return tinaA;
    }

    public static void setTinaA(boolean tinaA) {
        Variables.tinaA = tinaA;
    }

    public static boolean isTinaB() {
        return tinaB;
    }

    public static void setTinaB(boolean tinaB) {
        Variables.tinaB = tinaB;
    }

    public static boolean isTinaC() {
        return tinaC;
    }

    public static void setTinaC(boolean tinaC) {
        Variables.tinaC = tinaC;
    }



    public static String getEquipo_tina() {
        return equipo_tina;
    }

    public static void setEquipo_tina(String equipo_tina) {
        Variables.equipo_tina = equipo_tina;
    }



    public static Context getContextoGral() {
        return contextoGral;
    }

    public static void setContextoGral(Context contextoGral) {
        Variables.contextoGral = contextoGral;
    }


    public static int getLinea_fundido() {
        return linea_fundido;
    }

    public static void setLinea_fundido(int linea_fundido) {
        Variables.linea_fundido = linea_fundido;
    }




}
