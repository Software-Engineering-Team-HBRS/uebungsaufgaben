import java.util.List;

public abstract class AbteilungComposite implements AbteilungComponent {

    protected List<AbteilungComponent> components;


    public abstract String getName();
    public abstract void printName();

    public void addAbteilung(AbteilungComponent abteilung){

        components.add(abteilung);
    }
}
