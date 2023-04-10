package basic;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-04-10
 */
@Slf4j
public class BigDecimalTest {



    public static void main(String[] args) {

         BigDecimal figureOne = BigDecimal.valueOf(2340.59354);
         String format1 = new DecimalFormat("#.0000000").format(figureOne);//
         log.info(format1);//结果：.5935400
         String format2 = new DecimalFormat("#.00").format(figureOne);//保留俩位小数
         log.info(format2);//结果：.59
         String format3 = new DecimalFormat("0.00").format(figureOne);//保留俩位小数
         log.info(format3);//结果：0.59
         BigDecimal figureTwo = BigDecimal.valueOf(5201314.59354);
         String format4 = new DecimalFormat("###,###0.00").format(figureTwo);//三位一逗，保留一位
         log.info(format4);//结果：520,1314.59
         String format5 = new DecimalFormat("00.000").format(figureTwo);//取两位整数和三位小数
         log.info(format5);//结果：5201314.594
         String format6 = new DecimalFormat("0").format(figureTwo);//取所有整数
         log.info(format6);//结果：5201315
         String format7 = new DecimalFormat("#").format(figureTwo);//取所有整数部分
         log.info(format7);//结果：5201315
         String format8 = new DecimalFormat("#.##%").format(figureTwo);//以百分比方式计数，并取两位小数
         log.info(format8);//结果：520131459.35%
         //科学计数法
         long c = 299792458;//光速：299792458m/s
         String format9 = new DecimalFormat("#.#####E0").format(c);//五位小数
         log.info(format9);//结果：2.99792E8
         String format10 = new DecimalFormat("00.####E0").format(c);//两位整数和四位小数
         log.info(format10);//结果：29.9792E7
         String format11 = new DecimalFormat(",###").format(c);
         // 三位一逗
         log.info(format11);//结果：299,792,458
         String format12 = new DecimalFormat("光速大小为：###m/s").format(c);//和文本拼接
         log.info(format12);//结果：光速大小为：299792458m/s
    }
}
