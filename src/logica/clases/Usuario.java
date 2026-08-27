package logica.clases;

public abstract class Usuario {
    protected String nickname;
    protected String nombre;
    protected String apellido;
    protected String correo;

    public Usuario(String nickname, String nombre, String apellido, String correo) {
        this.nickname = nickname;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public String getNickname() { return nickname; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
}
