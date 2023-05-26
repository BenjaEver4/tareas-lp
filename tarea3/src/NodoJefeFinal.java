public class NodoJefeFinal extends Nodo{
    Personaje jefe;
    public NodoJefeFinal(int id,Personaje jefe){ //constructor de nodoJefeFinal
        super(id);
        this.jefe=jefe;       
    }
    public void interactuar(Jugador jugador_1){ //funcion que interactua con jugador_1 para poder combatir.
        jugador_1.combate(jefe);
    }
}
