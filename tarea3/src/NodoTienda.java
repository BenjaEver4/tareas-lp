import java.util.ArrayList;
import java.util.Scanner;
public class NodoTienda extends Nodo{
    public ArrayList<Item> inventario = new ArrayList<Item>(); 
    public NodoTienda(int id,Item items){
        super(id);  
        inventario.add(items);
    }

    public void comprar(int n,Jugador jugador_1){ //funcion para comprar items del inventario de nodo_tienda 
        System.out.println("COMPRANDO EL ITEM:"+n+" = "+inventario.get(n));
        inventario.get(n).aplicar(jugador_1); // se le aplica el item comprado a las estadisticas de jugador.
        jugador_1.items_aplicados.add(inventario.get(n));// se le aplica el item comprado a items_aplicados de jugador.
    }
    public void interactuar(Jugador jugador_1){ // funcion para interactuar con el jugador,
        Scanner entrada =new Scanner(System.in);
        System.out.println("Tu dinero actual: "+ jugador_1.getDinero()+"$");
        for(int i=1;i<inventario.size();i++){
            System.out.println("Item "+i+" :"+ inventario.get(i));

        }
        boolean t=true;
        while(t==true){
            //En este proceso se decide Si quiere comprar un item , Cual Item, y si quiere seguir comprando más Items.
            System.out.println("Si deseas continuar la compra, presiona: 1, si deseas finalizar la compra presiona 2 o otra cifra"); 
            int finalizar = entrada.nextInt();
            if(finalizar!=1){
                t=false;
                break;
            }
            if (finalizar==1){
                int cantidad=inventario.size()-1;
                System.out.println("Cual de los "+cantidad +" items quieres comprar :");
                int num = entrada.nextInt();
                if(jugador_1.getDinero()>=inventario.get(num).getPrecio()){  //Jugador_1 Si tiene el dinero para poder comprar el Item que quiere.
                    comprar(num,jugador_1);
                    jugador_1.setDinero(jugador_1.getDinero()-inventario.get(num).getPrecio());
                    jugador_1.verEstado();
                }
                else{//Jugador_1 No tiene el dinero para poder comprar el Item que quiere.
                    System.out.println("Te falta dinero para comprar el item, vuelve otro día "+num);
                    t=false;
                    break;
                }  
            }
        }
    }   
}