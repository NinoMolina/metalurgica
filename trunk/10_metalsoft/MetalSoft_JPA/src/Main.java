
import controller.ClienteJpaController;
import controller.exceptions.NonexistentEntityException;
import entity.Cliente;
import entity.Pedido;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nino
 */
public class Main {

    public static void main(String args[]){
        ClienteJpaController controller=new ClienteJpaController();
        List<Cliente> list=controller.findClienteEntities();
        Cliente cliente=null;
        for (Iterator<Cliente> it = list.iterator(); it.hasNext();) {
            cliente = it.next();
            System.out.println(cliente.getRazonsocial());
            System.out.println(cliente.getDomicilio().getCalle());
            Set<Pedido> set=cliente.getPedidoSet();
            for (Iterator<Pedido> it1 = set.iterator(); it1.hasNext();) {
                Pedido pedido = it1.next();
                System.out.println("\t--"+pedido.getIdpedido()+", "+pedido.getFechapedidocotizacion());
            }
        }
        cliente.setRazonsocial("MATERIALFERROVIARIO SRL");
        try {
            controller.edit(cliente);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
