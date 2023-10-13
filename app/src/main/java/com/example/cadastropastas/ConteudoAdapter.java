package com.example.cadastropastas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConteudoAdapter extends RecyclerView.Adapter<ConteudoAdapter.ViewHolder> {
    private List<String> listaDeConteudo;
    private Context context;

    public ConteudoAdapter(List<String> listaDeConteudo, Context context) {
        this.listaDeConteudo = listaDeConteudo;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_conteudo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String conteudo = listaDeConteudo.get(position);
        holder.textConteudo.setText(conteudo);
    }

    @Override
    public int getItemCount() {
        return listaDeConteudo.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textConteudo;

        public ViewHolder(View itemView) {
            super(itemView);
            textConteudo = itemView.findViewById(R.id.textConteudo);
        }
    }
}
