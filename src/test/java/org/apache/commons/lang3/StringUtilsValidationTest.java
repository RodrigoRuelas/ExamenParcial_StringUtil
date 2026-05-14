package org.apache.commons.lang3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StringUtilsValidationTest {

    @Mock
    CharSequence mockSeq; // Creamos un mock de la interfaz CharSequence

    // ====================================================================
    // CONFIGURACIÓN INICIAL
    // ====================================================================
    @BeforeEach
    public void setUp() {
        // Esto le dice a Mockito que cree un mock fresco y nuevo 
        // ANTES de ejecutar cada uno de los @Test. ¡Soluciona el error null!
        mockSeq = mock(CharSequence.class);
    }
    
    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: isBlank()
    // ====================================================================
    @Test
    public void testIsBlank_WithMock() {
        // Configuramos el mock para que parezca una cadena con espacios
        when(mockSeq.length()).thenReturn(3);
        when(mockSeq.charAt(0)).thenReturn(' ');
        when(mockSeq.charAt(1)).thenReturn(' ');
        when(mockSeq.charAt(2)).thenReturn(' ');

        assertTrue(StringUtils.isBlank(mockSeq));
        
        // Verificamos que StringUtils realmente leyó los caracteres
        verify(mockSeq, atLeastOnce()).charAt(anyInt());
        System.out.println("ÉXITO: El método isBlank() fue testeado con Mockito correctamente.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: isEmpty()
    // ====================================================================
    @Test
    public void testIsEmpty_WithMock() {
        // Escenario 1: Simulamos una secuencia vacía (longitud 0)
        when(mockSeq.length()).thenReturn(0);
        assertTrue(StringUtils.isEmpty(mockSeq), "Debería devolver true si la longitud es 0");

        // Escenario 2: Simulamos una secuencia con contenido (longitud > 0)
        when(mockSeq.length()).thenReturn(5);
        assertFalse(StringUtils.isEmpty(mockSeq), "Debería devolver false si la longitud es mayor a 0");

        // Verificamos que StringUtils consultó el tamaño (length) exactamente 2 veces
        verify(mockSeq, times(2)).length();
        
        System.out.println("ÉXITO: El método isEmpty() fue testeado con Mockito simulando diferentes longitudes.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: contains()
    // ====================================================================
    @Test
    public void testContains_WithMock() {
        when(mockSeq.toString()).thenReturn("pizarrón");
        
        assertTrue(StringUtils.contains(mockSeq, "piza"));
        
        System.out.println("ÉXITO: El método contains() validó correctamente la secuencia simulada.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: indexOf()
    // ====================================================================
    @Test
    public void testIndexOf_BehaviorOnException() {
        // Simulamos un comportamiento anómalo: el mock lanza error al intentar leer
        when(mockSeq.length()).thenReturn(5);
        when(mockSeq.charAt(anyInt())).thenThrow(new IndexOutOfBoundsException("Error simulado"));

        assertThrows(IndexOutOfBoundsException.class, () -> {
            StringUtils.indexOf(mockSeq, 'a');
        });

        System.out.println("ÉXITO: El método indexOf() reaccionó correctamente a un fallo del CharSequence.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: containsAny()
    // ====================================================================
    @Test
    public void testContainsAny_Interaction() {
        when(mockSeq.length()).thenReturn(2);
        when(mockSeq.charAt(0)).thenReturn('a');
        // El método debería detenerse en 'a' y no llegar al segundo carácter
        
        boolean result = StringUtils.containsAny(mockSeq, 'a', 'b');
        
        assertTrue(result);
        verify(mockSeq, times(1)).charAt(0);
        System.out.println("ÉXITO: El método containsAny() es eficiente y se detuvo en la primera coincidencia.");
    }

    // ====================================================================
    // ÁREA DE PRUEBAS PARA EL MÉTODO: containsNone()
    // ====================================================================
    @Test
    public void testContainsNone_FullScan() {
        when(mockSeq.length()).thenReturn(3);
        when(mockSeq.charAt(0)).thenReturn('x');
        when(mockSeq.charAt(1)).thenReturn('y');
        when(mockSeq.charAt(2)).thenReturn('z');

        boolean result = StringUtils.containsNone(mockSeq, 'a', 'b');

        assertTrue(result);
        // Verificamos que revisó toda la cadena (los 3 caracteres)
        verify(mockSeq, times(3)).charAt(anyInt());
        System.out.println("ÉXITO: El método containsNone() verificó correctamente todos los caracteres.");
    }
}