package com.example.demo.service;

import com.example.demo.pojos.Url;
import com.example.demo.repository.UrlRepository;
import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class UrlService {
    @Inject
    UrlRepository urlRepository;

    public List<Url> getUrls(){
        return urlRepository.findAll();
    }

    @Transactional
    public String shortenUrl(HttpServletRequest servelet, String longUrl){
        Url url = new Url();
        urlRepository.save(url);
        Base62 base62 = Base62.createInstance();
        final byte[] encoded = base62.encode(url.id.toString().getBytes());
        url.shortUrl = new String(encoded);
        longUrl = longUrl.replaceAll("(^\\w+:|^)\\/\\/", "");
        url.longUrl = longUrl;
        urlRepository.save(url);
        return servelet.getRequestURL() + url.shortUrl;
    }

    public RedirectView redirectUrl(String url) {
        Base62 base62 = Base62.createInstance();
        final byte[] decoded = base62.decode(url.getBytes());

        Optional<Url> longUrl = urlRepository.findById(Integer.valueOf(new String(decoded)));

        if(longUrl.isPresent()){
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("https://"+longUrl.get().longUrl);
            return redirectView;
        }
        return null;
    }
}
