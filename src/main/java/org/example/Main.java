package org.example;

import com.mycompany.myapp.model.proxy.EmailConfigProxy;
import com.mycompany.myapp.model.vo.EmailConfigVO;
import com.mycompany.myapp.view.components.EmailConfig;
import javafx.application.Application;


// Main.java
public class Main {
    public static void main(String[] args) {
        Application.launch(EmailConfig.class, args);
    }
}
