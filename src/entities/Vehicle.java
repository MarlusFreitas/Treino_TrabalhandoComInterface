package entities;

//Criando o objeto do carro para poder até mesmo consultar disponibilidade fultura
public class Vehicle {
    private String model;

    public Vehicle(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
