package org.modelo;

public class Main {
    public static void main(String[] args) throws Exception {
      Juego juego = new Juego();
      juego.iniciarJuego();

       /* try {
            CargadorDeNivel cargador = new CargadorDeNivel();

            // Busca el archivo XML y XSD en resources/niveles
            InputStream xml = Main.class.getClassLoader()
                    .getResourceAsStream("nivel_de_prueba.xml");
            InputStream xsd = Main.class.getClassLoader()
                    .getResourceAsStream("levelConfig.xsd");

            Nivel nivel = cargador.cargarNivel(xml, xsd);


            System.out.println("Jugadores:");
            for (Tanque j : nivel.getJugadores()) {
                System.out.println("- " + j.getClass().getSimpleName()
                        + " en (" + j.obtenerPosicion().obtenerCoordenadaX()
                        + "," + j.obtenerPosicion().obtenerCoordenadaY() + ")");
            }

            System.out.println("Enemigos:");
            for (Enemigo e : nivel.getEnemigos()) {
                System.out.println("- " + e.getClass().getSimpleName()
                        + " en (" + e.obtenerPosicion().obtenerCoordenadaX()
                        + "," + e.obtenerPosicion().obtenerCoordenadaY() + ")");
            }

            System.out.println("Bloques en la grilla:");
            for (int f = 0; f < nivel.getFilas(); f++) {
                for (int c = 0; c < nivel.getColumnas(); c++) {
                    Posicion pos = new Posicion(f, c);
                    Bloque b = nivel.getBloqueEnPosicion(pos);
                    if (b != null) {
                        System.out.println("- " + b.getClass().getSimpleName()
                                + " en (" + f + "," + c + ")");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
