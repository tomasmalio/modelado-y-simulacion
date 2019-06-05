# Modelado y Simulación
## Descripción
Desarrollo de programa para:
- **Método de Euler**, un procedimiento de integración numérica para resolver ecuaciones diferenciales ordinarias a partir de un valor inicial dado. Euler es el más simple de los métodos numéricos para resolver un problema de valor inicial.
- **Método de Euler Mejorado**, es igual al método de euler, pero hace un refinamiento en la aproximación, tomando un promedio entre ciertas pendientes.

## Información
**Profesor:** Fernando Acero

**Autor:** Tomás Malio 

**Año:** 2019

## Paquetes adicionales utilizados
**XChart:**
- https://knowm.org/open-source/xchart/xchart-example-code/
- https://github.com/knowm/XChart

**MathParser:**
- http://mathparser.org/

## Documentación del desarrollo
### Método de Euler
```
dx(t) / dt = f(x,t) en a <= t <= b
```
*Con la condición inicial x(a) = xo*

Desde el programa, se ingresa la **función** que se desea analiar, el parámetro **a**, **b**, y el **Xo**. A su vez, se define el valor de **n**, que es el intervalo por el cual a dividir. Por último, se ingresa el valor de **k**, el cual la función depende de cuando ingresemos la función.

Generamos la separación entre los puntos a través de **h** que se obtiene de:
```
h = (b - a) / n
```
Recorremos hasta el **n** definido en los parámetros ingresados a través de la fórmula:
```
Xn = Xn-1 + YXn-1 * h
```
### Método de Euler Mejorado
```
dx(t) / dt = f(x,t) en a <= t <= b
```
*Con la condición inicial x(a) = xo*

Desde el programa, se ingresa la **función** que se desea analiar, el parámetro **a**, **b**, y el **Xo**. A su vez, se define el valor de **n**, que es el intervalo por el cual a dividir. Por último, se ingresa el valor de **k**, el cual la función depende de cuando ingresemos la función.

Generamos la separación entre los puntos a través de **h** que se obtiene de:
```
h = (b - a) / n
```
Recorremos hasta el **n** definido en los parámetros ingresados a través de la fórmula:
```
Xn = Xn-1 + (1/2) * (F(Xn-1, t) + F(Xn, t)) * h
```

## Adicional
Importante a la hora de cargar una función, debemos recordar que los nombres son en inglés:
- Seno: **(sin)**
- Coseno: **(cos)**
- Tangente: **(tan)**
- Exponencial: **(^)**
- Multiplicación: **(*)**

Ejemplos:
- f(x,t)= sin(x^2)+t
