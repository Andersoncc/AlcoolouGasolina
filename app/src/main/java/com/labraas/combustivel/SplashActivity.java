package com.labraas.combustivel;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
      Handler handle = new Handler();
      handle.postDelayed(new Runnable() {
          @Override public void run() {
              mostrarMain();
          }
      }, 2000);
  }
  private void mostrarMain() {
            Intent intent = new Intent(SplashActivity.this, SeletorActivity.class);
            startActivity(intent);
            finish();
        }
    }