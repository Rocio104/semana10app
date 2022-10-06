package com.itca.appmysql.ui.categorias;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.itca.appmysql.MySingleton;
import com.itca.appmysql.R;

import org.json.JSONException;
import org.json.JSONObject;


public class Categorias extends Fragment {
    private EditText etcodigo;
    private Button btncat;
    private TextView tvid, tvnombre;

    public Categorias() {
    }


    /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_categorias, container, false);

        etcodigo = root.findViewById(R.id.etcodigo);
        btncat = root.findViewById(R.id.btncat);
        tvid = root.findViewById(R.id.tvid);
        tvnombre = root.findViewById(R.id.tvnombre);


        btncat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Clic en botón Guardar", Toast.LENGTH_SHORT).show();
                recibirJson(getContext());
            }
        });


        return root;
    }


    private void guardar(){

    }

    private boolean estado(){

        return true;
    }

    public void recibirJson(final Context context){

        String url = "https://servicestechnology.com.sv/ws/json1.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject respuestaJSON = new JSONObject(response.toString());
                    String dato1 = respuestaJSON.getString("idcategoria");
                    String dato2 = respuestaJSON.getString("nombre");
                    Toast.makeText(context, "Datos recibidos: \n" +"Id: " + dato1 + ".\n" + "nombre:"+dato2, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "Respuesta: " + response.toString(), Toast.LENGTH_SHORT).show();

                    tvid.setText("Id categoria: " + dato1);
                    tvnombre.setText("Nombre categoria: " + dato2);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "ERROR. Verifque su conexión.", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }


}