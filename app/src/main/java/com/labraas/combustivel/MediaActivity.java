package com.labraas.combustivel;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class MediaActivity extends AppCompatActivity {

    private AdView adview;
    private InterstitialAd mInterstitialAd;
    private TextInputEditText editAutonomia, editPrecoLitro,editValorAbastecido;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_por_media);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-9584107572536485/2112277072");
        AdRequest adRequesInter = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequesInter);

        MobileAds.initialize(this, "ca-app-pub-9584107572536485~3440002314");
        adview = (AdView) findViewById(R.id.adViewBan);
        AdRequest adRequest =  new AdRequest.Builder().build();
        adview.loadAd(adRequest);

        editAutonomia = findViewById(R.id.editAutonomia);
        editPrecoLitro = findViewById(R.id.editPrecoLitro);
        editValorAbastecido = findViewById(R.id.editValorAbastecido);
        textResultado = findViewById(R.id.textResultado);
        SimpleMaskFormatter smfi = new SimpleMaskFormatter("NNN.NN");
        SimpleMaskFormatter smfv = new SimpleMaskFormatter("N.NN");
        MaskTextWatcher mtwA = new MaskTextWatcher(editAutonomia, smfi);
        MaskTextWatcher mtwP = new MaskTextWatcher(editPrecoLitro, smfv);
        MaskTextWatcher mtwV = new MaskTextWatcher(editValorAbastecido, smfi);
        editAutonomia.addTextChangedListener(mtwA);
        editPrecoLitro.addTextChangedListener(mtwP);
        editValorAbastecido.addTextChangedListener(mtwV);


    }

    public void voltar(View view){

        Intent intent = new Intent(this, SeletorActivity.class);
        startActivity(intent);
    }

    public void calcularPreco( View view){

        String autonomia = editAutonomia.getText().toString();
        String preco = editPrecoLitro.getText().toString();
        String abastecido = editValorAbastecido.getText().toString();
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        boolean camposValidados = validarCampos(autonomia, preco,abastecido);
        if(camposValidados)
        {

            Double valorAutonomia = Double.parseDouble(autonomia);
            Double valorPreco = Double.parseDouble(preco);
            Double valorAbastecido = Double.parseDouble(abastecido);

            Double valorResultado = (valorAbastecido / valorPreco * valorAutonomia);
            DecimalFormat df = new DecimalFormat("0.00");
            String valorFormatado = (df.format(valorResultado));


            textResultado.setTextColor(Color.parseColor("#ff0000"));
            textResultado.setText("Você percorrerá "+valorFormatado+" Km") ;

        }else{
            textResultado.setText("preencha todos os campos!");
        }

        fecharTeclado();


    }
    public boolean validarCampos (String vAutonomia, String vPreco, String vAbastecido){
         boolean camposValidados = true;

         if(vAutonomia == null || vAutonomia.equals("")){
            camposValidados = false;

         } else if (vPreco == null || vPreco.equals("")){
             camposValidados = false;
         } else if (vAbastecido == null || vAbastecido.equals("")){
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