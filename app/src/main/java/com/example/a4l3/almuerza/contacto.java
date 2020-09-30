package com.example.a4l3.almuerza;



public class contacto {
   int id;
   String solapin;
   int edad;
   int ci;
   String nombre;
   short virgen;
   String cargo;
   String ueb;

    public contacto(String solapin, int edad, int ci, String nombre, short virgen, String cargo, String ueb) {
        //this.id = id;
        this.solapin = solapin;
        this.edad=edad;
        this.ci=ci;
        this.nombre = nombre;
        this.virgen = virgen;
        this.cargo=cargo;
        this.ueb=ueb;
    }

    public contacto(int id, String solapin, int edad, int ci, String nombre, short virgen, String cargo, String ueb) {
        this.id = id;
        this.solapin = solapin;
        this.edad=edad;
        this.ci=ci;
        this.nombre = nombre;
        this.virgen = virgen;
        this.cargo=cargo;
        this.ueb=ueb;
    }




    public String getSolapin() {
        return (solapin);
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Short getVirgen() {
        return virgen;
    }

   /* public boolean isVirgen() {
        if (virgen==0){
            return false;
        }else{
            return true;
        }

    }*/

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getUeb() {
        return ueb;
    }

    public void setUeb(String ueb) {
        this.ueb = ueb;
    }

    public void setVirgen(short virgen) {
        this.virgen = virgen;
    }

    public void setSolapin(String solapin) {
        this.solapin = solapin;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    }
