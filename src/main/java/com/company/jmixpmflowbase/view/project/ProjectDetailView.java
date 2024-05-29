package com.company.jmixpmflowbase.view.project;

import com.company.jmixpmflowbase.entity.Document;
import com.company.jmixpmflowbase.entity.Project;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.download.Downloader;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "projects/:id", layout = MainView.class)
@ViewController("Project.detail")
@ViewDescriptor("project-detail-view.xml")
@EditedEntityContainer("projectDc")
public class ProjectDetailView extends StandardDetailView<Project> {
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