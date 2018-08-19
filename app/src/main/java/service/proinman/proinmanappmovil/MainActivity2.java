package service.proinman.proinmanappmovil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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


public class MainActivity2 extends AppCompatActivity {

    private static final String TAG = "MainActivity2";
    private static String USERNAME = "";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDetalleTarea = new ArrayList<>();
    private ArrayList<ListaTarea> tareas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.d(TAG, "onCreate: started.");

        Bundle parametros = this.getIntent().getExtras();
        USERNAME = parametros.getString("username");

        System.out.println("************** USERNAME "+USERNAME);
        consultarTareasPorUsuario();
        initImageBitmaps();
    }


    private void consultarTareasPorUsuario(){

        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Gson gson= new GsonBuilder().create();
                    try {
                        JSONArray jsonArray = new JSONArray(status.getResult());
                        tareas = new ArrayList<ListaTarea>();
                        for (int i=0; i < jsonArray.length(); i++){
                            String tarea = jsonArray.getString(i);
                            ListaTarea tareaObjeto = gson.fromJson(tarea,ListaTarea.class);
                            tareas.add(tareaObjeto);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        cliente.excecute( Constantes.URL_WEB_SERVICE+"/tareas/consultarTareasPorUsuario?username="+USERNAME);


    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");


        for (ListaTarea tarea:  tareas) {

            mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
            mNames.add(tarea.getMotorActividad().getNombre()+ " CLIENTE: " +
                    tarea.getSolicitud().getCliente().getNombreRazonSocial()+" CIUDAD: " +
                    tarea.getSolicitud().getCiudad().getDescripcion() + " DESCRIPCIÓN: " +
                    tarea.getSolicitud().getDescipcion()+" DIRECCIÓN: " +
                    tarea.getSolicitud().getDireccion());
//            mDetalleTarea.add(tarea.getSolicitud().getCliente().getNombreRazonSocial()+
//                    tarea.getSolicitud().getDescipcion()+
//            tarea.getSolicitud().getDireccion());

        }
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}






















