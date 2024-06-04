package com.company.jmixpmflowbase.security;

import com.company.jmixpmflowbase.entity.Document;
import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.Task;
import com.company.jmixpmflowbase.entity.User;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityflowui.role.annotation.MenuPolicy;
import io.jmix.securityflowui.role.annotation.ViewPolicy;

@ResourceRole(name = "ProjectManagement", code = ProjectManagementRole.CODE, scope = "UI")
public interface ProjectManagementRole {
    String CODE = "project-management";


    @EntityAttributePolicy(entityClass = Document.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Document.class, actions = EntityPolicyAction.ALL)
    void document();

    @EntityAttributePolicy(entityClass = Project.class, attributes = "endDate", action = EntityAttributePolicyAction.VIEW)
    @EntityAttributePolicy(entityClass = Project.class, attributes = {"id", "name", "startDate", "status", "manager", "tasks", "documents"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Project.class, actions = EntityPolicyAction.ALL)
    void project();

    @EntityAttributePolicy(entityClass = Task.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Task.class, actions = EntityPolicyAction.ALL)
    void task();

    @EntityAttributePolicy(entityClass = User.class, attributes = {"firstName", "lastName", "email"}, action = EntityAttributePolicyAction.MODIFY)
    @EntityAttributePolicy(entityClass = User.class, attributes = {"username", "password", "active"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = {EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void user();

    @MenuPolicy(menuIds = {"Project.list", "Task_.list", "Document.list", "User.list"})
    @ViewPolicy(viewIds = {"Project.list", "Task_.list", "Document.list", "Document.detail", "Project.detail", "Task_.detail", "User.detail", "User.list"})
    void screens();
}