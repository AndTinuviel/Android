package service.proinman.proinmanappmovil;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");

        getIncomingIntent(savedInstanceState);
        consultarDatosSolicitud();
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

                        System.out.println("*******************  solicitud getCodigoSolicitud "+solicitud.getCodigoSolicitud());

                        TextView editTextDireccion = (TextView) findViewById(R.id.txtDireccion);
                        editTextDireccion.setText(solicitud.getDireccion());

                        TextView editTextDescripcion = (TextView) findViewById(R.id.txtDescripcion);
                        editTextDescripcion.setText(solicitud.getDescipcion());



//                        if(resultadoLogguinBoolean){
//                            verificacionCorrecta = true;
//                        }else{
//                            verificacionCorrecta = false;
//                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //Toast.makeText(LoginActivity.this, status.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cliente.excecute(Constantes.URL_WEB_SERVICE+"/gestionSolicitud/consultarSolicitudPorCodigo?codigoSolicitud="+codigoSolicitud);


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


















