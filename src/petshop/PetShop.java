package petshop;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class simulates a PetShop which allows user to add new pets to the
 * collection and display them too
 *
 * @author
 */
public class PetShop {

    // Collection to store all types of Pet i.e. Mammal, Bird, Fish
    ArrayList<Pet> pets;

    // For taking input from the user
    Scanner inputSource = new Scanner(System.in);

    /**
     * Constructor to read serialized data from the data file 
     *
     */
    PetShop() {
        pets = readFromFile();
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        PetShop demo = new PetShop();

        demo.menu();
    }

    /**
     * This method is used to give interface options to the user or whether to
     * add new pet or to display the pets
     */
    private void menu() {

        // running the loop for menu
        while (true) {

            // Greeting and then displaying different menu options to the user
            System.out.print("\n\n*********************************** ");
            System.out.print("Welcome to Pet Shop menu");
            System.out.println(" ***********************************");
            System.out.println("1- Add a new pet to the collection");
            System.out.println("2- Display all Pets i.e. mammals, birds and fish");
            System.out.println("3- Display all Pets in a price range");
            System.out.println("0- Exit");

            System.out.print("\nYour choice: ");
            // Taking user's choice for what he/she wants to do
            String s = inputSource.next();
            int choice = Integer.parseInt(s);
            // Calling operations according to choice
            switch (choice) {
                // if choice is 1 go to addNewPet method for letting user to add a new pet
                case 1:
                    addNewPet();
                    break;
                // If choice is 2 go to call the displayAllPets method to list details of all pets i.e. Mammals, Birds, Fishes
                case 2:
                    displayAllPets();
                    break;
                // If choice is 2 go to call the displayAllPets method to list details 
                //of all pets i.e. Mammals, Birds, Fishes in a specific price range
                case 3:
                    displayAllPetsInRange();
                    break;
                // If choice is 0, write existing pet data to the file and display a message to end the program
                case 0:
                    writeToFile();
                    System.out.println("\n***************** Thank you for using our program, quitting. *****************");
                    System.exit(0);
                    break;

                // Otherwise, notify that input was invalid
                default:
                    System.out.println("Invalid choice. \n\n");
            }
        }
    }

    /**
     * This method allows user to chose what pet type they want to add to the
     * collections
     */
     void addNewPet() {
        System.out.println("Please select what type of pet you want to add...\n");
        System.out.println("1- For adding Mammal");
        System.out.println("2- For adding bird ");
        System.out.println("3- For adding fish");
        System.out.println("0- Go back to main menu");

        System.out.print("\nYour choice: ");
        // Taking user's choice for what he/she wants to do
        String s = inputSource.next();
        int choice = Integer.parseInt(s);
        // Calling operations according to choice
        switch (choice) {
            // if choice is 1, call addPet method for adding a Mammal
            case 1:
                addPet("Mammal");

                break;
            // if choice is 2, call addPet method for adding a Bird
            case 2:
                addPet("Bird");
                break;
            // if choice is 3, call addPet method for adding a Fish
            case 3:
                addPet("Fish");
                break;
            // If choice is 0, display a message and go back to the main menu
            case 0:
                return;

            // Otherwise, notify that input was invalid
            default:
                System.out.println("Invalid choice. \n\n");
        }

    }

