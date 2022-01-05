package com.suliborski.appvideo;

import com.suliborski.appvideo.controller.Controller;
import com.suliborski.appvideo.model.Model;
import com.suliborski.appvideo.view.View;

public class AppVideo {

    public static void main(String[] args) {
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        view.setVisible(true);
    }
}
