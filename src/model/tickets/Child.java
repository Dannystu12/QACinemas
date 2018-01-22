package model.tickets;

public class Child implements Ticket{
    private static final double PRICE = 4;

    @Override
    public double getPrice() {
        return PRICE;
    }
}
