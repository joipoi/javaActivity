import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    List<Activity> activityList = new ArrayList<>();
    private static final String filepath="C:\\Users\\Joipoi\\IdeaProjects\\Java2021\\src\\obj";

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.ReadObjectToFile();


        if(args.length == 0) {
            controller.displayActivities();
        } else if(args.length == 1 ) {
            controller.updateActivities(args[0]);
            controller.displayActivities();
        } else if (args.length == 2 && args[0].equals("remove")) {
            controller.deleteActivity(args[1]);
        }






    }
   public void deleteActivity(String name) {
        Activity act = new Activity("");
       for(Activity activity: activityList) {
           if(activity.name.equals(name)) {
               act = activity;

           }
       }
       activityList.remove(act);
       WriteObjectToFile();
   }

    public void updateActivities(String name) {
        for(Activity activity: activityList) {
            if(activity.name.equals(name)) {
                activity.lastDate = LocalDate.now();
                WriteObjectToFile();
                return;
            }
        }
        Activity activity = new Activity(name);
        activityList.add(activity);
        WriteObjectToFile();
    }
    public void displayActivities() {
        for(Activity activity: activityList) {
            System.out.println(activity);
        }
    }
    public void WriteObjectToFile() {

        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            for(Activity activity: activityList) {
                objectOut.writeObject(activity);
            }

            objectOut.close();
            fileOut.close();
            System.out.println("The Object  was succesfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void ReadObjectToFile() {

        try {

            FileInputStream fileIn = new FileInputStream(filepath);

            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            boolean cont = true;
            while(cont) {
                if (fileIn.available() != 0) {
                    Activity activity = (Activity) objectIn.readObject();
                    activityList.add(activity);
                } else {
                    cont = false;
            }
            }

            objectIn.close();
            fileIn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    }






