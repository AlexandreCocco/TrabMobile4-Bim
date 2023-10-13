package com.example.cadastropastas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PastaAdapter extends RecyclerView.Adapter<PastaAdapter.PastaViewHolder> {
    private List<String> pastas;
    private OnPastaClickListener listener;

    public PastaAdapter(List<String> pastas, OnPastaClickListener listener) {
        this.pastas = pastas;
        this.listener = listener;
    }

    public interface OnPastaClickListener {
        void onPastaClick(String nomePasta);
    }

    @NonNull
    @Override
    public PastaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pasta, parent, false);
        return new PastaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PastaViewHolder holder, int position) {
        String nomePasta = pastas.get(position);
        holder.nomePastaTextView.setText(nomePasta);
    }

    @Override
    public int getItemCount() {
        return pastas.size();
    }

    class PastaViewHolder extends RecyclerView.ViewHolder {
        TextView nomePastaTextView;

        PastaViewHolder(View itemView) {
            super(itemView);
            nomePastaTextView = itemView.findViewById(R.id.textViewNomePasta);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    String nomePasta = pastas.get(position);
                    listener.onPastaClick(nomePasta);
                }
            });
        }
    }
}
