package run.tinyurl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.tinyurl.domains.Url;
import run.tinyurl.repositories.UrlRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    private UrlRepository urlRepository;
    final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Autowired
    public void setUrlRepository(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url generate(Url url) {
        url.setCode(generateCode());
        return urlRepository.save(url);
    }

    private String generateCode() {
        int n = new Random().ints(901356496, 916132832).findAny().getAsInt();
        StringBuilder sb = new StringBuilder();
        while(n > 0)
        {
            sb.append(BASE62[n % 62]);
            n /= 62;
        }
        return sb.reverse().toString();
    }

    public Optional<Url> fetch(String code) {
        return urlRepository.getByCode(code);
    }
}
