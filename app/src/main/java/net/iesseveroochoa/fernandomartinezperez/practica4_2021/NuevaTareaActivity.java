package net.iesseveroochoa.fernandomartinezperez.practica4_2021;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NuevaTareaActivity extends AppCompatActivity {
    private EditText etTecnico;
    private EditText etBreveDes;
    private EditText etmDescripcion;
    private FloatingActionButton fabGuardar;
    private Spinner spCategoria;
    private Spinner spPrioridad;
    private Spinner spEstado;
    private ImageView ivEstadoAb;
    private ImageView ivEstadoEC;
    private ImageView ivEstadoTe;

    public final static String EXTRA_TAREA = "EXTRA_TAREA";

    boolean esEdicion;
    Tarea tareaAnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_tarea);
        Intent intent = new Intent();
        //int indice = getIntent().getIntExtra(EXTRA_INDICE, 0);

        tareaAnt = getIntent().getParcelableExtra(EXTRA_TAREA);

        /** aqui se enlazan los objetos con los recursos correspondientes*/

        ivEstadoAb = findViewById(R.id.ivEstadoAb);
        ivEstadoEC = findViewById(R.id.ivEstadoEC);
        ivEstadoTe = findViewById(R.id.ivEstadoTe);

        spCategoria = findViewById(R.id.spCategoria);
        spPrioridad = findViewById(R.id.spPrioridad);
        spEstado = findViewById(R.id.spEstado);
        etTecnico = findViewById(R.id.etTecnico);
        etBreveDes = findViewById(R.id.etResumen);
        etmDescripcion = findViewById(R.id.etmDescripcion);
        fabGuardar = findViewById(R.id.fabGuardar);
/**En caso de que la actividad no sea nuava se recoje y muestan los datos anteriores*/
        if (tareaAnt != null) {
            esEdicion = true;
            this.setTitle(getString(R.string.nTarea) + tareaAnt.getId());


            spCategoria.setSelection(getIndex(spCategoria, tareaAnt.getCategoria()));
            spPrioridad.setSelection(getIndex(spPrioridad, tareaAnt.getPrioridad()));
            spEstado.setSelection(getIndex(spEstado, tareaAnt.getEstado()));

            etTecnico.setText(tareaAnt.getTecnico());
            etBreveDes.setText(tareaAnt.getResumen());
            etmDescripcion.setText(tareaAnt.getDescripcion());
        } else {
            esEdicion = false;
            this.setTitle(getString(R.string.nuevaT));
        }

/**Aqui en caso de que se cambie algo del spiner 'Estado' se cambia la imagen en consecuencia*/
        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String posicionActual = parent.getItemAtPosition(position).toString();
                if (posicionActual.equals(getString(R.string.abierta))) {
                    ivEstadoAb.setVisibility(View.VISIBLE);
                    ivEstadoEC.setVisibility(View.INVISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals(getString(R.string.enCurso))) {
                    ivEstadoAb.setVisibility(View.INVISIBLE);
                    ivEstadoEC.setVisibility(View.VISIBLE);
                    ivEstadoTe.setVisibility(View.INVISIBLE);
                } else if (posicionActual.equals(getString(R.string.terminada))) {
                    ivEstadoAb.setVisibility(View.INVISIBLE);
                    ivEstadoEC.setVisibility(View.INVISIBLE);
                    ivEstadoTe.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
/**Aqui si se pulsa 'guardar' se recogen los datos en pantalla y se envian a la acividad principal*/
        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (etTecnico.getText().toString().isEmpty() || etmDescripcion.getText().toString().isEmpty() || etBreveDes.toString().isEmpty()) {

                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(NuevaTareaActivity.this);

                    builder.setMessage(getString(R.string.cvacioMensage))
                            .setTitle(R.string.campovacio)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    builder.show();
                } else {

                    Tarea tarea;

                    String categoria = spCategoria.getSelectedItem().toString();
                    String prioridad = spPrioridad.getSelectedItem().toString();
                    String estado = spEstado.getSelectedItem().toString();


                    if (esEdicion == true) {
                        tarea = new Tarea(tareaAnt.getId(), prioridad, categoria, estado, etTecnico.getText().toString(), etBreveDes.getText().toString(), etmDescripcion.getText().toString());

                    } else {
                        tarea = new Tarea(prioridad, categoria, estado, etTecnico.getText().toString(), etBreveDes.getText().toString(), etmDescripcion.getText().toString());

                    }

                    intent.putExtra(EXTRA_TAREA, tarea);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });


    }

    /**
     * Este metodo sirve para localizar un valor a traves de su string en un spinner
     */
    private int getIndex(Spinner spinner, String valor) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(valor)) {
                return i;
            }
        }
        return 0;
    }

}