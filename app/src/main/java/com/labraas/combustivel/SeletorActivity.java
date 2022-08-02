package com.labraas.combustivel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.textfield.TextInputEditText;

public class SeletorActivity extends AppCompatActivity {

    private AdView adview;
    private InterstitialAd mInterstitialAd;
    private TextInputEditText editPrecoAlcool, editPrecoGasolina;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seletor);






    }
    public void fixo(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void media(View view){

        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }



    private void fecharTeclado(){
        View view = this.getCurrentFocus();
        if(view != null){
            InputMethodManager fech = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            fech.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }


}