package RegistrarTramite.dtos;


public class TipoTramiteResumenDTO {
    
    private int codTipoTramite;
    private String nombreTipoTramite;
    private String descripcionTipoTramite;
    private String nombreCategoriaTipoTramite; 

    public int getCodTipoTramite() {
        return codTipoTramite;
    }

    public void setCodTipoTramite(int codTipoTramite) {
        this.codTipoTramite = codTipoTramite;
    }

    public String getNombreTipoTramite() {
        return nombreTipoTramite;
    }

    public void setNombreTipoTramite(String nombreTipoTramite) {
        this.nombreTipoTramite = nombreTipoTramite;
    }

    public String getDescripcionTipoTramite() {
        return descripcionTipoTramite;
    }

    public void setDescripcionTipoTramite(String descripcionTipoTramite) {
        this.descripcionTipoTramite = descripcionTipoTramite;
    }

    public String getNombreCategoriaTipoTramite() {
        return nombreCategoriaTipoTramite;
    }

    public void setNombreCategoriaTipoTramite(String nombreCategoriaTipoTramite) {
        this.nombreCategoriaTipoTramite = nombreCategoriaTipoTramite;
    }
    
    
    
}
