package com.mycompany.myapp.model.proxy;

import java.util.prefs.Preferences;

import com.mycompany.myapp.model.vo.EmailConfigVO;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

/**
 * Este es un ejemplo de Proxy para persistir elementos de configuración del correo electrónico en AIR Almacén local cifrado (ELS) para MyApp.
 */
public class EmailConfigProxy extends Proxy {

    public static final String NAME = "EmailConfigProxy";
    public static final String EMAIL_HOST = NAME + "/email/config/host";
    public static final String EMAIL_PORT = NAME + "/email/config/port";
    public static final String EMAIL_USER = NAME + "/email/config/user";
    public static final String EMAIL_PASS = NAME + "/email/config/pass";

    private Preferences prefs;

    public EmailConfigProxy() {
        super(NAME);
        prefs = Preferences.userRoot().node(NAME);
        /**
         *super(NAME);: Esta línea llama al constructor de la clase base Proxy y pasa el nombre de la clase EmailConfigProxy como argumento.
         * La palabra clave super se utiliza en Java para llamar a un constructor de la clase base.
         * En este caso, se está llamando al constructor de la clase Proxy y se está pasando el nombre de la clase EmailConfigProxy como argumento.
         * Esto es importante porque el constructor de la clase Proxy espera recibir el nombre de la clase como argumento.
         * prefs = Preferences.userRoot().node(NAME); Esta línea crea un objeto Preferences que se utiliza para almacenar las preferencias de la aplicación.
         * El método userRoot() devuelve el nodo raíz de las preferencias del usuario actual.
         * El método node(NAME) crea un nodo con el nombre de la clase EmailConfigProxy como nombre.
         * Esto significa que todas las preferencias que se almacenen en este nodo estarán asociadas con la clase EmailConfigProxy
         */
    }

    public EmailConfigVO getEmailConfigVO() {
        String host = retrieve(EMAIL_HOST);
        int port = Integer.parseInt(retrieve(EMAIL_PORT));
        String user = retrieve(EMAIL_USER);
        String password = retrieve(EMAIL_PASS);
        return new EmailConfigVO(host, port, user, password);
    }

    public void setEmailConfigVO(EmailConfigVO config) {
        store(EMAIL_HOST, config.getHost());
        store(EMAIL_PORT, String.valueOf(config.getPort()));
        store(EMAIL_USER, config.getUser());
        store(EMAIL_PASS, config.getPassword());
        this.setData(config);
    }

    private void store(String key, String value) {
        prefs.put(key, value);
    }

    private String retrieve(String key) {
        return prefs.get(key, "");
    }
}
