package reventa;
import java.time.LocalDateTime;
import usuarios.Cliente;
public class Contraoferta {
       
        public enum Estado { Pendiente, Aceptada, Rechazada }
    
        private final Cliente comprador;
        private final double nuevoPrecio;
        private final LocalDateTime fecha;
        private Estado estado;
    
        public Contraoferta(Cliente comprador, double nuevoPrecio) {
            this.comprador = comprador;
            this.nuevoPrecio = nuevoPrecio;
            this.fecha = LocalDateTime.now();
            this.estado = Estado.Pendiente;
        }
    
        public Cliente getComprador() { return comprador; }
        public double getNuevoPrecio() { return nuevoPrecio; }
        public LocalDateTime getFecha() { return fecha; }
        public Estado getEstado() { return estado; }
        public void setEstado(Estado estado) { this.estado = estado; }
    


    }



