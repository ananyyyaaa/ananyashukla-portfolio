package com.learnsphere.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.learnsphere.model.Group;
import com.learnsphere.model.Task;
import com.learnsphere.model.User;
import com.learnsphere.service.GroupService;
import com.learnsphere.service.TaskService;
import com.learnsphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/certificate")
@CrossOrigin(origins = "http://localhost:5173")
public class CertificateController {

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/generate/{groupId}/{userId}")
    public ResponseEntity<byte[]> generateCertificate(@PathVariable Long groupId, @PathVariable Long userId) {
        // Check if user exists
        User user = userService.getUserById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check if group exists
        Group group = groupService.getGroupById(groupId).orElse(null);
        if (group == null) {
            return ResponseEntity.badRequest().body(null);
        }

        // Check if user has completed all tasks in the group
        List<Task> tasks = taskService.getTasksByUserId(userId);
        List<Task> groupTasks = tasks.stream()
                .filter(t -> t.getGroupId().equals(groupId))
                .toList();

        if (groupTasks.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        boolean allCompleted = groupTasks.stream()
                .allMatch(Task::isCompleted);

        if (!allCompleted) {
            return ResponseEntity.status(403).body(null);
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);
            document.open();

            document.add(new Paragraph("Certificate of Completion"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("This certifies that"));
            document.add(new Paragraph(user.getName()));
            document.add(new Paragraph("has successfully completed the group"));
            document.add(new Paragraph(group.getName()));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Completion Date: " + LocalDate.now()));

            document.close();

            byte[] pdfBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "certificate.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);

        } catch (DocumentException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
