package model.tickets;

public class Standard implements Ticket{
    private static final double PRICE = 8;

    @Override
    public double getPrice() {
        return PRICE;
    }
}
