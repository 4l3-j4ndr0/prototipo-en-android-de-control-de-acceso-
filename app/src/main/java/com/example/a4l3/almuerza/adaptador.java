package com.example.a4l3.almuerza;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

public class adaptador extends BaseAdapter {
    ArrayList<contacto> listado;
    Data_Base bd;
    contacto c;
    Activity a;

    public adaptador(Activity a, ArrayList<contacto> listado, Data_Base bd) {
        this.listado = listado;
        this.bd = bd;
       this.a=a;
       }

    public ArrayList<contacto> getListado() {
        return listado;
    }

    public void setListado(ArrayList<contacto> listado) {
        this.listado = listado;
    }

    public Data_Base getBd() {
        return bd;
    }

    public void setBd(Data_Base bd) {
        this.bd = bd;
    }

    public contacto getC() {
        return c;
    }

    public void setC(contacto c) {
        this.c = c;
    }

    public Activity getA() {
        return a;
    }

    public void setA(Activity a) {
        this.a = a;
    }

    @Override
    public int getCount() {

        return listado.size();


    }

    @Override
    public contacto getItem(int i) {
        c = listado.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        c = listado.get(i);
        return c.getId();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        View v = view;
            if(v==null) {
                LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.item, null);
            }
        c = listado.get(posicion);
        TextView solapin = v.findViewById(R.id.t_solapin);
        TextView edad =  v.findViewById(R.id.t_edad);
        TextView ci =  v.findViewById(R.id.t_ci);
        TextView nombre = v.findViewById(R.id.t_nombre);
        TextView virgen =  v.findViewById(R.id.t_virgen);
        TextView cargo =  v.findViewById(R.id.t_cargo);
        TextView ueb =  v.findViewById(R.id.t_ueb);
        Switch luz=v.findViewById(R.id.switch2);
        //TextView estado =  v.findViewById(R.id.t_estado);


        // Button cambiar = (Button)v.findViewById(R.id.);
        // Button kitar = (Button)v.findViewById(R.id.kitar);

        solapin.setText(""+c.getSolapin());
        edad.setText(""+c.getEdad());
        ci.setText(""+c.getCi());
        nombre.setText(c.getNombre());
       virgen.setText(""+c.getVirgen());
        cargo.setText(c.getCargo());
        ueb.setText(c.getUeb());





        if (c.getVirgen()==1){
            luz.setTag(posicion);
            luz.setChecked(true);}
        //kitar.setTag(position);
        /*kitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

        /*cambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        kitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        return v;
    }
}
