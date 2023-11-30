package pers.zjh.shop.util;

// 分页类
public class Page {

    // 开始页数
    private int start;

    // 每页显示个数
    private int count;

    // 总个数
    private int total;

    // 参数,辅助分页
    private String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    // 默认每页显示7条数据
    private static final int defaultCount = 7;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Page(){
        count = defaultCount;
    }

    public Page(int start, int count) {
        this();
        this.start = start;
        this.count = count;
    }

    // 是否有前一页
    public boolean isHasPreviouse(){
        return start!=0;
    }

    // 是否有下一页
    public boolean isHasNext(){
        return start!=getLast();
    }

    // 返回最后一页第一条数据对应行数
    public int getLast(){
        int last;
        if(0 == total % count){
            last = total - count;
        }else{
            last = total - total % count;
        }
        last = last<0?0:last;
        return last;
    }

    // 返回总页数
    public int getTotalPage(){
        int totalPage;
        if(0 == total % count){
            totalPage = total / count;
        }else{
            totalPage = total / count + 1;
        }
        if(0==totalPage){
            totalPage = 1;
        }
        return totalPage;
    }
}
