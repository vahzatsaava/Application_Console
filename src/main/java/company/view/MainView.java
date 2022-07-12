package company.view;

import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final Scanner scanner = new Scanner(System.in);

    public void MainMenu(){
        while (true){
            info();
            int value = scanner.nextInt();
            if (value == 6){
                break;
            }
            switch (value){
                case 1:
                    System.out.println("All skills :" + labelView.getAllSkills());
                    break;
                case 2:
                    System.out.println(labelView.createSkill());
                    break;
                case 3:
                    labelView.delete();
                    break;
                case 4:
                    System.out.println(labelView.get());
                    break;
                case 5:
                    labelView.update();
                    break;
            }
        }
    }
    private void info(){
        System.out.println("1 - Get All Info");
        System.out.println("2 - Save skill");
        System.out.println("3 - Delete skill");
        System.out.println("4 - Get skill");
        System.out.println("5 - Update Position");
        System.out.println("6 - Exit");

    }
}
