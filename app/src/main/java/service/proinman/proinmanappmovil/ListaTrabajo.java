package service.proinman.proinmanappmovil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;

import service.proinman.clases.UbicacionGeografica;
import service.proinman.rest.HttpClient;
import service.proinman.rest.OnHttpRequestComplete;
import service.proinman.rest.Response;

public class ListaTrabajo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_trabajo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson= new GsonBuilder().create();
                    try {
                        JSONArray jsonArray = new JSONArray(status.getResult());
                        ArrayList<UbicacionGeografica> regiones = new ArrayList<UbicacionGeografica>();


                        for (int i=0; i < jsonArray.length(); i++){
                            String ubicacion = jsonArray.getString(i);
                            UbicacionGeografica region = gson.fromJson(ubicacion,UbicacionGeografica.class);
                            regiones.add(region);
                            TextView t =new TextView(getBaseContext());
                            t.setText(region.getDescripcion());
                            stackContenct.addView(t);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Activity2.this, status.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cliente.excecute("http://192.168.100.5:8180/proinman/rest/ubicacionGeografica/consultarRegiones");




    }

}
