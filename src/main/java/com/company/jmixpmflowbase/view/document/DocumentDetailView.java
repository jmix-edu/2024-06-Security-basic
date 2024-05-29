package com.company.jmixpmflowbase.view.document;

import com.company.jmixpmflowbase.entity.Document;

import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "documents/:id", layout = MainView.class)
@ViewController("Document.detail")
@ViewDescriptor("document-detail-view.xml")
@EditedEntityContainer("documentDc")
public class DocumentDetailView extends StandardDetailView<Document> {
}