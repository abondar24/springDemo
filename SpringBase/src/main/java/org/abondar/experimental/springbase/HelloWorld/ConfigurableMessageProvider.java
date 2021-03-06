package org.abondar.experimental.springbase.HelloWorld;


import org.springframework.stereotype.Service;

/**
 * Created by abondar on 03.07.16.
 */

@Service("messageProviderConf")
public class ConfigurableMessageProvider implements MessageProvider{
    private String message;


    public ConfigurableMessageProvider( String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
