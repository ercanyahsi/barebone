package com.ecommonproj.barebone.config;

import com.ecommonproj.barebone.controller.dto.ContentDataDto;
import com.ecommonproj.barebone.controller.resource.ContentDataResource;
import com.ecommonproj.barebone.data.model.ContentData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.GenericConversionService;

@Configuration
public class ConverterConfig {

    @Bean
    public ConfigurableConversionService conversionService() {
        final ConfigurableConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(new ContentDataToContentDataResourceConverter());
        conversionService.addConverter(new ContentDataDtoToContentDataConverter());
        return conversionService;
    }

    private static class ContentDataToContentDataResourceConverter implements Converter<ContentData, ContentDataResource> {

        @Override
        public ContentDataResource convert(final ContentData contentData) {
            ContentDataResource result = null;

            if (contentData != null) {
                result = ContentDataResource.builder()
                        .description(contentData.getDescription())
                        .build();
            }

            return result;
        }
    }

    private static class ContentDataDtoToContentDataConverter implements Converter<ContentDataDto, ContentData> {

        @Override
        public ContentData convert(final ContentDataDto contentDataDto) {
            ContentData contentData = null;
            if (contentDataDto != null) {
                contentData = new ContentData();
                contentData.setDescription(contentDataDto.getDescription());
            }

            return contentData;
        }
    }


}
