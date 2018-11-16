package cn.com.cslc.aw.ws.client;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
  
public class ClientPasswordCallback implements CallbackHandler {  
    public void handle(Callback[] callbacks) throws IOException,  
            UnsupportedCallbackException {  
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];  
        String ident = "aw_ws_client";  
        String passwd = "Passw0rd";  
        pc.setPassword(passwd);  
        pc.setIdentifier(ident);  
  
    }  
}  