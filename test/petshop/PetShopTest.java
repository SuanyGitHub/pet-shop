/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petshop;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 
 */
public class PetShopTest {
    
    public PetShopTest() {
    }

    
      /**
     * Test of addNewPet method, of class PetShop.
     */
    @Test
    public void testAddNewPet() {
        PetShop p = new PetShop();
        
        // Adding new pet to the pet collection 
        Mammal m = new Mammal();
        m.setBread("demo-bread");
        m.setSpecies("dog");
        m.setPrice(340);
        m.setVaccinations(new ArrayList(Arrays.asList("rabies,distemper".split(","))));
        p.pets.add(m);
        
        // Writing new pet data to the file
        p.writeToFile();
        
        // reinitializing the petshop collection to read the updated data
        p.pets = p.readFromFile();
        
        // Assuring that newly added pet was stored in file too.
        assertEquals(p.pets.get(p.pets.size()-1).toString(),m.toString());
        
    }
    /**
     * Test of main method, of class PetShop.
     */
    @Test
    public void testCollectionSize() {
        PetShop p = new PetShop();
        
        // Testing as per current stored data size, might need to change test data on updation of data
        int expected = p.readFromFile().size();
        int actual = p.pets.size();
        assertEquals(expected,actual);
    }

  

    public void testPetsInRange(){
        
        // testing the getsPetsInRange function to make sure we are getting correct resulst
        PetShop p = new PetShop();
        ArrayList<Pet> rangedPets = p.getPetsInRange(600, 300);
        assertEquals(4,rangedPets.size());
    }

}
