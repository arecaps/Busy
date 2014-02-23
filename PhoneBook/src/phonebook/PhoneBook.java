
package phonebook;

import java.io.*;

public class PhoneBook {

    public static void main(String[] args){
        
        Book book = new Book();
        book.contacts.add(new Contact("Bob", "732-321-4321"));
        book.contacts.add(new Contact("Sam", "732-876-9876"));
        Contact jerry = new Contact("Jerry", "732-746-8787");
        jerry.setEmail("jerry@gmail.com");
        book.contacts.add(jerry);
        book.contacts.add(new Contact("Harry", "732-321-4321", "432 Outamy Way", "harry@gmail.com"));
                
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("contactList"));
            out.writeObject(book);
            
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("contactList"));
            Book b = (Book)in.readObject();
            in.close();
            for(Contact contact : b.contacts){
                System.out.println(contact.getName());
                System.out.println(contact.getPhone());
                System.out.println(contact.getAddress());
                System.out.println(contact.getEmail());
            }
            
        }
        catch(IOException | ClassNotFoundException ex) {
              System.err.println(ex.getMessage());  
        }
    }
    
}
