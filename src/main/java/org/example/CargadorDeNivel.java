package org.example;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import javax.xml.XMLConstants;
import java.io.InputStream;
import java.io.ByteArrayInputStream;


public class CargadorDeNivel {

    public Nivel cargarNivel(InputStream archivoxml, InputStream archivoxsd) throws Exception{
        System.out.println("Cargando nivel: " + archivoxml);

        if (archivoxml == null) {
            throw new IllegalArgumentException("No se pudo encontrar el archivo XML " );
        }
        if (archivoxsd == null) {
            throw new IllegalArgumentException("No se pudo encontrar el archivo XSD ");
        }
        byte[] xmlBytes = archivoxml.readAllBytes();

        // Validar usando un ByteArrayInputStream independiente
        validarXMLconXSD(new ByteArrayInputStream(xmlBytes), archivoxsd);

        // Parsear usando otro ByteArrayInputStream
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(xmlBytes));
        document.getDocumentElement().normalize();


        Nivel nivel = new Nivel(2);

        // 1. Leer dimensiones del nivel
        Element root = document.getDocumentElement(); // <levelConfig>
        NodeList levels = root.getElementsByTagName("level");
        Element nivelElem = (Element) levels.item(0); // Primer <level>

        double anchoNivelPx = Integer.parseInt(nivelElem.getAttribute("width"));   // ej: 800
        double altoNivelPx  = Integer.parseInt(nivelElem.getAttribute("height"));    // ej: 600
        double numFilas     = Integer.parseInt(nivelElem.getAttribute("rows"));     // ej: 30
        double numColumnas  = Integer.parseInt(nivelElem.getAttribute("cols"));  // ej: 40

        double anchoCelda = anchoNivelPx / numColumnas;  // ej: 800 / 40 = 20
        double altoCelda  = altoNivelPx / numFilas;      // ej: 600 / 30 = 20

        // 4. Cargar bloques
        NodeList bloques = document.getElementsByTagName("staticObject");
        for (int i = 0; i < bloques.getLength(); i++) {
            Element elem = (Element) bloques.item(i);

            String tipo = elem.getAttribute("type");
            int coordenadax = Integer.parseInt(elem.getAttribute("x"));
            int coordenaday = Integer.parseInt(elem.getAttribute("y"));

            double fila = coordenaday / altoCelda;
            double columna = coordenadax / anchoCelda;


            Bloque bloque = CreadorDeBloque.crearBloque(tipo, columna, fila);

            nivel.agregarBloque(bloque);
        }

        // 5. Cargar jugadores
        NodeList jugadores = document.getElementsByTagName("player");
        for (int i = 0; i < jugadores.getLength(); i++) {
            Element elem = (Element) jugadores.item(i);

            double coordenadax = Integer.parseInt(elem.getAttribute("x"));
            double coordenaday = Integer.parseInt(elem.getAttribute("y"));

            double fila = coordenaday / altoCelda;
            double columna = coordenadax / anchoCelda;
            /*otra forma de sumar jugador*/
            Jugador jugador = new Jugador("Jugador1", columna, fila, 2);
            nivel.agregarJugador(jugador);
            /*
            Tanque jugador = new Tanque(columna, fila, 0);
            nivel.agregarJugador(jugador);*/
        }

        // 6. Cargar enemigos
        NodeList enemigos = document.getElementsByTagName("enemy");
        for (int i = 0; i < enemigos.getLength(); i++) {
            Element elem = (Element) enemigos.item(i);

            int coordenadax = Integer.parseInt(elem.getAttribute("x"));
            int coordenaday = Integer.parseInt(elem.getAttribute("y"));

            double fila = coordenaday / altoCelda;
            double columna = coordenadax / anchoCelda;
            /*otra forma de sumar enemigo*/
            Enemigo enemigo = new Enemigo(columna, fila, 2, 2000);
            nivel.agregarEnemigo(enemigo);
            /*
            Tanque enemigo = new Tanque(columna, fila, 0);
            nivel.agregarEnemigo(enemigo);*/
        }
        return nivel;
    }

        private void validarXMLconXSD(InputStream xml, InputStream xsd) throws Exception {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema= factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));

    }



}
