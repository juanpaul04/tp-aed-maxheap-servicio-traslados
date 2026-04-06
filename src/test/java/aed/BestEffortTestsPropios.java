package aed;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BestEffortTestsPropios {

    
    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 8;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 7, 0, 1000, 10),
                                            new Traslado(2, 6, 1, 1000, 20),
                                            new Traslado(3, 5, 2, 1000, 50),
                                            new Traslado(4, 4, 3, 1000, 9),
                                            new Traslado(5, 3, 4, 1000, 40),
                                            new Traslado(6, 2, 5, 1000, 41),
                                            new Traslado(7, 1, 6, 1000, 42),
                                            new Traslado(8, 0, 7, 1000, 48)

                                        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }


    @Test
    void nMayorAcantYtodosMismoGasto(){

    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

    sis.despacharMasAntiguos(100);


    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorPerdida());
    assertEquals(0, sis.ciudadConMayorSuperavit());


    }

    @Test
    void despachoTodoYAgregoNuevos(){

    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

    sis.despacharMasAntiguos(100);

     Traslado[]  Traslado2 = new Traslado[]{new Traslado(9,0,4,2000,87), 
                                            new Traslado(10,2,1,2000,75),
                                                                                                                        
    };

    sis.registrarTraslados(Traslado2);

 
    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorPerdida());
    assertEquals(0, sis.ciudadConMayorSuperavit());


    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);

    assertSetEquals(new ArrayList<>(Arrays.asList(0,2)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(4,1)), sis.ciudadesConMayorPerdida());
    assertEquals(0, sis.ciudadConMayorSuperavit());


    }

    @Test
    void despachoIntercaladoPasandome(){

    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);
    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);
    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);
    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);
    sis.despacharMasAntiguos(1);
    sis.despacharMasRedituables(1);

     
    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7)), sis.ciudadesConMayorPerdida());
    assertEquals(0, sis.ciudadConMayorSuperavit());

    }

    @Test
    void cambioDeSuperavit(){

    BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

     sis.despacharMasAntiguos(1);

    assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
    assertEquals(4, sis.ciudadConMayorSuperavit());

    Traslado[]  Traslado2 = new Traslado[]{
        new Traslado(9,0,4,2000,87), 
        new Traslado(10,2,1,100000,75),
                                                                                    
    };
    
    sis.registrarTraslados(Traslado2);

    sis.despacharMasRedituables(1);

    assertSetEquals(new ArrayList<>(Arrays.asList(2)), sis.ciudadesConMayorGanancia());
    assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());
    assertEquals(2, sis.ciudadConMayorSuperavit());

    }


    @Test
    void agregoDosVeces(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);


        Traslado[]  Traslado2 = new Traslado[]{new Traslado(9,1,2,2000,8100), 
            new Traslado(10,2,1,2000,751),};

        Traslado[]  Traslado3 = new Traslado[]{new Traslado(11,3,7,3000,870), 
                new Traslado(12,6,4,2000,750),};

        sis.despacharMasRedituables(2);

        assertSetEquals(new ArrayList<>(Arrays.asList(7,6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0,1)), sis.ciudadesConMayorPerdida());
        assertEquals(6, sis.ciudadConMayorSuperavit());

        sis.registrarTraslados(Traslado2);

        sis.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(7,6,4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0,1,3)), sis.ciudadesConMayorPerdida());
        assertEquals(4, sis.ciudadConMayorSuperavit());
    

        sis.registrarTraslados(Traslado3);

        sis.despacharMasRedituables(1);


        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(7)), sis.ciudadesConMayorPerdida());
        assertEquals(3, sis.ciudadConMayorSuperavit());
    }
    
   
}
