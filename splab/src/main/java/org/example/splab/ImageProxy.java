package org.example.splab;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ImageProxy extends BaseElement {
    private String url;

    @Transient
    private Image realImage;

    public ImageProxy() {}

    public ImageProxy(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image loadImage() {
        if (realImage == null) {
            realImage = new Image(url);
        }
        return realImage;
    }

    @Override
    public void print() {
        loadImage().print();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
