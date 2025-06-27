class Abteilung implements AbteilungComponent {

    private String name;



    public Abteilung(String name) {

        this.name = name;
    }




    public String getName() {

        return name;
    }

    public void printName(){

        System.out.println(this.getName());
    }
}