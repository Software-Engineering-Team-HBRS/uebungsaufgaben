import java.util.ArrayList;

public class Oberabteilung extends AbteilungComposite implements AbteilungComponent{

    private String name;

    public Oberabteilung(String name) {

        this.name = name;
        this.components = new ArrayList<AbteilungComponent>();
    }

    public String getName() {

        return name;
    }

    public void printName(){

        System.out.println(this.getName());
        System.out.println("Unterabteilungen:");

        for (int i = 0; i < this.components.size(); i++){

            this.components.get(i).printName();
        }
        System.out.println("");
    }
}
