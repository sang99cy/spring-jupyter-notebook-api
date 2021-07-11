package com.quangsang.springjupyternotebookapi.controller;

import com.quangsang.springjupyternotebookapi.service.JupyterBrowser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jupyter-notebook")
public class NoteBookController {

    private final JupyterBrowser jupyterBrowser;

    public NoteBookController(JupyterBrowser jupyterBrowser) {
        this.jupyterBrowser = jupyterBrowser;
    }

    @PostMapping("/login")
    public String getSessionId(){
        String session = jupyterBrowser.getToken();
        return session;
    }

    @GetMapping("/contents")
    public String getDirectory(){

        return "";
    }
}
