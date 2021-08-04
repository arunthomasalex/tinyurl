package run.tinyurl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import run.tinyurl.domains.Url;
import run.tinyurl.responses.UrlResponse;
import run.tinyurl.services.UrlService;

@RestController
@RequestMapping("/add")
public class TinyController {
    private UrlService urlService;

    @Autowired
    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping(consumes = "application/json")
    public UrlResponse add(@RequestBody Url url) {
        return new UrlResponse(urlService.generate(url));
    }
}
