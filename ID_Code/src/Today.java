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
        String msg = "";
        if (sum.getAverageTemp() < 0) {
            if (sum.getWeatherCode() == 0) {    //sunny
                msg = "Sunny but cold? Sounds like skiing weather to me! Wrap up warm."
            } else if (sum.getWeatherCode() == 1) {   //windy
                msg = "Man, it's cold out there! Make sure to bring your gloves, scarf and hat!";
            } else if (sum.getWeatherCode() == 2) {     //cloudy
                msg = "Bit of an overcast day, huh? Sub-zero temperatures means wrap up warm though!";
            } else if (sum.getWeatherCode() == 3) {     //rainy
                msg = "Make sure you wrap up warm and be careful as the rain may become icy.";
            } else {    //snowy
                msg = "Wrap up warm and don't forget your gloves! Do you want to build a snowman?";
            }
        } else if (sum.getAverageTemp() < 5) {
            if (sum.getWeatherCode() == 0) {    //sunny
                msg = "Well the positive is that it's sunny right?"
            } else if (sum.getWeatherCode() == 1) {   //windy
                msg = "It might be quite nippy so make sure to bring your gloves, scarf and hat!";
            } else if (sum.getWeatherCode() == 2) {     //cloudy
                msg = "Bit of an overcast day, huh? Wrap up warm!";
            } else if (sum.getWeatherCode() == 3) {     //rainy
                msg = "Make sure you wrap up warm and be careful as the rain may become icy.";
            } else {    //snowy
                msg = "Wrap up warm and don't forget your gloves!";
            }
        } else if (sum.getAverageTemp() < 10) {
            if (sum.getWeatherCode() == 0) {    //sunny
                msg = "If only it were 10 degrees warmer..."
            } else if (sum.getWeatherCode() == 1) {   //windy
                msg = "Don't forget to bring a jacket today! The wind may make it feel colder than it actually is.";
            } else if (sum.getWeatherCode() == 2) {     //cloudy
                msg = "Well it's a bit dull and grey. Wear a few layers and a jacket.";
            } else if (sum.getWeatherCode() == 3) {     //rainy
                msg = "The weather may be miserable but you don't have to be! Have a good day!";
            } else {    //snowy
                msg = "Wear a few layers and maybe bring your gloves as well as your jacket.";
            }
        } else if (sum.getAverageTemp() < 15) {
            if (sum.getWeatherCode() == 0) {    //sunny
                msg = "Sunny but cold? Sounds like skiing weather to me! Wrap up warm."
            } else if (sum.getWeatherCode() == 1) {   //windy
                msg = "It might be quite nippy so make sure to bring your gloves, scarf and hat!";
            } else if (sum.getWeatherCode() == 2) {     //cloudy
                msg = "Bit of an overcast day, huh? Wrap up warm!";
            } else if (sum.getWeatherCode() == 3) {     //rainy
                msg = "Make sure you wrap up warm and be careful as the rain may become icy.";
            } else {    //snowy
                msg = "Hmm... well that's interesting. Take some gloves?";
            }
        } else if (sum.getAverageTemp() < 20) {
            if (sum.getWeatherCode() == 0) {    //sunny
                msg = "Hmm, is it warm enough to go without a jacket?"
            } else if (sum.getWeatherCode() == 1) {   //windy
                msg = "The wind may make it feel a little colder than it is so take a jacket!";
            } else if (sum.getWeatherCode() == 2) {     //cloudy
                msg = "It's a little overcast, so take a jacket if you get cold easily.";
            } else if (sum.getWeatherCode() == 3) {
                msg = "Don't forget to take a raincoat or umbrella with you!";
            } else {
                msg = "Did they change the freezing point of water?";
            }
        } else if (sum.getAverageTemp() < 25) {
            if (sum.getWeatherCode() == 0 || sum.getWeatherCode() == 1) {
                msg = "Don't forget to put on suncream today!";
            } else if (sum.getWeatherCode() == 2) {
                msg = "There may be clouds but you should probably still wear suncream!";
            } else if (sum.getWeatherCode() == 3) {
                msg = "It may be warm but don't forget a raincoat!";
            } else {
                msg = "I'm honestly just as confused as you.";
            }
        } else {
            if (sum.getWeatherCode() == 0 || sum.getWeatherCode() == 1) {
                msg = "Don't forget to put on suncream today!";
            } else if (sum.getWeatherCode() == 2) {
                msg = "There may be clouds but you should probably still wear suncream!";
            } else if (sum.getWeatherCode() == 3) {
                msg = "It may be a bit like a rainforest today - both hot and rainy so don't forget a jacket!";
            } else {
                msg = "I guess they were right about that climate change thing?";
            }
        }
        return msg;
    }
}
