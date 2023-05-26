
%Acotada recibe: L una lista, [X|Y] que proviene del mismo L anterior,RM rango maximo variable o numero,
%Rm rango minimo variable o numero , Min variable o numero y Max variable o numero.
%Acotada encuentra el intervalo en que esta la lista L ,que es el siguente[Rm-RM).
acotada(L,[],RM,Rm,Min,Max):- not(integer(Min)),not(integer(Max)), Min is Rm, Max is RM+1 ,Z=[], contar(Min,Max,Z,L).
acotada(L,[X|Y],RM,Rm,Min,Max):- X>RM ,X<Rm ,acotada(L,Y,X,X,Min,Max).
acotada(L,[X|Y],RM,Rm,Min,Max):- X>RM ,X >= Rm ,acotada(L,Y,X,Rm,Min,Max).
acotada(L,[X|Y],RM,Rm,Min,Max):- X =< RM, X >= Rm , acotada(L,Y,RM,Rm,Min,Max).
acotada(L,[X|Y],RM,Rm,Min,Max):- X =< RM, X < Rm , acotada(L,Y,RM,X,Min,Max).


%Contar recibe: Min variable o numero , Max variable o numero , R la lista de elementos , L una lista.
%Contar genera una lista de todos los elementos entre el intervalo [Min,Max).
contar(Min,Max,R,L):- Min = Max ,not(member(Max,L)) ,revisar(L,R,R,Min,Max).
contar(Min,Max,R,L):-  Min<Max , Num is Min+1,contar(Num,Max,[Min|R],L).

%Revisar recibe una lista[X|Y] proveniente de L, y una lista R que es la lista con los elementos.
%Revisar revisa que todos los elementos de la lista L pertenezcan a lista R. 
revisar(L,R,[],_,_):- revisar2(L,R). 
revisar(L,R,[X|Y],Min,Max):-  member(X,L) , revisar(L,R,Y,Min,Max).

%Revisar2 recibe una lista[X|Y] proveniente de R, y una lista L que es la lista principal.
%Revisar revisa que todos los elementos de la lista R pertenezcan a la lista L. 
revisar2([],R).
revisar2([X|Y],R):- member(X,R), revisar2(Y,R).
%rangomax recibe: L una lista , Min una variable o numero , Max una variable o numero.
%Genera una lista de todos los elementos dentro del intervalo
rangomax(L,Min,Max):- not(integer(Min)) , not(integer(Max)), acotada(L,L,0,1000,Min,Max) ; R=[],contar(Min,Max,R,L).