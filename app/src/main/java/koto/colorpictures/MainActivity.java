package koto.colorpictures;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final int PETICION_FOTO = 1;
    public static final int PETICION_VIDEO = 2;
    public static final int PETICION_GALERIA_FOTOS = 3;
    public static final int PETICION_GALERIA_VIDEOS = 4;

    public static final int MEDIA_FOTO = 5;
    public static final int MEDIA_VIDEO = 6;

    Uri mediaUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void verCamara(View view) {

        try {
            mediaUri = crearArchivoMedio(PETICION_FOTO);

            if (mediaUri == null){
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }else{

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,mediaUri);
                startActivityForResult(intent,PETICION_FOTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void verVideos(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){

        }else{
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void verGaleriaFotos(View view) {
    }

    public void verGaleriaVideos(View view) {
    }

    private boolean almacenamientoExternoDisponible(){
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }

    }
    private Uri crearArchivoMedio(int tipoMedio) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String nombreArchivo;
        File archivo;

        if(tipoMedio == 1){
            nombreArchivo = "IMG_" + timeStamp + "_";
            File directorioAlmacenamiento = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            archivo = File.createTempFile(nombreArchivo,".jpg",directorioAlmacenamiento);

            Log.d("TAG", archivo.getAbsolutePath());

            return Uri.fromFile(archivo);



        }else if(tipoMedio == 2 ){
            nombreArchivo = "VID_" + timeStamp + "_";
            File directorioAlmacenamiento = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            archivo = File.createTempFile(nombreArchivo,".mp4",directorioAlmacenamiento);

            Log.d("TAG", archivo.getAbsolutePath());

            return Uri.fromFile(archivo);
        }else{
            return null;
        }


    }
}
