#lang scheme
;;DESCRIPCION DE LA FUNCION
;; Funcion Recorrer : aqui invertimos la lista ,
;;Si el primer elemento de la lista no es igual a contador , se añade el elemento a listanueva. 
;;Si el primer elemento de la lista es igual a contador , no se añade el elemento a lista nueva , entonces (cdr lista) para que el primer elemento cambie.
;;siempre que se evalue una condicion hace un contador++, a menos de que contador sea igual a n ahí se retorna la lista invertida.

;;DESCRIPCION PARAMETROS
;; lista : lista a invertir
;; n : ultimo elemento de la lista vacia sin incluir
;; contador : un contador en 0
;; lista nueva : una lista vacia



(define (recorrer lista n listanueva contador)

  (cond
    [(= n contador) listanueva]
    [(and (not(null? lista)) (not(= n contador)))
     (cond
       [(= (car lista) contador) (recorrer (cdr lista) n listanueva (+ contador 1)) ]
       [(not(= (car lista) contador)) (recorrer lista n (append listanueva (list contador)) (+ contador 1)) ])]

     [(null? lista) (recorrer lista n (append listanueva (list contador)) (+ contador 1)) ])) 

(define (inverso lista n)
  (recorrer lista n '() 0)) 
