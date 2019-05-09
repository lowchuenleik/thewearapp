package PACKAGE_NAME;

public class Today {
  private int jacketLimit;
  private int rainLimit;
  private File clothes;
  private File background;


  public Today() {
    setJacketLimit(14);
    setRainLimit(60);

  }



  //getter and setter methods
  public void setJacketLimit(int limit) {
    jacketLimit = limit;
  }
  public void setRainLimit(int limit) {
    rainLimit = limit;
  }
  public File getClothes(){
    updateClothes();
    return clothes;
  }

  public File getBackground(){
    updateBackground();
    return background;
  }



  //TODO: would it be better if we have some sort of refresh method that sets
  //      all the clothes, accessories, etc then get methods for them?

  public void updateClothes(){
    if(Summary.getWeatherCode() = 4){ //snowy (will always be cold)
      clothes =  new File("\\data\\clothes\\snowy.png");
    }

    else if(Summary.getWeatherCode() = 3){ //rainy
      if(Summary.getAverageTemp() > jacketLimit) // rainy warm
      clothes =  new File("\\data\\clothes\\rainWarm.png");
     else { //rainy cold
      clothes =  new File("\\data\\clothes\\rainCold.png");
    }
  }

    else if (Summary.getLowTemp() < jacketLimit){ //cold (no rain)
      clothes = new File("\\data\\clothes\\cold.png");
    }
    else { //warm (no rain)
      clothes =  new File("\\data\\clothes\\warm.png");
    }
  }


  public void updateBackground(){
    if(Summary.partOfDay = "n"){ //night
      background = new File("\\data\\backgrounds\\night.png");
    }
    else{ //day
      switch(Summary.getWeatherCode()) {
        case 0:
          background =  new File("\\data\\clothes\\sunny.png");
          break;
        case 1:
          background =  new File("\\data\\clothes\\windy.png");
          break;
        case 2:
          background =  new File("\\data\\clothes\\cloudy.png");
          break;
        case 3:
          background =  new File("\\data\\clothes\\rainy.png");
          break;
        case 4:
          background =  new File("\\data\\clothes\\snowy.png");
          break;

      }
    }
  }
}
