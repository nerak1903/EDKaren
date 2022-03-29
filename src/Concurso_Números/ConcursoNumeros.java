/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concurso_Números;

import java.util.Arrays;
import java.util.Scanner;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Programa que permita jugar a un juego de preguntas y respuestas inspirado en
 * el conocido concurso de televisión Saber y ganar.
 *
 * @author madrid
 *
 */
public class ConcursoNumeros {

    static int jugadores, rondas;
    static String nombres[], opcion = "";
    static Scanner entrada = new Scanner(System.in);

    /**
     * Clase principal, donde está la estructura principal del programa. Se va
     * llamando a las distintas funciones del juego.
     *
     * @param args: argumentos de la clase principal (código principal)
     */
    public static void main(String[] args) {

        iniciarJuego(); // Función para iniciar el juego, número de jugadores, nombres y rondas

        int numRondas = numerarRondas(opcion); // Guardar el número de rondas que se seleccionen

        int puntuaciones[][] = new int[jugadores][2]; // Crear matriz con dos columnas, jugadores y puntuaciones

        // Bucle para numerar a los jugadores
        for (int i = 0; i < jugadores; i++)
        {
            puntuaciones[i][0] = (i + 1);
        }

        for (int i = 0; i < numRondas; i++) // Iniciar bucle con i=0 para pasar las rondas
        {
            System.out.println("Comienza la ronda " + (i + 1) + ".");

            for (int j = 0; j < jugadores; j++) // Iniciar bucle con j=0 para el número de turnos (que lo marca el número de jugadores)
            {
                System.out.println("Turno de " + nombres[j]);
                String pregunta = generarPreguntaAleatoria();

                System.out.println("Resuelve la siguiente expresión matemática: " + pregunta + "= ");

                System.out.println(evaluarExpresion(pregunta)); //Mostrar el resultado de la expresión matemática para corregir

                int respuesta = entrada.nextInt();

                // Función para comprobar resultado
                if (comprobarRersultado(respuesta, pregunta))
                {
                    System.out.println("¡Correcto!, has ganado un punto.");
                    puntuaciones[j][1] += 1; // Si es correcta, la puntuación se incrementa una unidad
                } else
                {
                    // Si no es correcta, se muestra el resultado
                    System.out.println("Error, la respuesta correcta era: " + evaluarExpresion(pregunta));
                }
            }
            System.out.println();
            mostrarPuntuaciones(puntuaciones); // Función mostrarPuntuaciones
            System.out.println(mostrarGanador(puntuaciones) + " ¡Está en racha!");
            System.out.println();
        }
        // Función para mostrar el ganador del concurso
        System.out.println("El ganador del concurso es: " + mostrarGanador(puntuaciones));
    }

