public class NodoInicial extends Nodo{ 
    public NodoInicial(int id){ //Constructor de nodo_inicial.
        super(id);
    }
    public void interactuar(Jugador jugador_1){ //funcion que interactua con jugador_1 para poder darle la bienvenida al juego.
        System.out.println("Hola "+jugador_1.getNombre()+", Bienvenido a Tunel Fight, haz sido seleccionado por leyendas divinas para ser participe de este juego, del cual solo puedes salir SI GANAS . Tendrás que pelear contra personajes muy fuertes , y a medida que avances irás recolectando items para volverte más y más fuerte. ¡TE DESEAMOS MUCHA SUERTE!");
    }
}