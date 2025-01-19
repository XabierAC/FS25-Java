public class Crop {
    private String name;
    private int seedPerHa;
    private int harvestPerHa;
    private String seedingMonth;
    private String harvestingMonth;

    public Crop(String name, int seedPerHa, int harvestPerHa, String seedingMonth, String harvestingMonth){
        this.name = name;
        this.seedPerHa = seedPerHa;
        this.harvestPerHa = harvestPerHa;
        this.seedingMonth = seedingMonth;
        this.harvestingMonth = harvestingMonth;
    }
}
