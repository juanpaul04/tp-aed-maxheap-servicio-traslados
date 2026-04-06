package aed;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class MaxHeapTests {

    @Test
    void heapVacio(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);

        assertEquals(true, list.vacio());




    }

    @Test
    void insertarElementosOrdenados(){
        MaxHeapArray<Integer> list;
        ArrayList aux;

        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);

        aux = new ArrayList();

        ArrayList listMax;
        listMax = new ArrayList();


        list.apilar(50);
        list.apilar(25);
        list.apilar(12);
        list.apilar(11);
        list.apilar(5);
        list.apilar(1);

        listMax.add(0);


        aux.add(50);
        aux.add(25);
        aux.add(12);
        aux.add(11);
        aux.add(5);
        aux.add(1);


        assertEquals(aux,list.dameLista());

    }
    @Test
    void insertarElementosDesordenados(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);

       

        aux.add(60);
        aux.add(25);
        aux.add(7);
        aux.add(2);
        aux.add(20);
        aux.add(4);


        assertEquals(aux,list.dameLista());

        ArrayList listMax;
        listMax = new ArrayList();
        listMax.add(4);

    }

    @Test
    void insertarElementosRepetidos(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(7);
        list.apilar(7);
        list.apilar(4);




        aux.add(25);
        aux.add(7);
        aux.add(7);
        aux.add(2);
        aux.add(7);
        aux.add(4);


        assertEquals(aux,list.dameLista());



    }
    @Test
    void eliminarMaximo0(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);


        aux.add(25);
        aux.add(2);
        aux.add(7);

        
        assertEquals(aux,list.dameLista());

     

        list.sacarMaximo();
        
        aux.clear();
        aux.add(7);
        aux.add(2);


        assertEquals(aux,list.dameLista());
        


    }


    @Test
    void eliminarMaximo1(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);

        list.sacarMaximo();


        aux.add(25);
        aux.add(20);
        aux.add(7);
        aux.add(2);
        aux.add(4);


        assertEquals(aux,list.dameLista());

        assertEquals(25,list.dameMaximo());

    }
    
    @Test
    void eliminarMaximoRepetido(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(60);
        list.apilar(4);

        list.sacarMaximo();

        aux.add(60);
        aux.add(25);
        aux.add(7);
        aux.add(2);
        aux.add(20);
        aux.add(4);


        assertEquals(aux,list.dameLista());

        assertEquals(60,list.dameMaximo());

    }
    



    @Test
    void modificarNumeroMayor(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);

        aux.add(60);
        aux.add(25);
        aux.add(7);
        aux.add(2);
        aux.add(20);
        aux.add(4);

        assertEquals(aux,list.dameLista());

        ArrayList listMax;
        listMax = new ArrayList();
        listMax.add(4);

        list.modificar(70, 0);

        aux.clear();
        aux.add(70);
        aux.add(60);
        aux.add(7);
        aux.add(25);
        aux.add(20);
        aux.add(4);



   
        assertEquals(aux,list.dameLista());



    }

    @Test
    void modificarNumeroMayor2(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);
        list.apilar(5);


        assertEquals(1,list.dameCantMaximos());

        list.modificar(60, 5);
        assertEquals(2,list.dameCantMaximos());

        list.modificar(60, 1);
        assertEquals(3,list.dameCantMaximos());


       

        list.modificar(65, 4);

        
        assertEquals(1,list.dameCantMaximos());



    }

    @Test
    void modificar0(){
        MaxHeapArray<Integer> list;
        ArrayList<Integer> aux;
        comparadorEnteros comparador;

        aux = new ArrayList();

        aux.add(0);
        aux.add(0);
        aux.add(0);
        aux.add(0);
        aux.add(0);
        aux.add(0);
        aux.add(0);


        
        comparador = new comparadorEnteros();

        list = new MaxHeapArray(aux, comparador);

        list.arrayToHeap();

        assertEquals(0,list.dameCantMaximos());

        list.modificar(100, 0);
        list.modificar(500, 0);
        list.modificar(500, 4);

        assertEquals(2,list.dameCantMaximos());

        list.modificar(1000, 1);
        list.modificar(2000, 1);
        list.modificar(2000, 6);

        assertEquals(2,list.dameCantMaximos());


    }
    
    @Test
    void modificarNumeroMenor(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);

        aux.add(60);
        aux.add(25);
        aux.add(7);
        aux.add(2);
        aux.add(20);
        aux.add(4);

        assertEquals(aux,list.dameLista());


        list.modificar(1, 1);

        aux.clear();
        aux.add(60);
        aux.add(20);
        aux.add(7);
        aux.add(2);
        aux.add(1);
        aux.add(4);

        assertEquals(aux,list.dameLista());


    }


    @Test
    void modificarConDosHijosIguales(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        ArrayList aux2;

        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        aux = new ArrayList();
        aux2 = new ArrayList();


        aux.add(60);
        aux.add(55);
        aux.add(60);
        aux.add(30);
        aux.add(30);
        aux.add(32);
        aux.add(41);
        aux.add(20);
        aux.add(7);
        aux.add(8);
        aux.add(21);

        list = new MaxHeapArray(aux ,comparador);
        list.arrayToHeap();

        list.modificar(1, 1);

        aux2.add(60);
        aux2.add(30);
        aux2.add(60);
        aux2.add(20);
        aux2.add(30);
        aux2.add(32);
        aux2.add(41);
        aux2.add(1);
        aux2.add(7);
        aux2.add(8);
        aux2.add(21);



        assertEquals(aux2, list.dameLista());

    }


    @Test
    void arrayToheap0(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        ArrayList aux1;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        aux = new ArrayList();

 
        aux.add(60);
        aux.add(22);
        aux.add(1);
        aux.add(27);
        aux.add(79);
        aux.add(4);

        list = new MaxHeapArray(aux ,comparador);

        assertEquals(aux,list.dameLista());


        list.arrayToHeap();

        assertEquals(true,list.esHeap());



    }

    @Test
    void arrayToheap2(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        ArrayList aux1;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        aux = new ArrayList();
        aux1 = new ArrayList();

 
        aux.add(60);
        aux.add(220);
        aux.add(12);
        aux.add(27);
        aux.add(79);
        aux.add(4);
        aux.add(79);
        aux.add(100);


        list = new MaxHeapArray(aux, comparador);

        assertEquals(aux,list.dameLista());


        list.arrayToHeap();

        assertEquals(true,list.esHeap());



    }

    @Test
    void eliminarElemento1(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(4);

        aux.add(60);
        aux.add(25);
        aux.add(7);
        aux.add(2);
        aux.add(20);
        aux.add(4);

        assertEquals(aux,list.dameLista());


        list.eliminar(1);

        aux.clear();
        aux.add(60);
        aux.add(20);
        aux.add(7);
        aux.add(2);
        aux.add(4);

        assertEquals(aux,list.dameLista());


    }
    
    @Test
    void eliminarElemento2(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(87);
        list.apilar(99);
        list.apilar(1);
        list.apilar(45);

        aux.add(99);
        aux.add(45);
        aux.add(87);
        aux.add(25);
        aux.add(20);
        aux.add(7);
        aux.add(60);
        aux.add(1);
        aux.add(2);
;

        assertEquals(aux,list.dameLista());


        list.eliminar(1);

        aux.clear();
        aux.add(99);
        aux.add(45);
        aux.add(87);
        aux.add(2);
        aux.add(20);
        aux.add(7);
        aux.add(60);
        aux.add(1);
 

        assertEquals(aux,list.dameLista());


    }
    @Test
    void eliminarElemento3(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(2);
        list.apilar(25);
        list.apilar(7);
        list.apilar(20);
        list.apilar(60);
        list.apilar(87);
        list.apilar(99);
        list.apilar(1);
        list.apilar(45);

        aux.add(99);
        aux.add(45);
        aux.add(87);
        aux.add(25);
        aux.add(20);
        aux.add(7);
        aux.add(60);
        aux.add(1);
        aux.add(2);
;

        assertEquals(aux,list.dameLista());


        list.eliminar(2);


        aux.clear();
        aux.add(99);
        aux.add(45);
        aux.add(87);
        aux.add(25);
        aux.add(20);
        aux.add(2);
        aux.add(60);
        aux.add(1);
        

        assertEquals(aux,list.dameLista());


        list.eliminar(6);


        aux.clear();
        aux.add(87);
        aux.add(45);
        aux.add(60);
        aux.add(25);
        aux.add(20);
        aux.add(2);
        aux.add(1);


        assertEquals(aux,list.dameLista());


        list.eliminar(4);

        aux.clear();
        aux.add(87);
        aux.add(45);
        aux.add(2);
        aux.add(25);
        aux.add(20);
        aux.add(1);
  
        assertEquals(aux,list.dameLista());




    }

    @Test
    void eliminarElemento4(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;

        
        comparador = new comparadorEnteros();

        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(45);
        list.apilar(2);
        list.apilar(87);
        list.apilar(20);
        list.apilar(1);
        list.apilar(25);
        list.apilar(29);


    aux.add(87);
    aux.add(20);
    aux.add(45);
    aux.add(2);
    aux.add(1);
    aux.add(25);
    aux.add(29);


    assertEquals(aux,list.dameLista());

    
    list.eliminar(0);

    aux.clear();
    aux.add(87);
    aux.add(20);
    aux.add(29);
    aux.add(2);
    aux.add(1);
    aux.add(25);

    assertEquals(aux,list.dameLista());




}

    @Test
    void arrayToheap1(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        ArrayList aux1;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        aux = new ArrayList();

 
        aux.add(60);
        aux.add(22);
        aux.add(1);
        aux.add(27);
        aux.add(79);
        aux.add(4);

        list = new MaxHeapArray(aux, comparador);

        assertEquals(aux,list.dameLista());


        list.arrayToHeap();

        assertEquals(true,list.esHeap());



    }

    

    @Test
    void eliminarElementoTodosIguales(){
        MaxHeapArray<Integer> list;
        ArrayList aux;
        comparadorEnteros comparador;


        comparador = new comparadorEnteros();


        list = new MaxHeapArray(comparador);
        aux = new ArrayList();

        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);
        list.apilar(100);


        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);

        assertEquals(aux,list.dameLista());


        list.eliminar(2);


        aux.remove(aux.size()-1);
        assertEquals(aux,list.dameLista());


        list.eliminar(1);
        list.eliminar(3);
        list.eliminar(5);
        list.eliminar(6);
        list.eliminar(7);

        aux.clear();
        aux.add(100);
        aux.add(100);
        aux.add(100);
        aux.add(100);

        assertEquals(aux,list.dameLista());



    }


}