    /**
     * This method takes type of Pet to figure out what type of Pet to take
     * input from
     *
     * @param type string to represent what pet type to add
     */
     void addPet(String type) {

        try {

            // Taking generic inputs for any pet i.e.e Specie and their price
            System.out.print("Enter specie of the pet  e.g. dog, goldfish  : ");
            String species = inputSource.next();
            System.out.print("Enter price of the pet: ");
            double price = inputSource.nextDouble();

            // If type is mammal, take inputs accordingly
            if (type.equalsIgnoreCase("mammal")) {

                // create a Mammal object
                Mammal m = new Mammal();

                // taking required inputs for the Mammal
                System.out.print("Enter type of bread for mammal: ");
                String breadType = inputSource.next();

                System.out.print("Enter list of vaccinations for mammal seperated by comma i.e. rabies,distemper : ");
                String vaccination = inputSource.next();

                // convering vaccine inputs into a list
                ArrayList<String> vaccinationTypes = new ArrayList(Arrays.asList(vaccination.split(",")));

                // Setting up data for Mammal object
                m.setBread(breadType);
                m.setPrice(price);
                m.setSpecies(species);
                m.setVaccinations(vaccinationTypes);

                // Adding new Mammal object to the pets collection
                this.pets.add(m);
            } // If type is bird, take inputs accordingly
            else if (type.equalsIgnoreCase("bird")) {

                // create a Bird object
                Bird b = new Bird();

                // taking required inputs for the Bird
                System.out.print("Enter minimum cage dimensions for bird e.g. “82cmx44cmx40cm”: ");
                String cageDims = inputSource.next();
                // convering food inputs into a list

                System.out.print("Enter list of foods for bird seperated by comma : ");
                String food = inputSource.next();

                // convering food inputs into a list
                ArrayList<String> birdFoods = new ArrayList(Arrays.asList(food.split(",")));

                // Setting up data for Bird object
                b.setCageDims(cageDims);
                b.setFoods(birdFoods);
                b.setPrice(price);
                b.setSpecies(species);

                // Adding new Bird object to the pets collection
                this.pets.add(b);
            } // If type is fish, take inputs accordingly
            else if (type.equalsIgnoreCase("fish")) {
                // create a Fish object
                Fish f = new Fish();

                // taking required inputs for the Bird
                System.out.print("Enter minimum tank volume in litres for a single fish e.g. 100: ");
                double tankVol = inputSource.nextDouble();

                System.out.print("Enter tank volume for small school i.e. 6-8 fishes : ");
                int num = inputSource.nextInt();

                // Setting up data for Fish object
                f.setFishNumInSchool(num);
                f.setMinTankVolInLitres(tankVol);
                f.setSpecies(species);
                f.setPrice(price);

                // Adding new Fish object to the pets collection
                this.pets.add(f);
            } // Otherwise display error message and return to main menu
            else {
                System.out.println("Invalid Pet type !!");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input, returning to main menu.");
            return;
        }

    }

    /**
     * This method is used to display all pets in the collection
     */
     void displayAllPets() {

        // displaying a message
        System.out.println("\n***************** Displaying all mammals, birds and fishes *****************\n");
        // if there is no pet in collection, display message
        if (this.pets.isEmpty()) {
            System.out.println("\n***************** No pets in the collection yet *****************\n");
        } // Otherwise loop over the list and display objects
        else {
            for (int i = 0; i < this.pets.size(); i++) {
                System.out.println(this.pets.get(i));
            }
        }
    }

    /**
     * This method is used to display pets in a given range of price
     */
     void displayAllPetsInRange() {

        try {

            // Take max value of range from user
            System.out.print("Enter max range you want to see pets in: ");
            double max = inputSource.nextDouble();

            // Take min value of range from user
            System.out.print("Enter min range you want to see pets in: ");
            double min = inputSource.nextDouble();

            System.out.println("\n***************** Displaying price in range " + min + " - " + max + " *****************\n");
            if (this.pets.isEmpty()) {
                System.out.println("\n***************** No pets in the collection yet *****************\n");
            } else {
                ArrayList<Pet> rangedPets = getPetsInRange(max,min);
                // Loop over the list to print any pets within given range
                for (int i = 0; i < rangedPets.size(); i++) {
                    System.out.println(rangedPets.get(i));
                }
            }
        } catch (Exception ex) {
            System.out.println("Invalid input, returning");
            return;
        }
    }

    public ArrayList<Pet> getPetsInRange(double max, double min){
        ArrayList<Pet> rangedPets = new ArrayList();
         for (int i = 0; i < this.pets.size(); i++) {
                    if (this.pets.get(i).price >= min && this.pets.get(i).price <= max) {
                        rangedPets.add(this.pets.get(i));
                    }
                }
         return rangedPets;
    }
    /**
     * This method is used to store the pet collection dat to the file
     */
     void writeToFile() {
        FileOutputStream fileOut = null;
        try {
            // file name to store data in
            String fileName = "pets-data.ser";
            fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

            // writing whole list of pets at once
            objectOut.writeObject(this.pets);
            objectOut.close();
            System.out.println("\n************* Pets data was written to the file *************\n");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileOut.flush();
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     
    ArrayList<Pet> readFromFile(){
        ArrayList<Pet> pets = null ;
        // For reading inut file
        FileInputStream fileIn = null;
        try {
            // Initializing pets collection to store pets
            pets = new ArrayList();

            // Filename to read data from
            fileIn = new FileInputStream("pets-data.ser");
            // To read data as objects
            ObjectInputStream in = new ObjectInputStream(fileIn);

            // reading all the collection of pets as once as it was written
            pets = (ArrayList) in.readObject();

            // closing the input stream after reading data
            in.close();

            fileIn.close();
        } catch (FileNotFoundException ex) {

//            Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fileIn.close();
                return pets;
            } catch (IOException | NullPointerException ex) {
//                Logger.getLogger(PetShop.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pets;
        // code referred from https://www.tutorialspoint.com/java/java_serialization.htm
    
    }

    // Code reference taken from : https://examples.javacodegeeks.com/core-java/io/fileoutputstream/how-to-write-an-object-to-file-in-java/
}

/**
 * This class refers to Pet object which will have a species and a price, making
 * it serializable to make data storing and accessing easy
 *
 * @author
 */
class Pet implements Serializable {

    //default serialVersion id
    private static final long serialVersionUID = 1L;
    String species;
    double price;

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

/**
 * This class refers to the Bird which is inherited from Pet but have additional
 * attributes like cageDims and foods
 *
 * @author
 */
class Bird extends Pet {

    String cageDims;
    ArrayList<String> foods;

    public String getCageDims() {
        return cageDims;
    }

    public void setCageDims(String cageDims) {
        this.cageDims = cageDims;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<String> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "Bird{" + "cageDims=" + cageDims + ", foods=" + foods + ", species=" + this.species + ", price=" + this.price + "}";
    }

}

/**
 * This class refers to the Mammal which is inherited from Pet but have
 * additional attributes like bread and vaccinations
 *
 * @author
 */
class Mammal extends Pet {

    String bread;
    ArrayList<String> vaccinations;

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public ArrayList<String> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(ArrayList<String> vaccinations) {
        this.vaccinations = vaccinations;
    }

    @Override
    public String toString() {
        return "Mammal{" + "bread=" + bread + ", vaccinations=" + vaccinations + ", species=" + this.species + ", price=" + this.price + "}";
    }

}

/**
 * This class refers to the Fish which is inherited from Pet but have additional
 * attributes like minTankVolInLitres and fishNumInSchool
 *
 * @author
 */
class Fish extends Pet {

    double minTankVolInLitres;
    int fishNumInSchool;

    public double getMinTankVolInLitres() {
        return minTankVolInLitres;
    }

    public void setMinTankVolInLitres(double minTankVolInLitres) {
        this.minTankVolInLitres = minTankVolInLitres;
    }

    public int getFishNumInSchool() {
        return fishNumInSchool;
    }

    public void setFishNumInSchool(int fishNumInSchool) {
        this.fishNumInSchool = fishNumInSchool;
    }

    @Override
    public String toString() {
        return "Fish{" + "minTankVolInLitres=" + minTankVolInLitres + ", fishNumInSchool=" + fishNumInSchool + ", species=" + this.species + ", price=" + this.price + "}";
    }

}
