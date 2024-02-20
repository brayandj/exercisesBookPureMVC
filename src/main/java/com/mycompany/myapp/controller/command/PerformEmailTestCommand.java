package com.mycompany.myapp.controller.command;

import com.mycompany.myapp.model.proxy.EmailProxy;
import com.mycompany.myapp.model.vo.EmailConfigVO;
import org.puremvc.java.multicore.interfaces.INotification;
import org.puremvc.java.multicore.patterns.command.SimpleCommand;

public class PerformEmailTestCommand extends SimpleCommand {
    @Override
    public void execute(INotification note) {
        // Obtener la configuración del correo electrónico del cuerpo de la notificación
        EmailConfigVO config = (EmailConfigVO) note.getBody();
        // Obtener el EmailProxy
        EmailProxy emailProxy = (EmailProxy) getFacade().retrieveProxy(EmailProxy.NAME);
        // Invoca la prueba de configuración del correo electrónico.
        // El EmailProxy enviará el resultado como
        // un booleano en el cuerpo de una nota EmailProxy.TEST_RESULT,
        // que será gestionada por el EmailConfigMediator
        emailProxy.testConfiguration(config);


    }
}
