package com.example.springbootlab.common.dto;

public class PageForm {

    /**
     * 页码
     */
    private Integer pageNumber;


    /**
     * 每页显示的数量
     */
    private Integer pageSize;

    /**
     * 计算当前页 ,方便mysql 进行分页查询
     *
     * @return 返回 pageForm
     */
    public PageForm calcCurrent() {

        pageNumber = (pageNumber - 1) * pageSize;
        return this;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
