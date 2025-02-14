package ui;

import model.Gallery;
import model.Drawing;

import java.util.Scanner;
import java.util.List;
import java.awt.Color;

// An application interface that allows users to modify and interact with their gallery via the console
public class TerminalApp {

    private Gallery gallery;
    private Drawing currentDrawing;

    private Scanner scanner;
    private Boolean showMainMenu;
    private Boolean showEditMenu;
    private Boolean mainMenuCurrentlyShowing;
    private Boolean editMenuCurrentlyShowing;

    /*
     * EFFECTS: creates an instance of the TerminalApp ui application
     */
    public TerminalApp() {
        init();

        printDivider();
        System.out.print("Welcome to your art gallery application!\n");

        while (showMainMenu || showEditMenu) {
            if (showMainMenu) {
                handleMainMenu();
            } else if (showEditMenu) {
                handleEditMenu();
            }
        }
    }


    /*
     * MODIFIES: this
     * EFFECTS: initializes the application with the starting values
     */
    public void init() {
        gallery = new Gallery();
        scanner = new Scanner(System.in);
        showMainMenu = true;
        showEditMenu = false;
        mainMenuCurrentlyShowing = false;
        editMenuCurrentlyShowing = false;
    }

    /*
     * EFFECTS: display and process inputs for the main menu
     */
    public void handleMainMenu() {
        if (!mainMenuCurrentlyShowing) {
            displayMainMenu();
            mainMenuCurrentlyShowing = true;
            editMenuCurrentlyShowing = false;
        }
        String input = scanner.nextLine();
        processMainMenuInputs(input);
    }

    /*
     * EFFECTS: display a list of commans that can be used in the main menu
     */
    public void displayMainMenu() {
        printDivider();
        System.out.println("Main Menu");
        printDivider();
        System.out.println("Please select an option:\n");
        System.out.println("s: View a summary of the gallery");
        System.out.println("v: View the entire gallery");
        System.out.println("c: View completed drawings");
        System.out.println("p: View in-progress drawings");
        System.out.println("a: Add a new drawing");
        System.out.println("e: Edit or delete an existing drawing");
        System.out.println("r: Refresh menu");
        System.out.println("q: Quit application");
        printDivider();
    }

    /*
     * EFFECTS: process the user's input in the main menu
     */
    @SuppressWarnings("methodlength")
    public void processMainMenuInputs(String input) {
        switch (input) {
            case "s":
                displaySummary();
                break;
            case "v":
                displayDrawings(gallery.getDrawingList());
                break;
            case "c":
                displayDrawings(gallery.getCompleteList());
                break;
            case "p":
                displayDrawings(gallery.getInProgressList());
                break;
            case "a":
                addDrawing();
                break;
            case "e":
                showMainMenu = false;
                showEditMenu = true;
                break;
            case "r":
                mainMenuCurrentlyShowing = false;
                break;
            case "q":
                showMainMenu = false;
        }
    }

    /*
     * EFFECTS: display and process input for the edit menu
     */
    public void handleEditMenu() {
        if (!editMenuCurrentlyShowing) {
            displayEditMenu();
            editMenuCurrentlyShowing = true;
            mainMenuCurrentlyShowing = false;
        }
        String input = scanner.nextLine();
        processEditMenuInputs(input);
    }

    /*
     * EFFECTS: display a list of commands that can be used in the edit menu
     */
    public void displayEditMenu() {
        printDivider();
        System.out.println("Edit Menu");
        printDivider();
        if (currentDrawing == null) {
            System.out.println("No drawing is currently selected.");
        } else {
            System.out.println("The currently selected drawing is: \n");
            System.out.println(currentDrawing.toString());
        }
        printDivider();
        System.out.println("Please select an option:\n");
        System.out.println("s: Change the selected drawing");
        System.out.println("t: Edit selected drawing title");
        System.out.println("d: Edit selected drawing dimensions");
        System.out.println("c: Edit selected drawing color");
        System.out.println("m: Mark selected drawing as complete (irreversible)");
        System.out.println("x: Delete selcted drawing");
        System.out.println("r: Refresh menu");
        System.out.println("q: Return to main menu");
        printDivider();
    }

