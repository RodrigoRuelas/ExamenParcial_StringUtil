package org.apache.commons.lang3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsManipulationTest {

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: capitalize()
    // ====================================================================

    @Test
    public void testCapitalize_NormalString() {
        String input = "gato";
        String expected = "Gato";
        String result = StringUtils.capitalize(input);
        
        assertEquals(expected, result, "El primer carácter debería estar en mayúscula");
        System.out.println("ÉXITO: El método capitalize() fue testeado correctamente. Entrada: '" + input + "' -> Resultado: '" + result + "'");
    }

    @Test
    public void testCapitalize_NullOrEmptyString() {
        assertNull(StringUtils.capitalize(null), "Debería devolver null si la entrada es null");
        assertEquals("", StringUtils.capitalize(""), "Debería devolver cadena vacía si la entrada es vacía");
        
        System.out.println("ÉXITO: El método capitalize() manejó correctamente valores null y vacíos.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: appendIfMissing()
    // ====================================================================

    @Test
    public void testAppendIfMissing_SuffixMissing() {
        String input = "archivo";
        String suffix = ".txt";
        String expected = "archivo.txt";
        String result = StringUtils.appendIfMissing(input, suffix);
        
        assertEquals(expected, result, "Debería añadir el sufijo porque falta");
        System.out.println("ÉXITO: El método appendIfMissing() fue testeado correctamente. Entrada: '" + input + "' -> Resultado: '" + result + "'");
    }

    @Test
    public void testAppendIfMissing_SuffixAlreadyPresent() {
        String input = "documento.pdf";
        String suffix = ".pdf";
        String expected = "documento.pdf";
        String result = StringUtils.appendIfMissing(input, suffix);
        
        assertEquals(expected, result, "No debería añadir el sufijo porque ya está presente");
        System.out.println("ÉXITO: El método appendIfMissing() detectó que el sufijo ya existía. Resultado: '" + result + "'");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: deleteWhitespace()
    // ====================================================================

    @Test
    public void testDeleteWhitespace_WithSpaces() {
        String input = "  h o l a   m u n d o  ";
        String expected = "holamundo";
        String result = StringUtils.deleteWhitespace(input);
        
        assertEquals(expected, result, "Debería eliminar todos los espacios en blanco");
        System.out.println("ÉXITO: El método deleteWhitespace() fue testeado correctamente. Entrada: '" + input + "' -> Resultado: '" + result + "'");
    }

    @Test
    public void testDeleteWhitespace_Null() {
        assertNull(StringUtils.deleteWhitespace(null), "Debería devolver null si la entrada es null");
        System.out.println("ÉXITO: El método deleteWhitespace() manejó correctamente un valor null.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: swapCase()
    // ====================================================================

    @Test
    public void testSwapCase_MixedCharacters() {
        String input = "HoLa MuNdO 123!";
        String expected = "hOlA mUnDo 123!";
        String result = StringUtils.swapCase(input);
        
        assertEquals(expected, result, "Debería invertir mayúsculas y minúsculas sin alterar números o símbolos");
        System.out.println("ÉXITO: El método swapCase() fue testeado correctamente. Entrada: '" + input + "' -> Resultado: '" + result + "'");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: truncate()
    // ====================================================================

    @Test
    public void testTruncate_NormalBehavior() {
        String input = "Esta es una cadena muy larga";
        int maxWidth = 10;
        String expected = "Esta es un"; // Primeros 10 caracteres
        String result = StringUtils.truncate(input, 0, maxWidth);
        
        assertEquals(expected, result, "Debería truncar la cadena a la longitud máxima especificada");
        System.out.println("ÉXITO: El método truncate() fue testeado correctamente. Entrada: '" + input + "' (ancho: " + maxWidth + ") -> Resultado: '" + result + "'");
    }

    @Test
    public void testTruncate_NegativeMaxWidthThrowsException() {
        String input = "Texto";
        
        // Verificamos que lance la excepción IllegalArgumentException si pasamos un ancho negativo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            StringUtils.truncate(input, 0, -5);
        });
        
        assertNotNull(exception);
        System.out.println("ÉXITO: El método truncate() lanzó correctamente una excepción al recibir un maxWidth negativo.");
    }

}