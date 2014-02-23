package phonebook2;

import java.io.*;

public class PhoneBook {

    public static void main(String[] args) {

        Book book = new Book();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("contactList"));
            out.writeObject(book);

            ObjectInputStream in = new ObjectInputStream(new FileInputStream("contactList"));
            Book b = (Book) in.readObject();
            in.close();

            System.out.println(b.bob.getName() + System.lineSeparator());
            System.out.println(b.sam.getName() + System.lineSeparator()
                    + b.sam.getPhone() + System.lineSeparator());
            System.out.println(b.bill.getAllInfo());
            System.out.println(b.harry.getAllInfo());
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
