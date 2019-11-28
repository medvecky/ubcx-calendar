import model.MyDate;
import model.MyTime;

public class Main {
    public static void main(String[] args) {
        MyDate date = new MyDate(1,1,2019);
        System.out.println(date.getShortDate());
        System.out.println(date.getLongDate());

        MyTime time = new MyTime(18, 35);
        System.out.println(time.getTime());
    }
}
