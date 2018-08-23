package service.proinman.proinmanappmovil;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import service.proinman.clases.Cotizacion;
import service.proinman.clases.CotizacionItem;
import service.proinman.clases.ListaTarea;
import service.proinman.clases.Solicitud;
import service.proinman.rest.HttpClient;
import service.proinman.rest.OnHttpRequestComplete;
import service.proinman.rest.Response;

/**
 * Created by User on 1/2/2018.
 */

public class GalleryActivity extends AppCompatActivity {

    private static final String TAG = "GalleryActivity";
    private int codigoSolicitud;
    private int codigoTarea;
    private TableLayout tabla;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");
        tabla = (TableLayout) findViewById(R.id.simpleTableLayout);

        getIncomingIntent(savedInstanceState);
        consultarDatosSolicitud();
        consultarCotizacionPorCodigoSolicitud();
    }

    private void getIncomingIntent(@Nullable Bundle bundle){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrl = getIntent().getStringExtra("image_url");
            String imageName = getIntent().getStringExtra("image_name");
            codigoTarea = getIntent().getIntExtra("codigoTarea",0);
            codigoSolicitud = getIntent().getIntExtra("codigoSolicitud",0);

            //setImage(imageUrl, imageName);
        }
    }

    private void consultarDatosSolicitud(){

        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson= new GsonBuilder().create();
                    try {
                        JSONObject jsonObj = new JSONObject(status.getResult());
                        String solicitudString = jsonObj.toString();
                        Solicitud solicitud = gson.fromJson(solicitudString,Solicitud.class);

                        TextView editTextDireccion = (TextView) findViewById(R.id.txtDireccion);
                        editTextDireccion.setText(solicitud.getDireccion());

                        TextView editTextDescripcion = (TextView) findViewById(R.id.txtDescripcion);
                        editTextDescripcion.setText(solicitud.getDescipcion());


                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //Toast.makeText(LoginActivity.this, status.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cliente.excecute(Constantes.URL_WEB_SERVICE+"/gestionSolicitud/consultarSolicitudPorCodigo?codigoSolicitud="+codigoSolicitud);


    }


    private void consultarCotizacionPorCodigoSolicitud(){

        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson= new GsonBuilder().create();
                    try {
                        JSONObject jsonObj = new JSONObject(status.getResult());
                        String cotizacionString = jsonObj.toString();
                        Cotizacion cotizacion = gson.fromJson(cotizacionString,Cotizacion.class);

                        for (CotizacionItem item : cotizacion.getListaCotizacionItems()){
                            TextView itemNombre =new TextView(getBaseContext());
                            itemNombre.setText(item.getCatalogoItem().getDescripcion());
                            itemNombre.setBackgroundColor(Color.GRAY);
                            itemNombre.setPadding(5,5,5,5);
                            itemNombre.setTextColor(Color.BLUE);
                            itemNombre.setTextSize(12);

                            TextView itemCantidad =new TextView(getBaseContext());
                            itemCantidad.setText(item.getCantidad().toString());
                            itemCantidad.setBackgroundColor(Color.GRAY);
                            itemCantidad.setPadding(5,5,5,5);
                            itemCantidad.setTextColor(Color.BLUE);
                            itemCantidad.setTextSize(12);


                            TableRow filaNueva = new TableRow(getBaseContext());
                            filaNueva.addView(itemNombre);
                            filaNueva.addView(itemCantidad);
                            tabla.addView(filaNueva);

                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        cliente.excecute(Constantes.URL_WEB_SERVICE+"/gestionCotizacion/consultarCotizacionPorCodigoSolicitud?codigoSolicitud=10");//+codigoSolicitud);

    }


    private void setImage(String imageUrl, String imageName){
        Log.d(TAG, "setImage: setting te image and name to widgets.");

        TextView name = findViewById(R.id.image_description);
        name.setText(imageName);

        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(image);
    }

}


















