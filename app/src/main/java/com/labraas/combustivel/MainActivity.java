package com.labraas.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);
    }
    public void calcularPreco( View view){
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();
        boolean resultado = validarCampos(precoAlcool, precoGasolina);
        if(resultado){
           Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);
        }else{
            textResultado.setText("preencha todos os campos!");
        }


    }
    public boolean validarCampos (String pAlcool, String pGasolina){
         boolean camposValidados = true;
         return camposValidados;
         if(pAlcool == null || pAlcool.equals("")){
            camposValidados = false;
         } else if (pGasolina == null || pGasolina.equals("")){
             camposValidados = false;
         }

    }

}