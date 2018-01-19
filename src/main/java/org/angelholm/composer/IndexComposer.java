package org.angelholm.composer;

import org.angelholm.service.TestService;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Button;
import org.zkoss.zul.Window;
import org.zkoss.util.media.AMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class IndexComposer extends SelectorComposer {

    Button btnLogin;
    Button btnManual;

    @Listen("onClick=#btnTest")
    public void test(){

        TestService test = new TestService();

        test.test();

    }
    @Listen("onClick=#btnManual")
    public void openPdfWindow(){

        File f = new File( Sessions.getCurrent().getWebApp().getServletContext().getRealPath("/") + "/resources/pdf/manual.pdf");

        AMedia fileContent = null;
        try {
            fileContent = new AMedia(f, null, null);
            HashMap<String,Object> args = new HashMap<>(2);
            args.put("windowTitle", Labels.getLabel("index.windowManualTitle"));
            args.put("iframeContent", fileContent);

            Window window = (Window)Executions.createComponents("/pdfWindow.zul", null, args);
            window.doModal();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Listen("onClick=#btnLogin")
    public void openLoginWindow(){
        Window window = (Window)Executions.createComponents("/login.zul", null, null);
        window.doModal();
    }

    @AfterCompose
    public void doAfterCompose (Component comp) throws Exception {
        super.doAfterCompose(comp);
    }

}
