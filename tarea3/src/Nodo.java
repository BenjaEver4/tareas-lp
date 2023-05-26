import java.util.ArrayList;
public abstract class Nodo{
    public int id;
    public ArrayList<Nodo> siguentes_nodos= new ArrayList<Nodo>();
    public Nodo(int id){
        this.id=id;
    } 
    public void setId(int id){
        this.id=id;
    }   
    public int getId(){
        return id;
    }
    public void agregarNodo(Nodo n){ //funcion para agregar nodos a la lista de nodos.
        siguentes_nodos.add(n);
    } 

    public abstract void interactuar(Jugador jugador_1); //funcion que permite a jugador_1 hacer multiples acciones como: Nodo Tienda-> Comprar , Nodo Combate->Combatir , NodoEvento-> Regalar Items 
    
}