package aed;

public class Ciudad {
        int nombre;
        int ganancias;
        int perdidas;
        int superavit;
    
        public Ciudad(int nombre, int ganancias, int perdidas, int superavit){
            this.nombre = nombre;
            this.ganancias = ganancias;
            this.perdidas = perdidas;
            this.superavit = superavit;
        }

        public void copiaCiudad(Ciudad ciudad){
            this.nombre = ciudad.nombre;
            this.ganancias = ciudad.ganancias;
            this.perdidas = ciudad.perdidas;
            this.superavit = ciudad.superavit;
        
        }
    
    

}
