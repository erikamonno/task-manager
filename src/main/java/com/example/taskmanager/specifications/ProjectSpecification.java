package com.example.taskmanager.specifications;

import com.example.taskmanager.entities.Project;
import com.example.taskmanager.filters.ProjectFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
@Data
public class ProjectSpecification implements Specification<Project> {

    private final ProjectFilter filter;

    @Override
    public Predicate toPredicate(Root<Project> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                nameEqual(),
                descriptionEqual(),
                createdTimestampLessThanOrEqualTo()
        ).toPredicate(root, query, criteriaBuilder);
    }

    public Specification<Project> nameEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getName()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("name"), filter.getName());
            }
        };
    }

    public Specification<Project> descriptionEqual() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getDescription()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("description"), filter.getDescription());
            }
        };
    }

    public Specification<Project> createdTimestampLessThanOrEqualTo() {
        return (root, query, criteriaBuilder) -> {
            if(filter.getCreatedTimestamp()==null) {
                return null;
            }else{
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdTimestamp"), filter.getCreatedTimestamp());
            }
        };
    }
}
