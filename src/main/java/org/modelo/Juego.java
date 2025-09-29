package org.modelo;


import java.util.ArrayList;
import java.util.List;

public class Juego {
    private List<Nivel> niveles;
    private int nivelActual;
    private CargadorDeNivel cargadordenivel;
    private List<Jugador> jugadoresActivos;

    public Juego() throws Exception {
        niveles = new ArrayList<>();
        nivelActual = 0;
        /*aca cargar los niveles con cargador de niveles*/
        cargadordenivel = new CargadorDeNivel();

        Nivel nivel1 = cargadordenivel.cargarNivel("nivel1.xml","levelConfig.xsd");
        Nivel nivel2 = cargadordenivel.cargarNivel("nivel2.xml","levelConfig.xsd");
        Nivel nivel3 = cargadordenivel.cargarNivel("nivel3.xml","levelConfig.xsd");

        niveles.add(nivel1);
        niveles.add(nivel2);
        niveles.add(nivel3);
    }

    public void iniciarJuego() {
        while (nivelActual < niveles.size()) {

            Nivel nivel = niveles.get(nivelActual);
            nivel.activarJugadores(2);
            cargarJugadores(2);






            System.out.println("Inicio  de nivel " +nivelActual);
            /*while(nivel.enCurso()){
                //aca irian los inputs de el/los jugadores
                //nivel.actualizar(movimientos)



            }*/
            if (nivel.terminoEnVictoria()){
                System.out.println("Termino en Victoria el nivel " + nivelActual+1);
                nivelActual++;
            }
            else if(nivel.terminoEnDerrota()){
                System.out.println("Termino en Derrota el nivel " + nivelActual+1);
                break;/*regreso al menu*/
            }
        }
        if (nivelActual == niveles.size()) {
            System.out.println("Termino el juego con todos los niveles ganados");
        }
    }

    public void actualizarJuego() {
        Nivel nivel = niveles.get(nivelActual);
        nivel.actualizar();
    }


    public void terminarJuego(){
        /*termina el juego, libera recursos, guarda progreso, etc*/
    }

    public void cargarJugadores(int cantidad) {
        jugadoresActivos = new ArrayList<>();
        for (int i = 0; i < cantidad ; i++) {
            jugadoresActivos.add(niveles.get(nivelActual).getJugadores().get(i));
        }
    }


}
