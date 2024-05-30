package com.model.usuario;

/**
 * La clase {@code DeportesUsuario} representa las actividades deportivas recomendadas
 * para un usuario durante diferentes fases de su ciclo menstrual.
 */
public class DeportesUsuario {
    private String usuario;
    private String deporteFaseMenstrual;
    private String deporteFaseFolicular;
    private String deporteFaseOvular;
    private String deporteFaseLutea;

    /**
     * Crea una nueva instancia de {@code DeportesUsuario} sin inicializar sus atributos.
     */
    public DeportesUsuario() {
    }

    /**
     * Crea una nueva instancia de {@code DeportesUsuario} con los atributos especificados.
     *
     * @param usuario             el nombre del usuario
     * @param deporteFaseMenstrual el deporte recomendado durante la fase menstrual
     * @param deporteFaseFolicular el deporte recomendado durante la fase folicular
     * @param deporteFaseOvular    el deporte recomendado durante la fase ovular
     * @param deporteFaseLutea     el deporte recomendado durante la fase lutea
     */
    public DeportesUsuario(String usuario, String deporteFaseMenstrual, String deporteFaseFolicular, String deporteFaseOvular, String deporteFaseLutea) {
        this.usuario = usuario;
        this.deporteFaseMenstrual = deporteFaseMenstrual;
        this.deporteFaseFolicular = deporteFaseFolicular;
        this.deporteFaseOvular = deporteFaseOvular;
        this.deporteFaseLutea = deporteFaseLutea;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return el nombre del usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param usuario el nombre del usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el deporte recomendado durante la fase menstrual.
     *
     * @return el deporte recomendado durante la fase menstrual
     */
    public String getDeporteFaseMenstrual() {
        return deporteFaseMenstrual;
    }

    /**
     * Establece el deporte recomendado durante la fase menstrual.
     *
     * @param deporteFaseMenstrual el deporte recomendado durante la fase menstrual
     */
    public void setDeporteFaseMenstrual(String deporteFaseMenstrual) {
        this.deporteFaseMenstrual = deporteFaseMenstrual;
    }

    /**
     * Obtiene el deporte recomendado durante la fase folicular.
     *
     * @return el deporte recomendado durante la fase folicular
     */
    public String getDeporteFaseFolicular() {
        return deporteFaseFolicular;
    }

    /**
     * Establece el deporte recomendado durante la fase folicular.
     *
     * @param deporteFaseFolicular el deporte recomendado durante la fase folicular
     */
    public void setDeporteFaseFolicular(String deporteFaseFolicular) {
        this.deporteFaseFolicular = deporteFaseFolicular;
    }

    /**
     * Obtiene el deporte recomendado durante la fase ovular.
     *
     * @return el deporte recomendado durante la fase ovular
     */
    public String getDeporteFaseOvular() {
        return deporteFaseOvular;
    }

    /**
     * Establece el deporte recomendado durante la fase ovular.
     *
     * @param deporteFaseOvular el deporte recomendado durante la fase ovular
     */
    public void setDeporteFaseOvular(String deporteFaseOvular) {
        this.deporteFaseOvular = deporteFaseOvular;
    }

    /**
     * Obtiene el deporte recomendado durante la fase lutea.
     *
     * @return el deporte recomendado durante la fase lutea
     */
    public String getDeporteFaseLutea() {
        return deporteFaseLutea;
    }

    /**
     * Establece el deporte recomendado durante la fase lutea.
     *
     * @param deporteFaseLutea el deporte recomendado durante la fase lutea
     */
    public void setDeporteFaseLutea(String deporteFaseLutea) {
        this.deporteFaseLutea = deporteFaseLutea;
    }
}
