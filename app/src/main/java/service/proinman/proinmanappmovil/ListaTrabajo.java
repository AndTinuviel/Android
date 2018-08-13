package service.proinman.proinmanappmovil;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;

import service.proinman.clases.ListaTarea;
import service.proinman.clases.UbicacionGeografica;
import service.proinman.rest.HttpClient;
import service.proinman.rest.OnHttpRequestComplete;
import service.proinman.rest.Response;

public class ListaTrabajo extends AppCompatActivity {

    LinearLayout stackContenct2;

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

        stackContenct2 = (LinearLayout) findViewById(R.id.StackContenct2);

        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson= new GsonBuilder().create();
                    try {
                        JSONArray jsonArray = new JSONArray(status.getResult());
                        ArrayList<ListaTarea> tareas = new ArrayList<ListaTarea>();


                        for (int i=0; i < jsonArray.length(); i++){
                            String arrayTareas = jsonArray.getString(i);
                            ListaTarea tarea = gson.fromJson(arrayTareas,ListaTarea.class);
                            tareas.add(tarea);
                            TextView t =new TextView(getBaseContext());
                            t.setText(tarea.getEstado());
                            stackContenct2.addView(t);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(ListaTrabajo.this, status.getResult(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cliente.excecute("http://192.168.100.5:8180/proinman/rest/tareas/consultarTareasPorUsuario");

    }

}
