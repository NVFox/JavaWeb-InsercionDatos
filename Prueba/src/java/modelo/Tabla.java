package modelo;

import java.util.ArrayList;

public class Tabla {
    private String nombreTabla;
    private String[] camposTabla;
    private String[] valoresTabla;
    private ArrayList<String[]> valoresTotales;

    public Tabla(String nombreTabla, String[] camposTabla, String[] valoresTabla) {
        this.nombreTabla = nombreTabla;
        this.camposTabla = camposTabla;
        this.valoresTabla = valoresTabla;
    }
    
    public Tabla(String nombreTabla, String[] camposTabla, ArrayList<String[]> valoresTotales) {
        this.nombreTabla = nombreTabla;
        this.camposTabla = camposTabla;
        this.valoresTotales = valoresTotales;
    }

    public String getNombreTabla() {
        return nombreTabla;
    }

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    public String[] getCamposTabla() {
        return camposTabla;
    }

    public void setCamposTabla(String[] camposTabla) {
        this.camposTabla = camposTabla;
    }

    public String[] getValoresTabla() {
        return valoresTabla;
    }

    public void setValoresTabla(String[] valoresTabla) {
        this.valoresTabla = valoresTabla;
    }

    public ArrayList<String[]> getValoresTotales() {
        return valoresTotales;
    }

    public void setValoresTotales(ArrayList<String[]> valoresTotales) {
        this.valoresTotales = valoresTotales;
    }
    
    
}
