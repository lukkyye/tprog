package com.models.clases;
import com.models.datatypes.DTUsuario;
public abstract class Usuario { // abstract xq tiene hijos
    protected String nickname;
    protected String nombre;
    protected String apellido;
    protected String correo;
    protected String password;
    
    public Usuario(String nickname, String password, String nombre, String apellido, String correo) { //este es el constructor
        this.nickname = nickname; //this. asigna a esa variable de ese objeto 
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }

    public DTUsuario get_dt() {
    	return new DTUsuario(this.nickname, this.nombre, this.apellido, this.correo);
    }
     
    public String getNickname() { return nickname; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getPassword() {return this.password;}
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}
