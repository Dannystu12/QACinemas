package model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MovieShowing {

    private String name;
    private LocalDateTime dateTime;
    private CinemaScreen screen;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public MovieShowing(String movieName, LocalDateTime datetime, CinemaScreen screen){
        this.name = movieName;
        this.dateTime = datetime;
        this.screen = screen;
    }

    public LocalDateTime getShowingDateTime(){return dateTime;}

    /**
     * Produces string which displays summary information about the movie showing
     * @return the String value represnting a movie showing
     */
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(this.name);
        result.append(" @ ");
        result.append(dateTime.format(formatter));
        result.append(" in Cinema ");
        result.append(screen.toString());
        return result.toString();
    }
}
