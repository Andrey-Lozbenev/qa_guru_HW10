package dzr.hanom;


import java.util.List;

public class Car {

    public String brand;
    public String model;
    public String colour;
    public Integer wheels;
    public Long price;
    public Dimensions dimensions;
    public List<String> extras;

    public static class Dimensions {
        public Integer length;
        public Integer width;
        public Integer height;

    }

}
