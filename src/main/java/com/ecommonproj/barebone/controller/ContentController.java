package com.ecommonproj.barebone.controller;

import com.ecommonproj.barebone.controller.dto.ContentDataDto;
import com.ecommonproj.barebone.controller.resource.ContentDataResource;
import com.ecommonproj.barebone.data.model.ContentData;
import com.ecommonproj.barebone.data.service.ContentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ContentController {

    @Autowired
    private ContentDataService contentDataService;

    @Autowired
    private ConfigurableConversionService conversionService;

    @GetMapping("/contentdata")
    public ResponseEntity<Page<ContentDataResource>> getContentList(final Pageable pageable) {
        Page<ContentData> contentData = contentDataService.getAllContent(pageable);

        List<ContentDataResource> contentDataResources = contentData.getContent()
                .stream()
                .map(a -> conversionService.convert(a, ContentDataResource.class))
                .collect(
                        Collectors.toList());

        return ResponseEntity.ok()
                .body(new PageImpl<>(contentDataResources, pageable, contentData.getTotalElements()));
    }

    @PostMapping("/contentdata")
    public ResponseEntity<ContentDataResource> createContentData(@RequestBody @Valid ContentDataDto contentDataDto) {
        ContentData contentData = conversionService.convert(contentDataDto, ContentData.class);
        contentDataService.save(contentData);
        return ResponseEntity.ok()
                .body(conversionService.convert(contentData, ContentDataResource.class));
    }
}
