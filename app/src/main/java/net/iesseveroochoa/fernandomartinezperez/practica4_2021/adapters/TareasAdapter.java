package net.iesseveroochoa.fernandomartinezperez.practica4_2021.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.iesseveroochoa.fernandomartinezperez.practica4_2021.R;
import net.iesseveroochoa.fernandomartinezperez.practica4_2021.Tarea;

import java.util.List;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder> {
    private List<Tarea> listaTareas;
    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickEditarListener listenerEditar;

    public void setListaTareas(List<Tarea> tareas){
        listaTareas=tareas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TareasAdapter.TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.TareaViewHolder holder, int position) {
        if (listaTareas != null) {
            final Tarea tarea = listaTareas.get(position);

            holder.tvResumen.setText(tarea.getId() + "-" + tarea.getResumen());
            holder.tvTecnico.setText(tarea.getId() + "-" + tarea.getTecnico());
            switch (tarea.getEstado()) {
                case "Abierta":
                    //holder.ivEstado.setImageResource(R.mipmap.ic_abierta);
                    break;
                case "En curso":
                    //holder.ivEstado.setImageResource(R.mipmap.ic_en_curso);
                    break;
                case "Terminada":
                    //holder.ivEstado.setImageResource(R.mipmap.ic_terminada);
                    break;
            }
            if (tarea.getPrioridad().equals("alta")) {
                holder.clItem.setBackgroundResource(R.color.prusia);
            } else {
                holder.clItem.setBackgroundColor(Color.TRANSPARENT);

            }
        }
    }

    @Override
    public int getItemCount() {
        if (listaTareas!= null)
            return listaTareas.size();
        else return 0;
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder {

        private TextView tvResumen;
        private TextView tvTecnico;
        private ImageView ivEstado;
        private ImageView ivEditar;
        private ImageView ivBorrar;
        private ConstraintLayout clItem;

        private TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvResumen = itemView.findViewById(R.id.tvResumen);
            tvTecnico = itemView.findViewById(R.id.tvTecnico);
            ivEstado = itemView.findViewById(R.id.ivEstado);


            ivEditar = itemView.findViewById(R.id.ivEditar);
            ivEditar.setOnClickListener(v -> listenerEditar.onItemClickEditar(
                    listaTareas.get(TareaViewHolder.this.getAbsoluteAdapterPosition())));


            ivBorrar = itemView.findViewById(R.id.ivBorrar);
            ivBorrar.setOnClickListener(v -> listenerBorrar.onItemClickBorrar(
                    listaTareas.get(TareaViewHolder.this.getAbsoluteAdapterPosition())));

            clItem = itemView.findViewById(R.id.clItem);

        }
    }

    public interface OnItemClickBorrarListener {
        void onItemClickBorrar(Tarea tarea);

    }

    public interface OnItemClickEditarListener {
        void onItemClickEditar(Tarea tarea);
    }

    public void setOnItemClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;

    }

    public void setOnItemClickEditarListener(OnItemClickEditarListener listener) {
        this.listenerEditar = listener;

    }

}
