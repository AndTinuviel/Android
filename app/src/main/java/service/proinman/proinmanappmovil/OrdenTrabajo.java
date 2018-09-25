package service.proinman.proinmanappmovil;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageLoader;

import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import service.proinman.clases.Cotizacion;
import service.proinman.clases.CotizacionItem;
import service.proinman.clases.ReporteTrabajo;
import service.proinman.clases.Solicitud;
import service.proinman.rest.HttpClient;
import service.proinman.rest.OnHttpRequestComplete;
import service.proinman.rest.Response;

/**
 * Created by User on 1/2/2018.
 */

public class OrdenTrabajo extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GalleryActivity";
    private int codigoSolicitud;
    private int codigoTarea;
    private TableLayout tabla;
    private EditText horaDesde, horaHasta;
    private int hora,minutos;
    private Solicitud solicitud;

    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //Variables para uso de camara
    private Button ImageButton;
    private ImageView picImageView;

    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    private static final int CAMERA_REQUEST = 1;
    private static final int GALLERY_REQUEST = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Log.d(TAG, "onCreate: started.");
        tabla = (TableLayout) findViewById(R.id.simpleTableLayout);

        horaDesde = (EditText) findViewById(R.id.txtHoraDesde);
        horaHasta = (EditText) findViewById(R.id.txtHoraHasta);

        horaDesde.setOnClickListener(this);
        horaHasta.setOnClickListener(this);

        getIncomingIntent(savedInstanceState);
        consultarDatosSolicitud();
        consultarCotizacionPorCodigoSolicitud();
        cargarCondifuracionParaUsoDeCamara();
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
                        solicitud = gson.fromJson(solicitudString,Solicitud.class);

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
                            itemNombre.setBackgroundColor(Color.rgb(235, 237, 239  ));
                            itemNombre.setPadding(5,5,5,5);
                            itemNombre.setTextColor(Color.BLACK);
                            itemNombre.setTextSize(20);
                            itemNombre.setLeft(10);

                            TextView itemCantidad =new TextView(getBaseContext());
                            itemCantidad.setText(item.getCantidad().toString());
                            itemCantidad.setBackgroundColor(Color.rgb(244, 246, 246  ));
                            itemCantidad.setPadding(5,5,5,5);
                            itemCantidad.setTextColor(Color.BLACK);
                            itemCantidad.setTextSize(20);
                            itemCantidad.setLeft(10);


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

    private void cargarCondifuracionParaUsoDeCamara(){
        // Los objetos de la librería que nos ayudarán en la tarea
        cameraPhoto = new CameraPhoto(getApplicationContext());
        galleryPhoto = new GalleryPhoto(getApplicationContext());

        // Array con las opciones para el diálogo que se abrirá al pulsar el botón "PIC"
        final String[] items = new String[] {"Camera", "Gallery"};

        // Creamos el diálogo
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select image");
        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0){
                    // Opción Cámara
                    callCameraApp();
                    dialog.cancel();
                }else{
                    // Opción Galería
                    startActivityForResult(galleryPhoto.openGalleryIntent(), GALLERY_REQUEST);
                }
            }
        });
        final AlertDialog dialog = builder.create();

        // Buscamos el botón por su ID y creamos una referencia al mismo
        ImageButton = (Button) findViewById(R.id.image_but);
        ImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        // Buscamos el ImageView por su ID y creamos una referencia al mismo
        picImageView = (ImageView) findViewById(R.id.picImageView);
    }

    private void callCameraApp(){
        try {
            // Lanzamos la actividad correspondiente a la Cámara, gracias a la librería.
            startActivityForResult(cameraPhoto.takePhotoIntent(), CAMERA_REQUEST);

            // Indicamos que queremos guardar la imagen tomada en la galería
            cameraPhoto.addToGallery();
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Something goes wrong while taking photos", Toast.LENGTH_SHORT).show();
        }
    }

    private void createImage(Bitmap bitmap){
        picImageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == CAMERA_REQUEST){
                // Obtenemos el path de la imagen que hemos tomado
                String photoPath = cameraPhoto.getPhotoPath();
                try {
                    // Creamos un bitmap a partir de la imagen y lo redimensionamos
                    Bitmap imageBitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();

                    // Aplicamos el Bitmap al ImageView
                    createImage(imageBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something goes wrong while loading photos", Toast.LENGTH_SHORT).show();
                }
            } else if(requestCode == GALLERY_REQUEST){
                // Creamos una ruta en formato Uri para los datos correspondientes a la imagen
                Uri uri = data.getData();

                // Asignamos esa ruta al objeto de la librería
                galleryPhoto.setPhotoUri(uri);

                // Obtenemos la ruta completa para acceder a la imagen
//                Uri direccion=galleryPhoto.getPath();
//                getApplicationContext().grantUriPermission(getCallingPackage(), fileUri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                String photoPath = galleryPhoto.getPath();
                try {
                    // Seguimos el mismo proceso que para la cámara
                    Bitmap imageBitmap = ImageLoader.init().from(photoPath).requestSize(512, 512).getBitmap();
                    createImage(imageBitmap);
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Something goes wrong while choosing photos", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onClick(final View vista) {
        final Calendar calendario= Calendar.getInstance();
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (vista==horaDesde){
                    horaDesde.setText(hourOfDay+":"+minute);
                }
                if (vista==horaHasta){
                    horaHasta.setText(hourOfDay+":"+minute);
                }
            }
        },hora,minutos,false);
        timePickerDialog.show();
    }

    public void guardarReporteTrabajo(View vista){
        //Setear datos
        ReporteTrabajo reporteTrabajo = new ReporteTrabajo();
        reporteTrabajo.setSolicitud(solicitud);
        try {
            reporteTrabajo.setFechaHoraDesde(convertStringToDate(horaDesde.getText().toString()));
            reporteTrabajo.setFechaHoraDesde(convertStringToDate(horaHasta.getText().toString()));
        }catch (ParseException e){
            System.out.println("Error al convertir a fechas"+ e.getMessage() + "causa "+ e.getCause());
        }
        reporteTrabajo.setObservacion((  (EditText)findViewById(R.id.txtObservacion)  ).getText().toString());

        //convertir a json
        String reporteJson = convertirObjetoAJson(reporteTrabajo);

        System.out.println("******************* reporteJson "+reporteJson);
        //Invocar rest para guardado
        HttpClient cliente =  new HttpClient(new OnHttpRequestComplete() {
            @Override
            public void onComplete(Response status) {
                if(status.isSuccess()){
                    Toast.makeText(OrdenTrabajo.this, "Guardado Correcto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cliente.excecute(Constantes.URL_WEB_SERVICE+"/reporteTrabajo/guardar="+reporteJson);


    }

    private String convertirObjetoAJson(Object objeto){
        Gson gson = new Gson();
        String json = gson.toJson(objeto);
        return json;
    }

    private Date convertStringToDate(String dateString) throws ParseException {
        String fechaEntera="";
        fechaEntera = fechaEntera.concat(" "+dateString);
        Date result;
        System.out.println("******************* convertStringToDate");
        synchronized(df) {
            System.out.println("******************* df");
            result = df.parse(fechaEntera);
            System.out.println("******************* parse");
        }
        System.out.println("******************* synchronized");
        return result;
    }

}


