    /**
     * Función para iniciar el juego. Se solicitará al usuario el número de
     * jugadores (se comprobará que sea menor o igual a 6) y sus nombres (que se
     * guardarán en un array), además se pedirá que seleccione el número de
     * rondas que también se comprobarán. Desde este método también se llama a
     * otras funciones.
     *
     */
    public static void iniciarJuego() {
        System.out.println("¡Bienvenid@ al concurso de números!"); // Dar la bienvenida al juego
        System.out.println("(Presiona Intro para continuar)");
        entrada.nextLine();

        // Solicitar y leer número de jugadores
        System.out.println("Antes de comenzar el concurso, indica el número de jugadores que participarán (entre 1 y 6 jugadores): ");
        jugadores = entrada.nextInt();

        // Mientras el número de jugadores no sea válido, continúa el bucle
        while (!comprobarNumeroJugadores(jugadores))
        {
            System.out.println("ERROR: El número de jugadores no es válido. El núemro de jugadores debe ser entre 1 y 6.");
            System.out.println("Introduce de nuevo el número de jugadores  (entre 1 y 6 jugadores): ");

            jugadores = entrada.nextInt(); // Leer entrada de jugadores

            comprobarNumeroJugadores(jugadores); // Función que comprueba los jugadores
        }
        nombres = new String[jugadores];

        // Bucle para guardar los nombres de los jugadores
        for (int i = 0; i < nombres.length; i++)
        {
            System.out.println("Introduce el nombre del jugador " + (i + 1) + ": ");
            nombres[i] = entrada.next();
        }
        ordenarNombres(nombres); // Función para ordenar jugadores aleatoriamente

        System.out.println("Los turnos serán los siguientes: ");
        // Bucle para mostrar el orden de los jugadores
        for (int i = 0; i < nombres.length; i++)
        {
            System.out.println("El jugador " + (i + 1) + " será: " + nombres[i]);
        }

        // Solicitar y leer rondas
        System.out.println(mostrarOpcionesRondas()); // Función para mostrar opciones de rondas

        opcion = entrada.next(); // Leer número de rondas

        // Mientras la opción seleccionada no sea correcta, continúa el bucle
        while (!comprobarSeleccionRondas(opcion))
        {
            System.out.println("ERROR: La selección de rondas indicada no es correcta.");

            System.out.println(mostrarOpcionesRondas()); // Función para mostrar opciones de rondas

            opcion = entrada.next(); // Leer número de rondas

            comprobarSeleccionRondas(opcion); // Se vuelve a comprobar que la opción sea correcta
        }

        System.out.println("Has escogido " + numerarRondas(opcion) + " rondas."); // Mostrar la selección al usuario

    }

    /**
     * Función que comprueba si el núero de jugadores está entre 1 y 6 y
     * devuelve true si está entre 1 y 6 y false si no.
     *
     * @param N: número a comprobar
     * @return Devuelve true o false
     *
     */
    public static boolean comprobarNumeroJugadores(int N) {
        boolean jugadores = true;
        if (N >= 1 && N <= 6)
        {
            jugadores = true;
        } else
        {
            jugadores = false;
        }
        return jugadores;
    }

    /**
     * Función que ordena un array aleatoriamente y devuelve el array
     * reordenado. Para ordenar el turno de los jugadores aleatoriamente.
     *
     * @param nombres[]: array que hay que reordenar aleatoriamente.
     * 
     */
    public static void ordenarNombres(String nombres[]) {
        int S;
        String aux = "";
        for (int i = 0; i < nombres.length; i++)
        {
            S = (int) (Math.random() * (nombres.length - 1));
            aux = nombres[i];
            nombres[i] = nombres[S];
            nombres[S] = aux;
        }
    }

    /**
     * Función que muestra por pantalla las opciones que hay para seleccionar el
     * número de rondas.
     *
     * @return Devuelve un String
     */
    public static String mostrarOpcionesRondas() {

        return "Ahora indica el número de rondas que se jugarán, puedes elegir entre las siguientes opciones:\n"
                + "a) Partida rápida (3 rondas)\n" + "b) Partida corta (5 rondas)\n"
                + "c) Partida normal (10 rondas)\n" + "d) Partida larga (20 rondas)";
    }

    /**
     * Función para comprobar la selección de las rondas mediante la lectura de
     * un strig y devuelve true o false.
     *
     * @param opcion El string que lee del usuario y que compara con "a", "b",
     * "c" y "d".
     * @return Devuelve true si la opción es válida y false si no lo es.
     */
    public static boolean comprobarSeleccionRondas(String opcion) {
        boolean rondas = true;
        if ((opcion.equals("a")) || (opcion.equals("b")) || (opcion.equals("c")) || (opcion.equals("d")))
        {
            rondas = true;
        } else
        {
            rondas = false;
        }
        return rondas;
    }

    /**
     * Función para comprobar el número de rondas que quiere el usuario. Según
     * escoja "a", "b", "c" o "d", se devolverá una cantidad u otra.
     *
     * @param rondas Se lee la opción que ha seleccionado el usuario, "a", "b",
     * "c" o "d".
     * @return Devuelve un número que será el número de rondas del juego.
     */
    public static int numerarRondas(String rondas) {
        int numRondas = 0;
        switch (rondas)
        {
            case "a":
                numRondas = 3;
                break;
            case "b":
                numRondas = 5;
                break;
            case "c":
                numRondas = 10;
                break;
            case "d":
                numRondas = 20;
                break;
        }
        return numRondas;
    }

