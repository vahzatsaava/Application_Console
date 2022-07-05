package company.repository;


import com.google.gson.Gson;
import company.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GsonSkillRepository implements SkillRepository<Skill, Integer> {

    private final String PATH_TO_JSONFILE = "C:\\Users\\User\\IdeaProjects\\Application_Console\\src\\main\\java\\company\\repository\\gson_files\\writer.json";

    @Override
    public Skill getById(Integer integer) throws IOException {
        List<Skill> skills = getAll();
        return skills.get(integer);
    }

    @Override
    public List<Skill> getAll() throws IOException {
        List<Skill> infoSkill = new ArrayList<>();
        String readFromFile = readFromJsonWriter();
        //проверка на пустоту файла
        if (readFromFile.length() == 0) {
            return new ArrayList<>();
        }
        String[] values = readFromJsonWriter().split("},");

        convertToSkillFormatAndAddToList(values, infoSkill);
        return infoSkill;
    }

    @Override
    public void save(Skill skill) throws IOException {
        List<Skill> modifierList = getAll();
        //счетчик для подсчета колличества совпадений
        int count = 0;
        for (int i = 0; i < modifierList.size(); i++) {
            if (modifierList.get(i).getId() == skill.getId()) {
                count++;
            }
            if (count > 0) {
                break;
            }
        }
        if (count == 0) {
            modifierList.add(skill);
        }
        convertListToJson(modifierList);
    }

    @Override
    public Skill update(Skill skill) throws IOException {
        List<Skill> skills = getAll();
        String updatedName = "";
        int countOfSimilarNames = 0;
        int updatedID = 0;

        for (int i = 0; i < skills.size(); i++) {
            if (skill.getId() == skills.get(i).getId()) {
                int pos = skills.indexOf(skills.get(i));
                skills.get(i).setName(skill.getName());
                updatedName = skills.get(i).getName();
                updatedID = skills.get(i).getId();
                deleteById(pos);
                countOfSimilarNames++;
                break;
            }
        }

        save(new Skill(updatedID, updatedName));
        if (countOfSimilarNames == 0) {
            System.out.println("No such elements for update");
        }

        return new Skill(updatedID, updatedName);
    }

    @Override
    public void deleteById(Integer integer) throws IOException {
        List<Skill> skills = getAll();
        skills.remove(integer.intValue());
        convertListToJson(skills);
    }

    /**
     * Метод конвертирует коллекцию в Json формат
     *
     * @param skills
     * @throws IOException
     */
    private void convertListToJson(List<Skill> skills) throws IOException {
        Collections.sort(skills);
        String convertToJson = new Gson().toJson(skills);
        try (FileWriter writer = new FileWriter(PATH_TO_JSONFILE)) {
            writer.write(convertToJson);
        }
    }

    /**
     * Читаем и возвращаем строку с Json файла
     *
     * @return
     * @throws IOException
     */
    private String readFromJsonWriter() throws IOException {
        String readFromGsonFile = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_JSONFILE))) {
            while (reader.ready()) {
                readFromGsonFile = reader.readLine();
            }
        }
        return readFromGsonFile;
    }

    /**
     * Конвертируем в коллекцию значения из Json Файла
     */
    private void convertToSkillFormatAndAddToList(String[] file, List<Skill> skills) {
        for (int i = 0; i < file.length; i++) {
            String[] dropValues = file[i].split(",");
            String[] findId = dropValues[0].split(":");
            String[] findName = dropValues[1].split(":");
            int id = Integer.parseInt(findId[1]);
            String name = findName[1].replaceAll("}", "").replaceAll("\"", "").replaceAll("]", "");
            skills.add(new Skill(id, name));
        }
    }
}
