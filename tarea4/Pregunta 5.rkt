#lang scheme
(current-namespace (make-base-namespace))

(define (umbral_cola lista umbral tipo)
  (cond
    [(equal? tipo #\M)
     (let mayor ((l1 lista) (l2 '()) (cont 0))
       (cond
             [(null? l1) l2]
             [(> (car l1) umbral)  (mayor (cdr l1) (append l2 (list cont)) (+ cont 1)) ]
             [(<= (car l1) umbral) (mayor (cdr l1) l2 (+ cont 1)) ]))]
  
    [(equal? tipo #\m) 0
     (let menor ((l1 lista) (l2 '()) (cont 0))
       (cond
             [(null? l1) l2]
             [(< (car l1) umbral)  (menor (cdr l1) (append l2 (list cont)) (+ cont 1)) ]
             [(>= (car l1) umbral) (menor (cdr l1) l2 (+ cont 1)) ]))]))

(define (modsel_cola lista seleccion f)
  (let menor ((l1 lista) (l2 seleccion) (cont 0) (ln '()))
    (cond
      [(null? l2) l1]
      [(null? l1) (menor (reverse ln) (cdr l2) 0 '())]
      [(not(= cont (car l2))) (menor (cdr l1) l2 (+ cont 1) (append (list(car l1)) ln))]
      [(= cont (car l2)) (menor (cdr l1) l2 (+ cont 1) (append (list((eval f)(car l1))) ln))])))


(define (estables lista umbral fM fm)
  (list (length (umbral_cola (modsel_cola lista (umbral_cola lista umbral #\M) fM) umbral #\M )) (length (umbral_cola (modsel_cola lista (umbral_cola lista umbral #\m) fm) umbral #\m ))) )
  

(define (query lista pos op params)
   (cond
        [(= op 1) 
             (cond
               [(= pos 0)(umbral_cola (car lista) (car params) (car (cdr params)))]
               [(= pos 1)(umbral_cola (car(cdr lista)) (car params) (car (cdr params)))]
               [(= pos 2)(umbral_cola (car(cdr(cdr lista))) (car params) (car (cdr params)))])]

        [(= op 2)
             (cond
               [(= pos 0) (modsel_cola (car lista) (car params) (car (cdr params)))]
               [(= pos 1) (modsel_cola (car(cdr lista)) (car params) (car (cdr params)))]
               [(= pos 2) (modsel_cola (car(cdr(cdr lista))) (car params) (car (cdr params)))])]
          
        [(= op 3) 
             (cond 
               [(= pos 0)(estables (car lista) (car params) (car(cdr params)) (car(cdr(cdr params))))]
               [(= pos 1)(estables (car (cdr lista)) (car params) (car(cdr params)) (car(cdr(cdr params))))]
               [(= pos 2)(estables (car(cdr(cdr lista))) (car params) (car(cdr params)) (car(cdr(cdr params))))]
        )])) 


