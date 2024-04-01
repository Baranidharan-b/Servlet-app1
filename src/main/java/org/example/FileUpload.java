package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet("/fileUploadClass")
@MultipartConfig(
        location = "/tmp",
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 20, // 20MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class FileUpload extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        //String filename=req.getParameter("fileName");
        Part filePart=req.getPart("userFile");
        //res.getWriter().println(filename);

        InputStream file=filePart.getInputStream();

        BufferedReader reader=new BufferedReader(new InputStreamReader(file));
        String word;

        while((word=reader.readLine())!=null){
            res.getWriter().print(word);
        }

        reader.close();
        file.close();
    }
}
