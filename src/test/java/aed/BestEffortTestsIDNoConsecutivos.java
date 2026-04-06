package aed;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BestEffortTestsIDNoConsecutivos {

    
    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 8;
        listaTraslados = new Traslado[] {
                                            new Traslado(17, 7, 0, 1000, 10),
                                            new Traslado(23, 6, 1, 1000, 20),
                                            new Traslado(12, 5, 2, 1000, 50),
                                            new Traslado(44, 4, 3, 1000, 9),
                                            new Traslado(52, 3, 4, 1000, 40),
                                            new Traslado(161, 2, 5, 1000, 41),
                                            new Traslado(7, 1, 6, 1000, 42),
                                            new Traslado(84, 0, 7, 1000, 48)

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

     Traslado[]  Traslado2 = new Traslado[]{new Traslado(623,0,4,2000,87), 
                                            new Traslado(10000,2,1,2000,75),
                                                                                                                        
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


        Traslado[]  Traslado2 = new Traslado[]{new Traslado(2,1,2,2000,8100), 
            new Traslado(102,2,1,2000,751),};

        Traslado[]  Traslado3 = new Traslado[]{new Traslado(8,3,7,5000,870), 
                new Traslado(5,6,5,5000,750),};

        sis.despacharMasRedituables(2);

        assertSetEquals(new ArrayList<>(Arrays.asList(1,5)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2,6)), sis.ciudadesConMayorPerdida());
        assertEquals(1, sis.ciudadConMayorSuperavit());

        sis.registrarTraslados(Traslado2);

        sis.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(4,1,5)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(2,6,3)), sis.ciudadesConMayorPerdida());
        assertEquals(1, sis.ciudadConMayorSuperavit());
    

        sis.registrarTraslados(Traslado3);

        sis.despacharMasRedituables(1);


        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(5)), sis.ciudadesConMayorPerdida());
        assertEquals(6, sis.ciudadConMayorSuperavit());
    }
    


    
    @Test
    void despachar_con_mas_ganancia_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(1);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(1);
        sis.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 5,7)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 6,2)), sis.ciudadesConMayorPerdida());
    }
    
    @Test
    void despachar_con_mas_ganancia_de_a_varios(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 5,7)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 6,2)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 3,4,5,6,7)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(6,4,3,2,1,0)), sis.ciudadesConMayorPerdida());

    }
    
    @Test
    void despachar_mas_viejo_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
        
        sis.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(4,7)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0,3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(6,4,7)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1,0,3)), sis.ciudadesConMayorPerdida());
    }
    
    
    @Test
    void despachar_mixtos(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);
        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 5,6,4,7,3)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(6,2,1,3,0,4)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 5,6,4,7,3,2)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(6,2,1,3,0,4,5)), sis.ciudadesConMayorPerdida());
        
    }
    

}
   

