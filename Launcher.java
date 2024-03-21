import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        final int dias = 7;
        final int horasPorDia = 24;

        // Ingresar datos
        double[][] lecturasSensor = null;

        Scanner scanner = new Scanner(System.in);
        char opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Ingresar datos");
            System.out.println("2. Mostrar sismo de mayor magnitud");
            System.out.println("3. Contar sismos mayores o iguales a 5.0");
            System.out.println("4. Enviar SMS por cada sismo mayor o igual a 7.0");
            System.out.println("5. Salir (S/N)");

            opcion = scanner.next().charAt(0);

            switch (opcion) {
                case '1':
                    lecturasSensor = ingresarDatos(dias, horasPorDia);
                    // Mostrar los datos ingresados
                    mostrarSismos(lecturasSensor);
                    break;
                case '2':
                    if (lecturasSensor != null) {
                        double mayorSismo = buscarMayorSismo(lecturasSensor);
                        DecimalFormat formatoDecimal = new DecimalFormat("0.0");
                        System.out.println("El sismo de mayor intensidad registrado fue de: " + formatoDecimal.format(mayorSismo));
                    } else {
                        System.out.println("Primero debe ingresar los datos.");
                    }
                    break;
                case '3':
                    if (lecturasSensor != null) {
                        int sismosMayores5 = contarSismos(lecturasSensor);
                        System.out.println("Cantidad de sismos con magnitud mayor o igual a 5.0: " + sismosMayores5);
                    } else {
                        System.out.println("Primero debe ingresar los datos.");
                    }
                    break;
                case '4':
                    if (lecturasSensor != null) {
                        enviarSMS(lecturasSensor);
                    } else {
                        System.out.println("Primero debe ingresar los datos.");
                    }
                    break;
                case '5':
                    System.out.println("¿Desea salir del programa? (S/N)");
                    opcion = scanner.next().charAt(0);
                    if (opcion == 'S' || opcion == 's') {
                        System.out.println("adios");
                        System.exit(0); // Salir del programa
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 'S' && opcion != 's');
        scanner.close();
    }

    // Método para ingresar datos con valores aleatorios entre 0.0 y 9.9
    public static double[][] ingresarDatos(int dias, int horasPorDia) {
        double[][] sismos = new double[dias][horasPorDia];
        Random rand = new Random();
        for (int dia = 0; dia < dias; dia++) {
            for (int hora = 0; hora < horasPorDia; hora++) {
                sismos[dia][hora] = rand.nextDouble() * 9.9;
            }
        }
        System.out.println("Datos ingresados correctamente.");
        return sismos;
    }

    // Método para buscar el sismo de mayor intensidad
    public static double buscarMayorSismo(double[][] sismos) {
        double mayorSismo = Double.MIN_VALUE;
        for (int dia = 0; dia < sismos.length; dia++) {
            for (int hora = 0; hora < sismos[dia].length; hora++) {
                if (sismos[dia][hora] > mayorSismo) {
                    mayorSismo = sismos[dia][hora];
                }
            }
        }
        return mayorSismo;
    }

    // Método para contar sismos con magnitud mayor o igual a 5.0
    public static int contarSismos(double[][] sismos) {
        int contador = 0;
        for (int dia = 0; dia < sismos.length; dia++) {
            for (int hora = 0; hora < sismos[dia].length; hora++) {
                if (sismos[dia][hora] >= 5.0) {
                    contador++;
                }
            }
        }
        return contador;
    }

    // Método para mostrar solo los valores de los sismos con un decimal
    public static void mostrarSismos(double[][] sismos) {
        System.out.println("Valores de los sismos ingresados:");
        DecimalFormat formatoDecimal = new DecimalFormat("0.0");
        for (int dia = 0; dia < sismos.length; dia++) {
            for (int hora = 0; hora < sismos[dia].length; hora++) {
                System.out.println(formatoDecimal.format(sismos[dia][hora]));
            }
        }
    }

    // Método para enviar SMS por cada sismo mayor o igual a 7.0
    public static void enviarSMS(double[][] sismos) {
        System.out.println("Enviando SMS por cada sismo mayor o igual a 7.0:");
        for (int dia = 0; dia < sismos.length; dia++) {
            for (int hora = 0; hora < sismos[dia].length; hora++) {
                if (sismos[dia][hora] >= 7.0) {
                    System.out.println("SMS enviado para el día " + (dia + 1) + ", hora " + (hora + 1));
                }
            }
        }
    }
}
