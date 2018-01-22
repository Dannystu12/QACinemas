package ui;

import model.CinemaScreen;
import model.MovieShowing;
import model.Order;
import model.tickets.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<MovieShowing> availableMovies;
    private static Scanner input;
    private static Order order;


    public static void main(String[] args) {
        input = new Scanner(System.in);
        order = new Order();
        populateMovieList();
        System.out.println("Welcome to QA Cinemas!");
        getMovie();
        getAttendees();
        displayOrderSummary();

    }

    /**
     * Adds movies to the selection of available movies
     */
    private static void populateMovieList(){

        ArrayList<MovieShowing> movieShowings = new ArrayList<>();

        //Movie showing on a Friday
        MovieShowing starWars;
        starWars = new MovieShowing("Star Wars: The Last Jedi",
                LocalDateTime.of(2018,2,2,19,0,0),
                CinemaScreen.A);

        //Movie showing on a Wednesday
        MovieShowing coco;
        coco = new MovieShowing("Coco",
                LocalDateTime.of(2018,1,31,20,30,0),
                CinemaScreen.F);

        //Add movies to available list
        movieShowings.add(starWars);
        movieShowings.add(coco);
        availableMovies = movieShowings;
    }


    /**
     * Asks the user to select a movie from those available and add the movie to the order
     */
    private static void getMovie(){
        String response;

        while(true){
            System.out.println("Which film would you like to see?");

            //List available movies and corresponding key to select
            for(int i = 0; i < availableMovies.size(); i++){
                System.out.println("\tPress " + (i+1) + " for: " + availableMovies.get(i));
            }
            response = input.nextLine();

            //Convert user selection to integer to select chosen movie from list
            try{
                int movieIndex = Integer.parseInt(response);
                if(movieIndex > 0 && movieIndex <= availableMovies.size()){
                    order.setMovie(availableMovies.get(movieIndex - 1));
                    return;
                }
                System.out.println("Error: Invalid number\n");
            }catch (NumberFormatException e) {
                System.out.println("Error: Please select a valid number\n");
            }
        }
    }

    /**
     * Asks a user to select attendees and adds them to the order
     */
    private static void getAttendees(){

        String response;

        //Build list of possible attendee classes
        ArrayList<Ticket> possibleAttendees = new ArrayList<>();
        possibleAttendees.add(new Standard());
        possibleAttendees.add(new Student());
        possibleAttendees.add(new Child());
        possibleAttendees.add(new OAP());

        HashMap<Ticket, Integer> attendees = new HashMap<>();
        while(true){

            System.out.println("\nCurrent Attendees: ");

            //List current attendees
            for(Ticket attendee : attendees.keySet()){
                System.out.println("\t" + String.format("%-10s",attendee.getClass().getSimpleName())
                + " x" + String.format("%2s",attendees.get(attendee)));
            }

            System.out.println();
            System.out.println("Please select attendees...");

            //Present options for selection
            for(int i = 0; i < possibleAttendees.size(); i++){
                System.out.println("\tPress " + (i+1) + " for " + possibleAttendees.get(i).getClass().getSimpleName()
                        + " ticket" );
            }
            System.out.println("\tPress c to confirm selection");
            System.out.println("\tPress r to reset selection");


            response = input.nextLine();

            //Check if user is finished adding attendees
            if(response.equalsIgnoreCase("c")){
                if(attendees.size() == 0){
                    System.out.println("Error: No attendees selected\n");
                    continue;
                }
                order.setAttendees(attendees);
                return;
            }

            //Check if user wishes to start adding attendees again
            if(response.equalsIgnoreCase("r")){
                attendees.clear();
                System.out.println("Attendees reset successfully");
                continue;
            }

            //Convert user selection to integer to select chosen ticket
            try{
                int attendeeIndex = Integer.parseInt(response);
                if(attendeeIndex > 0 && attendeeIndex <= possibleAttendees.size() ){
                    //Add/increment ticket count
                    attendees.put(possibleAttendees.get(attendeeIndex -1),
                            attendees.getOrDefault(possibleAttendees.get(attendeeIndex -1),0)+1);

                } else {
                    System.out.println("Error: Invalid number\n");
                }
            }catch (NumberFormatException e) {
                System.out.println("Error: Please select a valid number\n");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Allow user to review order and choose to confirm it or cancel it
     */
    private static void displayOrderSummary(){
        String response;

        while(true){
            System.out.println("\nPlease check your order below:");
            //Fetch order summary String
            System.out.println(order.getSummary());
            System.out.println("Press c to confirm selection");
            System.out.println("Press x to cancel order");

            response = input.nextLine();

            if(response.equalsIgnoreCase("c")){
                System.out.println("You will now be directed to our payment portal. Thank you for using QA Cinemas!");
                return;
            }

            if(response.equalsIgnoreCase("x") ){
                System.out.println("Your order has been cancelled. We hope to see you soon!");
                return;
            }

            System.out.println("Error: please select a valid response\n");
        }
    }
}
