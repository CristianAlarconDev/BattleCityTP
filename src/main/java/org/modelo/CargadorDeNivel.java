package org.modelo;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import javax.xml.XMLConstants;
import java.io.File;


public class CargadorDeNivel {

    public NivelModel cargarNivel(String archivoxml, String archivoxsd, int cantidadDeJugadores, String nombre1, String nombre2) throws Exception{
        System.out.println("Cargando nivel: " + archivoxml);

        var resourcexml = getClass().getResource("/" + archivoxml);
        var resourcexsd = getClass().getResource("/" + archivoxsd);

        if (resourcexml == null) {
            throw new IllegalArgumentException("No se pudo encontrar el archivo XML " );
        }
        if (resourcexsd == null) {
            throw new IllegalArgumentException("No se pudo encontrar el archivo XSD ");
        }

        File xmlFile = new File(resourcexml.getPath());
        File xsdFile = new File(resourcexsd.getPath());

        validarXMLcocoordenadaXActualSD(xmlFile, xsdFile);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        NivelModel nivel = new NivelModel(800, 600);


        Element root = document.getDocumentElement(); // <levelConfig>
        Element nivelElem = (Element) root.getElementsByTagName("level").item(0);


        Element playersContainer = (Element) nivelElem.getElementsByTagName("players").item(0);
        Element enemiesContainer = (Element) nivelElem.getElementsByTagName("enemies").item(0);
        Element staticObjectsContainer = (Element) nivelElem.getElementsByTagName("staticObjects").item(0);

        // Jugadores
        NodeList playerNodes = playersContainer.getElementsByTagName("player");
        for (int i = 0; i < cantidadDeJugadores; i++) {
            Node nodo = playerNodes.item(i);
            if (nodo.getNodeType() != Node.ELEMENT_NODE) continue;
            Element elem = (Element) nodo;
            int xPx = Integer.parseInt(elem.getAttribute("x"));
            int yPx = Integer.parseInt(elem.getAttribute("y"));
            if (i==0){
                Jugador jugador = new Jugador(nombre1, xPx, yPx, 2);
                nivel.agregarJugador(jugador);
            }
            else {
                Jugador jugador = new Jugador(nombre2, xPx, yPx, 2);
                nivel.agregarJugador(jugador);
            }
        }

        // Enemigos
        NodeList enemyNodes = enemiesContainer.getChildNodes();
        for (int i = 0; i < enemyNodes.getLength(); i++) {
            Node nodo = enemyNodes.item(i);
            if (nodo.getNodeType() != Node.ELEMENT_NODE) continue;
            Element elem = (Element) nodo;
            String tipo = elem.getAttribute("type");
            int xPx = Integer.parseInt(elem.getAttribute("x"));
            int yPx = Integer.parseInt(elem.getAttribute("y"));
            Enemigo enemigo = CreadorDeEnemigo.crearEnemigo(tipo, xPx, yPx);
            nivel.agregarEnemigo(enemigo);
        }

        // Bloques
        NodeList blockNodes = staticObjectsContainer.getChildNodes();
        for (int i = 0; i < blockNodes.getLength(); i++) {
            Node nodo = blockNodes.item(i);
            if (nodo.getNodeType() != Node.ELEMENT_NODE) continue;
            Element elem = (Element) nodo;
            String tipo = elem.getAttribute("type");
            int xPx = Integer.parseInt(elem.getAttribute("x"));
            int yPx = Integer.parseInt(elem.getAttribute("y"));
            Bloque bloque = CreadorDeBloque.crearBloque(tipo, xPx, yPx);
            nivel.agregarBloque(bloque);
        }
        return nivel;
    }

    private void validarXMLcocoordenadaXActualSD(File xml, File xsd) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema= factory.newSchema(xsd);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));

    }

}
