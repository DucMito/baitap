package com.example.demo.utils.file;

import com.example.demo.model.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JsonFileReader implements IFileReader{
    public List<Person> readFile(String filePath) {
        log.info("Json file");
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Person>>() {});
        } catch (IOException e) {
            log.error("Error reading JSON file: {}", filePath, e);
            return List.of();
        }
    }
}
