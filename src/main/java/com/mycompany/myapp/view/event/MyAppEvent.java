package com.mycompany.myapp.view.event;

import com.mycompany.myapp.model.proxy.EmailConfigProxy;
import com.mycompany.myapp.model.vo.EmailConfigVO;

public class MyAppEvent {
    public static final String SAVE_EMAIL_CONFIG = "SAVE_EMAIL_CONFIG";
    public static final String TEST_EMAIL_CONFIG = "TEST_EMAIL_CONFIG";
    public static final String PERFORM_EMAIL_TEST = "PERFORM_EMAIL_TEST";
    private String type;
    private EmailConfigVO data;

    public MyAppEvent(String type, EmailConfigVO data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public EmailConfigVO getData() {
        return data;
    }
}
