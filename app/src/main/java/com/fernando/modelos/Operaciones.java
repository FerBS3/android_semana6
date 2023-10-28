package com.fernando.modelos;

public class Operaciones {
    public double calcular(int operador, double dato1, double dato2) {
        double resultado = 0;
        switch (operador) {
            case 1:
                resultado = sumar(dato1, dato2);
                break;
            case 2:
                resultado = restar(dato1, dato2);
                break;
            case 3:
                resultado = multiplicar(dato1, dato2);
                break;
            case 4:
                resultado = dividir(dato1, dato2);
                break;
            case 5:
                resultado = porcenaje(dato1, dato2);
                break;
        }
        return resultado;
    }

    private double sumar(double sumando1, double sumando2) {
        return sumando1 + sumando2;
    }

    private double restar(double minuendo, double sustraendo) {
        return minuendo - sustraendo;
    }

    private double multiplicar(double multiplicando, double multiplicador) {
        return multiplicando * multiplicador;
    }

    private double porcenaje(double porcentaje, double numero) {
        return numero * porcentaje / 100;
    }

    private double dividir(double dividendo, double divisor) throws ArithmeticException {
        if (divisor == 0) {
            throw new ArithmeticException("El divisor no puede ser 0");
        }
        return dividendo / divisor;
    }

}
