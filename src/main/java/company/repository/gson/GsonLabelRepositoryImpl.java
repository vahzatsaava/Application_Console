package company.repository.gson;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import company.model.Label;
import company.repository.LabelRepository;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GsonLabelRepositoryImpl implements LabelRepository {
    private final Gson GSON = new Gson();
    private final String PATH_TO_JSONFILE = "src/main/resources/labels.json";

    private List<Label> getSkillFromFile() {
        Type targetClassType = new TypeToken<ArrayList<Label>>() {
        }.getType();
        return GSON.fromJson(readStringFromJsonFile(), targetClassType);
    }

    private void writeSkillToFile(List<Label> skills) {
        String jsonCollection = GSON.toJson(skills);
        writeJsonToFile(jsonCollection);
    }

    private void writeJsonToFile(String s) {
        try (FileWriter writer = new FileWriter(PATH_TO_JSONFILE)) {
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readStringFromJsonFile() {
        StringBuilder jsonToString = new StringBuilder();

        try (FileReader reader = new FileReader(PATH_TO_JSONFILE)) {

            while (reader.ready()) {
                jsonToString.append((char) reader.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonToString.toString();
    }

    private Integer generateId(List<Label> skills) {
        Label maxSkill = skills.stream().max(Comparator.comparing(Label::getId)).orElse(null);
        return Objects.isNull(maxSkill) ? 1 : maxSkill.getId() + 1;
    }

    @Override
    public Label getById(Integer id) {
        List<Label> skills = getSkillFromFile();
        return skills.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Label> getAll() {
        return getSkillFromFile();
    }

    @Override
    public Label save(Label skill) {
        List<Label> current = getSkillFromFile();
        if (current == null) {
            current = new ArrayList<>();
        }
        current.add(skill);
        skill.setId(generateId(current));
        writeSkillToFile(current);
        return skill;
    }

    @Override
    public Label update(Label skill) {
        List<Label> currentLabels = getSkillFromFile();
        currentLabels.forEach(s -> {
            if (s.getId().equals(skill.getId())) {
                s.setName(skill.getName());
            }
        });
        writeSkillToFile(currentLabels);
        return skill;
    }

    @Override
    public void deleteById(Integer id) {
        List<Label> skills = getSkillFromFile();
        skills.removeIf(i -> i.getId().equals(id));
        writeSkillToFile(skills);

    }

}
