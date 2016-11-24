package modelo;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.MockitoAnnotations;

public class VendaTest {

    public VendaTest() {
    }

    private CarrinhoCompras carrinho;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        carrinho = new CarrinhoCompras();
    }

    @Test
    public void setCarrinho() {
        Venda venda = new Venda();
        venda.setCarrinho(carrinho);

        assertEquals(carrinho, venda.getCarrinho());
    }
}
