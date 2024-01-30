import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Admin{
    public LoginMenu loginMenu;
    public UserMenu userMenu;
    private static User loggedInUser;
    private Course course;


    public Admin(){
        loginMenu = new LoginMenu(this);
        userMenu = new UserMenu(this);
    }

    public void run(){
        if(loginMenu.run().equals("exit"))
            return;
    }

    public static String register(String username, String password, String email) {
        if (User.getUserByUsername(username) != null)
            return "register failed: username already exists";

        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*[0-9].*")) {
            return "register failed: password is weak\nthe password should contains at least one capital and small word and one number and the length should be more than 8 character";
        }

        if(!email.matches("^.*@gmail\\.com.*$") && !email.matches("^.*@yahoo.com.*$")){
            return "register failed: the format of email is not correct\nthe email format should be like that : example@gmail.com or example@yahoo.com";
        }

        User.addUser(username, password,email);
        return "register successful\nnow you can login in this format >>> login username password";
    }

    public static String login(String username, String password) {
        if ((loggedInUser = User.getUserByUsername(username)) != null) {
            if (!loggedInUser.isPasswordCorrect(password)) {
                loggedInUser = null;
                return "login failed: incorrect password!";
            }
            return "login successful";
        } else
            return "login failed!";
    }

    public void addCourse(String name,String quantity){
        for (Course loggedInUserCourse : loggedInUser.getCourses()) {
            if(loggedInUserCourse.getName().equals(name)){
                System.out.println("You have a course by this name!");
                return;
            }
        }
        Course course;
        course = new Course(name,Integer.parseInt(quantity));
        loggedInUser.getCourses().add(course);
    }

    public String removeCourse(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null) {
            loggedInUser.getCourses().remove(course);
            return "the course removed successfully";
        }
        return "A problem happend ,maybe the name is wrong";
    }

    public String addComment(String name,String comment){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null) {
            course.setComment(comment);
            return "comment saved successfully";
        }
        return "A problem happend ,maybe the name is wrong";
    }

    public String getProgressRateOfACourse(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null)
            return "The progress rate is : " + course.getProgressRate() + "%";
        return "A problem happend ,maybe the name is wrong";
    }

    public String addProgressRate(int amount,String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null){
            course.setProgressRate(amount);
            return "updated successfuly, if you want to see your progress rate type \"get progress rate of <course name>\"";
        }
        return "an erroe occured";
    }

    public void info(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null){
            System.out.println(course);
        }
        else {
            System.out.println("The course not found!");
        }
    }

    public String addToFavorite(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null) {
            loggedInUser.getFavorites().add(course);
            return "successfully added to favorites";
        }
        return "A problem happend ,maybe the name is wrong";
    }

    public String supportAndHelp(){
        System.out.println("do you want to see what others ask? [Y,N]");
        String inp = Menu.getScanner().nextLine();
        if(inp.equals("Yes") || inp.equals("y") || inp.equals("Y") || inp.equals("yes")){
            recentProblems();
        }
        System.out.print("How can i help you?");
        loggedInUser.getProblems().add(Menu.getScanner().nextLine());
        return "we save your recommandation and we hope to solve that as soon as possible!";
    }

    public void showCourses(){
        if(loggedInUser.getCourses().isEmpty()){
            System.out.println("you don't have any course");
            return;
        }
        int counter = 1;
        System.out.println("these are your courses:");
        for (Course cours : loggedInUser.getCourses()) {
            System.out.print(counter + ". ");
            System.out.println(cours);
            counter+=1;
        }
    }

    public void showFavorites(){
        if(loggedInUser.getFavorites().isEmpty()){
            System.out.println("you don't have any favorite course");
            return;
        }
        int counter = 1;
        System.out.println("these are your favorites:");
        for (Course favorite : loggedInUser.getFavorites()) {
            System.out.print(counter + ". ");
            System.out.println(favorite);
            counter+=1;
        }
    }

    public String showComment(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null)
            return course.showComment();
        return "An error occured maybe the course name is wrong";
    }

    public String showInfo(String name){
        Course course;
        if((course = loggedInUser.getCourseByName(name)) != null)
            return course.showDescription();
        return "An error occured maybe the course name is wrong";
    }

    public void recentProblems(){
        int counter = 1;
        for (User user : User.getUsers()) {
            for (String problem : user.getProblems()) {
                System.out.println(counter + ". " + problem.trim());
                counter +=1;
            }
        }
    }

    public void getCommands(){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("commands.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getCategory(String name){
        if ((course = loggedInUser.getCourseByName(name)) != null)
            return course.getCategory();
        else
            return "An error occurred";
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("logged out successfully");
        loginMenu.run();
    }
}