package company.view;

import java.util.Scanner;

public class MainView {
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final Scanner scanner = new Scanner(System.in);

    public void MainMenuForLabels() {
        while (true) {
            info();
            int value = scanner.nextInt();
            if (6 <= value || value < 0) {
                break;
            }
            switch (value) {
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

    public void MainMenuForPosts() {
        while (true) {
            info();
            int value = scanner.nextInt();
            if (6 <= value || value < 0) {
                break;
            }
            switch (value) {
                case 1:
                    System.out.println("All skills :" + postView.getAllPosts());
                    break;
                case 2:
                    System.out.println(postView.createPost());
                    break;
                case 3:
                    postView.delete();
                    break;
                case 4:
                    System.out.println(postView.get());
                    break;
                case 5:
                    postView.update();
                    break;
            }
        }
    }

    private void info() {
        System.out.println("1 - Get All Info");
        System.out.println("2 - Save ");
        System.out.println("3 - Delete ");
        System.out.println("4 - Get ");
        System.out.println("5 - Update Position");
        System.out.println("6 - Exit");

    }
}
