import java.util.ArrayList;
public class Jugador extends Personaje{  //jugador extension de personaje , contiene un atributo de tipo ArrayList<Item> para almacenar items obtenidos.
    public ArrayList<Item> items_aplicados = new ArrayList<Item>(); 
    public Jugador(String nombre,int dinero,int hp_actual,int hp_total,int danio, int defensa,Item items ){ //Constructor de Jugador.
        super(nombre,dinero,hp_actual,hp_total,danio,defensa);
        items_aplicados.add(items);
        
    }
    public void verItems(){ //funcion para ver todos los items que se le han aplicado al jugador.
        for(int i=1;i<items_aplicados.size();i++){
            System.out.println("ITEM :"+i);
            System.out.println("  precio :"+items_aplicados.get(i).getPrecio());
            System.out.println("  recuperar_hp:"+items_aplicados.get(i).getRecuperar_Hp());
            System.out.println("  aumentar_hp_total :"+items_aplicados.get(i).getAumentar_HpTotal());
            System.out.println("  aumetnar_danio :"+items_aplicados.get(i).getAumentar_Danio());
            System.out.println("  aumentar_defensa :"+items_aplicados.get(i).getAumentar_Defensa());
        }
        
    }
    
    public void verEstado(){//funcion para ver todos los items que se le han aplicado al jugador.
        System.out.println("Nombre: "+getNombre());
        System.out.println("Dinero: "+getDinero());
        System.out.println("Hp actual: "+getHpActual());
        System.out.println("Hp total: "+getHpTotal());
        System.out.println("Danio: "+getDanio());
        System.out.println("Defensa: "+getDefensa());
    }
}