package alugueldecarros_inclusaodeinterface;

import entities.CarRental;
import entities.Invoice;
import entities.Vehicle;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import model.service.BrazilianTax;
import model.service.RentalService;

public class Program {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("Entre com os dados do aluguel");
        System.out.print("Modelo do carro: ");
        String carName = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Entre com o preco por hora: ");
        double hora = sc.nextDouble();
        System.out.print("Entre com o preco por dia: ");
        double dia = sc.nextDouble();
        
        CarRental cr = new CarRental(start, finish, new Vehicle(carName));
        
        RentalService rs = new RentalService(hora, dia, new BrazilianTax());
        
        rs.processInvoice(cr);
        
        System.out.println("FATURA:");
        System.out.println("Pagamento basico: " + cr.getInvoice().getBasicPayment());
        System.out.println("Imposto: " + cr.getInvoice().getTax());
        System.out.println("Pagamento total: " + cr.getInvoice().totalPayment());
        
        
        sc.close();
        
    }
    
}
