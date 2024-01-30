import java.text.SimpleDateFormat;
import java.util.Date;

public class Course {
    private double progressRate;
    private final String name;

    private String description;
    private String comment;
    private int amount;
    private final int quantity;
    private String date;
    private String category;
    private final Category[] categories = Category.values();

    public Course(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        setProgressRate(0);
        this.amount = 0;
        var date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = date1.format(new Date());
        extraQuestions();
    }

    public void extraQuestions(){
        System.out.println("what is the category of this course?");
        System.out.println("you can choose from these items:");
        int count = 0;
        for (Category category : categories) {
            if(count == categories.length-1){
                System.out.print(category);
            }else {
                System.out.print(category + ", ");
            }
            count +=1;
        }
        System.out.println();
        while (true){
            String res = setCategory(Menu.getScanner().nextLine());
            if(res.equals("valid"))
                break;
        }
        //
        System.out.println("please enter a description for course then you can know what it is about later");
        setDescription(Menu.getScanner().nextLine());
        System.out.println("do you want to add any comment for this course? [Y/N]");
        String commentReq = Menu.getScanner().nextLine();
        if(commentReq.equals("Y") || commentReq.equals("y")){
            System.out.print("sure, type your comment:");
            setComment(Menu.getScanner().nextLine());
        }
        else {
            System.out.println("it sounds that you dont want to have a comment for this course.");
            System.out.println("anytime you change your mind and want to add a comment type \"add a comment for <name of the course>,<comment>\"");
        }
        System.out.println();
        System.out.println("successfully added");

    }

    public String setCategory(String category){
        boolean isCategory = false;
        String cat;
        for (Category category1 : categories) {
            cat = category1.toString();
            if(cat.equals(category)){
                isCategory = true;
            }
        }
        if(!isCategory){
            System.out.println("invalid category!");
            return "invalid";
        }else {
            this.category = category;
            return "valid";
        }
    }

    public void setDescription(String info) {
        this.description = info;
    }

    public String showDescription(){
        return "This is the description of this course : " + this.description;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getProgressRate() {
        return progressRate;
    }

    public String getDescription() {
        return description;
    }

    public int getRemainVideosNumber(){
        return this.quantity - this.amount;
    }


    public String showComment(){
        return "This is the comment of this course : " + this.comment;
    }

    public void setProgressRate(int amount) {
        this.amount += amount;
        this.progressRate = ((float)this.amount/this.quantity) * 100;
    }


    public String getCategory() {
        return category;
    }

    public String toString(){
        return this.getName() + " / " + this.getCategory() + " / " + this.getProgressRate() + "% / " + this.getDescription() + " / " + this.getDate();
    }


}
