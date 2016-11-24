package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    public ClienteTest() {
    }

    @Test
    public void testGetSetCpf() {
        System.out.println("getCpf");
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        assertEquals("12345678901", cliente.getCpf());
    }

    @Test
    public void testGetSetNome() {
        System.out.println("getNome");
        Cliente cliente = new Cliente();
        cliente.setNome("Jacque");
        assertEquals("Jacque", cliente.getNome());   
    }

    @Test
    public void testGetSetData() {
        System.out.println("getData");
        Cliente cliente = new Cliente();
        cliente.setData("20/11/2016");
        assertEquals("20/11/2016", cliente.getData());
    }

    @Test
    public void testGetSetEmail() {
        System.out.println("getEmail");
        Cliente cliente = new Cliente();
        cliente.setEmail("jacque@gmail.com");
        assertEquals("jacque@gmail.com", cliente.getEmail());
    }

    @Test
    public void testGetSetSenha() {
        System.out.println("getSenha");
        Cliente cliente = new Cliente();
        cliente.setSenha("12345");
        assertEquals("12345", cliente.getSenha());
    }

    @Test
    public void testGetSetIdEndereco() {
        System.out.println("getIdEndereco");
        Cliente cliente = new Cliente();
        cliente.setIdEndereco(10);
        assertEquals(10, cliente.getIdEndereco());
    }

}
