package com.ecommerce.project.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String field;
    String fieldName;
    long fieldId;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String resourceName, String field, String fieldName) {
        super(String.format("Resource %s not found with name %s and field %s", resourceName, field, fieldName));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldName = fieldName;
    }

    public ResourceNotFoundException(String resourceName, String field, long fieldId) {
        super(String.format("Resource %s not found with name %s and field %d", resourceName, field, fieldId));
        this.resourceName = resourceName;
        this.field = field;
        this.fieldId = fieldId;
    }
}
