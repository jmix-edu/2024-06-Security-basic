package com.company.jmixpmflowbase.security;

import com.company.jmixpmflowbase.entity.Project;
import com.company.jmixpmflowbase.entity.User;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.security.model.RowLevelBiPredicate;
import io.jmix.security.model.RowLevelPolicyAction;
import io.jmix.security.role.annotation.PredicateRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;
import org.springframework.context.ApplicationContext;

@RowLevelRole(name = "ManagerProjectRestrictions", code = ManagerProjectRestrictionsRole.CODE)
public interface ManagerProjectRestrictionsRole {
    String CODE = "manager-project-restrictions";


    @PredicateRowLevelPolicy(entityClass = Project.class, actions = {RowLevelPolicyAction.UPDATE, RowLevelPolicyAction.DELETE})
    default RowLevelBiPredicate<Project, ApplicationContext> projectPredicate() {
        return (project, applicationContext) -> {
            CurrentAuthentication currentAuthentication = applicationContext.getBean(CurrentAuthentication.class);
            User user = (User) currentAuthentication.getUser();

            return user.equals(project.getManager());
        };
    }
}