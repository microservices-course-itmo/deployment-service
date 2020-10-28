package com.wine.to.up.deployment.service;

import com.wine.to.up.deployment.service.DAO.TemplateRepository;
import com.wine.to.up.deployment.service.entity.ApplicationTemplate;
import com.wine.to.up.deployment.service.entity.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AccessingDataMongodbApplication implements CommandLineRunner {

    @Autowired
    private TemplateRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataMongodbApplication.class, args);
    }

    public List<String> postMappings = Arrays.asList ("8080:8080", "8081:48081");
    public List<String> volumes = Arrays.asList ("opt/kafka");

    Environment env = new Environment("POSTGRES_HOST", "postgres");

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // insert template
        ApplicationTemplate template = new ApplicationTemplate();
        template.setTemplateVersion("15");
        template.setCreatedBy("asukhoa");
        template.setName("order-service");
        template.setPortMappings(postMappings);
        template.setVolumes(volumes);
        template.setEnv(env);
        repository.save(template);

        // fetch all
        System.out.println("Templates found with findAll():");
        System.out.println("-------------------------------");
        for (ApplicationTemplate template2 : repository.findAll()) {
            System.out.println(template2);
        }
        System.out.println();
    }

}