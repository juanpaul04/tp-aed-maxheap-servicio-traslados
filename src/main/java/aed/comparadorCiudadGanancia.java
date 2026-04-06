package aed;

import java.util.Comparator;

public class comparadorCiudadGanancia implements Comparator<Ciudad>{
    @Override
    public int compare(Ciudad t1, Ciudad t2){


        return Integer.compare(t1.ganancias, t2.ganancias);
    
   

    }
    }


