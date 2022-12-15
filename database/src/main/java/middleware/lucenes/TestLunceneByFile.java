package middleware.lucenes;

/**
 * @author fun.pengzh
 * @class middleware.lucenes.TestLunceneByFile
 * @desc 从140k条产品记录中索引
 * @since 2021-04-03
 */
public class TestLunceneByFile {
    public static void main(String[] args) throws Exception {
        System.out.println(SearchFileUtil.search("小米"));
    }
}

/**
 * 产品实体类
 */
class Product {
    int id;
    String name;
    String category;
    float price;
    String place;

    String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", place="
                + place + ", code=" + code + "]";
    }
}