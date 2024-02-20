package com.mycompany.myapp.view.components;

import com.mycompany.myapp.controller.constant.MyAppConstants;
import com.mycompany.myapp.model.proxy.EmailConfigProxy;
import com.mycompany.myapp.model.vo.EmailConfigVO;
import com.mycompany.myapp.view.event.MyAppEvent;

import com.mycompany.myapp.view.mediator.EmailConfigMediator;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.puremvc.java.multicore.patterns.facade.Facade;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EmailConfig extends Application {
    private EmailConfigVO vo;
    private boolean testResult;

    private List<Consumer<MyAppEvent>> emailConfigListeners = new ArrayList<>();
    private List<Consumer<MyAppEvent>> testEmailConfigListeners = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        Button testButton = new Button("Test");
        testButton.setOnAction(actionEvent -> {
            testEmailConfig();
        });
        // Crear una instancia de EmailConfigProxy
        EmailConfigProxy emailConfigProxy = new EmailConfigProxy();
        // Crear una instancia de EmailConfigVO
        EmailConfigVO emailConfigVO = new EmailConfigVO("example.com", 25, "user@example.com", "123456");
        // Establecer la configuración del correo electrónico en el proxy
        emailConfigProxy.setEmailConfigVO(emailConfigVO);
        // Obtener la configuración del correo electrónico del proxy
        EmailConfigVO retrievedEmailConfigVO = (EmailConfigVO) emailConfigProxy.getData();

        VBox root = new VBox(5);
        TextField txtHost = new TextField(retrievedEmailConfigVO.getHost());
        TextField txtPort = new TextField(String.valueOf(retrievedEmailConfigVO.getPort()).toString());
        TextField txtUser = new TextField(retrievedEmailConfigVO.getUser());
        TextField txtPassword = new TextField(retrievedEmailConfigVO.getPassword());

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(txtHost, txtPort, txtUser, txtPassword, testButton);

        Scene scene = new Scene(root,100,150);
        stage.setScene(scene);
        stage.setTitle("Test PureMVC Mediator");
        stage.show();
        Facade facade = new Facade("Facade");
        facade.registerMediator(new EmailConfigMediator(this));
    }

    public void addEmailConfigListener(Consumer<MyAppEvent> listener) {
        emailConfigListeners.add(listener);
    }

    public void addTestEmailConfigListener(Consumer<MyAppEvent> listener) {
        testEmailConfigListeners.add(listener);
    }

    private void testEmailConfig() {
        // Crear un evento personalizado con el EmailConfigVO
        MyAppEvent event = new MyAppEvent(MyAppConstants.PERFORM_EMAIL_TEST, vo );

        // Notificar a los listeners registrados para el evento PERFORM_EMAIL_TEST
        for (Consumer<MyAppEvent> listener : testEmailConfigListeners) {
            listener.accept(event);
        }
    }

    public EmailConfigVO getVo() {
        return vo;
    }

    public void setVo(EmailConfigVO vo) {
        this.vo = vo;
    }

    public void setTestResult(boolean testResult) {
        this.testResult = testResult;
    }
}