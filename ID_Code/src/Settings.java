package PACKAGE_NAME;

public class Settings{
  private String location;
  private boolean notficiations;
  private boolean unit_celsius;
  private int jacket;
  private int rain;
  private boolean[] clothes;

  public Settings(){
    // all fields have a default setting that  can be changed if the user wants to
    location="Cambridge";// should not be changed
    notficiations= true;// we're not using notifications in the design anymore should we change the design and ommit that
    unit_celsius= true;// if false use Fahreneheit

    jacket= 20; // I like to wear a jacket below "..."
    rain=50;// I like to bring an umbrella above "..." cahnce of rain

    //initialize array to default: person wears everything
    for(int i=0; i<5; i++){
      clothes[i]=true;
    }
  }
    //array indices corresponding to diff clothes
    //clothes[0] dresses
    //clothes[1] shorts
    //clothes[2] vests
    //clothes[3] skirts
    //clothes[4] jeans


    /*methods
    quite interlinked with gui so currently the code doesn't mean much*/

    //notifications
    private void set_notifications() {
        if (notifications) {
           notifications_button.setText("Yes");
           //align the button to the right as in the design
        }
        else {
            notifications_button.setText("No");
            //align the button to the left as in the design
        }
    }

    private boolean get_notifications (){
        return  notifications;
    }

    //unit
    private void set_unit() {}
    private boolean get_unit_celsius (){
        return  unit_celsius;
    }

    //jacket
    public void set_jacket(int t){
        jacket=t;
    }
    public int get_jacket(){
        return jacket;
    }
    //rain
    public void set_rain(int p){
        rain=p;
    }

    public int get_rain(){
        return rain;
    }

    //clothes array
    //get
    //set

}