    /*
     * EFFECTS: process the user's input in the edit menu
     */
    @SuppressWarnings("methodlength")
    public void processEditMenuInputs(String input) {
        switch (input) {
            case "s":
                selectDrawing();
                editMenuCurrentlyShowing = false;
                break;
            case "t":
                editTitle();
                break;
            case "d":
                editDimensions();
                break;
            case "c":
                editColor();
                break;
            case "m":
                markAsComplete();
                break;
            case "x":
                delete();
                break;
            case "r":
                editMenuCurrentlyShowing = false;
                break;
            case "q":
                showMainMenu = true;
                showEditMenu = false;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a drawing to the gallery, checking that the title is unique and rbg colors are valid
     */
    public void addDrawing() {
        printDivider();
    
        System.out.print("Enter title: ");
        String title = getValidNewTitle();

        System.out.print("Enter width in pixels: ");
        int width = getValidDimension();

        System.out.print("Enter height in pixels: ");
        int height = getValidDimension();

        System.out.print("Enter red RGB value: ");
        int red = getValidColorValue();

        System.out.print("Enter green RGB value: ");
        int green = getValidColorValue();

        System.out.print("Enter blue RGB value: ");
        int blue = getValidColorValue();

        gallery.addDrawing(new Drawing(title, width, height, new Color(red, green, blue)));

        System.out.println("Drawing added successfully!");

        printDivider();
    }

    /*
     * MODIFIES: this, currentDrawing
     * EFFECTS: changes the dimensions of currentDrawing based on user input
     */
    public void editDimensions() {
        printDivider();
        if (currentDrawing == null) {
            System.out.println("You must select a drawing before editing.");
        } else if (currentDrawing.getStatus().equals("Complete")) {
            System.out.println("Drawings marked as complete cannot be edited.");
        } else {
            System.out.println("Enter new width in pixels: ");
            int width = getValidDimension();

            System.out.println("Enter new height in pixels: ");
            int height = getValidDimension();

            currentDrawing.setWidth(width);
            currentDrawing.setHeight(height);

            System.out.println("Dimensions edited successfully!");
        }
        printDivider();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Change the title of currentDrawing based on user input
     */
    public void editTitle() {
        printDivider();
        if (currentDrawing == null) {
            System.out.println("You must select a drawing before editing.");
        } else if (currentDrawing.getStatus().equals("Complete")) {
            System.out.println("Drawings marked as complete cannot be edited.");
        } else {
            System.out.println("Enter new title: ");
            String title = getValidNewTitle();

            currentDrawing.setTitle(title);

            System.out.println("Title edited successfully!");
        }
        printDivider();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Change the color of currentDrawing based on user input
     */
    public void editColor() {
        printDivider();
        if (currentDrawing == null) {
            System.out.println("You must select a drawing before editing.");
        } else if (currentDrawing.getStatus().equals("Complete")) {
            System.out.println("Drawings marked as complete cannot be edited.");
        } else {
            System.out.print("Enter new red RGB value: ");
            int red = getValidColorValue();
    
            System.out.print("Enter new green RGB value: ");
            int green = getValidColorValue();
    
            System.out.print("Enter new blue RGB value: ");
            int blue = getValidColorValue();

            currentDrawing.setColor(new Color(red, green, blue));

            System.out.println("Color edited successfully!");
        }
        printDivider();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Marks the currentDrawing as complete if it has not been done already
     */
    public void markAsComplete() {
        printDivider();
        if (currentDrawing == null) {
            System.out.println("You must select a drawing before editing.");
        } else if (currentDrawing.getStatus().equals("Complete")) {
            System.out.println("Drawing has already been marked as complete.");
        } else {
            currentDrawing.markAsComplete();
            System.out.println("Drawing marked as complete!");
        }
        printDivider();
    }

    /*
    * MODIFIES: this
    * EFFECTS: removes currentDrawing from the gallery
    */
    public void delete() {
        printDivider();
        if (currentDrawing == null) {
            System.out.println("You must select a drawing to delete.");
        } else {
            gallery.removeDrawing(currentDrawing);
            currentDrawing = null;
            System.out.println("Drawing successfully removed!");
        }
    }

    /*
     * EFFECTS: displays string representations of every drawing in gallery
     */
    public void displayDrawings(List<Drawing> drawingList) {
        printDivider();
        for (Drawing drawing : drawingList) {
            System.out.println(drawing.toString());
        }
        printDivider();
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes currentDrawing to the drawing with the corresponding title if it exists, 
     *          else does not modify currentDrawing
     */
    public void selectDrawing() {
        printDivider();
        System.out.println("Enter the title of the drawing to select: ");
        String title = scanner.nextLine();

        if (gallery.containsDrawingWithTitle(title)) {
            currentDrawing = gallery.getDrawing(title);
            System.out.println("Selected drawing has been updated.");
        } else {
            System.out.println("Could not find a drawing with a matching title.");
        }
        printDivider();
    }

    /*
    * EFFECTS: prints a summary of the status of the drawings in the gallery
    */
    public void displaySummary() {
        printDivider();
        System.out.println(String.format("There are a total of %d drawings in the gallery.", 
                            gallery.getDrawingList().size()));
        System.out.println(String.format("%d drawings are complete.", 
                            gallery.getCompleteList().size()));
        System.out.println(String.format("%d drawings are in progress.", 
                            gallery.getInProgressList().size()));
        printDivider();
    }

    /*
     * EFFECTS: gets an integer input between 0-255 from the user
     */
    public int getValidColorValue() {
        int value = -1;       

        do {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 0 and 255: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value < 0 || value > 255) {
                System.out.println("Invalid input. Please enter a number between 0 and 255: ");
            }

        } while (value < 0 || value > 255);

        return value;
    }

    /*
    * EFFECTS: get an integer input > 0 from the user
    */
    public int getValidDimension() {
        int value = -1;       

        do {  
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number greater than 0: ");
                scanner.next();
                continue;
            }

            value = scanner.nextInt();

            if (value < 0) {
                System.out.println("Invalid input. Please enter a number greater than 0: ");
            }

        } while (value < 0);

        return value;
    }

    /*
     * EFFECTS: get a new unique title from the user
     */
    public String getValidNewTitle() {
        String title;

        do {
            title = scanner.nextLine();

            if (gallery.containsDrawingWithTitle(title)) {
                System.out.println("Gallery already contains a drawing with that title. Please try again: ");
            }

        } while (gallery.containsDrawingWithTitle(title));

        return title;
    }

    /*
     * EFFECTS: prints out a line of dashes to act as a divider
     */
    public void printDivider() {
        System.out.println("---------------------------------------------------------------------");
    }

}
