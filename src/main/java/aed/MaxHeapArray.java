package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class MaxHeapArray<T> {

    private int cantidad;
    private ArrayList<elem> heap;
    private Comparator<T> comparador;      
    private ArrayList<Integer> indices;

    private ArrayList<Integer> listaMax;
    private Integer cantMax; 


    private class elem{                                      // creamos la clase de los objetos que irán en el heap
        private T valor;                                     // el handle estará ligado en las ciudades del 0 a la cantidad-1 y en los traslados asociaremos cada traslado ingresado con un handle a partir del 0
        private int handle;

        private elem(T valor, int handle){
            this.valor = valor;
            this.handle = handle;
        }

    }
    

    public MaxHeapArray(Comparator<T> comparador){
        this.cantidad=0;                                    //inicio con cantidad igual a 0
        this.heap = new ArrayList();                        //inicio con la lista de elementos vacia
        this.comparador = comparador;                       //inicio con el comparador pasado por parámetro
        this.indices =  new ArrayList();                    //inicio de la lista de los indices vacía (cada posición de la lista será el indice que le corresponde al handle que vale esa posición de la lista, pudiendo acceder al indice del elemento a partir de su handle "indices[h] = indice del elemento de handle h"
        this.listaMax =  new ArrayList();                   //inicio de la lista que contendrá los máximos (esto es utilizado únicamente para calcular los maximos de ganancias y perdidas de las ciudades, ya que al estas siempre sumarse, nunca se da el caso de que el máximo "baje", sino que o se mantiene, o es alcanzado (o superado) por un elemento de abajo)
        this.cantMax = 0;
    }

    public MaxHeapArray(ArrayList<T> lista, Comparator<T> comparador){          // constructor a partir de una lista de elementos de tipo T y un comparador

        this.cantidad=0;                                    
        this.heap = new ArrayList();                        
        this.comparador = comparador;                       
        this.indices =  new ArrayList();                    
        this.listaMax =  new ArrayList();                  
        this.cantMax = 0;


        int n = 0;                                        // proc para a partir de una lista de elementos T, desordenados, agregar uno a uno a la lista

        while(n<lista.size()){                            // O(n)    
            elem elem = new elem(lista.get(n),n);         // creamos un elemento nuevo, los handles irán del 0 al |lista|-1   (O(1)
            this.heap.add(elem);                          // agregamos al final de la lista de elems el nuevo elemento sin ordenar (O(1))
            this.indices.add(n);                          // agregamos al final de la lista de indices el índice correspondiente al elem (creciente de 0 a |lista|-1) (O(1))


            n+=1;
        }

        this.cantidad = lista.size();                      // proc  O(n)
    }

    public boolean vacio(){      
        if(this.cantidad == 0){
            return true;
        }else{
            return false;
        }

    }

    public int compararPorValor(T valor1, T valor2){       // procedimiento para comparar a partir de los dos valores T, y no del indice en el heap
    return this.comparador.compare(valor1,valor2);}


    public int comparar(int indice1, int indice2){
      
        return this.comparador.compare(this.heap.get(indice1).valor,this.heap.get(indice2).valor);       // defino el metodo comparar a partir del comparador a utilizar, se va a definir con los dos indices de los elementos a comparar
    }


    public void subir(int indiceCambio){

        //algoritmo para subir un elemento que es pasado por parámetro;

        int indice = indiceCambio;                                                                  //fijo el indice del elemento a subir

        int indicePadre = ((indice-1)/2);                                                           //busco el indice del padre

            while(indicePadre>=0 && this.comparar(indice, indicePadre) > 0){                        //mientras el indice del padre se mantenga en rango, y el padre sea menor que el elemento a cambiar


                swap(indice,indicePadre);                                                           // intercambio el padre con el elemento a cambiar


                 indice = indicePadre;                                                              //luego, "subo" en el arbol y debo hacer la misma operación con la nueva posicion (la que era del padre), con la del nuevo padre, así sucesivamente hasta que no se cumpla la guarda.
                 indicePadre = ((indice-1)/2);       
            }

            // se realiza en O(log n) siendo n la cantidad de elementos, ya que los heaps estan perfectamente balanceados y la cantidad de cambios en el peor caso (debo subir una hoja hasta la raíz) es la altura, que es logarítmica al estar balanceado
    }

    public void bajar(int indiceCambio){


        int indice = indiceCambio;                                                                          // fijo los indices del elementos a cambiar y sus hijos
        int indiceHijoIzq = 2*indice + 1;
        int indiceHijoDer = 2*indice + 2;


        while(indiceHijoIzq < this.cantidad && indiceHijoDer < this.cantidad &&                                                       // mientas que los hijos se mantengan en rango,
        ((this.comparar(indice,indiceHijoIzq))<0  || (this.comparar(indice,indiceHijoDer))<0)){                                       // y al menos uno de sus hijos sea mayor en comparación, entro en el ciclo

            if((this.comparar(indiceHijoIzq,indiceHijoDer))>=0){                                                                       // caso 1: el hijo izquierdo es mayor al derecho o ambos hijos son iguales
                
                swap(indice,indiceHijoIzq);                                                                                           // intercambio el hijo izquierdo con el elemento a bajar


                indice = 2*indice+1;                            
                indiceHijoIzq = 2*indice + 1;                                                                                         // ahora el elemento a cambiar es el que era el hijo izquierdo, y me fijo sus dos hijos para seguir realizando el ciclo hasta que no se cumpla la guarda
                indiceHijoDer = 2*indice + 2;


            }else{
                
                                                                                                                                      // caso 2: la hijo derecho es estrictamente mayor que la izquierda
                swap(indice, indiceHijoDer);                                                                                          // mismo procedimiento que caso 1, pero con el hijo derecho
              
                indice = 2*indice+2;
                indiceHijoIzq = 2*indice + 1;
                indiceHijoDer = 2*indice + 2;

            }
        }

        if(indiceHijoIzq==this.cantidad-1 && indiceHijoDer >= this.cantidad){                        // finalmente, como el ciclo finaliza si uno de los dos hijos está fuera de rango        

            if((this.comparar(indiceHijoIzq,indice))>0){                                             // hago el caso aparte si existe un último  padre con solo hijo izquierdo(ocurre si la cant de elementos es par)     
               
                swap(indice,indiceHijoIzq);

            }

        }                                  // al igual que subir, la complejidad es O(log n) ya que el peor caso sería bajar el elemento de la raiz  hasta una de las hojas.
    
    }   
        
    public void swap(int indice1, int indice2){                                                         // proc para el swapeo de elementos, pasando como parámetro sus indices en el heap
        elem elem1 = new elem(this.heap.get(indice1).valor,this.heap.get(indice1).handle);            
        elem elem2 = new elem(this.heap.get(indice2).valor,this.heap.get(indice2).handle);              //creo dos elementos nuevos con  el mismo valor y handle que el original


        this.heap.set(indice2, elem1);                                                                    // los swapeo en el heap
        this.heap.set(indice1,elem2);

        this.indices.set(elem1.handle,indice2);                                                         // swapeo los indices en la lista de indices usando los handles (set en la lista es O(1))
        this.indices.set(elem2.handle,indice1);
    }                                                                                                   // O(1)





    public void modificar(T elem, int handle){                                                          // proc para modificar un elemento a partir de su handle

        int indiceCambio = this.indices.get(handle);

        elem nuevoElem = new elem(elem, handle);

        if(this.compararPorValor(this.dameMaximo(), elem)<0){                                           // si el elemento modificado es mayor en comparacion al maximo:
            this.listaMax.clear();
            this.listaMax.add(handle);                                                                  // reseteo la lista de maximos (O(1)) y agrego su handle ((O(1))
            this.resetCantMax();
        }

        if(this.compararPorValor(this.dameMaximo(), elem)==0 ){                                         // si el elemento agregado es igual en comparación al maximo, agrego su handle al final de la lista de maximos
            this.listaMax.add(handle);
            this.cantMax+=1;

        }


        this.heap.set(indiceCambio, nuevoElem);                                     // seteo el  nuevo elemento en el indice correspondiente

        this.subir(indiceCambio);                                                   // lo subo si es posible  (O(log n))
        this.bajar(indiceCambio);                                                   // lo bajo si es posible  (O(log (n))          

    
       
    }

    public void apilar(T elem){

        this.cantidad+=1;
        elem nuevoElem = new elem(elem, this.indices.size());                       // creo nuevo elemento con el valor pasado como parámetro y con handle consecutivo al último agregado

        // primero chequeo si esta vacio
        if(this.vacio()==true){ 
            

            this.heap.add(nuevoElem);                                                   // agrego al final del heap (O(1))
            this.indices.add(this.cantidad-1);                                                       
            this.listaMax.add(nuevoElem.handle);                                        // si el heap está vacío el primer elemento sí o sí será el máximo
            this.resetCantMax();

        }else{                                                                          // si no está vacío el heap
            this.heap.add(nuevoElem);                                                  // agrego el elemento al final O(1)
            this.indices.add(this.cantidad-1);                                     // agrego al final de indices el valor del indice del nuevo elemento (la última posición del heap)


            // actualizo la cantidad de maximos dependiendo si el elemento agregado es  mayor o igual al maximo actual


            if(this.compararPorValor(dameMaximo(), nuevoElem.valor)<0){
                this.listaMax.clear();
                this.listaMax.add(nuevoElem.handle);
                this.resetCantMax();                                                // procedimiento igual al caso de modificar un elemento
            }

            if(this.compararPorValor(dameMaximo(), nuevoElem.valor)==0 ){
                this.listaMax.add(nuevoElem.handle);
                this.cantMax+=1;

            }
        }


            int indiceCambio = this.cantidad-1;
            this.subir(indiceCambio);                                               // subo el elemento desde la ultima posición hasta la que le corresponda


                // O(log n)

    }

    public void eliminar(int handle){                                               // proc para eliminar un elemento a partir de su handle
       int indiceCambio = this.indices.get(handle);                                 // busco el índice correspondiente
        swap(indiceCambio,this.heap.size()-1);                                         // swapeo el último elemento del heap con el elemento a eliminar
        
        this.indices.set(handle,null);                                      // el indice del elemento a eliminar queda con null, ya que los handles son únicos y no reutilizamos esa posición de la lista de indices

        this.heap.remove(this.cantidad-1);                                          // eliminamos el elemento que ahora está en la última posición del heap ((O(1))
        this.cantidad-=1;


        bajar(indiceCambio);                                                        // como el elemento borrado fue intercambiado por el último del heap, sólo es posible bajarlo

    }   //(O (log n))



    public T sacarMaximo(){

        elem max = this.heap.get(0);                                                 // caso particular del procedimiento anterior, sólo que el elemento a borrar es la raíz o máximo, y devuelvo su valor
        
        T res = max.valor;



        swap(0,this.cantidad-1);
        this.indices.set(max.handle,null);


        this.heap.remove(this.cantidad-1);                                                                            // borro la ultima posición


        this.cantidad-=1;

        int indiceCambio = 0;
        this.bajar(indiceCambio);                                                                                      // bajo desde la primera posicion hasta la que le corresponda al elemento cambiado



        return res;
    }

    public int dameHandleMax(){
        return this.heap.get(0).handle;
    }


    public void arrayToHeap(){                                                      // proc para utilizar cuando me pasan una lista por parámetro y luego debo volverla un heap

        int n = this.heap.size()-1;                                                 // recorro el heap desde el último elemento hasta el primero, y cada uno si es posible lo bajo

        while(n>=0){                                                                // O(n)
            this.bajar(n);                                                          // O(log n)
            n-=1;
        }
            // sin embargo, por la demostración vista en la teórica, este algoritmo (Floyd) es O(n)
    }

    public boolean esHeap(){                                                                                            // para poder chequear en los tests si la lista es un heap (para ver si el arrayToheap funciona)

        boolean res = true;

        int indice = 0;
        int indiceHijoIzq = 2*indice + 1;
        int indiceHijoDer = 2*indice + 2;

        while(indiceHijoDer<this.cantidad && indiceHijoIzq<this.cantidad){                                               // mientras tanto el hijo izq como el hijo der estén en rango,
            if(this.comparar(indice,indiceHijoDer)<0 || this.comparar(indice,indiceHijoIzq)<0){                          // si uno de los dos es mayor al padre se vuelve falso
                res=false;
            }
            indice+=1;
            indiceHijoIzq = 2*indice + 1;
            indiceHijoDer = 2*indice + 2;

        }

        if(indiceHijoDer >= this.cantidad && indiceHijoIzq == this.cantidad-1){                                          // esto para el caso que el último padre del heap tiene sólo un hijo izquierdo

            if(this.comparar(indice,indiceHijoIzq)<0){
                res=false;
            }

        }
        return res;


    }


    public ArrayList<Integer> dameIndices(){
        return this.indices;
    }                                                                       // algoritmos solo utilizados para tests

    public ArrayList<T> dameLista(){

        int n = 0;

        ArrayList<T>  res;

        res = new ArrayList<>();


        while(n<this.heap.size()){
            res.add(this.heap.get(n).valor);
            n+=1;
        }

        return res;
    }

    public T dameMaximo(){
        return this.heap.get(0).valor;          //O(1)
    }
    public int dameCantMaximos(){
        return this.cantMax;    
    }                                               

    public ArrayList<Integer> dameListMax(){
        return this.listaMax;
    }

    public void resetCantMax(){
        this.cantMax=1;
    }


}
