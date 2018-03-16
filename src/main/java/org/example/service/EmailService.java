package org.example.service;

import org.example.model.Greeting;

import java.util.concurrent.Future;

public interface EmailService {

    Boolean send (Greeting greeting);

    void sendAsync(Greeting greeting);

    Future<Boolean> sendAsyncWithResult(Greeting greeting);
}
