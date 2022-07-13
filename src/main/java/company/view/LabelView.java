package company.view;

import company.controller.LabelController;
import company.model.Label;

import java.util.List;
import java.util.Scanner;

public class LabelView {
    private final LabelController labelController = new LabelController();
    private final Scanner scannerView = new Scanner(System.in);

    public Label createSkill() {
        System.out.println("Enter the Skill name for save!");
        String name = scannerView.next();
        Label skill = labelController.createSkill(name);
        System.out.printf("Created id - %d ,created name - %s ", skill.getId(), skill.getName());
        return skill;
    }

    public List<Label> getAllSkills() {
        return labelController.getList();
    }

    public void delete() {
        System.out.println("Enter id of the Skills for delete");
        Integer number = scannerView.nextInt();
        labelController.delete(number);
    }

    public Label update() {
        System.out.println("Enter the Skill id for update");
        Integer id = scannerView.nextInt();
        System.out.println("Enter the Skill name for update");
        String name = scannerView.next();
        return labelController.update(new Label(id, name));
    }

    public Label get() {
        System.out.println("Enter id number ");
        Integer id = scannerView.nextInt();
        return labelController.get(id);
    }

    public void close() {
        scannerView.close();
    }
}
