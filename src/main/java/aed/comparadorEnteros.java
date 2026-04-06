package aed;

import java.util.Comparator;

// Clase creada para facilitar los tests del Heap


public class comparadorEnteros implements Comparator<Integer>
{
    
    @Override
    public int compare(Integer t1, Integer t2){
        return Integer.compare(t1 ,t2);
    }


}
