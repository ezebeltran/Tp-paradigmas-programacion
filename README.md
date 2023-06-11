# Tp-paradigmas-programacion

Este trabajo tiene como objetivo afianzar la práctica de la
Programación Orientada a Objetos. En particular, de los
mecanismos de herencia, polimorfismo, clases abstractas e
interfaces.

# Turismo en la Tierra Media

La secretaría de turismo de la Tierra Media ha decidido crear un sistema para promocionar
el turismo en su territorio. Éste contará con la información de las distintas atracciones de
toda la Tierra Media.
El sistema deberá ser capaz de sugerir visitas a partir de la ubicación de los visitantes y
también de generar itinerarios a partir de la información de preferencias disponible en el
perfil de los usuarios. Se espera que los usuarios puedan descargarse una aplicación móvil
que les permita interactuar con el sistema y los vaya guiando en su recorrido.

Dado el alcance del sistema y la limitación de tiempo, varios equipos han sido contratados
para el desarrollo de este sistema.

En el caso de su equipo, debe implementar la lógica para sugerir visitas e itinerarios
respetando las siguientes especificaciones:

* Cada atracción cuenta con su costo de visita, el promedio de tiempo necesario para
realizarla, el cupo de visitantes diarios y el tipo de atracción (paisaje, de aventura, de
degustación).
* Por su parte, para cada usuario el sistema conoce su presupuesto, el tiempo
disponible para visitas y el tipo de atracción preferida.
* En la generación de las sugerencias deben contemplarse las promociones vigentes.
Cada promoción incluye una o varias atracciones y beneficia al usuario con una
reducción del costo total. Se espera que el sistema permita la definición de
promociones de tres tipos:
  * Promociones porcentuales (X % de descuento en el costo total)
  * Promociones absolutas ($ X por todo el paquete)
  * Promociones A x B (si el usuario compra A,B,C entonces tiene gratis D).


# Diagrama de Clases

![diagrama de clases](https://github.com/ezebeltran/Tp-paradigmas-programacion/blob/e250a65bd9d84236c45e9c7097463f6c15e824cd/TierraMedia.drawio.svg)
