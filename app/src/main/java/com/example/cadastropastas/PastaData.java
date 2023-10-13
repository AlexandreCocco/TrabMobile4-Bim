package com.example.cadastropastas;

import java.util.ArrayList;
import java.util.List;

public class PastaData {
    private static PastaData instance;
    private List<String> pastas;

    private PastaData() {
        pastas = new ArrayList<>();
    }

    public static PastaData getInstance() {
        if (instance == null) {
            instance = new PastaData();
        }
        return instance;
    }

    public List<String> getPastas() {
        return pastas;
    }

    public void adicionarPasta(String nomePasta) {
        pastas.add(nomePasta);
    }
}