package servidor;

import java.io.IOException;
import java.net.SocketException;


import comun.MyStreamSocket;
import gestor.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Clase ejecutada por cada hebra encargada de servir a un cliente del servicio de viajes.
 * El metodo run contiene la logica para gestionar una sesion con un cliente.
 */

class HiloServidorViajes implements Runnable {


    private MyStreamSocket myDataSocket;
    private GestorViajes gestor;

    /**
     * Construye el objeto a ejecutar por la hebra para servir a un cliente
     * @param	myDataSocket	socket stream para comunicarse con el cliente
     * @param	unGestor		gestor de viajes
     */
    HiloServidorViajes(MyStreamSocket myDataSocket, GestorViajes unGestor) {
        // POR IMPLEMENTAR
        this.myDataSocket = myDataSocket;
        gestor = unGestor;
    }

    /**
     * Gestiona una sesion con un cliente
     */
    public void run( ) {
        String operacion = "0";
        boolean done = false;
        // ...
        try {
            while (!done) {
                // Recibe una petición del cliente
                String peticion = myDataSocket.receiveMessage();
                // Extrae la operación y sus parámetros
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = (JSONObject) parser.parse(peticion);
                operacion = jsonObject.get("peticion").toString();
                switch (operacion) {
                    case "0":
                        gestor.guardaDatos();
                        done = true;
                        myDataSocket.close();
                        break;

                    case "1": { // Consulta los viajes con un origen dado
                        String origen = (String) jsonObject.get("origen");
                        JSONArray viajes = gestor.consultaViajes(origen);
                        System.out.println(viajes.toJSONString());
                        myDataSocket.sendMessage(viajes.toJSONString());
                        break;
                    }
                    case "2": { // Reserva una plaza en un viaje
                        String codviaje = (String) jsonObject.get("codviaje");
                        String codcli = (String) jsonObject.get("codcliente");
                        JSONObject viaje = gestor.reservaViaje(codviaje, codcli);
                        myDataSocket.sendMessage(viaje.toJSONString());
                        break;
                    }
                    case "3": { // Pone en venta un articulo
                        String codViaje = (String) jsonObject.get("codviaje");
                        String codCliente = (String) jsonObject.get("codcliente");

                        JSONObject viaje = gestor.anulaReserva(codViaje, codCliente);
                        myDataSocket.sendMessage(viaje.toJSONString());
                        break;

                    }
                    case "4": { // Oferta un viaje
                        String codprop = (String) jsonObject.get("codprop");
                        String origen = (String) jsonObject.get("origen");
                        String destino = (String) jsonObject.get("destino");
                        String fecha = (String) jsonObject.get("fecha");
                        Long precio = (Long) jsonObject.get("precio");
                        Long numplazas = (Long) jsonObject.get("numplazas");

                        JSONObject viaje = gestor.ofertaViaje(codprop, origen, destino, fecha, precio, numplazas);
                        myDataSocket.sendMessage(viaje.toJSONString());
                        break;
                    }
                    case "5": { // Borra un viaje
                        String codViaje = (String) jsonObject.get("codviaje");
                        String codCliente = (String) jsonObject.get("codcliente");

                        JSONObject viaje = gestor.borraViaje(codViaje, codCliente);
                        myDataSocket.sendMessage(viaje.toJSONString());
                        break;
                    }

                } // fin switch
            } // fin while
        } // fin try
        catch (SocketException ex) {
            System.out.println("Capturada SocketException");
        }
        catch (IOException ex) {
            System.out.println("Capturada IOException");
        }
        catch (Exception ex) {
            System.out.println("Exception caught in thread: " + ex);
            ex.printStackTrace();
        } // fin catch
    } //fin run

} //fin class