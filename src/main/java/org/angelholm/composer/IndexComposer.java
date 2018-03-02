package org.angelholm.composer;

import org.angelholm.service.OrganizationService;
import org.hl7.fhir.dstu3.model.Organization;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.lang.Library;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Window;
import org.zkoss.util.media.AMedia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class IndexComposer extends SelectorComposer {

    @Wire
    Button btnLogin;
    @Wire
    Button btnManual;
    @Wire
    Combobox comboTheme;

    @Listen("onClick=#btnTest")
    public void testButton(){
        OrganizationService organizationService = new OrganizationService();
        organizationService.createOrganization(new Organization());
    }
    @Listen("onSelect=#comboTheme")
    public void changeTheme(){
        String theme = comboTheme.getSelectedItem().getLabel();
        Library.setProperty("org.zkoss.theme.preferred", theme);
        Executions.sendRedirect("");
    }

    @Listen("onClick=#btnListPatient")
    public void listPatient(){

        Executions.sendRedirect("/patient/listpatient.zul");

    }

    @Listen("onClick=#btnNewPatient")
    public void newPatient(){

        Window window = (Window)Executions.createComponents("/patient/newpatient.zul", null, null);
        window.doModal();

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
