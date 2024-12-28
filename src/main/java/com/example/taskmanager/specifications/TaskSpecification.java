package com.example.taskmanager.specifications;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.filters.TaskFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
@Data
public class TaskSpecification implements Specification<Task> {

    private final TaskFilter taskFilter;

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return Specification.allOf(
                titleEqual(),
                descriptionEqual(),
                createdTimestampFrom(),
                createdTimestampTo(),
                startedTimestampFrom(),
                startedTimestampTo(),
                endTimestampFrom(),
                endTimestampTo(),
                projectIdEqual(),
                statusIdEqual(),
                assignedToEqual(),
                createdByEqual()
        ).toPredicate(root, query, criteriaBuilder);
    }

    public Specification<Task> titleEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getTitle()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("title"), taskFilter.getTitle());
            }
        };
    }

    public Specification<Task> descriptionEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getDescription()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("description"), taskFilter.getDescription());
            }
        };
    }

    public Specification<Task> createdTimestampFrom() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getCreatedTimestampFrom()==null) {
                return null;
            }else{
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdTimestamp"), taskFilter.getCreatedTimestampFrom());
            }
        };
    }

    public Specification<Task> createdTimestampTo() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getCreatedTimestampTo()==null) {
                return null;
            }else{
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdTimestamp"), taskFilter.getCreatedTimestampTo());
            }
        };
    }
    public Specification<Task> startedTimestampFrom() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getStartedTimestampFrom()==null) {
                return null;
            }else{
                return criteriaBuilder.greaterThanOrEqualTo(root.get("startedTimestamp"), taskFilter.getStartedTimestampFrom());
            }
        };
    }

    public Specification<Task> startedTimestampTo() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getStartedTimestampTo()==null) {
                return null;
            }else{
                return criteriaBuilder.lessThanOrEqualTo(root.get("startedTimestamp"), taskFilter.getStartedTimestampTo());
            }
        };
    }
    public Specification<Task> endTimestampFrom() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getEndTimestampFrom()==null) {
                return null;
            }else{
                return criteriaBuilder.greaterThanOrEqualTo(root.get("endTimestamp"), taskFilter.getEndTimestampFrom());
            }
        };
    }

    public Specification<Task> endTimestampTo() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getEndTimestampTo()==null) {
                return null;
            }else{
                return criteriaBuilder.lessThanOrEqualTo(root.get("endTimestamp"), taskFilter.getEndTimestampTo());
            }
        };
    }

    public Specification<Task> projectIdEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getProjectId()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("project").get("id"), taskFilter.getProjectId());
            }
        };
    }

    public Specification<Task> statusIdEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getStatusId()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("status").get("id"), taskFilter.getStatusId());
            }
        };
    }

    public Specification<Task> assignedToEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getAssignedTo()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("assignedTo").get("id"), taskFilter.getAssignedTo());
            }
        };
    }

    public Specification<Task> createdByEqual() {
        return (root, query, criteriaBuilder) -> {
            if(taskFilter.getCreatedBy()==null) {
                return null;
            }else{
                return criteriaBuilder.equal(root.get("createdBy").get("id"), taskFilter.getCreatedBy());
            }
        };
    }
}
