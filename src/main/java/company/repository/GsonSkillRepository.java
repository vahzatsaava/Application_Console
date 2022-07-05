package company.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import company.model.Skill;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GsonSkillRepository implements SkillRepository<Skill,Integer> {

    private final String pathToWriterJson = "C:\\Users\\User\\IdeaProjects\\Console_Application\\src\\main\\java\\com\\company\\repository\\gson_files\\writer.json";

    @Override
    public Skill getById(Integer integer) throws IOException {
        List<Skill> skills = getAll();
        return skills.get(integer);
    }


    @Override
    public List<Skill> getAll() throws IOException {
        List<Skill> infoSkill = new ArrayList<>();
        String[] values = readFromJsonWriter().split("},");
        String exp = "";
        for (int i = 0; i < values.length; i++) {
            String[] dropValues = values[i].split(",");
            String[] findId = dropValues[0].split(":");
            String[] findName = dropValues[1].split(":");
            int id = Integer.parseInt(findId[1]);
            String name = findName[1].replaceAll("}", "").replaceAll("\"", "").replaceAll("]", "");

            infoSkill.add(new Skill(id, name));
        }
        return infoSkill;
    }

    @Override
    public void save(Skill skill) throws IOException {
        List<Skill> modifierList = getAll();
        modifierList.add(skill);
        String convertToJson = new Gson().toJson(modifierList);
        ObjectMapper mapper = new ObjectMapper();

    }

    @Override
    public Skill update(Skill skill) {

        return null;
    }

    @Override
    public void deleteById(Integer integer) throws IOException {
        List<Skill> skills = getAll();
        skills.removeIf(iterator -> iterator.getId() == integer);
    }

    /**
     * Читаем и возвращаем строку с Json файла
     *
     * @return
     * @throws IOException
     */
    private String readFromJsonWriter() throws IOException {
        String readFromGsonFile = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToWriterJson))) {
            while (reader.ready()) {
                readFromGsonFile = reader.readLine();
            }
        }
        return readFromGsonFile;
    }
}
