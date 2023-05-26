COSAS A RECALCAR:
1-Inicialize a jugador_1 ocupando item_0 , un item vacio con estadisticas al 0. Esto no cambia nada en el Juego, es como si no existiera.
2-Todo esta en una carpeta, por el hecho de que mi visual arrojaba errores al utilizar carpetas. 
3-class NombresPJ contiene una lista de 51 "nombres" para los nuevos Personajes que se crean. Al utilizar por completo esta lista , los personajes siguentes tendrán el nombre PJ.
4-Existe la posiblidad/probabilidad de que en NodoEvento, 2 de los items mostrados sean iguales, ya que estos se extraen del inventario de nodo tienda actual.
5- Al aplicar un item al jugador, primero se le recupera la vida actual, sin que sobrepase la vida total, y después se le suma la vida hp_total.
6- Unico problema, la accion de ver mapa para cuando se esta en el nodo Jefe Final, no esta bien implementada.
7- Las estadisticas de jefe_final son mas fuertes que las de un personaje común.
8- Intentar porfavor ingresar todos los datos correctamente cuando se piden.

EJECUCION DE CODIGO:
el makefile es el mismo que está publicado en aula.
Se accede a la carpeta donde están los archivos.java, la carpeta src.
Comandos:
make run      Se compila y ejectua el codigo.
make clean    Para limpiar los archivos .class  (rm -rf Edge.class GraphGenerator.class Items.class JavaQuest.class Mapa.class Nodo.class Personaje.class NombresPJ.class NodoCombate.class NodoEvento.class NodoInicial.class NodoJefeFinal.class NodoTienda.class Jugador.class)

