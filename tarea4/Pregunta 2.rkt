#lang scheme


;;DESCRIPCION DE LA FUNCION
;; Funcion recorrerM : aqui obtenemos la posicion de los elementos mayores que el umbral ,
;;Si el primer elemento de la lista es mayor que el umbral , se añade el elemento a listanueva. 
;;Si el primer elemento de la lista  es menor o igual que el umbral , no se añade el elemento a listanueva , entonces (cdr lista) para que el primer elemento cambie.
;;siempre que se evalue una condicion hace un contador++, a menos que lista este vacia, en ese caso se retorna la listanueva.

;;DESCRIPCION PARAMETROS
;; lista : la lista a trabajar
;; umbral: umbral a evaluar en cada elemento de la lista
;; contador : un contador en 0
;; lista nueva : una lista vacia

(define(recorrerM lista umbral contador listanueva)
  (cond
    [(null? lista) listanueva]
    [(> (car lista) umbral) (recorrerM (cdr lista) umbral (+ contador 1) (append listanueva (list contador)) )] 
     [(<= (car lista) umbral) (recorrerM (cdr lista) umbral (+ contador 1) listanueva )]
  ))


;;DESCRIPCION DE LA FUNCION
;; Funcion recorrerm : aqui obtenemos la posicion de los elementos menores que el umbral ,
;;Si el primer elemento de la lista es menor que el umbral , se añade el elemento a listanueva. 
;;Si el primer elemento de la lista  es mayor o igual que el umbral , no se añade el elemento a listanueva , entonces (cdr lista) para que el primer elemento cambie.
;;siempre que se evalue una condicion hace un contador++, a menos que lista este vacia, en ese caso se retorna la listanueva.

;;DESCRIPCION PARAMETROS
;; lista : la lista a trabajar
;; umbral: umbral a evaluar en cada elemento de la lista
;; contador : un contador en 0
;; lista nueva : una lista vacia

(define(recorrerm lista umbral contador listanueva)
  (cond
    [(null? lista) listanueva]
    [(< (car lista) umbral) (recorrerm (cdr lista) umbral (+ contador 1) (append listanueva (list contador)) )] 
     [(>= (car lista) umbral) (recorrerm (cdr lista) umbral (+ contador 1) listanueva )]
  ))


(define (umbral_simple lista umbral tipo)
  (cond
    [(equal? tipo #\M) (recorrerM lista umbral 0 '())] ; RecorrerM se le envia la lista con el elemento -1 añadido, en el retorno de este , la lista posee un -1 al principio seguido de las posiciones, entonces hacemos cdr.
    [(equal? tipo #\m) (recorrerm lista umbral 0 '())]; Recorrerm se le envia la lista con el elemento -1 añadido, en el retorno de este , la lista posee un -1 al principio seguido de las posiciones, entonces hacemos cdr.
))





(define (umbral_cola lista umbral tipo)
  (cond
    [(equal? tipo #\M)
     (let mayor ((l1 lista) (l2 '()) (cont 0))
       (cond
             [(null? l1) l2]
             [(> (car l1) umbral)  (mayor (cdr l1) (append l2 (list cont)) (+ cont 1)) ]
             [(<= (car l1) umbral) (mayor (cdr l1) l2 (+ cont 1)) ]))]
  
    [(equal? tipo #\m) 
     (let menor ((l1 lista) (l2 '()) (cont 0))
       (cond
             [(null? l1) l2]
             [(< (car l1) umbral)  (menor (cdr l1) (append l2 (list cont)) (+ cont 1)) ]
             [(>= (car l1) umbral) (menor (cdr l1) l2 (+ cont 1)) ]))]))
         

