package com.example.a4l3.almuerza;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;


public class Data_Base {
    SQLiteDatabase cx;
    ArrayList<contacto> listado = new ArrayList<contacto>();
    Principal p;
    contacto c;
    Context ct;
    anadir a;
    String nombreBD = "plantilla";



    String tabla = "Create table if not exists contacto(id integer,"
            + "solapin TEXT primary key,"+" edad integer, "+" ci integer, "+" nombre TEXT,"+" virgen short default 0, "+" cargo TEXT, "+" ueb TEXT)";

    public Data_Base(Context c) {
        this.ct = c;
        cx = c.openOrCreateDatabase(nombreBD, Context.MODE_PRIVATE, null);
        cx.execSQL(tabla);
    }

    public boolean insertar(contacto c) {
       // short virenn=0;
        ContentValues contenedor = new ContentValues();
        contenedor.put("solapin", c.getSolapin());
        contenedor.put("edad", c.getEdad());
        contenedor.put("ci", c.getCi());
        contenedor.put("nombre", c.getNombre());
        contenedor.put("virgen", c.getVirgen());
        contenedor.put("cargo", c.getCargo());
        contenedor.put("ueb", c.getUeb());
        return (cx.insert("contacto", null, contenedor)) > 0;
    }

    public boolean editar(contacto c, String solapinn) {
        // short virenn=0;
        ContentValues contenedor = new ContentValues();
        contenedor.put("solapin", c.getSolapin());
        contenedor.put("nombre", c.getNombre());
        contenedor.put("virgen", c.getVirgen());
        return (cx.update("contacto", contenedor, "solapin=" +solapinn,null)) > 0;
    }


    public ArrayList<contacto> verTodos() {
        listado.clear();
        Cursor cursor = cx.rawQuery("select * from contacto", null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                listado.add(new contacto(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getShort(5),
                        cursor.getString(6),
                        cursor.getString(7)

                ));
            } while (cursor.moveToNext());
        }
        return listado;
    }

    public int total()
    {
        int cont=-1;
        Cursor cursor = cx.rawQuery("select * from contacto", null);
        cursor.moveToFirst();
        while (!cursor.isLast())
        {
            cont=cont+1;
        }
        return cont;
    }

    public void cambia_estado()
    {
            Cursor cursor = cx.rawQuery("update contacto set virgen=0 " , null);
            if (cursor != null && cursor.getCount() > 0)
                cursor.moveToFirst();
            verTodos();
    }

    public void cambia_mi_estado(String solapinnn)
    {
        Cursor cursor = cx.rawQuery("update contacto set virgen=0 where solapin="+solapinnn , null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        verTodos();
    }

    public contacto verUno(String solapinn) {


      Cursor cursor = cx.rawQuery("select * from contacto where solapin="+solapinn, null);
        c=null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            c = new contacto(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getShort(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            }
        return  c;
    }

    public contacto dame_estado(String solapinn) {


        Cursor cursor = cx.rawQuery("select * from contacto where solapin="+solapinn, null);
        c=null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            c = new contacto(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getShort(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        return  c;
    }

    public contacto virgenes( ) {


        Cursor cursor = cx.rawQuery("select * from contacto ", null);
        c=null;

            cursor.moveToPosition(5);
            c = new contacto(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getShort(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );

        return  c;
    }




    public contacto verUno_Nombre(String nombree) {
        Cursor cursor = cx.rawQuery("select * from contacto where nombre="+ nombree, null);
        c=null;
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            c = new contacto(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getString(4),
                    cursor.getShort(5),
                    cursor.getString(6),
                    cursor.getString(7)
            );
        }
        return  c;
    }

    public void editar(String num)
    {
        Cursor cursor = cx.rawQuery("update contacto set virgen=1 where solapin=" + num, null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        verTodos();
    }

    public void bajandaS(String baja)
    {
        Cursor cursor = cx.rawQuery("delete from contacto where solapin=" + baja, null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        verTodos();
    }
    /*public void editarN(String nombre_new,String num_viejo)
    {
        Cursor cursor = cx.rawQuery("update contacto set nombre="+nombre_new+" where solapin=" + num_viejo, null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        verTodos();
    }*/
    public void editarS(String num_new,String num_old)
    {
        Cursor cursor = cx.rawQuery("update contacto set solapin="+num_new+" where solapin=" + num_old, null);
        if (cursor != null && cursor.getCount() > 0)
            cursor.moveToFirst();
        verTodos();
    }
}
