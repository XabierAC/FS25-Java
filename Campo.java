public class Campo {
    private int idCampo;
    private int idEstadoCultivo;
    private String estadoCultivo;
    private String cultivo;

    public Campo(int idCampo, int idEstadoCultivo, String cultivo){
        this.idCampo = idCampo;
        this.idEstadoCultivo = idEstadoCultivo;
        this.cultivo = cultivo;
        if (idEstadoCultivo == 1) {
            estadoCultivo = "Desmenuzado";
        } else if (idEstadoCultivo == 2) {
            estadoCultivo = "Arado o Labrado";
        } else if (idEstadoCultivo == 3) {
            estadoCultivo = "Con Cal";
        }
    }

    /* Muestra por consola todos los datos del campo solicitado */
    public void mostrarCampo() {

    }

    /* Agrega un nuevo campo a los ya adquiridos */
    public void agregarCampo() {

    }

    /* Elimina un campo que previamente habia sido adquirido */
    public void venderCampo() {

    }

    /* Edita informacion del campo indicado */
    public void editarCampo() {

    } 
}
