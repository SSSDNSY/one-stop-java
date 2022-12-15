package middleware.solr.entity;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author fun.pengzh
 * @class middleware.solr.entity.Product
 * @desc
 * @since 2021-04-16
 */
public class Product {
    @Field
    int id;
    @Field
    String name;
    @Field
    String category;
    @Field
    float price;
    @Field
    String place;
    @Field
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
