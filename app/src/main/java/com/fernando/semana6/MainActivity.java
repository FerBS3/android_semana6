package com.fernando.semana6;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fernando.db.Dbhelper;
import com.fernando.modelos.Operaciones;

public class MainActivity extends AppCompatActivity {
    private double valor1 = 0;
    private double valor2 = 0;
    private double resultado = 0;
    private int operador = 0;
    private boolean primerNum = true;
    private boolean esPrimerDigito = true;
    private TextView txtResultado;
    private Operaciones operacion;
    private Dbhelper dbhelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtResultado = findViewById(R.id.txt_resultado);
        operacion = new Operaciones();
        limpiar();
        this.db = dbhelper.getWritableDatabase();

        if (db != null) {
            Toast.makeText(this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al crear la base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    public MainActivity() {
        this.operacion = new Operaciones();
        this.dbhelper = new Dbhelper(MainActivity.this);
    }

    public void limpiar() {
        valor1 = 0;
        valor2 = 0;
        resultado = 0;
        operador = 0;
        primerNum = true;
        txtResultado.setText("0");
        esPrimerDigito = true;
    }

    public void onClickDecimal(View view) {
        String currentText = txtResultado.getText().toString();
        if (!currentText.contains(".")) {
            txtResultado.setText(currentText + ".");
        }
    }

    public void onClickNumeros(View view) {
        Button btn = (Button) view;
        if (primerNum) {
            txtResultado.setText(btn.getText().toString());
            primerNum = false;
        } else {
            txtResultado.append(btn.getText().toString());
        }
    }

    public void onClickLimpiar(View view) {
        limpiar();
    }

    public void onClickOperadores(View view) {
        Button btn = (Button) view;
        if (esPrimerDigito) {
            operador = Integer.parseInt(btn.getTag().toString());
            valor1 = Double.parseDouble(txtResultado.getText().toString());
            esPrimerDigito = false;
            primerNum = true;
        } else if (!primerNum) {
            valor2 = Double.parseDouble(txtResultado.getText().toString());
            operacion();
            operador = Integer.parseInt(btn.getTag().toString());
            primerNum = true;
        }
    }

    public void onClickIgual(View view) {
        if (!primerNum) {
            valor2 = Double.parseDouble(txtResultado.getText().toString());
            operacion();
            esPrimerDigito = true;
            db.execSQL("INSERT INTO tbl_calculos (numero1, numero2, operacion, resultado) VALUES (" + valor1 + ", " + valor2 + ", " + operador + ", " + resultado + ")");
            operador = 0;
        }
    }

    private void operacion() {
        try {
            if (operador != 0) {
                if (primerNum) {
                    valor2 = resultado;
                } else {
                    valor2 = Double.parseDouble(txtResultado.getText().toString());
                }

                resultado = operacion.calcular(operador, valor1, valor2);
                txtResultado.setText(String.valueOf(resultado));
                valor1 = resultado;
            }
        } catch (ArithmeticException e) {
            txtResultado.setText("Error de sintaxis");
        } catch (Exception e) {
            txtResultado.setText("Error desconocido");
        }
        esPrimerDigito = false;
        primerNum = true;
        operador = 0;
    }

    public void onClickMasMenos(View view) {
        String currentText = txtResultado.getText().toString();
        if (currentText.startsWith("-")) {
            txtResultado.setText(currentText.substring(1));
        } else {
            txtResultado.setText("-" + currentText);
        }
    }
}
