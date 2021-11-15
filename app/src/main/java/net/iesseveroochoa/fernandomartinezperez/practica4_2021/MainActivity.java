package net.iesseveroochoa.fernandomartinezperez.practica4_2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import net.iesseveroochoa.fernandomartinezperez.practica4_2021.adapters.TareasAdapter;
import net.iesseveroochoa.fernandomartinezperez.practica4_2021.model.TareaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvTareas;
private TareaViewModel tareaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTareas = findViewById(R.id.rvTareas);


        TareasAdapter adapter = new TareasAdapter();
        rvTareas.setLayoutManager(new LinearLayoutManager(this));
        rvTareas.setAdapter(adapter);

        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);
        tareaViewModel.getListaTareas().observe(this, tareas -> adapter.setListaTareas(tareas));
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
}