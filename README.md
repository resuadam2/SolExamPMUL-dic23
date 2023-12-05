# Enunciado

Crear una aplicación Android usando el IDE Android Studio y el lenguaje de programación Java. 

En la actividad principal de la aplicación se debe mostrar dos botones, uno para cada ejercicio. Al pulsar sobre uno de los botones se debe abrir una nueva actividad que nos lleve al ejercicio correspondiente.

## Ejercicio 1: Albums Wrapped 2023

Este ejercicio trata de elaborar una aplicación que nos muestre la información de los álbumes más relevantes que han salido en este 2023.

Para ello, debemos elaborar una base de datos que almacene el nombre de cada álbum, el nombre del artista o grupo, el número de canciones que contiene y la valoración de 1 a 5 estrellas que le damos a cada álbum.

En la actividad principal de esta aplicación se debe mostrar un listado con todos los álbumes que tenemos en la base de datos. Al pulsar sobre uno de ellos se debe abrir una nueva actividad que nos muestre la información del álbum seleccionado y nos permita modificar la valoración.

También es necesario que si hacemos una pulsación larga sobre el álbum nos aparezca un cuadro de diálogo que nos pregunte si queremos eliminar el álbum de la base de datos.

Para la elaboración de esta aplicación se debe usar una base de datos SQLite.

### Creación de la base de datos

Al crear la base de datos deben insertarse al menos 2 álbumes de vuestra preferencia junto con uno de ellos siempre debe ser el siguiente:
 - Álbum: 'Me muevo con Dios'
 - Artista: 'Cruz Cafune'
 - Número de canciones: 23
 - Valoración: 5 estrellas

### Creación de la actividad principal

En la actividad principal, como ya se adelantó, se debe mostrar un listado con todos los álbumes que tenemos en la base de datos.

Además de esto, debe ser posible ordenar los álbumes en base a su nombre, nombre del artista o grupo, número de canciones o valoración. 
Esto se hará siempre pulsando sobre el título de la columna que queremos ordenar. Si pulsamos sobre el título de la columna que ya está ordenada, se debe invertir el orden de la lista, debe indicarse con una flecha arriba si el orden es ascendente o abajo si es descendente.

El orden por defecto debe ser por nombre del álbum (A-Z). Además, el orden debe mantenerse al volver a la actividad principal, para ello se debe guardar el orden en las preferencias de la aplicación.

## Ejercicio 2: Carta Alta

Este ejercicio trata de elaborar una aplicación que nos permita jugar a la carta alta. 

Para ello, debemos elaborar un fragmento que funcione como un componente independiente y reutilizable que nos permita robar una carta de la baraja española.

En la actividad principal de esta aplicación debe haber la opción de iniciar una nueva partida, con tantas cartas como el usuario haya insertado en un EditText.

Al pulsar sobre el botón de iniciar una neuva partida, se generarán tantos fragmentos como cartas se hayan indicado en el EditText. Cada fragmento nos permitirá robar una carta aleatoria de la baraja española, mostrando su número y palo.

Cada vez que robemmos una carta de un fragmento, este debe dejar de permitir robar más cartas. 

Cuándo hayamos robado todas las cartas, se debe mostrar un mensaje indicando quién ha ganado la partida, es decir, quién tiene la carta más alta.

*NOTA*: Debemos controlar el que todos los fragmentos funcionen como si estuviesemos en realidad robando de la misma baraja. Es decir, no puede haber dos fragmentos que nos muestren la misma carta.

### Creación del fragmento

Debemos crear un fragmento que nos permita robar una carta de la baraja española. Para ello, debemos crear un layout que contenga un botón para robar y un modo debug.

Si el modo debug está activado, se debe mostrar el número y palo de la carta robada. Además de dos spinner o selectores (uno para el número y otro para el palo) que nos permitan elegir la carta robada. 

En este ejemplo trabajaremos siempre con el modo debug activado.

