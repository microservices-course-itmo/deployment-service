package com.wine.to.up.deployment.service.command.provider.impl;

import com.wine.to.up.deployment.service.command.processor.CommandProcessor;
import com.wine.to.up.deployment.service.command.provider.ImageCommandProvider;
import com.wine.to.up.deployment.service.dto.ImageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Component("testImageCommandProvider")
public class TestImageCommandProvider implements ImageCommandProvider {

    private CommandProcessor commandProcessor;

    @Autowired
    public void setCommandProcessor(final CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Override
    public boolean accepts(ImageDto imageRequestDto) {
        //return imageRequestDto.getImageRequestType() == ImageRequestType.FIND;
        return false;
    }

    @Override
    public ImageDto process(ImageDto imageDto) {
        final String answer = commandProcessor.processCommand("echo hi");
        final Map<String, Object> payload = new HashMap<>();
        payload.put("answer", answer);
        return ImageDto.builder().payload(Collections.unmodifiableMap(payload)).build();
    }
}
