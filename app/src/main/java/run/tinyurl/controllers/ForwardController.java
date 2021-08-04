package run.tinyurl.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import run.tinyurl.domains.Url;
import run.tinyurl.services.UrlService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ForwardController {
    private UrlService urlService;

    @Autowired
    public void setUrlService(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping(value = "/{code}")
    public ResponseEntity<Void> index(@PathVariable String code) {
        Optional<Url> url = urlService.fetch(code);
        if(url.isPresent()) {
            String urlStr = url.get().getUrl();
            if(!urlStr.startsWith("http:")) {
                urlStr = "http://" + urlStr;
            }
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(urlStr)).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
