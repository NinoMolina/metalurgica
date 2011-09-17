//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\produccion\\CodigoDeBarra.java
package metalsoft.negocio.produccion;

public class CodigoDeBarra {

    private long idCodigo;
    private int imagen;
    private String descripcion;
    private String codigo;
   

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getIdCodigo() {
        return idCodigo;
    }

    public void setIdCodigo(long idCodigo) {
        this.idCodigo = idCodigo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    /**
     * @roseuid 4C27ED2602C8
     */
    public CodigoDeBarra() {
    }

}
