public class Main {
    public static void main(String[] args) {


        AbteilungComposite ab = new Oberabteilung("AB");

        AbteilungComposite ab3 = new Oberabteilung("AB-3");
        AbteilungComposite ab4 = new Oberabteilung("AB-4");

        AbteilungComponent ab30 = new Abteilung("AB-30");
        AbteilungComponent ab31 = new Abteilung("AB-30");

        AbteilungComponent ab40 = new Abteilung("AB-40");
        AbteilungComponent ab41 = new Abteilung("AB-41");

        ab.addAbteilung(ab3);
        ab.addAbteilung(ab4);

        ab3.addAbteilung(ab30);
        ab3.addAbteilung(ab31);

        ab4.addAbteilung(ab40);
        ab4.addAbteilung(ab41);
        ab.printName();

         }
}