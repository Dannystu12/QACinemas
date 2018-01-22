package model;

import model.tickets.Ticket;

import java.time.DayOfWeek;
import java.util.Map;

public class Order {
    private MovieShowing movie;

    //Attendees Map represents a type of ticket along with the quantity in the order
    private Map<Ticket, Integer> attendees;
    private static final int WEDNESDAY_DISCOUNT = 2;

    public void setMovie(MovieShowing movie){this.movie = movie;}
    public MovieShowing getMovie() {return movie;}
    public void setAttendees(Map<Ticket, Integer> attendees) {this.attendees = attendees;}

    /**
     * Calculates order pricing and returns a summary of the order
     * @return summary String  which presents attendees and prices along with a total price
     */
    public String getSummary(){
        StringBuilder summary = new StringBuilder();

        //Check if discount should be applied
        boolean applyDiscount = movie.getShowingDateTime().getDayOfWeek() == DayOfWeek.WEDNESDAY;

        if(applyDiscount){
            summary.append("Order qualifies for Wednesday Discount!");
        }

        double ticketPrice;
        int qty;
        double totalPrice = 0;
        for(Ticket t : attendees.keySet()){
            summary.append("\n\t");
            summary.append(String.format("%-10s", t.getClass().getSimpleName()));
            ticketPrice = applyDiscount ? t.getPrice() - WEDNESDAY_DISCOUNT : t.getPrice();
            summary.append(String.format("£%5.2f", ticketPrice));
            summary.append(" x ");
            qty = attendees.get(t);
            summary.append(String.format("%2s", qty));
            summary.append(" = ");
            summary.append(String.format("£%5.2f", ticketPrice * qty));
            totalPrice += ticketPrice * qty;
        }

        summary.append("\nTOTAL PRICE: ");
        summary.append(String.format("£%5.2f", totalPrice ));

        return summary.toString();

    }
}
