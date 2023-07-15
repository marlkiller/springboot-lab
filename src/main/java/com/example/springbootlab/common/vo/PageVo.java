package com.example.springbootlab.common.vo;

import com.example.springbootlab.common.dto.PageForm;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageVo<T> {
    private List<T> list;
    private int total;
    private int pageNumber;
    private int pageSize;
    private int totalPage;

    public PageVo(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public PageVo(List<T> list, int total, int pageNumber, int pageSize) {
        this.list = list;
        this.total = total;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPage = total / pageSize;
    }


    public static <T> PageVo<T> of(List<T> list, int total) {
        return new PageVo<>(list, total);
    }
    public static <T> PageVo<T> of(PageForm pageForm, List<T> list, int total) {
        PageVo<T> pageVo = new PageVo<>(list, total);
        Integer size = pageForm.getPageSize();
        pageVo.setPageSize(size);
        pageVo.setPageNumber(pageForm.getPageNumber());
        pageVo.setTotalPage(total % size > 0 ? total / size + 1 : total / size);
        return pageVo;
    }

    public static <T> PageVo<T> of(List<T> list, int total, int pageNumber, int pageSize) {
        return new PageVo<>(list, total, pageNumber, pageSize);
    }

    public static <T> PageVo empty() {
        return null;
    }

    public <R> PageVo<R> convert(Function<T, R> func) {
        if (null == list) {
            return empty();
        }
        return PageVo.of(list.stream().map(func).collect(Collectors.toList()), total);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}