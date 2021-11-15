package net.iesseveroochoa.fernandomartinezperez.practica4_2021.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import net.iesseveroochoa.fernandomartinezperez.practica4_2021.Tarea;

import java.util.ArrayList;
import java.util.List;

public class TareaViewModel extends AndroidViewModel {
    private MutableLiveData<List<Tarea>> listaTareasLiveData;

    private List<Tarea> listaTareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);

        listaTareasLiveData = new MutableLiveData<List<Tarea>>();
        crearDatos();
        listaTareasLiveData.setValue(listaTareas);

    }


    public LiveData<List<Tarea>> getListaTareas() {
        return listaTareasLiveData;
    }
    /**Este metodo sirve para setear una lista de tareas precargada en caso de ser necesario*/
    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }
    /**Este metodo sirve par añadir una tarea*/
    public void addTarea(Tarea tarea) {
        int i = listaTareas.indexOf(tarea);
        if (i < 0) {
            listaTareas.add(tarea);
            listaTareasLiveData.setValue(listaTareas);

        } else {
            listaTareas.remove(i);
            listaTareas.add(i, tarea);
            listaTareasLiveData.setValue(listaTareas);

        }
    }
    /**Este metodo sirve para borrar una tarea*/
    public void delTarea(Tarea tarea) {
        if (listaTareas.size() > 0) {
            listaTareas.remove(tarea);
            listaTareasLiveData.setValue(listaTareas);
        }
    }
    /**Este metodo sive para contar el numero de tareas en la lista*/
    public int getItemCount() {
        if (listaTareas != null) {
            return listaTareas.size();
        } else {
            return 0;
        }
    }
    /**Este metodo sirve para conseguir el indice de una tarea dentro de la lista*/
    public int tareaIndexOf(Tarea tarea) {
        if (tarea == null) {
            return 0;
        } else {
            return listaTareas.indexOf(tarea);
        }
    }
    /**Este metodo sirve para crear las tareas de ejemplo*/
    private void crearDatos() {
        listaTareas = new ArrayList<Tarea>();
        Tarea tarea = new Tarea("Alta", "Mantenimiento", "Abierta", "Juan " +
                "Perez", "Actualización de antivirus", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Mantenimiento", "Terminada", "Maria " +
                "Perez", "Actualización de S.O.Linux", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Baja", "Reparación", "En curso", "Maria " +
                "Lopez", "Sustitución de ratones", "Lorem ipsum dolor sit amet, consectetur " +
                "adipiscing elit. Mauris laoreet aliquam sapien, quis mattis diam pretium " +
                "vel. Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed " +
                "blandit ex fringilla at. Vivamus non sem vitae nisl viverra pharetra. " +
                "Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu " +
                "sed risus interdum fermentum. Integer ornare lorem urna, eget consequat " +
                "ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele " +
                "Martinez", "Presentar presupuesto Web", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "9 " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
        listaTareas.add(tarea);
        tarea = new Tarea("Media", "Comercial", "Abierta", "Fele " +
                "Martinez", "Presentar presupuesto Web", "Lorem ipsum dolor sit amet, " +
                "consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis " +
                "diam pretium vel. Integer nec tincidunt turpis. Vestibulum interdum " +
                "accumsan massa, sed blandit ex fringilla at. Vivamus non sem vitae nisl " +
                "viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. " +
                "Sed blandit arcu sed risus interdum fermentum. Integer ornare lorem urna, " +
                "eget consequat ante lacinia et. Phasellus ut diam et diam euismod " +
                "convallis");
        listaTareas.add(tarea);
    }
    /**Este metodo sive para sobreescribir los datos de una tarea en concreto*/
    public void setDatos(int indice, String prioridad, String categoria, String estado, String tecnico, String resumen, String descripcion) {


        if (indice > 0) {
            listaTareas.get(indice).setPrioridad(prioridad);
            listaTareas.get(indice).setCategoria(categoria);
            listaTareas.get(indice).setEstado(estado);
            listaTareas.get(indice).setTecnico(tecnico);
            listaTareas.get(indice).setDescripcion(descripcion);
            listaTareas.get(indice).setResumen(resumen);
            listaTareasLiveData.setValue(listaTareas);

        }
    }
}
