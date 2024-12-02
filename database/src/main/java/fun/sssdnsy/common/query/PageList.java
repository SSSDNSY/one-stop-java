package fun.sssdnsy.common.query;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Restful接口返回的分页列表数据
 */
public class PageList<E> {

    /**
     * 结果列表
     */
    List<E> rows;

    /**
     * 总记录数
     */
    long total;

    /**
     * 结果列表
     */
    int page;

    /**
     * 每页条数
     */
    int pageSize;

    public PageList() {
    }

    public PageList(List<E> c) {
        if (c instanceof Page) {
            Page<E> page = (Page<E>) c;
            this.rows = page.getResult();
            this.total = page.getTotal();
            this.page = page.getPageNum();
            this.pageSize = page.getPageSize();
        } else {
            this.rows = c;
            this.total = this.rows.size();
        }
        if (this.total < 0) {
            this.total = 0;
        }
    }

    public PageList(Page<E> page) {
        this.rows = page.getResult();
        this.total = page.getTotal();
        this.page = page.getPageNum();
        this.pageSize = page.getPageSize();
        if (this.total < 0) {
            this.total = 0;
        }
    }

    public static PageList of() {
        return new PageList();
    }

    public static PageList of(List c) {
        return new PageList(c);
    }

}
