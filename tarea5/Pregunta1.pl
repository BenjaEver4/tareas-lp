%Inversa, recibe 1 lista y una variable , esta variable se instanciará a la lista invertida.

inversa([],[]).
inversa([X|L1],L):-inversa(L1,Resto), append(Resto,[X],L).

%sepparimpar recibe 1 lista, una variable P , una variable I. 
%P se instanciará a la lista de pares de la lista.
%I se instanciará a la lista de impares de la lista.
sepparimpar(L,P,I) :- par(L,[],[],P, I).

%par recibe una lista de elementos que se parte en cabeza(X) y cola (Y)  , M una lista  , N una lista vacia ,P una variable,I una variable.
%par obtiene los elementos pares de la lista y los almacena en la lista M,
%esta lista se invertira ,para que P se instancie a ella.
par([],M,N,P,I):- inversa(N,Z),inversa(M,H),P=H,I=Z,!.
par([X|Y],M,N,P,I):- impar(Y,[X|M],N,P,I).

%impar recibe una lista de elementos que se parte en cabeza(X) y cola (Y) , M una lista  , N una lista ,P una variable,I una variable.
%impar obtiene los elementos impares de la lista y los almacena en la lista N,
%esta lista se invertira ,para que I se instancie a ella.
impar([],M,N,P,I):- inversa(N,Z),inversa(M,H),P=H,I=Z,!.
impar([X|Y],M,N,P,I):- par(Y,M,[X|N],P,I).
