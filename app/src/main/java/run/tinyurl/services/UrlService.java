package run.tinyurl.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import run.tinyurl.domains.Settings;
import run.tinyurl.domains.Url;
import run.tinyurl.repositories.SettingRespository;
import run.tinyurl.repositories.UrlRepository;
import run.tinyurl.utils.ApplicationConstant;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class UrlService {
    private ReentrantLock lock = new ReentrantLock(true);
    private UrlRepository urlRepository;
    private SettingRespository settingRespository;
    final char[] BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Autowired
    public void setUrlRepository(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @Autowired
    public void setSettingsRespository(SettingRespository settingRespository) { this.settingRespository = settingRespository; }

    public Url generate(Url url) {
        url.setCode(generateCode());
        return urlRepository.save(url);
    }

    private String generateCode() {
        lock.lock();
        int n = getValue();
        lock.unlock();
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(BASE62[n % 62]);
            n /= 62;
        }
        return sb.reverse().toString();
    }

    private int getValue() {
        Optional<Settings> settings = settingRespository.getByName(ApplicationConstant.COUNTER_NAME);
        Settings counter = settings.orElseGet(() -> {
            Settings obj = new Settings();
            obj.setName(ApplicationConstant.COUNTER_NAME);
            obj.setValue(ApplicationConstant.COUNTER_INITIAL_VALUE);
            return obj;
        });
        int n = Integer.parseInt(counter.getValue());
        counter.setValue(String.valueOf(((n + 1) % ApplicationConstant.COUNTER_LIMIT)));
        settingRespository.save(counter);
        return n;
    }

    public Optional<Url> fetch(String code) {
        return urlRepository.getByCode(code);
    }
}
