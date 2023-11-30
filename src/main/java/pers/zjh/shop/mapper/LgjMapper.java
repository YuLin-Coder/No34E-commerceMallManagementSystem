package pers.zjh.shop.mapper;

        import pers.zjh.shop.pojo.Admin;
        import java.util.List;

public interface LgjMapper {
    public  List<Admin> selectByName(String uname);
}
