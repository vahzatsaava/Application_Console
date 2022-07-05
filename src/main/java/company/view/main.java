package company.view;


import com.google.gson.Gson;
import company.model.Skill;
import company.repository.GsonSkillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException {
        GsonSkillRepository skillRepository = new GsonSkillRepository();

        //String s = skillRepository.getCollectionsInfo();
        System.out.println(skillRepository.getAll());
        //System.out.println(s);
        List<Skill> ar = new ArrayList<>();
        ar.add(new Skill(4,"435345"));
        ar.add(new Skill(5,"dsfsdf"));
        String res = new Gson().toJson(ar);
        System.out.println(res);
        List<Skill> skills = new ArrayList<>();
    }
}
