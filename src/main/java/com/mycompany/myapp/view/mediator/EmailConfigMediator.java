package com.mycompany.myapp.view.mediator;

import com.mycompany.myapp.controller.constant.MyAppConstants;
import com.mycompany.myapp.model.proxy.EmailConfigProxy;
import com.mycompany.myapp.model.proxy.EmailProxy;
import com.mycompany.myapp.view.components.EmailConfig;
import com.mycompany.myapp.view.event.MyAppEvent;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.mediator.Mediator;

public class EmailConfigMediator extends Mediator {
    public EmailConfigMediator instance;
    public static final String NAME = "EmailConfigMediator";
    private EmailConfigProxy emailConfigProxy;

    public EmailConfigMediator(EmailConfig viewComponent) {
        super(NAME, viewComponent);
    }

    private EmailConfig emailConfig() {
        return (EmailConfig) viewComponent;
    }

    @Override
    public void onRegister() {
        emailConfig().addEmailConfigListener(this::saveEmailConfig);
        emailConfig().addTestEmailConfigListener(this::testEmailConfig);
        emailConfigProxy = (EmailConfigProxy) getFacade().retrieveProxy(EmailConfigProxy.NAME);

    }

    private void saveEmailConfig(MyAppEvent event) {
        emailConfigProxy.setEmailConfigVO( event.getData());
    }

    private void testEmailConfig(MyAppEvent event) {
        sendNotification(MyAppConstants.PERFORM_EMAIL_TEST, emailConfig().getVo());
    }

    @Override
    public String[] listNotificationInterests() {
        return new String[] {
                MyAppConstants.SHOW_CONFIG, EmailProxy.TEST_RESULT
        };
    }

    @Override
    public void handleNotification(INotification note) {
        switch (note.getName()) {
            case MyAppConstants.SHOW_CONFIG:
                emailConfig().setVo(emailConfigProxy.getEmailConfigVO());
                break;
            case EmailProxy.TEST_RESULT:
                emailConfig().setTestResult((Boolean) note.getBody());
                break;
        }
    }
}