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
    private boolean primerNum;
    private boolean esPrimerDigito;
    private boolean decimal;
    private TextView txtResultado;
    private Operaciones operacion;

    public MainActivity() {
        this.operacion = new Operaciones();
        this.primerNum = true;
        this.esPrimerDigito = true;
        this.decimal = false;
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
        this.esPrimerDigito = true;
        resetearDecimal();
    }

    public void onClickDecimal(View view) {
        if (this.decimal == false) {
            this.txtResultado.setText(this.txtResultado.getText().toString() + ".");
            this.decimal = true;
        }
    }

    public void onClickNumeros(View view) {
        Button btn = (Button) view;
        System.out.println(this.valor1 + "valor 1");
        System.out.println(this.valor2 + "valor 2");
        if (this.primerNum) {
            this.txtResultado.setText(btn.getText().toString());
            this.primerNum = false;
        } else {
            this.txtResultado.setText(this.txtResultado.getText().toString() + btn.getText().toString());
        }
    }

    public void onclicklimpiar(View view) {
        this.limpiar();
    }

    public void onClickOperadores(View view) {
        Button btn = (Button) view;
        if (this.esPrimerDigito) {
            this.operador = Integer.parseInt(btn.getTag().toString());
            this.valor1 = Double.parseDouble(this.txtResultado.getText().toString());
            this.esPrimerDigito = false;
            this.primerNum = true;
            this.resetearDecimal();
        } else {
            if (!this.primerNum) {
                this.valor2 = Double.parseDouble(this.txtResultado.getText().toString());
                this.operacion();
                this.operador = Integer.parseInt(btn.getTag().toString());
                this.primerNum = true;
                this.resetearDecimal();
            }
        }
        System.out.println(this.valor1 + "valor 1");
        System.out.println(this.valor2 + "valor 2");
    }

    public void onClickIgual(View view) {
        if (!this.primerNum) {
            this.valor2 = Double.parseDouble(this.txtResultado.getText().toString());
            this.operacion();
        }
    }

    private void operacion() {
        try {
            this.resultado = this.operacion.calcular(this.operador, this.valor1, this.valor2);
            this.txtResultado.setText(String.valueOf(this.resultado));
            this.valor1 = this.resultado;
        } catch (ArithmeticException e) {
            this.txtResultado.setText("Syntax error");
        } catch (Exception e) {
            this.txtResultado.setText("Se pudrió todo");
        }
        this.esPrimerDigito = false;
        this.primerNum = true;
        this.valor2 = 0;
    }

    public void onClickMasMenos(View view) {
        if (txtResultado.getText().toString().contains("-")) {
            this.txtResultado.setText(this.txtResultado.getText().toString().replace("-", ""));
        } else {
            this.txtResultado.setText("-" + this.txtResultado.getText().toString());
        }
    }

    private void resetearDecimal() {
        this.decimal = false;
    }
}