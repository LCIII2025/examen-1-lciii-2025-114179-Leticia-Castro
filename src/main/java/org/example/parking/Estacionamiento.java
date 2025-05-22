package org.example.parking;

import java.util.*;

public class Estacionamiento {
    private final int capacidadMaxima = 50;
    private final Map<String, Ticket> vehiculosEstacionados = new HashMap<>();
    private final Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public boolean ingresarVehiculo(String dni, String nombre, Vehiculo vehiculo) {
        // TODO implementar la logica para registrar el ingreso de un nuevo vehiculo en el parking

        if (vehiculosEstacionados.size() >= capacidadMaxima) {
            return false;
        }

        if (vehiculosEstacionados.containsKey(vehiculo.getPatente())) {
            return false;
        }

        Cliente cliente = clientesRegistrados.get(dni);
        if (cliente == null) {
            cliente = new Cliente(dni, nombre);
            clientesRegistrados.put(dni, cliente);
        }
        cliente.agregarVehiculo(vehiculo);

        Ticket ticket = new Ticket(cliente, vehiculo);

        vehiculosEstacionados.put(vehiculo.getPatente(), ticket);


        return true;
    }

    public Ticket retirarVehiculo(String patente) throws Exception {
        // TODO implementar la lógica para retirar un vehiculo del parking
        // validar que exista la patente, caso contrario arrojar la exception "Vehiculo no encontrado"
        // calcular y retornar el ticket del vehiculoEstacionado (ver Ticket.marcarSalida())
        Ticket ticket = vehiculosEstacionados.get(patente);
        if (ticket == null) {
            throw new Exception("Vehículo no encontrado");
        }
        ticket.marcarSalida();
        vehiculosEstacionados.remove(patente);
        return ticket;
    }

    public List<Ticket> listarVehiculosEstacionados() {
        return new ArrayList<>(vehiculosEstacionados.values());
    }
}
