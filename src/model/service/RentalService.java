package model.service;

import entities.CarRental;
import entities.Invoice;
import java.time.Duration;


//Serviço para gerar a fatura com o imposto
public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;

    private BrazilianTax tax;

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilianTax tax) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.tax = tax;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public BrazilianTax getTax() {
        return tax;
    }

    public void setTax(BrazilianTax tax) {
        this.tax = tax;
    }
    
    public void processInvoice(CarRental cr){
        double minutes = Duration.between(cr.getStart(), cr.getFinish()).toMinutes();
        double hours = minutes/60;
        double basicPayment;
        if( hours <= 12){
            basicPayment = pricePerHour * Math.ceil(hours);
        }else{
            basicPayment = pricePerDay * Math.ceil(hours/24);
        }
        
        double tx = tax.tax(basicPayment);
        cr.setInvoice(new Invoice(basicPayment, tx));
    }
}
