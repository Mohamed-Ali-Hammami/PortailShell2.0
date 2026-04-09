package com.tn.shell.ui.paie;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> img;
     
    @PostConstruct
    public void init() {
        img = new ArrayList<String>();
        for (int i = 1; i <= 2; i++) {
            img.add("image" + i + ".png");
        }
    }
 
    public List<String> getImg() {
        return img;
    }
}