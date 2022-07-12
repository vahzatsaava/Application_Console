package company.controller;

import company.model.Label;
import company.repository.LabelRepository;
import company.repository.gson.GsonLabelRepositoryImpl;

import java.util.List;

public class LabelController {
    private final LabelRepository labelRepository = new GsonLabelRepositoryImpl();

    public Label createSkill(String name) {
        Label skill = new Label(0, name);
        return labelRepository.save(skill);
    }

    public List<Label> getList() {
        return labelRepository.getAll();
    }

    public Label update(Label name) {
        return labelRepository.update(name);
    }

    public void delete(Integer id) {
        labelRepository.deleteById(id);
    }

    public Label get(Integer id) {
        return labelRepository.getById(id);
    }
}
