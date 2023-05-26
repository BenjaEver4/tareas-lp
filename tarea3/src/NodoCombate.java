public class NodoCombate extends Nodo{
    Personaje enemigo;
    public NodoCombate(int id,Personaje enemigo){ //constructor de nodoCombate
        super(id);  
        this.enemigo=enemigo;
    }
    public void setEnemigo(Personaje enemigo){
        this.enemigo=enemigo;
    }
    public void interactuar(Jugador jugador_1){ // esta funcion permite que jugador_1 pueda interactuar combatiendo contra los personajes del juego.
        jugador_1.combate(enemigo);
    }

}