package com.wine.to.up.deployment.service.command.provider.impl;

import com.wine.to.up.deployment.service.command.processor.CommandProcessor;
import com.wine.to.up.deployment.service.command.provider.ImageCommandProvider;
import com.wine.to.up.deployment.service.command.response.parser.GetImagesCommandResponseParser;
import com.wine.to.up.deployment.service.dto.ImageDto;
import com.wine.to.up.deployment.service.enums.ImageRequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component("getImagesCommandProvider")
public class GetImagesCommandProvider implements ImageCommandProvider {

    private CommandProcessor commandProcessor;
    private GetImagesCommandResponseParser parser;

    @Autowired
    public void setCommandProcessor(final CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    @Autowired
    public void setParser(final GetImagesCommandResponseParser parser) {
        this.parser = parser;
    }


    @Override
    public boolean accepts(ImageDto imageRequestDto) {
        return imageRequestDto.getImageRequestType() == ImageRequestType.FIND;
    }

    @Override
    public ImageDto process(ImageDto imageDto) {
        final Map<String, Object> payload = new HashMap<>();
        parser.parse(commandProcessor.processCommand("dai mne vse image"), payload);
        return ImageDto.builder().payload(Collections.unmodifiableMap(payload)).build();
    }
}
