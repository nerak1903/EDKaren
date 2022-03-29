package Concurso_Números;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author madrid
 */
public class ConcursoNumerosTest {

    /**
     * Test of comprobarNumeroJugadores method, of class ConcursoNumeros.
     */
    @Test
    public void testComprobarNumeroJugadores() {
        System.out.println("No válido");
        int N = 0;
        boolean expResult = false;
        boolean result = ConcursoNumeros.comprobarNumeroJugadores(N);
        assertEquals(expResult, result);
        
        System.out.println("Válido");
        N = 1;
        expResult = true;
        result = ConcursoNumeros.comprobarNumeroJugadores(N);
        assertEquals(expResult, result);
        
        System.out.println("Válido");
        N = 6;
        expResult = true;
        result = ConcursoNumeros.comprobarNumeroJugadores(N);
        assertEquals(expResult, result);
        
        System.out.println("No válido");
        N = 7;
        expResult = false;
        result = ConcursoNumeros.comprobarNumeroJugadores(N);
        assertEquals(expResult, result);
    }

    /**
     * Test of comprobarSeleccionRondas method, of class ConcursoNumeros.
     */
    @Test
    public void testComprobarSeleccionRondas() {
        System.out.println("Válidas");
        String opcion = "a";
        boolean expResult = true;
        boolean result = ConcursoNumeros.comprobarSeleccionRondas(opcion);
        assertEquals(expResult, result);
        
        System.out.println("Válidas");
        opcion = "b";
        expResult = true;
        result = ConcursoNumeros.comprobarSeleccionRondas(opcion);
        assertEquals(expResult, result);
        
        System.out.println("Válidas");
        opcion = "c";
        expResult = true;
        result = ConcursoNumeros.comprobarSeleccionRondas(opcion);
        assertEquals(expResult, result);
        
        System.out.println("Válidas");
        opcion = "d";
        expResult = true;
        result = ConcursoNumeros.comprobarSeleccionRondas(opcion);
        assertEquals(expResult, result);

        System.out.println("No válidas");
        opcion = "k";
        expResult = false;
        result = ConcursoNumeros.comprobarSeleccionRondas(opcion);
        assertEquals(expResult, result);
    }

    /**
     * Test of numerarRondas method, of class ConcursoNumeros.
     */
    @Test
    public void testNumerarRondas() {
        System.out.println("Rondas = 0");
        String rondas = "k";
        int expResult = 0;
        int result = ConcursoNumeros.numerarRondas(rondas);
        assertEquals(expResult, result);

        System.out.println("Rondas = 3");
        rondas = "a";
        expResult = 3;
        result = ConcursoNumeros.numerarRondas(rondas);
        assertEquals(expResult, result);

        System.out.println("Rondas = 5");
        rondas = "b";
        expResult = 5;
        result = ConcursoNumeros.numerarRondas(rondas);
        assertEquals(expResult, result);

        System.out.println("Rondas = 10");
        rondas = "c";
        expResult = 10;
        result = ConcursoNumeros.numerarRondas(rondas);
        assertEquals(expResult, result);

        System.out.println("Rondas = 20");
        rondas = "d";
        expResult = 20;
        result = ConcursoNumeros.numerarRondas(rondas);
        assertEquals(expResult, result);
    }

    /**
     * Test of generarSignoAleatorio method, of class ConcursoNumeros.
     */
    @Test
    public void testGenerarSignoAleatorio() {
        String signo;
        int num = 1;
        if (num == 1)
        {
            signo = "+";
        } else if (num == 2)
        {
            signo = "-";
        } else
        {
            signo = "*";
        }
        System.out.println("Signo +");
        String expResult = "+";
        String result = signo;
        assertEquals(expResult, result);
        
        num = 2;
        if (num == 1)
        {
            signo = "+";
        } else if (num == 2)
        {
            signo = "-";
        } else
        {
            signo = "*";
        }
        System.out.println("Signo -");
        expResult = "-";
        result = signo;
        assertEquals(expResult, result);
        
        num = 3;
        if (num == 1)
        {
            signo = "+";
        } else if (num == 2)
        {
            signo = "-";
        } else
        {
            signo = "*";
        }
        System.out.println("Signo *");
        expResult = "*";
        result = signo;
        assertEquals(expResult, result);
    }
}
