package fun.sssdnsy.common.query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;

/**
 * 分页对象
 * 继承自RowBounds，可做为SqlSessionTemplate查询时的分页参数
 */
@Getter
@Setter
@ToString
public class PageBean extends RowBounds implements Serializable {

    private static final long serialVersionUID = 1;

    /**
     * 默认每页显示的记录数
     */
    public static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 默认显示第一页
     */
    public final static int NO_PAGE = 1;
    /**
     * 当前第几页
     */
    protected int page = NO_PAGE;

    /**
     * 每页数量
     */
    protected int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 是否展示全部， 默认展示全部
     */
    protected boolean showTotal = true;

    public PageBean() {
    }

    public PageBean(Integer page) {
        this.page = page;
    }

    public PageBean(Boolean showTotal) {
        this.showTotal = showTotal;
    }

    public PageBean(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageBean(Integer page, Integer pageSize, Boolean showTotal) {
        this.page = page;
        this.pageSize = pageSize;
        this.showTotal = showTotal;
    }

    @JsonIgnore
    @Override
    public int getLimit() {
        return this.pageSize;
    }

    /**
     * 获取起始行号
     *
     * @return 起始行号
     */
    @JsonIgnore
    @Override
    public int getOffset() {
        return this.page > 0 ? (this.page - 1) * this.pageSize : 0;
    }

}
