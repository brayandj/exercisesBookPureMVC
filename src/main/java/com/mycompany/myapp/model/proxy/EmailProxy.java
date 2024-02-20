package com.mycompany.myapp.model.proxy;


import com.mycompany.myapp.model.vo.EmailConfigVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class EmailProxy extends Proxy {
    public static final String NAME = "EmailProxy";
    public static final String TEST_RESULT = "TEST_RESULT";

    public EmailProxy() {
        super(NAME);
    }

    public void testConfiguration(EmailConfigVO configVO) {
        boolean testResult = true;
        sendNotification(TEST_RESULT, testResult);
    }
}
