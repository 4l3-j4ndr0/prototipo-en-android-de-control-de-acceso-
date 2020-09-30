package com.example.a4l3.almuerza;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;

public class Principal extends AppCompatActivity {
    ArrayList<contacto> listado;
    SQLiteDatabase cx;
    Data_Base bd;
    adaptador adapter;
    contacto c;
    Context ct;
    anadir a;
    TextView campo_solapa;



    //guardar una variable en las preferencias para conservarla cuando vuelva a iniciar la apk
    Calendar calendario= Calendar.getInstance();
    int dia_viejo=calendario.get(Calendar.DATE);
    int dia_nuevo=calendario.get(Calendar.DATE);
    private String PREFS_KEY = "misPreferencias";
    //metodo pa guardar
     public void saveValuesPreference(Context context, String PREFS_KEY, int value)
     {
         SharedPreferences settings=context.getSharedPreferences(PREFS_KEY,MODE_PRIVATE);
         SharedPreferences.Editor editor;
         editor=settings.edit();
         editor.putInt(PREFS_KEY,value);
         editor.apply();
     }
     //metodo pa obtener el valor guardado
    public int getValuePreference (Context context, String PREFS_KEY)
    {
        SharedPreferences preferences=context.getSharedPreferences(PREFS_KEY,MODE_PRIVATE);
        return  preferences.getInt(PREFS_KEY,0);
    }
//################ cargar menu de la carpeta creada menu ###############################

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.principal,menu);
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
            case R.id.action_uno: startActivity(new Intent(Principal.this, yo.class));
            break;
            default: break;
            case R.id.action_config:
                final TextView campo_solapa_principal=(TextView) findViewById(R.id.solapa);
                final Dialog dialogo_login=new Dialog(Principal.this);
                dialogo_login.setTitle("Eliminar Registro");
                dialogo_login.setCancelable(true);
                dialogo_login.setContentView(R.layout.dialogo_login);
                dialogo_login.show();
                final EditText campo_user = (EditText)dialogo_login.findViewById(R.id.user);
                final EditText campo_pass = (EditText)dialogo_login.findViewById(R.id.pass);
                final TextView campo_solapa=(TextView)findViewById(R.id.solapa);
                Button aceptar_login=(Button)dialogo_login.findViewById(R.id.botaceptar);
                Button cancelar_login=(Button)dialogo_login.findViewById(R.id.botcancelar);
                aceptar_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* if (campo_user.getText().toString().equals("admin") &&
                                campo_pass.getText().toString().equals("admin")) {*/
                            if (campo_solapa.getText().toString().equals("solapín del comensal")) {
                                startActivity(new Intent(Principal.this, anadir.class));
                                Toast.makeText(getApplicationContext(), "Redireccionando",Toast.LENGTH_SHORT).show();
                                dialogo_login.dismiss();
                            }
                                else{
                    //############## enviar datos de un editext a otra activity ########################
                               //se crea el intent
                                Intent i=new Intent(Principal.this,anadir.class);
                               //se guarda el text ene l intent
                                i.putExtra("este",campo_solapa_principal.getText());
                               // se lanza el intent
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Redireccionando",Toast.LENGTH_SHORT).show();
                                dialogo_login.dismiss();
                            }
                       /* } else {
                            Toast.makeText(getApplicationContext(), "Usuario o contraseña incurrecta"
                                    , Toast.LENGTH_SHORT).show();

                        }*/
                    }
                });
                cancelar_login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo_login.dismiss();
                    }
                });

               /* if (!campo_solapa_principal.getText().toString().equals("solapín del comensal"))
                {
                    startActivity(new Intent(Principal.this, login.class));
                }else
                {
                    Bundle b=new Bundle();
                    b.putString("datos",campo_solapa_principal.getText().toString());
                    Intent i=new Intent(Principal.this,login.class);
                    i.putExtra("este",b);
                }
               startActivity(new Intent(Principal.this, login.class));*/
            }
        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        bd = new Data_Base(this);
        listado = bd.verTodos();
        dia_viejo=getValuePreference(getApplicationContext(),"dia_viejo");



 //###########################################################################

        if (dia_viejo!=dia_nuevo)
        {
            bd.cambia_estado();
            dia_viejo=dia_nuevo;
            //llamada del metodo de guardar las preferencias
            saveValuesPreference(getApplicationContext(),"dia_viejo",dia_viejo);

        }

        //#############################################################################################

      /*
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd.verUno_Nombre(prueba.getText().toString());
            }
        });*/

        //#############################################################################################


       /*//saltar para otra activity
        findViewById(R.id.config).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
        });*/



        findViewById(R.id.listo).setOnClickListener(new View.OnClickListener() {
           @SuppressLint("NewApi")
           @Override
           public void onClick(View v) {
               EditText CampoSolapinPrincipal = (EditText)findViewById(R.id.solapin_principal);

                contacto temp=null;
                short ass=1;
                 campo_solapa=(TextView)findViewById(R.id.solapa);
               TextView campo_nombrata=(TextView)findViewById(R.id.nombrata);
               com.example.a4l3.almuerza.CircleImageView foto=(com.example.a4l3.almuerza.CircleImageView)findViewById(R.id.foto_comensal);

               if  (CampoSolapinPrincipal.getText().toString().isEmpty())
               {
                   Toast.makeText(getApplication(), "Campo solapín vacio", Toast.LENGTH_SHORT).show();
               } else {
                   temp=bd.verUno(CampoSolapinPrincipal.getText().toString());
                   if(temp==null)

                    {
                        Toast toast = new Toast(Principal.this);
                        //usamos cualquier layout como Toast
                        View toast_layout = getLayoutInflater().inflate(R.layout.toast, (ViewGroup) findViewById(R.id.lytLayout));
                        toast.setView(toast_layout);
                        //se podría definir el texto en el layout si es invariable pero lo hacemos programáticamente como ejemplo
                        //tenemos acceso a cualquier widget del layout del Toast
                        TextView textView = (TextView) toast_layout.findViewById(R.id.toastMessageNo);
                        //textView.setText(R.string.mensaje);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.show();
                    } else if (temp.getSolapin().equals(CampoSolapinPrincipal.getText().toString()) && temp.getVirgen()==0)
                        {
                            temp.setVirgen(ass);

                            bd.editar(CampoSolapinPrincipal.getText().toString());
                            Toast toast = new Toast(Principal.this);
                            //usamos cualquier layout como Toast
                            View toast_layout = getLayoutInflater().inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.lytLayout));
                            toast.setView(toast_layout);
                            //se podría definir el texto en el layout si es invariable pero lo hacemos programáticamente como ejemplo
                            //tenemos acceso a cualquier widget del layout del Toast
                            TextView textView = (TextView) toast_layout.findViewById(R.id.toastMessage);
                            //textView.setText(R.string.mensaje);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.show();
                            campo_solapa.setText(temp.getSolapin());
                            Bundle b=new Bundle();
                            b.putString("datos", campo_solapa.getText().toString());
                            campo_nombrata.setText(temp.getNombre());

                            foto.setImageResource(R.drawable.yo);
                         }
                         else if (temp.getVirgen()!=0)
                   {
                       Toast.makeText(getApplication(), "El comensal ya consumió el servicio", Toast.LENGTH_SHORT).show();
                   }

               }

                CampoSolapinPrincipal.setText("");
           }
       });

    }
}



