package com.fernando.semana6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fernando.modelos.Operaciones;

public class MainActivity extends AppCompatActivity {
    private double valor1;
    private double valor2;
    private double resultado;
    private int operador;
    private boolean primerNum = false;
    private TextView txtResultado;
    private Operaciones operacion;

    public MainActivity() {
        // this.primerNum = false;
        this.operacion = new Operaciones();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtResultado = findViewById(R.id.txt_resultado);
        this.limpiar();
    }

    public void limpiar() {
        this.valor1 = 0;
        this.valor2 = 0;
        this.resultado = 0;
        this.operador = 0;
        this.primerNum = true;
        this.txtResultado.setText("0");
    }

    public void onClickNumeros(View view) {
        Button btn = (Button) view;
        if (this.primerNum) {
            this.txtResultado.setText(btn.getText().toString());
            this.primerNum = false;
        } else {
            this.txtResultado.setText(this.txtResultado.getText().toString() + btn.getText().toString()); ;
        }
    }
    public void onclicklimpiar(View view) {
        this.limpiar();
    }
    public void onclickOperadores(View view) {
        Button btn = (Button) view;

    }
}