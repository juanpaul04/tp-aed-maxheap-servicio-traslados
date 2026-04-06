package aed;

import java.util.ArrayList;

public class BestEffort {

    private ArrayList<Ciudad> listaDeCiudades;                              //  utilizamos una lista en la que cada posición será la de la ciudad que le corresponde el ID para poder modificar fácilmente sus valores
    private MaxHeapArray<Traslado>  trasladosRedituables;                   // utilizaremos 2 heaps para los traslados (redituables y timeStamp como criterios)
    private MaxHeapArray<Traslado>  trasladosAntiguos;
    private MaxHeapArray<Ciudad>    ciudadesGanancia;
    private MaxHeapArray<Ciudad>    ciudadesPerdida;                        // utilizaremos 3 heaps para las ciudades (gananacia, pérdida, superavit como criterios)
    private MaxHeapArray<Ciudad>    ciudadesSuperavit;



    private int cantidadVentas;                                             // para calcular el promedio global
    private int gananciaTotal;
    



    public BestEffort(int cantCiudades, Traslado[] traslados){
        int i = 0;

        comparadorCiudadSuperavit comparadorCiudadSuperavit = new comparadorCiudadSuperavit();
        comparadorCiudadGanancia comparadorCiudadGanancia = new comparadorCiudadGanancia();                                     // creo los comparadores de las ciudades ((O(1)))
        comparadorCiudadPerdidas comparadorCiudadPerdidas = new comparadorCiudadPerdidas();


        this.listaDeCiudades = new ArrayList<>();

        while(i<cantCiudades){
            Ciudad c;
            c = new Ciudad(i,0,0,0);                                                // agrego todas las ciudades en la lista de ciudades con sus valores todos en 0

            listaDeCiudades.add(c);
            i+=1;
        }   // O(|C|)
        

        this.ciudadesSuperavit = new MaxHeapArray(listaDeCiudades, comparadorCiudadSuperavit);                  //O(|C|)
        this.ciudadesSuperavit.arrayToHeap();                                                                   //O(|C|)

                                                                                                               //  creamos los heaps de ciudades con la lista creada y sus comparadores
        this.ciudadesGanancia = new MaxHeapArray(listaDeCiudades, comparadorCiudadGanancia);                   // realizamos el array2heap (floyd) para cada una de las ciudades
        this.ciudadesGanancia.arrayToHeap();                                                                   // todos O(|C|)


        this.ciudadesPerdida = new MaxHeapArray(listaDeCiudades, comparadorCiudadPerdidas);
        this.ciudadesPerdida.arrayToHeap();


        comparadorTrasRedituable comparadorTrasRedituable = new comparadorTrasRedituable();
                                                                                                              // creamos los  comparadores de los traslados
        comparadorTrasladoTimeStamp comparadorTrasladoTimeStamp = new comparadorTrasladoTimeStamp();          // O(1)

        int n = 0;
        ArrayList<Traslado> list = new ArrayList();

        while(n<traslados.length){
            list.add(traslados[n]);                                 // pasamos del array de traslados a una lista cada traslado
            n+=1;
        }       // O(|T|)

        this.trasladosRedituables = new MaxHeapArray(list, comparadorTrasRedituable);       // O(|T|)
        this.trasladosAntiguos = new MaxHeapArray(list, comparadorTrasladoTimeStamp);                                                       
                                                                    // creación de heaps y array2heap según el timestamp o la gananciaNeta, todo O(|T|)
        this.trasladosAntiguos.arrayToHeap();                                               // O(|T|)
        this.trasladosRedituables.arrayToHeap();


    }       //O(|T| + |C|)




    public void registrarTraslados(Traslado[] traslados){
        int n = 0;

        while(n<traslados.length){                                                      // O(|traslados|)
            trasladosRedituables.apilar(traslados[n]);                                  // para agregar nuevos traslados pasados en un array
            trasladosAntiguos.apilar(traslados[n]);                                     // por cada elemento en traslados, lo apilo en los heaps (O(ln(T)) siendo T la cant de traslados ya en el heap
            n+=1;
        }
    }   // O(|traslados| * log(T))




