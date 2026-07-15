package com.chownrani.urlshortener.model.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import com.chownrani.urlshortener.model.UrlModel;
import com.chownrani.urlshortener.service.SequenceGeneratorService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UrlModelListener extends AbstractMongoEventListener<UrlModel> {
     
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<UrlModel> event) {
        if (event.getSource().getId().intValue() < 1) {
            event.getSource().setId(Long.valueOf(
                sequenceGeneratorService.generateSequence(UrlModel.SEQUENCE_NAME)
            ));
        }
    }
}
