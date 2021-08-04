package run.tinyurl.responses;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import run.tinyurl.domains.Url;

public class UrlResponse {
    private String code;
    private String tinyUrl;
    private String url;
    public UrlResponse(Url url) {
        this.code = url.getCode();
        this.tinyUrl = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString() + "/" + url.getCode();
        this.url = url.getUrl();
    }
    public UrlResponse(String code, String tinyUrl, String url) {
        this.code = code;
        this.tinyUrl = tinyUrl;
        this.url = url;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTinyUrl() {
        return tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
