package aed;

import java.util.Comparator;

public class comparadorTrasladoTimeStamp  implements  Comparator<Traslado>{

    @Override
    public int compare(Traslado t1, Traslado t2){
        return -1*Integer.compare(t1.timestamp, t2.timestamp);
    }

}
