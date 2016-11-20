package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bebida;
import modelo.CarrinhoCompras;
import modelo.Cliente;
import modelo.Endereco;
import modelo.Pedido;
import modelo.Prato;
import modelo.Produto;
import modelo.dao.BebidaDAO;
import modelo.dao.ClienteDAO;
import modelo.dao.DAOFactory;
import modelo.dao.EnderecoDAO;
import modelo.dao.PedidoDAO;
import modelo.dao.PratoDAO;
import modelo.dao.ProdutoDAO;

public class ServletControle extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String caminho = request.getServletPath();
        if (caminho.equals("/cliente/adicionar")) {

            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                EnderecoDAO dao_endereco = factory.criarEnderecoDAO();
                Endereco endereco = new Endereco();
                endereco.setEndereco(request.getParameter("endereco"));
                endereco.setNumero(request.getParameter("numero"));
                endereco.setBairro(request.getParameter("bairro"));
                endereco.setCep(request.getParameter("cep"));
                endereco.setEstado(request.getParameter("estado"));
                endereco.setCidade(request.getParameter("cidade"));
                endereco.setComplemento(request.getParameter("complemento"));
                dao_endereco.gravar(endereco);

                ClienteDAO dao_cliente = factory.criarClienteDAO();
                Cliente cliente = new Cliente();
                cliente.setCpf(request.getParameter("cpf"));
                cliente.setNome(request.getParameter("nome"));
                cliente.setData(request.getParameter("data"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setIdEndereco(endereco.getIdEndereco());
                dao_cliente.gravar(cliente);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        } else if (caminho.equals("/produtos/bebida/adicionar")) {

            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                BebidaDAO dao_bebida = factory.criarBebidaDAO();
                Bebida bebida = new Bebida();
                bebida.setCodigo_bebida(Integer.parseInt(request.getParameter("codigo_bebida")));
                bebida.setNome(request.getParameter("nome_bebida"));
                bebida.setDescricao(request.getParameter("descricao_bebida"));
                bebida.setPreco(Double.parseDouble(request.getParameter("preco_bebida")));
                dao_bebida.gravar(bebida);

                ProdutoDAO dao_produto = factory.criarProdutoDAO();
                Produto produto = new Produto();
                produto.setProduto_Bebida_codigo(bebida.getCodigo_bebida());
                produto.setPreco(bebida.getPreco());
                produto.setNome(bebida.getNome());
                dao_produto.gravar_bebida(produto);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        } else if (caminho.equals("/produtos/prato/adicionar")) {

            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                PratoDAO dao_prato = factory.criarPratoDAO();
                Prato prato = new Prato();
                prato.setCodigo_prato(Integer.parseInt(request.getParameter("codigo_prato")));
                prato.setNome(request.getParameter("nome_prato"));
                prato.setDescricao(request.getParameter("descricao_prato"));
                prato.setPreco(Double.parseDouble(request.getParameter("preco_prato")));
                dao_prato.gravar(prato);

                ProdutoDAO dao_produto = factory.criarProdutoDAO();
                Produto produto = new Produto();
                produto.setProduto_Prato_codigo(prato.getCodigo_prato());
                produto.setPreco(prato.getPreco());
                produto.setNome(prato.getNome());
                dao_produto.gravar_prato(produto);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        } else if (caminho.equals("/produtos/comprar")) {
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();

                BebidaDAO dao_bebida = factory.criarBebidaDAO();
                List<Bebida> bebidas = dao_bebida.buscarTodos();
                request.setAttribute("bebidas", bebidas);

                PratoDAO dao_prato = factory.criarPratoDAO();
                List<Prato> pratos = dao_prato.buscarTodos();
                request.setAttribute("pratos", pratos);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/efetuarVenda.jsp");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }else if (caminho.equals("/carrinho/bebida/adicionar")) {
            CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new CarrinhoCompras();
            }

            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            long codigo = Long.parseLong(request.getParameter("codigo"));
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                ProdutoDAO dao = factory.criarProdutoDAO();
                Produto produto = dao.buscar_bebida(codigo);
                carrinho.adicionarItem(quantidade, produto);
                request.getSession().setAttribute("carrinho", carrinho);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/produtos/comprar");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }else if (caminho.equals("/carrinho/prato/adicionar")) {
            CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");
            if (carrinho == null) {
                carrinho = new CarrinhoCompras();
            }

            int quantidade = Integer.parseInt(request.getParameter("quantidade"));
            long codigo = Long.parseLong(request.getParameter("codigo"));
            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                ProdutoDAO dao = factory.criarProdutoDAO();
                Produto produto = dao.buscar_prato(codigo);
                carrinho.adicionarItem(quantidade, produto);
                request.getSession().setAttribute("carrinho", carrinho);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/produtos/comprar");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }else if (caminho.equals("/vendas/finalizar")) {
            CarrinhoCompras carrinho = (CarrinhoCompras) request.getSession().getAttribute("carrinho");

            Pedido pedido = new Pedido();
            pedido.setDataCompra("20/11/2016");
            pedido.setItens(carrinho.getItens());

            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                PedidoDAO dao = factory.criarPedidoDAO();
                dao.gravar(pedido);
                carrinho.esvaziar();
                request.getSession().setAttribute("carrinho", carrinho);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        } else if (caminho.equals("/vendas/listar")) {

            DAOFactory factory = new DAOFactory();
            try {
                factory.abrirConexao();
                PedidoDAO dao = factory.criarPedidoDAO();
                List<Pedido> pedidos = dao.buscarTodos();
                request.getSession().setAttribute("pedidos", pedidos);

                RequestDispatcher rd = null;
                rd = request.getRequestDispatcher("/listarVendas.jsp");
                rd.forward(request, response);

            } catch (SQLException ex) {
                System.out.println("Erro no acesso ao banco de dados.");
                DAOFactory.mostrarSQLException(ex);
            } finally {
                try {
                    factory.fecharConexao();
                } catch (SQLException ex) {
                    System.out.println("Erro ao fechar a conexão com o BD.");
                    DAOFactory.mostrarSQLException(ex);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
