package service.proinman.proinmanappmovil;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import service.proinman.clases.UbicacionGeografica;
import service.proinman.rest.HttpClient;
import service.proinman.rest.OnHttpRequestComplete;
import service.proinman.rest.Response;


public class Activity2 extends AppCompatActivity {

    LinearLayout stackContenct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        stackContenct = (LinearLayout) findViewById(R.id.StackContent);

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

    public void getData(){




    }

}
