package com.exampledemo.service.scan;

import org.springframework.stereotype.Service;

@Service
public interface AccessibilityScanService {

    public void launch(String url);

    public void scanPdf(String fileLocation);

}
