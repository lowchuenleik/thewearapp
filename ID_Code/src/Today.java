import java.util.LinkedList;

public class Today {
    private int genWeather;
    private LinkedList<String> clothes;
    private LinkedList<String> accessories;
    private int temperature;
    private String background; //string to directory or change to file?
    private String message;

    public Today(Summary sum) {
        this.genWeather = sum.getWeatherCode();
        this.clothes = setClothes(sum);
        this.accessories = setAccessories(sum);
        this.temperature = sum.getAvgTemp();
        this.background = setBackground(sum);
        this.message = setMessage(sum);
    }

    public LinkedList<String> getClothes() {
        return clothes;
    }

    public LinkedList<String> setClothes(Summary sum) {
        if(Summary.getWeatherCode() = 4){ //snowy (will always be cold)
            clothes =  new File("\\data\\clothes\\snowy.png");
        }

        else if(Summary.getWeatherCode() = 3){ //rainy
            if(Summary.getAverageTemp() > Settings.get_jacket();) // rainy warm
            clothes =  new File("\\data\\clothes\\rainWarm.png");
         else { //rainy cold
                    clothes =  new File("\\data\\clothes\\rainCold.png");
                }
            }

            else if (Summary.getLowTemp() < Settings.get_jacket();){ //cold (no rain)
                clothes = new File("\\data\\clothes\\cold.png");
            }
        else { //warm (no rain)
                clothes =  new File("\\data\\clothes\\warm.png");
        }

        //TODO: put into linked list structure
        return new LinkedList<>();
    }

    public LinkedList<String> getAccessories() {
        return accessories;
    }

    public LinkedList<String> setAccessories(Summary sum) {
        return new LinkedList<>();
    }

    public int getTemperature() {
        return temperature;
    }

    public String getBackground() {
        return background;
    }

    public String setBackground(Summary sum) {
        background = "";
        if(Summary.partOfDay = "n"){ //night
            background = new File("\\data\\backgrounds\\night.png");
        }
        else { //day
            switch (Summary.getWeatherCode()) {
                case 0:
                    background = new File("\\data\\clothes\\sunny.png");
                    break;
                case 1:
                    background = new File("\\data\\clothes\\windy.png");
                    break;
                case 2:
                    background = new File("\\data\\clothes\\cloudy.png");
                    break;
                case 3:
                    background = new File("\\data\\clothes\\rainy.png");
                    break;
                case 4:
                    background = new File("\\data\\clothes\\snowy.png");
                    break;

            }
        }

        return background;
    }

    public String getMessage() {
        return message;
    }

    public String setMessage(Summary sum) {

         return "";
    }
}
