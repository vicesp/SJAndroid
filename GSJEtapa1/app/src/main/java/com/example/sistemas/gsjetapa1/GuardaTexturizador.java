import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.sistemas.gsjetapa1.Texturizador;

import org.ksoap2.serialization.SoapObject;

import DTO.Variables;

public class GuardaTexturizador extends AsyncTask<String, Void, Boolean>
{
    private final ProgressDialog dialog = new ProgressDialog(Texturizador.this);

    @Override
    protected void onPreExecute()
    {
        this.dialog.setMessage("Enviando Datos...");
        this.dialog.show();
    }

    protected Boolean doInBackground(final String... args)
    {
        final String NAMESPACE = "http://serv_gsj.net/";
        final String URL = "http://" + Variables.getIp_servidor() + "ServidorWebSoap/ServicioClientes.asmx";
        final String METHOD_NAME = "insertatexturizador";
        final String SOAP_ACTION = NAMESPACE + METHOD_NAME;
        final int time = 2000, time2 = 190000;

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("lote", Lote.getText().toString());
        request.addProperty("fecha", Fecha.getText().toString());
        request.addProperty("texturizador", texturizador_select);
        request.addProperty("mp002", tvtx1.getText().toString());
        request.addProperty("lote_mp002",lote1.getText().toString());
        request.addProperty("mp003", tvtx2.getText().toString());
        request.addProperty("lote_mp003", lote2.getText().toString());
        request.addProperty("mp004", tvtx3.getText().toString());
        request.addProperty("lote_mp004", lote3.getText().toString());
        request.addProperty("mp005", tvtx4.getText().toString());
        request.addProperty("lote_mp005", lote4.getText().toString());
        request.addProperty("mp006", tvtx5.getText().toString());
        request.addProperty("lote_mp006", lote5.getText().toString());
        request.addProperty("mp007", tvtx6.getText().toString());
        request.addProperty("lote_mp007", lote6.getText().toString());
        request.addProperty("mp008", tvtx7.getText().toString());
        request.addProperty("lote_mp008", lote7.getText().toString());
        request.addProperty("mp009", tvtx8.getText().toString());
        request.addProperty("lote_mp009", lote8.getText().toString());
        request.addProperty("mp010", tvtx9.getText().toString());
        request.addProperty("lote_mp010", lote9.getText().toString());
        request.addProperty("mp021", tvtx10.getText().toString());
        request.addProperty("lote_mp021", lote10.getText().toString());
        request.addProperty("mp025", tvtx11.getText().toString());
        request.addProperty("lote_mp025", lote11.getText().toString());
        request.addProperty("mp026", tvtx12.getText().toString());
        request.addProperty("lote_mp026", lote12.getText().toString());
        request.addProperty("mp027", tvtx13.getText().toString());
        request.addProperty("lote_mp027", lote13.getText().toString());
        request.addProperty("mp028", tvtx14.getText().toString());
        request.addProperty("lote_mp028", lote14.getText().toString());
        request.addProperty("mp031", tvtx15.getText().toString());
        request.addProperty("lote_mp031", lote15.getText().toString());
        request.addProperty("mp012", tvtx16.getText().toString());
        request.addProperty("lote_mp012", lote16.getText().toString());
        request.addProperty("mp013", tvtx17.getText().toString());
        request.addProperty("lote_mp013", lote17.getText().toString());
        request.addProperty("mp014", tvtx18.getText().toString());
        request.addProperty("lote_mp014", lote18.getText().toString());
        request.addProperty("kilos_totales", kilos_tot.getText().toString());
        request.addProperty("num_consecutivo", numero_conse);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        HttpTransportSE transporte = new HttpTransportSE(URL, time);

        try
        {
            transporte.call(SOAP_ACTION, envelope);

            SoapPrimitive resultado_XML = (SoapPrimitive)envelope.getResponse();
            String mensaje = resultado_XML.toString();

            if(mensaje.contentEquals("true")){
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            Log.i("Error", "Error de sincronizacion:  " + e);
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
            Toast.makeText(Texturizador.this, "Sincronizacion Exitosa", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(Texturizador.this, "Error de Sincronizacion", Toast.LENGTH_SHORT).show();
        }
    }

    public void PaginaMonitor()
    {
        WebView myWebView = (WebView) findViewById(R.id.webView);
        myWebView.loadUrl("http://" + Variables.getIp_servidor() + "SignalRTest/simplechat.aspx?val=123");

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }
}
}