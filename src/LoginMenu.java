import java.util.regex.Matcher;
public class LoginMenu {
    private final Admin admin;
    private UserMenu userMenu;

    public LoginMenu(Admin admin) {
        this.admin = admin;
    }

    public String run() {
        Matcher matcher;
        String command, res;
        System.out.println("Hi there, you are in login menu.");
        System.out.println("If you are new you can register in this format >>> register username password email.");
        System.out.println("and if you are not,you can login to your account in this format >>> login username password.");
        while (true) {
            command = Menu.getScanner().nextLine();

            if (command.matches("^\\s*logout\\s*$"))
                return "exit";
            if ((matcher = Menu.getMatcher(command, "^\\s*register\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s+(?<email>\\S+)\\s*$")) != null)
                System.out.println(Admin.register(matcher.group("username"), matcher.group("password"), matcher.group("email")));
            else if ((matcher = Menu.getMatcher(command, "^\\s*login\\s+(?<username>\\S+)\\s+(?<password>\\S+)\\s*$")) != null) {
                res = Admin.login(matcher.group("username"), matcher.group("password"));
                System.out.println(res);
                if (res.equals("login successful")) {
                    userMenu = new UserMenu(admin);
                    userMenu.run();
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }
}
