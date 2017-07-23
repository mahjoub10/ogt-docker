package com.show.car.service;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Service responsible of retrieving message base in  locale and key.
 */
@Component
public class LocalMessageResource {

    private Logger log = LoggerFactory.getLogger(LocalMessageResource.class) ;

    private MessageSource messageSource ;

    public LocalMessageResource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }


    /**
     *
     * @param key
     * @return
     */
    public  String getMessage(String key, String [] params){
        try {
            String langKey = "en" ;//TODO set up multi language config ;
            log.info("Get the message for key : '{}' base en lang '{}'",key, langKey);
            Locale locale =  Locale.forLanguageTag(langKey);
            return messageSource.getMessage(key, params,locale);
        }catch (Exception ex){
            log.error("No message found for key : '{}'",key);
            return StringUtils.EMPTY;
        }

    }
}
