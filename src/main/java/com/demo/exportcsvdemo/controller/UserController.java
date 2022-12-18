package com.demo.exportcsvdemo.controller;

import com.demo.exportcsvdemo.domain.Users;
import com.demo.exportcsvdemo.service.AnnotationReflection;
import com.demo.exportcsvdemo.service.UserService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AnnotationReflection annotationReflection;

    @GetMapping("/export-users")
    public void exportCSV(HttpServletResponse resp) throws Exception{

        String filename = "users.csv";

        resp.setContentType("text/csv");
        resp.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");

        // create a csv writer
        StatefulBeanToCsv<Users> writer = new StatefulBeanToCsvBuilder<Users>(resp.getWriter())
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();

        annotationReflection.test();

        // write all users to csv file
        writer.write(userService.listUsers());

    }

}
