package com.serv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Student;

@WebServlet("/FORM")
public class Serv1 extends HttpServlet {
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        int maths = Integer.parseInt(req.getParameter("maths"));
        int english = Integer.parseInt(req.getParameter("english"));

        PrintWriter writer = res.getWriter();

        Student stud = new Student();
        int i = stud.save(name, email, maths, english);
        if (i == 1) {
            writer.print(true);
        } else {
            writer.print(false);
        }	
        // Display the saved data
        List<List<Object>> dataList = stud.getAll();
        writer.println("<h2>Saved Data:</h2>");

        for (List<Object> rowData : dataList) {
            writer.println("Name: " + rowData.get(0) + "");
            writer.println("Email: " + rowData.get(1) + "");
            writer.println("Maths: " + rowData.get(2) + "");
            writer.println("English: " + rowData.get(3) + "");
        }
        writer.println("");
    }
}
