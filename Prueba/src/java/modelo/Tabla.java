package modelo;

public class Tabla {
    private String nombreTabla;
    private String[] camposTabla;
    private String[] valoresTabla;

    public Tabla(String nombreTabla, String[] camposTabla, String[] valoresTabla) {
        this.nombreTabla = nombreTabla;
        this.camposTabla = camposTabla;
        this.valoresTabla = valoresTabla;
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
}
