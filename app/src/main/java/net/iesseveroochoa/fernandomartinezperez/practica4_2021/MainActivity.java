package net.iesseveroochoa.fernandomartinezperez.practica4_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iesseveroochoa.fernandomartinezperez.practica4_2021.adapters.TareasAdapter;
import net.iesseveroochoa.fernandomartinezperez.practica4_2021.model.TareaViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvTareas;
    private TareaViewModel tareaViewModel;
    private FloatingActionButton fabNuevaTarea;
    private TareasAdapter adapter;
    int cuentaTareas = 1;
    public static final int OPTION_REQUEST_CREAR = 1;
    public static final int OPTION_REQUEST_EDITAR = 2;
    public final static String EXTRA_TAREA = "EXTRA_TAREA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTareas = findViewById(R.id.rvTareas);
        fabNuevaTarea = findViewById(R.id.fabNuevaTarea);

        adapter = new TareasAdapter();
        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        rvTareas.setAdapter(adapter);

        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        tareaViewModel.getListaTareas().observe(this, adapter::setListaTareas);

        adapter.setOnItemClickBorrarListener(tarea -> {

            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            builder.setMessage(getString(R.string.mensageBorrar) + tarea.getId() + "?").setTitle(R.string.borrar)
                    .setPositiveButton("Ok", (dialog, id) -> {
                        tareaViewModel.delTarea(tarea);
                        dialog.cancel();

                    })
                    .setNegativeButton(R.string.cancelar, (dialog, which) -> dialog.cancel());
            builder.show();

        });
        adapter.setOnItemClickEditarListener(tarea -> {
            Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
            intent.putExtra(EXTRA_TAREA, tarea);
            startActivityForResult(intent, OPTION_REQUEST_EDITAR);
        });
        fabNuevaTarea.setOnClickListener(v -> {


            Intent intent = new Intent(MainActivity.this, NuevaTareaActivity.class);
            int codigoNuevaTarea = OPTION_REQUEST_CREAR;
            startActivityForResult(intent, codigoNuevaTarea);

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.accion_AcercaDe) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            AcercaDe dialogo = new AcercaDe();
            dialogo.show(fragmentManager, "tagAlerta");

            return true;
        } else if (id == R.id.accion_ordenar) {


            return false;

        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            /**En caso de que la tarea sea nuava aqui se recoge y se añade al RV*/
            Tarea tarea = data.getParcelableExtra(EXTRA_TAREA);

            if (requestCode == OPTION_REQUEST_CREAR) {



                tareaViewModel.addTarea(tarea);

                /**en caso de que la tarea fuese una editada se sobreesciben los datos*/
            } else if (requestCode == OPTION_REQUEST_EDITAR) {

                tareaViewModel.addTarea(tarea);
            }
        }
    }
}