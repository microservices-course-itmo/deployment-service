package com.wine.to.up.deployment.service.command.processor;

import org.springframework.stereotype.Component;


@Component("commandProcessor")
public class CommandProcessor {

    /**
     * Processes the given command and returns answer from console
     *
     * @param command command to process
     * @return output from stdout
     */
    public String processCommand(final String command) {
        return "stub";
    }
}
