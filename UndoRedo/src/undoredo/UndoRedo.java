package undoredo;

public class UndoRedo {
    private static UndoRedoManager undoRedo = new UndoRedoManager();
    
    public static void main(String[] args) {
        Person person = new Person();
        Organization organization = new Organization(); 
        System.out.print("person: ");
        System.out.println(person.getName());
        
        //person.setName("Bob");
        
        Command command = new SetNameCommand(person, "Bob");
        System.out.println("Executing name change person to Bob");
        command.execute();
        undoRedo.addCommand(command);
        System.out.print("person: ");
        System.out.println(person.getName());
        
        command = new SetNameCommand(organization, "PCS");
        System.out.println("Executing name change organization to PCS");
        command.execute();
        undoRedo.addCommand(command);
        System.out.print("organization: ");
        System.out.println(organization.getName());
        
        command = new SetAddressCommand(organization, "212 2nd Street, Lakewood");
        System.out.println("Executing address change organization to 212 2nd Street, Lakewood");
        command.execute();
        undoRedo.addCommand(command);
        System.out.print("organization address: ");
        System.out.println(organization.getAddress());
        
        Person person2 = new Person();
        System.out.print("person2: ");
        System.out.println(person2.getName());
        
        command = new SetNameCommand(person2, "Joe");
        System.out.println("Executing name change person2 to Joe");
        command.execute();
        undoRedo.addCommand(command);
        System.out.print("person2: ");
        System.out.println(person2.getName());
        
        command = new SetAddressCommand(person2, "123 Any Street Any Town USA");
        System.out.println("Executing address change person2 to 123 Any Street Any Town USA");
        command.execute();
        undoRedo.addCommand(command);
        System.out.print("address: ");
        System.out.println(person2.getAddress());
        
        while(undoRedo.canUndo()) {
            System.out.println("Undoing a command");
            undoRedo.undo();
            System.out.print("person: ");
            System.out.println(person.getName());
            System.out.print("organization: ");
            System.out.println(organization.getName());
            System.out.print("organization address: ");
            System.out.println(organization.getAddress());
            System.out.print("person2: ");
            System.out.println(person2.getName());
            System.out.print("address: ");
            System.out.println(person2.getAddress());
        }
        
        while(undoRedo.canRedo()) {
            System.out.println("Redoing a command");
            undoRedo.redo();
            System.out.print("person: ");
            System.out.println(person.getName());
            System.out.print("organization: ");
            System.out.println(organization.getName());
            System.out.print("organization address: ");
            System.out.println(organization.getAddress());
            System.out.print("person2: ");
            System.out.println(person2.getName());
            System.out.print("address: ");
            System.out.println(person2.getAddress());

        }
    }
}