    public int[] despacharMasRedituables(int n) {

        int[] res = new int[n];
        int i = 0;


        while(trasladosRedituables.vacio()==false && i<n){                                      // O(n)
            int handle = trasladosRedituables.dameHandleMax();                                  // pedimos el handle del máximo del heap O(1)

            Traslado traslado = trasladosRedituables.sacarMaximo();                             // O(log(T)), realizamos el traslado según el criterio pedido (ganancia), retiramos el máximo de ese heap


            trasladosAntiguos.eliminar(handle);                                          // O(log(T)), eliminamos el elemento del otro heap, usando que el handle del elemento es el mismo
            
            res[i]=traslado.id;                                                               // O(1) agregamos al resultado el id del traslado

            Ciudad origen = new Ciudad(traslado.origen, this.listaDeCiudades.get(traslado.origen).ganancias+traslado.gananciaNeta,this.listaDeCiudades.get(traslado.origen).perdidas,this.listaDeCiudades.get(traslado.origen).superavit+traslado.gananciaNeta);
            Ciudad destino = new Ciudad(traslado.destino, this.listaDeCiudades.get(traslado.destino).ganancias,this.listaDeCiudades.get(traslado.destino).perdidas+traslado.gananciaNeta,this.listaDeCiudades.get(traslado.destino).superavit-traslado.gananciaNeta);


            this.listaDeCiudades.set(origen.nombre, origen);                                    // modificamos las ciudades a partir de la gananciaNeta del traslado realizado
            this.listaDeCiudades.set(destino.nombre, destino);                                  //  O(1)


            this.ciudadesGanancia.modificar(origen, origen.nombre);

            this.ciudadesPerdida.modificar(destino, destino.nombre);
                                                                                        //modificamos cada heap con la ciudad modificada  
            this.ciudadesSuperavit.modificar(origen, origen.nombre);                    // todas O(log(|C|))

            this.ciudadesSuperavit.modificar(destino, destino.nombre);



            this.cantidadVentas+=1;
            this.gananciaTotal+= traslado.gananciaNeta;                                  // aumentamos la cant de traslados realizados en 1 y sumamos la ganancia total

            i+=1;

        }




      
        return res; // O(n*(log(|T|) + log(|C|)))
    }

    public int[] despacharMasAntiguos(int n){
        
        int[] res = new int[n];
        int i = 0;

        while(trasladosAntiguos.vacio()==false && i<n){                                             // mismas operaciones que despacharMasRedituables, solamente que despachando según timestamp, por lo tanto, misma complejidad
            int handle = trasladosAntiguos.dameHandleMax();
            Traslado traslado = trasladosAntiguos.sacarMaximo();

            trasladosRedituables.eliminar(handle);           
            
            res[i]=traslado.id;

            Ciudad origen = new Ciudad(traslado.origen, this.listaDeCiudades.get(traslado.origen).ganancias+traslado.gananciaNeta,this.listaDeCiudades.get(traslado.origen).perdidas,this.listaDeCiudades.get(traslado.origen).superavit+traslado.gananciaNeta);
            Ciudad destino = new Ciudad(traslado.destino, this.listaDeCiudades.get(traslado.destino).ganancias,this.listaDeCiudades.get(traslado.destino).perdidas+traslado.gananciaNeta,this.listaDeCiudades.get(traslado.destino).superavit-traslado.gananciaNeta);


            this.listaDeCiudades.set(origen.nombre, origen);
            this.listaDeCiudades.set(destino.nombre, destino);


            this.ciudadesGanancia.modificar(origen, origen.nombre);


            this.ciudadesPerdida.modificar(destino, destino.nombre);


            this.ciudadesSuperavit.modificar(origen, origen.nombre);


            this.ciudadesSuperavit.modificar(destino, destino.nombre);



            this.cantidadVentas+=1;
            this.gananciaTotal+= traslado.gananciaNeta; 

            i+=1;

        }

        return res;     // O(n*(log(|C|)+log(|T|)))

    }

    public int ciudadConMayorSuperavit(){
        return ciudadesSuperavit.dameMaximo().nombre;       // O(1) ya que solo se devuelve la primera posición del heap, no se la desapila
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        return this.ciudadesGanancia.dameListMax();          //O(1) ya que es solo devolver la lista de maximos que fue creada y modificada durante los otros procedimientos
    }                                                                   
    public ArrayList<Integer> ciudadesConMayorPerdida(){
        return this.ciudadesPerdida.dameListMax();              //O(1)
    }

    public int gananciaPromedioPorTraslado(){
        return (int)(gananciaTotal/cantidadVentas);             //(O(1))
    }
    


}