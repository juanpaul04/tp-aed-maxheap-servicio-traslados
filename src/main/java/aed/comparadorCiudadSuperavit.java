package aed;

import java.util.Comparator;

public class comparadorCiudadSuperavit implements Comparator<Ciudad>{
    @Override
    public int compare(Ciudad t1, Ciudad t2){

        if(Integer.compare(t1.superavit,t2.superavit) !=0){

        return Integer.compare(t1.superavit, t2.superavit);}
    
    else{

        return (-1) * Integer.compare(t1.nombre,t2.nombre);
    }

    }

}
