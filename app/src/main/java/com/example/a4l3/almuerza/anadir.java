package com.example.a4l3.almuerza;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class anadir extends AppCompatActivity {
    Data_Base bd;
    adaptador adapter;
    ArrayList<contacto> listado;
    contacto c;
    Principal p;

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_anadir,menu);
        if (menu instanceof MenuBuilder)
        {
            MenuBuilder m=(MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    //###################### hacer cuando se pulse algo del menu #############################

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            //###################### cambiar activity ###########################################################
            case R.id.action_uno: startActivity(new Intent(anadir.this, yo.class));
                break;
            default: break;
            //###################### ANADIR ###########################################################
           /* case R.id.action_agregar:

                        final Dialog dialogo=new Dialog(anadir.this);
                        dialogo.setTitle("Nuevo Registro");
                        dialogo.setCancelable(true);
                        dialogo.setContentView(R.layout.dialogo);
                        dialogo.show();
                        final EditText campoSolapin = (EditText)dialogo.findViewById(R.id.solapin);
                        final EditText campoNombre = (EditText)dialogo.findViewById(R.id.nombre);
                        Button aceptar=(Button)dialogo.findViewById(R.id.botaceptar);
                        Button cancelar=(Button)dialogo.findViewById(R.id.botcancelar);

                        aceptar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String Nombre=campoNombre.getText().toString();

                                    if (campoSolapin.getText().toString().isEmpty() || campoNombre.getText().toString().isEmpty())
                                    {
                                        Toast.makeText(getApplication(), "Faltan campos por llenar", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        if (campoNombre.getText().toString().charAt(0)==' ' ||
                                                campoNombre.getText().toString().charAt(campoNombre.getText().length()-1)==' ')
                                        {
                                            Toast.makeText(getApplication(), "El nombre no puede comenzar ni culminar con espacio", Toast.LENGTH_SHORT).show();
                                        }else {
                                            if (bd.verUno(campoSolapin.getText().toString())!=null)
                                            {
                                                Toast.makeText(getApplication(), "El comensal ya existe en la base de datos", Toast.LENGTH_SHORT).show();
                                            }else {
                                                if (campoSolapin.getText().toString().charAt(0)=='0')
                                                {
                                                    Toast.makeText(getApplication(), "El numero de solapín no puede empezar con cero", Toast.LENGTH_LONG).show();
                                                }else {
                                                    c = new contacto(campoSolapin.getText().toString(),
                                                            Nombre, (short) 0);
                                                    bd.insertar(c);

                                                    listado = bd.verTodos();
                                                    adapter.notifyDataSetChanged();
                                                    dialogo.dismiss();
                                                    Toast.makeText(getApplication(), "Comensal añadido correctamente", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                }catch (Exception e)
                                {
                                    Toast.makeText(getApplication(), "Error interno", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        cancelar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogo.dismiss();
                            }
                        });
                break;*/
            //################### ELIMINAR ###########################################################
            case R.id.action_eliminar:
                        final Dialog dialogo_eliminar=new Dialog(anadir.this);
                dialogo_eliminar.setTitle("Eliminar Registro");
                dialogo_eliminar.setCancelable(true);
                dialogo_eliminar.setContentView(R.layout.dialogo_elimiar);
                dialogo_eliminar.show();
                        final EditText campoSolapin_eliminar = (EditText)dialogo_eliminar.findViewById(R.id.solapin_eliminar);
                        Button aceptar_elimiar=(Button)dialogo_eliminar.findViewById(R.id.botaceptar);
                        Button cancelar_eliminar=(Button)dialogo_eliminar.findViewById(R.id.botcancelar);

                aceptar_elimiar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                if (campoSolapin_eliminar.getText().toString().isEmpty())
                                {
                                    Toast.makeText(getApplication(), "Debe introducir un número de solapín", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    if (bd.verUno(campoSolapin_eliminar.getText().toString())==null)
                                    {
                                        Toast.makeText(getApplication(), "El comensal no existe en la base de datos", Toast.LENGTH_SHORT).show();
                                    }else {

                                        bd.bajandaS(campoSolapin_eliminar.getText().toString());
                                        bd.verTodos();
                                        dialogo_eliminar.dismiss();
                                        Toast.makeText(getApplication(), "Comensal eliminado correctamente", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                        });
                cancelar_eliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogo_eliminar.dismiss();
                            }
                        });
                break;
            //################### CAMBIA ESTADO ###########################################################
            case R.id.action_estado:
                final Dialog dialogo_estado=new Dialog(anadir.this);
                dialogo_estado.setTitle("Cambiar estado");
                dialogo_estado.setCancelable(true);
                dialogo_estado.setContentView(R.layout.dialogo_estado);
                dialogo_estado.show();
                Button aceptar_estado=(Button)dialogo_estado.findViewById(R.id.botaceptar);
                Button cancelar_estado=(Button)dialogo_estado.findViewById(R.id.botcancelar);

                //#################### aki recupero datos pasados por el intent ######################################
                final EditText campoSolapin_estado = (EditText)dialogo_estado.findViewById(R.id.solapin_estado);
                //creo un bundle para recibir los datos
                Bundle b=getIntent().getExtras();
                //valido para q no llame un objeto nulo
                if (b != null) {
                    //obtengo el texto de la otra activity q obtuve en el bundle
                    String valor_cogido=b.getString("este");
                    //cambio el texto
                    campoSolapin_estado.setText(valor_cogido);
                }



                //####################################################################################################

                aceptar_estado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        if (campoSolapin_estado.getText().toString().isEmpty())
                        {
                            Toast.makeText(getApplication(), "Debe introducir un número de solapín", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if (bd.verUno(campoSolapin_estado.getText().toString())==null)
                            {
                                Toast.makeText(getApplication(), "El comensal no existe en la base de datos", Toast.LENGTH_SHORT).show();
                            }else {

                                bd.cambia_mi_estado(campoSolapin_estado.getText().toString());
                                adapter.notifyDataSetChanged();
                                bd.verTodos();
                                final ListView list = (ListView) findViewById(R.id.listado);
                                list.setAdapter(adapter);
                                dialogo_estado.dismiss();
                                Toast.makeText(getApplication(), "Cambiado estado de comensal correctamente", Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                });
                cancelar_estado.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo_estado.dismiss();
                    }
                });
                break;
            //########################### EDITAR #######################################################
           /* case R.id.action_editar:

                        final Dialog dialogo_editar=new Dialog(anadir.this);
                dialogo_editar.setTitle("Nuevo Registro");
                dialogo_editar.setCancelable(true);
                dialogo_editar.setContentView(R.layout.dialogo_editar);
                dialogo_editar.show();


                        final EditText campoSolapin_old = (EditText)dialogo_editar.findViewById(R.id.solapin_old);

                        final EditText campoSolapin_New = (EditText)dialogo_editar.findViewById(R.id.solapin_new);
                        final EditText campoNombre_New = (EditText)dialogo_editar.findViewById(R.id.nombre_new);



                        Button aceptar_editar=(Button)dialogo_editar.findViewById(R.id.botaceptar);
                        Button cancelar_editar=(Button)dialogo_editar.findViewById(R.id.botcancelar);
                aceptar_editar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{

                                //todos vacios
                                if (campoSolapin_old.getText().toString().isEmpty() &&
                                        campoSolapin_New.getText().toString().isEmpty() && campoNombre_New.getText().toString().isEmpty())
                                {
                                    Toast.makeText(getApplication(), "No hay comensal que editar", Toast.LENGTH_SHORT).show();
                                }else {

                                    // los dos nuevos vacios
                                    if (campoNombre_New.getText().toString().isEmpty() && campoSolapin_New.getText().toString().isEmpty()) {
                                        Toast.makeText(getApplication(), "Introduzca almenos un valor nuevo para editar el existente", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //solapin viejo vacio
                                        if (campoSolapin_old.getText().toString().isEmpty()) {
                                            Toast.makeText(getApplication(), "Campo 'solapin existente' no puede estar vacío", Toast.LENGTH_SHORT).show();
                                        } else {

                                            // solapin viejo no existe
                                            if (bd.verUno(campoSolapin_old.getText().toString()) == null) {
                                                Toast.makeText(getApplication(), "El solapin introducido no se encuentra en la base de datos", Toast.LENGTH_SHORT).show();
                                            }else
                                            {
                                                //solapin nuevo vacio
                                                if (campoSolapin_New.getText().toString().isEmpty())
                                                {
                                                    if (campoNombre_New.getText().toString().charAt(0) == ' ' ||
                                                            campoNombre_New.getText().toString().charAt(campoNombre_New.getText().length() - 1) == ' ')
                                                        Toast.makeText(getApplication(), "El nombre no puede comenzar ni culminar con espacio", Toast.LENGTH_SHORT).show();
                                                    else
                                                    {
                                                        c = new contacto(campoSolapin_old.getText().toString(),
                                                                campoNombre_New.getText().toString(), bd.dame_estado(campoSolapin_old.getText().toString()).getVirgen());
                                                        bd.editar(c,campoSolapin_old.getText().toString());
                                                        listado = bd.verTodos();
                                                        adapter.notifyDataSetChanged();
                                                        dialogo_editar.dismiss();
                                                        Toast.makeText(getApplication(), "Nombre del comensal editado corectamente", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    if (campoNombre_New.getText().toString().isEmpty()) {
                                                        if (bd.verUno(campoSolapin_New.getText().toString()) == null) {
                                                            bd.editarS(campoSolapin_New.getText().toString(), campoSolapin_old.getText().toString());
                                                            dialogo_editar.dismiss();
                                                            Toast.makeText(getApplication(), "Solapin del comensal editado correctamente", Toast.LENGTH_SHORT).show();
                                                        } else {
                                                            Toast.makeText(getApplication(), "El nuevo numero de solapin ya existe en la base de datos", Toast.LENGTH_LONG).show();
                                                        }

                                                    } else
                                                    {
                                                        if (bd.verUno(campoSolapin_New.getText().toString()) == null) {
                                                            c = new contacto(campoSolapin_New.getText().toString(),
                                                                    campoNombre_New.getText().toString(), bd.dame_estado(campoSolapin_old.getText().toString()).getVirgen());
                                                            bd.editar(c, campoSolapin_old.getText().toString());
                                                            listado = bd.verTodos();
                                                            adapter.notifyDataSetChanged();
                                                            dialogo_editar.dismiss();
                                                            Toast.makeText(getApplication(), "Comensal editado completo correctamete", Toast.LENGTH_SHORT).show();
                                                        }else
                                                        {Toast.makeText(getApplication(), "El nuevo numero de solapin ya existe en la base de datos", Toast.LENGTH_LONG).show();}
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                        }catch(Exception e)
                        {
                            {
                                Toast.makeText(getApplication(), "Error interno", Toast.LENGTH_SHORT).show();
                            }
                        }
                            }

                        });
                cancelar_editar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialogo_editar.dismiss();
                            }
                        });
                break;*/
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);


        bd = new Data_Base(this);
        listado = bd.verTodos();
        adapter = new adaptador(this, listado, bd);
        final ListView list = (ListView) findViewById(R.id.listado);
        list.setAdapter(adapter);




        //################### ELIMINAR ###########################################################





        //########################### EDITAR #######################################################

    }

}
