import mvc.Control;
import mvc.Model;
import mvc.View;

/**
 * Created by Juanaj on 26/02/2016.
 */
public class Main {
    public static void main(String[] args){
        View view = new View();
        Model model = new Model();
        Control control = new Control(model, view);
    }
}
