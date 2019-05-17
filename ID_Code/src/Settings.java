public class Settings{
    //sets the values to defaults
    private static String location = "Cambridge";
    static boolean enableNotifications = false;
    static boolean unit_isCelcius = true;
    static int jacket = 20;
    static int rain = 50;
    private static boolean[] clothes = resetClothes();

    //enable notifications
    public static void setEnableNotifications(boolean b){enableNotifications = b;}
    public static boolean getEnableNotifications(){return enableNotifications;}

    //unit
    public static void setUnit(boolean b) {
        unit_isCelcius = b;
    }
    public static boolean getUnit(){
        return unit_isCelcius;
    }

    //jacket
    public static void setJacket(int t){
        jacket=t;
    }
    public static int getJacket(){
        return jacket;
    }

    //rain
    public static void setRain(int p){
        rain=p;
    }
    public static int getRain(){
        return rain;
    }

    //clothes array
    public static boolean[] getClothes() {
        return clothes;
    }
    public static void setClothes(boolean d, boolean sh, boolean v, boolean sk, boolean j) {
        clothes[0] = d;  //dresses
        clothes[1] = sh;  //shorts
        clothes[2] = v;  //vests
        clothes[3] = sk;  //skirts
        clothes[4] = j;  //jeans
    }

    //initialises the clothes array to all true
    public static boolean[] resetClothes() {
        boolean[] clothes = new boolean[5];
        for(int i=0; i<5; i++) {
            clothes[i] = true;
        }
        return clothes;
    }

}
