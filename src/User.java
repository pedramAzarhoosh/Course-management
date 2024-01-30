import java.util.ArrayList;

public class User extends Person {
    private static final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Course> courses;
    private final ArrayList<Course> favorites;
    private final ArrayList<String> problems;

    private User(String username, String password,String email) {
        super(username,password,email);
        this.courses = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.problems = new ArrayList<>();
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username))
                return user;
        }
        return null;
    }

    public Course getCourseByName(String name) {
        for(Course course : courses){
            if(course.getName().equals(name))
                return course;
        }
        return null;
    }

    public static void addUser(String username, String password,String email) {
        users.add(new User(username, password,email));
    }

    public String getUsername() {
        return super.getName();
    }


    public String getEmail(){return super.getEmail();}

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Course> getFavorites() {
        return favorites;
    }

    public boolean isPasswordCorrect(String password) {
        return this.getPassword().equals(password);
    }

    public ArrayList<String> getProblems() {
        return problems;
    }

    public static ArrayList<User> getUsers(){
        return users;
    }
}
