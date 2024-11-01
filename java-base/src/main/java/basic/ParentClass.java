package basic;

/**
 * @author pengzh
 * @date 2020-03-05
 */
public abstract class ParentClass {
    void tradeReg() throws Exception {
        String code = getOrderTypeCode();
        System.out.println(this.getClass().getName() + "：父类登记台账 " + code);
    }

    public abstract String getOrderTypeCode() throws Exception;

}