    /**
     * Función para generar un signo aleatorio que será una operación para la
     * expresión matemática.
     *
     * @return Devuelve un String que será el signo.
     */
    public static String generarSignoAleatorio() {
        String signo;
        int A = (int) (1 + Math.random() * (3 - 1 + 1));
        if (A == 1)
        {
            signo = "+";
        } else if (A == 2)
        {
            signo = "-";
        } else
        {
            signo = "*";
        }
        return signo;
    }

    /**
     * Función para generar una cadena (expresión matemática) aleatoria. Crea un
     * array para guardar números aleatorios y luego en un string se guardarán
     * esos números más signos que serán las operaciones llamando a la función
     * generarSignoAleatorio.
     *
     * @return Devuelve un string que será una cadena con la pregunta que se le
     * hará al usuario.
     */
    public static String generarPreguntaAleatoria() {
        // Devuelve una cadena de caracteres aleatoria de (4-8) numeros con valor de (2-12).
        int N = (int) (4 + Math.random() * (8 - 4 + 1));
        int array[] = new int[N];
        String cadena = "";
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) (2 + Math.random() * (12 - 2 + 1));
            if (i < (array.length - 1))
            {
                cadena += array[i] + " " + generarSignoAleatorio() + " ";
            } else
            {
                cadena += array[i] + " ";
            }
        }
        return cadena;
    }

    /**
     * Función para evaluar la expresión matemática generada aleatoriamente.
     *
     * @param expresion Lee un string (la expresión matemática) y la evalúa.
     * @return Devuelve el resultado de la expresiń matemática que lee.
     */
    public static int evaluarExpresion(String expresion) {
        int valor = 0;
        try
        {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("javascript");
            Object result = engine.eval(expresion);
            valor = Integer.decode(result.toString());

        } catch (Exception e)
        {
            e.getMessage();
        }
        return valor;
    }

    /**
     * Función para comprobar si la respuesta del usuario es correcta o no.
     *
     * @param respuesta Lee la respuesta del usuario y la compara con el
     * resultado de la pregunta.
     * @param pregunta Lee la pregunta que se le ha hecho al usuario y la
     * resuelve con la función evaluarResultado para comprobarla con la
     * respuesta
     * @return Devuelve true si la respuesta es correcta o false si no o es.
     */
    public static boolean comprobarRersultado(int respuesta, String pregunta) {
        boolean correcto = false;
        if (evaluarExpresion(pregunta) == respuesta)
        {
            correcto = true;
        }
        return correcto;
    }

    /**
     * Función para mostrar las puntuaciones de cada jugador.
     *
     * @param puntuaciones Lee la matriz de las puntuaciones y muestra por
     * pantalla cada una.
     */
    public static void mostrarPuntuaciones(int puntuaciones[][]) {
        System.out.println("Las puntuaciones son las siguientes: ");
        for (int i = 0; i < puntuaciones.length; i++)
        {
            System.out.println(nombres[i] + " tiene una puntuación de: " + puntuaciones[i][1]);
        }
        System.out.println();
    }

    /**
     * Función para mostrar al ganador, lee las puntuaciones y muestra el
     * jugador que tiene la mayor puntuación.
     *
     * @param puntuaciones Lee la matriz de puntuaciones
     * @return Devuelve el string del nombre del jugador que tiene mayor
     * puntuación
     */
    public static String mostrarGanador(int puntuaciones[][]) {
        int ganador = 0, puntuacionMax = 0;
        for (int i = 0; i < puntuaciones.length; i++)
        {
            if (i == 0)
            {
                puntuacionMax = puntuaciones[i][1];
                ganador = puntuaciones[i][0];
            } else if (puntuaciones[i][1] > puntuacionMax)
            {
                puntuacionMax = puntuaciones[i][1];
                ganador = puntuaciones[i][0];
            }
        }
        return nombres[ganador - 1];
    }
}
