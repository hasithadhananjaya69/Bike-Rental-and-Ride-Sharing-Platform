package ridedrivermanagement.model;

// Abstract class to demonstrate OOP Inheritance
public abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Encapsulation
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
