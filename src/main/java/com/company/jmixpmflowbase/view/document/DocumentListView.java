package com.company.jmixpmflowbase.view.document;

import com.company.jmixpmflowbase.entity.Document;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.download.Downloader;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "documents", layout = MainView.class)
@ViewController("Document.list")
@ViewDescriptor("document-list-view.xml")
@LookupComponent("documentsDataGrid")
@DialogMode(width = "64em")
public class DocumentListView extends StandardListView<Document> {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Supply(to = "documentsDataGrid.file", subject = "renderer")
    private Renderer<Document> documentsDataGridFileRenderer() {
        return new ComponentRenderer<JmixButton, Document>(document -> {
            JmixButton button = uiComponents.create(JmixButton.class);
            button.setText(document.getFile().getFileName());
            button.setThemeName("tertiary-inline");
            button.addClickListener(clickEvent -> {
                downloader.download(document.getFile());
            });

            return button;
        } );
    }
}