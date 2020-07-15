package com.labraas.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    private AdView adview;
    private InterstitialAd mInterstitialAd;
    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9584107572536485/2112277072");
        AdRequest adRequesInter = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequesInter);

        MobileAds.initialize(this, "ca-app-pub-9584107572536485~3440002314");
        adview = (AdView) findViewById(R.id.adViewBan);
        AdRequest adRequest =  new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);

        SimpleMaskFormatter smf = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher mtwA = new MaskTextWatcher(editPrecoAlcool, smf);
        MaskTextWatcher mtwG = new MaskTextWatcher(editPrecoGasolina, smf);
        editPrecoAlcool.addTextChangedListener(mtwA);
        editPrecoGasolina.addTextChangedListener(mtwG);


    }

    public void calcularPreco( View view){



        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        boolean camposValidados = validarCampos(precoAlcool, precoGasolina);
        if(camposValidados){

            Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);
            if(valorAlcool / valorGasolina >=0.7){
                textResultado.setTextColor(Color.parseColor("#ff0000"));
                textResultado.setText("É melhor utilizar Gasolina");
            }else {
                textResultado.setTextColor(Color.parseColor("#2c3d91"));
                textResultado.setText("É melhor utilizar Álcool");
            }


        }else{
            textResultado.setText("preencha todos os campos!");
        }

        fecharTeclado();


    }
    public boolean validarCampos (String pAlcool, String pGasolina){
         boolean camposValidados = true;

         if(pAlcool == null || pAlcool.equals("")){
            camposValidados = false;

         } else if (pGasolina == null || pGasolina.equals("")){
             camposValidados = false;
         }
             return camposValidados;
    }

    private void fecharTeclado(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager fech = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            fech.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }


}