package com.loeyae.cloud.commons.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.loeyae.cloud.commons.tool.BeanUtils;

import java.util.List;

/**
 * Page Result.
 *
 * @author ZhangYi<loeyae @ gmail.com>
 * @version 1.0
 * @date 2020-06-27
 */
public class PageResult<T> {

    private long current = 1L;

    private long size = 0L;

    private long total = 0L;

    private long page = 0L;

    private List<T> rows;

    public PageResult()
    {

    }

    public static <T> PageResult<T> fromPage (IPage data, Class<T> target)
    {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.current = data.getCurrent();
        pageResult.size = data.getSize();
        pageResult.page = data.getPages();
        pageResult.total = data.getTotal();
        pageResult.rows = BeanUtils.copyObjListProperties(data.getRecords(), target);
        return pageResult;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

}