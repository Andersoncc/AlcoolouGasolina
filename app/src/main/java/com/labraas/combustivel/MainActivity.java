package com.labraas.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    //private Button mBotao = (Button) findViewById(R.id.btnCalcular);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9584107572536485/2112277072");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        MobileAds.initialize(this, "ca-app-pub-9584107572536485~3440002314");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        adview = (AdView) findViewById(R.id.adViewBan);
        AdRequest adRequest =  new AdRequest.Builder().build();
        adview.loadAd(adRequest);


        editPrecoAlcool = findViewById(R.id.editPrecoAlcool);
        editPrecoGasolina = findViewById(R.id.editPrecoGasolina);
        textResultado = findViewById(R.id.textResultado);


    }


    /*mBotao.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }
    });*/





    public void calcularPreco( View view){
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGasolina = editPrecoGasolina.getText().toString();
        boolean camposValidados = validarCampos(precoAlcool, precoGasolina);
        if(camposValidados){
           Double valorAlcool = Double.parseDouble(precoAlcool);
            Double valorGasolina = Double.parseDouble(precoGasolina);
            if(valorAlcool / valorGasolina >=0.7){
                textResultado.setText("É melhor utilizar Gasolina");
            }else {
                textResultado.setText("É melhor utilizar Álcool");
            }


        }else{
            textResultado.setText("preencha todos os campos!");
        }


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


}