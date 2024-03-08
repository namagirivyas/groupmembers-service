package com.bits.groupmembers.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bits.groupmembers.dto.GroupMembersInfoDto;
import com.bits.groupmembers.dto.StudentInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroupMembersService {

    @Autowired
    private ObjectMapper mapper;

    public GroupMembersInfoDto getGroupMembersDetails() {
        String filePath = "com/bits/groupmembers/service/Students.json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
       //     log.error("File '{}' not found", filePath);
            return null;
        }

        try {
            return mapper.readValue(inputStream, GroupMembersInfoDto.class);
        } catch (IOException e) {
         //   log.error("Error occurred while reading student information", e);
            return null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
           //     log.error("Error occurred while closing input stream", e);
            }
        }
    }

    public List<StudentInfoDto> getAllGroupMembersForGivenElectives(String electives) {
        String filePath = "com/bits/groupmembers/service/Students.json";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
         //   log.error("File '{}' not found", filePath);
            return Collections.emptyList();
        }

        try {
            File file = File.createTempFile("Students", ".json");
            file.deleteOnExit();
            try (OutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
            return getAllGroupMembersFrom(electives, file);
        } catch (IOException e) {
         //   log.error("Error occurred while creating temporary file", e);
            return Collections.emptyList();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
          //     log.error("Error occurred while closing input stream", e);
            }
        }
    }

    public List<StudentInfoDto> getAllGroupMembersFrom(String electives, File sourceData) {

        try {
        //    log.info("Reading student information from JSON file");
            GroupMembersInfoDto groupMembersInfoDto = mapper.readValue(sourceData, GroupMembersInfoDto.class);
            List<StudentInfoDto> studentInfoDtoList = groupMembersInfoDto.getStudents();

        //    log.info("Retrieving student info for given electives {}", electives);
            return studentInfoDtoList.stream()
                    .filter(studentInfoDto -> studentInfoDto.getElectiveCourses().contains(electives))
                    .collect(Collectors.toList());
        } catch (IOException e) {
         //   log.error("Error occurred while reading student information", e);
            return Collections.emptyList();
        }
    }

}
