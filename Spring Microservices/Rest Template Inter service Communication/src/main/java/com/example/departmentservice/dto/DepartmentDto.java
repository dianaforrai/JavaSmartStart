package com.example.departmentservice.dto;

public class DepartmentDto {
    private Long id;
    private String departmentName;
    private String departmentCode;
    private String departmentAddress;

    // Constructors, getters, setters
    public DepartmentDto() {}

    public DepartmentDto(Long id, String departmentName, String departmentCode, String departmentAddress) {
        this.id = id;
        this.departmentName = departmentName;
        this.departmentCode = departmentCode;
        this.departmentAddress = departmentAddress;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
    public String getDepartmentCode() { return departmentCode; }
    public void setDepartmentCode(String departmentCode) { this.departmentCode = departmentCode; }
    public String getDepartmentAddress() { return departmentAddress; }
    public void setDepartmentAddress(String departmentAddress) { this.departmentAddress = departmentAddress; }
}
