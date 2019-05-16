import java.io.File;
import java.util.LinkedList;

public class Today {
    private int genWeather;
    private LinkedList<File> clothes;
    private LinkedList<File> accessories;
    private int temperature;
    private File background;
    private String message;
    private static String dataFolder = "src/data/";

    public Today(Summary sum) {
        this.genWeather = sum.getWeatherCode();
        this.clothes = setClothes(sum);
        this.accessories = setAccessories(sum);
        this.temperature = sum.getAverageTemp();
        this.background = setBackground(sum);
        this.message = setMessage(sum);
    }

    public LinkedList<File> getClothes() {
        return clothes;
    }

    public LinkedList<File> setClothes(Summary sum) {
        LinkedList<File> clothes = new LinkedList<>();
        if(sum.getWeatherCode() == 4){ //snowy (will always be cold)
            clothes.add(new File(dataFolder + "clothes/snowy1.png"));
            clothes.add(new File(dataFolder + "clothes/snowy2.png"));
        }

        else if (sum.getAverageTemp() <= 15 && sum.getAverageTemp() > 10){ //cold
            clothes.add(new File(dataFolder + "clothes/cold1.png"));
            clothes.add(new File(dataFolder + "clothes/cold2.png"));
        }

        else if(sum.getAverageTemp() <= 10){//below jacket temp
            clothes.add(new File(dataFolder + "clothes/snowy1.png"));
            clothes.add(new File(dataFolder + "clothes/cold1.png"));

        }

        else { //warm
            if(Settings.getClothes()[1] == true && Settings.getClothes()[2] == true){ //wears shorts & vests
                clothes.add(new File(dataFolder + "clothes/warm1.png"));
                clothes.add(new File(dataFolder + "clothes/warm3.png"));
            }
            if(Settings.getClothes()[0] == true){ //wears dresses
                clothes.add(new File(dataFolder + "clothes/warm4.png"));
            }
            if(Settings.getClothes()[4] == true){ //wears jeans
                clothes.add(new File(dataFolder + "clothes/warm2.png"));
            }
            if(Settings.getClothes()[3] == true){ //wears skirts
                clothes.add(new File(dataFolder + "clothes/warm5.png"));
            }
            clothes.add(new File(dataFolder + "clothes/warm6.png"));

        }

        return clothes;
    }

    public LinkedList<File> getAccessories() {
        return accessories;
    }

    public LinkedList<File> setAccessories(Summary sum) {
        LinkedList<File> accessories = new LinkedList<>();

        //accessories based on weather
        if (sum.getWeatherCode() == 4) {    //snowy
            if (sum.getAverageTemp() <= 10) {
                accessories.add(new File(dataFolder + "accessories/hat.png"));
            }
            accessories.add(new File(dataFolder + "accessories/gloves.png"));
            accessories.add(new File(dataFolder + "accessories/scarf.png"));
        } else if (sum.getWeatherCode() == 0) {     //sunny
            if (sum.getAverageTemp() > 14) {
                accessories.add(new File(dataFolder + "accessories/sunglasses.png"));
            }
            if (sum.getAverageTemp() > 20) {
                accessories.add(new File(dataFolder + "accessories/sunhat.png"));
            }
        } else if (sum.getWeatherCode() == 3) { //rainy
            accessories.add(new File(dataFolder + "accessories/raincoat.png"));
        }

        //accessories based on settings
        if (sum.getAverageTemp() < Settings.getJacket() && sum.getWeatherCode() != 3) {
            accessories.add(new File(dataFolder + "accessories/jacket.png"));
        }
        if (sum.getRain() > Settings.getRain()) {
            accessories.add(new File(dataFolder + "accessories/umbrella.png"));
        }
        return accessories;
    }

    public int getTemperature() {
        return temperature;
    }

    public File getBackground() {
        return background;
    }

    public File setBackground(Summary sum) {
        if(sum.getPartOfDay() == "n"){ //night
            if (sum.getWeatherCode() == 3) {    //rainy
                background = new File(dataFolder + "backgrounds/nightrain.png");
            } else {
                background = new File(dataFolder + "backgrounds/night.png");
            }
        }
        else { //day
            switch (sum.getWeatherCode()) {
                case 0:
                    background = new File(dataFolder + "backgrounds/sunny.png");
                    break;
                case 1:
                    background = new File(dataFolder + "backgrounds/windy.png");
                    break;
                case 2:
                    background = new File(dataFolder + "backgrounds/cloudy.png");
                    break;
                case 3:
                    background = new File(dataFolder + "backgrounds/rainy.png");
                    break;
                case 4:
                    background = new File(dataFolder + "backgrounds/snowy.png");
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
                msg = "Sunny but cold? Sounds like skiing weather to me! Wrap up warm.";
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
                msg = "Well the positive is that it's sunny right?";
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
                msg = "If only it were 10 degrees warmer...";
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
                msg = "Sunny but cold? Sounds like skiing weather to me! Wrap up warm.";
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
                msg = "Hmm, is it warm enough to go without a jacket?";
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
