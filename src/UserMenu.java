import java.util.regex.Matcher;

public class UserMenu {
    private final Admin admin;
    private LoginMenu loginMenu;
    public UserMenu(Admin admin){
        this.admin = admin;
    }

    public void run(){
        Matcher matcher;
        String command;
        loginMenu = new LoginMenu(admin);
        System.out.println("Now you are in usermenu, what do you want to do?");
        System.out.println("You can see the commands that you can use with typing \"get commands\".");
        while (true){
            command = Menu.getScanner().nextLine();
            if (command.matches("^\\s*back\\s*$")) {
                admin.logout();
                return;
            }
            if ((matcher = Menu.getMatcher(command, "^\\s*add course\\s+(?<name>\\S+)\\s+(?<quantity>\\d+)\\s*$")) != null)
                admin.addCourse(matcher.group("name"),matcher.group("quantity"));
            if ((matcher = Menu.getMatcher(command,"^\\s*remove course\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.removeCourse(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*add a comment for\\s+(?<name>\\S+)\\s*,\\s*(?<comment>\\S+)\\s*$")) != null)
                System.out.println(admin.addComment(matcher.group("name"),matcher.group("comment")));
            if ((matcher = Menu.getMatcher(command,"^\\s*I see\\s+(?<amount>\\d+)\\s+of the\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.addProgressRate(Integer.parseInt(matcher.group("amount")),matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*get progress rate of\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.getProgressRateOfACourse(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*info of\\s+(?<name>\\S+)\\s*$")) != null)
                admin.info(matcher.group("name"));
            if ((matcher = Menu.getMatcher(command,"^\\s*add\\s+(?<name>\\S+)\\s+to favorites$")) != null)
                System.out.println(admin.addToFavorite(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*show description of\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.showInfo(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*show comment of\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.showComment(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*category of\\s+(?<name>\\S+)\\s*$")) != null)
                System.out.println(admin.getCategory(matcher.group("name")));
            if ((matcher = Menu.getMatcher(command,"^\\s*how many videos do i have to watch for\\s+(?<name>\\S+)\\s*$")) != null)
                admin.howManyVideos(matcher.group("name"));
            if(command.matches("^\\s*support & help\\s*$") || command.matches("^\\s*support and help\\s*$"))
                System.out.println(admin.supportAndHelp());
            if(command.matches("^\\s*recent problems\\s*$"))
                admin.recentProblems();
            if(command.matches("^\\s*show my courses\\s*$"))
                admin.showCourses();
            if(command.matches("^\\s*show my favorites\\s*$"))
                admin.showFavorites();
            if(command.matches("^\\s*get commands\\s*$"))
                admin.getCommands();

        }
    }
}
