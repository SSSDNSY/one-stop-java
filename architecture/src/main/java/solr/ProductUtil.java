///*
// * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
// * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
// * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
// * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
// * Vestibulum commodo. Ut rhoncus gravida arcu.
// */
//
//package solr;
//
//import cn.hutool.core.io.file.FileReader;
//import solr.entity.Product;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author fun.pengzh
// * @class middleware.solr.ProductUtil
// * @desc
// * @since 2021-04-16
// */
//public final class ProductUtil {
//
//    public static void main(String[] args) throws IOException {
//
//        String fileName = "140k_products.txt";
//
//        List<Product> products = file2list(fileName);
//
//        System.out.println(products.size());
//
//    }
//
//    public static List<Product> file2list(String fileName) throws IOException {
//        File f = new File(fileName);
//        FileReader fr = new FileReader("140k_products.txt", "UTF-8");
//        List<String> lines = fr.readLines();
//
//        List<Product> products = new ArrayList<>();
//        for (String line : lines) {
//            Product p = line2product(line);
//            products.add(p);
//        }
//        return products;
//    }
//
//    private static Product line2product(String line) {
//        Product p = new Product();
//        String[] fields = line.split(",");
//        p.setId(Integer.parseInt(fields[0]));
//        p.setName(fields[1]);
//        p.setCategory(fields[2]);
//        p.setPrice(Float.parseFloat(fields[3]));
//        p.setPlace(fields[4]);
//        p.setCode(fields[5]);
//        return p;
//    }
//
//}
