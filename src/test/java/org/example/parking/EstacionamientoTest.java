package org.example.parking;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.fail;

import static org.junit.Assert.assertNotNull;
import org.junit.Before;

public class EstacionamientoTest {

    private Estacionamiento estacionamiento;

    @Before
    public void setUp() {
        estacionamiento = new Estacionamiento();
    }


    @Test
    public void testRetirarVehiculo() throws Exception {
        //TODO test
        Vehiculo v = new Vehiculo("SALIR1", "DUSTER", Vehiculo.Tipo.SUV);
        estacionamiento.ingresarVehiculo("5555", "Leticia", v);


        Ticket ticket = estacionamiento.retirarVehiculo("SALIR1");
        assertNotNull(ticket);
        assertNotNull(ticket.getHoraSalida());
        assertEquals("Leticia", ticket.getCliente().getNombre());
    }

   public void testRetiroVehiculoInexistente() {
        try {
            estacionamiento.retirarVehiculo("NOEXISTE");
            fail("Esperaba una excepción por vehículo inexistente");
        } catch (Exception e) {
            assertEquals("Vehiculo no encontrado", e.getMessage());
        }
    }



    @Test
    public void testCalcularPrecio() throws Exception {
        // TODO test
        Cliente cliente = new Cliente("9999", "MAURICIO");
        Vehiculo vehiculo = new Vehiculo("AA676XM", "CRONOS", Vehiculo.Tipo.AUTO);
        Ticket ticket = new Ticket(cliente, vehiculo);


        ticket.marcarSalida();

        long minutos = ticket.calcularMinutos();
        double precio = ticket.calcularPrecio();

        long horasRedondeadas = (long) Math.ceil(minutos / 60.0);
        double tarifaEsperada = horasRedondeadas * 100;

        assertEquals(tarifaEsperada, precio, 0.001);
    }



}