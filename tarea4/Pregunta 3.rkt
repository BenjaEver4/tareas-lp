#lang scheme
(current-namespace (make-base-namespace))

;;DESCRIPCION DE LA FUNCION
;; Funcion recorrer :Aplica lambda en determinados elementos de la lista.

;;DESCRIPCION PARAMETROS
;; lista : la lista con elementos.
;; seleccion: lista que contiene las posiciones, donde se aplicara lambda
;; f : es el lambda que se le aplica a los respectivos elementos de la lista.
;; listanueva: es la lista al aplicarse lambda en las respectivas posiciones.
;;contador : contador que empieza desde 0.
(define (recorrer lista seleccion f listanueva contador)
  (cond
    [(null? lista) (modsel_simple (reverse listanueva) (cdr seleccion) f)] 
    [(null? seleccion) 0]
    [(not(null? seleccion)) (cond
                              [(= (car seleccion) contador)  (recorrer (cdr lista) seleccion f (append (list((eval f)(car lista))) listanueva) (+ contador 1))]
                              [(not(= (car seleccion) contador)) (recorrer (cdr lista) seleccion f (append (list(car lista)) listanueva) (+ contador 1))])
                               ]))

(define (modsel_simple lista seleccion f)
  (cond
    [(null? seleccion) lista]
    [(not (null? seleccion)) (recorrer lista seleccion f '() 0)])) 





(define (modsel_cola lista seleccion f)
  (let menor ((l1 lista) (l2 seleccion) (cont 0) (ln '()))
    (cond
      [(null? l2) l1]
      [(null? l1) (menor (reverse ln) (cdr l2) 0 '())]
      [(not(= cont (car l2))) (menor (cdr l1) l2 (+ cont 1) (append (list(car l1)) ln))]
      [(= cont (car l2)) (menor (cdr l1) l2 (+ cont 1) (append (list(f(car l1))) ln))])))
































