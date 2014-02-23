package phonebook2;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable{
     Contact bob;
    Contact sam;
    Contact bill;
    Contact harry;
    public Book(){
       

    bob = new Contact("Bob", "732-321-4321");
    sam = new Contact("Sam", "732-876-9876");
    bill = new Contact("Bill", "732-465-9786");
    bill.setAddress("478 Anyold Street");
    harry = new Contact("Harry", "732-876-9876", "89 Farblunged Place", "harry@freemail.com");
    }
}
