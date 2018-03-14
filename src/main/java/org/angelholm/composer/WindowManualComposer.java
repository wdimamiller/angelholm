package org.angelholm.composer;

import org.zkoss.util.media.AMedia;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Window;

public class WindowManualComposer extends GenericForwardComposer {

    Iframe iframePdfContent;
    Window windowPdfBrowser;

    @Override
    public void doAfterCompose(Component comp) throws Exception {

        super.doAfterCompose(comp);
        windowPdfBrowser.setTitle((String) arg.get("windowTitle"));
        iframePdfContent.setContent((AMedia)arg.get("iframeContent"));

    }
}
