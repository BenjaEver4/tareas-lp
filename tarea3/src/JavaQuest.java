//package GraphGenerator;
import java.util.SortedSet;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class JavaQuest {
    public static void main(String[] args) {
        
        Item item_0=new Item(0,0,0,0,0);   //inicializo item_0 con estadisticas al 0 para facilitar el proceso
        Random r = new Random();
        NodoTienda nodo_tienda =new NodoTienda(-1,item_0);
        int randomNumber = r.nextInt(8-5+1)+5   ;
        for (int i=1 ; i<randomNumber+1;i++){   //creo el primer item y lo agrego al inventario de nodo_tienda
            int x=r.nextInt(300-50+1)+50;
            int y=r.nextInt(20-5+1)+5;
            int z=r.nextInt(5-1+1)+1;
            int w=r.nextInt(3-1+1)+1;           
            int v=r.nextInt(2-1+1)+1;
            Item item=new Item(x,y,z,w,v);
            nodo_tienda.inventario.add(item);
       
        }
        System.out.printf("Que nombre le quieres poner a tu jugador: ");
        Scanner nombres =new Scanner(System.in);
        String nombreJugador = nombres.nextLine();
        Jugador jugador_1=new Jugador(nombreJugador,500,20,20,5,1,item_0); //Inicializo jugador_1

        int invNumEv1=r.nextInt(nodo_tienda.inventario.size()-1)+1;  //un random int: para extraer 1 item del inventario de nodo tienda,cuando se requiere para NodoEvento
        int invNumEv2=r.nextInt(nodo_tienda.inventario.size()-1)+1; //un random int:  para extraer 1 item del inventario de nodo tienda,cuando se requiere para NodoEvento
        //inicializando nodo_evento y nodo_inicial
        NodoEvento nodo_evento=new NodoEvento(1,"Evento","item 1:","item 2:",nodo_tienda.inventario.get(invNumEv1),nodo_tienda.inventario.get(invNumEv2)); //Sr inicializa nodo_evento
        NodoInicial nodo_inicial=new NodoInicial(0); //se inicializa nodo_inicial.
        System.out.println("\n");
        nodo_inicial.interactuar(jugador_1);
        
        Scanner profundidad =new Scanner(System.in);
        System.out.println("\n");
        System.out.printf("¿ Que profundidad deseas que tenga el mapa ? : ");
        int profundidadMapa = profundidad.nextInt();
        Mapa mapa=new Mapa(profundidadMapa,nodo_inicial,nodo_inicial);  //Inicializando mapa
        
        SortedSet<Edge> edges = GraphGenerator.Generar(mapa.getProfundidad()); //Generando el mapa con su respectiva profundidad
        int cambio=0;
        int aleatorio;
        int termino=0;
        int numf=0;
        NombresPJ np = new NombresPJ(); //Lista de nombres de personajes.
        np.nombresPersonajesLista();
        
        for (Edge e : edges) {
            numf=e.y;
        }
        for (Edge e : edges) { //Genero el mapa en relacion al codigo compartido en la tarea, para generar el grafo/arbol dirigido.
            aleatorio=r.nextInt(10-1+1)+1; //numero aleatorio entre 1-10   10%nodo_tienda(1), 30% nodo_evento(2-4) , , 60% nodo_combate (5-10)
            if (cambio!=e.x && e.x!=0 && mapa.nodo_actual.siguentes_nodos.size()!=0){ 
                    Scanner decidir =new Scanner(System.in);
                    System.out.println("\n");
                    System.out.printf("¿ Quieres ver en que nivel del mapa te encuentras ? , si es asi presiona 1, si no 2 u otra cifra :");
                    int decision0 = decidir.nextInt();
                    if (decision0==1){
                        mapa.verMapa();
                    }
                    mapa.avanzar(jugador_1);
                    cambio=mapa.nodo_actual.getId();
                    if (jugador_1.getHpActual()<=0){
                        System.out.printf("HAS PERDIDO, SUERTE PARA LA PROXIMA NOVATO!");
                        break;
                    }
                    System.out.println("\n");
                    System.out.println("Quieres ver tu estado actual?, si es asi presiona 1, si no 2 u otra cifra :");
                    int decision = decidir.nextInt();
                    if (decision==1){
                        jugador_1.verEstado();
                    }
                    System.out.println("\n");
                    System.out.println("Quieres ver tus items actuales?, si es asi presiona 1, si no 2 u otra cifra :");
                    int decision2 = decidir.nextInt();
                    
                    if (decision2==1){
                        if (jugador_1.items_aplicados.size()>1){
                            jugador_1.verItems();
                        }
                        else{
                            System.out.println("Aún no has obtenido Items!");
                        }
                    }
                    termino++;
                    if(termino==mapa.getProfundidad()){ 
                        //estadisticas del jefe final mas fuertes que personaje común.
                        int u=r.nextInt(25-5+1)+5;
                        int t=r.nextInt(25-5+1)+5;
                        if (u<t){
                            u=t;
                        }
                        int h=r.nextInt(8-1+1)+1;           
                        int j=r.nextInt(4-1+1)+1;
                        Personaje jefe = new Personaje("FINAL BOSS KING",0,u,t,h,j);  
                        NodoJefeFinal nodo_jefe_final = new NodoJefeFinal(numf,jefe); //se inicializa el Jefe Final.
                        nodo_jefe_final.setId(numf);
                        mapa.nodo_actual.agregarNodo(nodo_jefe_final);
                        mapa.avanzar(jugador_1);
                        if (jugador_1.getHpActual()<=0){
                            System.out.printf("HAS PERDIDO, SUERTE PARA LA PROXIMA NOVATO!");
                            break;
                        }
                        if(jefe.getHpActual()<=0){
                            System.out.println("\n");
                            System.out.printf("FELICIDADES HAS ACABADO EL JUEGO!!!!");
                        }
                        break;
                    }
                    
                    
            }
            if(cambio==e.x){
                if(aleatorio==1){ //CREACION DE NODOS TIENDA 10%probabilidad
                    nodo_tienda =new NodoTienda(0,item_0);
                    randomNumber = r.nextInt(8-5+1)+5   ;
                    //estadisticas aleatorias para un Item.
                    for (int f=1 ; f<randomNumber+1;f++){
                        int x=r.nextInt(300-150+1)+150;
                        int y=r.nextInt(20-5+1)+5;
                        int z=r.nextInt(5-1+1)+1;
                        int w=r.nextInt(3-1+1)+1;           
                        int v=r.nextInt(2-1+1)+1;
                        Item item=new Item(x,y,z,w,v);
                        nodo_tienda.inventario.add(item);//terminada la construccion de nodo se almacena en la lista. 
                
                    }
                    nodo_tienda.setId(e.y);
                    mapa.nodo_actual.agregarNodo(nodo_tienda);
                }
                if(aleatorio==2 || aleatorio==3 || aleatorio==4){  //CREACION DE NODOS EVENTO 30%probablididad
                    int invNumItEv1=r.nextInt((nodo_tienda.inventario.size()-1))+1;  
                    int invNumItEv2=r.nextInt((nodo_tienda.inventario.size()-1))+1;
                    nodo_evento=new NodoEvento(e.y,"RECOMPENSAS!!! ","item 1","item 2",nodo_tienda.inventario.get(invNumItEv1),nodo_tienda.inventario.get(invNumItEv2));
                    nodo_evento.setId(e.y);
                    mapa.nodo_actual.agregarNodo(nodo_evento);//terminada la construccion de nodo se almacena en la lista. 
                    
                }
                if(aleatorio==5 || aleatorio==6 || aleatorio==7 || aleatorio==8 || aleatorio==9 || aleatorio==10){  //CREACION DE NODOS  COMBATE 60%probabilidad
                    //estadiscticas aleatorias para personaje
                    int l=r.nextInt(12-10+1)+10; 
                    int m=r.nextInt(12-10+1)+10;
                    if (l<m){
                        l=m;
                    }
                    int n=r.nextInt(3-1+1)+3;         
                    int o=r.nextInt(2-1+1)+1;
                    Personaje p = new Personaje(np.getNombresPJ(),0,l,m,n,o); //0 es el dinero del personaje, ya que los personajes no ocupan dinero en el juego.
                    if (np.getLargoNombresPJ()==0){
                        p.setNombre("PJ");
                    }
                    NodoCombate nodo_combate=new NodoCombate(0,p);
                    nodo_combate.setId(e.y);
                    mapa.nodo_actual.agregarNodo(nodo_combate);  //terminada la construccion de nodo se almacena en la lista.            
                }
            }
            
                      
        }
    }
}